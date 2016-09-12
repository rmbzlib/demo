package com.qufenqi.test;

import com.qufenqi.AbstractTestBase;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import java.io.IOException;

import static com.qufenqi.test.BaseRequest.url;

/**
 * Created by liuxin on 16/9/2.
 */
public class TestCommonHttpClient extends AbstractTestBase {

    @Test
    public void test(){
        long start = System.currentTimeMillis();
        System.out.println("**********apache.common开始: " + start + "**********");
        int n = 100;
        HttpClient httpClient = new HttpClient();
        for(int i = 0; i< n; i++){
            testCommons(httpClient);
        }
        long end = System.currentTimeMillis();
        System.out.println("**********apache.common结束 "+ end +"**********");
        System.out.println(n + "次用时: " + (end - start) + "ms");
    }

    public  void testCommons(HttpClient httpClient){
        long start = System.currentTimeMillis();
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("Connection", "close");
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        String response = null;
        try {
            int i = httpClient.executeMethod(postMethod);
            response = postMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("result: " + response);
        System.out.println("用时: " + (end - start));    }


}
