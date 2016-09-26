package com.zte.ums.watchdog.controller;

import com.zte.ums.watchdog.model.Greeting;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by root on 2016/9/21.
 */
@RestController
public class JenkinsController {

    @RequestMapping("/api/jenkins/createApp/{ip}/{port}/{url}")
    public Greeting createJenkinsApp(@PathVariable(value = "ip") String ip,
                                     @PathVariable(value = "port") String port,
                                     @PathVariable(value = "url") String url) {
        return null;
    }
}
