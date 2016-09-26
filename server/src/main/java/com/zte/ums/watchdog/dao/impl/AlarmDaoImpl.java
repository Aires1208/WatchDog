package com.zte.ums.watchdog.dao.impl;

import com.zte.ums.watchdog.common.constant.AlarmConstant;
import com.zte.ums.watchdog.common.constant.ErrorCodeConstant;
import com.zte.ums.watchdog.common.exception.WatchDogException;
import com.zte.ums.watchdog.common.utils.SqliteUtils;
import com.zte.ums.watchdog.common.utils.TimeUtils;
import com.zte.ums.watchdog.dao.AlarmDao;
import com.zte.ums.watchdog.model.Alarm;
import com.zte.ums.watchdog.model.AlarmProcessMeta;
import com.zte.ums.watchdog.model.Alarms;
import com.zte.ums.watchdog.model.JenkinsJob;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2016/9/22.
 */
@Repository("alarmDao")
public class AlarmDaoImpl implements AlarmDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmDao.class);

    @Autowired
    private SqliteUtils utils;

    public static String dateToString() {
        Date data = new Date(System.currentTimeMillis());
        String formatType = "yyyy-MM-dd HH:mm:ss";
        return new SimpleDateFormat(formatType).format(data);
    }

    @Override
    public void addAlarm(JenkinsJob job) {
        String hostIp = job.getUrl().substring(0, job.getUrl().indexOf("/job"));
        if (isAlarmExists(job)) {
            LOGGER.info("alarm already in database");
        } else {
            StringBuilder sb = new StringBuilder("insert into alarm(alarmobject,alarmcontext,belongapp,hostip,alarmlevel,alarmstatus,activetime) values(")
                    .append("\"" + job.getName() + "\",")
                    .append("\"" + job.getUrl() + "\",")
                    .append("\"jenkins\",")
                    .append("\"" + hostIp + "\",")
                    .append("\"严重\",")
                    .append(AlarmConstant.PENDING + ",")
                    .append("\"" + dateToString() + "\"")
                    .append(")");
            LOGGER.info("add sql:" + sb.toString());
            utils.put(sb.toString());
        }
    }

    @Override
    public void modifyAlarm(String alarmId, Map<String, Object> updateValues) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = utils.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(fatchUpdateSql(alarmId, updateValues));
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                throw new WatchDogException(ErrorCodeConstant.ROLLBACK_ERROR, e);
            }
            throw new WatchDogException(ErrorCodeConstant.SQL_ERROR, e);
        } finally {
            utils.closeStatement(stmt);
            utils.closeConn(conn);
        }
    }

    @Override
    public void queryAlarm() {

    }

    @Override
    public boolean isAlarmExists(JenkinsJob jenkinsJob) {
        boolean flag = false;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String hostIp = jenkinsJob.getUrl().substring(0, jenkinsJob.getUrl().indexOf("/job"));
        StringBuilder sb = new StringBuilder("SELECT *  FROM ALARM WHERE ALARMOBJECT = ")
                .append("\'" + jenkinsJob.getName() + "\'")
                .append(" AND HOSTIP = " + "\'" + hostIp + "\'")
                .append(" AND ALARMSTATUS < " + AlarmConstant.CLOSED);
        LOGGER.info("select sql: " + sb.toString());
        try {
            conn = utils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sb.toString());
            while (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            LOGGER.error("sql error" + e.getMessage(), e);
            return flag;
        } finally {
            utils.close(conn, stmt, rs);
        }
        return flag;
    }

    @Override
    public Alarms getAlarms() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Alarm> alarms = new ArrayList<Alarm>();
        try {
            conn = utils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from ALARM");
            while (rs.next()) {
                Alarm alarm = new Alarm();
                alarm.setAlarmId(String.valueOf(rs.getInt("ALARMID")));
                alarm.setAlarmObject(rs.getString("ALARMOBJECT"));
                alarm.setAlarmContext(rs.getString("ALARMCONTEXT"));
                alarm.setBelogApp(rs.getString("BELONGAPP"));
                alarm.setHostIp(rs.getString("HOSTIP"));
                alarm.setAlarmLevel(rs.getString("ALARMLEVEL"));
                alarm.setAlarmStatus(rs.getString("ALARMSTATUS"));
                alarm.setActiveTime(rs.getString("ACTIVETIME"));
                alarm.setProcessingMember(rs.getString("PROCESSINGMEMBER"));
                alarm.setProcessingTime(rs.getString("PROCESSINGTIME"));
                alarms.add(alarm);
            }

        } catch (SQLException e) {
            LOGGER.error("sql error" + e.getMessage(), e);
        } finally {
            utils.close(conn, stmt, rs);
        }

        return new Alarms(alarms);
    }

    @Override
    public void updateAlarm(AlarmProcessMeta alarmProcessMeta) {
        if ("alarmStatus".equals(alarmProcessMeta.getModifyType())) {
            StringBuilder updateSQL = new StringBuilder("UPDATE ALARM set ")
                    .append("ALARMSTATUS = ").append(alarmProcessMeta.getModifyContent()).append(",")
                    .append("PROCESSINGMEMBER=").append("\'").append(alarmProcessMeta.getUserName()).append("\'").append(",")
                    .append("PROCESSINGTIME=").append("\'").append(TimeUtils.dateToString()).append("\'")
                    .append(" where ALARMID=").append("\'").append(alarmProcessMeta.getAlarmID()).append("\';");
            utils.put(updateSQL.toString());
        }
    }

    @Override
    public boolean isProcessorNull(String alarmId) {
        StringBuilder sb = new StringBuilder("select * from ALARM where ALARMID=" + alarmId + ";");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            conn = utils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sb.toString());
            while (rs.next()) {
                String processor = rs.getString("PROCESSINGMEMBER");
                if (null == processor || "".equals(processor)) {
                    flag = true;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("sql error" + e.getMessage(), e);
        } finally {
            utils.close(conn, stmt, rs);
        }
        return flag;
    }


    @Override
    public void addGivenAlarm(JSONObject json) {
        String alarmObject = (String) json.get("alarmObject");
        String alarmContent = (String) json.get("alarmContent");
        String belongApp = (String) json.get("belongApp");
        String hostIp = (String) json.get("hostIp");
        String alarmLevel = (String) json.get("alarmLevel");
        StringBuilder sb = new StringBuilder("insert into alarm(alarmobject,alarmcontext,belongapp,hostip,alarmlevel,alarmstatus,activetime) values(")
                .append("\"" + alarmObject + "\",")
                .append("\"" + alarmContent + "\",")
                .append("\"" + belongApp + "\",")
                .append("\"" + hostIp + "\",")
                .append("\"" + alarmLevel + "\",")
                .append(AlarmConstant.PENDING + ",")
                .append("\"" + dateToString() + "\"")
                .append(")");
        LOGGER.info("add sql:" + sb.toString());
        utils.put(sb.toString());

        //todo
    }

    @Override
    public Alarm getAlarmByAlarmId(String alarmId) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Alarm alarm = new Alarm();
        String selectSql = new StringBuilder("select * from ALARM where ")
                .append(" ALARMID = ").append("\'").append(alarmId).append("\';").toString();
        try {
            conn = utils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectSql);
            while (rs.next()) {
                alarm.setAlarmId(String.valueOf(rs.getInt("ALARMID")));
                alarm.setAlarmObject(rs.getString("ALARMOBJECT"));
                alarm.setAlarmContext(rs.getString("ALARMCONTEXT"));
                alarm.setBelogApp(rs.getString("BELONGAPP"));
                alarm.setHostIp(rs.getString("HOSTIP"));
                alarm.setAlarmLevel(rs.getString("ALARMLEVEL"));
                alarm.setAlarmStatus(rs.getString("ALARMSTATUS"));
                alarm.setProcessingMember(rs.getString("PROCESSINGMEMBER"));
                alarm.setProcessingTime(rs.getString("PROCESSINGTIME"));
            }
        } catch (SQLException e) {
            LOGGER.error("sql error" + e.getMessage(), e);
        } finally {
            utils.close(conn, stmt, rs);
        }
        return alarm;
    }

    public String fatchUpdateSql(String alarmId, Map<String, Object> updateValues) {
        StringBuilder updateSQL = new StringBuilder();
        updateSQL.append(" UPDATE ALARM set ");
        for (Map.Entry entry : updateValues.entrySet()) {
            updateSQL.append(entry.getKey()).append("= '").append(entry.getValue()).append("'").append(", ");
        }
        updateSQL.append("PROCESSINGTIME = '").append(dateToString()).append("'");
        updateSQL.append(" where ALARMID='").append(alarmId).append("';");
        return updateSQL.toString();
    }
}
