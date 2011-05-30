package com.ivyinfo.framework.common.time;

import com.ivyinfo.framework.common.exception.IvyinfoException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

/**
 * 日期公用处理类
 */
public class DateTools {

    /**
     * 解析一个日期段之间的所有日期
     *
     * @param beginDateStr
     *            开始日期
     * @param endDateStr
     *            结束日期
     * @return
     */
    public static ArrayList getDayList(String beginDateStr, String endDateStr) throws IvyinfoException {
        // 指定要解析的时间格式
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        // 定义一些变量
        Date beginDate = null;
        Date endDate = null;

        Calendar beginGC = null;
        Calendar endGC = null;
        ArrayList list = new ArrayList();
        try {
            // 将字符串parse成日期
            beginDate = f.parse(beginDateStr);
            endDate = f.parse(endDateStr);
            // 设置日历
            beginGC = Calendar.getInstance();
            beginGC.setTime(beginDate);

            endGC = Calendar.getInstance();
            endGC.setTime(endDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 直到两个时间相同
            while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {
                list.add(sdf.format(beginGC.getTime()));
                // 以日为单位，增加时间
                beginGC.add(Calendar.DAY_OF_MONTH, 1);
            }
            return list;
        } catch (Exception e) {
            throw new IvyinfoException("解析日期错误：" + e.getMessage());
        }
    }

    /**
     * 解析一个日期之间的所有月份
     * @param beginDateStr
     * @param endDateStr
     * @return
     */
    public static ArrayList getMonthList(String beginDateStr, String endDateStr) throws IvyinfoException {
        // 指定要解析的时间格式
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
        // 返回的月份列表
        String sRet = "";
        // 定义一些变量
        Date beginDate = null;
        Date endDate = null;

        GregorianCalendar beginGC = null;
        GregorianCalendar endGC = null;
        ArrayList list = new ArrayList();
        try {
            // 将字符串parse成日期
            beginDate = f.parse(beginDateStr);
            endDate = f.parse(endDateStr);
            // 设置日历
            beginGC = new GregorianCalendar();
            beginGC.setTime(beginDate);

            endGC = new GregorianCalendar();
            endGC.setTime(endDate);
            // 直到两个时间相同
            while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {
                sRet = beginGC.get(Calendar.YEAR) + "-"
                        + (beginGC.get(Calendar.MONTH) + 1);
                list.add(sRet);
                // 以月为单位，增加时间
                beginGC.add(Calendar.MONTH, 1);
            }
            return list;
        } catch (Exception e) {
            throw new IvyinfoException("解析日期错误：" + e.getMessage());
        }
    }

    /**
     * 获取年列表，根据当前年份，向前 -5年，向后 +10年，共16条数据
     * @return
     */
    public static ArrayList getYearList() throws IvyinfoException {
        ArrayList list = new ArrayList();
        try {
            Calendar c = null;
            c = Calendar.getInstance();
            c.setTime(new Date());
            int currYear = Calendar.getInstance().get(Calendar.YEAR);

            int startYear = currYear - 5;
            int endYear = currYear + 10;
            for (int i = startYear; i < endYear; i++) {
                list.add(new Integer(i));
            }
        } catch (Exception e) {
            throw new IvyinfoException("获取年列表出错：" + e.getMessage());
        }
        return list;
    }

    /**
     * 得到某一年周的总数
     *
     * @param year
     * @return
     */
    public static LinkedHashMap getWeekList(int year) {
        LinkedHashMap map = new LinkedHashMap();
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        int count = getWeekOfYear(c.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dayOfWeekStart = "";
        String dayOfWeekEnd = "";
        for (int i = 1; i <= count; i++) {
            dayOfWeekStart = sdf.format(getFirstDayOfWeek(year, i));
            dayOfWeekEnd = sdf.format(getLastDayOfWeek(year, i));
            map.put(new Integer(i), "第" + i + "周(从" + dayOfWeekStart + "至" + dayOfWeekEnd + ")");
        }
        return map;

    }

    /**
     * Description:获得系统当前的年份
     *
     * @return String
     */
    public static String getYear() {
        return (Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
    }

    /**
     * Description:获得系统当前的月份
     *
     * @return String
     */
    public static String getMonth() {
        DecimalFormat df = new DecimalFormat("00");
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        return (df.format(month));
    }

    /**
     * Description: 得到当前的天数（指在当前的月的天数）
     *
     * @return String
     */
    public static String getDay() {
        DecimalFormat df = new DecimalFormat("00");
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return (df.format(day));
    }

    /**
     * 获取当前星期几
     * @return
     */
    public static String getWeek() {
        int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String[] weeks = {"日", "一", "二", "三", "四", "五", "六"};
        return (weeks[week - 1]);
    }

    /**
     * 得到一年的总周数
     * @param year
     * @return
     */
    public static int getWeekCountInYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        int count = getWeekOfYear(c.getTime());
        return count;
    }

    /**
     * 取得当前日期是当年的第多少周
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    /**
     * 得到某年某月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirestDayOfMonth(int year, int month) {
        month = month - 1;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);

        int day = c.getActualMinimum(c.DAY_OF_MONTH);

        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTime();

    }

    /**
     * 提到某年某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        month = month - 1;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        int day = c.getActualMaximum(c.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    /*获得和指定日期相差distance的日期字符串
     *@param  strDate：指定的日期字符串   distance：相差的天数，在指定日期之前用负数表示
     *@return String 日期字符串
     *@throws              Exception
     */
    public static String getOneDate(String strDate, int distance) {
        Calendar cal = new GregorianCalendar();
        java.util.StringTokenizer stk = new StringTokenizer(strDate, "-", false);
        if (stk.countTokens() != 3) {
            return null;
        }
        int[] date = new int[3];
        int i = 0;
        while (stk.hasMoreTokens()) {
            String tmp = stk.nextToken().trim();
            date[i] = Integer.parseInt(tmp);
            i++;
        }

        cal.set(date[0], date[1] - 1, date[2], 0, 0, 0);
        cal.add(GregorianCalendar.DATE, distance);
        java.util.Date d = cal.getTime();
        return (DateFormat.getDateInstance().format(d));
    }

    /**
     * 得到二个日期间的间隔天数
     */
    public static String getTwoDay(String sj1, String sj2) throws IvyinfoException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            java.util.Date date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            throw new IvyinfoException(e.getMessage());
        }
        return day + "";
    }

    /**
     * 判断时间date1是否在时间date2之前,时间格式 2005-4-21 16:16:34
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isDateBefore(String date1, String date2) throws IvyinfoException {
        try {
            DateFormat df = DateFormat.getDateTimeInstance();
            return df.parse(date1).before(df.parse(date2));
        } catch (ParseException e) {
            throw new IvyinfoException(e.getMessage());
        }
    }

    /**
     * 判断当前时间是否在时间date2之前,时间格式 2005-4-21 16:16:34
     * @param date2
     * @return
     */
    public static boolean isDateBefore(String date2) throws IvyinfoException {
        try {
            Date date1 = new Date();
            DateFormat df = DateFormat.getDateTimeInstance();
            return date1.before(df.parse(date2));
        } catch (ParseException e) {
            throw new IvyinfoException(e.getMessage());
        }
    }

    /**
     * Description:获得指定日期所在月的天数
     *
     * @param date
     * @return 月份的天数
     */
    public static int getDaysOfMonth(String date) throws IvyinfoException {
        int days = 0;
        GregorianCalendar currentDay = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            currentDay.setTime(sdf.parse(date));
            int month = currentDay.get(Calendar.MONTH);
            int year = currentDay.get(Calendar.YEAR);
            Calendar thisMonth = Calendar.getInstance();
            thisMonth.set(Calendar.MONTH, month);
            thisMonth.set(Calendar.YEAR, year);
            thisMonth.set(Calendar.DAY_OF_MONTH, 1);
            days = thisMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            throw new IvyinfoException(e.getMessage());
        }
        return (days);
    }

    /**
     * 将字符型日期转成Date型
     * @param dateStr
     * @return
     */
    public static Date parseStringToDate(String dateStr) throws IvyinfoException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            throw new IvyinfoException(e);
        }
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static java.util.Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        java.util.Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static void main(String[] args) {
        System.err.println(getOneDate("2009-06-28", 3));
    }
}
