package com.company.service;

import com.company.api.dao.IRoleDao;
import com.company.api.service.IRoleService;
import org.apache.log4j.Logger;

public class RoleService implements IRoleService {

    private static final Logger LOGGER = Logger.getLogger(RoleService.class.getName());

    private final IRoleDao roleDao;

    public RoleService(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }
}
