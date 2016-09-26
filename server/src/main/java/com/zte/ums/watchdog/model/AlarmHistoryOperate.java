package com.zte.ums.watchdog.model;

/**
 * Created by root on 2016/9/22.
 */

public class AlarmHistoryOperate {
    private String historyId;
    private String alarmId;
    private String processingMember;
    private String procesingTime;

    public AlarmHistoryOperate() {
    }

    public AlarmHistoryOperate(String alarmId, String processingMember, String procesingTime) {
        this.alarmId = alarmId;
        this.processingMember = processingMember;
        this.procesingTime = procesingTime;
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getProcessingMember() {
        return processingMember;
    }

    public void setProcessingMember(String processingMember) {
        this.processingMember = processingMember;
    }

    public String getProcesingTime() {
        return procesingTime;
    }

    public void setProcesingTime(String procesingTime) {
        this.procesingTime = procesingTime;
    }

    @Override
    public String toString() {
        return "AlarmHistoryOperate{" +
                "historyId='" + historyId + '\'' +
                ", alarmId='" + alarmId + '\'' +
                ", processingMember='" + processingMember + '\'' +
                ", procesingTime='" + procesingTime + '\'' +
                '}';
    }
}
