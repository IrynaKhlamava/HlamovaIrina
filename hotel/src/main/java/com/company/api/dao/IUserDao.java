package com.company.api.dao;


import com.company.model.Role;
import com.company.model.User;

import java.util.Set;

public interface IUserDao {

    User getById(Long id);


    Object save(User user);

    Set<Role> getRolesById(Long id);

    User findByUsername(String username);
}
