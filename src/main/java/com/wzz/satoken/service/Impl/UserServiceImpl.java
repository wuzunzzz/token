package com.wzz.satoken.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wzz.satoken.Exception.BusinessException;
import com.wzz.satoken.Mapper.UserMapper;
import com.wzz.satoken.pojo.User111;
import com.wzz.satoken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User111 LoginIn(String name, String pwd) {
        QueryWrapper<User111> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        wrapper.eq("pwd",pwd);
        User111 user = userMapper.selectOne(wrapper);

        return user;
    }

    @Override
    public void Register(String name, String pwd,String email) throws BusinessException {
        User111 user = new User111();
        QueryWrapper<User111> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        User111 user111 = userMapper.selectOne(wrapper);
        if(user111==null){
            user.setName(name);
            user.setPwd(pwd);
            user.setEmail(email);
            userMapper.insert(user);
        }else {
            throw new BusinessException("用户名已存在");
        }


    }

    @Override
    public void update(Long id,String pwd) {
        User111 user = new User111();
        user.setId(id);
        user.setPwd(pwd);
        userMapper.updateById(user);

    }
}
