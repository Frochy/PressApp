package cn.frochy.http;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpTest {

    private static final String url = "http://10.250.6.86:8080/MessageTransferWebAppTL/servlet/messageTransferServiceServletByXml";
    //private final String url = "http://47.106.95.55:8000/api/getuser";
    private final static Logger logger = LoggerFactory.getLogger(HttpTest.class);
    private final static ExecutorService cachedThread = Executors.newCachedThreadPool();

    public HttpTest(int threadNum) {
        for (int i = 0; i < threadNum; i++)
            new WorkerThread("Thread==>" + i).start();
//            cachedThread.execute(new Runnable() {
//                @Override
//                public void run() {
//                    int seq = 0;
//                    while (true) {
//                        seq++;
//                        if (seq > 5000) {
//                            logger.info("Message sent succeed!");
//                            break;
//                        }
//                        try {
//                            post(seq);
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            Thread.sleep(50);
//                        } catch (InterruptedException e) {
//                            logger.error(e.getMessage());
//                        }
//                    }
//                }
//            });
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            logger.error("Usage: input the threadNum!");
            return;
        }
        new HttpTest(Integer.parseInt(args[0]));
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
                try {
                    post(seq);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        private synchronized void post(int seq) throws UnsupportedEncodingException {
            Map<String, String> param = new HashMap<>();
            param.put("userName", "devtest");
            param.put("passWord", "888888");
            param.put("cmd", "sendMessage");
            param.put("mobilePhone", "13388703783");
            //
            param.put("body", new String("【通联金融】您好，通道成功率测试中！".getBytes(), StandardCharsets.ISO_8859_1));
            param.put("id", "6");
            logger.info(Thread.currentThread().getName() + "==>" + "sent " + seq + "Result: \n" + HttpRequest.post(url, param));
        }
    }
}
