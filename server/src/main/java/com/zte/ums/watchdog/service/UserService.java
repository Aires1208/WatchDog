package com.zte.ums.watchdog.service;

import com.zte.ums.watchdog.model.Group;
import com.zte.ums.watchdog.model.User;

import java.util.List;

/**
 * Created by 10174957 on 2016/9/23.
 */
public interface UserService {
    void createUser(String phoneNumber,String name, String password, String mail,String wechat,String groups, String createTime,int userLevel);
    void createUser(User user);
    void deleteUser(String phoneNumber);
    void editUser(String phoneNumber,String name, String password, String mail,String wechat,String groups, String createTime,int userLevel);
    User searchUser(String phoneNumber);
    void joinGroup(String groupName);
    void exitGroup(String groupName);
    List<Group> searchGroups(String phoneNumber);
}
