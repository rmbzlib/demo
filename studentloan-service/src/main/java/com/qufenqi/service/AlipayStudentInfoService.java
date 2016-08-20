package com.qufenqi.service;


import com.qufenqi.dto.ApiResult;

/**
 * Created by suzunshou on 16/8/20.
 */
public interface AlipayStudentInfoService {
    public String getAccessToken(String auth_code);

    public ApiResult getStudentInfoByAccessToken(String access_token);

    public ApiResult getStudentInfoByAuthCode(String auth_code);
}
