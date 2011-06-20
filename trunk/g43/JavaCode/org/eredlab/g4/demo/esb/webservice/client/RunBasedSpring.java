package org.eredlab.g4.demo.esb.webservice.client;

import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.demo.esb.webservice.HelloWorld;

public class RunBasedSpring {
	
	public static void main(String[] args) {
		//sayHello();
		//queryBalanceInfo();
		queryBalanceInfoLimitRownum();
	}
	
	private static void sayHello(){
		HelloWorld client = (HelloWorld)SpringBeanLoader.getSpringBean("client");
		String outString = client.sayHello("熊春!");
		System.out.println(outString);
	}
	
	private static void queryBalanceInfo(){
		HelloWorld client = (HelloWorld)SpringBeanLoader.getSpringBean("client");
		String outString = client.queryBalanceInfo("BJLK1000000005713");
		System.out.println(outString);
	}
	
	private static void queryBalanceInfoLimitRownum(){
		HelloWorld client = (HelloWorld)SpringBeanLoader.getSpringBean("client");
		String outString = client.queryBalanceInfoLimitRownum(new Integer(10));
		System.out.println(outString);
	}
}
