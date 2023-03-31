package com.wzz.satoken.service;

import com.wzz.satoken.Exception.BusinessException;
import com.wzz.satoken.pojo.User111;

public interface UserService {
    User111 LoginIn(String name, String pwd);

    void Register(String name ,String pwd ,String email) throws BusinessException;

    void update(Long id,String pwd);
}
