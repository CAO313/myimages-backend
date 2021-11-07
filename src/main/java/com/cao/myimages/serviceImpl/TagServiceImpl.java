package com.cao.myimages.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cao.myimages.entity.Tag;
import com.cao.myimages.mapper.TagMapper;
import com.cao.myimages.service.TagService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author cao123
 * @since 2021-10-29
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
