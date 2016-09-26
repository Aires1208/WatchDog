package com.zte.ums.watchdog.dao.impl;

import com.zte.ums.watchdog.common.utils.SqliteUtils;
import com.zte.ums.watchdog.dao.UserDao;
import com.zte.ums.watchdog.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10174957 on 2016/9/23.
 */
@Service

public class UserDaoImpl implements UserDao {
    private static boolean hasCreateTable = false;
    private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    @Autowired
    private SqliteUtils sqliteUtils;

//    public UserDaoImpl() {
//        if (!hasCreateTable){
//            createTableUser();
//            hasCreateTable = true;
//        }
//    }

    private void createTableUser() {
        sqliteUtils.createTable("drop table User");
        sqliteUtils.createTable(User.getCreateTableSql());
    }

    @Override
    public void createUser(String phoneNumber, String name, String password, String mail, String wechat, String groups, String createTime, int userLevel) {
        String sql = "insert into User ( phoneNumber,name,password,mail,wechat,groupNames,createTime,userLevel) values (" +
               "\'"+phoneNumber+"\'" +"," +
               "\'"+name+"\'" +"," +
               "\'"+password+"\'" +"," +
               "\'"+mail+"\'" +"," +
               "\'"+wechat+"\'" +"," +
               "\'"+groups+"\'" +"," +
               "\'"+createTime+"\'" +"," +
                   +userLevel  +
                ")";
        System.out.println(sql);
        sqliteUtils.put(sql);

    }

    @Override
    public void deleteUser(String phoneNumber) {
        String sql = "delete from  User where phoneNumber = " + phoneNumber;
        Connection conn = sqliteUtils.getConnection();
        try {
            conn.prepareStatement(sql).execute();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void editUser(String phoneNumber, String name, String password, String mail, String wechat, String groups, String createTime, int userLevel) {
        String sql = "update User set " +
                "name = ?," +
                "password=?," +
                "createdata = ?," +
                "lastlogin = ?," +
                "loggintimes = ?) where phoneNumber = ?";
        Connection conn = sqliteUtils.getConnection();
        int i = 1;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(i++, name);
            preparedStatement.setString(i++, password);
            preparedStatement.setString(i++, mail);
            preparedStatement.setString(i++, wechat);
            preparedStatement.setString(i++, groups);
            preparedStatement.setString(i++, createTime);
            preparedStatement.setInt(i++, userLevel);
            preparedStatement.setString(i++, phoneNumber);
            int result = preparedStatement.executeUpdate();
            logger.info("update result = " + result);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public User searchUser(String phoneNumber) {
        String sql = "select * from User where phoneNumber = "+"'"+phoneNumber+"'";
        Connection conn = sqliteUtils.getConnection();
        User user = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setPassword(resultSet.getString("password"));
                user.setMail(resultSet.getString("mail"));
                user.setWechat(resultSet.getString("wechat"));
                user.setUserLevel(resultSet.getInt("userLevel"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        StringBuilder selectSql = new StringBuilder("select * from user");
        try {
            conn = sqliteUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectSql.toString());
            while (rs.next()) {
                User user = new User();
                user = new User();
                user.setName(rs.getString("name"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setPassword(rs.getString("password"));
                user.setMail(rs.getString("mail"));
                user.setWechat(rs.getString("wechat"));
                user.setUserLevel(rs.getInt("userLevel"));
                users.add(user);
            }

        } catch (SQLException e) {
            logger.error("sql error" + e.getMessage(), e);
        } finally {
            sqliteUtils.close(conn, stmt, rs);
        }
        return users;
    }

    @Override
    public User getUserByTel(String tel) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        StringBuilder selectSql = new StringBuilder("select * from user where phoneNumber='").append(tel).append("'");
        User user = new User();
        try {
            conn = sqliteUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectSql.toString());
            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setPassword(rs.getString("password"));
                user.setMail(rs.getString("mail"));
                user.setWechat(rs.getString("wechat"));
                user.setUserLevel(rs.getInt("userLevel"));
            }

        } catch (SQLException e) {
            logger.error("sql error" + e.getMessage(), e);
        } finally {
            sqliteUtils.close(conn, stmt, rs);
        }
        return user;
    }

    @Override
    public User getUserById(int uuid) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        StringBuilder selectSql = new StringBuilder("select * from user where uuid='").append(uuid).append("'");
        User user = new User();
        try {
            conn = sqliteUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectSql.toString());
            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setPassword(rs.getString("password"));
                user.setMail(rs.getString("mail"));
                user.setWechat(rs.getString("wechat"));
                user.setUserLevel(rs.getInt("userLevel"));
            }

        } catch (SQLException e) {
            logger.error("sql error" + e.getMessage(), e);
        } finally {
            sqliteUtils.close(conn, stmt, rs);
        }
        return user;
    }

    @Override
    public User getUserByUserName(String userName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        StringBuilder selectSql = new StringBuilder("select * from user where name='").append(userName).append("'");
        User user = new User();
        try {
            conn = sqliteUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectSql.toString());
            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setPassword(rs.getString("password"));
                user.setMail(rs.getString("mail"));
                user.setWechat(rs.getString("wechat"));
                user.setUserLevel(rs.getInt("userLevel"));
            }

        } catch (SQLException e) {
            logger.error("sql error" + e.getMessage(), e);
        } finally {
            sqliteUtils.close(conn, stmt, rs);
        }
        return user;
    }

}
