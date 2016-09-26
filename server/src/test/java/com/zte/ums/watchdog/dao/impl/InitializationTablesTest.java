package com.zte.ums.watchdog.dao.impl;

import com.zte.ums.watchdog.common.env.SqliteEnvProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Goldon on 2016/9/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan(basePackageClasses = {InitializationTables.class, SqliteEnvProperties.class})
@SpringBootTest
public class InitializationTablesTest {
    @Autowired
    private InitializationTables initializationTables;

    @Test
    public void initialTables(){
        initializationTables.initialTables();
    }
}