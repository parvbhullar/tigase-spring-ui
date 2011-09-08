/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mitian.airad.common.codec.EncryptService;
import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.service.DeploymentContextService;
import com.mitian.airad.utils.CookieUtils;
import com.mitian.airad.web.controller.AbstractController;
import com.mitian.boss.web.httpinvoker.ValidateServer;

/**
 * OssLogonController.java
 * 
 * @author baojun
 */
@Controller
public class OssLogonController extends AbstractController {

    /**
     * 通过httpinvoke向boss项目进行验证 0-失败 1-成功
     */
    private final static String VALID_FAILE = "0";
    private final static String VALID_SUCCES = "1";

    @Autowired
    private DeploymentContextService deploymentContextService;

    @Autowired
    private ValidateServer validateServer;

    @Autowired
    private EncryptService encryptService;

    @RequestMapping(value = "/oss/dev")
    public String ossLogon4Dev(HttpServletRequest request, HttpServletResponse response) throws IOException,
            InvalidInfoException {
        HttpSession session = request.getSession();
        session.setAttribute("salesId", "121");
        session.setAttribute("memberId", "585");
        session.setAttribute("salesemail", "sales@airad.com");
        return "oss/logon";
    }

    @RequestMapping(value = "/oss/logon")
    public String ossLogon(HttpServletRequest request, HttpServletResponse response) throws IOException,
            InvalidInfoException {
        String validResult = null;
        String decodeSalesId = null;
        String decodeMemberId = null;
        if (deploymentContextService.isOSSDeployType()) {
            String salesId = request.getParameter("salerId");
            String memberId = request.getParameter("memberId");
            if (StringUtils.isNotBlank(salesId) && StringUtils.isNotBlank(memberId)) {
                decodeSalesId =
                        encryptService.decrypt(salesId, EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.name());
                decodeMemberId =
                        encryptService.decrypt(memberId, EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.name());
                if (NumberUtils.isNumber(decodeSalesId) && NumberUtils.isNumber(decodeMemberId)) {
                    validResult = validateServer.validate(decodeSalesId, decodeMemberId);
                }
            }
            if (VALID_SUCCES.equals(validResult)) {
                HttpSession session = request.getSession();
                // salesId,memberId
                session.setAttribute("salesId", decodeSalesId);
                session.setAttribute("memberId", decodeMemberId);
                session.setAttribute("salesemail", "sales@airad.com");

                // 解决第一次点击店铺主登录失败bug
                long curTime = System.currentTimeMillis();
                String encryptedCurTime =
                        encryptService.encrypt("" + curTime, EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.name());
                CookieUtils.setCookieAfterOssLogon(response, salesId, memberId, encryptedCurTime);

                return "oss/logon";
            }
            else {
                logger.error("oss logon faile with salesId: " + decodeSalesId + "  membrId :" + decodeMemberId);
                return "oss/error";
            }
        }
        else {
            throw new InvalidInfoException("airad can not support oss logon");
        }
    }

    @RequestMapping(value = "/oss/logout")
    public void ossLogoutOut(HttpServletRequest request, HttpServletResponse response) throws IOException,
            InvalidInfoException {
        if (deploymentContextService.isOSSDeployType()) {
            HttpSession session = request.getSession();
            session.removeAttribute("salesId");
            session.removeAttribute("memberId");
            session.removeAttribute("salesemail");
            CookieUtils.removeCookie(response, CookieUtils.COOKIE_KEY_OSS_SALES_ID);
            CookieUtils.removeCookie(response, CookieUtils.COOKIE_KEY_OSS_MEMBER_ID);
            CookieUtils.removeCookie(response, CookieUtils.COOKIE_KEY_OSS_EMAIL);
            response.getWriter().print("logout done");
        }
        else {
            throw new InvalidInfoException("airad can not support oss logon");
        }
    }
}
