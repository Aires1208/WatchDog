package com.zte.ums.watchdog.common.env;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by root on 2016/9/20.
 */
@Component
@ConfigurationProperties(prefix = "jenkins")
public class JenkinsEnvProperties {
    private String ip;

    private String port;

    private String context;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
