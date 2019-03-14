package cn.frochy.http;


import java.util.HashMap;
import java.util.Map;

public class HttpTest {

    private static final String url = "http://10.250.6.86:8080/MessageTransferWebAppTL/servlet/messageTransferServiceServletByXml";

    public HttpTest(int threadNum) {
        for (int i = 0;i < threadNum;i++)
            new WorkerThread("Thread==>"+i).start();
    }

    public static void main(String[] args) {

    }

    private class WorkerThread extends Thread{

        public WorkerThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int seq = 0;
            while (true){
                seq++;
                post(seq);
            }
        }


        private void post(int seq){
            Map<String, String> param = new HashMap<>();
            param.put("userName", "devtest");
            param.put("passWord", "888888");
            param.put("cmd", "sendMessage");
            param.put("mobilePhone", "17621536313");
            param.put("body", "你好");

            System.out.println(Thread.currentThread().getName()+"==>"+HttpRequest.post(url,param));
        }
    }
}
