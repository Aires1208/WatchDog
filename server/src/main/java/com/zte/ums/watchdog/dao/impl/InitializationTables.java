package com.zte.ums.watchdog.dao.impl;

import com.zte.ums.watchdog.common.utils.SqliteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 2016/9/22.
 */
@Repository
public class InitializationTables {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitializationTables.class);
    @Autowired
    private SqliteUtils utils;

    public void initialTables() {
        LOGGER.info("initial all tables");
        createUserTable();
        createAppTable();
        createAlarmTable();
        createOperatorHistoryTable();
        createOperatorLog();
        createGroupTable();

    }

    private void createAppTable() {
        String tableSql = new StringBuilder("drop table if exists app").toString();
        utils.createTable(tableSql);
        tableSql = new StringBuilder("create table if not exists app ")
                .append("(appid integer primary key autoincrement,")
                .append("appurl text,")
                .append("userid text,")
                .append("username text,")
                .append("createtime text)").toString();
        utils.createTable(tableSql);
    }
    private void createUserTable() {
        String tableSql = "DROP TABLE IF EXISTS  USER";
        System.out.println(tableSql);
        utils.createTable(tableSql);
        tableSql = "CREATE TABLE IF NOT EXISTS USER" +
                "(uuid integer primary key autoincrement" +
                ",phoneNumber TEXT  NOT NULL ," +
                "name TEXT  ," +
                "password TEXT  ," +
                "mail TEXT  ," +
                "wechat TEXT  ," +
                "groupNames TEXT  ," +
                "createTime TEXT  ," +
                "userLevel TEXT  ," +
                "lastlogin TEXT  ," +
                "loggintimes TEXT )";

        utils.createTable(tableSql);

//        String insertIntoSql = "insert into USER (name,phoneNumber,mail,password) values('钱国栋','18516592768','18516592768@163.com','Aa888888')";
        String insertIntoSql = "insert into USER (name,phoneNumber,mail,password) values('吴振宇','18621065408','18621065408@163.com','Aa888888')";
        String insertIntoSql3 = "insert into USER (name,phoneNumber,mail,password) values('沈毅','13817530351','13817530351@163.com','Aa888888')";
        String insertIntoSql2 = "insert into USER (name,phoneNumber,mail,password) values('唐蕾','18616712679','18616712679@163.com','Aa888888')";
        String insertIntoSql4 = "insert into USER (name,phoneNumber,mail,password) values('陈立鹏','18565695919','18565695919@163.com','Aa888888')";

//        String insertIntoSql1 = "insert into USER (name,phoneNumber,mail,password) values('张沛','15800775585','15800775585@163.com','Aa888888')";
//        String insertIntoSql3 = "insert into USER (name,phoneNumber,mail,password) values('能芬','13918964136','18565695212@163.com','Aa888888')";

//        String insertIntoSql5 = "insert into USER (name,phoneNumber,mail,password) values('刘顺睿','13472500678','13472500678@163.com','Aa888888')";
        utils.put(insertIntoSql);
        utils.put(insertIntoSql3);
//        utils.put(insertIntoSql1);
//        utils.put(insertIntoSql2);
        utils.put(insertIntoSql4);
        utils.put(insertIntoSql2);
    }
    private void createGroupTable() {
        String tableSql = " DROP TABLE IF EXISTS APPGROUP ";
        utils.createTable(tableSql);
        tableSql = "CREATE TABLE IF NOT EXISTS APPGROUP" +
                "(uuid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "groupName TEXT  NOT NULL," +
                "ownerUuid TEXT  NOT NULL," +
                "createTime TEXT," +
                "desc TEXT," +
                "memberUuids TEXT," +
                "appUuids TEXT)";

        utils.createTable(tableSql);
        String insertIntoSql = "insert into APPGROUP (groupName,ownerUuid,desc) values('manbuyunduan','uuid1','laowangtuandui')";
        String insertIntoSql1 = "insert into APPGROUP (groupName,ownerUuid,desc) values('gebilangwang','uuid2','heikesongtuandui')";
        utils.put(insertIntoSql);
        utils.put(insertIntoSql1);

    }

    private void createAlarmTable() {
        String tableSql = "DROP TABLE IF EXISTS ALARM ";
        utils.createTable(tableSql);
        tableSql = "CREATE TABLE IF NOT EXISTS ALARM " +
                "(ALARMID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ALARMOBJECT   TEXT  NOT NULL, " +
                " ALARMCONTEXT   TEXT  NOT NULL, " +
                " BELONGAPP   TEXT, " +
                " HOSTIP    TEXT    NOT NULL, " +
                " ALARMLEVEL  TEXT, " +
                " ALARMSTATUS    INTEGER, " +
                " ACTIVETIME  TEXT, " +
                " PROCESSINGMEMBER TEXT, " +
                " PROCESSINGTIME  TEXT) ";
        utils.createTable(tableSql);
    }

    private void createOperatorHistoryTable() {
        String tableSql = "DROP TABLE IF EXISTS ALARMHISTORYOPERATE ";
        utils.createTable(tableSql);
        tableSql = "CREATE TABLE IF NOT EXISTS ALARMHISTORYOPERATE " +
                "(HISTORYID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ALARMID   TEXT  NOT NULL, " +
                " PROCESSINGMEMBER   TEXT  NOT NULL, " +
                " PROCESSINGTIME   TEXT NOT NULL) ";
        utils.createTable(tableSql);
    }

    private void createOperatorLog() {
        String tableSql = "DROP TABLE IF EXISTS ALARMHISTORYLOG ";
        utils.createTable(tableSql);
        tableSql = "CREATE TABLE IF NOT EXISTS ALARMHISTORYLOG " +
                "(LOGID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ALARMID   TEXT  NOT NULL, " +
                " RECORDINGMEMBER   TEXT, " +
                " RECORDINGTYPE  TEXT," +
                " RECORDINGCONTEXT   TEXT, " +
                " RECORDINGTIME   TEXT NOT NULL) ";
        utils.createTable(tableSql);
    }

}
