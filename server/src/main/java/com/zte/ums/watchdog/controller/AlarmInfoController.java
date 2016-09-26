package com.zte.ums.watchdog.controller;

import com.zte.ums.watchdog.model.AlarmInfoSequence;
import com.zte.ums.watchdog.model.AlarmProcessMeta;
import com.zte.ums.watchdog.service.AlarmInfoService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by root on 2016/9/23.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AlarmInfoController {

    @Autowired
    private AlarmInfoService alarmInfoService;

    @RequestMapping("/alarmsInfo")
    public AlarmInfoSequence getAlarmsInfo() {
        return alarmInfoService.getAlarmInfo();
    }

    @RequestMapping(value = "/modifyAlarm", method = {RequestMethod.POST})
    @ResponseBody
    public void processAlarm(@RequestBody AlarmProcessMeta alarmProcessMeta) {
        alarmInfoService.updateAlarmMeta(alarmProcessMeta);
    }

    @RequestMapping(value = "/recordLog", method = {RequestMethod.POST})
    @ResponseBody
    public void recordLog(@RequestBody JSONObject json) {
        alarmInfoService.recordLog(json);
    }


    @RequestMapping(value = "/addAlarm", method = {RequestMethod.POST})
    @ResponseBody
    public void addAlarm(@RequestBody JSONObject json) {
        alarmInfoService.addAlarm(json);
    }


}
