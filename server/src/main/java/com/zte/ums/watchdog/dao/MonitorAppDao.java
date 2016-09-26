package com.zte.ums.watchdog.dao;

import com.zte.ums.watchdog.model.MonitorApp;

import java.util.List;

/**
 * Created by root on 2016/9/23.
 */
public interface MonitorAppDao {
    void createMonitorApp(MonitorApp monitorApp);

    void deleteMonitorAppByAppId(String appId);

    void updateMonitorApp(MonitorApp monitorApp);

    List<MonitorApp> queryMonitorApp();

    MonitorApp queryMonitorAppById(String appId);
}
