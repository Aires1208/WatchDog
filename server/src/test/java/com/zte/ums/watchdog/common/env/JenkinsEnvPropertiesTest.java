package com.zte.ums.watchdog.common.env;

import com.zte.ums.watchdog.common.env.JenkinsEnvProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by  on 2016/9/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JenkinsEnvPropertiesTest {

    @Autowired
    JenkinsEnvProperties jenkins;

    @Test
    public void should_be_equals_when_get_jenkins_ip() {
        assertEquals("10.62.100.76", jenkins.getIp());
    }

    @Test
    public void should_be_equals_when_get_jenkins_port() {
        assertEquals("8080", jenkins.getPort());
    }

    @Test
    public void should_be_equals_when_get_jenkins_context() {
        assertEquals("jenkins", jenkins.getContext());
    }

}
