package com.zte.ums.watchdog.dao;

import com.zte.ums.watchdog.assignment.AssignmentPolicy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by root on 9/23/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AssignmentPolicyTest {
    @Autowired

    private AssignmentPolicy assignmentPolicy;

    @Test
    public void assignmentPolicyTest() {
        assignmentPolicy.assignmentPolicy();

    }
}
