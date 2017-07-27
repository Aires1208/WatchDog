package com.zte.ums.watchdog.common.sms;

/**
 * Created by  on 2016/9/21.
 */

import java.security.MessageDigest;

public class Test {

    public static void main(String args[]) {
        System.out.println("Test");
//        String url = "https://api.netease.im/sms/sendcode.action"; // ��ȡ��֤��Ĺ̶�����·��
//        String appKey = "f1e738aa63dbab10fa322f7aa1a0dfa8";    //    Ӧ�õ� key
//        String appSecret = "f31e676f6c16";    // Ӧ�õ� ����
//        String nonce = "123456";    //    �����
//        String mobile = "13918964136";    //    Ŀ���ֻ����
//        String curTime = String.valueOf((new Date().getTime() / 1000L));
//        String checkSum = getCheckSum(appSecret, nonce, curTime);    //    �õ� ������֤�����Ĳ���checkSum
//
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // ������浽����ͷ��
//
//        HttpHost proxy = new HttpHost("proxysh.zte.com.cn",80,"http");
//        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
//
//        HttpPost httpPost = new HttpPost(url);
//        httpPost.setConfig(config);
//        httpPost.addHeader("AppKey", appKey);
//        httpPost.addHeader("Nonce", nonce);
//        httpPost.addHeader("CurTime", curTime);
//        httpPost.addHeader("CheckSum", checkSum);
//        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//
//
//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        formparams.add(new BasicNameValuePair("mobile", mobile));
//        UrlEncodedFormEntity uefEntity;
//        try {
//            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
//            httpPost.setEntity(uefEntity);
//            System.out.println("executing request " + httpPost.getURI());
//            CloseableHttpResponse response = httpclient.execute(httpPost);
//            try {
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
//                }
//            } finally {
//                response.close();
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // �ر�����,�ͷ���Դ
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    // ���㲢��ȡCheckSum
    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }

    // ���㲢��ȡmd5ֵ
    public static String getMD5(String requestBody) {
        return encode("md5", requestBody);
    }

    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest
                    = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
}
