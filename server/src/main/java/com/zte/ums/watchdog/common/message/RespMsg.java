package com.zte.ums.watchdog.common.message;

/**
 * Created by 10165228 on 2016/9/23.
 */
import com.zte.ums.watchdog.model.User;
import lombok.Data;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 13:31
 * @description: 响应AJAX请求返回对象，通常返回JSON格式
 */
@Data
public class RespMsg <T>{

    private int code = RespMsgStatus.OK;

    private String msg;

    private String alaiStr;

    private T data;

    private User user;

    public RespMsg(){

    }

    public RespMsg(int code, String msg, String alaiStr,User user) {
        this.code = code;
        this.msg = msg;
        this.alaiStr = alaiStr;
        this.user = user;
    }

    public RespMsg(String msg,T data, String alaiStr,User user) {
        this.msg = msg;
        this.data = data;
        this.alaiStr = alaiStr;
        this.user = user;
    }

    public RespMsg(int code, String msg, T data, String alaiStr,User user) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.alaiStr = alaiStr;
        this.user = user;
    }

    @Override
    public String toString() {
        return "RespMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", alaiStr=" + alaiStr +
                '}';
    }
}
