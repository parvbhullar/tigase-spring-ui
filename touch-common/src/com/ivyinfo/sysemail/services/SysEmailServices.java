package com.ivyinfo.sysemail.services;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.sysemail.bean.SysEmailBean;

public interface SysEmailServices extends IBaseService{
	/**
	 * 系统邮箱总记录数
	 * @return
	 * @throws Exception
	 */
	public int MailListCount() throws Exception;
	
	/**
	 * 查询系统邮箱所有信息
	 * @param from
	 * @param len
	 * @param order
	 * @param orderby
	 * @return
	 * @throws Exception
	 */
	public List AllIndex(int from, int len,String order,String orderby) throws Exception;
	
	/**
	 * 查询系统邮箱详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysEmailBean ViewSysMail(int id) throws Exception;
	
	/**
	 * 新增系统邮箱信息
	 * @param sysemailBean
	 * @throws Exception
	 */
	public void AddSysMail(SysEmailBean sysemailBean) throws Exception;
	
	/**
	 * 修改系统邮箱信息
	 * @param sysemailBean
	 * @throws Exception
	 */
	public void UpdSysMail(SysEmailBean sysemailBean) throws Exception;
	
	/**
	 * 删除系统邮箱信息
	 * @param id
	 * @throws Exception
	 */
	public void DelSysMail(int id) throws Exception;
}
