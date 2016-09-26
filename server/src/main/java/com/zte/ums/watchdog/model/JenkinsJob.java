package com.zte.ums.watchdog.model;

/**
 * Created by root on 2016/9/21.
 */
public class JenkinsJob {
    private String name;
    private String url;
    private String color;

    public JenkinsJob(){}

    public JenkinsJob(String name, String url, String color) {
        this.name = name;
        this.url = url;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "JenkinsJob{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
