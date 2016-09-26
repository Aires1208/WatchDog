package com.zte.ums.watchdog.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by root on 2016/9/22.
 */
@RestController
public class MockWatchdog {
    String mockJson = "{\n" +
            "  \"alarmsInfo\":[\n" +
            "    {\n" +
            "      \"alarmID\": 11111,\n" +
            "      \"alarmObject\": \"alarmObject\",\n" +
            "      \"alarmContent\": \"Shanghai Pudong Data-center\",\n" +
            "      \"belongApp\": \"belongApp\",\n" +
            "      \"hostIp\":\"10.62.100.76:8080/jenkins\",\n" +
            "      \"alarmLevel\": \"提醒\",\n" +
            "      \"alarmStatue\": \"待处理\",\n" +
            "      \"activeTime\": \"2016-06-02T02:49:51.000000\",\n" +
            "      \"processingMember\":\"乔木\",\n" +
            "      \"processingTime\": \"2016-06-02T02:49:51.000000\",\n" +
            "      \"historyProcessingMember\":[\n" +
            "        {\n" +
            "          \"processingMember\":\"陈立鹏\",\n" +
            "          \"processingTime\": \"2016-06-02T02:49:51.000000\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"processingMember\":\"乔木\",\n" +
            "          \"processingTime\": \"2016-06-02T02:49:51.000000\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"recording\":[\n" +
            "        {\n" +
            "          \"recordingMember\":\"陈立鹏\",\n" +
            "          \"recordingType\":\"已关闭报警\",\n" +
            "          \"recordingContent\":\"1111111\",\n" +
            "          \"recordingTime\": \"2016-06-02T02:49:51.000000\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"alarmID\": 11112,\n" +
            "      \"alarmObject\": \"alarmObject\",\n" +
            "      \"alarmContent\": \"Shanghai Pudong Data-center\",\n" +
            "      \"belongApp\": \"belongApp\",\n" +
            "      \"hostIp\":\"10.62.100.76:8080/jenkins\",\n" +
            "      \"alarmLevel\": \"提醒\",\n" +
            "      \"alarmStatue\": \"处理中\",\n" +
            "      \"activeTime\": \"2016-06-02T02:49:51.000000\",\n" +
            "      \"processingMember\":\"乔木\",\n" +
            "      \"processingTime\": \"2016-06-02T02:49:51.000000\",\n" +
            "      \"historyProcessingMember\":[\n" +
            "        {\n" +
            "          \"processingMember\":\"陈立鹏\",\n" +
            "          \"processingTime\": \"2016-06-02T02:49:51.000000\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"processingMember\":\"乔木\",\n" +
            "          \"processingTime\": \"2016-06-02T02:49:51.000000\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"recording\":[\n" +
            "        {\n" +
            "          \"recordingMember\":\"陈立鹏\",\n" +
            "          \"recordingType\":\"已关闭报警\",\n" +
            "          \"recordingContent\":\"1111111\",\n" +
            "          \"recordingTime\": \"2016-06-02T02:49:51.000000\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"alarmID\": 11113,\n" +
            "      \"alarmObject\": \"alarmObject\",\n" +
            "      \"alarmContent\": \"Shanghai Pudong Data-center\",\n" +
            "      \"belongApp\": \"belongApp\",\n" +
            "      \"hostIp\":\"10.62.100.76:8080/jenkins\",\n" +
            "      \"alarmLevel\": \"通知\",\n" +
            "      \"alarmStatue\": \"已转交\",\n" +
            "      \"activeTime\": \"2016-06-02T02:49:51.000000\",\n" +
            "      \"processingMember\":\"乔木\",\n" +
            "      \"processingTime\": \"2016-06-02T02:49:51.000000\",\n" +
            "      \"historyProcessingMember\":[\n" +
            "        {\n" +
            "          \"processingMember\":\"陈立鹏\",\n" +
            "          \"processingTime\": \"2016-06-02T02:49:51.000000\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"recording\":[\n" +
            "        {\n" +
            "          \"recordingMember\":\"陈立鹏\",\n" +
            "          \"recordingType\":\"已关闭报警\",\n" +
            "          \"recordingContent\":\"1111111\",\n" +
            "          \"recordingTime\": \"2016-06-02T02:49:51.000000\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"alarmID\": 11114,\n" +
            "      \"alarmObject\": \"alarmObject\",\n" +
            "      \"alarmContent\": \"Shanghai Pudong Data-center\",\n" +
            "      \"belongApp\": \"belongApp\",\n" +
            "      \"hostIp\":\"10.62.100.76:8080/jenkins\",\n" +
            "      \"alarmLevel\": \"严重\",\n" +
            "      \"alarmStatue\": \"已关闭\",\n" +
            "      \"activeTime\": \"2016-06-02T02:49:51.000000\",\n" +
            "      \"processingMember\":\"乔木\",\n" +
            "      \"processingTime\": \"2016-06-02T02:49:51.000000\",\n" +
            "      \"historyProcessingMember\":[\n" +
            "        {\n" +
            "          \"processingMember\":\"陈立鹏\",\n" +
            "          \"processingTime\": \"2016-06-02T02:49:51.000000\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"recording\":[\n" +
            "        {\n" +
            "          \"recordingMember\":\"陈立鹏\",\n" +
            "          \"recordingType\":\"已关闭报警\",\n" +
            "          \"recordingContent\":\"1111111\",\n" +
            "          \"recordingTime\": \"2016-06-02T02:49:51.000000\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}\n" +
            "\n" +
            "\n" +
            "\n";

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/mock/watchdog")
    public String mockWatch() {
        return mockJson;
    }
}
