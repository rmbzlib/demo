package com.qufenqi.test;

import com.qufenqi.AbstractTestBase;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.qufenqi.test.BaseRequest.param;
import static com.qufenqi.test.BaseRequest.url;

/**
 * Created by liuxin on 16/9/2.
 */
public class TestComponents extends AbstractTestBase {

    @Test
    public void test(){
        long start = System.currentTimeMillis();
        System.out.println("**********apache.http开始: " + start + "**********");
        int n = 100;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        for(int i = 0; i< n; i++){
            testComponents(httpClient);
        }
        long end = System.currentTimeMillis();
        System.out.println("**********apache.http结束 "+ end +"**********");
        System.out.println(n + "次用时: " + (end - start) + "ms");
    }


    public void testComponents(CloseableHttpClient httpClient){
        long start = System.currentTimeMillis();
        HttpPost postMethod = new HttpPost(url);

        postMethod.addHeader("Content-type","application/json; charset=utf-8");
        postMethod.setHeader("Accept", "application/json");
        postMethod.setEntity(new StringEntity(param, Charset.forName("UTF-8")));

        String response = null;
        try {
            CloseableHttpResponse res = httpClient.execute(postMethod);
            HttpEntity entity = res.getEntity();
            response = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("result: " + response);
        System.out.println("用时: " + (end - start));
    }





}
