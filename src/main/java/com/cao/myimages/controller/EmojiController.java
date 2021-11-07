package com.cao.myimages.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cao.myimages.constant.RedisConstant;
import com.cao.myimages.request.EmojiQueryRequest;
import com.cao.myimages.result.EmojiResult;
import com.cao.myimages.constant.MessageConstant;
import com.cao.myimages.entity.Emoji;
import com.cao.myimages.service.EmojiService;
import com.cao.myimages.utils.FTPUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 表情 前端控制器
 * </p>
 *
 * @author cao123
 * @since 2021-10-29
 */
@RestController
@RequestMapping(value = "/emoji")
@CrossOrigin(origins = {"http://127.0.0.1:8080","http://106.14.136.249:8080","http://106.14.136.249"})
public class EmojiController {
    @Resource
    private EmojiService emojiService;

    @Resource
    private JedisPool jedisPool;

    /**
     * 添加emoji
     *
     * @param emoji
     * @return EmojiResult
     */
    @PostMapping("/add")
    public EmojiResult add(@RequestBody Emoji emoji){
      //  System.out.println(emoji.getName()+emoji.getUrl());
        boolean flag = emojiService.save(emoji);
        if (flag) {
            Jedis jedis = jedisPool.getResource();
            jedis.sadd(RedisConstant.URL_DB,emoji.getUrl());
            jedis.close();
            return new EmojiResult(true, MessageConstant.EMOJI_ADD_SUCCESS,true);
        }
        return new EmojiResult(false, MessageConstant.EMOJI_ADD_FAILED);
    }

    /**
     * 删除ById
     *
     * @param map
     * @return EmojiResult
     */
    @PostMapping("/delete")
    public EmojiResult delete(@RequestBody Map<String,Integer> map) {
        Integer id = map.get("id");
        System.out.println(id);
        if (id == null) {
            return new EmojiResult(false, MessageConstant.EMOJI_DELETE_FAILED);
        }
        boolean flag = emojiService.removeById(id);
        if (flag == true) {
            return new EmojiResult(true, MessageConstant.EMOJI_DELETE_SUCCESS,true);
        }
        return new EmojiResult(false, MessageConstant.EMOJI_DELETE_FAILED);
    }

    /**
     * 更新
     *
     * @param emoji
     * @return EmojiResult
     */
    @PostMapping("/update")
    public EmojiResult update(@RequestBody Emoji emoji) {
        if (emoji == null || emoji.getId() != null) {
            return new EmojiResult(false, MessageConstant.EMOJI_UPDATE_FAILED);
        }
        emojiService.updateById(emoji);
        return new EmojiResult(true, MessageConstant.EMOJI_UPDATE_SUCCESS,true);
    }


    /**
     * 查找emoji
     *
     * @return EmojiResult
     */
    @GetMapping(value = "/query")
    public EmojiResult queryAll( EmojiQueryRequest emojiQueryRequest) {
        System.out.println(emojiQueryRequest);
        int pageNum = emojiQueryRequest.getPageNum();
        int pageSize = emojiQueryRequest.getPageSize();
        final int MAX_VALUE = 777;
        if(pageNum*pageSize>=MAX_VALUE){
            return new EmojiResult(false,"超出最大查询数");
        }
        String name = emojiQueryRequest.getName();
        QueryWrapper<Emoji> queryWrapper = new QueryWrapper();
        if(StringUtils.isNoneBlank(name)){
            queryWrapper.like("name",name);
        }
        String tag = emojiQueryRequest.getTag();
        if(StringUtils.isNoneBlank(tag)){
            queryWrapper.like("tag",tag);
        }
        Integer reviewStatus = emojiQueryRequest.getReviewStatus();
        if(reviewStatus!=null){
            queryWrapper.eq("review_status",reviewStatus);
        }
        queryWrapper.orderByDesc("create_time");

        Page<Emoji> page = emojiService.page(new Page<Emoji>(pageNum,pageSize),queryWrapper);
        return new EmojiResult(true, MessageConstant.EMOJI_QUERY_SUCCESS, page);
    }

    /**
     * 加载表情到服务器
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public EmojiResult upload(MultipartFile file){
        System.out.println(file.getOriginalFilename());
        try {
            if(file.isEmpty()){
                throw new RuntimeException("文件为空");
            }
            String name = UUID.randomUUID().toString();
            int lastFix = file.getOriginalFilename().lastIndexOf('.');
            name = name + file.getOriginalFilename().substring(lastFix);
            Emoji emoji = new Emoji();
            String url = "http://101.34.14.236:313/"+name;
            FTPUtils ftpUtils = new FTPUtils();
            ftpUtils.uploadFile("/home/www/pages",name, file.getInputStream());
            Jedis jedis = jedisPool.getResource();
            jedis.sadd(RedisConstant.URL_SERVER,url);
            jedis.close();
            return new EmojiResult(true,MessageConstant.EMOJI_UPLOAD_SUCCESS,url);
        }
        catch (Exception e){
            e.printStackTrace();
            return new EmojiResult(false,MessageConstant.EMOJI_UPLOAD_FAILED);
        }
    }

    /**
     * 审核
     * @param emoji
     * @return
     */
    @PostMapping("/review")
    public EmojiResult review(@RequestBody Emoji emoji){
        Integer id = emoji.getId();
        Integer status = emoji.getReviewStatus();
        if(id==null){
            return new EmojiResult(false,MessageConstant.OPERATE_FAIED);
        }
       boolean bo = emojiService.updateReviewStatusById(id,status);
        if(bo){
            return new EmojiResult(true,MessageConstant.OPERATE_SUCCESS,true);
        }
        return new EmojiResult(false,MessageConstant.OPERATE_FAIED);
    }
}