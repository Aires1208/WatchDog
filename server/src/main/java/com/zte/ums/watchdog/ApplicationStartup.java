package com.zte.ums.watchdog;

import com.zte.ums.watchdog.dao.impl.InitializationTables;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by root on 2016/9/22.
 */
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        InitializationTables initializationTables = contextRefreshedEvent.getApplicationContext().getBean(InitializationTables.class);
        initializationTables.initialTables();
    }
}
