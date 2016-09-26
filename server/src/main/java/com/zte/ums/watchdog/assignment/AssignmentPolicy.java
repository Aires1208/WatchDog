package com.zte.ums.watchdog.assignment;

import com.zte.ums.watchdog.common.sms.MobileMessageSend;
import com.zte.ums.watchdog.dao.impl.AlarmDaoImpl;
import com.zte.ums.watchdog.dao.impl.UserDaoImpl;
import com.zte.ums.watchdog.model.Alarm;
import com.zte.ums.watchdog.model.User;
import com.zte.ums.watchdog.service.impl.AlarmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 9/23/16.
 */
@Service
public class AssignmentPolicy {
    @Autowired
    private AlarmDaoImpl alarmDao;

    @Autowired
    private AlarmServiceImpl alarmService;

    @Autowired
    private UserDaoImpl userDao;

    public void assignmentPolicy() {
        List<Alarm> alarms = alarmService.getAlarms();
        if (null != alarms && alarms.size() > 0) {
            for (Alarm alarm : alarms) {
                if (null==alarm.getProcessingMember()||alarm.getProcessingMember().isEmpty()) {
                    int alarmId = Integer.parseInt(alarm.getAlarmId());
                    User user = new User();
                    if (alarmId % 2 != 0) {
                        user = userDao.getUserById(1);
                    } else {
                        user = userDao.getUserById(2);
                    }
//                    Map<String, Object> updateValues = new HashMap<String, Object>();
//                    updateValues.put("PROCESSINGMEMBER", user.getName());
//                    alarmDao.modifyAlarm(alarm.getAlarmId(), updateValues);

                    try {
                        MobileMessageSend.sendMsg(user.getPhoneNumber(), user.getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("MobileMessageSend.sendMsg(user.getPhoneNumber(), user.getName())");
                }
            }
        }
    }
}
