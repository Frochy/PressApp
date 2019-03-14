package cn.frochy.http;

import cn.frochy.entity.SendMsgRes;
import cn.frochy.xmlparse.SendMsgResParse;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpTest {

    private static final String url = "http://10.250.6.86:8080/MessageTransferWebAppTL/servlet/messageTransferServiceServletByXml";

    @Test
    public void demo01() {
        Map<String, String> param = new HashMap<>();
        param.put("userName", "devtest");
        param.put("passWord", "888888");
        param.put("cmd", "sendMessage");
        param.put("mobilePhone", "17621536313");
        param.put("body", "你好");

        System.out.println(HttpRequest.post(url,param));
//        List<SendMsgRes> msgRess = SendMsgResParse.parseXmlByDoc(HttpRequest.post(url, param));
//        if (msgRess != null && msgRess.size() > 0)
//            for (SendMsgRes msgRes : msgRess)
//                System.out.println(msgRes);
//        else
//            System.out.println("null");
    }
}
