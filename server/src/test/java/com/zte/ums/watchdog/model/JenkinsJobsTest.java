package com.zte.ums.watchdog.model;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import static com.zte.ums.watchdog.model.Color.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by  on 2016/9/21.
 */
public class JenkinsJobsTest {

    private JenkinsJobs jenkinsJobs;

    @Before
    public void buildJenkinsJobs() {
        jenkinsJobs = new JenkinsJobs(ImmutableList.of(new JenkinsJob("AMIGO-Automation-Test", "http://10.62.100.76:8080/jenkins/job/AMIGO-Automation-Test/", "disable"),
                new JenkinsJob("Apm_test_Compile", "http://10.62.100.76:8080/jenkins/job/Apm_test_Compile/", "blue"),
                new JenkinsJob("nfv-trace-ut", "http://10.62.100.76:8080/jenkins/job/nfv-trace-ut/", "red")));
    }


    @Test
    public void should_return_nfv_trace_ut_when_get_red_job() {
        List<JenkinsJob> jenkinsRedJobs = jenkinsJobs.getRedJenkinsJobs();
        for (JenkinsJob jenkinsJob : jenkinsRedJobs) {
            assertEquals("nfv-trace-ut", jenkinsJob.getName());
            assertEquals("http://10.62.100.76:8080/jenkins/job/nfv-trace-ut/", jenkinsJob.getUrl());
            assertEquals(RED.getValue(), jenkinsJob.getColor());
        }
    }
}
