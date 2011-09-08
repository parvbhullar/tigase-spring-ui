/**
 * $Id: CryptoUtils.java
 */
package com.mitian.airad.common.utils;

import java.util.Calendar;

import org.springframework.util.Assert;

/**
 * 数字加解密
 */
public class CryptoUtils {

    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 加密
     */
    private static String alphabet0(long input, int size) {
        Assert.isTrue(input > 0, "num must be greater than 0.");

        StringBuilder sb = new StringBuilder();
        for (; input > 0; input /= size) {
            sb.append(ALPHABET.charAt((int) (input % size)));
        }

        return sb.toString();
    }

    /**
     * 解密
     */
    private static long alphabet1(String str, int size) {
        Assert.hasText(str);

        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            result += ALPHABET.indexOf(str.charAt(i)) * Math.pow(size, i);
        }

        return result;
    }

    public static <T> String encrypt(T input) {
        return alphabet0(Long.parseLong(input.toString()) + 1, 62);
    }

    /**
	 */
    public static <T> long decrypt(T input) {
        return alphabet1(input.toString(), 62) - 1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            // System.out.println(i+" - "+encrypt(i) +" - "+ decrypt(encrypt(i)));
        }
        String url = "/show-$1$2.html";
        args = url.split("\\$[0-9]+");
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        System.out.println(url.indexOf("$1"));
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.DAY_OF_MONTH) > 16) {
            cal.add(Calendar.MONTH, 1);
        }
        cal.set(Calendar.DAY_OF_MONTH, 16);
        System.out.println(DateFormatUtils.format(cal, "yyyy-MM-dd"));
    }
}
