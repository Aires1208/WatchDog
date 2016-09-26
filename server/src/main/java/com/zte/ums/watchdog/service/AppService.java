package com.zte.ums.watchdog.service;

/**
 * Created by root on 2016/9/22.
 */
public interface AppService {
    String createApp(String appUrl, String appCreateUser, String appCreateName);

    String modifyApp(String appUrl, String appCreateUser, String appCreateName);

    String deleteApp(String appUrl, String appCreateUser, String appCreateName);
}
