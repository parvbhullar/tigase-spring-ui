/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.Constants;
import com.mitian.airad.JsonConstants;
import com.mitian.airad.common.constant.AccMailNoticeConstant;
import com.mitian.airad.model.AccMailNotice;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.service.AccMailNoticeService;
import com.mitian.airad.utils.CookieUtils;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.BaseRole;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.AccMailNoticeForm;

/**
 * AccMailNoticeController.java
 * 
 * @author Administrator
 */

@Controller
@RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_ADVERTISERS})
@RequestMapping("/accMailNotice.do")
public class AccMailNoticeController extends AbstractController {
    @Autowired
    private AccMailNoticeService accMailNoticeService;

    /**
     *打开通知
     */
    @RequestMapping(params = "action=openNotice")
    public ModelAndView openSetNotice(AccMailNoticeForm form) {
        ModelAndView mv = new ModelAndView("notice/advertiser_notice_setting", Constants.DEFAULT_COMMAND, form);
        AccMailNotice notice = accMailNoticeService.insertOrFindAccMailNotice(getLogonMemberId(), getLogonEmail());
        form.setAccMailNotice(notice);
        //
        form.setMailAddr(notice.getMailAddr());
        form.setAccountBlance(notice.getAccountBlance().toString());
        form.setRemindFlag(notice.getRemindFlag());
        return mv;
    }

    @RequestMapping(params = "action=editRemind")
    public ModelAndView editRemind(AccMailNoticeForm form) {
        ModelAndView mv = new ModelAndView("notice/advertiser_notice_setting_edit", Constants.DEFAULT_COMMAND, form);
        AccMailNotice notice = accMailNoticeService.insertOrFindAccMailNotice(getLogonMemberId(), getLogonEmail());
        form.setAccMailNotice(notice);
        //
        form.setMailAddr(notice.getMailAddr());
        form.setAccountBlance(notice.getAccountBlance().toString());
        form.setRemindFlag(notice.getRemindFlag());
        return mv;
    }

    @RequestMapping(params = "action=updateRemind")
    public ModelAndView updateRemind(AccMailNoticeForm form, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("redirect:/accMailNotice.do?action=openNotice");
        if (!form.getErrors().isEmpty()) {
            return editRemind(form);
        }
        String mail = form.getMailAddr();
        BigDecimal remindAmount = new BigDecimal(form.getAccountBlance());
        Long memberId = getLogonMemberId();
        Date curDate = new Date();
        //
        AccMailNotice notice = accMailNoticeService.findAccMailNoticeByMemberId(memberId);
        if (null == notice) {
            accMailNoticeService.txCreateAccMailNotice(memberId, mail, curDate);
            return mv;
        }
        AccMailNotice an = new AccMailNotice();
        an.setMailNoticeId(notice.getMailNoticeId());
        an.setMemberId(memberId);
        an.setMailAddr(mail);
        an.setUpdateId(memberId);
        an.setUpdateTime(curDate);
        an.setNoticeType(AccMailNoticeConstant.NOTICE_TYPE_ADVERTISER);
        an.setRemindFlag(AccMailNoticeConstant.REMIND_FLAG_ON);
        an.setTriggerType(AccMailNoticeConstant.TRIGGER_TYPE_JOB);
        an.setAccountBlance(remindAmount);
        accMailNoticeService.txUpdateAccMailNotice(an);
        CookieUtils.setTipMessageCookie(request, response, Constants.SETTINGS_SUC);
        form.setAccMailNotice(an);
        return mv;
    }

    /**
     *启用 停用 通知
     */
    @RequestMapping(params = "action=updateRemindFlag")
    public ModelAndView updateRemindFlag(AccMailNotice form) {
        //
        Map<String, String> result = new HashMap<String, String>();
        CoreMemberInfo memberInfo = getMemberInfo();
        BaseRole role = memberInfo.getRole();
        Long memberId = memberInfo.getMemberId();
        String logonEmail = memberInfo.getEmail();
        // 目前只有广告主有通知功能
        AccMailNotice notice = accMailNoticeService.findAccMailNoticeByMemberId(memberId);
        if (null == notice) {
            accMailNoticeService.txCreateAccMailNotice(memberId, logonEmail, new Date());
        }
        else {
            if (notice.getRemindFlag().equals(AccMailNoticeConstant.REMIND_FLAG_OFF)) {
                notice.setRemindFlag(AccMailNoticeConstant.REMIND_FLAG_ON);
            }
            else {
                notice.setRemindFlag(AccMailNoticeConstant.REMIND_FLAG_OFF);
            }
            notice.setUpdateTime(new Date());
            notice.setUpdateId(memberId);
            accMailNoticeService.txUpdateAccMailNoticeRemndFlag(notice);
        }
        result.put(JsonConstants.RETURN_STATUS, JsonConstants.RETURN_STATUS_SUCCESS);
        return new ModelAndView("jsonView", result);
    }

}
