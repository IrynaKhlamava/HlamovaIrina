package com.company.api.dao;

import com.company.model.Role;
import com.company.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleDao{

    Role getById(Long id);
}
