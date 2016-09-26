package com.zte.ums.watchdog.controller;

import com.zte.ums.watchdog.common.utils.ValidateCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: lin
 * @Date: 2016-08-03 Time: 11:33
 * @description: <p>通用功能controller</p>
 */
@RestController
public class CommonController  {


    /**
     * 获取图形验证码
     *
     * @param response HttpResponse
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/common/getimagecode")
    public void imageCode(@RequestParam("reToken") String reToken, HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            ValidateCodeUtils.createImage(request, response,reToken);
            System.out.println("reToken..."+reToken);
//            log.info("reToken:"+reToken);
        } catch (IOException e) {
//            log.error("生成验证码错误...", e);
            System.out.println("生成验证码错误..."+e.toString());
        }
    }
}