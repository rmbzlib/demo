package com.qufenqi.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayCommerceEducateStudentinfoShareRequest;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserUserinfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.qufenqi.builder.AlipayVoBuilder;
import com.qufenqi.constant.AlipayConstant;
import com.qufenqi.dto.ApiResult;
import com.qufenqi.enums.EAlipay;
import com.qufenqi.service.AlipayStudentInfoService;
import com.qufenqi.utils.AlipayUtils;

/**
 * Created by suzunshou on 16/8/20.
 */
public class AlipayStudentInfoServiceImpl implements AlipayStudentInfoService {
    private AlipayClient alipayClient = AlipayUtils.initAlipayClient();

    /**
     * 用授权码换取access_token令牌
     *
     * @param auth_code
     * @return
     */
    @Override
    public String getAccessToken(String auth_code) {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(auth_code);
        request.setGrantType(AlipayConstant.GRANT_TYPE);

        try {
            AlipaySystemOauthTokenResponse
                    response = alipayClient.execute(request, auth_code);
            return response.getAccessToken();
        } catch (AlipayApiException e) {
            return null;
        }
    }

    /**
     * 用access_token调用学生数据接口
     *
     * @param access_token
     * @return
     */
    @Override
    public ApiResult getStudentInfoByAccessToken(String access_token) {

        if (StringUtils.isEmpty(access_token)) {
            return ApiResult.build(EAlipay.NOT_NULL.getCode(), EAlipay.NOT_NULL.getMsg());
        }

        AlipayUserUserinfoShareRequest alipayUserUserinfoShareRequest
                = new AlipayUserUserinfoShareRequest();
        AlipayCommerceEducateStudentinfoShareRequest alipayCommerceEducateStudentinfoShareRequest
                = new AlipayCommerceEducateStudentinfoShareRequest();
        try {
            ApiResult apiResult
                    = AlipayVoBuilder.buildAlipayStidentInfoVo(
                    alipayClient.execute(alipayUserUserinfoShareRequest, access_token),
                    alipayClient.execute(alipayCommerceEducateStudentinfoShareRequest, access_token));
            return apiResult;
        } catch (AlipayApiException e) {
            return ApiResult.build(e.getErrCode(), e.getErrMsg());
        }
    }

    /**
     * 直接忽略显式利用auth_code换取access_token过程
     *
     * @param auth_code
     * @return
     */
    @Override
    public ApiResult getStudentInfoByAuthCode(String auth_code) {
        return getStudentInfoByAccessToken(getAccessToken(auth_code));
    }
}
