package com.zte.ums.watchdog.controller;

import com.zte.ums.watchdog.common.constant.Constants;
import com.zte.ums.watchdog.common.message.Msg;
import com.zte.ums.watchdog.common.message.RespMsg;
import com.zte.ums.watchdog.dao.impl.UserDaoImpl;
import com.zte.ums.watchdog.model.User;
import com.zte.ums.watchdog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by root on 2016/9/21.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @Autowired
    UserDaoImpl userDaoImpl;

    @RequestMapping(value="/login1",method = RequestMethod.POST)
    public String loggin1(@RequestParam(value="phoneNumber") String phoneNumber,@RequestParam(value="pwd") String pwd) {
        User user = userService.searchUser(phoneNumber);
        if (null != user){
            if(!user.getPassword().equals(pwd)){
                return "password is not correct !!!";
            }else{
                return "loggin success";
            }
        }else{
            return "user "+ phoneNumber +" not exist";
        }
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public @ResponseBody RespMsg loggin(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
//        String alaiStr = UtilsTools.getIpAddr(request);
        String alaiStr = "";
        User user1 = userService.searchUser(user.getPhoneNumber());
        if (null != user1){
            if(!user1.getPassword().equals(user.getPassword())){
                return error(Constants.MSG_TYPE_DANGER, "密码不正确！",alaiStr,null);
            }else{
                user1.setPassword("");
                user1.setMail("");
                user1.setGroupNames(null);
                return ok("登录认证通过",alaiStr,user1);
            }
        }else{
            return error(Constants.MSG_TYPE_DANGER, "无此用户，或其他错误！",alaiStr,null);
        }
    }

    @RequestMapping(value="/getUsers",method = RequestMethod.GET)
    public @ResponseBody List<User> getUsers() {
        List<User> users = userDaoImpl.getUsers();
        if (null != users ){
           return users;
        }else{
            return null;
        }
    }


    @RequestMapping(value="/register",method = RequestMethod.POST)
    public String register(@RequestParam(value="phoneNumber") String phoneNumber,@RequestParam(value="name") String name,@RequestParam(value="pwd") String pwd) {
        User user = userService.searchUser(phoneNumber);
        if (null != user){
            return "register "+ phoneNumber +" has exist";
        }else{
            User newUser = new User();
            newUser.setPhoneNumber(phoneNumber);
            newUser.setName(name);
            newUser.setPassword(pwd);
            userService.createUser(newUser);
            return "register success";
        }
    }

    @RequestMapping(value="/addregister",method = RequestMethod.POST)
    public void addUser(@RequestParam(value="phoneNumber") String phoneNumber,@RequestParam(value="name") String name,@RequestParam(value="password")  String password,@RequestParam(value="mail")  String mail,@RequestParam(value="wechat") String wechat,@RequestParam(value="groups") String groups,@RequestParam(value="createTime")  String createTime,@RequestParam(value="userLevel") int userLevel){
        userService.createUser(phoneNumber,name,password,mail,wechat,groups,createTime,userLevel);
    }
    @RequestMapping(value="/deleteuser",method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam(value="phoneNumber") String phoneNumber){
        userService.deleteUser(phoneNumber);
    }
    @RequestMapping(value="/edituser",method = RequestMethod.PUT)
    public void edituser(@RequestParam(value="phoneNumber") String phoneNumber,@RequestParam(value="name") String name,@RequestParam(value="password")  String password,@RequestParam(value="mail")  String mail,@RequestParam(value="wechat") String wechat,@RequestParam(value="groups") String groups,@RequestParam(value="createTime")  String createTime,@RequestParam(value="userLevel") int userLevel){
        userService.editUser(phoneNumber, name, password, mail, wechat, groups, createTime, userLevel);
    }
}
