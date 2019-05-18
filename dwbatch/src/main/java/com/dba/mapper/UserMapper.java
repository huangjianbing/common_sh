package com.dba.mapper;

import com.dba.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author:huangjb
 * Date:2018/11/2
 * Description:
 */
@Mapper
public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */
    public List<User> getAllUser();

    /**
     * 查询单个用户详情
     * @param id
     * @return
     */
    public User getUserById(String id);

    /**
     * 更新用户
     * @param user
     */
    public void updateUser (User user);

    /**
     * 根据电话号码查询用户
     * @param phone
     * @return
     */
    public User getUserByPhone(String phone);

    /**
     * 通过电话删除用户
     * @param phone
     * @return
     */
    public int delete(String phone);

    /**
     * z注册用户
     * @param user
     */
    void registerUser(User user);

    /**
     * 激活用户
     * @param userUpdate
     */
    void active(User userUpdate);

    /**
     * 用户登录
     * @param user
     * @return
     */

    User login(User user);
}
