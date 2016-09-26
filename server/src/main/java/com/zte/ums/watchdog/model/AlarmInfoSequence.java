package com.zte.ums.watchdog.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * Created by root on 2016/9/23.
 */
@JsonSerialize(using = AlarmInfoSequenceSerializer.class)
public class AlarmInfoSequence {
    private List<AlarmsInfo> alarmsInfos;

    public AlarmInfoSequence(List<AlarmsInfo> alarmsInfos) {
        this.alarmsInfos = alarmsInfos;
    }

    public List<AlarmsInfo> getAlarmsInfos() {
        return alarmsInfos;
    }

}
