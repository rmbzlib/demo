package com.qufenqi.service;/**
 * Created by zhangyang on 19/8/2016.
 */

import com.qufenqi.common.service.CacheManagerService;
import com.qufenqi.common.service.RedisService;
import com.qufenqi.edu.dao.mapper.TestMapper;
import com.qufenqi.edu.dao.po.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试serviceimpl
 *
 * @author zhangyang
 * @create 2016-08-19
 */
@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private CacheManagerService cacheManagerService;

    @Autowired
    private TestMapper testMapper;

    @Override
    public Test get(String id) {
        Test t=null;
        Object obj = cacheManagerService.getObject(id);
        if (obj==null){
            t=testMapper.selectByPk(id);
            cacheManagerService.setObject(id,obj);
        }else
           t= (Test) obj;
        return t;
    }

    @Override
    public List<Test> getAll() {
        return testMapper.selectAll();
    }
}
