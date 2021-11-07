package com.cao.myimages.security.Handler;
import com.google.gson.Gson;
import com.cao.myimages.security.Message.Msg;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeny implements AccessDeniedHandler{
    @Resource
    Gson gson;
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
           Msg result = Msg.denyAccess("无权访问,need Authorities!");
           httpServletResponse.setContentType("application/json;charset=utf-8");
           httpServletResponse.getWriter().write(gson.toJson(result));
    }
}
