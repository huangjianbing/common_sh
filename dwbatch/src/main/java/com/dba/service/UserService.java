package com.dba.service;

import com.dba.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:huangjb
 * Date:2018/11/2
 * Description:
 */
@Service
public interface UserService {

    public List<User> getAllUser();

    public User getUserById(String id);

    public User updateUser(User user);

    public User getUserByphone(String phone);

    Boolean registerUser(User user);

    Boolean userService(String key);

    Boolean login(User user);
}
