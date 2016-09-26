package com.zte.ums.watchdog.service.impl;

import com.zte.ums.watchdog.dao.UserDao;
import com.zte.ums.watchdog.model.Group;
import com.zte.ums.watchdog.model.User;
import com.zte.ums.watchdog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 10174957 on 2016/9/23.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    @Override
    public void createUser(String phoneNumber, String name, String password, String mail, String wechat, String groups, String createTime, int userLevel) {
        userDao.createUser(phoneNumber,name,password,mail,wechat,groups,createTime,userLevel);
    }

    @Override
    public void createUser(User user) {
        createUser(user.getPhoneNumber(),user.getName(),user.getPassword(),user.getMail(),user.getWechat(),"","",user.getUserLevel());
    }

    @Override
    public void deleteUser(String phoneNumber) {
        userDao.deleteUser(phoneNumber);
    }

    @Override
    public void editUser(String phoneNumber, String name, String password, String mail, String wechat, String groups, String createTime, int userLevel) {
        userDao.editUser(phoneNumber,name,password,mail,wechat,groups,createTime,userLevel);
    }

    @Override
    public User searchUser(String phoneNumber) {
        return userDao.searchUser(phoneNumber);
    }

    @Override
    public void joinGroup(String groupName) {

    }

    @Override
    public void exitGroup(String groupName) {

    }

    @Override
    public List<Group> searchGroups(String phoneNumber) {
        return null;
    }

}
