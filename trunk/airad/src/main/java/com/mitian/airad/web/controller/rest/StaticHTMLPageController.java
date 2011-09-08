/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * StaticHTMLPageController.java
 * 
 * @author baojun
 */
@Controller
public class StaticHTMLPageController {
    @RequestMapping(value = "/static/{path}", method = RequestMethod.GET)
    public ModelAndView redirectToStaticPage(@PathVariable("path") String path, HttpServletRequest request,
            HttpServletResponse response) {
        String returnPath = "page/" + path;
        ModelAndView mv = new ModelAndView(returnPath);
        return mv;
    }
}
