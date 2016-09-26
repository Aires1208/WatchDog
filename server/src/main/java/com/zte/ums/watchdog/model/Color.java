package com.zte.ums.watchdog.model;

/**
 * Created by root on 2016/9/21.
 */
public enum Color {
    RED("red"), BLUE("blue"), DISABLED("disabled"), NOBUILT("notbuilt");

    private String value;

    Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
