package com.qufenqi.service;/**
 * Created by zhangyang on 19/8/2016.
 */

import com.qufenqi.edu.dao.po.Test;

import java.util.List;

/**
 * 测试service
 *
 * @author zhangyang
 * @create 2016-08-19
 */
public interface TestService {
    /**
     *
     * @param id
     * @return
     */
    Test get(String id);

    /**
     *
     * @return
     */
    List<Test> getAll();

}
