package com.zte.ums.watchdog.service;

import com.zte.ums.watchdog.model.Alarm;
import com.zte.ums.watchdog.model.JenkinsJob;

import java.util.List;

/**
 * Created by root on 2016/9/22.
 */

public interface AlarmService {
    void addAlarm(JenkinsJob jenkinsJob);

    List<Alarm> getAlarmsByUserName(String userName);
    List<Alarm> getAlarmsByUserNameAndCode(String userName,String statusCode);

}
