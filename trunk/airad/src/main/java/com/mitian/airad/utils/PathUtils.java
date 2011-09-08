/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.mitian.airad.CommonDef;
import com.mitian.airad.Constants;

/**
 * PathUtils.java
 * 
 * @author baojun
 */
public class PathUtils {
    private static String iphoneSDKDownloadURL = null;
    private static String androidSDKDownloadURL = null;

    /**
     * 获取SDK下载路径
     * 
     * @param type
     * @return
     */
    public static String getSDKDownURL(String type, HttpServletRequest request) {
        if (CommonDef.downUrlKey.KEY_IPHONE.equals(type)) {
            if (iphoneSDKDownloadURL == null) {
                String path = request.getSession().getServletContext().getRealPath(Constants.AIRAD_PROERTIES_PATH);
                iphoneSDKDownloadURL = PropertiesOperateUtils.getString(path, CommonDef.downUrlKey.KEY_IPHONE, null);
            }
            return iphoneSDKDownloadURL;
        }
        else if (CommonDef.downUrlKey.KEY_ANDROID.equals(type)) {
            if (androidSDKDownloadURL == null) {
                String path = request.getSession().getServletContext().getRealPath(Constants.AIRAD_PROERTIES_PATH);
                androidSDKDownloadURL = PropertiesOperateUtils.getString(path, CommonDef.downUrlKey.KEY_ANDROID, null);
            }
            return androidSDKDownloadURL;
        }
        else {
            return StringUtils.EMPTY;
        }
    }
}
