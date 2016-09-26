package com.zte.ums.watchdog.dao;

import com.zte.ums.watchdog.model.User;

import java.util.List;

/**
 * Created by duhui on 2016/9/23.
 */
public interface UserDao {
    void createUser(String phoneNumber,String name, String password, String mail,String wechat,String groups, String createTime,int userLevel);
    void deleteUser(String phoneNumber);
    void editUser(String phoneNumber,String name, String password, String mail,String wechat,String groups, String createTime,int userLevel);
    User searchUser(String phoneNumber);

    List<User> getUsers();
    User getUserByTel(String tel);
    User getUserById(int userId);
    User getUserByUserName(String userName);
}
