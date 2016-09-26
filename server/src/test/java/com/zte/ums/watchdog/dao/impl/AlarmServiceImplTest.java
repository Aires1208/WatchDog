package com.zte.ums.watchdog.dao.impl;

import com.zte.ums.watchdog.common.sms.MobileMessageSend;
import com.zte.ums.watchdog.model.Alarm;
import com.zte.ums.watchdog.service.impl.AlarmServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

/**
 * Created by root on 9/22/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AlarmServiceImplTest {
    @Autowired
    private AlarmServiceImpl alarmService;

    @Test
    public void getAlarms() {
        System.out.println("get alarms....");
//        List<Alarm> alarmList=alarmService.getAlarmsByUserName("zhangsan");
//        for (Alarm alarm:alarmList ) {
//            System.out.println(alarm.getProcessingMember());
////            try {
////                MobileMessageSend.sendMsg("15958199403","Aires");
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//        }
    }
}
