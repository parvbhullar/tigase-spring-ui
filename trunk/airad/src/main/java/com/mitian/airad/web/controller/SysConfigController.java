/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.model.SysConfig;
import com.mitian.airad.service.SysConfigService;

@Controller
@RequestMapping("/sysconfig.do")
public class SysConfigController extends AbstractController {
    @Autowired
    public SysConfigService sysConfigService;

    @RequestMapping(params = "action=querySysConfigById")
    public ModelAndView querySysConfigById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 查询状态
        SysConfig sysConfig = sysConfigService.querySysConfigById();
        if (sysConfig != null) {
            String publish = sysConfig.getSysVal();
            // 1已发布0未发布
            response.getWriter().print(publish);
        }
        else {
            response.getWriter().print("ERROR");
        }
        return null;
    }

    @RequestMapping(params = "action=updateSysConfigById")
    public ModelAndView updateSysConfigById() {
        ModelAndView mv = new ModelAndView("publish/button");
        sysConfigService.updateSysConfigById();// 更新状态
        return mv;
    }

    /**
     * 后台
     * 
     * @return
     */
    @RequestMapping(params = "action=show")
    public ModelAndView show() {
        ModelAndView mv = new ModelAndView("publish/button");
        return mv;
    }

    /**
     * 前台
     * 
     * @return
     */
    @RequestMapping(params = "action=showIndex")
    public ModelAndView showIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("publish/index");
        SysConfig sysConfig = sysConfigService.querySysConfigById();
        if (sysConfig != null) {
            String publish = sysConfig.getSysVal();
            // 1已发布0未发布
            if ("1".equals(publish)) {
                mv.addObject("ispublish", publish);
                return mv;
            }
            else {
                return mv;
            }
        }
        return mv;
    }

}
