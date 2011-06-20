package org.eredlab.g4.rif.util;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.rif.report.jasper.ReportData;

/**
 * Session容器
 * 
 * @author 熊春
 * @since 2009-09-03
 * @see HttpSessionBindingListener
 */
public class SessionContainer implements HttpSessionBindingListener {
	
	/**
	 * 登陆用户对象
	 */
	private UserInfoVo userInfo;

	/**
	 * 报表对象集
	 */
	private Dto reportDto;
	
	public SessionContainer() {
		super();
		reportDto =  new BaseDto();
	}
	
	/**
	 * 设置报表对象
	 * 缺省方法：支持一个页面一个打印对象
	 * @param pReportData
	 */
	public void setReportData(ReportData pReportData){
		reportDto.put("default", pReportData);
	}
	
	/**
	 * 获取报表对象
	 * 缺省方法：支持一个页面一个打印对象
	 * @return
	 */
	public ReportData getReportData(){
		return (ReportData)reportDto.get("default");
	}
	
	/**
	 * 设置报表对象
	 * 重载方法：支持一个页面多个打印对象
	 * @param pReportData
	 */
	public void setReportData(String pFlag, ReportData pReportData){
		reportDto.put(pFlag, pReportData);
	}
	
	/**
	 * 获取报表对象
	 * 重载方法：支持一个页面多个打印对象
	 * @return
	 */
	public ReportData getReportData(String pFlag){
		return (ReportData)reportDto.get(pFlag);
	}
	

	/**
	 * 清除会话容器缓存对象
	 */
	public void cleanUp() {
		// userInfo不能在此批量重置,只能使用setUserInfo(null)方法对其进行独立操作
		// userInfo = null;
		reportDto.clear();
	}

	public void valueBound(HttpSessionBindingEvent event) {
		//System.out.println("会话创建了");
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		//System.out.println("会话销魂了");
	}

	/**
	 * 获取用户会话对象
	 * @return UserInfo
	 */
	public UserInfoVo getUserInfo() {
		return userInfo;
	}

	/**
	 * 设置用户会话对象
	 * @param userInfo
	 */
	public void setUserInfo(UserInfoVo userInfo) {
		this.userInfo = userInfo;
		
	}

}
