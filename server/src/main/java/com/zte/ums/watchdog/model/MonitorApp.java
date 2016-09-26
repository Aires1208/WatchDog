package com.zte.ums.watchdog.model;

/**
 * Created by root on 2016/9/23.
 */
public class MonitorApp {
    private String appId;
    private String appUrl;
    private String userId;
    private String userName;
    private String createDate;

    public MonitorApp() {
    }

    public MonitorApp(String appId, String appUrl, String userId, String userName, String createDate) {
        this.appId = appId;
        this.appUrl = appUrl;
        this.userId = userId;
        this.userName = userName;
        this.createDate = createDate;
    }

    public MonitorApp(String appUrl, String userId, String userName, String createDate) {
        this.appUrl = appUrl;
        this.userId = userId;
        this.userName = userName;
        this.createDate = createDate;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "MonitorApp{" +
                "appId='" + appId + '\'' +
                ", appUrl='" + appUrl + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
