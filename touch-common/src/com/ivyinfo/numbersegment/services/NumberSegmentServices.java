package com.ivyinfo.numbersegment.services;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.numbersegment.bean.NumberSegmentBean;

public interface NumberSegmentServices extends IBaseService{
	public void AddNumberSegment(NumberSegmentBean numberSegmentBean) throws Exception;
	
	public void UpdNumberSegment(NumberSegmentBean numberSegmentBean) throws Exception;
	
	public NumberSegmentBean getNumberSegment(String orgid, String state) throws Exception;
	
	/**
	 * 分配用户号码段
	 * @param orgid
	 * @return
	 * @throws Exception
	 */
	public String getAllotNumberSegment(String orgid) throws Exception;
}
