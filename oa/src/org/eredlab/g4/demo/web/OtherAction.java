package org.eredlab.g4.demo.web;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.ftp.FtpHelper;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Constants;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.demo.service.DemoService;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

import com.xzb.oa.knowledge.service.KService;

/**
 * 其他标准范例暨教程Action 文件管理、
 * 
 * @author XiongChun
 * @since 2011-04-09
 * @see BaseAction
 */
public class OtherAction extends BaseAction {

	private DemoService demoService = (DemoService) getService("demoService");
	
	private KService kService = (KService) getService("kService");

	/**
	 * 文件操作页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward uploadInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("fileUploadView");
	}

	/**
	 * 查询文件列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryFileDatas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		String sqlid = G4Utils.defaultJdbcTypeOracle() ? "Demo.queryFiles4Oracle" : "Demo.queryFiles";
		List list = g4Reader.queryForPage(sqlid, dto);
		Integer countInteger = (Integer) g4Reader.queryForObject("Demo.countFiles", dto);
		String jsonString = JsonHelper.encodeList2PageJson(list, countInteger, G4Constants.FORMAT_DateTime);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}

	/**
	 * Web表单文件上传 单个/批量同理
	 * 
	 * @param
	 * @return
	 */
	public ActionForward doUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm cForm = (CommonActionForm) form;
		// 单个文件,如果是多个就cForm.getFile2()....支持最多5个文件
		FormFile myFile = cForm.getFile1();
		// 获取web应用根路径,也可以直接指定服务器任意盘符路径
		String savePath = getServlet().getServletContext().getRealPath("/") + "uploaddata/";
		//String savePath = "d:/upload/";
		// 检查路径是否存在,如果不存在则创建之
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdir();
		}
		// 文件按天归档
		savePath = savePath + G4Utils.getCurDate() + "/";
		File file1 = new File(savePath);
		if (!file1.exists()) {
			file1.mkdir();
		}
		// 文件真实文件名
		String fileName = myFile.getFileName();
		// 我们一般会根据某种命名规则对其进行重命名
		// String fileName = ;
		File fileToCreate = new File(savePath, fileName);
		// 检查同名文件是否存在,不存在则将文件流写入文件磁盘系统
		if (!fileToCreate.exists()) {
			FileOutputStream os = new FileOutputStream(fileToCreate);
			os.write(myFile.getFileData());
			os.flush();
			os.close();
		} else {
			// 此路径下已存在同名文件,是否要覆盖或给客户端提示信息由你自己决定
			FileOutputStream os = new FileOutputStream(fileToCreate);
			os.write(myFile.getFileData());
			os.flush();
			os.close();
		}
		// 我们通常还会把这个文件的相关信息持久化到数据库
		Dto inDto = cForm.getParamAsDto(request);
		inDto.put("title", G4Utils.isEmpty(inDto.getAsString("title")) ? fileName : inDto.getAsString("title"));
		inDto.put("filesize", myFile.getFileSize());
		inDto.put("path", savePath + fileName);
		demoService.doUpload(inDto);
		setOkTipMsg("文件上传成功", response);
		return mapping.findForward(null);
	}

	/**
	 * 删除文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delFiles(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		String[] strChecked = dto.getAsString("strChecked").split(",");
		for (int i = 0; i < strChecked.length; i++) {
			String fileid = strChecked[i];
			Dto fileDto = (BaseDto) g4Reader.queryForObject("Demo.queryFileByFileID", fileid);
			String path = fileDto.getAsString("path");
			File file = new File(path);
			file.delete();
			demoService.delFile(fileid);
		}
		setOkTipMsg("文件删除成功", response);
		return mapping.findForward(null);
	}

	/**
	 * 下载文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		String fileid = dto.getAsString("fileid");
		Dto fileDto = (BaseDto) g4Reader.queryForObject("Demo.queryFileByFileID", fileid);
		//这里可稍做优化,根据文件类型动态设置此属性
		//response.setContentType("application/vnd.ms-excel");
		String filename = G4Utils.encodeChineseDownloadFileName(request, fileDto.getAsString("title"));
		response.setHeader("Content-Disposition", "attachment; filename=" + filename + ";");
		String path = fileDto.getAsString("path");
		File file = new File(path);
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));  
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);  
        byte[] temp = new byte[1024];  
        int size = 0;  
        while ((size = in.read(temp)) != -1) {  
            out.write(temp, 0, size);  
        }  
        in.close();
		ServletOutputStream os = response.getOutputStream();
		os.write(out.toByteArray());
		os.flush();
		os.close();
		return mapping.findForward(null);
	}
	
	/**
	 * Flash组件文件上传 
	 * 如果是批量则客户端的SWF会循环来调用这个方法
	 * 
	 * @param
	 * @return
	 */
	public ActionForward doUploadBasedFlah(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
	

		//UserInfoVo userInfo = (UserInfoVo) request.getSession().getAttribute("userInfo");
		//String userId="10004894";//userInfo.getUserid();
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		FormFile myFile = cForm.getSwfUploadFile();	
		String dirId=(String)request.getParameter("dirId");
		String userId=request.getParameter("loginuserid");
		String person=request.getParameter("person");
		/**
		if(myFile.getFileSize()>109715200)//209715200
		{
			
			
			boolean b = true;
			String loginuserid=userId;
			long time=System.currentTimeMillis();
			String fileName1 = myFile.getFileName();
			//FTP开始
			//封装的FTP还有其他处理方式,这只是其中一种,更多API请查看FtpHelper.java
			FtpHelper ftpHelper = new FtpHelper();
			ftpHelper.createConnection("192.168.18.239", "anonymous", "", 21);
			ftpHelper.useWorkingDir("/TEMP/"+loginuserid+"/"+time);
			//ftpHelper.useWorkingDir("/"+loginuserid);
			b = ftpHelper.storeFile(myFile.getInputStream(), myFile.getFileName());
			//释放连接非常重要
			ftpHelper.disconnect();
			
			inDto.put("title", G4Utils.isEmpty(inDto.getAsString("title")) ? fileName1 : inDto.getAsString("title") );
			inDto.put("filesize", myFile.getFileSize());
			inDto.put("fileType", "1");
			inDto.put("fileName", myFile.getFileName());
			inDto.put("dirName", loginuserid+"/"+time);
			Dto tOutDto=demoService.doUploadFtp(inDto);
			
			String msg = "";
			if (b) {
				msg = "文件上传成功";
			}else {
				msg = "文件上传失败";
			}
			setOkTipMsg(msg, response);
			return mapping.findForward(null);
			
		}else{
				// 获取web应用根路径,也可以直接指定服务器任意盘符路径
				*/
				Dto pDto=new BaseDto();
				pDto.put("dirId", dirId);
				//Dto dto=kService.getDirItems(pDto);
				//String dirName=dto.get("filepath").toString();
				
				String basePath = getServlet().getServletContext().getRealPath("")+"\\"+"uploaddata";
				File baseFile=new File(basePath);
				if(!baseFile.exists())
				{
					baseFile.mkdir();
				}
				if(person.equals("0"))
				{
					basePath+="\\"+"01";
				}else{
					basePath+="\\"+"02";
				}
				File baseFile1=new File(basePath);
				if(!baseFile1.exists())
				{
					baseFile1.mkdir();
				}
				
					basePath=basePath+"\\"+userId;
					File userFile=new File(basePath);
					if(!userFile.exists())
					{
						userFile.mkdir();
					}
				
				String type="";
				if(person.equals("1"))
				{
					type="02";
				}else
				{
					type="01";
				}
				long currentTime=System.currentTimeMillis();
				
			
				String savePath = basePath + "\\"+currentTime;
				//String savePath = "d:/upload/";
				// 检查路径是否存在,如果不存在则创建之
				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdir();
				}
				// 文件按天归档
		//		savePath = savePath +  "/";
		//		File file1 = new File(savePath);
		//		if (!file1.exists()) {
		//			file1.mkdir();
		//		}
				// 文件真实文件名
				String fileName = myFile.getFileName();
				// 我们一般会根据某种命名规则对其进行重命名
				// String fileName = ;
				File fileToCreate = new File(savePath, fileName);
				// 检查同名文件是否存在,不存在则将文件流写入文件磁盘系统
				if (!fileToCreate.exists()) {
					FileOutputStream os = new FileOutputStream(fileToCreate);
					os.write(myFile.getFileData());
					os.flush();
					os.close();
				} else {
					// 此路径下已存在同名文件,是否要覆盖或给客户端提示信息由你自己决定
					FileOutputStream os = new FileOutputStream(fileToCreate);
					os.write(myFile.getFileData());
					os.flush();
					os.close();
				}
				// 我们通常还会把这个文件的相关信息持久化到数据库
				
				inDto.put("title", G4Utils.isEmpty(inDto.getAsString("title")) ? fileName : inDto.getAsString("title"));
				inDto.put("filesize", myFile.getFileSize());
				inDto.put("path", savePath+"\\" + fileName);
				inDto.put("fileName", fileName);
				inDto.put("upLoadType", "0");
				inDto.put("dirName", "uploaddata"+"/"+type+"/"+userId+"/"+currentTime+"/");
				Dto tOutDto=demoService.doUpload(inDto);
				Dto outDto = new BaseDto(G4Constants.TRUE, "文件上传成功");
				outDto.put("fileid", tOutDto.get("fileid"));
				outDto.put("filename", fileName);
				outDto.put("dirname", "uploaddata"+"/"+type+"/"+userId+"/"+currentTime+"/");
				write(outDto.toJson(), response);
				return mapping.findForward(null);
		//}
	}
	
	/**
	 * FTP上传
	 * 
	 * @param
	 * @return
	 */
	public Dto doUploadFtp(Dto inDto)
	{
		return null;
	}
	
	
	
	public ActionForward doUploadByFTP(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm cForm = (CommonActionForm) form;
		boolean b = true;
		String loginuserid=request.getParameter("loginuserid");
		long time=System.currentTimeMillis();
		FormFile myFile = cForm.getFile1();
		//FTP开始
		//封装的FTP还有其他处理方式,这只是其中一种,更多API请查看FtpHelper.java
		FtpHelper ftpHelper = new FtpHelper();
		ftpHelper.createConnection("192.168.18.239", "anonymous", "", 21);
		ftpHelper.useWorkingDir("/TEMP/"+loginuserid+"/"+time);
		//ftpHelper.useWorkingDir("/"+loginuserid);
		b = ftpHelper.storeFile(myFile.getInputStream(), myFile.getFileName());
		//释放连接非常重要
		ftpHelper.disconnect();
		
		Dto inDto = cForm.getParamAsDto(request);
		//inDto.put("title", myFile.getFileName());
		inDto.put("filesize", myFile.getFileSize());
		inDto.put("upLoadType", "1");
		inDto.put("fileName", myFile.getFileName());
		inDto.put("dirName", loginuserid+"/"+time+"/");
		Dto tOutDto=demoService.doUploadFtp(inDto);
		Dto outDto = new BaseDto(G4Constants.TRUE, "ftp文件上传成功");
		outDto.put("fileid", tOutDto.get("fileid"));
		outDto.put("filename", myFile.getFileName());
		outDto.put("dirname", loginuserid+"/"+time+"/");
		outDto.put("upLoadType", "1");
		//Dto ftpDto=new BaseDto();
		//ftpDto.put("json", value)
		write(outDto.toJson(), response);
	   /**
		String msg = "";
		if (b) {
			msg = "文件上传成功";
		}else {
			msg = "文件上传失败";
		}
		setOkTipMsg(msg, response);
		*/
		return mapping.findForward(null);
	}
	
}
