package com.zte.ums.watchdog.dao.impl;

import com.zte.ums.watchdog.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by root on 9/23/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan(basePackageClasses = {InitializationTables.class, SqliteEnvProperties.class})
@SpringBootTest
public class UserDaoImplTest {
    @Autowired
    private UserDaoImpl userDao;

    @Test
    public void getUserByTelTest() {
        System.out.println(userDao.getUserByTel("18565695919"));
    }

    @Test
    public void getUserByUserNameTest() {
        System.out.println(userDao.getUserByUserName("ChenLiPeng"));
    }

    @Test
    public void createUserTest() {

        userDao.createUser("15800775585","Aires","Aa888888","aire@163.com","test","1","2012",2);

    }

    @Test
    public void getUsersTest() {
        for (User user : userDao.getUsers()) {
            System.out.println(user.toString());
        }
    }
    @Test
    public void getUserByIdTest() {
            System.out.println(userDao.getUserById(1));
    }
}
