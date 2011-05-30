package com.ivyinfo.webtouch.conversion;

import java.util.Map;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.organization.bean.OrganizationBean;
import com.ivyinfo.purview.bean.PurviewBean;
import com.ivyinfo.user.bean.UserBean;

public interface UserConversion extends IBaseService{
	/**
	 * 创建会议系统用户
	 * @param userBean
	 * @throws Exception
	 */
	public Map MeetAddUser(UserBean userBean,UserBean suserBean,String meetorgid) throws Exception;
	
	/**
	 * 修改会议系统用户
	 * @param userBean
	 * @param suserBean
	 * @return
	 * @throws Exception
	 */
	public Map MeetUpdUser(UserBean userBean,UserBean suserBean,PurviewBean purviewBean) throws Exception;
	
	/**
	 * 删除会议系统用户
	 * @param id
	 * @param suserBean
	 * @return
	 * @throws Exception
	 */
	public Map MeetDelUser(String id,UserBean suserBean) throws Exception;
	
	/**
	 * 创建ftp用户
	 * @param userBean
	 * @throws Exception
	 */
	public void FtpAddUser(UserBean userBean) throws Exception;
	
	/**
	 * 修改ftp用户
	 * @param userBean
	 * @throws Exception
	 */
	public void FtpUpdUser(UserBean userBean) throws Exception;
	
	/**
	 * 删除ftp用户
	 * @param userBean
	 * @throws Exception
	 */
	public void FtpDelUser(UserBean userBean) throws Exception;
	
	/**
	 * 创建会议系统组织
	 * @param organizationBean
	 * @throws Exception
	 */
	public String MeetAddOrg(OrganizationBean organizationBean) throws Exception;
}
