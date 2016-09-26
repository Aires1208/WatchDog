package com.zte.ums.watchdog.dao;

import com.zte.ums.watchdog.model.AlarmHistoryOperate;

import java.util.List;

/**
 * Created by root on 2016/9/22.
 */
public interface AlarmHistoryOperateDao {

    void addHistoryAlarmOperate(AlarmHistoryOperate alarmHistoryOperate);
    List<AlarmHistoryOperate> getAlarmHistoryOperateByAlarmId(String alarmId);
}
