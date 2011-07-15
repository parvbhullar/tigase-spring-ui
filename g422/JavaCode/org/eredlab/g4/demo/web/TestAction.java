package org.eredlab.g4.demo.web;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;
import org.njdt.gg.ccl.datastructure.Dto;
import org.njdt.gg.ccl.datastructure.impl.BaseDto;
import org.njdt.gg.ccl.json.JsonHelper;
import org.njdt.gg.ccl.util.G4Utils;

/**
 * 演示用 Action
 * 
 * @author XiongChun
 * @since 2010-06-13
 * @see BaseAction
 */
public class TestAction extends BaseAction {

	/**
	 * 测试页面1初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test1Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return mapping.findForward("test1View");
	}

	/**
	 * 测试页面2初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test2Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("ID", "测试");
		return mapping.findForward("test2View");
	}

	/**
	 * 测试3页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test3Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test3View");
	}

	/**
	 * 测试4页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test4Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test4View");
	}

	/**
	 * 测试5页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test5Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test5View");
	}

	/**
	 * 测试6页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test6Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return mapping.findForward("test6View");
	}

	/**
	 * 测试7页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test7Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test7View");
	}

	/**
	 * 测试8页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test8Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test8View");
	}

	/**
	 * 测试9页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test9Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test9View");
	}

	/**
	 *测试10页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test10Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test10View");
	}

	/**
	 * 测试11页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test11Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test11View");
	}

	/**
	 * 测试12页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test12Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test12View");
	}

	/**
	 * 测试13页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test13Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test13View");
	}
	
	/**
	 * 文件上传测试
	 * 
	 * @param
	 * @return
	 */
	public ActionForward doUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		CommonActionForm cForm = (CommonActionForm) form;
		FormFile myFile = cForm.getSwfUploadFile();
		// 获取web应用根路径,也可以直接指定服务器任意盘符路径
		// String savePath = getServlet().getServletContext().getRealPath("/") +
		// "upload/201104";
		String savePath = "d:/upload/";
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
		}
		Dto outDto = new BaseDto();
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "文件上传成功");
		write(JsonHelper.encodeObject2Json(outDto), response);
		return mapping.findForward(null);
	}
	
    /**
     * 将文件写入文件系统
     * @param formFile
     * @param fileName
     * @throws Exception
     */
	public void saveFileToFileSystem(FormFile formFile) throws Exception {

	}
	
	
	
}
