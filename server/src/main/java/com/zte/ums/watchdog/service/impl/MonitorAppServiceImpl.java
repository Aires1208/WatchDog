package com.zte.ums.watchdog.service.impl;

import com.zte.ums.watchdog.dao.MonitorAppDao;
import com.zte.ums.watchdog.model.MonitorApp;
import com.zte.ums.watchdog.service.MonitorAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 2016/9/23.
 */
@Service
public class MonitorAppServiceImpl implements MonitorAppService {

    @Autowired
    private MonitorAppDao monitorAppDao;

    @Override
    public void createMonitorApp(MonitorApp monitorApp) {
        monitorAppDao.createMonitorApp(monitorApp);
    }

    @Override
    public void deleteMonitorAppByAppId(String appId) {
        monitorAppDao.deleteMonitorAppByAppId(appId);
    }

    @Override
    public void updateMonitorApp(MonitorApp monitorApp) {
        monitorAppDao.updateMonitorApp(monitorApp);
    }

    @Override
    public List<MonitorApp> queryMonitorApp() {
        return monitorAppDao.queryMonitorApp();
    }

    @Override
    public MonitorApp queryMonitorAppById(String appId) {
        return monitorAppDao.queryMonitorAppById(appId);
    }
}
