/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.Constants;
import com.mitian.airad.model.AccIncome;
import com.mitian.airad.model.CoreApp;
import com.mitian.airad.service.AppIncomeService;
import com.mitian.airad.service.AppService;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.IncomForm;

/**
 * AppIncomeController
 * 
 * @author Administrator
 */
@Controller
@RequestMapping("/appIncome.do")
@RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_DEVELOPERS})
public class AppIncomeController extends AbstractController {

    // 注入AppService
    @Autowired
    private AppIncomeService appIncomeService;
    @Autowired
    private AppService appService;

    /**
     * 应用收益列表
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=listAppIncome")
    public ModelAndView list(IncomForm form) {

        // 查询数据封装
        Long memberId = getLogonMemberId();
        AccIncome accIncome = form.getAccIncome();
        CoreApp app = new CoreApp();
        app.setMemberId(memberId);
        List<CoreApp> listApp = appService.queryList(app);
        StringBuffer inAppCode = new StringBuffer();
        for (CoreApp a : listApp) {
            inAppCode.append("'" + a.getAppCode() + "',");
        }
        String inAppCodeId = inAppCode.toString().substring(0, inAppCode.toString().length() - 1);

        Map<String, String> params = new HashMap<String, String>();
        params.put("memberId", String.valueOf(memberId));
        params.put("pageSize", form.getPageSize());
        params.put("currentPage", form.getCurrentPage());
        accIncome.setInAppCode(inAppCodeId);
        List<AccIncome> listBean = appIncomeService.queryList(params);
        int k = appIncomeService.queryCount(memberId);
        form.setTotalCount(Integer.toString(k));
        form.setListBean(listBean);
        ModelAndView mv = new ModelAndView("app/appIncome_list", Constants.DEFAULT_COMMAND, form);
        mv.addObject("p", form);
        return mv;
    }
}
