package cn.frochy.xmlparse;

import cn.frochy.entity.SendMsgRes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class SendMsgResParse {

    private static final String BODY = "body";
    private static final String RESULTCODE = "resultCode";
    private static final String ERRORCODE = "errorCode";


    public static List<SendMsgRes> parseXmlByDoc(String str) {
        if (str == null || "".equals(str)) {
            System.out.println("str is null or ''");
            return null;
        }
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        List<SendMsgRes> msgRess = new ArrayList<>();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new ByteArrayInputStream(str.getBytes()));
            Element rootElement = document.getDocumentElement();
            NodeList msgResNodeList = rootElement.getElementsByTagName(BODY);
            for (int i = 0; i < msgResNodeList.getLength(); i++) {
                Element appElement = (Element) msgResNodeList.item(i);
                NodeList msgResInfo = appElement.getChildNodes();
                SendMsgRes msgRes = new SendMsgRes();
                for (int j = 0; j < msgResInfo.getLength(); j++) {
                    Element element = (Element) msgResInfo.item(j);
                    String appAttr = element.getTagName();
                    switch (appAttr) {
                        case RESULTCODE:
                            msgRes.setResultCode(element.getTextContent());
                            break;
                        case ERRORCODE:
                            msgRes.setErrorCode(element.getTextContent());
                            break;
                        default:
                            break;
                    }
                }
                msgRess.add(msgRes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return msgRess;
    }
}
