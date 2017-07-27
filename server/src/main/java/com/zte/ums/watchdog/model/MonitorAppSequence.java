package com.zte.ums.watchdog.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * Created by  on 2016/9/23.
 */
@JsonSerialize(using = MonitorAppSequenceSerializer.class)
public class MonitorAppSequence {
    private List<MonitorApp> monitorAppList;

    public MonitorAppSequence(List<MonitorApp> monitorAppList) {
        this.monitorAppList = monitorAppList;
    }

    public List<MonitorApp> getMonitorAppList() {
        return monitorAppList;
    }
}
