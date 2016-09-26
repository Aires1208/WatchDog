package com.zte.ums.watchdog.controller;

import com.zte.ums.watchdog.common.utils.TimeUtils;
import com.zte.ums.watchdog.model.MonitorApp;
import com.zte.ums.watchdog.model.MonitorAppSequence;
import com.zte.ums.watchdog.service.MonitorAppService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by root on 2016/9/23.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MonitorAppController {
    @Autowired
    private MonitorAppService monitorAppService;


    @RequestMapping(value = "/createMonitorApp", method = {RequestMethod.POST})
    @ResponseBody
    public void createMonitorApp(@RequestBody JSONObject jsonObject) {
        String appUrl = (String) jsonObject.get("appUrl");
        String userId = (String) jsonObject.get("userId");
        String userName = (String) jsonObject.get("userName");
        MonitorApp monitorApp = new MonitorApp(appUrl, userId, userName, TimeUtils.dateToString());
        monitorAppService.createMonitorApp(monitorApp);
    }

    @RequestMapping(value = "/deleteMonitorAppByAppId/{appId}", method = RequestMethod.DELETE)
    public void deleteMonitorAppByAppId(@PathVariable("appId") String appId) {
        monitorAppService.deleteMonitorAppByAppId(appId);
    }

    @RequestMapping(value = "/updateMonitorApp", method = {RequestMethod.POST})
    @ResponseBody
    public void updateMonitorApp(@RequestBody JSONObject jsonObject) {
        String appId = (String) jsonObject.get("appId");
        String appUrl = (String) jsonObject.get("appUrl");
        String userId = (String) jsonObject.get("userId");
        String userName = (String) jsonObject.get("userName");
        monitorAppService.updateMonitorApp(new MonitorApp(appId, appUrl, userId, userName, TimeUtils.dateToString()));
    }

    @RequestMapping(value = "/queryMonitorApp", method = {RequestMethod.GET})
    public MonitorAppSequence queryMonitorApp() {
        return new MonitorAppSequence(monitorAppService.queryMonitorApp());
    }

    @RequestMapping(value = "/queryMonitorAppById/{appId}", method = {RequestMethod.GET})
    public JSONObject queryMonitorAppById(@PathVariable("appId") String appId) {
        MonitorApp monitorApp = monitorAppService.queryMonitorAppById(appId);
        return JSONObject.fromObject(monitorApp);
    }

}
