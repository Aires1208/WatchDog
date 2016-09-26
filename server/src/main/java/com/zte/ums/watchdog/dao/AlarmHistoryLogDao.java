package com.zte.ums.watchdog.dao;

import com.zte.ums.watchdog.model.AlarmHistoryLog;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by root on 2016/9/22.
 */
public interface AlarmHistoryLogDao {
    void addalarmHistoryLog(AlarmHistoryLog alarmHistoryLog);
    List<AlarmHistoryLog> getAlarmHistoryLogByAlarmId(String alarmId);

    void recordLog(JSONObject json);
}
