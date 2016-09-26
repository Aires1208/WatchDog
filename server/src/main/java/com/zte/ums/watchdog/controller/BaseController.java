package com.zte.ums.watchdog.controller;


import com.google.common.base.Strings;

import com.zte.ums.watchdog.common.constant.Constants;
import com.zte.ums.watchdog.common.message.Msg;
import com.zte.ums.watchdog.common.message.RespMsg;
import com.zte.ums.watchdog.common.message.RespMsgStatus;
import com.zte.ums.watchdog.model.User;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.beans.PropertyEditorSupport;
import java.util.List;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 13:18
 * @description: 控制器基类
 */

public class BaseController implements Msg {


    /**
     * <p>初始化数据绑定</p>
     * <p>将所有传递进来的String进行HTML编码，防止XSS攻击</p>
     * <p>将字段中Date类型转换为String类型</p>
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }

            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });

    }

    @Override
    public RespMsg ok(String content, Object data, String ipStr, User user) {
        if (Strings.isNullOrEmpty(content)) content = Constants.SUCCESS_MSG;
        return new RespMsg(content, data,ipStr,user);
    }

    @Override
    public RespMsg ok(Object data,String ipStr, User user) {
        return new RespMsg(Constants.SUCCESS_MSG, data,ipStr,user);
    }

    @Override
    public RespMsg error(String content, Object data,String ipStr, User user) {
        if (Strings.isNullOrEmpty(content)) content = Constants.FAIL_MSG;
        return new RespMsg(content, data,ipStr,null);
    }

    @Override
    public RespMsg error(Object data,String ipStr, User user) {
        return new RespMsg(RespMsgStatus.ERROR, Constants.FAIL_MSG, null,null);
    }
}
