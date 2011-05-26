package com.ivyinfo.framework.common.tools;

import com.ivyinfo.framework.common.exception.IvyinfoException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**  
 * 与系统相关的一些常用工具方法.  
 *   
 * @author 张砚
 * @version 1.0.0  
 */
public class SystemTools {

    /** User home dir. */
    public static final String userHome = System.getProperty("user.home");
    /** OS dependent file separator. */
    public static final String fileSeparator = System.getProperty("file.separator");
    /** OS dependent line terminator. */
    public static final String lineTerminator = getSystemLineTerminator();
    /** Java 6 Update 10 or later? (but not Java 7 yet). */
    public static final boolean isJava6Update10OrLater = isJava6Update10OrLater();
    public static final boolean IS_JAVA_6_OR_GREATER = System.getProperty("java.version").compareTo("1.6") >= 0;

    /**
     * 获取当前操作系统名称.  
     * return 操作系统名称 例如:windows xp,linux 等.  
     */
    public static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    /**
     * Returns <code>true</code> if the current operating system (actually
     * the VM) is 64 bit.
     *
     * @return If the current operating system is 64 bit
     */
    public boolean is64Bit() {
        return System.getProperty("os.arch").contains("64");
    }

    /**
     * Checks if java 6 update 10 or later is used.
     *
     * @return true, if java 6 update 10 or later is used
     */
    private static boolean isJava6Update10OrLater() {
        String versionString = System.getProperty("java.version");
        Pattern pattern = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)_(\\d+).*");
        Matcher matcher = pattern.matcher(versionString);
        if (matcher.find()) {
            try {
                int v1 = Integer.parseInt(matcher.group(1));
                int v2 = Integer.parseInt(matcher.group(2));
                int v3 = Integer.parseInt(matcher.group(3));
                int update = Integer.parseInt(matcher.group(4));
                return v1 == 1 && v2 == 6 && v3 >= 0 && update >= 10;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Gets the system line terminator.
     *
     * @return the system line terminator
     */
    private static String getSystemLineTerminator() {
        if (getOSName() == "WINDOWS") {
            return "\r\n";
        }
        return "\n";
    }

    /**  
     * 获取unix网卡的mac地址.  
     * 非windows的系统默认调用本方法获取.如果有特殊系统请继续扩充新的取mac地址方法.  
     * @return mac地址  
     */
    public static String getUnixMACAddress() throws IvyinfoException {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ifconfig eth0");// linux下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息   
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                index = line.toLowerCase().indexOf("hwaddr");// 寻找标示字符串[hwaddr]   
                if (index >= 0) {// 找到了   
                    mac = line.substring(index + "hwaddr".length() + 1).trim();//  取出mac地址并去除2边空格
                    break;
                }
            }
        } catch (IOException e) {
            throw new IvyinfoException("获取错误：" + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                throw new IvyinfoException("获取错误：" + e1.getMessage());
            }
            bufferedReader = null;
            process = null;
        }

        return mac;
    }

    /**  
     * 获取widnows网卡的mac地址.  
     * @return mac地址  
     */
    public static String getWindowsMACAddress() throws IvyinfoException {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ipconfig /all");// windows下的命令，显示信息中包含有mac地址信息   
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                index = line.toLowerCase().indexOf("physical address");// 寻找标示字符串[physical address]   
                if (index >= 0) {// 找到了   
                    index = line.indexOf(":");// 寻找":"的位置   
                    if (index >= 0) {
                        mac = line.substring(index + 1).trim();//  取出mac地址并去除2边空格   
                    }
                    break;
                }
            }
        } catch (IOException e) {
            throw new IvyinfoException("获取错误：" + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                throw new IvyinfoException("获取错误：" + e1.getMessage());
            }
            bufferedReader = null;
            process = null;
        }

        return mac;
    }

    /**
     * 运行控制台命令，返回结果字符串
     * @param command String
     * @return String
     * @throws IOException
     */
    private String runConsoleCommand(String command) throws IOException {
        Process p = Runtime.getRuntime().exec(command);
        InputStream stdoutStream = new BufferedInputStream(p.getInputStream());

        StringBuffer buffer = new StringBuffer();
        while (true) {
            int c = stdoutStream.read();
            if (c == -1) {
                break;
            }
            buffer.append((char) c);
        }
        String outputText = buffer.toString();

        stdoutStream.close();

        return outputText;
    }

    /**
     * 清除缓存
     */
    public void gc() {
        List list = new LinkedList();
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        while (totalMemory == runtime.totalMemory()) {
            list.add("mongo bongo");
            if (list.size() % 400 == 0) {
                System.gc();
            }
        }
    }

    // 将127.0.0.1 形式的IP地址转换成10进制整数，这里没有进行任何错误处理
    public static long ipToLong(String strIP) {
        long[] ip = new long[4];
        // 先找到IP地址字符串中.的位置
        int position1 = strIP.indexOf(".");
        int position2 = strIP.indexOf(".", position1 + 1);
        int position3 = strIP.indexOf(".", position2 + 1);
        // 将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(strIP.substring(0, position1));
        ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strIP.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    /**
     * 判断ip地址的合法性
     */
    public static boolean isValidIPAddress(String str) {
        String temp = "";
        int tag = 0;
        //字符串的第一位和最后以为如果是.的话返回false
        if (str.charAt(0) == '.' || str.charAt(str.length() - 1) == '.') {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            System.out.println("temp ===" + temp);
            if (str.charAt(i) == '.') {
                System.out.println("tag1 === " + tag);
                tag++;
                System.out.println("tag2 === " + tag);

                if (Integer.parseInt(temp) > 255) {
                    return false;
                }
                temp = "";
                continue;
            }
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
            temp += String.valueOf(str.charAt(i));
        }
        System.out.println("tag3 ==" + tag);
        if (tag != 3) {
            return false;
        }
        return true;
    }

    public static int isInnerIP(long a_ip)// 检查ip地址是否是内网ip
    {
        int bValid = -1;
        if ((a_ip >> 24 == 0xa) || (a_ip >> 16 == 0xc0a8)
                || (a_ip >> 22 == 0x2b0)) {
            bValid = 0;
        }
        return bValid;
    }

    /**  
     * 测试用的main方法.  
     *   
     * @param argc  
     *            运行参数.  
     */
    public static void main(String[] argc) {
//        String os = getOSName();
//        System.out.println(os);
//        if (os.startsWith("windows")) {
//            //本地是windows
//            //String mac = getWindowsMACAddress();
//            //System.out.println(mac);
//        } else {
//            //本地是非windows系统 一般就是unix
////            String mac = getUnixMACAddress();
////            System.out.println(mac);
//        }
    }

}  

