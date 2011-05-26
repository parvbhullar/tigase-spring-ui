package com.ivyinfo.user.services;

import com.ivyinfo.user.bean.FtpUserBean;
import com.ivyinfo.user.util.FtpUserUtil;

public class FtpUserServicesImpl implements FtpUserServices{
	
	public boolean CreateFtpUser(FtpUserBean ftpuserBean) throws Exception{
		FtpUserUtil ftpuserUtil = new FtpUserUtil();
		boolean returnvalue = ftpuserUtil.CreateFtpUser(ftpuserBean);
		
		return returnvalue;
	}
	
	public boolean UpdateFtpUser(FtpUserBean ftpuserBean) throws Exception{
		FtpUserUtil ftpuserUtil = new FtpUserUtil();
		boolean returnvalue = ftpuserUtil.UpdateFtpUser(ftpuserBean);
		
		return returnvalue;
	}
	
	public boolean DeleteFtpUser(FtpUserBean ftpuserBean) throws Exception{
		FtpUserUtil ftpuserUtil = new FtpUserUtil();
		boolean returnvalue = ftpuserUtil.DeleteFtpUser(ftpuserBean);
		
		return returnvalue;
	}
}
