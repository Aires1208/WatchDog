package com.zte.ums.watchdog.common.constant;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Created by root on 2016/9/23.
 */
public interface AlarmLevel {
    //提醒
    int REMIND = 0;

    //通知
    int NOTICE = 1;

    //严重
    int SERIOUS = 2;

    Map<Integer, String> levelMap = ImmutableMap.of(REMIND, "提醒", NOTICE, "通知", SERIOUS, "严重");

    static String getAlarmLevel(int status) {
        return levelMap.get(status);
    }
}
