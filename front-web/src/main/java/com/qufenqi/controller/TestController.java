package com.qufenqi.controller;/**
 * Created by zhangyang on 19/8/2016.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试
 *
 * @author zhangyang
 * @create 2016-08-19
 */
@Controller
public class TestController implements Ordered{
    public static Logger log = LoggerFactory.getLogger(TestController.class);
    //@Autowired
    //private TestService testService;

    //@MonitorLog



    @ResponseBody
    @RequestMapping(value = "/index")
    public String getData(){
        return "success";
    }





    @Override
    public int getOrder() {
        return 0;
    }
}
