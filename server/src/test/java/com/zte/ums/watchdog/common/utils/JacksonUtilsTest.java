package com.zte.ums.watchdog.common.utils;

import com.zte.ums.watchdog.model.JenkinsJob;
import com.zte.ums.watchdog.model.JenkinsJobs;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.List;

import static com.zte.ums.watchdog.model.Color.RED;
import static org.junit.Assert.assertEquals;

/**
 * Created by 10172605 on 2016/9/21.
 */
public class JacksonUtilsTest {
    String response = "{\n" +
            "    \"jobs\":[\n" +
            "        {\n" +
            "            \"name\":\"AMIGO-Automation-Test\",\n" +
            "            \"url\":\"http://10.62.100.76:8080/jenkins/job/AMIGO-Automation-Test/\",\n" +
            "            \"color\":\"disabled\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\":\"AMIGO-PMMgt-Compile\",\n" +
            "            \"url\":\"http://10.62.100.76:8080/jenkins/job/AMIGO-PMMgt-Compile/\",\n" +
            "            \"color\":\"disabled\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\":\"AMIGO-PMMgt-Docker\",\n" +
            "            \"url\":\"http://10.62.100.76:8080/jenkins/job/AMIGO-PMMgt-Docker/\",\n" +
            "            \"color\":\"red\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Test
    public void Json2Pojo() throws Exception {
        JSONObject jsonObject = JSONObject.fromObject(response);
        JSONArray jsonArray = (JSONArray) jsonObject.get("jobs");
        List<JenkinsJob> jenkinsJobs = JacksonUtils.json2list(jsonArray.toString(), JenkinsJob.class);
        JenkinsJobs jenkinsRedJobs = new JenkinsJobs(jenkinsJobs);
        for (JenkinsJob jenkinsJob : jenkinsRedJobs.getRedJenkinsJobs()) {
            assertEquals(RED.getValue(), jenkinsJob.getColor());
            assertEquals("http://10.62.100.76:8080/jenkins/job/AMIGO-PMMgt-Docker/", jenkinsJob.getUrl());
            assertEquals("AMIGO-PMMgt-Docker", jenkinsJob.getName());
        }
    }

}