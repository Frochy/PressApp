package cn.frochy.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;


public final class HttpRequest {

    // ;charset=utf-8 必须要，不然会出现乱码

    private final static Logger logger = LoggerFactory.getLogger(HttpTest.class);


    public static String CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded;charset=utf-8";


    public static String CONTENT_TYPE_FORM_DATA = "multipart/form-data;charset=utf-8";

    /**
     * text/plain;charset=utf-8
     */
    public static String CONTENT_TYPE_PLAIN = "text/plain;charset=utf-8";
    /**
     * application/json;charset=utf-8
     */
    public static String CONTENT_TYPE_JSON = "application/json;charset=utf-8";


    public static String post(String URL, Map<String, String> params) {
        StringBuilder param = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            for (Entry<String, String> entry : params.entrySet()) {
                param.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return post(URL, param.toString());
    }


    public static String post(String URL, String parm) {

        HttpURLConnection conn = null;
        DataOutputStream dataOut = null;
        BufferedReader dataIn = null;

        try {
            URL url = new URL(URL);

            conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);

            conn.setRequestProperty("Content-Type", HttpRequest.CONTENT_TYPE_FORM_URLENCODED);
            conn.setRequestProperty("accept", "*/*");
            conn.connect();

            dataOut = new DataOutputStream(conn.getOutputStream());
            dataOut.writeBytes(parm != null ? parm : "");
            dataOut.flush();

            outConnInfo(conn, url);

            dataIn = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = dataIn.readLine()) != null) {
                sb.append(line).append(System.getProperty("line.separator"));
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataOut != null) {
                    dataOut.close();
                }
                if (dataIn != null) {
                    dataIn.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static String get(String URL) {

        HttpURLConnection conn = null;
        BufferedReader dataIn = null;

        try {
            URL url = new URL(URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            dataIn = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = dataIn.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (dataIn != null) {
                    dataIn.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 输出连接信息
     */
    private static synchronized void outConnInfo(HttpURLConnection conn, URL url) throws IOException {
        // url与url = conn.getURL()是等价的
        logger.info("conn.getResponseCode():" + conn.getResponseCode());
        logger.info("conn.getURL():" + conn.getURL());
        logger.info("conn.getRequestMethod():" + conn.getRequestMethod());
//        logger.info("conn.getContentType():" + conn.getContentType());
        logger.info("conn.getReadTimeout():" + conn.getReadTimeout());
//        logger.info("conn.getResponseMessage():" + conn.getResponseMessage());
        logger.info("url.getDefaultPort():" + url.getDefaultPort());
        logger.info("url.getFile():" + url.getFile());
        logger.info("url.getHost():" + url.getHost());
        logger.info("url.getPath():" + url.getPath());
        logger.info("url.getPort():" + url.getPort());
//        logger.info("url.getProtocol():" + url.getProtocol());
//        logger.info("url.getQuery():" + url.getQuery());
//        logger.info("url.getRef():" + url.getRef());
        logger.info("url.getUserInfo():" + url.getUserInfo());
    }
}