package com.ivyinfo.imcontactsnode.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.imcontactsnode.bean.IMContactsNodeBean;

public interface IMContactsNodeDAO extends IBaseDAO{
	public void AddIMContactsNode(IMContactsNodeBean imcontactsNodeBean) throws Exception;
	
	public List getListIMContactsNode_PidORNode(IMContactsNodeBean imcontactsNodeBean) throws Exception;
	
	public List getListIMContactsNode_NidORUid(IMContactsNodeBean imcontactsNodeBean) throws Exception;
	
	public void DelIMContactsNode(String id,String logname) throws Exception;
}
