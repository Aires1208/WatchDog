package com.zte.ums.watchdog.dao;

import com.zte.ums.watchdog.model.Group;

/**
 * Created by root on 2016/9/23.
 */
public interface GroupDao {
    void changeGroupName(String oldName,String newName);
    void addMember(String userUuid);
    void deleteMember(String userUuid);
    Group searchGroup(String groupName);
}
