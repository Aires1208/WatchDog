package com.zte.ums.watchdog.common.env;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by root on 2016/9/22.
 */
@Component
@ConfigurationProperties(prefix = "sqlite")
public class SqliteEnvProperties {

    private String dbPath;


    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }

    public String getDbPath() {
        return "".equals(dbPath) ? "watchdog.db" : dbPath;
    }
}
