package com.zte.ums.watchdog.dao;

import com.zte.ums.watchdog.model.AlarmHistoryOperate;
import com.zte.ums.watchdog.common.utils.SqliteUtils;
import com.zte.ums.watchdog.dao.impl.AlarmHistoryOperateDaoImpl;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by  on 9/22/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AlarmHistoryOperateDaoImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmHistoryOperateDaoImplTest.class);

    @Autowired
    private AlarmHistoryOperateDaoImpl alarmHistoryOperateDao;
    @Autowired
    private SqliteUtils utils;

    @Autowired
    private InitializationTables initializationTables;

    @Before
    public void createTable() {
        initializationTables.initialTables();
    }

    @Test
    public void addHistoryAlarmOperateTest() {
        AlarmHistoryOperate alarmHistoryOperate = new AlarmHistoryOperate();
        alarmHistoryOperate.setAlarmId("123");
        alarmHistoryOperate.setHistoryId("1");
        alarmHistoryOperate.setProcessingMember("lisi");
        alarmHistoryOperate.setProcesingTime("2016-09-22 19:59");

        alarmHistoryOperateDao.addHistoryAlarmOperate(alarmHistoryOperate);


    }

    @Test
    public void getAlarmHistoryOperateByAlarmIdTest() {
        List<AlarmHistoryOperate> alarmHistoryOperates=alarmHistoryOperateDao.getAlarmHistoryOperateByAlarmId("123");
        for (AlarmHistoryOperate alarmHistoryOperate:alarmHistoryOperates
             ) {
            System.out.println(alarmHistoryOperate.toString());
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
            rs = stmt.executeQuery("select * from ALARMHISTORYOPERATE");
            while (rs.next()) {
                System.out.println("ALARMHISTORYOPERATE: " + rs.getString("HISTORYID"));
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
