package com.zte.ums.watchdog.common.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 10172605 on 2016/9/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HttpUtilsTest {

    @Test
    public void getJenkinsTask() {
        String httpResponse = HttpUtils.get("10.62.100.76", 8080, "http://10.62.100.76:8080/jenkins/api/json?tree=jobs[name,url,color]");
        System.out.println("<==================");
        System.out.println(httpResponse);
        System.out.println("==================>");
    }
}