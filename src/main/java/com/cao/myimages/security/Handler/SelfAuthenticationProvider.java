package com.cao.myimages.security.Handler;

import com.cao.myimages.serviceImpl.UserServiceImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {
    @Resource
    UserServiceImpl userServiceImpl;

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = userServiceImpl.loadUserByUsername(username);
        boolean check = bCryptPasswordEncoder.matches(password,userDetails.getPassword());
        if(!check){
            throw new BadCredentialsException("密码不正确!");
        }
        return new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
