package com.ivyinfo.imcontacts.services;

import java.util.List;

import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.imcontacts.bean.IMContactsBean;
import com.ivyinfo.imcontacts.dao.IMContactsDAO;

public class IMContactsServicesImpl implements IMContactsServices{
	
	public void AddIMContacts(IMContactsBean imcontactsBean) throws Exception{
		IMContactsDAO imcontactsDAO =(IMContactsDAO) SpringContextUtil.getBean("imcontactsDAO");
		imcontactsBean.setId(TimeTools.getTimestampMilliSecond());
		imcontactsDAO.AddIMContacts(imcontactsBean);
	}
	
	public void DelIMContacts(IMContactsBean imcontactsBean) throws Exception{
		IMContactsDAO imcontactsDAO =(IMContactsDAO) SpringContextUtil.getBean("imcontactsDAO");
		IMContactsServices imcontactsServices =(IMContactsServices) SpringContextUtil.getBean("imcontactsServices");
		List list = imcontactsServices.getListIMContacts(imcontactsBean);
		for(int i=0;i<list.size();i++){
			IMContactsBean imContactsBean = (IMContactsBean) list.get(i);
			imcontactsDAO.DelIMContacts(imContactsBean.getId(),imContactsBean.getLogname());
		}
	}
	
	public List getListIMContacts(IMContactsBean imcontactsBean) throws Exception{
		IMContactsDAO imcontactsDAO =(IMContactsDAO) SpringContextUtil.getBean("imcontactsDAO");
		return imcontactsDAO.getListIMContacts(imcontactsBean);
	}
}
