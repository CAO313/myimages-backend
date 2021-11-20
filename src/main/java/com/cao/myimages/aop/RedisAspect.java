package com.cao.myimages.aop;

import com.cao.myimages.entity.Emoji;
import com.cao.myimages.service.RedisService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Aspect
public class RedisAspect {
    @Resource
    RedisService redisService;
//    @Pointcut("execution(* com.cao.myimages.controller.*.*(..))")
//    private void clear(){}
    @After("execution(* com.cao.myimages.controller.EmojiController.delete(..))")
    public void clearRedis(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Emoji emoji =(Emoji) args[0];
        String tag = emoji.getTag();
        redisService.del(tag);
        System.out.println("after delete clear redis");
    }
    @After("execution(* com.cao.myimages.controller.EmojiController.review(..))")
    public void clearRedis1(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Emoji emoji =(Emoji) args[0];
        String tag = emoji.getTag();
        redisService.del(tag);
        System.out.println("after update clear redis");
    }
}
