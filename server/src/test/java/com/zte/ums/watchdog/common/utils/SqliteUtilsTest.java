package com.zte.ums.watchdog.common.utils;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by  on 2016/9/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SqliteUtilsTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqliteUtilsTest.class);

    @Autowired
    private SqliteUtils utils;

    @Before
    public void createTable() {
        utils.createTable("DROP TABLE  IF EXISTS COMPANY");
        String createSql = "CREATE TABLE COMPANY " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " NAME           TEXT    NOT NULL, " +
                " AGE            INT     NOT NULL, " +
                " ADDRESS        CHAR(50), " +
                " SALARY         REAL)";

        utils.createTable(createSql);
    }

    @Test
    public void insert2Table() throws SQLException {
        String sql = "INSERT INTO COMPANY (NAME,AGE,ADDRESS,SALARY) " +
                "VALUES ('Paul', 32, 'California', 20000.00 );";
        utils.put(sql);
    }

    @Test
    public void scanTable() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = utils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM COMPANY");
            while (rs.next()) {
                assertTrue(1 == rs.getInt("ID"));
                assertEquals("Paul", rs.getString("NAME"));
                assertTrue(32 == rs.getInt("AGE"));
                assertEquals("California", rs.getString("ADDRESS"));
                assertTrue(20000.00 == rs.getDouble("SALARY"));
            }
        } catch (SQLException e) {
            LOGGER.error("sql error" + e.getMessage(), e);
        } finally {
            utils.close(conn, stmt, rs);
        }

    }
}
