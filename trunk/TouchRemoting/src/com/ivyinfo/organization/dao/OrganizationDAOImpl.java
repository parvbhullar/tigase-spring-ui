package com.ivyinfo.organization.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.organization.bean.OrganizationBean;
import com.ivyinfo.organization.bean.OrganizationValidationBean;

public class OrganizationDAOImpl extends SqlMapClientDaoSupport implements OrganizationDAO{
	
	public int OrgListCount(String conname,String convalue) throws Exception{
		Map map = new HashMap();
		if(conname != null && convalue != null){
			String convalue1 = "like '%"+convalue+"%'";
			map.put("conname", conname);
			map.put("convalue", convalue1);
		}
		String s = (String)this.getSqlMapClientTemplate().queryForObject("organizationcount",map);
		return Integer.parseInt(s);
	}
	
	public List AllIndex(int from, int len,String order,String orderby,String conname,String convalue) throws Exception{
		Map map = new HashMap();
		if(order != null){
			map.put("order", "a."+order);
			map.put("orderby", orderby);
		}
		if(from !=0 && len !=0){
			map.put("from", from);
			map.put("len", len);
		}
		if(conname != null && convalue != null){
			String convalue1 = "like '%"+convalue+"%'";
			map.put("conname", conname);
			map.put("convalue", convalue1);
		}
		
		return this.getSqlMapClientTemplate().queryForList("findAllOrganization", map);
	}
	
	public void AddSubmit(OrganizationBean organizationBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveOrganization", organizationBean);
	}
	
	public void AddSubmitContact(OrganizationBean organizationBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveOrganizationContact", organizationBean);
	}
	
	public OrganizationBean View(int id) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		
		return (OrganizationBean) this.getSqlMapClientTemplate().queryForObject("findAllOrganization", map);
	}
	
	public OrganizationBean Upd(int id) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		return (OrganizationBean) this.getSqlMapClientTemplate().queryForObject("findAllOrganization", map);
	}
	
	public void UpdSubmit(OrganizationBean organizationBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateOrganization", organizationBean);
	}
	
	public void UpdSubmitContact(OrganizationBean organizationBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateOrganizationContact", organizationBean);
	}
	
	public void Del(int id) throws Exception{
		this.getSqlMapClientTemplate().delete("deleteOrganization", id);
	}
	
	public void DelContact(String id) throws Exception{
		this.getSqlMapClientTemplate().delete("deleteOrganizationContact", id);
	}
	
	public int ValidationOrgName(String orgname) throws Exception{
		Map map = new HashMap();
		map.put("orgname", orgname);
		String s = (String)this.getSqlMapClientTemplate().queryForObject("orgname",map);
		return Integer.parseInt(s);
	}
	
	public int VOrgName(String orgname) throws Exception{
		Map map = new HashMap();
		map.put("orgname", orgname);
		String s = (String)this.getSqlMapClientTemplate().queryForObject("vorgname",map);
		return Integer.parseInt(s);
	}
	
	public OrganizationValidationBean ViewOrgValidation(int id) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		return (OrganizationValidationBean) this.getSqlMapClientTemplate().queryForObject("findAllOrgValidation", map);
	}
	
	public void AddOrgValidation(OrganizationValidationBean organizationValidationBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveOrganizationValidation", organizationValidationBean);
	}
	
	public void UpdOrgValidation(OrganizationValidationBean organizationValidationBean) throws Exception{
		this.getSqlMapClientTemplate().update("updOrganizationValidation", organizationValidationBean);
	}
	
	public void DelOrgValidation(int id) throws Exception{
		this.getSqlMapClientTemplate().delete("delOrganizationValidation", id);
	}
	
	public OrganizationValidationBean ValidationOrgMail(String orgid,String validationcode) throws Exception{
		Map map = new HashMap();
		map.put("orgid", orgid);
		map.put("validationcode", validationcode);
		return (OrganizationValidationBean) this.getSqlMapClientTemplate().queryForObject("findOrgValidation", map);
	}
	
	public void UpdOrgCZZE(int id,String czze) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		map.put("czze", czze);
		this.getSqlMapClientTemplate().update("UpdOrgCZZE", map);
	}
}
