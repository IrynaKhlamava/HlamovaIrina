package com.company.security;

import com.company.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser {

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),user.getRoles()
        );
    }
}
