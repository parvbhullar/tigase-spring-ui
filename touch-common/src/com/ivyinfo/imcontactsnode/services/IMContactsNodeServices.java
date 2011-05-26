package com.ivyinfo.imcontactsnode.services;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.imcontactsnode.bean.IMContactsNodeBean;

public interface IMContactsNodeServices extends IBaseService{
	public void AddIMContactsNode(IMContactsNodeBean imcontactsNodeBean) throws Exception;
	
	public List getListIMContactsNode_PidORNode(IMContactsNodeBean imcontactsNodeBean) throws Exception;
	
	public List getListIMContactsNode_NidORUid(IMContactsNodeBean imcontactsNodeBean) throws Exception;
	
	public void DelIMContactsNode(IMContactsNodeBean imcontactsNodeBean) throws Exception;
}
