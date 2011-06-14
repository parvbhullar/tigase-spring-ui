package com.ivyinfo.cdr.services;

import com.ivyinfo.cdr.bean.CdrBean;
import com.ivyinfo.framework.service.base.IBaseService;

public interface CdrServices extends IBaseService{
	public void AddCdr(CdrBean cdr) throws Exception;

}
