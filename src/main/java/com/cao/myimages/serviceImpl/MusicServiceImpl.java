package com.cao.myimages.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cao.myimages.entity.Music;
import com.cao.myimages.mapper.MusicMapper;
import com.cao.myimages.service.MusicService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cao123
 * @since 2021-11-04
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {
}
