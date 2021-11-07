package com.cao.myimages.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cao.myimages.entity.Emoji;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.EmptyStackException;
import java.util.List;

/**
 * <p>
 * 表情 Mapper 接口
 * </p>
 *
 * @author cao123
 * @since 2021-10-29
 */
public interface EmojiMapper extends BaseMapper<Emoji> {
    @Update("update emoji set name = #{name} where url = #{url}")
    void updateNameByUrl(@Param("name") String name,@Param("url") String url);
    @Update("update emoji set review_status = #{status} where id = #{id}")
    void updateReviewStatusById(Integer id,Integer status);
    @Select("select * from emoji")
    List<Emoji> getAll();
}
