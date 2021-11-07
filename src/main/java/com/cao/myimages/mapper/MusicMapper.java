package com.cao.myimages.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cao.myimages.entity.Music;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cao123
 * @since 2021-11-04
 */
public interface MusicMapper extends BaseMapper<Music> {
        @Select("SELECT * FROM music AS t1 JOIN (SELECT ROUND(RAND()*((SELECT MAX(id) FROM music)-(SELECT MIN(id) FROM music))+(SELECT MIN(id) FROM music)) AS id) AS t2 WHERE t1.id>=t2.id ORDER BY t1.id LIMIT 1;")
        Music searchOne();
}
