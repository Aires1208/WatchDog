package com.zte.ums.watchdog.common.message;

import com.zte.ums.watchdog.model.User;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 13:21
 * @description: 消息接口
 *               定义控制器输出到页面的消息
 */
public interface Msg<T> {

    //--------------------------异步操作-------------------------------


    /**
     * 操作成功
     * @param content 自定义提示语
     * @param data 返回的数据集合
     * @return
     */
    RespMsg<T> ok(String content,T data, String alaiStr, User user);

    /**
     * 操作成功
     * @param data 返回的数据集合
     * @return
     */
    RespMsg<T> ok(T data, String alaiStr, User user);

    /**
     * 操作失败
     * @param content 自定义提示语
     * @param data 返回的数据集合
     * @return
     */
    RespMsg<T> error(String content,T data, String alaiStr, User user);

    /**
     * 操作失败
     * @param data 返回的数据集合
     * @return
     */
    RespMsg<T> error(T data, String alaiStr, User user);



    //--------------------------异步操作-------------------------------




}
