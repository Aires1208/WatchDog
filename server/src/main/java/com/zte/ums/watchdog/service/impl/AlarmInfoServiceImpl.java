package com.zte.ums.watchdog.service.impl;

import com.zte.ums.watchdog.dao.AlarmInfoDao;
import com.zte.ums.watchdog.model.AlarmInfoSequence;
import com.zte.ums.watchdog.model.AlarmProcessMeta;
import com.zte.ums.watchdog.service.AlarmInfoService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 2016/9/23.
 */
@Service
public class AlarmInfoServiceImpl implements AlarmInfoService {

    @Autowired
    private AlarmInfoDao alarmInfoDao;

    @Override
    public AlarmInfoSequence getAlarmInfo() {
        return new AlarmInfoSequence(alarmInfoDao.getAlarmsInfo());
    }

    @Override
    public void updateAlarmMeta(AlarmProcessMeta alarmProcessMeta) {
        alarmInfoDao.updateAlarmMeta(alarmProcessMeta);
    }

    @Override
    public void recordLog(JSONObject json) {
        alarmInfoDao.recordLog(json);
    }

    @Override
    public void addAlarm(JSONObject json) {
        alarmInfoDao.addAlarm(json);
    }
}
