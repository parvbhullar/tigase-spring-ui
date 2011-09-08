/*
 * Copyright 2011 mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.controller;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.Constants;
import com.mitian.airad.ErrorMessages;
import com.mitian.airad.model.CoreAdvertiser;
import com.mitian.airad.model.CoreAdvertiserImg;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.service.AdvertiserService;
import com.mitian.airad.utils.IdCardVerificationUtils;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.AdvertiserForm;

/**
 * AdvertiserController.java
 * 
 * @author leifenghai
 */
@Controller
@RequestMapping("/advertiser.do")
@RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_ADVERTISERS, RoleFactory.ROLE_DEVELOPERS,
        RoleFactory.ROLE_GENERAL})
public class AdvertiserController extends AbstractController {
    @Autowired
    private AdvertiserService advertiserService;

    private static Logger logger = Logger.getLogger(AdvertiserController.class);

    /**
     * 广告主认证页面
     * 
     * @param form
     * @return
     * @throws ParseException
     */
    @RequestMapping(params = "action=authenticatePage")
    public ModelAndView authenticatePage() throws ParseException {
        ModelAndView mv = new ModelAndView("member/advertisers_aut");
        return mv;
    }

    /**
     * 广告主认证
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=authenticate")
    public ModelAndView authenticate(AdvertiserForm form, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("member/advertisers_aut", Constants.DEFAULT_COMMAND, form);
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();

        String message = IdCardVerificationUtils.checkIdCard(form.getCoreAdvertiser().getCardId());
        if (null != message) {
            form.getErrors().put("idCardErr", message);
            return mv;
        }

        CoreAdvertiserImg coreAdvertiserImg = new CoreAdvertiserImg();
        // 得到上传图片的内容等
        byte[] bytes = form.getPhoto().getBytes();
        if (bytes != null && bytes.length > 0) {

            // 上传图片时，ie会把 jpg、jpeg翻译成image/pjpeg，png翻译成image/x-png
            String fileType = form.getPhoto().getContentType();
            int fileSize = (int) form.getPhoto().getSize();
            if (null == Constants.map.get(fileType)) {
                mv.addObject("advertiserMessage", "imgTypeErr");
                form.getErrors().put("uploadImgSize", ErrorMessages.UPLOAD_IMG_TYPE_ERR);
                return mv;
            }
            if (fileSize > 1024000) {
                mv.addObject("advertiserMessage", "imgSizeErr");
                form.getErrors().put("uploadImgSize", ErrorMessages.UPLOAD_IMG_SIZE_ERR);
                return mv;
            }
            coreAdvertiserImg.setImgContent(bytes);
            coreAdvertiserImg.setImgFormat(fileType);
            coreAdvertiserImg.setImgSize(fileSize);
        }

        try {
            // 插数据之前要先看看是否已经有此用户的验证信息了
            // int count = advertiserImgService.selectAuthentication(pmap);

            coreAdvertiserImg.setAddTime(new Date());// 认证时间
            // if (count <= 0) {// 插入
            coreAdvertiserImg.setMemberId(memberId.intValue());// 用户id
            coreAdvertiserImg.setApproveNum(1);// 填写次数

            // 往数据库插入数据
            CoreAdvertiser coreAdvertiser = new CoreAdvertiser();

            coreAdvertiser.setMemberId(memberId);
            coreAdvertiser.setCardId(form.getCoreAdvertiser().getCardId());
            coreAdvertiser.setAddOper(getLogonEmail());
            advertiserService.txInsertAdvertiser(coreAdvertiser, coreAdvertiserImg);

            // 把当前用户认证成广告主
            advertiserService.txAdvertiserCertification(memberId, memberInfo.getRole());
        }
        catch (Exception e) {
            logger.error("authenticate error", e);
            mv.addObject("advertiserMessage", "no");
            return mv;
        }
        // 重新跳到个人中心页面
        mv = new ModelAndView("redirect:/personal.do?action=advlist");
        request.getSession().setAttribute("advertiserMessage", "ok");
        return mv;
    }
}
