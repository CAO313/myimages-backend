package com.cao.myimages.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cao.myimages.entity.Emoji;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 表情 服务类
 * </p>
 *
 * @author cao123
 * @since 2021-10-29
 */
public interface EmojiService extends IService<Emoji> {
    boolean updateNameByUrl(String name, String url);
    boolean updateReviewStatusById(Integer id,Integer status);
}
