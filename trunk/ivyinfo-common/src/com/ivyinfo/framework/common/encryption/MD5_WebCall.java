package com.ivyinfo.framework.common.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5_WebCall {

	public static String getMD5(String sInput) {
		try {

			String algorithm = "";
			// 输入不能为空
			if (sInput.trim() == null) {
				return "";
			}

			// 指定采用MD5算法
			try {
				algorithm = System.getProperty("MD5.algorithm", "MD5");
			} catch (SecurityException se) {
			}

			// 定义MessageDigest对象
			MessageDigest md = MessageDigest.getInstance(algorithm);

			// 按照iso8859-1字符编码方式把sInput 转换成字节，并把结果存到一新的字节数组buffer中
			byte buffer[] = sInput.getBytes("iso8859-1");

			// 从指定的字节数组buffer的偏移量0开始，用指定的字节数组修改由sInput生成摘要
			// count为从 0 开始用的字节数长度。
			for (int count = 0; count < sInput.length(); count++) {
				md.update(buffer, 0, count);
			}

			// 通过执行最后的诸如填充的操作完成散列码的计算。 在调用之后复位该摘要
			// 返回存放结果散列值的字节数组bDigest
			byte bDigest[] = md.digest();

			// 将bDigest转换为大整数bi
			BigInteger bi = new BigInteger(bDigest);

			// 返回bi字符串表示，即最终的编码结果
			return (bi.toString(16));
		} catch (Exception ex) {
			return null;
		}
	}

	public static void main(String[] args) {

		// String authenticator="";
		// String sessionid="ab134352daaq112";
		// String action="createCall";
		// //String action="updatePassword";
		// //String action="searchResidualAmount";
		// String service="webCall";
		// String code="RUQ3OEQzQkM5QThGMDU2RTcwQUM0NEY4NjhDMDEwNjk=";
		// String password="OUI4Q0E5RTNDRUUxRjgzMw==";
		// //String oldpassword="QTY1RDBDNTQwRjAxNDc2Mw==";
		// //String newpassword="OUI4Q0E5RTNDRUUxRjgzMw==";
		// String timestamp="201011171744";
		//	
		// authenticator =
		// sessionid+"$"+action+"$"+service+"$"+code+"$"+password+"$"+timestamp;

		// authenticator =
		// sessionid+"$"+action+"$"+service+"$"+code+"$"+oldpassword+"$"+newpassword+"$"+timestamp;

		try {
			String aftermd5 = getMD5("b");
			// String b64 = Base64.getBASE64(aftermd5);

			// System.out.println("原始字段:"+authenticator);
			System.out.println("MD5后:" + aftermd5);
			// System.out.println("b64转码后:"+b64);
		} catch (Exception e) {

		}
	}
}
