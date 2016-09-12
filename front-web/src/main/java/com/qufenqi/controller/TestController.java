package com.qufenqi.controller;/**
 * Created by zhangyang on 19/8/2016.
 */

import com.qufenqi.controller.MyAnnotation.MonitorLog;
import com.qufenqi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试
 *
 * @author zhangyang
 * @create 2016-08-19
 */
@Controller
public class TestController implements Ordered{
    @Autowired
    private TestService testService;

    @MonitorLog
    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getData(int i){

        return "10JQKA";
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
