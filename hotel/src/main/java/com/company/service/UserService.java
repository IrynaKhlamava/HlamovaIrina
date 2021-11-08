package com.company.service;

import com.company.api.dao.IRoleDao;
import com.company.api.dao.IUserDao;
import com.company.api.service.IUserService;
import com.company.model.Role;
import com.company.model.User;
import com.company.security.SecurityUser;
import org.apache.log4j.Logger;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserService implements IUserService {

    private IUserDao userDao;

    private IRoleDao roleDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    public UserService(IUserDao userDao, IRoleDao roleDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getById(2L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public User getByLogin(String login) {
        return userDao.findByUsername(login);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDB = userDao.findByUsername(username);
        if (userFromDB != null) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            return SecurityUser.fromUser(userFromDB);
        } else {
            return null;
        }
    }


    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }


}

