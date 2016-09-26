package com.zte.ums.watchdog.dao.impl;

import com.zte.ums.watchdog.common.sms.MobileMessageSend;
import com.zte.ums.watchdog.common.utils.TimeUtils;
import com.zte.ums.watchdog.dao.AlarmDao;
import com.zte.ums.watchdog.dao.AlarmHistoryLogDao;
import com.zte.ums.watchdog.dao.AlarmHistoryOperateDao;
import com.zte.ums.watchdog.dao.AlarmInfoDao;
import com.zte.ums.watchdog.model.*;
import com.zte.ums.watchdog.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by root on 2016/9/23.
 */
@Repository
public class AlarmInfoDaoImpl implements AlarmInfoDao {
    @Autowired
    private AlarmDao alarmDao;
    //    @Autowired
//    AlarmInfoDaoImpl alarmInfoDaoImpl;
    @Autowired
    private AlarmHistoryLogDao alarmHistoryLogDao;

    @Autowired
    private AlarmHistoryOperateDao alarmHistoryOperateDao;

    @Autowired
    private UserDaoImpl userDao;


    @Override
    public List<AlarmsInfo> getAlarmsInfo() {
        List<AlarmsInfo> alarmsInfos = newArrayList();
        AlarmsInfo alarmsInfo = null;
        Alarms alarmList = alarmDao.getAlarms();
        for (Alarm alarm : alarmList.getAlarmList()) {
            alarmsInfo = new AlarmsInfo();
            alarmsInfo.setAlarm(alarm);
            alarmsInfo.setAlarmHistoryOperates(alarmHistoryOperateDao.getAlarmHistoryOperateByAlarmId(alarm.getAlarmId()));
            alarmsInfo.setAlarmHistoryLogs(alarmHistoryLogDao.getAlarmHistoryLogByAlarmId(alarm.getAlarmId()));
            alarmsInfos.add(alarmsInfo);
        }
        return alarmsInfos;
    }

    @Override
    public void updateAlarmMeta(AlarmProcessMeta alarmProcessMeta) {
        String alarmId = alarmProcessMeta.getAlarmID();
        String loginStatus = alarmProcessMeta.getLoginStatus();
        String userId = alarmProcessMeta.getUserID();
        String userName = alarmProcessMeta.getUserName();
        String modifyTye = alarmProcessMeta.getModifyType();
        String modifyContent = alarmProcessMeta.getModifyContent();
        String recordingType = alarmProcessMeta.getRecordingType();
        String recordingContent = alarmProcessMeta.getRecordingContent();
        alarmHistoryLogDao.addalarmHistoryLog(new AlarmHistoryLog(alarmId, userName, recordingType, recordingContent, TimeUtils.dateToString()));
        if (!alarmDao.isProcessorNull(alarmId)) {
            Alarm alarm = alarmDao.getAlarmByAlarmId(alarmId);
            String processingMember = alarm.getProcessingMember();
//            String usName = "";
//            List<AlarmsInfo> alist = alarmInfoDaoImpl.getAlarmsInfo();
//            for(int i=0;i<alist.size();i++){
//                if (alist.get(i).getAlarm().getAlarmId() == alarmId){
//                    usName = alist.get(i).getAlarm().getProcessingMember();
//                    break;
//                }
//            }
            alarmHistoryOperateDao.addHistoryAlarmOperate(new AlarmHistoryOperate(alarmId, processingMember, TimeUtils.dateToString()));
        }else {
            alarmHistoryOperateDao.addHistoryAlarmOperate(new AlarmHistoryOperate(alarmId, userName, TimeUtils.dateToString()));

        }


        alarmDao.updateAlarm(alarmProcessMeta);

        if ("2".equals(modifyContent)) {
            User user = userDao.getUserByUserName(userName);
            try {
                MobileMessageSend.sendMsg(user.getPhoneNumber(), userName);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void recordLog(JSONObject json) {
        alarmHistoryLogDao.recordLog(json);
    }

    @Override
    public void addAlarm(JSONObject json) {
        alarmDao.addGivenAlarm(json);

    }
}
