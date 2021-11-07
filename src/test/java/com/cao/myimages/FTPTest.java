package com.cao.myimages;

import com.cao.myimages.entity.Emoji;
import com.cao.myimages.mapper.EmojiMapper;
import com.cao.myimages.service.EmojiService;
import com.cao.myimages.utils.FTPUtils;
import org.apache.commons.net.ftp.FTP;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class FTPTest {
    private FTPUtils ftpUtils = new FTPUtils();
    @Resource
    EmojiMapper emojiMapper;

    @Test
    public void deleteTest() {
        List<Emoji> emojis = emojiMapper.getAll();
        for (Emoji emoji : emojis) {
            String url = emoji.getUrl();
            String filename = url.substring(url.lastIndexOf('/')+1);
            System.out.println(filename);
            boolean b = ftpUtils.deleteFile("/home/www/pages", filename);
            System.out.println(b);
        }

    }
}
