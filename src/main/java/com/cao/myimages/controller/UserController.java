package com.cao.myimages.controller;

import com.cao.myimages.security.Message.Msg;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:8080","http://106.14.136.249:8080","http://106.14.136.249"})
public class UserController {

    @GetMapping("/index")
    public String index(){
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            System.out.println(authority);
        }
        return "index";
    }
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/check")
    public Msg hello(){
        Msg result = Msg.success("已认证");
        return result;
    }

    @PostMapping("/login")
    public String login() {
        return "success";
    }
}
