package com.cao.myimages.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cao.myimages.entity.User;
import com.cao.myimages.mapper.UserMapper;
import com.cao.myimages.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cao123
 * @since 2021-11-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {
    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_name",s);
        User user=userMapper.selectOne(wrapper);    //user即为查询结果
        if(user==null){
            throw new UsernameNotFoundException("用户名错误！！");
        }
        //获取用户权限，并把其添加到GrantedAuthority中
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(user.getRole());
        grantedAuthorities.add(grantedAuthority);

        //方法的返回值要求返回UserDetails这个数据类型，  UserDetails是接口，找它的实现类就好了
        //new org.springframework.security.core.userdetails.User(String username,String password,Collection<? extends GrantedAuthority> authorities) 就是它的实现类
        return new org.springframework.security.core.userdetails.User(s,user.getPassword(),grantedAuthorities);
    }
}
