package cn.frochy.http;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpTest {

    @Test
    public void demo01(){
        System.out.println(HttpRequest.post("http://47.106.95.55:8000/api/getuser","id=6"));
    }
}
