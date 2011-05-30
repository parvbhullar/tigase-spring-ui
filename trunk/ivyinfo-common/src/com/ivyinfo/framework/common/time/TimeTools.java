package com.ivyinfo.framework.common.time;

import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * 有关时间日期的类
 * User: 张砚
 * Date: 2005-6-8
 * Time: 11:40:21
 */
public class TimeTools extends TimeZonesHandle {

    /**
     * Description: 得到当前天的小时数
     *
     * @return String
     */
    public static String getHour() {
        DecimalFormat df = new DecimalFormat("00");
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return (df.format(hour));
    }

    /**
     * Description: 得到当前天的小时内的分钟数
     *
     * @return String
     */
    public static String getMinute() {
        DecimalFormat df = new DecimalFormat("00");
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        return (df.format(minute));
    }

    /**
     * Description: 得到当前分钟内的秒数
     *
     * @return String
     */
    public static String getSecond() {
        DecimalFormat df = new DecimalFormat("00");
        int second = Calendar.getInstance().get(Calendar.SECOND);
        return (df.format(second));
    }

    /**
     * Description: 得到当前的毫秒数
     *
     * @return String
     */
    public static String getMilliSecond() {
        String result = "";

        int temp = Calendar.getInstance().get(Calendar.MILLISECOND);
        result = Integer.toString(temp);

        if (temp < 100 && temp >= 10) {
            result = "0" + result;
        } else {
            if (temp < 10) {
                result = "00" + result;
            }
        }

        return (result);
    }

    /*Description: 获得时-分-秒
     *@return String
     *@throws
     */
    public static String getTime() {
        return (getHour() + ":" + getMinute() + ":" + getSecond());
    }

    /*Description: 获得时.分
     *@return String
     *@throws
     */
    public static String getTime_state() {
        return (getHour() + "." + getMinute());
    }

    /**
     * getCurrentTime得到当前时间
     * @return
     */
    public static String getCurrentTime() {
        String datestr = "";
        try {
            java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            datestr = df.format(new java.util.Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datestr;
    }

    /**
     * Description: 获得当前的Timestamp
     *
     * @return
     */
    public static Timestamp getTimestamp() {
        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = 1 + cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        int milliSecond = cal.get(Calendar.MILLISECOND);
        return (Timestamp.valueOf(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second + "." + milliSecond));
    }

    /**
     * @return
     */
    public static String getString() {
        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = 1 + cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        DecimalFormat df = new DecimalFormat("00");

        return (String.valueOf(year) + String.valueOf(df.format(month)) + String.valueOf(df.format(day)) + String.valueOf(df.format(hour)) + String.valueOf(minute) + String.valueOf(df.format(second)));
    }

    /**
     * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
     */
    public static String getTwoHour(String st1, String st2) {
        String[] kk = null;
        String[] jj = null;
        kk = st1.split(":");
        jj = st2.split(":");
        if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0])) {
            return "0";
        } else {
            double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1])
                    / 60;
            double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1])
                    / 60;
            if ((y - u) > 0) {
                return y - u + "";
            } else {
                return "0";
            }
        }
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public static String getPreTime(String sj1, String jj) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            java.util.Date date1 = format.parse(sj1);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
        }
        return mydate1;
    }

    /**
     * 转换取时间格式
     * @param aDate
     * @return
     */
    public static final String getDateTime(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }
    /**
     * webcall系统需要的时间格式
     * @param aDate
     * @return
     */
    public static final String getDateTime_webCall() {
        SimpleDateFormat df = null;
        String returnValue = "";
        Date aDate = new Date();
        if (aDate != null) {
            df = new SimpleDateFormat("yyyyMMddHHmmss");
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * 由String类型的yyyy-MM-dd HH:mm:ss格式输出Date类型的yyyy-MM-dd 格式的时间
     * <p>
     * @param  string
     * @return Date
     * @exception
     *
     */
    public static Date getDateFormatYMDHMSString(String sourcedate) {
        if (sourcedate == null) {
        	//System.out.println("null sourcedate");
            return null;
        }
        Date newDate = new Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            newDate = sdf.parse(sourcedate);

        } catch (Exception e) {
        	//System.out.println("exception!!!!!");
            return null;
        }
        /*if(newDate!=null)
        	System.out.println("new date: " + newDate.toString());
        else
        	System.out.println("null new date");*/
        return newDate;
    }
    
    /**
     * 带毫秒的时间戳  20101102152030265
     * @return
     */
    public static String getTimestampMilliSecond(){
    	return getDateTime_webCall()+getMilliSecond();
    }
}

