package com.qufenqi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by suzunshou on 16/8/20.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AlipayStudentInfoVo {
    /**
     * 支付宝用户的唯一userId
     */
    private String user_id;
    /**
     * 支付宝uid
     */
    private String alipay_user_id;
    /**
     * 真实姓名
     */
    private String real_name;
    /**
     * 学校名称
     */
    private String collegeName;
    /**
     * 学院名称
     */
    private String departments;
    /**
     * 学历
     */
    private String degree;
    /**
     * 入学时间
     */
    private Date gmtEnrollment;
    /**
     * 毕业时间
     */
    private Date gmtGraduation;

}
