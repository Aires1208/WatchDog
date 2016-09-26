package com.zte.ums.watchdog.dao.impl;

import com.zte.ums.watchdog.common.constant.AlarmConstant;
import com.zte.ums.watchdog.common.constant.ErrorCodeConstant;
import com.zte.ums.watchdog.common.exception.WatchDogException;
import com.zte.ums.watchdog.common.utils.SqliteUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by root on 9/22/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AlarmDaoImplTest {
//    @Autowired
//    private SqliteUtils utils;
    @Autowired
    private AlarmDaoImpl alarmDao;

    @Test
    public void fatchSqlTest() {
        Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("PROCESSINGMEMBER", "wangermazi");
        stringObjectHashMap.put("ALARMSTATUS", 3);
        System.out.println(alarmDao.fatchUpdateSql("1",stringObjectHashMap));
        alarmDao.modifyAlarm("1",stringObjectHashMap);

    }
    @Test
    public void  dateToString() {
        Date data = new Date(System.currentTimeMillis());
        String formatType = "yyyy-MM-dd HH:mm:ss";
        String dateToString = new SimpleDateFormat(formatType).format(data);
        System.out.println("dateToString: "+dateToString);
    }

//    @Test
//    public void modifyAlarmTest() {
//        String alarmId = "1";
//        Map<String, Object> updateValues = new HashMap<String, Object>();
//        updateValues.put("PROCESSINGMEMBER", "wangwu");
//        updateValues.put("ALARMSTATUS", 2);
//        StringBuilder updateSQL = new StringBuilder();
//        updateSQL.append(" UPDATE ALARM set ");
//        int index = 0;
//        for (Map.Entry entry : updateValues.entrySet()) {
//            if (index < updateValues.entrySet().size() - 1) {
//                updateSQL.append(entry.getKey()).append("= '").append(entry.getValue()).append("'").append(", ");
//                index++;
//            } else {
//                updateSQL.append(entry.getKey()).append("='").append(entry.getValue()).append("' ");
//
//            }
//        }
//        updateSQL.append("where ALARMID='").append(alarmId).append("';");
//        System.out.println(updateSQL.toString());
//
//
//        Connection conn = null;
//        Statement stmt = null;
//        try {
//            conn = utils.getConnection();
//            stmt = conn.createStatement();
//            stmt.executeUpdate(updateSQL.toString());
//            conn.commit();
//        } catch (SQLException e) {
//            try {
//                conn.rollback();
//            } catch (SQLException e1) {
//                throw new WatchDogException(ErrorCodeConstant.ROLLBACK_ERROR, e);
//            }
//            throw new WatchDogException(ErrorCodeConstant.SQL_ERROR, e);
//        } finally {
//            utils.closeStatement(stmt);
//            utils.closeConn(conn);
//        }
//    }
}
