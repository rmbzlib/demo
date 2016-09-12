package com.qufenqi.test;

import com.qufenqi.AbstractTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static com.qufenqi.test.BaseRequest.param;
import static com.qufenqi.test.BaseRequest.url;

/**
 * Created by liuxin on 16/9/5.
 */
public class TestResTtemplant extends AbstractTestBase {

    @Autowired
    RestTemplate restTemplate;


    @Test
    public void test(){
        long start = System.currentTimeMillis();
        System.out.println("**********ResTtemplant开始: " + start + "**********");
        int n = 100;
        for(int i = 0; i< n; i++){
            testRest();
        }
        long end = System.currentTimeMillis();
        System.out.println("**********ResTtemplant结束 "+ end +"**********");
        System.out.println(n + "次用时: " + (end - start) + "ms");
    }


    public void testRest(){
        long start = System.currentTimeMillis();
        HttpHeaders headers =new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request=new HttpEntity(param, headers);
        String result = restTemplate.postForObject(url, request, String.class);
        long end = System.currentTimeMillis();

        System.out.println("result: " + result);
        System.out.println("用时: " + (end - start));
    }


}













