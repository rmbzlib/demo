package com.qufenqi.service;/**
 * Created by zhangyang on 19/8/2016.
 */

import com.qufenqi.AbstractTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * 测试类
 *
 * @author zhangyang
 * @create 2016-08-19
 */
public class TestServiceTest extends AbstractTestBase{
    @Autowired
    private TestService testService;

    @Test
    public void testGet(){
        com.qufenqi.edu.dao.po.Test test=testService.get("1");
        System.out.println(test.getName());
    }
}
