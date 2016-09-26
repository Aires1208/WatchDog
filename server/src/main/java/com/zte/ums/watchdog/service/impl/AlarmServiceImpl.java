package com.zte.ums.watchdog.service.impl;

import com.zte.ums.watchdog.model.Alarm;
import com.zte.ums.watchdog.model.Alarms;
import com.zte.ums.watchdog.model.JenkinsJob;
import com.zte.ums.watchdog.dao.AlarmDao;
import com.zte.ums.watchdog.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 2016/9/22.
 */
@Service("alarmService")
public class AlarmServiceImpl implements AlarmService{

    @Autowired
    @Qualifier("alarmDao")
    private AlarmDao alarmDao;

    public void addAlarm(JenkinsJob jenkinsJob) {
        alarmDao.addAlarm(jenkinsJob);
    }

    @Override
    public List<Alarm> getAlarmsByUserName(String userName) {
        return alarmDao.getAlarms().getAlarmsByUserName(userName);
    }

    @Override
    public List<Alarm> getAlarmsByUserNameAndCode(String userName, String statusCode) {
        return alarmDao.getAlarms().getAlarmsByUserNameAndCode(userName,statusCode);
    }

    public List<Alarm> getAlarms() {
        return alarmDao.getAlarms().getAlarmList();
    }
}
