package com.zte.ums.watchdog.dao.impl;

import com.zte.ums.watchdog.common.utils.SqliteUtils;
import com.zte.ums.watchdog.dao.AlarmHistoryOperateDao;
import com.zte.ums.watchdog.model.AlarmHistoryOperate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2016/9/22.
 */
@Service
public class AlarmHistoryOperateDaoImpl implements AlarmHistoryOperateDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmHistoryOperateDaoImpl.class);

    @Autowired
    private SqliteUtils utils;

    @Override
    public void addHistoryAlarmOperate(AlarmHistoryOperate alarmHistoryOperate) {
        String insertSQL = fatchInsertSQL(alarmHistoryOperate);
        utils.put(insertSQL);
    }

    @Override
    public List<AlarmHistoryOperate> getAlarmHistoryOperateByAlarmId(String alarmId) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<AlarmHistoryOperate> alarmHistoryOperates = new ArrayList<AlarmHistoryOperate>();
        StringBuilder quarrySql = new StringBuilder("select * from ALARMHISTORYOPERATE where ALARMID='").append(alarmId).append("'");
        System.out.println(quarrySql.toString());
        try {
            conn = utils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(quarrySql.toString());
            while (rs.next()) {
                AlarmHistoryOperate alarmHistoryOperate = new AlarmHistoryOperate();
                alarmHistoryOperate.setAlarmId(rs.getString("ALARMID"));
                alarmHistoryOperate.setHistoryId(rs.getString("HISTORYID"));
                alarmHistoryOperate.setProcesingTime(rs.getString("PROCESSINGTIME"));
                alarmHistoryOperate.setProcessingMember(rs.getString("PROCESSINGMEMBER"));

                alarmHistoryOperates.add(alarmHistoryOperate);
            }

        } catch (SQLException e) {
            LOGGER.error("sql error" + e.getMessage(), e);
        } finally {
            utils.close(conn, stmt, rs);
        }

        return alarmHistoryOperates;
    }

    private static String fatchInsertSQL(AlarmHistoryOperate alarmHistoryOperate) {
        StringBuilder insertSQL = new StringBuilder("insert into ALARMHISTORYOPERATE (ALARMID,PROCESSINGMEMBER,PROCESSINGTIME) values(")
                .append('\'').append(alarmHistoryOperate.getAlarmId()).append('\'').append(",")
                .append('\'').append(alarmHistoryOperate.getProcessingMember()).append('\'').append(",")
                .append('\'').append(alarmHistoryOperate.getProcesingTime()).append('\'')
                .append(");")
                ;

        return insertSQL.toString();
    }
}
