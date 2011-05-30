package com.ivyinfo.webdisk.services;

import com.ivyinfo.webdisk.bean.DiskBean;
import com.ivyinfo.webdisk.bean.WebDiskBean;

public interface IWebDiskServices {
	
	public String uploadFile(WebDiskBean webDiskBean)throws Exception;
	
	public void download(DiskBean diskBean)throws Exception;
}
