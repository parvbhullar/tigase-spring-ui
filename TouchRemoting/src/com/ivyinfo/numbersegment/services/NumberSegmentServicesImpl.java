package com.ivyinfo.numbersegment.services;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.numbersegment.bean.NumberSegmentBean;
import com.ivyinfo.numbersegment.dao.NumberSegmentDAO;
import com.ivyinfo.numbersegment.util.NumberSegInterface;

public class NumberSegmentServicesImpl implements NumberSegmentServices{
	
	public void AddNumberSegment(NumberSegmentBean numberSegmentBean) throws Exception{
		NumberSegmentDAO numberSegmentDAO =(NumberSegmentDAO) SpringContextUtil.getBean("numberSegmentDAO");
		numberSegmentDAO.AddNumberSegment(numberSegmentBean);
	}
	
	public void UpdNumberSegment(NumberSegmentBean numberSegmentBean) throws Exception{
		NumberSegmentDAO numberSegmentDAO =(NumberSegmentDAO) SpringContextUtil.getBean("numberSegmentDAO");
		numberSegmentDAO.UpdNumberSegment(numberSegmentBean);
	}
	
	public NumberSegmentBean getNumberSegment(String orgid, String state) throws Exception{
		NumberSegmentDAO numberSegmentDAO =(NumberSegmentDAO) SpringContextUtil.getBean("numberSegmentDAO");
		return numberSegmentDAO.getNumberSegment(orgid, state);
	}
	
	public String getAllotNumberSegment(String orgid) throws Exception{
		NumberSegInterface numberSegInterface =(NumberSegInterface) SpringContextUtil.getBean("numberSegInterface");
		String middleseg = String.valueOf(numberSegInterface.getMaxId("t_sys_number_segment",orgid));
		System.err.println("==NumberSegmentServicesImpl[getNumberSegment]==middleseg:"+middleseg);
		return middleseg;
	}
}
