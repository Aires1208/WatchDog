package com.zte.ums.watchdog.model;

/**
 * Created by root on 2016/9/23.
 */
public class AlarmProcessMeta {
    private String alarmID;
    private String loginStatus;
    private String userID;
    private String userName;
    private String modifyType;
    private String modifyContent;
    private String recordingType;
    private String recordingContent;

    public String getAlarmID() {
        return alarmID;
    }

    public void setAlarmID(String alarmID) {
        this.alarmID = alarmID;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getModifyType() {
        return modifyType;
    }

    public void setModifyType(String modifyType) {
        this.modifyType = modifyType;
    }

    public String getModifyContent() {
        return modifyContent;
    }

    public void setModifyContent(String modifyContent) {
        this.modifyContent = modifyContent;
    }

    public String getRecordingType() {
        return recordingType;
    }

    public void setRecordingType(String recordingType) {
        this.recordingType = recordingType;
    }

    public String getRecordingContent() {
        return recordingContent;
    }

    public void setRecordingContent(String recordingContent) {
        this.recordingContent = recordingContent;
    }
}
