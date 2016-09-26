package com.zte.ums.watchdog.service;

import com.zte.ums.watchdog.model.Group;

/**
 * Created by 10174957 on 2016/9/23.
 */
public interface GroupService {
    void changeGroupName(String oldName,String newName);
    void addMember(String userUuid);
    void deleteMember(String userUuid);
    Group searchGroup(String groupName);
}
