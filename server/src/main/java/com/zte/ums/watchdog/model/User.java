package com.zte.ums.watchdog.model;

import java.util.List;

/**
 * Created by 10174957 on 2016/9/22.
 */
public class User {
    private String uuid;
    private String phoneNumber;
    private String name;
    private String password;
    private String mail;
    private String wechat;
    private List<Group> groupNames;
    private String createTime;
    private int userLevel;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public List<Group> getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(List<Group> groupNames) {
        this.groupNames = groupNames;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public static String getCreateTableSql(){
        //(uuid,phoneNumber,name,password,mail,wechat,groupNames,createTime,userLevel,lastlogin,loggintimes)
        return "create table User(uuid varchar(40),phoneNumber int ,name varchar(20),password varchar(40),mail varchar(40),wechat varchar(40),groupNames varchar(100),createTime varchar(40),userLevel int,lastlogin varchar(40),loggintimes int)";
    }
}
