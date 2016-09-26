package com.zte.ums.watchdog.common.sms;

/**
 * Created by root on 2016/9/21.
 */

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MobileMessageSend {
    private static final String SERVER_URL = "https://api.netease.im/sms/sendtemplate.action";
    private static final String APP_KEY = "f1e738aa63dbab10fa322f7aa1a0dfa8";
    private static final String APP_SECRET = "f31e676f6c16";
//    private static final String MOULD_ID = "3033150";
    private static final String MOULD_ID = "3029179";
    private static final String NONCE = "987654321";

    public static int sendMsg(String phone, String msg) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpHost proxy = new HttpHost("proxysh.zte.com.cn",80,"http");
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        HttpPost post = new HttpPost(SERVER_URL);

        String curTime = String.valueOf((new Date().getTime() / 1000L));
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);

        post.addHeader("AppKey", APP_KEY);
        post.addHeader("Nonce", NONCE);
        post.addHeader("CurTime", curTime);
        post.addHeader("CheckSum", checkSum);
        post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        post.setConfig(config);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("templateid", MOULD_ID));
        nameValuePairs.add(new BasicNameValuePair("mobiles", "['" + phone + "']"));
        nameValuePairs.add(new BasicNameValuePair("params", "['" + msg + "']"));

        post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
//        // 执行请求
        HttpResponse response = httpclient.execute(post);
        String responseEntity = EntityUtils.toString(response.getEntity(), "utf-8");

        System.out.println(responseEntity);
        String code = JSON.parseObject(responseEntity).getString("code");
        if (code.equals("200")) {
            return 0;
        }

        return 500;
    }
}
