package com.ivyinfo.webcall.services;

import com.ivyinfo.webcall.bean.ChangePasswordReceiveBean;
import com.ivyinfo.webcall.bean.ChangePasswordSendBean;
import com.ivyinfo.webcall.bean.ContactsReceiveBean;
import com.ivyinfo.webcall.bean.ContactsSendBean;
import com.ivyinfo.webcall.bean.ExitCallReceiveBean;
import com.ivyinfo.webcall.bean.ExitCallSendBean;
import com.ivyinfo.webcall.bean.RechargeReceiveBean;
import com.ivyinfo.webcall.bean.RechargeSendBean;
import com.ivyinfo.webcall.bean.SearchCardStatusReceiveBean;
import com.ivyinfo.webcall.bean.SearchCardStatusSendBean;
import com.ivyinfo.webcall.bean.SearchRecordReceiveBean;
import com.ivyinfo.webcall.bean.SearchRecordSendBean;
import com.ivyinfo.webcall.bean.SearchResidualAmountReceiveBean;
import com.ivyinfo.webcall.bean.SearchResidualAmountSendBean;
import com.ivyinfo.webcall.bean.UserLoginReceiveBean;
import com.ivyinfo.webcall.bean.UserLoginSendBean;

public interface IWebCallAPIServices {
	
	public UserLoginReceiveBean UserLoginService(UserLoginSendBean ulsbean) throws Exception;
	
	public ChangePasswordReceiveBean UpdatePasswordService(ChangePasswordSendBean cpsbean) throws Exception;
	
	public SearchResidualAmountReceiveBean SearchResidualAmountService(SearchResidualAmountSendBean srasbean) throws Exception;
	
	public SearchCardStatusReceiveBean SearchCardStatusService(SearchCardStatusSendBean scssbean) throws Exception;
	
	public SearchRecordReceiveBean SearchRecordService(SearchRecordSendBean srssbean) throws Exception;
	
	public RechargeReceiveBean RechargeService(RechargeSendBean rsbean) throws Exception;
	
	public ContactsReceiveBean ContactsService(ContactsSendBean csbean) throws Exception;
	
	public ExitCallReceiveBean ExitCallService(ExitCallSendBean ecsbean) throws Exception;
}
