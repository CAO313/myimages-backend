package com.cao.myimages.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cao.myimages.constant.MessageConstant;
import com.cao.myimages.entity.Tag;
import com.cao.myimages.result.TagResult;
import com.cao.myimages.service.TagService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 标签 前端控制器
 * </p>
 *
 * @author cao123
 * @since 2021-10-29
 */
@RestController
@RequestMapping("/tag")
@CrossOrigin(origins = {"http://127.0.0.1:8080","http://106.14.136.249:8080","http://106.14.136.249"})
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping(value = "/search",produces = "application/json;charset=UTF-8")
    public TagResult search(Integer pageSize){
        if (pageSize == null) {
           return new TagResult(false,MessageConstant.TAG_QUERY_FAILED);
        }
        // 封装查询请求
        QueryWrapper<Tag> tagQueryWrapper = new QueryWrapper<>();
        tagQueryWrapper.eq("is_delete",0);
        tagQueryWrapper.orderByDesc("create_time");
        Page<Tag> page = tagService.page(new Page<>(1, pageSize),
                tagQueryWrapper);
        return new TagResult(true, MessageConstant.TAG_QUERY_SUCCES,page);
    }
}

