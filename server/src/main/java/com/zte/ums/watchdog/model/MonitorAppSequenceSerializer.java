package com.zte.ums.watchdog.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * Created by root on 2016/9/23.
 */
public class MonitorAppSequenceSerializer extends JsonSerializer<MonitorAppSequence> {
    @Override
    public void serialize(MonitorAppSequence monitorAppSequence, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("alarmsInfo");
        writeMonitorAppList(monitorAppSequence.getMonitorAppList(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }

    private void writeMonitorAppList(List<MonitorApp> monitorAppList, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartArray();
        for (MonitorApp monitorApp : monitorAppList) {
            writeMonitorApp(monitorApp, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }

    private void writeMonitorApp(MonitorApp monitorApp, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("alarmID", monitorApp.getAppId());
        jsonGenerator.writeStringField("alarmObject", monitorApp.getAppUrl());
        jsonGenerator.writeStringField("alarmContent", monitorApp.getUserId());
        jsonGenerator.writeStringField("belongApp", monitorApp.getUserName());
        jsonGenerator.writeStringField("hostIp", monitorApp.getCreateDate());
        jsonGenerator.writeEndObject();
    }
}
