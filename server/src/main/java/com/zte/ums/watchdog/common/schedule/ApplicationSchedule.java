package com.zte.ums.watchdog.common.schedule;

import com.zte.ums.watchdog.assignment.AssignmentPolicy;
import com.zte.ums.watchdog.common.env.JenkinsEnvProperties;
import com.zte.ums.watchdog.common.exception.WatchDogException;
import com.zte.ums.watchdog.model.JenkinsJob;
import com.zte.ums.watchdog.model.JenkinsJobs;
import com.zte.ums.watchdog.common.constant.HttpConstant;
import com.zte.ums.watchdog.common.utils.HttpUtils;
import com.zte.ums.watchdog.common.utils.JacksonUtils;
import com.zte.ums.watchdog.service.AlarmService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by root on 2016/9/20.
 */
@Component
@Configurable
@EnableScheduling
public class ApplicationSchedule {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationSchedule.class);

    @Autowired
    @Qualifier("alarmService")
    private AlarmService alarmService;

    @Autowired
    private JenkinsEnvProperties env;
    @Autowired

    private AssignmentPolicy assignmentPolicy;

    @Scheduled(cron = "*/5 * * * * *")
    public void scanJenkinsRedTasks() {
        String restUrl = HttpConstant.HTTP + env.getIp() + HttpConstant.COLON + env.getPort()
                + ("".equals(env.getContext()) ? "" : HttpConstant.SLASH + env.getContext())
                + HttpConstant.ALL_JOBS_URL;
        try {
            LOGGER.info("jenkins restful url:" + restUrl);
            String httpResponse = HttpUtils.get(env.getIp(), Integer.parseInt(env.getPort()), restUrl);
            JenkinsJobs jenkinsJobs = getJenkinsRedJob(httpResponse);
            System.out.println("<=================");
            for (JenkinsJob jenkinsJob : jenkinsJobs.getRedJenkinsJobs()) {
                alarmService.addAlarm(jenkinsJob);
            }
            jenkinsJobs.getRedJenkinsJobs().forEach(System.out::println);
            System.out.println("===================>");

            assignmentPolicy.assignmentPolicy();

        } catch (WatchDogException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("json to list error:" + e.getMessage(), e);
        }
    }

    private JenkinsJobs getJenkinsRedJob(String response) throws Exception {
        JSONObject jsonObject = JSONObject.fromObject(response);
        JSONArray jsonArray = (JSONArray) jsonObject.get("jobs");
        List<JenkinsJob> jenkinsJobs = JacksonUtils.json2list(jsonArray.toString(), JenkinsJob.class);
        return new JenkinsJobs(jenkinsJobs);

    }
}
