package com.mitian.airad.common.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * IpUtil.java
 * 
 * @author baojun
 */
public abstract class IpUtil {

    private static final Pattern IP_PATTERN =
            Pattern
                    .compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");

    private static Logger logger = Logger.getLogger(IpUtil.class);

    /**
     * 工具类无法实例化
     */
    private IpUtil() {
        super();
    }

    /**
     * 检验是否合法IP
     * 
     * @param ipAddress
     * @return
     */
    public static boolean isValidIp(String ipAddress) {
        return IP_PATTERN.matcher(ipAddress).matches();
    }

    /**
     * 字符串ip转换为long
     * 
     * @param 字符串ip
     * @return
     */
    private static long getStringIpToLong(String ip) {
        String[] ips = ip.split("[.]");
        long num =
                16777216L * Long.parseLong(ips[0]) + 65536L * Long.parseLong(ips[1]) + 256 * Long.parseLong(ips[2])
                        + Long.parseLong(ips[3]);
        return num;
    }

    /**
     * 长整型ip转换为string
     * 
     * @param long型ip
     * @return
     */
    public static String getLongIpToString(long ipLong) {

        long[] mask = {0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000};
        long num = 0;
        StringBuffer ipInfo = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            num = (ipLong & mask[i]) >> (i * 8);
            if (i > 0) {
                ipInfo.insert(0, ".");
            }
            ipInfo.insert(0, Long.toString(num, 10));
        }
        return ipInfo.toString();
    }

    /**
     * 通过配置文件来过滤id
     * 
     * @param ip
     * @param filePatch
     * @return
     */
    public static boolean isFromChina(String ip, String filePatch) {
        String ipTrim = StringUtils.trimToEmpty(ip);
        if (!isValidIp(ipTrim)) {
            return false;
        }
        long userIp = getStringIpToLong(ipTrim);
        Map<String, List> map = getIpCogfigMap(filePatch);
        List<Long> sta = map.get("str");
        List<Long> end = map.get("end");
        for (int i = 0; i < sta.size(); i++) {
            if (userIp >= sta.get(i)) {
                if (userIp <= end.get(i)) {
                    return true;
                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * 读取文件中的ip地址
     * 
     * @param filePatch
     * @return
     */
    private static Map<String, List> ipCache;
    private static final Object LOCK = new Object();

    public static Map<String, List> getIpCogfigMap(String filePatch) {
        if (ipCache == null) {
            synchronized (LOCK) {
                if (ipCache == null) {
                    initCacheMap(filePatch);
                }
            }
        }
        return ipCache;

    }

    public static void initCacheMap(String filePatch) {
        try {
            // 配置文件路径
            List<Long> stl = new ArrayList<Long>();
            List<Long> enl = new ArrayList<Long>();
            // read file content from file
            FileReader reader = new FileReader(filePatch);
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            while ((str = br.readLine()) != null) {
                String[] ipArry = str.split("-");
                long src1 = getStringIpToLong(ipArry[0].trim());
                long src2 = getStringIpToLong(ipArry[1].trim());
                stl.add(src1);
                enl.add(src2);
            }
            ipCache = new HashMap<String, List>();
            Collections.sort(stl);
            Collections.sort(enl);
            ipCache.put("str", stl);
            ipCache.put("end", enl);

            IOUtils.closeQuietly(br);
            IOUtils.closeQuietly(reader);
        }
        catch (IOException e) {
            logger.error("init cacheMap error ", e);
            ipCache = new HashMap<String, List>();
        }
    }

    /**
     * 获取真实ip
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 应对x-forwarded-for 中返回多个服务器ip的情况
        if (StringUtils.isNotBlank(ip) && ip.split(",").length > 1) {
            String realIp = ip.split(",")[0];
            logger.warn("this ip:" + realIp + " is cut from origin ips:" + ip);
            ip = realIp;
        }
        return ip;
    }

    public static void main(String[] arg) {
        // isFromChina("27.128.0.5", "D:\\cn_ip_seg.properties");
        System.out.println(IpUtil.isValidIp("127.0.0.1"));
        System.out.println(IpUtil.isValidIp("255.7.7.2"));
        System.out.println(IpUtil.isValidIp("444.0.0.1"));
        System.out.println(IpUtil.isValidIp("100.4.354.22"));
        System.out.println(IpUtil.isValidIp("127.0.0.1222222"));
        System.out.println(IpUtil.isValidIp("127.0.022222.1"));
        System.out.println(IpUtil.isValidIp("127.0.044.1"));
        System.out.println(IpUtil.isValidIp(""));
    }
}
