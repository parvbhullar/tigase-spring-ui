/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.CommonDef;
import com.mitian.airad.Constants;
import com.mitian.airad.ErrorMessages;
import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.model.CoreApp;
import com.mitian.airad.model.Dictionary;
import com.mitian.airad.service.AppService;
import com.mitian.airad.utils.CookieUtils;
import com.mitian.airad.utils.PathUtils;
import com.mitian.airad.utils.StringUtil;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.AppForm;

/**
 * AppController.java
 * 
 * @author baojun
 */
@Controller
@RequestMapping("/app.do")
@RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_DEVELOPERS})
public class AppController extends AbstractController {

    // 注入AppService
    @Autowired
    private AppService appService;
    private final static Logger logger = Logger.getLogger(AppController.class);

    /**
     * 应用活动列表
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=listApp")
    public ModelAndView list(AppForm form, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("app/app_list", Constants.DEFAULT_COMMAND, form);
        // 默认降序
        if (StringUtils.isEmpty(form.getAsce())) {
            form.setDesc("desc");
            mv.addObject("flag", "flag");
        }
        else {
            mv.addObject("", "flag");
        }
        // 查询数据封装
        Map<String, String> params = new HashMap<String, String>();
        if (StringUtils.isEmpty(form.getTimeSlotFlag())) {
            if (StringUtils.isNotEmpty(form.getApp().getAppName())) {
                if (!form.getApp().getAppName().contains("/")) {
                    params.put("escape", CommonDef.escape.ESCAPE);
                }
                String appName = StringUtil.replaceChar(form.getApp().getAppName());
                params.put("appName", appName);
                form.setAppName(form.getApp().getAppName());
            }
        }
        params.put("descFlag", form.getDesc());
        params.put("pageSize", form.getPageSize());
        params.put("memberId", getLogonMemberId().toString());
        int k = 0;
        List<CoreApp> listBean = new ArrayList<CoreApp>();
        if (StringUtils.isEmpty(form.getStartTime()) && StringUtils.isEmpty(form.getEndTime())) {
            k = appService.queryCount(params);
            if (Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1) >= k) {
                params.put("currentPage", "1");
            }
            else {
                params.put("currentPage", form.getCurrentPage());
            }
            listBean = appService.queryList(params);
        }
        else {
            if (StringUtils.isNotEmpty(form.getStartTime()) && StringUtils.isNotEmpty(form.getEndTime())) {
                if (StringUtil.getDateY(form.getStartTime()).compareTo(StringUtil.getDateY(form.getEndTime())) >= 0) {
                    form.setTimeSlotFlag("");
                    form.getErrors().put("REPORTTIME_ERROR", ErrorMessages.REPORT_REPORTTIME_ERROR);
                    return mv;
                }
            }
            params.put("startTime", form.getStartTime());
            params.put("endTime", form.getEndTime());
            k = appService.queryCountByTimeSlot(params);
            if (Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1) >= k) {
                params.put("currentPage", "1");

            }
            else {
                params.put("currentPage", form.getCurrentPage());
            }
            listBean = appService.queryListByTimeSlot(params);
        }
        form.setTotalCount(Integer.toString(k));
        form.setListBean(listBean);
        // 获取sdk下载地址
        form.setIphoneDownLoad(PathUtils.getSDKDownURL(CommonDef.downUrlKey.KEY_IPHONE, request));
        form.setAndroidDownLoad(PathUtils.getSDKDownURL(CommonDef.downUrlKey.KEY_ANDROID, request));
        mv.addObject("p", form);
        return mv;
    }

    /**
     * 添加装换页面
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=add")
    public ModelAndView add(AppForm form, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("app/app_add", Constants.DEFAULT_COMMAND, form);
        // 封装form的初始信息
        initialize(form);
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 增加应用第一步
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=doAddBegin")
    public ModelAndView doAddBegin(AppForm form) {
        ModelAndView mv = new ModelAndView("app/app_add2", Constants.DEFAULT_COMMAND, form);
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 更新跳转页面
     * 
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=edit")
    public ModelAndView edit(AppForm form, HttpServletRequest request) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView("redirect:app.do?action=listApp", Constants.DEFAULT_COMMAND, form);
        // 判断是否存在appId，不存在返回列表页面
        if (StringUtils.isNotEmpty(request.getParameter("appId"))) {
            Integer appId = StringUtil.StringToInteger(request.getParameter("appId"));
            Long memberId = getLogonMemberId();
            CoreApp app = appService.queryById(appId, memberId);
            String appPlatformType = app.getAppPlatformType();
            String downUrlKey = null;
            if (CommonDef.appType.CON_IPHONE.equals(appPlatformType)) {
                // iphone类型
                downUrlKey = CommonDef.downUrlKey.KEY_IPHONE;
                form.setAppPlatformType(CommonDef.appTypeDetil.APP_IPHONE);
            }
            else {
                downUrlKey = CommonDef.downUrlKey.KEY_ANDROID;
                form.setAppPlatformType(CommonDef.appTypeDetil.APP_ANDROID);
            }
            form.setDownLoad(PathUtils.getSDKDownURL(downUrlKey, request));
            form.setApp(app);
            initialize(form);
            mv = new ModelAndView("app/app_edit", Constants.DEFAULT_COMMAND, form);
            mv.addObject("form", form);
            return mv;
        }
        return mv;
    }

    /**
     * 添加、更新操作,根据是否存在appId判断
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=doAdd", method = RequestMethod.POST)
    public ModelAndView doAdd(AppForm form, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("app/app_add", Constants.DEFAULT_COMMAND, form);
        // 得到CoreApp对象
        CoreApp app = form.getApp();
        // 校验字段
        // 全年龄
        app.setUserAgePag("0");
        // 不分性别
        app.setUserSex("0");
        // 国内、国外
        app.setPubOutside("0");
        // 发现表单校验有错误返回增加页面
        if (!form.getErrors().isEmpty()) {
            initialize(form);
            mv.addObject("form", form);
            if (null != app.getAppId()) {
                mv = new ModelAndView("app/app_edit", Constants.DEFAULT_COMMAND, form);
                mv.addObject("form", form);
                return mv;
            }
            return mv;
        }
        app.setMemberId(getLogonMemberId());
        // 如果存在appId那么执行的是更新操作
        if (null != app.getAppId()) {
            app.setUpdTime(new Date());
            appService.txEditApp(app);
            mv = new ModelAndView("redirect:app.do?action=listApp&currentPage=" + form.getCurrentPage());
            return mv;
        }
        // 不存在appId执行添加操作
        String issueId = StringUtil.getUUID().toString();
        app.setAppCode(issueId);
        String appPlatformType = app.getAppPlatformType();
        String downUrlKey = null;
        if (CommonDef.appType.CON_IPHONE.equals(appPlatformType)) {
            // iphone类型
            downUrlKey = CommonDef.downUrlKey.KEY_IPHONE;
        }
        else {
            downUrlKey = CommonDef.downUrlKey.KEY_ANDROID;
        }
        form.setDownLoad(PathUtils.getSDKDownURL(downUrlKey, request));

        app.setAddOper(getLogonEmail());
        app.setUpdOper(getLogonEmail());
        appService.txAddApp(app);
        return doAddBegin(form);
    }

    /**
     * 应用发布功能
     * 
     * @param form
     * @param request
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=issue")
    public ModelAndView issue(AppForm form, HttpServletRequest request, HttpServletResponse response)
            throws InvalidInfoException {
        response.setContentType("text/html;charset=utf-8");
        // 判断是否存在appId，不存在返回列表页面
        try {
            if (StringUtils.isNotEmpty(request.getParameter("appId"))) {
                Integer appId = StringUtil.StringToInteger(request.getParameter("appId"));
                Long memberId = getLogonMemberId();
                CoreApp app = appService.queryById(appId, memberId);
                app.setAppStatus(CommonDef.appCon.CON_ISSUE);
                appService.txEditApp(app);
                CookieUtils.setTipMessageCookie(request, response, CommonDef.issue.RETURN_SUCCESS);
            }
            else {
                response.getWriter().print(CommonDef.finalStrCon.RETURN_FAILURE);
            }
        }
        catch (IOException e) {
            logger.error("issue error", e);
        }
        return null;
    }

    /**
     * 应用暂停
     * 
     * @param form
     * @param request
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=suspend")
    public ModelAndView suspend(AppForm form, HttpServletRequest request, HttpServletResponse response)
            throws InvalidInfoException {
        response.setContentType("text/html;charset=utf-8");
        try {
            // 判断是否存在appId，不存在返回列表页面
            if (StringUtils.isNotEmpty(request.getParameter("appId"))) {
                Long memberId = getLogonMemberId();
                Integer appId = StringUtil.StringToInteger(request.getParameter("appId"));
                CoreApp app = appService.queryById(appId, memberId);
                app.setAppStatus(CommonDef.appCon.CON_SUSPEND);
                appService.txEditApp(app);
                CookieUtils.setTipMessageCookie(request, response, CommonDef.suspend.RETURN_SUCCESS);
            }
            else {
                response.getWriter().print(CommonDef.finalStrCon.RETURN_FAILURE);
            }
        }
        catch (IOException e) {
            logger.error("suspend error", e);
        }
        return null;
    }

    /**
     * 初始化form信息
     * 
     * @param form
     * @return
     */
    public AppForm initialize(AppForm form) {
        // 分别取出用户群、用户年龄段、应用类型、适用平台赋值给form
        List<Dictionary> tagSexs = form.getTagSexs();
        List<Dictionary> tagAges = form.getTagAges();
        List<Dictionary> tagTypes = form.getTagTypes();
        List<Dictionary> tagSps = form.getTagSps();
        // 根据数据字典表中的数据分别匹配
        for (Dictionary dictionary : appService.txQueryDictionaryList()) {
            String dictType = dictionary.getDictType();
            if ("TAG_SEX".equals(dictType)) {
                tagSexs.add(dictionary);
            }
            if ("TAG_AGE".equals(dictType)) {
                tagAges.add(dictionary);
            }
            if ("TAG_SOFT_TYPE".equals(dictType)) {
                tagTypes.add(dictionary);
            }
            if ("TAG_SP".equals(dictType)) {
                tagSps.add(dictionary);
            }
        }
        return form;
    }
}
