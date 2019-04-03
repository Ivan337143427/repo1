package com.it.service.imp;

import com.it.domain.Role;
import com.it.domain.UserInfo;
import com.it.mapper.UserInfoMapper;
import com.it.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoMapper.findByUsername(username);
        if(userInfo!=null){
            ArrayList<GrantedAuthority> list=new ArrayList<>();
            for (Role role : userInfo.getRoles()) {
                list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
            }
            BCryptPasswordEncoder bcpe=new BCryptPasswordEncoder();
            return new User(username,bcpe.encode(userInfo.getPassword()),true,true,true,userInfo.getStatus()==1,list);
        }else{ return  null;}

    }
}
