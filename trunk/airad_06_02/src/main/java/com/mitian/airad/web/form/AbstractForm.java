package com.mitian.airad.web.form;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.mitian.airad.Constants;
import com.mitian.airad.model.CoreMemberInfo;

/**
 * AbstractForm.java
 * 
 * @author baojun
 */
public abstract class AbstractForm {
    private final Logger log = Logger.getLogger(getClass());
    // 处理动作
    private String action;
    // 验证码
    private String verifyCode;
    // 页面跳转标志
    private String xview = "default";
    private String nextUrl;
    private String remoteAddr;
    private String currentPage;
    private String pageSize;
    private String totalCount;
    /** 排序关键字 */
    private String keyWords;
    /** 降序标志 */
    private String desc;
    /** 升序标志 */
    private String asce;

    /**
     * search words 搜索关键词
     */
    private String sw;

    public AbstractForm() {
        currentPage = Constants.DEFAULT_CURRENT_PAGE;
        pageSize = Constants.DEFAULT_PAGE_SIZE;
    }
    private CoreMemberInfo coreMemberInfo;

    /**
     * @return the totalCount
     */
    public String getTotalCount() {
        return totalCount;
    }

    /**
     * @return the currentPage
     */
    public String getCurrentPage() {
        if (NumberUtils.isNumber(StringUtils.trimToEmpty(currentPage))) {
            return currentPage;
        }
        else {
            return "1";
        }
    }

    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    // 错误信息，保证错误信息按顺序输出
    private Map<String, String> errors = new LinkedHashMap<String, String>();
    // controller与service之间的数据传递
    private Map<String, Object> values = new LinkedHashMap<String, Object>();

    /**
     * @return the verifyCode
     */
    public String getVerifyCode() {
        return verifyCode;
    }

    /**
     * @param verifyCode the verifyCode to set
     */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the xview
     */
    public String getXview() {
        return xview;
    }

    /**
     * @param xview the xview to set
     */
    public void setXview(String xview) {
        this.xview = xview;
    }

    /**
     * @return the nextUrl
     */
    public String getNextUrl() {
        if (StringUtils.isEmpty(nextUrl)) {
            return "/personal.do?action=index";
        }
        else {
            return nextUrl;
        }
    }

    /**
     * @param nextUrl the nextUrl to set
     */
    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    /**
     * @return the remoteAddr
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }

    /**
     * @param remoteAddr the remoteAddr to set
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    /**
     * @return the pageSize
     */
    public String getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the errors
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    /**
     * @return the values
     */
    public Map<String, Object> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the asce
     */
    public String getAsce() {
        return asce;
    }

    /**
     * @param asce the asce to set
     */
    public void setAsce(String asce) {
        this.asce = asce;
    }

    public abstract void form2domain();

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getKeyWords() {
        return keyWords;
    }

    /**
     * @return the coreMemberInfo
     */
    public CoreMemberInfo getCoreMemberInfo() {
        return coreMemberInfo;
    }

    /**
     * @param coreMemberInfo the coreMemberInfo to set
     */
    public void setCoreMemberInfo(CoreMemberInfo coreMemberInfo) {
        this.coreMemberInfo = coreMemberInfo;
    }

    /**
     * @return the sw
     */
    public String getSw() {
        return sw;
    }

    /**
     * @param sw the sw to set
     */
    public void setSw(String sw) {
        this.sw = sw;
    }

    /**
     * @return the log
     */
    public Logger getLog() {
        return log;
    }
}
