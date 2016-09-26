package com.zte.ums.watchdog.model;

import java.util.List;

/**
 * Created by root on 2016/9/23.
 */
public class AlarmsInfo {
    private Alarm alarm;
    private List<AlarmHistoryOperate> alarmHistoryOperates;
    private List<AlarmHistoryLog> alarmHistoryLogs;

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public List<AlarmHistoryOperate> getAlarmHistoryOperates() {
        return alarmHistoryOperates;
    }

    public void setAlarmHistoryOperates(List<AlarmHistoryOperate> alarmHistoryOperates) {
        this.alarmHistoryOperates = alarmHistoryOperates;
    }

    public List<AlarmHistoryLog> getAlarmHistoryLogs() {
        return alarmHistoryLogs;
    }

    public void setAlarmHistoryLogs(List<AlarmHistoryLog> alarmHistoryLogs) {
        this.alarmHistoryLogs = alarmHistoryLogs;
    }
}
