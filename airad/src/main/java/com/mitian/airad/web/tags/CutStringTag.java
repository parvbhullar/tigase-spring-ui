/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.web.util.HtmlUtils;

/**
 * CutStringTag.java
 * 
 * @author baojun
 */
public class CutStringTag extends TagSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 1638984530467586859L;
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(CutStringTag.class);
    String value;
    String mark = "";
    Integer size;

    @Override
    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        String html = cutString(value, size, mark);
        try {
            pageContext.getOut().write(html.toString());
        }
        catch (IOException e) {
            logger.error("tag CutStringTag error", e);
        }
        return EVAL_PAGE;
    }

    public static void main(String[] args) {
        CutStringTag tag = new CutStringTag();
        String s = "&&&&————————————&&&&&&&";
        System.out.println(tag.cutString(s, 8, "..."));
    }

    /**
     * <airad:cutString size="4" value="我早3333"/>
     * 
     * @param str
     * @param len
     * @param mark
     * @return
     */
    public String cutString(String str, int len, String mark) {
        len = len * 2;
        StringBuffer sb = new StringBuffer();
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            // 中文两位英文一位
            counter += curChar < 255 ? 1 : 2;
            if (counter > len) {
                String result = sb.toString().trim();
                sb = new StringBuffer(result + mark);
                break;
            }
            sb.append(curChar);
        }
        return HtmlUtils.htmlEscape(sb.toString());
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
