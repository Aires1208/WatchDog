package com.zte.ums.watchdog.common.utils;

import com.zte.ums.watchdog.common.env.SqliteEnvProperties;
import com.zte.ums.watchdog.common.constant.ErrorCodeConstant;
import com.zte.ums.watchdog.common.exception.WatchDogException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * Created by root on 2016/9/21.
 */
@Repository
public class SqliteUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqliteUtils.class);

    @Autowired
    private SqliteEnvProperties sqliteEnv;

    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("<========================");
            System.out.println("jdbc:sqlite:" + sqliteEnv.getDbPath());
            System.out.println("=========================>");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + sqliteEnv.getDbPath());
            conn.setAutoCommit(false);
            return conn;
        } catch (ClassNotFoundException e) {
            throw new WatchDogException(ErrorCodeConstant.CLASS_NOT_FOUND, e);
        } catch (SQLException e) {
            throw new WatchDogException(ErrorCodeConstant.SQL_ERROR, e);
        }
    }

    public void createTable(String tableSql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt.execute(tableSql);
            conn.commit();
        } catch (SQLException e) {
            throw new WatchDogException(ErrorCodeConstant.SQL_ERROR, e);
        } finally {
            closeStatement(stmt);
            closeConn(conn);
        }
    }

    /**
     * api for insert update or delete
     */
    public void put(String putSql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(putSql);
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                throw new WatchDogException(ErrorCodeConstant.ROLLBACK_ERROR, e);
            }
            throw new WatchDogException(ErrorCodeConstant.SQL_ERROR, e);
        } finally {
            closeStatement(stmt);
            closeConn(conn);
        }
    }

    /**
     * please write you select method,this is for sample
     */
    public ResultSet select(String selectSql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectSql);
            while (rs.next()) {

            }
            return rs;
        } catch (SQLException e) {
            LOGGER.error("sql error" + e.getMessage(), e);
        } finally {
            close(conn, stmt, rs);
        }
        return null;
    }

    public void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new WatchDogException(ErrorCodeConstant.CLOSE_RS_ERROR, e);
            }
        }
    }

    public void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new WatchDogException(ErrorCodeConstant.CLOSE_CONNECTION_ERROR, e);
            }
        }
    }

    public void close(Connection conn, Statement stmt, ResultSet rs) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new WatchDogException(ErrorCodeConstant.CLOSE_RS_ERROR, ex);
            }
        }
        if (null != stmt) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                throw new WatchDogException(ErrorCodeConstant.CLOSE_STATEMENT_ERROR, ex);
            }
        }
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new WatchDogException(ErrorCodeConstant.CLOSE_CONNECTION_ERROR, ex);
            }
        }
    }

    public void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new WatchDogException(ErrorCodeConstant.CLOSE_STATEMENT_ERROR, e);
            }
        }
    }

}
