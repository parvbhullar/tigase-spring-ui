package com.ivyinfo.random.util;

import com.ivyinfo.framework.common.tools.RandomUtil;

public class RandomUtilTest {

    public static void main(String[] args) {
    	RandomUtil randomUtil = new RandomUtil();
    	String random = randomUtil.generateString(10);
        System.out.println(random); 
//      System.out.println(generateMixString(15)); 
//      System.out.println(generateLowerString(15)); 
//      System.out.println(generateUpperString(15)); 
//      System.out.println(generateZeroString(15)); 
//      System.out.println(toFixdLengthString(123, 15)); 
//      System.out.println(toFixdLengthString(123L, 15)); 
    }
}
