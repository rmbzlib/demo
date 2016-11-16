package com.qufenqi.controller;/**
 * Created by zhangyang on 19/8/2016.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试
 *
 * @author zhangyang
 * @create 2016-08-19
 */
@Controller
public class TestController {
    //@Autowired
    //private TestService testService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getData(){

        return null;
    }


}
