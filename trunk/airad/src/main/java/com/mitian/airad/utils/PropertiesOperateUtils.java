/*
 * Copyright 2011 mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.mitian.airad.common.utils.StringUtils;

/**
 * Properties操作类
 * 
 * @author leifenghai
 */
public class PropertiesOperateUtils {

    private static Logger logger = Logger.getLogger(PropertiesOperateUtils.class);
    private static final Map<String, Properties> propertiesMap = new HashMap<String, Properties>();

    /**
     * 获得属性文件的值
     * 
     * @param propertiesFileName 配置文件路径
     * @param key
     * @param defaultValue
     * @return
     */
    public static final String getString(String propertiesFileName, String key, String defaultValue) {
        String strValue = null;
        Properties props = getProperties(propertiesFileName);
        if ((props != null) && (key != null)) {
            strValue = StringUtils.trimToNull(props.getProperty(key));
        }

        if (strValue == null) {
            if (StringUtil.isEmpty(defaultValue)) {
                strValue = "";
            }
            else {
                strValue = defaultValue;
            }
        }

        return strValue;
    }

    public static final boolean getBoolean(String propertiesFileName, String key, boolean defaultValue)
            throws IllegalArgumentException {
        String strValue = getString(propertiesFileName, key, null);
        boolean value;

        if (strValue == null) {
            value = defaultValue;
        }
        else {
            if ("true".equalsIgnoreCase(strValue)) {
                value = true;
            }
            else if ("false".equalsIgnoreCase(strValue)) {
                value = false;
            }
            else {
                throw new IllegalArgumentException("Invalid properties value: " + key + "=" + strValue);
            }
        }

        return value;
    }

    public static final boolean getBoolean(String propertiesFileName, String key) throws IllegalArgumentException {
        String strValue = getString(propertiesFileName, key, null);
        boolean value;

        if (strValue == null) {
            value = false;
        }
        else {
            if ("true".equalsIgnoreCase(strValue)) {
                value = true;
            }
            else if ("false".equalsIgnoreCase(strValue)) {
                value = false;
            }
            else {
                throw new IllegalArgumentException("Invalid properties value: " + key + "=" + strValue);
            }
        }

        return value;
    }

    public static final int getInt(String propertiesFileName, String key, int defaultValue)
            throws IllegalArgumentException {
        String strValue = getString(propertiesFileName, key, null);
        int value;

        if (strValue == null) {
            value = defaultValue;
        }
        else {
            try {
                value = Integer.parseInt(strValue);
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid properties value: " + key + "=" + strValue);
            }
        }

        return value;
    }

    public static final long getLong(String propertiesFileName, String key, long defaultValue)
            throws IllegalArgumentException {
        String strValue = getString(propertiesFileName, key, null);
        long value;

        if (strValue == null) {
            value = defaultValue;
        }
        else {
            try {
                value = Long.parseLong(strValue);
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid properties value: " + key + "=" + strValue);
            }
        }

        return value;
    }

    private static final Object lockForRead = new Object();

    private static Properties getProperties(String propertiesFileName) {
        String fileName = org.apache.commons.lang.StringUtils.trimToEmpty(propertiesFileName);
        if (StringUtils.isBlank(fileName)) {
            return null;
        }
        Properties properties = propertiesMap.get(fileName);
        if (properties != null) {
            return propertiesMap.get(fileName);
        }
        else {
            synchronized (lockForRead) {
                properties = new Properties();
                FileInputStream in = null;
                try {
                    in = new FileInputStream(fileName);
                    properties.load(in);
                    propertiesMap.put(fileName, properties);
                }
                catch (IOException e) {
                    logger.error("调用getProperties方法时出现异常", e);
                }
                IOUtils.closeQuietly(in);
                return properties;
            }
        }
    }

    /**
     * 修改属性文件的值，若不存在则会新增一个
     * 
     * @param propertiesFileName
     * @param propertyName
     * @param propertyValue
     * @return
     */
    public synchronized static boolean changeValueByPropertyName(String propertiesFileName, String propertyName,
            String propertyValue) {
        boolean writeOK = true;
        String fileName = org.apache.commons.lang.StringUtils.trimToEmpty(propertiesFileName);
        Properties p = new Properties();
        FileInputStream in;
        try {
            in = new FileInputStream(fileName);
            p.load(in);//   
            in.close();
            p.setProperty(propertyName, propertyValue);// 设置属性值，如不属性不存在新建
            // p.setProperty("testProperty","testPropertyValue");
            FileOutputStream out = new FileOutputStream(fileName);// 输出流
            p.store(out, "");// 设置属性头，如不想设置，请把后面一个用""替换掉
            out.flush();// 清空缓存，写入磁盘
            propertiesMap.put(fileName, p);
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(in);
        }
        catch (Exception e) {
            logger.error("changeValueByPropertyName error", e);
            writeOK = false;
        }
        return writeOK;
    }
}
