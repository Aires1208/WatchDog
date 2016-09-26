package com.zte.ums.watchdog.common.utils;

import com.zte.ums.watchdog.common.constant.ErrorCodeConstant;
import com.zte.ums.watchdog.common.exception.WatchDogException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

/**
 * Created by root on 2016/9/21.
 */
public class HttpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);
    private static final String SERVLET_POST = "POST";
    private static final String SERVLET_GET = "GET";
    private static final String SERVLET_DELETE = "DELETE";
    private static final String SERVLET_PUT = "PUT";

    private HttpUtils() {
    }

    private static String prepareParam(Map<String, Object> paramMap) {
        StringBuilder sb = new StringBuilder();
        if (null == paramMap || paramMap.isEmpty()) {
            return "";
        } else {
            for (String key : paramMap.keySet()) {
                String value = (String) paramMap.get(key);
                if (sb.length() < 1) {
                    sb.append(key).append("=").append(value);
                } else {
                    sb.append("&").append(key).append("=").append(value);
                }
            }
            return sb.toString();
        }
    }

    public static String get(String ip, int port, String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // specify the host, protocol, and port
            HttpHost target = new HttpHost(ip, port, "http");
            // specify the get request
            HttpGet getRequest = new HttpGet(url);
            getRequest.setHeader("Content-Type", "application/json; charset=utf-8");
            HttpResponse httpResponse = httpclient.execute(target, getRequest);
            HttpEntity entity = httpResponse.getEntity();
            LOGGER.info("entity contenttype =" + entity.getContentType());
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            throw new WatchDogException(ErrorCodeConstant.TARGET_URL_CANNOT_CONNECT, e);
        } finally {
            if (httpclient != null)
                try {
                    httpclient.close();
                } catch (IOException e) {
                    throw new WatchDogException(ErrorCodeConstant.CLOSE_HTTP_CLIENT_ERROR, e);
                }
        }
        return null;
    }

    public static boolean delete(String ip, int port, String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpHost target = new HttpHost(ip, port, "http");
        HttpDelete deleteRequest = new HttpDelete(url);
        try {
            HttpResponse httpResponse = httpclient.execute(target, deleteRequest);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            LOGGER.info("delete statusCode = " + statusCode);
            return true;
        } catch (IOException e) {
            LOGGER.error("send delete rest request error", e);
        } finally {
            if (null != httpclient) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    LOGGER.error("close http client error", e);
                }
            }
        }
        return false;
    }

    public static boolean post(String ip, int port, String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpHost target = new HttpHost(ip, port, "http");
        HttpPost postRequest = new HttpPost(url);
        try {
            HttpResponse httpResponse = httpclient.execute(target, postRequest);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            LOGGER.info("post statusCode = " + statusCode);
            return true;
        } catch (IOException e) {
            LOGGER.error("send post rest request error", e);
        } finally {
            if (null != httpclient) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    LOGGER.error("close http client error", e);
                }
            }
        }
        return false;
    }

    public static void doPost(String urlStr, String jsonString) {
        URL url = null;
        BufferedReader br = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(SERVLET_POST);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(jsonString.getBytes("utf-8"));
            os.close();

            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String result = "";
            while ((line = br.readLine()) != null) {
                result += "/n" + line;
            }
            LOGGER.info(result);
        } catch (MalformedURLException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ProtocolException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

    }

    public static String doGet(String urlStr, Map<String, Object> paramMap) {
        String paramStr = prepareParam(paramMap);
        if (paramStr == null || paramStr.trim().length() < 1) {

        } else {
            urlStr += "?" + paramStr;
        }
        LOGGER.info(urlStr);
        URL url = null;
        String result = "";
        BufferedReader br = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(SERVLET_GET);
            conn.setRequestProperty("Content-Type", "text/html; charset=UTF-8");

            conn.connect();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                result += line.trim();
            }
            LOGGER.info(result);
        } catch (MalformedURLException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ProtocolException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }

    public static void doPut(String urlStr, int id, String jsonString) {
        URL url = null;
        BufferedReader br = null;
        try {
            url = new URL(urlStr + "/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(SERVLET_PUT);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(jsonString.getBytes("utf-8"));
            os.close();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            LOGGER.info(result);
        } catch (MalformedURLException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ProtocolException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public static boolean doDelete(String urlStr, int id) {
        urlStr += "/" + id;
        LOGGER.info(urlStr);
        URL url = null;
        HttpURLConnection conn = null;
        int connResponseCode = 0;
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod(SERVLET_DELETE);
            connResponseCode = conn.getResponseCode();
        } catch (MalformedURLException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ProtocolException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        if (connResponseCode == 200) {
            LOGGER.info("success");
            return true;
        } else {
            LOGGER.info(connResponseCode + "");
            return false;
        }
    }

}
