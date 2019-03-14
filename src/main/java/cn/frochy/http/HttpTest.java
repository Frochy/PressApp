package cn.frochy.http;


import java.util.HashMap;
import java.util.Map;

public class HttpTest {

    private static final String url = "http://10.250.6.86:8080/MessageTransferWebAppTL/servlet/messageTransferServiceServletByXml";

    public static void main(String[] args) {
        Map<String, String> param = new HashMap<>();
        param.put("userName", "devtest");
        param.put("passWord", "888888");
        param.put("cmd", "sendMessage");
        param.put("mobilePhone", "17621536313");
        param.put("body", "你好");

        System.out.println(HttpRequest.post(url,param));
    }
}
