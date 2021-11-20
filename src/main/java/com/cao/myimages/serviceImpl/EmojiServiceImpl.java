package com.cao.myimages.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cao.myimages.entity.Emoji;
import com.cao.myimages.mapper.EmojiMapper;
import com.cao.myimages.service.EmojiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 表情 服务实现类
 * </p>
 *
 * @author cao123
 * @since 2021-10-29
 */
@Service
public class EmojiServiceImpl extends ServiceImpl<EmojiMapper, Emoji> implements EmojiService {

    @Resource
    private EmojiMapper emojiMapper;
    @Override
    public boolean updateNameByUrl(String name, String url) {
        try{
           emojiMapper.updateNameByUrl(name,url);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateReviewStatusById(Integer id, Integer status) {
        try{
            emojiMapper.updateReviewStatusById(id,status);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<Emoji> getByTag(String tag, Integer reviewStatus) {
        return emojiMapper.getByTag( tag,  reviewStatus);
    }
}
