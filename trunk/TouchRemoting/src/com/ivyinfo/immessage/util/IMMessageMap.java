package com.ivyinfo.immessage.util;

import java.util.HashMap;
import java.util.Map;

import com.ivyinfo.communication.dao.Communication;
import com.ivyinfo.immessage.bean.IMMessageBean;
import com.ivyinfo.immessage.dao.IMMessage;

public class IMMessageMap {
	public Map<String, String> BeanToMap(IMMessageBean imMessageBean) throws Exception{
		Map<String, String> columnNames = new HashMap<String, String>();
		if(imMessageBean.getSenduser() != null){
			columnNames.put(IMMessage.Senduser, imMessageBean.getSenduser());
		}
		if(imMessageBean.getTouser() != null){
			columnNames.put(IMMessage.Touser, imMessageBean.getTouser());
		}
		if(imMessageBean.getMessage() != null){
			columnNames.put(IMMessage.Message, imMessageBean.getMessage());
		}
		if(imMessageBean.getSendyyyy() != null){
			columnNames.put(IMMessage.Sendyyyy, imMessageBean.getSendyyyy());
		}
		if(imMessageBean.getSendmm() != null){
			columnNames.put(IMMessage.Sendmm, imMessageBean.getSendmm());
		}
		if(imMessageBean.getSenddd() != null){
			columnNames.put(IMMessage.Senddd, imMessageBean.getSenddd());
		}
		if(imMessageBean.getSendhh() != null){
			columnNames.put(IMMessage.Sendhh, imMessageBean.getSendhh());
		}
		if(imMessageBean.getSendmin() != null){
			columnNames.put(IMMessage.Sendmin, imMessageBean.getSendmin());
		}
		if(imMessageBean.getSendss() != null){
			columnNames.put(IMMessage.Sendss, imMessageBean.getSendss());
		}
		if(imMessageBean.getReadyyyy() != null){
			columnNames.put(IMMessage.Readyyyy, imMessageBean.getReadyyyy());
		}
		if(imMessageBean.getReadmm() != null){
			columnNames.put(IMMessage.Readmm, imMessageBean.getReadmm());
		}
		if(imMessageBean.getReaddd() != null){
			columnNames.put(IMMessage.Readdd, imMessageBean.getReaddd());
		}
		if(imMessageBean.getReadhh() != null){
			columnNames.put(IMMessage.Readhh, imMessageBean.getReadhh());
		}
		if(imMessageBean.getReadmin() != null){
			columnNames.put(IMMessage.Readmin, imMessageBean.getReadmin());
		}
		if(imMessageBean.getReadss() != null){
			columnNames.put(IMMessage.Readss, imMessageBean.getReadss());
		}
		if(imMessageBean.getReadflag() != null){
			columnNames.put(IMMessage.Readflag, imMessageBean.getReadflag());
		}
		if(imMessageBean.getNicesenduser() != null){
			columnNames.put(IMMessage.Nicesenduser, imMessageBean.getNicesenduser());
		}
		if(imMessageBean.getNicetouser() != null){
			columnNames.put(IMMessage.Nicetouser, imMessageBean.getNicetouser());
		}
		if(imMessageBean.getMessagetype() != null){
			columnNames.put(IMMessage.Messagetype, imMessageBean.getMessagetype());
		}
		return columnNames;
	}
	
	public IMMessageBean MapToBean(Map mm) throws Exception{
		IMMessageBean imMessageBean = new IMMessageBean();
		if(mm.get(IMMessage.Senduser) !=null){
			imMessageBean.setSenduser(String.valueOf(mm.get(IMMessage.Senduser)));
		}
		if(mm.get(IMMessage.Touser) != null){
			imMessageBean.setTouser(String.valueOf(mm.get(IMMessage.Touser)));
		}
		if(mm.get(IMMessage.Message) != null){
			imMessageBean.setMessage(String.valueOf(mm.get(IMMessage.Message)));
		}
		if(mm.get(IMMessage.Sendyyyy) != null){
			imMessageBean.setSendyyyy(String.valueOf(mm.get(IMMessage.Sendyyyy)));
		}
		if(mm.get(IMMessage.Sendmm) != null){
			imMessageBean.setSendmm(String.valueOf(mm.get(IMMessage.Sendmm)));
		}
		if(mm.get(IMMessage.Senddd) != null){
			imMessageBean.setSenddd(String.valueOf(mm.get(IMMessage.Senddd)));
		}
		if(mm.get(IMMessage.Sendhh) != null){
			imMessageBean.setSendhh(String.valueOf(mm.get(IMMessage.Sendhh)));
		}
		if(mm.get(IMMessage.Sendmin) != null){
			imMessageBean.setSendmin(String.valueOf(mm.get(IMMessage.Sendmin)));
		}
		if(mm.get(IMMessage.Sendss) != null){
			imMessageBean.setSendss(String.valueOf(mm.get(IMMessage.Sendss)));
		}
		if(mm.get(IMMessage.Readyyyy) != null){
			imMessageBean.setReadyyyy(String.valueOf(mm.get(IMMessage.Readyyyy)));
		}
		if(mm.get(IMMessage.Readmm) != null){
			imMessageBean.setReadmm(String.valueOf(mm.get(IMMessage.Readmm)));
		}
		if(mm.get(IMMessage.Readdd) != null){
			imMessageBean.setReaddd(String.valueOf(mm.get(IMMessage.Readdd)));
		}
		if(mm.get(IMMessage.Readhh) != null){
			imMessageBean.setReadhh(String.valueOf(mm.get(IMMessage.Readhh)));
		}
		if(mm.get(IMMessage.Readmin) != null){
			imMessageBean.setReadmin(String.valueOf(mm.get(IMMessage.Readmin)));
		}
		if(mm.get(IMMessage.Readss) != null){
			imMessageBean.setReadss(String.valueOf(mm.get(IMMessage.Readss)));
		}
		if(mm.get(IMMessage.Readflag) != null){
			imMessageBean.setReadflag(String.valueOf(mm.get(IMMessage.Readflag)));
		}
		if(mm.get(IMMessage.Nicesenduser) != null){
			imMessageBean.setNicesenduser(String.valueOf(mm.get(IMMessage.Nicesenduser)));
		}
		if(mm.get(IMMessage.Nicetouser) != null){
			imMessageBean.setNicetouser(String.valueOf(mm.get(IMMessage.Nicetouser)));
		}
		if(mm.get(IMMessage.Messagetype) != null){
			imMessageBean.setMessagetype(String.valueOf(mm.get(IMMessage.Messagetype)));
		}
		
		return imMessageBean;
	}
}
