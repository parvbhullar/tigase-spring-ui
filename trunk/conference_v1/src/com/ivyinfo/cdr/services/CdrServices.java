package com.ivyinfo.cdr.services;

import java.util.List;

import com.ivyinfo.cdr.bean.CdrBean;
import com.ivyinfo.framework.service.base.IBaseService;

public interface CdrServices extends IBaseService{
	public int AddCdr(CdrBean cdr) throws Exception;
	
	public void updateCdr(CdrBean cdr) throws Exception;
	
	public List<CdrBean> queryCdr(CdrBean cdr) throws Exception;

}
