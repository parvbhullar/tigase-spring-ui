package com.ivyinfo.organization.services;

import java.util.List;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.dao.ReceiveMailDAO;
import com.ivyinfo.organization.bean.OrganizationBean;
import com.ivyinfo.organization.bean.OrganizationValidationBean;
import com.ivyinfo.organization.dao.OrganizationDAO;
import com.ivyinfo.organization.util.OrganizationUtil;
import com.ivyinfo.test.dao.ITestDAO;
import com.ivyinfo.webtouch.conversion.UserConversion;

public class OrganizationServicesImpl implements OrganizationServices{

	public int OrgListCount(String conname,String convalue) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		return organizationDAO.OrgListCount(conname, convalue);
	}
	
	public List AllIndex(int from, int len,String order,String orderby,String conname,String convalue) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		return organizationDAO.AllIndex(from, len, order, orderby, conname, convalue);
	}
	
	public String AddSubmit(OrganizationBean organizationBean) throws Exception{
		UserConversion userConversion = (UserConversion) SpringContextUtil.getBean("userConversion");
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		/**
		 * 创建会议组织
		 */
		String meetorgid = userConversion.MeetAddOrg(organizationBean);
		
		organizationBean.setMeetorgid(meetorgid);
		organizationDAO.AddSubmit(organizationBean);
		return meetorgid;
	}
	
	public void AddSubmitContact(OrganizationBean organizationBean) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		organizationDAO.AddSubmitContact(organizationBean);
	}
	
	public OrganizationBean View(int id) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		return organizationDAO.View(id);
	}
	
	public OrganizationBean Upd(int id) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		return organizationDAO.Upd(id);
	}
	
	public void UpdSubmit(OrganizationBean organizationBean) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		organizationDAO.UpdSubmit(organizationBean);
	}
	
	public void UpdSubmitContact(OrganizationBean organizationBean) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		organizationDAO.UpdSubmitContact(organizationBean);
	}
	
	public void Del(int id) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		organizationDAO.Del(id);
	}
	
	public void DelContact(String id) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		organizationDAO.DelContact(id);
	}
	
	public int ValidationOrgName(String orgname) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		return organizationDAO.ValidationOrgName(orgname);
	}
	
	public int VOrgName(String orgname) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		return organizationDAO.VOrgName(orgname);
	}
	
	public OrganizationValidationBean ViewOrgValidation(int id) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		return organizationDAO.ViewOrgValidation(id);
	}
	
	public void AddOrgValidation(OrganizationValidationBean organizationValidationBean) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		organizationDAO.AddOrgValidation(organizationValidationBean);
	}
	
	public void UpdOrgValidation(OrganizationValidationBean organizationValidationBean) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		organizationDAO.UpdOrgValidation(organizationValidationBean);
	}
	
	public void DelOrgValidation(int id) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		organizationDAO.DelOrgValidation(id);
	}
	
	public void OrgValidation(int id) throws Exception{
		OrganizationUtil organizationUtil = new OrganizationUtil();
		organizationUtil.OrgValidation(id);
	}
	
	public String ValidationOrgMail(String orgid,String validationcode) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		OrganizationValidationBean orgValidationBean = organizationDAO.ValidationOrgMail(orgid,validationcode);
		
		OrganizationUtil organizationUtil = new OrganizationUtil();
		return organizationUtil.ValidationOrgMail(orgValidationBean);
	}
	
	public void UpdOrgCZZE(int id,String czze) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
		organizationDAO.UpdOrgCZZE(id,czze);
	}
}
