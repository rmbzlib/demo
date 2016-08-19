package com.qufenqi.edu.dao.mapper;


import com.qufenqi.edu.dao.po.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangyang on 19/8/2016.
 */
@Repository
public interface TestMapper {

    Test selectByPk(String id);

    List<Test> selectAll();
}
