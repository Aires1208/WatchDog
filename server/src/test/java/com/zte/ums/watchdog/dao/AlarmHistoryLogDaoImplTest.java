package com.zte.ums.watchdog.dao;

import com.zte.ums.watchdog.model.AlarmHistoryLog;
import com.zte.ums.watchdog.common.utils.SqliteUtils;
import com.zte.ums.watchdog.dao.impl.AlarmHistoryLogDaoImpl;
import com.zte.ums.watchdog.dao.impl.InitializationTables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by 10183966 on 9/22/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AlarmHistoryLogDaoImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmHistoryOperateDaoImplTest.class);

    @Autowired
    private AlarmHistoryLogDaoImpl alarmHistoryLogDao;
    @Autowired
    private SqliteUtils utils;

    @Autowired
    private InitializationTables initializationTables;

    @Before
    public void createTable(){
        initializationTables.initialTables();
    }

    @Test
    public void addHistoryAlarmOperateTest() {
        AlarmHistoryLog alarmHistoryLog = new AlarmHistoryLog();
        alarmHistoryLog.setAlarmId("13");
        alarmHistoryLog.setLogId("1");
        alarmHistoryLog.setRecodeingContext("gaojing");
        alarmHistoryLog.setRecordingType("waring");
        alarmHistoryLog.setRecordingTime("2016-09-22 19:59");

        alarmHistoryLogDao.addalarmHistoryLog(alarmHistoryLog);


    }
    @Test
    public void getAlarmHistoryLogByAlarmIdTest() {
        List<AlarmHistoryLog > alarmHistoryLog = alarmHistoryLogDao.getAlarmHistoryLogByAlarmId("123");
        for (AlarmHistoryLog alarmHistoryLog1:alarmHistoryLog) {
            System.out.println(alarmHistoryLog1.toString());
        }


    }

    @Test
    public void select() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = utils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from ALARMHISTORYLOG");
            while (rs.next()) {
                System.out.println("ALARMHISTORYLOG: "+rs.getString("ALARMID"));
                System.out.println("ALARMHISTORYLOG: "+rs.getString("RECORDINGCONTEXT"));
            }
//            return rs;
        } catch (SQLException e) {
            LOGGER.error("sql error" + e.getMessage(), e);
        } finally {
            utils.close(conn, stmt, rs);
        }
//        return null;
    }
}
