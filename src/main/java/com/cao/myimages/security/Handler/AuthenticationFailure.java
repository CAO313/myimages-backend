package com.cao.myimages.security.Handler;

import com.cao.myimages.security.Message.Msg;
import com.google.gson.Gson;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFailure implements AuthenticationFailureHandler {
    @Resource
    Gson gson;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Msg msg=null;
        if(e instanceof UsernameNotFoundException){
            msg= Msg.fail(e.getMessage());
        }else if(e instanceof BadCredentialsException){
            msg=Msg.fail("密码错误!!");
        }else {
            msg=Msg.fail(e.getMessage());
        }
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //返回给前台
        httpServletResponse.getWriter().write(gson.toJson(msg));
    }
}
