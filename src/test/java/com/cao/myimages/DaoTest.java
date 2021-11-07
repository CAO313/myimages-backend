package com.cao.myimages;

import com.cao.myimages.entity.Emoji;
import com.cao.myimages.service.EmojiService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class DaoTest {
    @Resource
    private EmojiService emojiService;
    @Test
    public void queryById(){
        Emoji emoji = new Emoji();
        emoji.setUrl("123455");
        System.out.println(emojiService.save(emoji));
    }
    @Test
    public void update(){

    }
}
