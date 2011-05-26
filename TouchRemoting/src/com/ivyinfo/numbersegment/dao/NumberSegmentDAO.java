package com.ivyinfo.numbersegment.dao;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.numbersegment.bean.NumberSegmentBean;

public interface NumberSegmentDAO extends IBaseDAO{
	public void AddNumberSegment(NumberSegmentBean numberSegmentBean) throws Exception;
	
	public void UpdNumberSegment(NumberSegmentBean numberSegmentBean) throws Exception;
	
	public NumberSegmentBean getNumberSegment(String orgid, String state) throws Exception;
}
