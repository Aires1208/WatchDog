package com.zte.ums.watchdog.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zte.ums.watchdog.common.constant.AlarmConstant;

import java.io.IOException;
import java.util.List;

/**
 * Created by root on 2016/9/23.
 */
public class AlarmInfoSequenceSerializer extends JsonSerializer<AlarmInfoSequence> {
    @Override
    public void serialize(AlarmInfoSequence alarmInfoSequence, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("alarmsInfo");
        writeAlarmsInfo(alarmInfoSequence.getAlarmsInfos(), jsonGenerator);
        jsonGenerator.writeEndObject();
}

    private void writeAlarmsInfo(List<AlarmsInfo> alarmsInfos, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartArray();
        for (AlarmsInfo alarmsInfo : alarmsInfos) {
            writeAlarmInfo(alarmsInfo, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }

    private void writeAlarmInfo(AlarmsInfo alarmsInfo, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();
        Alarm alarm = alarmsInfo.getAlarm();
        jsonGenerator.writeStringField("alarmID", alarm.getAlarmId());
        jsonGenerator.writeStringField("alarmObject", alarm.getAlarmObject());
        jsonGenerator.writeStringField("alarmContent", alarm.getAlarmContext());
        jsonGenerator.writeStringField("belongApp", alarm.getBelogApp());
        jsonGenerator.writeStringField("hostIp", alarm.getHostIp());
        jsonGenerator.writeStringField("alarmLevel", alarm.getAlarmLevel());
        jsonGenerator.writeStringField("alarmStatue", AlarmConstant.getAlarmStatus(Integer.parseInt(alarm.getAlarmStatus())));
        jsonGenerator.writeStringField("activeTime", alarm.getActiveTime());
        jsonGenerator.writeStringField("processingMember", alarm.getProcessingMember());
        jsonGenerator.writeStringField("processingTime", alarm.getProcessingTime());
        jsonGenerator.writeFieldName("historyProcessingMember");
        writeHistoryProcessingMember(alarmsInfo.getAlarmHistoryOperates(), jsonGenerator);
        jsonGenerator.writeFieldName("recording");
        writeRecording(alarmsInfo.getAlarmHistoryLogs(), jsonGenerator);
        jsonGenerator.writeEndObject();

    }

    private void writeRecording(List<AlarmHistoryLog> alarmHistoryLogs, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartArray();
        for (AlarmHistoryLog alarmHistoryLog : alarmHistoryLogs) {
            writeEachRecording(alarmHistoryLog, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }

    private void writeEachRecording(AlarmHistoryLog alarmHistoryLog, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("recordingMember", alarmHistoryLog.getRecordingMember());
        jsonGenerator.writeStringField("recordingType", alarmHistoryLog.getRecordingType());
        jsonGenerator.writeStringField("recordingContent", alarmHistoryLog.getRecodeingContext());
        jsonGenerator.writeStringField("recordingTime", alarmHistoryLog.getRecordingTime());
        jsonGenerator.writeEndObject();
    }

    private void writeHistoryProcessingMember(List<AlarmHistoryOperate> alarmHistoryOperates, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartArray();
        for (AlarmHistoryOperate alarmHistoryOperate : alarmHistoryOperates) {
            writeEachHistoryProcessingMember(alarmHistoryOperate, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }

    private void writeEachHistoryProcessingMember(AlarmHistoryOperate alarmHistoryOperate, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("processingMember", alarmHistoryOperate.getProcessingMember());
        jsonGenerator.writeStringField("processingTime", alarmHistoryOperate.getProcesingTime());
        jsonGenerator.writeEndObject();
    }

}
