package com.zte.ums.watchdog.dao;

import com.zte.ums.watchdog.model.AlarmProcessMeta;
import com.zte.ums.watchdog.model.AlarmsInfo;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by root on 2016/9/23.
 */
public interface AlarmInfoDao {
    List<AlarmsInfo> getAlarmsInfo();

    void updateAlarmMeta(AlarmProcessMeta alarmProcessMeta);

    void recordLog(JSONObject json);

    void addAlarm(JSONObject json);
}
