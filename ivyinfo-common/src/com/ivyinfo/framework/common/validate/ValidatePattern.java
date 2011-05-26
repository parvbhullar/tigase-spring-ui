package com.ivyinfo.framework.common.validate;

import java.util.regex.Pattern;

public class ValidatePattern {
	/**
	 * 验证Email格式
	 * @param email
	 * @return
	 */
	public static boolean isVaildEmail(String email){ 
	     String emailPattern="[a-zA-Z0-9][a-zA-Z0-9._-]{2,16}[a-zA-Z0-9]@[a-zA-Z0-9]+.[a-zA-Z0-9]+"; 
	     boolean result=Pattern.matches(emailPattern, email); 
	     return result; 
	} 
	
	/**
	 * 只允许数字和字母
	 * @param name
	 * @return
	 */
	public static boolean isValidName(String name){
		String namePattern="[0-9A-Za-z]*";
		boolean result=Pattern.matches(namePattern, name);
		return result;
	}
	
	/**
	 * 电话格式
	 * @param name
	 * @return
	 */
	public static boolean isValidPhone(String phone){
		String namePattern="(^[0]\\d{2,3}\\-\\d{7,8})|(^[1-9]\\d{6,7})|(^[0]\\d{10,11})";
		boolean result=Pattern.matches(namePattern, phone);
		return result;
	}
	
	/**
	 * 手机格式
	 * @param name
	 * @return
	 */
	public static boolean isValidMobilePhone (String mobilePhone){
		String namePattern="^[1][\\d]{10}";
		boolean result=Pattern.matches(namePattern, mobilePhone);
		return result;
	}
	
	/**
	 * 传真格式
	 * @param name
	 * @return
	 */
	public static boolean isValidFax (String fax){
		String namePattern="(^[0]\\d{2,3}\\-\\d{7,8})|(^[1-9]\\d{6,7})|(^[0]\\d{10,11})";
		boolean result=Pattern.matches(namePattern, fax);
		return result;
	}
	
	/**
	 * 邮政格式
	 * @param name
	 * @return
	 */
	public static boolean isValidPostcode (String postcode){
		String namePattern="^[1-9]\\d{5}";
		boolean result=Pattern.matches(namePattern, postcode);
		return result;
	}
}
