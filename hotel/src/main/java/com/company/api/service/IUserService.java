package com.company.api.service;

import com.company.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);

    void save(User user); /**/

    User getByLogin(String login);/**/

    User getById(Long id);/**/

    User findByUsername(String username);
}
