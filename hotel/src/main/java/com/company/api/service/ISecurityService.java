package com.company.api.service;

public interface ISecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
