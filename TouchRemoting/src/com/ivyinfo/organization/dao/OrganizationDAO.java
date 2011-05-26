package com.ivyinfo.organization.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.organization.bean.OrganizationBean;
import com.ivyinfo.organization.bean.OrganizationValidationBean;

public interface OrganizationDAO extends IBaseDAO{
	public int OrgListCount(String conname,String convalue) throws Exception;
	
	public List AllIndex(int from, int len,String order,String orderby,String conname,String convalue) throws Exception;
	
	public void AddSubmit(OrganizationBean organizationBean) throws Exception;
	
	public void AddSubmitContact(OrganizationBean organizationBean) throws Exception;
	
	public OrganizationBean View(int id) throws Exception;
	
	public OrganizationBean Upd(int id) throws Exception;
	
	public void UpdSubmit(OrganizationBean organizationBean) throws Exception;
	
	public void UpdSubmitContact(OrganizationBean organizationBean) throws Exception;
	
	public void Del(int id) throws Exception;
	
	public void DelContact(String id) throws Exception;
	
	public int ValidationOrgName(String orgname) throws Exception;
	
	public int VOrgName(String orgname) throws Exception;
	
	public OrganizationValidationBean ViewOrgValidation(int id) throws Exception;
	
	public void AddOrgValidation(OrganizationValidationBean organizationValidationBean) throws Exception;
	
	public void UpdOrgValidation(OrganizationValidationBean organizationValidationBean) throws Exception;
	
	public void DelOrgValidation(int id) throws Exception;
	
	public OrganizationValidationBean ValidationOrgMail(String orgid,String validationcode) throws Exception;
	
	public void UpdOrgCZZE(int id,String czze) throws Exception;
}
