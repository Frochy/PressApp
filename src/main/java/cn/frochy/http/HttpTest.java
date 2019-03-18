package cn.frochy.http;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class HttpTest {

    private static final String url = "http://10.250.6.86:8080/MessageTransferWebAppTL/servlet/messageTransferServiceServletByXml";
    private final static Logger logger = LoggerFactory.getLogger(HttpTest.class);

    public HttpTest(int threadNum) {
        for (int i = 0; i < threadNum; i++)
            new WorkerThread("Thread==>" + i).start();
    }

    public static void main(String[] args) {

        Long start = System.currentTimeMillis();
        if (args.length != 1) {
            logger.error("Usage: input the threadNum!");
            return;
        }
        new HttpTest(Integer.parseInt(args[0]));

        logger.info("程序总耗时：" + (System.currentTimeMillis()-start)/1000);
    }

    private class WorkerThread extends Thread {

        WorkerThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int seq = 0;
            while (true) {
                seq++;
                if (seq > 10000) {
                    logger.info("Message sent succeed!");
                    break;
                }
                post(seq);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        private void post(int seq) {
            Map<String, String> param = new HashMap<>();
            param.put("userName", "devtest");
            param.put("passWord", "888888");
            param.put("cmd", "sendMessage");
            param.put("mobilePhone", "13388703783");
            param.put("body", "test messages to you!");
            logger.info(Thread.currentThread().getName() + "==>" + "sent " + seq + "Result: \n" + HttpRequest.post(url, param));
        }
    }
}
