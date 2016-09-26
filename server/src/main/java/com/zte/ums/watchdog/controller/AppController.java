package com.zte.ums.watchdog.controller;

import com.zte.ums.watchdog.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by root on 2016/9/22.
 */
@RestController
public class AppController {
    @Autowired
    private AppService appService;

    @RequestMapping("/app/create/{appUrl}/{userId}/{userName}")
    public String createApp(@PathVariable(value = "appUrl") String appUrl,
                            @PathVariable(value = "userId") String userId,
                            @PathVariable(value = "userName") String userName) {
        return appService.createApp(appUrl, userId, userName);
    }

    @RequestMapping("/app/modify/{appUrl}/{userId}/{userName}")
    public String modifyApp(@PathVariable(value = "appUrl") String appUrl,
                            @PathVariable(value = "userId") String userId,
                            @PathVariable(value = "userName") String userName) {
        return appService.modifyApp(appUrl, userId, userName);
    }

    @RequestMapping("/app/delete/{appUrl}/{userId}/{userName}")
    public String deleteApp(@PathVariable(value = "appUrl") String appUrl,
                            @PathVariable(value = "userId") String userId,
                            @PathVariable(value = "userName") String userName) {
        return appService.deleteApp(appUrl, userId, userName);
    }

    @RequestMapping("/app/query/{appUrl}/{appCreateUser}/{appCreateName}")
    public String queryApp(@PathVariable(value = "appUrl") String appUrl,
                           @PathVariable(value = "appCreateUser") String appCreateUser,
                           @PathVariable(value = "appCreateName") String appCreateName) {
        return appService.deleteApp(appUrl, appCreateUser, appCreateName);
    }

}
