package com.zte.ums.watchdog.model;

/**
 * Created by 10174957 on 2016/9/23.
 */
public class App {
    private String uuid;
    private String name;
    private String hostUrl;
    private User owner;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public static String getCreateTableSql(){
        //(uuid,name,hostUrl,owner)
        return "create table App(uuid varchar(40),name varchar(40),hostUrl varchar(40),owner varchar(40))";
    }
}
