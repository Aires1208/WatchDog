package com.zte.ums.watchdog.model;

/**
 * Created by root on 2016/9/22.
 */

public class AlarmHistoryLog {
    private String logId;
    private String alarmId;
    private String recordingMember;
    private String recordingType;
    private String recodeingContext;
    private String recordingTime;

    public AlarmHistoryLog() {
    }

    public AlarmHistoryLog(String alarmId, String recordingMember, String recordingType, String recodeingContext, String recordingTime) {
        this.alarmId = alarmId;
        this.recordingMember = recordingMember;
        this.recordingType = recordingType;
        this.recodeingContext = recodeingContext;
        this.recordingTime = recordingTime;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getRecordingMember() {
        return recordingMember;
    }

    public void setRecordingMember(String recordingMember) {
        this.recordingMember = recordingMember;
    }

    public String getRecordingType() {
        return recordingType;
    }

    public void setRecordingType(String recordingType) {
        this.recordingType = recordingType;
    }

    public String getRecodeingContext() {
        return recodeingContext;
    }

    public void setRecodeingContext(String recodeingContext) {
        this.recodeingContext = recodeingContext;
    }

    public String getRecordingTime() {
        return recordingTime;
    }

    public void setRecordingTime(String recordingTime) {
        this.recordingTime = recordingTime;
    }

    @Override
    public String toString() {
        return "AlarmHistoryLog{" +
                "logId='" + logId + '\'' +
                ", alarmId='" + alarmId + '\'' +
                ", recordingMember='" + recordingMember + '\'' +
                ", recordingType='" + recordingType + '\'' +
                ", recodeingContext='" + recodeingContext + '\'' +
                ", recordingTime='" + recordingTime + '\'' +
                '}';
    }
}
