package com.zte.ums.watchdog.dao;

import com.zte.ums.watchdog.model.Alarm;
import com.zte.ums.watchdog.model.AlarmProcessMeta;
import com.zte.ums.watchdog.model.Alarms;
import com.zte.ums.watchdog.model.JenkinsJob;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Created by root on 2016/9/22.
 */
public interface AlarmDao {
    void addAlarm(JenkinsJob job);

    void modifyAlarm(String alarmId,Map<String,Object> updateValues);

    void queryAlarm();

    boolean isAlarmExists(JenkinsJob job);

    Alarms getAlarms();

    void updateAlarm(AlarmProcessMeta alarmProcessMeta);

    boolean isProcessorNull(String alarmId);

    void addGivenAlarm(JSONObject json);

    Alarm getAlarmByAlarmId(String alarmId);
}
