package com.cao.myimages.security.Handler;

import com.cao.myimages.security.Message.Msg;
import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationLogout implements LogoutSuccessHandler {
    @Resource
    Gson gson;
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Msg result = Msg.success("注销成功");
        httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(gson.toJson(result));
    }
}
