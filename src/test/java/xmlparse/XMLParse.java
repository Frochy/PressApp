package xmlparse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLParse {
//    //DocumentBuilderFactor解析XML流程
//    //得到创建DOM解析器的工厂
//    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//    //得到DOM解析器对象
//    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//    /***
//     * DOM解析器解析输入流，
//     * DOM解析器对象的parse（）方法解析XML文档
//     * 得到代表整个文档的Document对象
//     */
//    InputStream is = new FileInputStream("xxx.xml");
//    documentBuilder.parse(is);
//    documentBuilder.parse(new ByteArrayInputStream(str.getBytes()));
//    //得到XML文档的根节点
//    Element rootElement = document.getDocumentElement();
//    //得到节点的子节点
//    NodeList studentInfo = appElement.getChildNodes();

    public static String xml = "<Students><student><name><![CDATA[陈喻]]></name><age><![CDATA[26]]></age><sex><![CDATA[男]]></sex></student><student><name><![CDATA[陈彩凤]]></name><age><![CDATA[25]]></age><sex><![CDATA[女]]></sex></student><student><name><![CDATA[陈紫宣]]></name><age><![CDATA[2]]></age><sex><![CDATA[女]]></sex></student><student><name><![CDATA[陈紫曦]]></name><age><![CDATA[7个月]]></age><sex><![CDATA[女]]></sex></student></Students>";
    public static final String STUDENT = "student";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String SEX = "sex";

    public static void main(String[] args) {
        List<Student> students = parseXmlByDoc(xml);
        if (students != null && students.size() > 0) {
            for (Student student : students)
                System.out.println(student);
            return;
        }
        System.out.println("students size is 0");
    }

    private static List<Student> parseXmlByDoc(String str) {
        if (str == null || "".equals(str)) {
            System.out.println("str is null or ''");
            return null;
        }
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        List<Student> students = new ArrayList<>();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new ByteArrayInputStream(str.getBytes()));
            Element rootElement = document.getDocumentElement();
            NodeList studentNodeList = rootElement.getElementsByTagName(STUDENT);
            for (int i = 0; i < studentNodeList.getLength(); i++) {
                Element appElement = (Element) studentNodeList.item(i);
                NodeList studentInfo = appElement.getChildNodes();
                Student student = new Student();
                for (int j = 0; j < studentInfo.getLength(); j++) {
                    Element element = (Element) studentInfo.item(j);
                    String appAttr = element.getTagName();
                    switch (appAttr) {
                        case NAME:
                            student.setName(element.getTextContent());
                            break;
                        case AGE:
                            student.setAge(element.getTextContent());
                            break;
                        case SEX:
                            student.setSex(element.getTextContent());
                            break;
                        default:
                            break;
                    }
                }
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return students;
    }

}
