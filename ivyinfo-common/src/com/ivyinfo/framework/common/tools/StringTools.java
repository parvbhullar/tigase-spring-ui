package com.ivyinfo.framework.common.tools;

import java.util.StringTokenizer;

/**
 * Utility class that groups string manipulation functions.
 *
 *
 * @version $Id: StringTools.java,v 1.2 2007-11-28 11:13:24 nichele Exp $
 */
public class StringTools {

    /**
     * 将一串字符串根据逗号分割成数组
     *
     * @param s the comma separated values list - NOT NULL
     *
     * @return the elements in the list as an array
     */
    public static String[] split(String s) {
        StringTokenizer st = new StringTokenizer(s, ", ");
        String[] values = new String[st.countTokens()];
        for (int i = 0; i < values.length; ++i) {
            values[i] = st.nextToken();
        }
        return values;
    }

    /**
     * 将数组中的值组建成字符串，用逗号连接
     *
     * @param array the String[] to join - NOT NULL
     *
     * @return a comma separated list as a single string
     */
    public static String join(String[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; (i < array.length); ++i) {
            if (i == 0) {
                sb.append(array[i]);
            } else {
                sb.append(',').append(array[i]);
            }
        }

        return sb.toString();
    }

    /**
     * 判断字符串是否为null
     *
     * @param s the string to check
     *
     * @return true if the given string is null or zero-length, false otherwise.
     */
    public static boolean isEmpty(String s) {
        return (s == null) || (s.length() == 0);
    }

    /**
     * Description:  判断字符串是否为空
     *
     * @param value 需要转换的字符
     * @return String 如果为空返回空字符串，不为空返回字符串原值
     */
    public static String tranNull(String value) {
        return (value == null || value.equals("null") ? "" : value);
    }

    /**
     * Description:  判断字符串是否为空并去掉尾部的字符串
     *
     * @param value 需要转换的字符
     * @return String 如果为空返回空字符串，不为空返回去掉字符串尾部空格的字符串
     */
    public static String trim(String value) {
        return (value == null ? "" : value.trim());
    }

    /**
     * 转换字符，将传入的字符中相应的字符转换成另外的字符
     * @param str
     * @param pattern
     * @param replace
     * @return
     */
    public static String replace(String str, String pattern, String replace) {
        if (replace == null) {
            replace = "";
        }
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer((int) str.length() * 2);
        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e + pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }

    /**
     * 转成html标记
     * @param value
     * @return
     */
    public static String toHTML(String value) {
        if (value == null || value.trim().equals("")) {
            return "";
        }
        value = replace(value, "&", "&amp;");
        value = replace(value, "<", "&lt;");
        value = replace(value, ">", "&gt;");
        value = replace(value, "\"", "&" + "quot;");
        value = replace(value, "\n", "<BR>");
        value = replace(value, " ", "&nbsp;");
        return value;
    }

    /**
     * 转成符号
     * @param value
     * @return
     */
    public static String toStr(String value) {
        if (value == null || value.trim().equals("")) {
            return "";
        }
        value = replace(value, "&amp;", "&");
        value = replace(value, "&lt;", "<");
        value = replace(value, "&gt;", ">");
        value = replace(value, "&" + "quot;", "\"");
        value = replace(value, "<BR>", "\n");
        value = replace(value, "&nbsp;", " ");
        return value;
    }

    /**
     *
     * @param s
     * @return
     */
    public static boolean isNumeric(String s) {
        String s1 = "0123456789";
        for (int i = 0; i < s.length(); i++) {
            if (s1.indexOf(s.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }
}
