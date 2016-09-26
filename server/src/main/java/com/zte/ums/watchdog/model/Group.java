package com.zte.ums.watchdog.model;

import java.util.List;

/**
 * Created by 10174957 on 2016/9/23.
 */
public class Group {
    private String uuid;
    private String groupName;
    private String ownerUuid;
    private String createTime;
    private String desc;
    private List<User> members;
    private List<App> apps;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getOwnerUuid() {
        return ownerUuid;
    }

    public void setOwnerUuid(String ownerUuid) {
        this.ownerUuid = ownerUuid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    public static String getCreateTableSql(){
        //(uuid,groupName,ownerUuid,createTime,desc,memberUuids,appUuids)
        return "create table Group(uuid varchar(40),groupName varchar(40),ownerUuid varchar(40),createTime varchar(40),desc varchar(40),memberUuids varchar(400),appUuids varchar(400))";
    }
}
