package com.zte.ums.watchdog.dao.impl;

import com.zte.ums.watchdog.common.utils.SqliteUtils;
import com.zte.ums.watchdog.common.utils.TimeUtils;
import com.zte.ums.watchdog.dao.MonitorAppDao;
import com.zte.ums.watchdog.model.MonitorApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by  on 2016/9/23.
 */
@Repository
public class MonitorAppDaoImpl implements MonitorAppDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorAppDaoImpl.class);
    @Autowired
    private SqliteUtils utils;

    @Override
    public void createMonitorApp(MonitorApp monitorApp) {
        String insertSQL = new StringBuilder("insert into app(appurl,userid,username,createtime) values(")
                .append("\'").append(monitorApp.getAppUrl()).append("\',")
                .append("\'").append(monitorApp.getUserId()).append("\',")
                .append("\'").append(monitorApp.getUserName()).append("\',")
                .append("\'").append(TimeUtils.dateToString()).append("\');").toString();
        utils.put(insertSQL);
    }

    @Override
    public void deleteMonitorAppByAppId(String appId) {
        String deleteSQL = new StringBuilder("delete from app where appid = ")
                .append("\'").append(appId).append("\'").toString();
        utils.put(deleteSQL);
    }

    @Override
    public void updateMonitorApp(MonitorApp monitorApp) {
        String updateSQL = new StringBuilder("update app set ")
                .append("appurl = ").append("\'").append(monitorApp.getAppUrl()).append("\',")
                .append("userid = ").append("\'").append(monitorApp.getUserId()).append("\',")
                .append("username = ").append("\'").append(monitorApp.getUserName()).append("\',")
                .append("createtime = ").append("\'").append(monitorApp.getCreateDate()).append("\'")
                .append(" where appid = ").append(monitorApp.getAppId()).toString();
        utils.put(updateSQL);
    }

    @Override
    public List<MonitorApp> queryMonitorApp() {
        List<MonitorApp> monitorApps = newArrayList();
        String sqlSQL = new StringBuilder("select * from app").toString();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = utils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlSQL);
            while (rs.next()) {
                MonitorApp monitorApp = new MonitorApp();
                monitorApp.setAppId(String.valueOf(rs.getInt("appid")));
                monitorApp.setAppUrl(rs.getString("appurl"));
                monitorApp.setUserId(rs.getString("userid"));
                monitorApp.setUserName(rs.getString("username"));
                monitorApp.setCreateDate(rs.getString("createtime"));
                monitorApps.add(monitorApp);
            }
        } catch (SQLException e) {
            LOGGER.error("sql error" + e.getMessage(), e);
        } finally {
            utils.close(conn, stmt, rs);
        }
        return monitorApps;
    }

    @Override
    public MonitorApp queryMonitorAppById(String appId) {
        MonitorApp monitorApp = new MonitorApp();
        String sqlSQL = new StringBuilder("select * from app where appid = ")
                .append("\'").append(appId).append("\'").toString();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = utils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlSQL);
            while (rs.next()) {
                monitorApp.setAppId(String.valueOf(rs.getInt("appid")));
                monitorApp.setAppUrl(rs.getString("appurl"));
                monitorApp.setUserId(rs.getString("userid"));
                monitorApp.setUserName(rs.getString("username"));
                monitorApp.setCreateDate(rs.getString("createtime"));
            }
        } catch (SQLException e) {
            LOGGER.error("sql error" + e.getMessage(), e);
        } finally {
            utils.close(conn, stmt, rs);
        }
        return monitorApp;
    }
}
