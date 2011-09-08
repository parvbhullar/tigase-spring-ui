/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.mitian.airad.common.utils.SpringContextUtils;
import com.mitian.airad.dao.SysConfigDAO;
import com.mitian.airad.model.SysConfig;

/**
 * SysConfigForMailNoticeTag.java
 * 
 * @author Administrator
 */
public class SysConfigForMailNoticeTag extends TagSupport {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(SysConfigTag.class);
    private String valDefault;
    private String key;
    private String type;
    private String value;
    private String id;
    private String name;
    private String style;
    private String valClass;
    private String flag;// out直接输出文本
    private List<SysConfig> sysConfigList;

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {

        try {
            if (flag != null) {
                if (flag.equals("out")) {
                    init(type, key);
                    if (value != null) {
                        pageContext.getOut().write(value);
                    }
                    else {
                        if (valDefault != null) {
                            pageContext.getOut().write(valDefault);
                        }
                        else {
                            pageContext.getOut().write("");
                        }
                    }
                }
            }
            else {
                init(type);
                if (sysConfigList != null) {
                    StringBuffer selectHtml = new StringBuffer();
                    selectHtml.append("<select id='" + id + "' name='" + name + "'");
                    if (valClass != null) {
                        selectHtml.append(" class='" + valClass + "'>");
                    }
                    if (style != null) {
                        selectHtml.append(" style='" + style + "'");
                    }
                    selectHtml.append(" >");
                    // selectHtml.append("<option value='-1'>请选择金额...</option>");
                    for (SysConfig sysConfig : sysConfigList) {
                        selectHtml.append("<option value='" + sysConfig.getSysVal() + "' ");
                        if (valDefault != null && valDefault.trim().equals(sysConfig.getSysVal().trim())) {
                            selectHtml.append(" selected ");
                        }
                        selectHtml.append(">" + sysConfig.getSysVal() + ".00" + "</option>");
                    }
                    selectHtml.append("</select>");
                    pageContext.getOut().write(selectHtml.toString());
                }
            }
        }
        catch (IOException e) {
            logger.error("tag SysConfigTag error", e);
        }
        return EVAL_PAGE;
    }

    /**
     * @param type
     * @param key
     * @return
     */
    public void init(String type, String key) {
        SysConfigDAO sysConfigDAO = SpringContextUtils.getBean("sysConfigDAOImpl");
        SysConfig sysConfig = sysConfigDAO.findConfigByTypeAndKey(type, key);
        if (sysConfig != null) {
            value = sysConfig.getSysVal();
        }
    }

    /**
     * @param type
     * @param key
     * @return
     */
    public void init(String type) {
        SysConfigDAO sysConfigDAO = SpringContextUtils.getBean("sysConfigDAOImpl");
        sysConfigList = sysConfigDAO.findConfigListByType(type);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param valClass the valClass to set
     */
    public void setValClass(String valClass) {
        this.valClass = valClass;
    }

    /**
     * @return the valClass
     */
    public String getValClass() {
        return valClass;
    }

    /**
     * @param valDefault the valDefault to set
     */
    public void setValDefault(String valDefault) {
        this.valDefault = valDefault;
    }

    /**
     * @return the valDefault
     */
    public String getValDefault() {
        return valDefault;
    }

}
