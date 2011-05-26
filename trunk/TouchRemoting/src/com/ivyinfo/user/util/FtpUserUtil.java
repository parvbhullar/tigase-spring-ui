package com.ivyinfo.user.util;

import com.ivyinfo.framework.common.ftp.FtpTools;
import com.ivyinfo.user.bean.FtpUserBean;

public class FtpUserUtil {
	
	public boolean CreateFtpUser(FtpUserBean ftpuserBean) throws Exception{
		FtpTools ftpTools = new FtpTools();
		boolean returnvalue = ftpTools.CreateFtpUser(ftpuserBean);
		
		return returnvalue;
	}
	
	public boolean UpdateFtpUser(FtpUserBean ftpuserBean) throws Exception{
		FtpTools ftpTools = new FtpTools();
		boolean returnvalue = ftpTools.UpdateFtpUser(ftpuserBean);
		
		return returnvalue;
	}
	
	public boolean DeleteFtpUser(FtpUserBean ftpuserBean) throws Exception{
		FtpTools ftpTools = new FtpTools();
		boolean returnvalue = ftpTools.DeleteFtpUser(ftpuserBean);
		
		return returnvalue;
	}
}
