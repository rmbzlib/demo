package com.qufenqi.builder;


import com.alipay.api.domain.StudentInfo;
import com.alipay.api.response.AlipayCommerceEducateStudentinfoShareResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;
import com.qufenqi.dto.ApiResult;
import com.qufenqi.enums.EAlipay;
import com.qufenqi.vo.AlipayStudentInfoVo;

import java.util.Calendar;
import java.util.List;

/**
 * Created by suzunshou on 16/8/20.
 */
public class AlipayVoBuilder {

    /**
     * 返回学生信息
     *
     * @param alipayUserUserinfoShareResponse
     * @param alipayCommerceEducateStudentinfoShareResponse
     * @return
     */
    public static ApiResult buildAlipayStidentInfoVo(AlipayUserUserinfoShareResponse alipayUserUserinfoShareResponse, AlipayCommerceEducateStudentinfoShareResponse alipayCommerceEducateStudentinfoShareResponse) {
        AlipayStudentInfoVo alipayStudentInfoVo = new AlipayStudentInfoVo();

        //是否实名认证
        if (alipayUserUserinfoShareResponse.getIsCertified().equals("F")) {
            return ApiResult.build(EAlipay.NOT_CERTIFIED.getCode(), EAlipay.NOT_CERTIFIED.getMsg());
        }

        //是否身份证认证
        if (alipayUserUserinfoShareResponse.getIsIdAuth().equals("F")) {
            return ApiResult.build(EAlipay.NOT_ID_AUTH.getCode(), EAlipay.NOT_ID_AUTH.getMsg());
        }

        //是否是学生
        if (alipayUserUserinfoShareResponse.getIsStudentCertified().equals("F")) {
            return ApiResult.build(EAlipay.NOT_STUDENT_CERTIFIED.getCode(), EAlipay.NOT_STUDENT_CERTIFIED.getMsg());
        } else {

            List<StudentInfo> studentInfos = alipayCommerceEducateStudentinfoShareResponse.
                    getStudentInfoShareResult().getStudentInfos();

            if (studentInfos.size() == 0) {
                return ApiResult.build(EAlipay.NOT_NULL.getCode(), EAlipay.NOT_NULL.getMsg());
            }

            //获取user_id
            alipayStudentInfoVo.setUser_id(alipayUserUserinfoShareResponse.getUserId());

            //获取alipay uid
            alipayStudentInfoVo.setAlipay_user_id(alipayUserUserinfoShareResponse.getAlipayUserId());

            //获取真实姓名
            alipayStudentInfoVo.setReal_name(alipayUserUserinfoShareResponse.getRealName());

            StudentInfo studentInfo = studentInfos.get(0);

            //获取学校名称
            alipayStudentInfoVo.setCollegeName(studentInfo.getCollegeName());

            //获取学历
            alipayStudentInfoVo.setDegree(studentInfo.getDegree());

            //获取学院
            alipayStudentInfoVo.setDepartments(studentInfo.getDepartments());

            //获取入学时间
            alipayStudentInfoVo.setGmtEnrollment(studentInfo.getGmtEnrollment());

            //获取毕业时间
            alipayStudentInfoVo.setGmtGraduation(studentInfo.getGmtGraduation());

            //如果当前时间在毕业时间之后,虽然学籍还是学生,但已经毕业了,不能算是学生
            if (Calendar.getInstance().after(studentInfo.getGmtGraduation())) {
                return ApiResult.build(EAlipay.GRATUATED.getCode(), EAlipay.GRATUATED.getMsg());
            }

            return ApiResult.build(EAlipay.SUCCESS.getCode(), EAlipay.SUCCESS.getMsg(), alipayStudentInfoVo);
        }
    }
}
