package com.cao.myimages.controller;

import com.cao.myimages.entity.Music;
import com.cao.myimages.mapper.MusicMapper;
import com.cao.myimages.result.MusicResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/music")
@CrossOrigin(origins = {"http://127.0.0.1:8080","http://106.14.136.249:8080","http://106.14.136.249"})
public class MusicController {
    @Resource
    MusicMapper musicMapper;
    @GetMapping("/search")
    public MusicResult searchOne(){
       Music music = musicMapper.searchOne();
       return new MusicResult(true,"success",music);
    }
}
