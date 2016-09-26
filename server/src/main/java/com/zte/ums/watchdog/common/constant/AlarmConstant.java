package com.zte.ums.watchdog.common.constant;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Created by root on 2016/9/22.
 */
public interface AlarmConstant {
    //待处理
    int PENDING = 0;

    //处理中
    int PROCESSING = 1;

    //已转交
    int FORWARDED = 2;

    //已关闭
    int CLOSED = 3;


    Map<Integer, String> statusMap = ImmutableMap.of(PENDING, "待处理", PROCESSING, "处理中", FORWARDED, "已转交", CLOSED, "已关闭");

    static String getAlarmStatus(int status) {
        return statusMap.get(status);
    }
}
