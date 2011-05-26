package com.ivyinfo.webcall.api.services;

import com.ivyinfo.framework.common.file.ResouceLoader;
import com.ivyinfo.webcall.api.bean.CallReturnBean;
import com.ivyinfo.webcall.api.bean.CallSendBean;
import com.ivyinfo.webcall.api.util.WebCallSendUtil;

public class WebCallServicesImpl implements WebCallServices{
	
	/**
	 * 开始呼叫
	 */
	public String StartCall(CallSendBean callSendBean) throws Exception{
		WebCallSendUtil webCallSendUtil = new WebCallSendUtil();
		
		ResouceLoader resouceloader = new ResouceLoader();
		String webcallsoapenv = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.webcall.api.soapenv");
		String webcallctd = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.webcall.api.ctd");
		
		callSendBean.setSoapenv(webcallsoapenv);
		callSendBean.setCtd(webcallctd);
		
		CallReturnBean callReturnBean = webCallSendUtil.StartCall(callSendBean);
		String result = callReturnBean.getResult();
		String returnvalue = "";
		if("0".equals(result)){
			returnvalue = "正在呼叫，请稍后...";
		}else if("1".equals(result)){
			String reason = callReturnBean.getReason();
			if("1".equals(reason)){
				returnvalue = "卡号段不存在";
			}else if("2".equals(reason)){
				returnvalue = "帐户余额不足";
			}else if("3".equals(reason)){
				returnvalue = "计费方案号不存在";
			}else if("4".equals(reason)){
				returnvalue = "呼叫被过负荷限制";
			}else if("5".equals(reason)){
				returnvalue = "系统计费异常";
			}
		}else if("1001".equals(result)){
			returnvalue = "系统异常，请联系管理员！";
			throw new Exception("系统异常，请联系管理员！");
		}
		return returnvalue;
	}
	
	/**
	 * 结束呼叫
	 * @param callSendBean
	 * @throws Exception
	 */
	public String EndCall(CallSendBean callSendBean) throws Exception{
		WebCallSendUtil webCallSendUtil = new WebCallSendUtil();
		
		ResouceLoader resouceloader = new ResouceLoader();
		String webcallsoapenv = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.webcall.api.soapenv");
		String webcallctd = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.webcall.api.ctd");
		
		callSendBean.setSoapenv(webcallsoapenv);
		callSendBean.setCtd(webcallctd);
		
		CallReturnBean callReturnBean = webCallSendUtil.EndCall(callSendBean);
		String result = callReturnBean.getResult();
		String returnvalue = "";
		if("0".equals(result)){
			returnvalue = "终止呼叫";
		}else if("1".equals(result)){
			String reason = callReturnBean.getReason();
			if("1".equals(reason)){
				returnvalue = "呼叫已结束";
			}
		}else if("1001".equals(result)){
			returnvalue = "系统异常，请联系管理员！";
			throw new Exception("系统异常，请联系管理员！");
		}
		return returnvalue;
	}
	
	/**
	 * 查询话单
	 * @param callSendBean
	 * @throws Exception
	 */
	public String CdrRequest(CallSendBean callSendBean) throws Exception{
		WebCallSendUtil webCallSendUtil = new WebCallSendUtil();
		
		ResouceLoader resouceloader = new ResouceLoader();
		String webcallsoapenv = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.webcall.api.soapenv");
		String webcallctd = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.webcall.api.ctd");
		
		callSendBean.setSoapenv(webcallsoapenv);
		callSendBean.setCtd(webcallctd);
		
		CallReturnBean callReturnBean = webCallSendUtil.CdrRequest(callSendBean);
		
		return "";
	}
	
	/**
	 * 查询呼叫状态
	 * @param callSendBean
	 * @throws Exception
	 */
	public String StatusRequest(CallSendBean callSendBean) throws Exception{
		WebCallSendUtil webCallSendUtil = new WebCallSendUtil();
		
		ResouceLoader resouceloader = new ResouceLoader();
		String webcallsoapenv = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.webcall.api.soapenv");
		String webcallctd = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.webcall.api.ctd");
		
		callSendBean.setSoapenv(webcallsoapenv);
		callSendBean.setCtd(webcallctd);
		
		CallReturnBean callReturnBean = webCallSendUtil.StatusRequest(callSendBean);
		String result = callReturnBean.getResult();
		String returnvalue = "";
		if("0".equals(result)){
			returnvalue = "查询呼叫状态";
		}else if("1".equals(result)){
			String status = callReturnBean.getStatus();
			if("1".equals(status)){
				returnvalue = "接续主叫中";
			}else if("2".equals(status)){
				returnvalue = "主叫应答";
			}else if("3".equals(status)){
				returnvalue = "主叫正忙";
			}else if("4".equals(status)){
				returnvalue = "主叫无应答";
			}else if("5".equals(status)){
				returnvalue = "主叫无法接通";
			}else if("6".equals(status)){
				returnvalue = "接续被叫中";
			}else if("7".equals(status)){
				returnvalue = "被叫正忙";
			}else if("8".equals(status)){
				returnvalue = "被叫无应答";
			}else if("9".equals(status)){
				returnvalue = "被叫无法接通";
			}else if("10".equals(status)){
				returnvalue = "被叫应答，主被叫通话中";
			}else if("11".equals(status)){
				returnvalue = "呼叫结束";
			}else if("12".equals(status)){
				returnvalue = "余额不足，结束呼叫";
			}
		}else if("1001".equals(result)){
			returnvalue = "系统异常，请联系管理员！";
			throw new Exception("系统异常，请联系管理员！");
		}
		return returnvalue;
	}
}
