/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.common.auth.cookie.CookieGenerator;
import com.mitian.airad.common.constant.MemberConstant;

/**
 * PromotionController.java
 * 
 * @author Administrator
 */
@Controller
public class PromotionController {
    @RequestMapping(value = "/i/{code}", method = RequestMethod.GET)
    public ModelAndView redirectToRegiest(@PathVariable("code") String code, HttpServletRequest request,
            HttpServletResponse response) {
        CookieGenerator cookie = new CookieGenerator();
        cookie.setCookieName(MemberConstant.COOKIE_KEY_PROMOTIONID);
        cookie.addCookie(response, code);
        ModelAndView mv = new ModelAndView("redirect:../member.do?action=register");
        return mv;
    }
}
