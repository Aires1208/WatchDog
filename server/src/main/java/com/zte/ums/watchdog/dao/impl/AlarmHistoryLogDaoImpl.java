package com.zte.ums.watchdog.dao.impl;

import com.zte.ums.watchdog.common.utils.TimeUtils;
import com.zte.ums.watchdog.dao.AlarmDao;
import com.zte.ums.watchdog.model.Alarm;
import com.zte.ums.watchdog.model.AlarmHistoryLog;
import com.zte.ums.watchdog.common.utils.SqliteUtils;
import com.zte.ums.watchdog.dao.AlarmHistoryLogDao;
import com.zte.ums.watchdog.model.Alarms;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 2016/9/22.
 */
@Service
public class AlarmHistoryLogDaoImpl implements AlarmHistoryLogDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmHistoryLogDaoImpl.class);

    @Autowired
    private SqliteUtils utils;
    @Override
    public void addalarmHistoryLog(AlarmHistoryLog alarmHistoryLog) {
        String insertSQL = fatchInsertSQL(alarmHistoryLog);
        utils.put(insertSQL);
    }

    @Override
    public List<AlarmHistoryLog> getAlarmHistoryLogByAlarmId(String alarmId) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<AlarmHistoryLog> alarmHistoryLogs = new ArrayList<AlarmHistoryLog>();
        StringBuilder quarrySql = new StringBuilder("select * from ALARMHISTORYLOG where ALARMID='").append(alarmId).append("'");
        System.out.println(quarrySql.toString());
        try {
            conn = utils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(quarrySql.toString());
            while (rs.next()) {
                AlarmHistoryLog alarmHistoryLog = new AlarmHistoryLog();
                alarmHistoryLog.setAlarmId(String.valueOf(rs.getInt("ALARMID")));
                alarmHistoryLog.setLogId(rs.getString("LOGID"));
                alarmHistoryLog.setRecodeingContext(rs.getString("RECORDINGCONTEXT"));
                alarmHistoryLog.setRecordingMember(rs.getString("RECORDINGMEMBER"));
                alarmHistoryLog.setRecordingTime(rs.getString("RECORDINGTIME"));
                alarmHistoryLog.setRecordingType(rs.getString("RECORDINGTYPE"));
                alarmHistoryLogs.add(alarmHistoryLog);
            }

        } catch (SQLException e) {
            LOGGER.error("sql error" + e.getMessage(), e);
        } finally {
            utils.close(conn, stmt, rs);
        }

        return alarmHistoryLogs;
    }

    @Override
    public void recordLog(JSONObject json) {
        String alarmId = (String) json.get("alarmId");
        String recordingMember = (String) json.get("recordingMember");
        String recordingType = (String) json.get("recordingType");
        String recordingContext = (String) json.get("recordingContext");
        addalarmHistoryLog(new AlarmHistoryLog(alarmId, recordingMember, recordingType, recordingContext, TimeUtils.dateToString()));
    }

    private static String dateToString() {
        Date data = new Date(System.currentTimeMillis());
        String formatType = "yyyy-MM-dd HH:mm:ss";
        return new SimpleDateFormat(formatType).format(data);
    }
    private static String fatchInsertSQL(AlarmHistoryLog alarmHistoryLog) {


        StringBuilder insertSQL = new StringBuilder("insert into ALARMHISTORYLOG (ALARMID,RECORDINGMEMBER,RECORDINGTYPE,RECORDINGCONTEXT,RECORDINGTIME) values(")
                .append('\'').append(alarmHistoryLog.getAlarmId()).append('\'').append(",")
                .append('\'').append(alarmHistoryLog.getRecordingMember()).append('\'').append(",")
                .append('\'').append(alarmHistoryLog.getRecordingType()).append('\'').append(",")
                .append('\'').append(alarmHistoryLog.getRecodeingContext()).append('\'').append(",")
                .append('\'').append(alarmHistoryLog.getRecordingTime()).append('\'')
                .append(");")
                ;

        return insertSQL.toString();
    }
}
