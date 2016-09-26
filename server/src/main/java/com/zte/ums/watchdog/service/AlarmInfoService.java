package com.zte.ums.watchdog.service;

import com.zte.ums.watchdog.model.AlarmInfoSequence;
import com.zte.ums.watchdog.model.AlarmProcessMeta;
import net.sf.json.JSONObject;

/**
 * Created by root on 2016/9/23.
 */
public interface AlarmInfoService {
    AlarmInfoSequence getAlarmInfo();

    void updateAlarmMeta(AlarmProcessMeta alarmProcessMeta);

    void recordLog(JSONObject json);

    void addAlarm(JSONObject json);
}
