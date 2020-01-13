package com.springapp.mvc.service;

import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;

/**
 * Created by kot on 22.03.17.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    //устанавиливаем роли-разрешения для пользователя
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for(Role role: user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }
}
