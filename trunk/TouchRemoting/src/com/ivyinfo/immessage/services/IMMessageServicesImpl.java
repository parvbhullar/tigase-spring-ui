package com.ivyinfo.immessage.services;

import java.util.List;

import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.immessage.bean.IMMessageBean;
import com.ivyinfo.immessage.dao.IMMessageDAO;

public class IMMessageServicesImpl implements IMMessageServices{
	
	public void AddIMMessage(IMMessageBean imMessageBean) throws Exception{
		IMMessageDAO imMessageDAO =(IMMessageDAO) SpringContextUtil.getBean("imMessageDAO");
		TimeTools timetools = new TimeTools();
		String time = timetools.getCurrentTime();
		imMessageBean.setId(timetools.getDateTime_webCall()+timetools.getMilliSecond());
		imMessageBean.setSendyyyy(time.substring(0,4));
		imMessageBean.setSendmm(time.substring(5,7));
		imMessageBean.setSenddd(time.substring(8,10));
		imMessageBean.setSendhh(time.substring(11,13));
		imMessageBean.setSendmin(time.substring(14,16));
		imMessageBean.setSendss(time.substring(17,19));
		
		imMessageDAO.AddIMMessage(imMessageBean);
	}
	
	public void UpdIMMessage(IMMessageBean imMessageBean) throws Exception{
		IMMessageDAO imMessageDAO =(IMMessageDAO) SpringContextUtil.getBean("imMessageDAO");
		TimeTools timetools = new TimeTools();
		String time = timetools.getCurrentTime();
		imMessageBean.setId(imMessageBean.getId());
		imMessageBean.setReadyyyy(time.substring(0,4));
		imMessageBean.setReadmm(time.substring(5,7));
		imMessageBean.setReaddd(time.substring(8,10));
		imMessageBean.setReadhh(time.substring(11,13));
		imMessageBean.setReadmin(time.substring(14,16));
		imMessageBean.setReadss(time.substring(17,19));
		
		imMessageDAO.UpdIMMessage(imMessageBean);
	}
	
	public List AllIMMessage(String logname,String id) throws Exception{
		IMMessageDAO imMessageDAO =(IMMessageDAO) SpringContextUtil.getBean("imMessageDAO");
		return imMessageDAO.AllIMMessage(logname, id);
	}
	
	public List AllIMMessage_ReadORType(String logname,String fieldname,String value) throws Exception{
		IMMessageDAO imMessageDAO =(IMMessageDAO) SpringContextUtil.getBean("imMessageDAO");
		return imMessageDAO.AllIMMessage_ReadORType(logname, fieldname, value);
	}
	
	public List AllIMMessage_ReadAndType(String logname,String typevalue,String readvalue) throws Exception{
		IMMessageDAO imMessageDAO =(IMMessageDAO) SpringContextUtil.getBean("imMessageDAO");
		return imMessageDAO.AllIMMessage_ReadAndType(logname, typevalue, readvalue);
	}
}
