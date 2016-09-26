package com.zte.ums.watchdog.model;

import java.util.List;
import java.util.stream.Collectors;

import static com.zte.ums.watchdog.model.Color.RED;

/**
 * Created by root on 2016/9/21.
 */
public class JenkinsJobs {
    private List<JenkinsJob> jenkinsJobs;

    private JenkinsJobs(){}
    public JenkinsJobs(List<JenkinsJob> jenkinsJobs) {
        this.jenkinsJobs = jenkinsJobs;
    }

    public List<JenkinsJob> getJenkinsJobs() {
        return jenkinsJobs;
    }

    public List<JenkinsJob> getRedJenkinsJobs() {
        List<JenkinsJob> jenkinsRedJobs = jenkinsJobs
                .stream()
                .filter(job -> job.getColor().equals(RED.getValue()))
                .collect(Collectors.toList());
        return jenkinsRedJobs;
    }
}
