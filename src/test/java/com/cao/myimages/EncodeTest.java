package com.cao.myimages;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
public class EncodeTest {
    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    public void encodeTest(){
        String encode = bCryptPasswordEncoder.encode("410222");
        System.out.println(encode);
    }
}
