package com.ivyinfo.user.services;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.user.bean.FtpUserBean;

public interface FtpUserServices extends IBaseService{
	/**
	 * 创建FTP用户
	 * @param ftpuserBean
	 * @return
	 * @throws Exception
	 */
	public boolean CreateFtpUser(FtpUserBean ftpuserBean) throws Exception;
	
	/**
	 * 修改FTP用户
	 * @param ftpuserBean
	 * @return
	 * @throws Exception
	 */
	public boolean UpdateFtpUser(FtpUserBean ftpuserBean) throws Exception;
	
	/**
	 * 删除FTP用户
	 * @param ftpuserBean
	 * @return
	 * @throws Exception
	 */
	public boolean DeleteFtpUser(FtpUserBean ftpuserBean) throws Exception;
}
