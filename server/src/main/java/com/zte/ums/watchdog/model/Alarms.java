package com.zte.ums.watchdog.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by root on 2016/9/22.
 */
public class Alarms {
    private List<Alarm> alarmList;
    public Alarms(List<Alarm> alarmList) {
        this.alarmList = alarmList;
    }


    public List<Alarm> getAlarmList() {
        return alarmList;
    }

    public List<Alarm> getAlarmsByUserName( String userName) {
        List<Alarm> alarms= alarmList
                .stream()
                .filter(alarm -> alarm.getProcessingMember().equals(userName))
                .collect(Collectors.toList());
        return alarms;
    }

    public List<Alarm> getAlarmsByUserNameAndCode(String userName, String codeStatus) {
        List<Alarm> alarms= alarmList
                .stream()
                .filter(alarm -> alarm.getProcessingMember().equals(userName))
                .filter(alarm -> alarm.getAlarmStatus().equals(codeStatus))
                .collect(Collectors.toList());
        return alarms;
    }
}
