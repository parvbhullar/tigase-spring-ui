package com.ivyinfo.webcall.util;

import java.util.ArrayList;
import java.util.List;

import com.ivyinfo.framework.common.encryption.Base64;
import com.ivyinfo.framework.common.encryption.DES;
import com.ivyinfo.framework.common.encryption.MD5_WebCall;
import com.ivyinfo.framework.common.xml.XMLTools;
import com.ivyinfo.webcall.bean.ChangePasswordReceiveBean;
import com.ivyinfo.webcall.bean.ContactsReceiveBean;
import com.ivyinfo.webcall.bean.ErrorReceiveBean;
import com.ivyinfo.webcall.bean.ExitCallReceiveBean;
import com.ivyinfo.webcall.bean.ReceiveBean;
import com.ivyinfo.webcall.bean.RechargeReceiveBean;
import com.ivyinfo.webcall.bean.RecordsBean;
import com.ivyinfo.webcall.bean.SearchCardStatusReceiveBean;
import com.ivyinfo.webcall.bean.SearchRecordReceiveBean;
import com.ivyinfo.webcall.bean.SearchResidualAmountReceiveBean;
import com.ivyinfo.webcall.bean.UserLoginReceiveBean;

public class HttpInvokerReceiveXML implements ReceiveBean{
	
	public static UserLoginReceiveBean XMLToUserLoginReceiveBean(String returnxml) throws Exception{
		try{
			UserLoginReceiveBean rbean = new UserLoginReceiveBean();
			rbean.setResult(XMLTools.getXMLValue(returnxml,RESULT));
			if("1".equals(rbean.getResult())){
				rbean.setSessionid(XMLTools.getXMLValue(returnxml,SESSIONID));
				rbean.setCode(XMLTools.getXMLValue(returnxml,CODE));
				rbean.setUrl(XMLTools.getXMLValue(returnxml,URL));
				rbean.setTimestamp(XMLTools.getXMLValue(returnxml,TIMESTAMP));
				rbean.setAuthenticator(XMLTools.getXMLValue(returnxml,AUTHENTICATOR));
				String auth = new String(
						Base64.encode(
								MD5_WebCall.getMD5(rbean.getSessionid()+"$"+rbean.getResult()+"$"+rbean.getCode()+"$"+rbean.getUrl()+"$"+rbean.getTimestamp()).getBytes()));
				if(!auth.equals(rbean.getAuthenticator())){
					throw new Exception("返回信息验证错误！");
				}else{
					rbean.setCode(DES.decrypt(new String(Base64.decode(rbean.getCode()))));
				}
				return rbean;
			}else if("2".equals(rbean.getResult())){
				rbean.setErBean(XMLToErrorReceiveBean(returnxml));
				return rbean;
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static ChangePasswordReceiveBean XMLToChangePasswordReceiveBean(String returnxml) throws Exception{
		try{
			ChangePasswordReceiveBean rbean = new ChangePasswordReceiveBean();
			rbean.setResult(XMLTools.getXMLValue(returnxml,RESULT));
			if("1".equals(rbean.getResult())){
				rbean.setSessionid(XMLTools.getXMLValue(returnxml,SESSIONID));
				rbean.setCode(XMLTools.getXMLValue(returnxml,CODE));
				rbean.setTimestamp(XMLTools.getXMLValue(returnxml,TIMESTAMP));
				rbean.setAuthenticator(XMLTools.getXMLValue(returnxml,AUTHENTICATOR));
				String auth = new String(
						Base64.encode(
								MD5_WebCall.getMD5(rbean.getSessionid()+"$"+rbean.getResult()+"$"+rbean.getCode()+"$"+rbean.getTimestamp()).getBytes()));
				if(!auth.equals(rbean.getAuthenticator())){
					throw new Exception("返回信息验证错误！");
				}else{
					rbean.setCode(DES.decrypt(new String(Base64.decode(rbean.getCode()))));
				}
				return rbean;
			}else if("2".equals(rbean.getResult())){
				rbean.setErBean(XMLToErrorReceiveBean(returnxml));
				return rbean;
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static SearchResidualAmountReceiveBean XMLToSearchResidualAmountReceiveBean(String returnxml) throws Exception{
		try{
			SearchResidualAmountReceiveBean rbean = new SearchResidualAmountReceiveBean();
			rbean.setResult(XMLTools.getXMLValue(returnxml,RESULT));
			if("1".equals(rbean.getResult())){
				rbean.setSessionid(XMLTools.getXMLValue(returnxml,SESSIONID));
				rbean.setCode(XMLTools.getXMLValue(returnxml,CODE));
				rbean.setCurrency(XMLTools.getXMLValue(returnxml,CURRENCY));
				rbean.setAmount(XMLTools.getXMLValue(returnxml,AMOUNT));
				rbean.setTimestamp(XMLTools.getXMLValue(returnxml,TIMESTAMP));
				rbean.setAuthenticator(XMLTools.getXMLValue(returnxml,AUTHENTICATOR));
				String auth = new String(
						Base64.encode(
								MD5_WebCall.getMD5(rbean.getSessionid()+"$"+rbean.getResult()+"$"+rbean.getCode()+"$"+rbean.getCurrency()+"$"+rbean.getAmount()+"$"+rbean.getTimestamp()).getBytes()));
				if(!auth.equals(rbean.getAuthenticator())){
					throw new Exception("返回信息验证错误！");
				}else{
					rbean.setCode(DES.decrypt(new String(Base64.decode(rbean.getCode()))));
					rbean.setCurrency(DES.decrypt(new String(Base64.decode(rbean.getCurrency()))));
					rbean.setAmount(DES.decrypt(new String(Base64.decode(rbean.getAmount()))));
				}
				return rbean;
			}else if("2".equals(rbean.getResult())){
				rbean.setErBean(XMLToErrorReceiveBean(returnxml));
				return rbean;
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static SearchCardStatusReceiveBean XMLToSearchCardStatusReceiveBean(String returnxml) throws Exception{
		try{
			SearchCardStatusReceiveBean rbean = new SearchCardStatusReceiveBean();
			rbean.setResult(XMLTools.getXMLValue(returnxml,RESULT));
			if("1".equals(rbean.getResult())){
				rbean.setSessionid(XMLTools.getXMLValue(returnxml,SESSIONID));
				rbean.setCode(XMLTools.getXMLValue(returnxml,CODE));
				rbean.setStatus(XMLTools.getXMLValue(returnxml,STATUS));
				rbean.setTimestamp(XMLTools.getXMLValue(returnxml,TIMESTAMP));
				rbean.setAuthenticator(XMLTools.getXMLValue(returnxml,AUTHENTICATOR));
				String auth = new String(
						Base64.encode(
								MD5_WebCall.getMD5(rbean.getSessionid()+"$"+rbean.getResult()+"$"+rbean.getCode()+"$"+rbean.getStatus()+"$"+rbean.getTimestamp()).getBytes()));
				if(!auth.equals(rbean.getAuthenticator())){
					throw new Exception("返回信息验证错误！");
				}else{
					rbean.setCode(DES.decrypt(new String(Base64.decode(rbean.getCode()))));
				}
				return rbean;
			}else if("2".equals(rbean.getResult())){
				rbean.setErBean(XMLToErrorReceiveBean(returnxml));
				return rbean;
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static SearchRecordReceiveBean XMLToSearchRecordReceiveBean(String returnxml) throws Exception{
		try{
			SearchRecordReceiveBean rbean = new SearchRecordReceiveBean();
			rbean.setResult(XMLTools.getXMLValue(returnxml,RESULT));
			if("1".equals(rbean.getResult())){
				rbean.setSessionid(XMLTools.getXMLValue(returnxml,SESSIONID));
				rbean.setCode(XMLTools.getXMLValue(returnxml,CODE));
				rbean.setRecordnum(XMLTools.getXMLValue(returnxml,RECORDNUM));
				rbean.setTimestamp(XMLTools.getXMLValue(returnxml,TIMESTAMP));
				rbean.setAuthenticator(XMLTools.getXMLValue(returnxml,AUTHENTICATOR));
				
				String auth = new String(
						Base64.encode(
								MD5_WebCall.getMD5(rbean.getSessionid()+"$"+rbean.getResult()+"$"+rbean.getCode()+"$"+rbean.getRecordnum()+"$"+rbean.getTimestamp()).getBytes()));
				if(!auth.equals(rbean.getAuthenticator())){
					throw new Exception("返回信息验证错误！");
				}else{
					rbean.setCode(DES.decrypt(new String(Base64.decode(rbean.getCode()))));
				}
				
				if(!"".equals(rbean.getRecordnum()) && !"0".equals(rbean.getRecordnum())){
					int num = Integer.parseInt(rbean.getRecordnum());
					List<RecordsBean> RecordsList = new ArrayList<RecordsBean>();
					for(int i=1; i<=num; i++){
						RecordsBean recordBean = new RecordsBean();
						recordBean.setCallee(XMLTools.getXMLValue(returnxml,CALLEE));
						recordBean.setStarttime(XMLTools.getXMLValue(returnxml,STARTTIME));
						recordBean.setEndtime(XMLTools.getXMLValue(returnxml,ENDTIME));
						RecordsList.add(recordBean);
					}
				}
				return rbean;
			}else if("2".equals(rbean.getResult())){
				rbean.setErBean(XMLToErrorReceiveBean(returnxml));
				return rbean;
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static RechargeReceiveBean XMLToRechargeReceiveBean(String returnxml) throws Exception{
		try{
			RechargeReceiveBean rbean = new RechargeReceiveBean();
			rbean.setResult(XMLTools.getXMLValue(returnxml,RESULT));
			if("1".equals(rbean.getResult())){
				rbean.setSessionid(XMLTools.getXMLValue(returnxml,SESSIONID));
				rbean.setCode(XMLTools.getXMLValue(returnxml,CODE));
				rbean.setCurrency(XMLTools.getXMLValue(returnxml,CURRENCY));
				rbean.setAmount(XMLTools.getXMLValue(returnxml,AMOUNT));
				rbean.setTimestamp(XMLTools.getXMLValue(returnxml,TIMESTAMP));
				rbean.setAuthenticator(XMLTools.getXMLValue(returnxml,AUTHENTICATOR));
				
				String auth = new String(
						Base64.encode(
								MD5_WebCall.getMD5(rbean.getSessionid()+"$"+rbean.getResult()+"$"+rbean.getCode()+"$"+rbean.getCurrency()+"$"+rbean.getAmount()+"$"+rbean.getTimestamp()).getBytes()));
				if(!auth.equals(rbean.getAuthenticator())){
					throw new Exception("返回信息验证错误！");
				}else{
					rbean.setCode(DES.decrypt(new String(Base64.decode(rbean.getCode()))));
					rbean.setCurrency(DES.decrypt(new String(Base64.decode(rbean.getCurrency()))));
					rbean.setAmount(DES.decrypt(new String(Base64.decode(rbean.getAmount()))));
				}
				
				return rbean;
			}else if("2".equals(rbean.getResult())){
				rbean.setErBean(XMLToErrorReceiveBean(returnxml));
				return rbean;
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static ContactsReceiveBean XMLToContactsReceiveBean(String returnxml) throws Exception{
		try{
			ContactsReceiveBean rbean = new ContactsReceiveBean();
			rbean.setResult(XMLTools.getXMLValue(returnxml,RESULT));
			if("1".equals(rbean.getResult())){
				rbean.setSessionid(XMLTools.getXMLValue(returnxml,SESSIONID));
				rbean.setCode(XMLTools.getXMLValue(returnxml,CODE));
				rbean.setContactid(XMLTools.getXMLValue(returnxml,CONTACTID));
				rbean.setTimestamp(XMLTools.getXMLValue(returnxml,TIMESTAMP));
				rbean.setAuthenticator(XMLTools.getXMLValue(returnxml,AUTHENTICATOR));
				
				String auth = new String(
						Base64.encode(
								MD5_WebCall.getMD5(rbean.getSessionid()+"$"+rbean.getResult()+"$"+rbean.getCode()+"$"+rbean.getContactid()+"$"+rbean.getTimestamp()).getBytes()));
				System.err.println("====:"+rbean.getSessionid()+"$"+rbean.getResult()+"$"+rbean.getCode()+"$"+rbean.getContactid()+"$"+rbean.getTimestamp());
				if(!auth.equals(rbean.getAuthenticator())){
					throw new Exception("返回信息验证错误！");
				}else{
					rbean.setCode(DES.decrypt(new String(Base64.decode(rbean.getCode()))));
					rbean.setContactid(DES.decrypt(new String(Base64.decode(rbean.getContactid()))));
				}
				
				return rbean;
			}else if("2".equals(rbean.getResult())){
				rbean.setErBean(XMLToErrorReceiveBean(returnxml));
				return rbean;
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static ExitCallReceiveBean XMLToExitCallReceiveBean(String returnxml) throws Exception{
		try{
			ExitCallReceiveBean rbean = new ExitCallReceiveBean();
			rbean.setResult(XMLTools.getXMLValue(returnxml,RESULT));
			if("1".equals(rbean.getResult())){
				rbean.setSessionid(XMLTools.getXMLValue(returnxml,SESSIONID));
				rbean.setCode(XMLTools.getXMLValue(returnxml,CODE));
				rbean.setTimestamp(XMLTools.getXMLValue(returnxml,TIMESTAMP));
				rbean.setAuthenticator(XMLTools.getXMLValue(returnxml,AUTHENTICATOR));
				
				String auth = new String(
						Base64.encode(
								MD5_WebCall.getMD5(rbean.getSessionid()+"$"+rbean.getResult()+"$"+rbean.getCode()+"$"+rbean.getTimestamp()).getBytes()));
				if(!auth.equals(rbean.getAuthenticator())){
					throw new Exception("返回信息验证错误！");
				}else{
					rbean.setCode(DES.decrypt(new String(Base64.decode(rbean.getCode()))));
				}
				
				return rbean;
			}else if("2".equals(rbean.getResult())){
				rbean.setErBean(XMLToErrorReceiveBean(returnxml));
				return rbean;
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static ErrorReceiveBean XMLToErrorReceiveBean(String returnxml) throws Exception{
		try{
			ErrorReceiveBean rbean = new ErrorReceiveBean();
			rbean.setResult(XMLTools.getXMLValue(returnxml,RESULT));
			rbean.setSessionid(XMLTools.getXMLValue(returnxml,SESSIONID));
			rbean.setCode(XMLTools.getXMLValue(returnxml,CODE));
			rbean.setReason(XMLTools.getXMLValue(returnxml,REASON));
			rbean.setExceptionid(XMLTools.getXMLValue(returnxml,EXCEPTIONID));
			rbean.setTimestamp(XMLTools.getXMLValue(returnxml,TIMESTAMP));
			rbean.setAuthenticator(XMLTools.getXMLValue(returnxml,AUTHENTICATOR));
			
			String auth = new String(
					Base64.encode(
							MD5_WebCall.getMD5(rbean.getSessionid()+"$"+rbean.getResult()+"$"+rbean.getCode()+"$"+rbean.getReason()+"$"+rbean.getExceptionid()+"$"+rbean.getTimestamp()).getBytes()));
			if(!auth.equals(rbean.getAuthenticator())){
				throw new Exception("返回信息验证错误！");
			}else{
				rbean.setCode(DES.decrypt(new String(Base64.decode(rbean.getCode()))));
			}
			
			return rbean;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
