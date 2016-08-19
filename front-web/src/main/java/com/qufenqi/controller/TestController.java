package com.qufenqi.controller;/**
 * Created by zhangyang on 19/8/2016.
 */

import com.qufenqi.controller.vo.TestVO;
import com.qufenqi.edu.dao.po.Test;
import com.qufenqi.service.TestService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 *
 * @author zhangyang
 * @create 2016-08-19
 */
@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public List<TestVO> getData(){
        List<Test> testList=testService.getAll();
        if (CollectionUtils.isEmpty(testList)) return null;

        List<TestVO> testVOs=new ArrayList<TestVO>();
        for (Test test:testList){
            testVOs.add(new TestVO(test.getId(),test.getName()));
        }
        return testVOs;
    }
}
