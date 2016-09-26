package com.zte.ums.watchdog.dao.impl;

import com.zte.ums.watchdog.common.utils.SqliteUtils;
import com.zte.ums.watchdog.dao.GroupDao;
import com.zte.ums.watchdog.model.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;

/**
 * Created by root on 2016/9/23.
 */
public class GroupDaoImpl implements GroupDao {
    private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private static boolean hasGroupExist = false;
    public GroupDaoImpl(){
        if(!hasGroupExist){
            createTabelGroup();
        }
    }
    @Autowired
    private SqliteUtils sqliteUtils;

    private void dropTableGroup(){
        String sql  = "drop table Group";
        sqliteUtils.createTable(sql);
    }
    private void createTabelGroup(){
        String sql  = Group.getCreateTableSql();
        sqliteUtils.createTable(sql);
    }
    @Override
    public void changeGroupName(String oldName, String newName) {

    }

    @Override
    public void addMember(String userUuid) {

    }

    @Override
    public void deleteMember(String userUuid) {

    }

    @Override
    public Group searchGroup(String groupName) {
        Connection connection = sqliteUtils.getConnection();

        return null;
    }
}
