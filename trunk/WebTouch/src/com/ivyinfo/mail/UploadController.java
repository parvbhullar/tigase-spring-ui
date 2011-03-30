package com.ivyinfo.mail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.bean.DeleteMailBean;
import com.ivyinfo.mail.bean.FileBean;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.mail.services.AuxiliaryMailServices;
import com.ivyinfo.mail.services.SendMailServices;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.util.Constant;
import com.ivyinfo.util.Page;
import com.ivyinfo.webdisk.bean.DiskBean;
import com.ivyinfo.webdisk.services.IWebDiskServices;

@Controller
public class UploadController{
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
	
	/**
	 * 删除上传的文件
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/mail/deleteFile.do",method = RequestMethod.POST)
	public void deleteFile(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
		UserBean userBean=sessionUserBean.getUserBean();
		String fileName=request.getParameter("deleteFileName");
		LOGGER.info("fileName="+fileName);
		File uploadedFile=new File(request.getRealPath("")+"/temp/",fileName);
		uploadedFile.delete();
	}
}