package com.zte.ums.watchdog.model;

/**
 * Created by root on 2016/9/22.
 */
public class Alarm {
    private String alarmId;
    private String alarmObject;
    private String alarmContext;
    private String belogApp;
    private String hostIp;
    private String alarmLevel;
    private String alarmStatus;
    private String activeTime;
    private String processingMember;
    private String processingTime;

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getAlarmObject() {
        return alarmObject;
    }

    public void setAlarmObject(String alarmObject) {
        this.alarmObject = alarmObject;
    }

    public String getAlarmContext() {
        return alarmContext;
    }

    public void setAlarmContext(String alarmContext) {
        this.alarmContext = alarmContext;
    }

    public String getBelogApp() {
        return belogApp;
    }

    public void setBelogApp(String belogApp) {
        this.belogApp = belogApp;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public String getProcessingMember() {
        return processingMember;
    }

    public void setProcessingMember(String processingMember) {
        this.processingMember = processingMember;
    }

    public String getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(String processingTime) {
        this.processingTime = processingTime;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "alarmId='" + alarmId + '\'' +
                ", alarmObject='" + alarmObject + '\'' +
                ", alarmContext='" + alarmContext + '\'' +
                ", belogApp='" + belogApp + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", alarmLevel='" + alarmLevel + '\'' +
                ", alarmStatus='" + alarmStatus + '\'' +
                ", activeTime='" + activeTime + '\'' +
                ", processingMember='" + processingMember + '\'' +
                ", processingTime='" + processingTime + '\'' +
                '}';
    }
}
