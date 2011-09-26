/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * QuartzService.java
 * 
 * @author Administrator
 */

public abstract class ReportDateUtil {
    public static final String BACKDATA = "Date Error";
    public static final String FLAG = "-";
    public static final String MONTH_MINNUM = "01";
    public static final String MONTH_MAXNUM = "12";
    public static final String ZERO = "0";
    public static final String STRING_MONTH = "MM";
    public static final String STRING_YEAR = "yyyy";

    /**
     * 得到当天+DAY的日期
     * 
     * @param style 日期样式
     * @param day 增加的天数
     * @return
     */
    public static String getTomorrowDate(String style, int day) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, day);
        date = calendar.getTime();
        return getDate(style, date);
    }

    public static String getTomorrowDate(String style, Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, day);
        date = calendar.getTime();
        return getDate(style, date);
    }

    public static String getStratQuarter() {
        int month = Integer.parseInt(getDate(STRING_MONTH, new Date()));
        int key = 4;
        if (month >= 1 && month <= 3) {
            key = 1;
        }
        else if (month >= 4 && month <= 6) {
            key = 2;
        }
        else if (month >= 7 && month <= 9) {
            key = 3;
        }
        String stratQuarter = getDate(STRING_YEAR, new Date()) + FLAG;
        switch (key) {
            case 1 :
                stratQuarter += "01" + FLAG + "01";
                break;
            case 2 :
                stratQuarter += "04" + FLAG + "01";
                break;
            case 3 :
                stratQuarter += "07" + FLAG + "01";
                break;

            default :
                stratQuarter += "10" + FLAG + "01";
                break;
        }
        return stratQuarter;
    }

    /**
     * 根据日期格式返回日期
     * 
     * @param style 日期格式
     * @param date 日期
     */
    public static String getDate(String style, Date date) {
        return new SimpleDateFormat(style).format(date);
    }

    /**
     * 根据日期格式返回日期
     */
    public static Date getDate(String style, String param) throws ParseException {
        return new SimpleDateFormat(style).parse(param);
    }

    public static String getStartMonthDate(String date) {
        String str = "";
        if (null != date && !"".equals(date)) {
            str = date.substring(0, date.length() - 2);
        }
        return str + MONTH_MINNUM;
    }

    /**
     * @param format 日期格式
     * @param date 日期
     */
    public static Date getDateTime(String format, Date date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format);
        String todayAsString = df.format(date);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(format, todayAsString));
        return cal.getTime();
    }

    /**
     * 得到某一日期的上个月的开始日期
     * 
     * @param dateString 格式yyyy*MM*dd(2001*01*01)
     * @return (2001-01-01)
     */
    public static String getBeginningDateOfLastMonth(String dateString) {
        if (isNum(dateString.substring(4, 5))) {
            return BACKDATA;
        }
        String lastYear = getYeaterYear(dateString) + FLAG;
        String lastMonth = getUltimo(dateString);
        return MONTH_MAXNUM.equals(lastMonth) ? lastYear + lastMonth + FLAG + MONTH_MINNUM : dateString.substring(0, 4)
                + FLAG + lastMonth + FLAG + MONTH_MINNUM;
    }

    /**
     * 得到某个日期上一年的年份
     */
    public static String getYeaterYear(String dateString) {
        int currentYear = Integer.parseInt(dateString.substring(0, 4));
        return currentYear > 0 ? String.valueOf(currentYear - 1) : BACKDATA;
    }

    /**
     * 得到某个日期上月月份的结束日期
     */
    public static String getEndingDateOfLastMonth(String dateString) {
        String lastYear_month = getBeginningDateOfLastMonth(dateString).substring(0, 7);
        String lastYear = lastYear_month.substring(0, 4);
        String lastMonth = lastYear_month.substring(5, 7);
        String date = null;
        switch (Integer.parseInt(lastMonth)) {
            case 2 :
                int intValueOfYear = Integer.parseInt(lastYear);
                if ((intValueOfYear % 4 == 0) || (intValueOfYear % 100 != 0 && intValueOfYear % 400 == 0)) {
                    date = lastYear_month + FLAG + "29";
                }
                else {
                    date = lastYear_month + FLAG + "28";
                }
                break;
            case 4 :
            case 6 :
            case 9 :
            case 11 :
                date = lastYear_month + FLAG + "30";
                break;
            default :
                date = lastYear_month + FLAG + "31";
                break;
        }
        return date;
    }

    /**
     * 判断是否是数字
     * 
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        final String pattern1 = "\\d";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(str);
        if (!mat.find()) {
            return false;
        }
        return true;
    }

    /**
     * 得到某个日期上月的月份
     */
    public static String getUltimo(String dateString) {
        if (isNum(dateString.substring(4, 5))) {
            return BACKDATA;
        }
        int currentMonth = Integer.parseInt(dateString.substring(5, 7));
        return currentMonth > 10 ? String.valueOf(currentMonth - 1) : currentMonth == 1 ? MONTH_MAXNUM : ZERO
                + (currentMonth - 1);
    }

    /**
     * 得到某个日期上一季度的开始日期
     */
    public static String getBeginingDateOfLastSeason(String dateString) {
        String currentYear = dateString.substring(0, 4);
        String lastYear = getYeaterYear(dateString);
        String currentMonth = dateString.substring(5, 7);
        String beginningMonthOfLastSeason = getBeginningMonthOfLastSeason(dateString);
        String date = null;
        if ("01".equals(currentMonth) || "02".equals(currentMonth) || "03".equals(currentMonth)) {
            date = lastYear + FLAG + beginningMonthOfLastSeason + FLAG + "01";
        }
        else {
            date = currentYear + FLAG + beginningMonthOfLastSeason + FLAG + "01";
        }
        return date;
    }

    /**
     * 得到某个日期上一季度的结束日期
     */
    public static String getEndingDateOfLastSeason(String dateString) {
        String currentYear = dateString.substring(0, 4);
        String lastYear = getYeaterYear(dateString);
        String currentMonth = dateString.substring(5, 7);
        String endingMonthOfLastSeason = getEndingMonthOfLastSeason(dateString);
        String date = null;
        if ("01".equals(currentMonth) || "02".equals(currentMonth) || "03".equals(currentMonth)) {
            switch (Integer.parseInt(endingMonthOfLastSeason)) {
                case 2 :
                    int intValueOfYear = Integer.parseInt(lastYear);
                    if ((intValueOfYear % 4 == 0) || (intValueOfYear % 100 != 0 && intValueOfYear % 400 == 0)) {
                        date = lastYear + FLAG + endingMonthOfLastSeason + FLAG + "29";
                    }
                    else {
                        date = lastYear + FLAG + endingMonthOfLastSeason + FLAG + "28";
                    }
                    break;
                case 4 :
                case 6 :
                case 9 :
                case 11 :
                    date = lastYear + FLAG + endingMonthOfLastSeason + FLAG + "30";
                    break;
                default :
                    date = lastYear + FLAG + endingMonthOfLastSeason + FLAG + "31";
                    break;
            }
        }
        else {
            switch (Integer.parseInt(endingMonthOfLastSeason)) {
                case 2 :
                    int intValueOfYear = Integer.parseInt(currentYear);
                    if ((intValueOfYear % 4 == 0) || (intValueOfYear % 100 != 0 && intValueOfYear % 400 == 0)) {
                        date = currentYear + FLAG + endingMonthOfLastSeason + FLAG + "29";
                    }
                    else {
                        date = currentYear + FLAG + endingMonthOfLastSeason + FLAG + "28";
                    }
                    break;
                case 4 :
                case 6 :
                case 9 :
                case 11 :
                    date = currentYear + FLAG + endingMonthOfLastSeason + FLAG + "30";
                    break;
                default :
                    date = currentYear + FLAG + endingMonthOfLastSeason + FLAG + "31";
                    break;
            }
        }
        return date;
    }

    /**
     * 得到某个日期上一季度的起始月份
     */
    public static String getBeginningMonthOfLastSeason(String dateString) {
        String currentMonth = dateString.substring(5, 7);
        String monthOfLastSeason = null;
        switch (Integer.parseInt(currentMonth)) {
            case 1 :
            case 2 :
            case 3 :
                monthOfLastSeason = "10";
                break;
            case 4 :
            case 5 :
            case 6 :
                monthOfLastSeason = "01";
                break;
            case 7 :
            case 8 :
            case 9 :
                monthOfLastSeason = "04";
                break;
            default :
                monthOfLastSeason = "07";
                break;
        }
        return monthOfLastSeason;
    }

    /**
     * 得到某个日期上一季度的结束月份
     */
    public static String getEndingMonthOfLastSeason(String dateString) {
        String currentMonth = dateString.substring(5, 7);
        String monthOfLastSeason = null;
        switch (Integer.parseInt(currentMonth)) {
            case 1 :
            case 2 :
            case 3 :
                monthOfLastSeason = "12";
                break;
            case 4 :
            case 5 :
            case 6 :
                monthOfLastSeason = "03";
                break;
            case 7 :
            case 8 :
            case 9 :
                monthOfLastSeason = "06";
                break;
            default :
                monthOfLastSeason = "09";
                break;
        }
        return monthOfLastSeason;
    }

    public static final Date convertStringToDate(String aMask, String strDate) throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);
        try {
            date = df.parse(strDate);
        }
        catch (ParseException pe) {
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
        return (date);
    }

    public static int getDays(Date sd, Date ed) {
        return (int) ((ed.getTime() - sd.getTime()) / (3600 * 24 * 1000));
    }

    /***
     * 获取两个字符串日期（yyyy-MM-dd） 之间 日期字符，包括起始 字符 如 2011-07-01 2011-07-09 则返回 2011-07-01,2011-07-02..2011-07-09
     * 
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("deprecation")
    public static List<String> getDateList(String startDate, String endDate) throws ParseException {
        Date d1 = getDate("yyyy-MM-dd", startDate);
        Date d2 = getDate("yyyy-MM-dd", endDate);

        List<String> dateList = new ArrayList<String>();

        if (d1.after(d2)) {
            return null;
        }

        if (startDate.equals(endDate)) {
            dateList.add(endDate);
            return dateList;
        }

        Calendar c1 = Calendar.getInstance();
        boolean tempFlag = true;
        while (d1.before(d2) || tempFlag) {

            if (d1.after(d2)) {
                tempFlag = false;
            }
            dateList.add(getDate("yyyy-MM-dd", d1));

            c1.set(Calendar.YEAR, d1.getYear() + 1900);
            c1.set(Calendar.MONTH, d1.getMonth());
            c1.set(Calendar.DATE, d1.getDate());
            c1.add(Calendar.DATE, 1);
            d1 = c1.getTime();
        }
        return dateList;

    }

    /***
     * 根据提供的队列和 日期字符串 获取数据索引需要保存的索引
     * 
     * @param dataList 日期队列
     * @param dateStr 日期字符串
     * @return 此日期字符串保存的位置索引
     * @throws ParseException
     */

    public static int getDateIndex(List<String> dataList, String dateStr) throws ParseException {
        int dateIndex = 0;
        if (dataList == null || dataList.size() == 0) {
            return dateIndex;
        }
        else {
            Date curretDate = ReportDateUtil.getDate("yyyy-MM-dd", dateStr.substring(0, 10));
            for (String str : dataList) {
                Date ad = ReportDateUtil.getDate("yyyy-MM-dd", str.substring(0, 10));

                if (dateStr.length() == 10) {
                    if (curretDate.before(ad)) {
                        break;
                    }
                }
                else {
                    String scurrent = str.substring(10);
                    String current = dateStr.substring(10);
                    if ((Integer.parseInt(current) < Integer.parseInt(scurrent) && curretDate.compareTo(ad) == 0)
                            || curretDate.before(ad)) {
                        break;
                    }
                }
                dateIndex++;
            }
        }
        return dateIndex;
    }

    /***
     * 更具时间类型不同，返回间隔时间
     * 
     * @param divideType
     * @param dateLength
     * @return
     */
    public static int getDivideLength(String divideType, int dateLength) {
        switch (Integer.parseInt(divideType)) {
            case 1 :
            case 2 : // 昨天 1
            case 3 : // 最近7 天 1
                return 1;
            case 4 : // 最近30 天
                return 9;
            case 5 : // 上一个月 4
                return 9;
            case 6 : // 上一个季度 14
                return 15;
            case 7 : // 本月开始
                if (dateLength <= 10) {
                    return 1;
                }
                else if (dateLength <= 15) {
                    return 2;
                }
                else {
                    return 4;
                }
            case 8 : // 本季开始
                if (dateLength <= 10) {
                    return 1;
                }
                else if (dateLength <= 45) {
                    return 7;
                }
                else {
                    return 15;
                }
            case 9 : // 今天已统计
                return 1;
            case 10 : // 最近90天
                return 15;
            case 11 : // 最近180天
                return 30;
            case 12 : // 最近一年
                return 60;
            default :
                return 1;
        }

        /*
         * 9 今天已统计 1 8 本季开始 7 or14 | 45 7 本月开始 2 or 4 | 15 6 上一个季度 14 5 上一个月 4 4 最近30 天 3 最近7 天 1 2 昨天 1
         */
    }

    public static void main(String[] args) throws ParseException {
        /*
         * String date = getTomorrowDate("yyyy-MM-dd", -1);// 得到今天0，昨天-1，过去7天-7，过去30天-30 String startDate =
         * getBeginningDateOfLastMonth(getDate("yyyy-MM-dd", new Date()));// 上一个月start String endDate =
         * getEndingDateOfLastMonth(getDate("yyyy-MM-dd", new Date()));// 上一个月end String lastStartQuarter =
         * getBeginingDateOfLastSeason(getDate("yyyy-MM-dd", new Date()));// 上一个季度start String lastEndQuarter =
         * getEndingDateOfLastSeason(getDate("yyyy-MM-dd", new Date()));// 上一个季度end String startMonth =
         * getStartMonthDate(getDate("yyyy-MM-dd", new Date()));// 本月开始 String startQuarter = getStratQuarter();// 本季开始
         * System.out.println("今天，昨天，后7天,过去30天:  " + date); System.out.println("上一个月start: " + startDate +
         * "   上一个月end: " + endDate); System.out.println("上一个季度start:  " + lastStartQuarter + "  上一个季度end: " +
         * lastEndQuarter); System.out.println("本月开始: " + startMonth + "  现在：" + getDate("yyyy-MM-dd", new Date()));
         * System.out.println("本季开始: " + startQuarter + "  现在：" + getDate("yyyy-MM-dd", new Date())); Date d1 =
         * getDate("yyyy-MM-dd", "2010-03-01"); Date d2 = getDate("yyyy-MM-dd", "2010-03-05");
         * System.out.println(getDays(d1, d2));
         * System.out.println(getTomorrowDate("yyyy-MM-dd",getDate("yyyy-MM-dd","2012-02-16"),-15));;
         */

        // List<String> a = new ArrayList<String>();
        // a.add("2011-07-211");
        // a.add("2011-07-262");
        // a.add("2011-07-293");
        System.out.println(2);
        // System.out.println(getDate("h",getDate("yyyy-MM-dd", "2011-07-02")));
    }

}
