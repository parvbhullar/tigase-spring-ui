/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivyinfo.framework.common.tools;

import com.ivyinfo.framework.common.exception.IvyinfoException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
public class TranTypeTools {

    /**
     * Description: 将字符串型变量转换为日期型变量
     *
     * @param source
     * @return Date型
     */
    public static Date tranStringToDate(String source) throws IvyinfoException {
        Date revalue = null;
        try {
            revalue = Date.valueOf(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to Date ");
        }
        return (revalue);
    }

    /**
     * Description: 将字符串型变量转换为Double型对象
     *
     * @param source
     * @return Double
     */
    public static Double tranStringToDouble(String source) throws IvyinfoException {
        Double revalue = null;
        try {
            revalue = new Double(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to Double ");
        }
        return (revalue);
    }

    /**
     * Description:将字符串型变量转换为Float型对象
     *
     * @param source
     * @return Float
     */
    public static Float tranStringToFloat(String source) throws IvyinfoException {
        Float revalue = null;
        try {
            revalue = new Float(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to Float ");
        }
        return (revalue);
    }

    /**
     * Description:将字符串型变量转换为Integer对象
     *
     * @param source
     * @return Integer
     */
    public static Integer tranStringToInteger(String source) throws IvyinfoException {
        Integer revalue = null;
        try {
            revalue = new Integer(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to Integer ");
        }
        return (revalue);
    }

    /**
     * Description:将字符串型变量转换为Short对象
     *
     * @param source
     * @return Short
     */
    public static Short tranStringToShort(String source) throws IvyinfoException {
        Short revalue = null;
        try {
            revalue = new Short(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to Short ");
        }
        return (revalue);
    }

    /**
     * @param source
     * @return
     */
    public static Long tranStringToLong(String source) throws IvyinfoException {
        Long revalue = null;
        try {
            revalue = new Long(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to Long ");
        }
        return (revalue);
    }

    /**
     * Description: 将字符串变量转换为Timestamp对象
     *
     * @param source
     * @return Timestamp
     */
    public static Timestamp tranStringToTimestamp(String source) throws IvyinfoException {
        Timestamp revalue = null;
        try {
            revalue = Timestamp.valueOf(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to Timestamp ");
        }
        return (revalue);
    }

    /**
     * Description: 将字符串变量转换为Time
     *
     * @param source
     * @return Time
     */
    public static Time tranStringToTime(String source) throws IvyinfoException {
        Time revalue = null;
        try {
            revalue = Time.valueOf(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to Time ");
        }
        return (revalue);
    }

    /**
     * Description: 将字符串变量转换为BigDecimal
     *
     * @param source
     * @return Time
     */
    public static BigDecimal tranStringToBigDecimal(String source) throws IvyinfoException {
        BigDecimal revalue = null;
        try {
            revalue = new BigDecimal(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to BigDecimal ");
        }
        return (revalue);
    }

    /**
     * 将整数字符串转化为int
     *
     * @param source
     * @return
     */
    public static int parseStringToInt(String source) throws IvyinfoException {
        int revalue = 0;
        try {
            revalue = Integer.parseInt(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to int ");
        }
        return (revalue);
    }

    /**
     * 将整数字符串转化为int
     *
     * @param source
     * @return
     */
    public static int parseStringToInt(String source, int defValue) throws IvyinfoException {
        int revalue = 0;
        if (StringTools.trim(source).equals("")) {
            return defValue;
        }
        try {
            revalue = Integer.parseInt(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to int ");
        }
        return (revalue);
    }

    /**
     * 将浮点数字符串转化为double
     *
     * @param source
     * @return
     */
    public static double parseStringToDouble(String source) throws IvyinfoException {
        double revalue = 0.00;
        try {
            revalue = Double.parseDouble(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to double ");
        }
        return (revalue);
    }

    /**
     * @param source
     * @return
     */
    public static float parseStringToFloat(String source) throws IvyinfoException {
        float revalue = 0;
        try {
            revalue = Float.parseFloat(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to float ");
        }
        return (revalue);
    }

    /**
     * @param source
     * @return
     */
    public static short parseStringToShort(String source) throws IvyinfoException {
        short revalue = 0;
        try {
            revalue = Short.parseShort(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to short ");
        }
        return (revalue);
    }

    /**
     * @param source
     * @return
     */
    public static long parseStringToLong(String source) throws IvyinfoException {
        long revalue = 0;
        try {
            revalue = Long.parseLong(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to long ");
        }
        return (revalue);
    }

    /**
     * @param source
     * @return
     */
    public static boolean parseStringToBoolean(String source) throws IvyinfoException {
        boolean revalue = false;
        try {
            revalue = Boolean.getBoolean(source);
        } catch (Exception e) {
            throw new IvyinfoException("can not tran:" + source + " to Boolean ");
        }
        return (revalue);
    }

    /**
     * Description: 解决链接中传过的中文字符乱码的问题
     *
     * @param value 需要转换的字符
     * @return String 返回的结果
     */
    public static String tranISO8859ToUTF8(String value) throws IvyinfoException {
        if (value != null) {
            try {
                value = new String(value.getBytes("iso-8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new IvyinfoException("转换出错:" + e.getMessage());
            }
        }
        return (StringTools.trim(value));
    }

    public static String tranISO8859toGBK(String str) throws IvyinfoException {
        try {
            //str = new String(str.getBytes("GBK"), "ISO8859-1");
            str = new String(str.getBytes("ISO8859-1"), "GBk");
            str = str.replaceAll("'", "\"");
        } catch (Exception e) {
            throw new IvyinfoException("转换出错:" + e.getMessage());
        }

        return str;
    }

    public static String tranGBKtoISO(String str) throws IvyinfoException {
        try {
            str = new String(str.getBytes("GBK"), "ISO8859-1");
            //str=new String(str.getBytes("ISO8859-1"),"GBk");
        } catch (Exception e) {
            throw new IvyinfoException("转换出错:" + e.getMessage());
        }

        return str;
    }

    /**
     * 对字符作中文的转换
     * @param s
     * @return
     */
    public static String toChinese(String s) throws IvyinfoException {
        try {
            if (s == null || s.equals("")) {
            } else {
                byte abyte0[] = s.getBytes("iso8859-1");
                for (int i = 0; i < abyte0.length; i++) {
                    if (abyte0[i] + 0 < 0) {
                        return new String(abyte0, "GBK");
                    }
                }

                abyte0 = s.getBytes("GBK");
                for (int j = 0; j < abyte0.length; j++) {
                    if (abyte0[j] + 0 < 0) {
                        return new String(abyte0, "GBK");
                    }
                }

            }

        } catch (Exception e) {
            throw new IvyinfoException("转换出错:" + e.getMessage());
        }
        return s;
    }
    
    private static final int MASKBITS = 0x3F;
    private static final int MASKBYTE = 0x80;
    private static final int MASK2BYTES = 0xC0;
    private static final int MASK3BYTES = 0xE0;
    //private static final int MASK4BYTES = 0xF0;
    //private static final int MASK5BYTES = 0xF8;
    //private static final int MASK6BYTES = 0xFC;

    /** */
    /**
     * @功能: 将UTF-8编码转成UNICODE（UTF-16LE）编码
     * @参数: byte[] b 源字节数组
     * @返回值: byte[] b 转为UNICODE编码后的数组
     * @作者: imuse
     * @MAIL: postzhu@hotmail.com
     */
    public static byte[] UTF8_TO_UNICODE(byte[] b) {
        int i = 0;
        int j = 0;
        byte[] unicodeByte = new byte[b.length * 2];
        while (i < b.length) {
            byte[] bUnicode = new byte[2];
            bUnicode[0] = bUnicode[1] = 0;
            int nUnicode = 0;
            if ((b[i] & MASK3BYTES) == MASK3BYTES) {
                nUnicode = ((b[i] & 0x0F) << 12) | ((b[i + 1] & MASKBITS) << 6)
                        | (b[i + 2] & MASKBITS);
                i += 3;
            } // 110xxxxx 10xxxxxx
            else if ((b[i] & MASK2BYTES) == MASK2BYTES) {
                nUnicode = ((b[i] & 0x1F) << 6) | (b[i + 1] & MASKBITS);
                i += 2;
            } // 0xxxxxxx
            else if (b[i] < MASKBYTE) {
                nUnicode = b[i];
                i += 1;
            }
            unicodeByte[j++] = (byte) (nUnicode & 0xFF);
            unicodeByte[j++] = (byte) ((nUnicode >> 8) & 0xFF);
        }
        b = new byte[j];
        System.arraycopy(unicodeByte, 0, b, 0, j);
        return b;
    }
}
