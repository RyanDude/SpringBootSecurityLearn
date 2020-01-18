package com.example.demo.service;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userMapper.findByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("user not found");
        }
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
        return user;
    }
    private List<GrantedAuthority> grantedAuthorities(String roles){
        List<GrantedAuthority> authorities=new ArrayList<>();
        String[] roleArray=roles.split(";");
        if(roles!=null && !"".equals(roles)){
            for(String role: roleArray){
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        return authorities;
    }
}
