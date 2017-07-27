package com.zte.ums.watchdog.dao.impl;

import com.zte.ums.watchdog.common.utils.SqliteUtils;
import com.zte.ums.watchdog.common.utils.TimeUtils;
import com.zte.ums.watchdog.dao.MonitorAppDao;
import com.zte.ums.watchdog.model.MonitorApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by  on 2016/9/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MonitorAppDaoImplTest {
    @Autowired
    private InitializationTables initializationTables;

    @Autowired
    private MonitorAppDao monitorAppDao;

    @Before
    public void initialTable() {
        initializationTables.initialTables();
    }

    @Test
    public void testCreateMonitorApp() throws Exception {
        MonitorApp monitorApp = new MonitorApp("http://127.0.0.1:8080", "user1", "user1", TimeUtils.dateToString());
        monitorAppDao.createMonitorApp(monitorApp);
    }


    @Test
    public void testDeleteMonitorAppByAppId() throws Exception {
        testCreateMonitorApp();
        testCreateMonitorApp();
        monitorAppDao.deleteMonitorAppByAppId("1");
    }

    @Test
    public void testUpdateMonitorApp() throws Exception {
        testCreateMonitorApp();
        MonitorApp monitorApp = new MonitorApp("http://10.62.57.148:8080", "user1", "user1", TimeUtils.dateToString());
        monitorApp.setAppId("1");
        monitorAppDao.updateMonitorApp(monitorApp);
    }

    @Test
    public void testQueryMonitorApp() throws Exception {
        testCreateMonitorApp();
        testCreateMonitorApp();
        testCreateMonitorApp();
        monitorAppDao.queryMonitorApp().forEach(System.out::println);
    }

    @Test
    public void testQueryMonitorAppById() throws Exception {
        testCreateMonitorApp();
        System.out.println(monitorAppDao.queryMonitorAppById("1"));
    }
}
