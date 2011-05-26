package com.ivyinfo.imcontactsnode.services;

import java.util.List;

import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.imcontactsnode.bean.IMContactsNodeBean;
import com.ivyinfo.imcontactsnode.dao.IMContactsNodeDAO;

public class IMContactsNodeServicesImpl implements IMContactsNodeServices{
	
	public void AddIMContactsNode(IMContactsNodeBean imcontactsNodeBean) throws Exception{
		IMContactsNodeDAO imcontactsNodeDAO =(IMContactsNodeDAO) SpringContextUtil.getBean("imcontactsNodeDAO");
		imcontactsNodeBean.setId(TimeTools.getTimestampMilliSecond());
		imcontactsNodeDAO.AddIMContactsNode(imcontactsNodeBean);
	}
	
	public List getListIMContactsNode_PidORNode(IMContactsNodeBean imcontactsNodeBean) throws Exception{
		IMContactsNodeDAO imcontactsNodeDAO =(IMContactsNodeDAO) SpringContextUtil.getBean("imcontactsNodeDAO");
		return imcontactsNodeDAO.getListIMContactsNode_PidORNode(imcontactsNodeBean);
	}
	
	public List getListIMContactsNode_NidORUid(IMContactsNodeBean imcontactsNodeBean) throws Exception{
		IMContactsNodeDAO imcontactsNodeDAO =(IMContactsNodeDAO) SpringContextUtil.getBean("imcontactsNodeDAO");
		return imcontactsNodeDAO.getListIMContactsNode_NidORUid(imcontactsNodeBean);
	}
	
	public void DelIMContactsNode(IMContactsNodeBean imcontactsNodeBean) throws Exception{
		IMContactsNodeDAO imcontactsNodeDAO =(IMContactsNodeDAO) SpringContextUtil.getBean("imcontactsNodeDAO");
		IMContactsNodeServices imcontactsNodeServices =(IMContactsNodeServices) SpringContextUtil.getBean("imcontactsNodeServices");
		List list = imcontactsNodeServices.getListIMContactsNode_NidORUid(imcontactsNodeBean);
		for(int i=0;i<list.size();i++){
			IMContactsNodeBean imContactsNodeBean = (IMContactsNodeBean) list.get(i);
			imcontactsNodeDAO.DelIMContactsNode(imContactsNodeBean.getId(),imContactsNodeBean.getLogname());
		}
	}
}
