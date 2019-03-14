package cn.frochy.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;



public final class HttpRequest {

    // ;charset=utf-8 必须要，不然会出现乱码


    public static String CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded;charset=utf-8";


    public static String CONTENT_TYPE_FORM_DATA = "multipart/form-data;charset=utf-8";

    /** text/plain;charset=utf-8 */
    public static String CONTENT_TYPE_PLAIN = "text/plain;charset=utf-8";
    /** application/json;charset=utf-8 */
    public static String CONTENT_TYPE_JSON = "application/json;charset=utf-8";


    public static String post(String URL, Map<String, String> params) {
        StringBuilder parm = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            for (Entry<String, String> entry : params.entrySet()) {
                parm.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return post(URL, parm.toString());
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
            e.printStackTrace();
        } finally {
            try {
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

    /** 输出连接信息 */
    private static void outConnInfo(HttpURLConnection conn, URL url) throws IOException {
        // url与url = conn.getURL()是等价的
        System.out.println("conn.getResponseCode():" + conn.getResponseCode());
        System.out.println("conn.getURL():" + conn.getURL());
        System.out.println("conn.getRequestMethod():" + conn.getRequestMethod());
        System.out.println("conn.getContentType():" + conn.getContentType());
        System.out.println("conn.getReadTimeout():" + conn.getReadTimeout());
        System.out.println("conn.getResponseMessage():" + conn.getResponseMessage());
        System.out.println("url.getDefaultPort():" + url.getDefaultPort());
        System.out.println("url.getFile():" + url.getFile());
        System.out.println("url.getHost():" + url.getHost());
        System.out.println("url.getPath():" + url.getPath());
        System.out.println("url.getPort():" + url.getPort());
        System.out.println("url.getProtocol():" + url.getProtocol());
        System.out.println("url.getQuery():" + url.getQuery());
        System.out.println("url.getRef():" + url.getRef());
        System.out.println("url.getUserInfo():" + url.getUserInfo());
    }
}