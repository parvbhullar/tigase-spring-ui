package com.ivyinfo.organization.services;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.organization.bean.OrganizationBean;
import com.ivyinfo.organization.bean.OrganizationValidationBean;

public interface OrganizationServices extends IBaseService{
	public int OrgListCount(String conname,String convalue) throws Exception;
	
	public List AllIndex(int from, int len,String order,String orderby,String conname,String convalue) throws Exception;
	
	public String AddSubmit(OrganizationBean organizationBean) throws Exception;
	
	public void AddSubmitContact(OrganizationBean organizationBean) throws Exception;
	
	public OrganizationBean View(int id) throws Exception;
	
	public OrganizationBean Upd(int id) throws Exception;
	
	public void UpdSubmit(OrganizationBean organizationBean) throws Exception;
	
	public void UpdSubmitContact(OrganizationBean organizationBean) throws Exception;
	
	public void Del(int id) throws Exception;
	
	public void DelContact(String id) throws Exception;
	
	/**
	 * 查询机构信息表名称是否重复
	 * @param orgname
	 * @return
	 * @throws Exception
	 */
	public int ValidationOrgName(String orgname) throws Exception;
	
	/**
	 * 查询机构验证信息表名称是否重复
	 * @param orgname
	 * @return
	 * @throws Exception
	 */
	public int VOrgName(String orgname) throws Exception;
	
	/**
	 * 新增机构验证信息表
	 * @param organizationValidationBean
	 * @throws Exception
	 */
	public void AddOrgValidation(OrganizationValidationBean organizationValidationBean) throws Exception;
	
	/**
	 * 修改机构验证信息表
	 * @param organizationValidationBean
	 * @throws Exception
	 */
	public void UpdOrgValidation(OrganizationValidationBean organizationValidationBean) throws Exception;
	
	/**
	 * 删除机构验证信息表
	 * @param id
	 * @throws Exception
	 */
	public void DelOrgValidation(int id) throws Exception;
	
	/**
	 * 查看机构验证详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public OrganizationValidationBean ViewOrgValidation(int id) throws Exception;
	
	/**
	 * 根据机构id和验证码查看机构验证详细信息
	 * @param orgid
	 * @param validationcode
	 * @return
	 * @throws Exception
	 */
	public String ValidationOrgMail(String orgid,String validationcode) throws Exception;
	
	/**
	 * 修改机构冲值总额信息
	 * @param id
	 * @param czze
	 * @throws Exception
	 */
	public void UpdOrgCZZE(int id,String czze) throws Exception;
}
