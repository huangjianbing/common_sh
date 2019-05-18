package com.dba.service.impl;

import com.dba.mapper.UserMapper;
import com.dba.pojo.User;
import com.dba.service.MailService;
import com.dba.service.UserService;
import com.dba.utils.HashUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Author:huangjb
 * Date:2018/11/2
 * Description:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User updateUser(User user) {
        userMapper.updateUser(user);
        return user;
    }

    @Override
    public User getUserByphone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
    @Transactional(noRollbackFor = Exception.class )
    public Boolean registerUser(User user) {
        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        user.setPassword(HashUtils.encryPassword(user.getPassword()));
        userMapper.registerUser(user);
        mailService.regietrNotify(user.getPhone());
        return true;
    }

    @Override
    public Boolean userService(String key) {
        return mailService.activeUser(key);
    }

    @Override
    public Boolean login(User user) {
        user.setPassword(HashUtils.encryPassword(user.getPassword()));
        User user1=userMapper.login(user);
        if(user==null){
            return false;
        }
        return true;
    }
}
