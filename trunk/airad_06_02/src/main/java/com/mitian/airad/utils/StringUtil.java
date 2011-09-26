/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.mitian.airad.CommonDef;

/**
 * StringUtil.java
 * 
 * @author Administrator
 */
public abstract class StringUtil {
    private static SimpleDateFormat form = new SimpleDateFormat();
    private static Logger log = Logger.getLogger(StringUtil.class);

    /**
     * 将字符串转换成SQL文的In条件.
     * 
     * @param value
     * @return
     */
    public static String formatStringToInCondition(String value) {
        if (isEmpty(value)) {
            return CommonDef.EMPTY;
        }
        StringBuffer sb = new StringBuffer();
        String[] strArr = value.split(CommonDef.STR_COMMA);
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                sb.append(CommonDef.SINGLE_QUOTE);
                sb.append(str);
                sb.append(CommonDef.SINGLE_QUOTE);
                sb.append(CommonDef.STR_COMMA);
            }
        }

        if (isEmpty(sb.toString())) {
            return CommonDef.EMPTY;
        }
        else {
            return sb.toString().substring(0, sb.toString().length() - 1);
        }
    }

    /**
     * 将字符串list转换成SQL文的In条件.
     * 
     * @param list
     * @return
     */
    public static String changeListToString(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        String str = CommonDef.EMPTY;
        for (String obj : list) {
            str = str + "'" + obj.toString() + "',";
        }
        return str.substring(0, str.length() - 1);
    }

    public static Integer integerToString(String val) {
        return Integer.parseInt(val);
    }

    public static boolean isEmpty(String str) {
        if (null == str || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 将Date类型转换string yyyy-mm-dd
     * 
     * @return
     */
    public static String getStringDateYear(Date date) {
        form = new SimpleDateFormat("yyyy-MM-dd");
        return form.format(date);
    }

    /**
     * 将Date类型转换string HH
     * 
     * @param date
     * @return
     */
    public static String getStringDateHour(Date date) {
        form = new SimpleDateFormat("HH");
        return form.format(date);
    }

    /**
     * 将Date类型转换string mm
     * 
     * @param date
     * @return
     */
    public static String getStringDateMin(Date date) {
        form = new SimpleDateFormat("mm");
        return form.format(date);
    }

    /**
     * 将Date类型转换string yyyy-mm
     * 
     * @param date
     * @return
     */
    public static String getStringDateYM(Date date) {
        form = new SimpleDateFormat("yyyy-MM");
        return form.format(date);
    }

    /**
     * 将String类型转换成Integer
     * 
     * @param str
     * @return
     */
    public static Integer stringToInteger(String str) {
        Integer value = null;
        try {
            value = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            log.error("StringToInteger error input :" + str, e);
        }
        return value;

    }

    /**
     * 将String类型转换成Long
     * 
     * @param str
     * @return
     */
    public static Long stringToLong(String str) {
        Long value = null;
        try {
            value = Long.parseLong(str);
        }
        catch (NumberFormatException e) {
            log.error("StringToLong error input :" + str);
        }
        return value;

    }

    /**
     * 将String类型转换成Long
     * 
     * @param str
     * @return
     */
    public static Short stringToShort(String str) {
        Short value = null;
        try {
            value = Short.parseShort(str);
        }
        catch (NumberFormatException e) {
            log.error("StringToShort error input :" + str);
        }
        return value;

    }

    /**
     * 获取唯一的uuid
     * 
     * @return
     */
    public static UUID getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid;
    }

    /**
     * 得到日期转化
     * 
     * @param date
     * @return
     */
    public static Date getDate(String date) {
        Date d = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            d = sf.parse(date);
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 得到日期转化yyyy-MM-dd
     * 
     * @param date
     * @return
     */
    public static Date getDateY(String date) {
        Date d = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            d = sf.parse(date);
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 得到日期转化yyyy-MM-dd
     * 
     * @param date
     * @return
     */
    public static Date getDateYM(Date date) {
        Date d = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String dateStr = sf.format(date);
            d = sf.parse(dateStr);
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 得到日期转化yyyy-MM-dd hh:mm
     * 
     * @param date
     * @return
     */
    public static Date getDateY(Date date) {
        Date d = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sf.format(date);
            d = sf.parse(dateStr);
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 将string转化string[]
     * 
     * @param str
     * @return
     */
    public static String[] getString(String str) {
        String[] s = str.split(",");
        return s;
    }

    /**
     * 将string[]转string
     * 
     * @param str
     * @return
     */
    public static String getString(String[] str) {
        StringBuffer s = new StringBuffer();

        for (int i = 0; i < str.length; i++) {
            String strre = str[i].replaceAll(" ", "");
            s.append(strre);
        }
        String strs = s.toString();
        return strs.substring(1, strs.length() - 1);
    }

    /**
     * 比较当前余额是否可用
     * 
     * @param offer 出价
     * @param blance 余额
     * @return
     */
    public static boolean bigDecimalCompareTo(String offer, BigDecimal blance) {
        if (isEmpty(offer)) {
            return false;
        }
        String test = "^\\d+(\\.\\d+)?$";
        Pattern p = Pattern.compile(test);
        if (p.matcher(offer).matches()) {
            Double offerD = Double.parseDouble(offer);
            int k = blance.compareTo(new BigDecimal(offerD));
            if (k >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断类型是否符合BigDecimal
     * 
     * @param blance
     * @return
     */
    public static boolean bigDecimalCon(String blance) {
        String test = "^\\d+(\\.\\d+)?$";
        Pattern p = Pattern.compile(test);
        boolean b = p.matcher(blance).matches();
        return b;
    }

    /**
     * 判断类型是否是数字
     * 
     * @param blance
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 替换特殊字符
     * 
     * @param str
     * @return
     */
    public static String replaceChar(String str) {
        return StringUtils.isNotBlank(str) ? str.replace("%", "/%").replace("_", "/_").replace("'", "") : "";

    }

    public static String replaceChar2(String str) {
        return StringUtils.isNotBlank(str) ? str.replace("%", "/%").replace("_", "/_").replace("'", "\\\'") : "";

    }

    public static String reverseReplaceChar(String str) {
        return StringUtils.isNotBlank(str) ? str.replace("/%", "%").replace("/_", "_").replace("\\\'", "'") : "";
    }

    /**
     * 对str进行缩减 超过20个字符长度后添加...
     * 
     * @param str
     * @return
     */
    public static String overlayWith20(String str) {
        if (StringUtils.trimToEmpty(str).length() > 20) {
            return StringUtils.overlay(str, "...", 20, str.length());
        }
        else {
            return str;
        }
    }

}
