/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * 基于volecity的邮件模板渲染组件
 * 
 * @author yuancaho
 */

public class VelocityTemplateUtils {

    // 模板引擎工厂
    @Autowired
    private VelocityEngineFactoryBean velocityEngineFactoty;

    // 模板引擎工厂
    private VelocityEngine velocityEngine;

    // 日志工具
    private static Log log = LogFactory.getLog(VelocityTemplateUtils.class);

    /**
     * 在容器启动时初始化模板引擎
     */
    public void initEngine() throws VelocityException, IOException {
        try {
            log.info("begin to init velocity engine !");
            velocityEngine = velocityEngineFactoty.createVelocityEngine();
            log.info("init velocity engine successful !");
        }
        catch (VelocityException e) {
            log.error("init velocity engine fail !", e);
            throw e;
        }
        catch (IOException e) {
            log.error("init velocity engine fail !", e);
            throw e;
        }
    }

    /**
     * @param templet 模板路径
     * @param map 模板中的参数
     * @return 模板内容
     * @throws IOException
     * @throws VelocityException
     */
    public String mergeMailTemplet(String template, Map<String, Object> map) {
        if (template != null && !"".equals((StringUtils.trimToEmpty(template)))) {
            return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, map);
        }
        else {
            return null;
        }

    }

    /**
     * @param templet 模板路径
     * @param encoding 模板内容采用的编码格式
     * @param map 模板中的参数
     * @return 模板内容
     * @throws IOException
     * @throws VelocityException
     */
    public String mergeMailTemplet(String template, String encoding, Map<String, Object> map) {
        if (template != null && !"".equals((StringUtils.trimToEmpty(template)))) {
            return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, encoding, map);
        }
        else {
            return null;
        }
    }

    /**
     * @return the velocityEngineFactoty
     */
    public VelocityEngineFactoryBean getVelocityEngineFactoty() {
        return velocityEngineFactoty;
    }

    /**
     * @param velocityEngineFactoty the velocityEngineFactoty to set
     */
    public void setVelocityEngineFactoty(VelocityEngineFactoryBean velocityEngineFactoty) {
        this.velocityEngineFactoty = velocityEngineFactoty;
    }
}
