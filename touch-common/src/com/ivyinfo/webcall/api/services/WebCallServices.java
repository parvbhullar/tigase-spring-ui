package com.ivyinfo.webcall.api.services;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.webcall.api.bean.CallSendBean;

public interface WebCallServices extends IBaseService{
	/**
	 * 开始呼叫
	 * @param callSendBean
	 * @throws Exception
	 */
	public String StartCall(CallSendBean callSendBean) throws Exception;
	
	/**
	 * 结束呼叫
	 * @param callSendBean
	 * @throws Exception
	 */
	public String EndCall(CallSendBean callSendBean) throws Exception;
	
	/**
	 * 查询话单
	 * @param callSendBean
	 * @throws Exception
	 */
	public String CdrRequest(CallSendBean callSendBean) throws Exception;
	
	/**
	 * 查询呼叫状态
	 * @param callSendBean
	 * @throws Exception
	 */
	public String StatusRequest(CallSendBean callSendBean) throws Exception;
}
