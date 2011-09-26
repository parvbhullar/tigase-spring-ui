package com.mitian.airad.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * IdCardVerificationUtils.java
 * 
 * @author baojun
 */
public abstract class IdCardVerificationUtils {

    public static void main(String[] args) {
        System.err.println(IdCardVerificationUtils.checkIdCard("111111111111111"));
        System.err.println(IdCardVerificationUtils.isValidateIdCard("111111111111111"));
    }

    /**
     * 身份证号合法则返回true
     * 
     * @param idCard
     * @return
     */
    public static final boolean isValidateIdCard(String idCard) {
        return checkIdCard(idCard) == null ? true : false;
    }

    /**
     * 身份证合法则返回null
     * 
     * @param idCard
     * @return
     */

    public static final String checkIdCard(String idCard) {
        String[] errors = {null, "身份证号码位数不对!", "身份证号码出生日期超出范围或含有非法字符!", "身份证号码校验错误!", "请输入正确的身份证号!"};
        if (StringUtil.isEmpty(idCard) || idCard.length() < 15) {
            return errors[1];
        }
        else if (idCard.substring(6, 10).equals("1111")) {
            return errors[3];
        }
        int y;
        String jym;
        int s;
        String m;
        String[] idcard_array = idCard.split("");
        if (AREA.get(idCard.substring(0, 2)) != null) {
            String ereg = "";
            switch (idCard.length()) {
                case 15 :
                    if ((Integer.parseInt(idCard.substring(6, 8)) + 1900) % 4 == 0
                            || ((Integer.parseInt(idCard.substring(6, 8)) + 1900) % 100 == 0 && (Integer
                                    .parseInt(idCard.substring(6, 8)) + 1900) % 4 == 0)) {
                        ereg =
                                "^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$";// 测试出生日期的合法性
                    }
                    else {
                        ereg =
                                "^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$";// 测试出生日期的合法性
                    }

                    if (idCard.matches(ereg)) {
                        return errors[0];
                    }
                    else {
                        return errors[3];
                    }
                case 18 :
                    if (Integer.parseInt(idCard.substring(6, 10)) % 4 == 0
                            || (Integer.parseInt(idCard.substring(6, 10)) % 100 == 0 && Integer.parseInt(idCard
                                    .substring(6, 10)) % 4 == 0)) {
                        // 闰年出生日期的合法性正则表达式
                        ereg =
                                "^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$";
                    }
                    else {
                        // 平年出生日期的合法性正则表达式
                        ereg =
                                "^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$";
                    }
                    // 测试出生日期的合法性
                    if (idCard.matches(ereg)) {
                        // 计算校验位
                        s =
                                (Integer.parseInt(idcard_array[1]) + Integer.parseInt(idcard_array[11])) * 7
                                        + (Integer.parseInt(idcard_array[2]) + Integer.parseInt(idcard_array[12])) * 9
                                        + (Integer.parseInt(idcard_array[3]) + Integer.parseInt(idcard_array[13])) * 10
                                        + (Integer.parseInt(idcard_array[4]) + Integer.parseInt(idcard_array[14])) * 5
                                        + (Integer.parseInt(idcard_array[5]) + Integer.parseInt(idcard_array[15])) * 8
                                        + (Integer.parseInt(idcard_array[6]) + Integer.parseInt(idcard_array[16])) * 4
                                        + (Integer.parseInt(idcard_array[7]) + Integer.parseInt(idcard_array[17])) * 2
                                        + Integer.parseInt(idcard_array[8]) * 1 + Integer.parseInt(idcard_array[9]) * 6
                                        + Integer.parseInt(idcard_array[10]) * 3;
                        y = s % 11;
                        m = "F";
                        jym = "10X98765432";
                        m = jym.substring(y, y + 1);// 判断校验位
                        if (m.equals(idcard_array[18])) {
                            return errors[0]; // 检测ID的校验位
                        }
                        else {
                            return errors[3];
                        }
                    }
                    else {
                        return errors[2];
                    }
                default :
                    return errors[1];
            }

        }
        else {
            return errors[3];
        }
    }

    private static final Map<String, String> AREA = new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
            put("11", "北京");
            put("12", "天津");
            put("13", "河北");
            put("14", "山西");
            put("15", "内蒙古");
            put("21", "辽宁");
            put("22", "吉林");
            put("23", "黑龙江");
            put("31", "上海");
            put("32", "江苏");
            put("33", "浙江");
            put("34", "安徽");
            put("35", "福建");
            put("36", "江西");
            put("37", "山东");
            put("41", "河南");
            put("42", "湖北");
            put("43", "湖南");
            put("44", "广东");
            put("45", "广西");
            put("46", "海南");
            put("50", "重庆");
            put("51", "四川");
            put("52", "贵州");
            put("53", "云南");
            put("54", "西藏");
            put("61", "陕西");
            put("62", "陕西");
            put("63", "青海");
            put("64", "宁夏");
            put("65", "新疆");
            put("71", "台湾");
            put("81", "香港");
            put("82", "澳门");
            put("91", "国外");
        }
    };

}
