package org.eredlab.g4.arm.web.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.arm.web.tag.vo.MenuVo;
import org.eredlab.g4.bmf.base.IDao;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.tplengine.DefaultTemplate;
import org.eredlab.g4.ccl.tplengine.FileTemplate;
import org.eredlab.g4.ccl.tplengine.TemplateEngine;
import org.eredlab.g4.ccl.tplengine.TemplateEngineFactory;
import org.eredlab.g4.ccl.tplengine.TemplateType;
import org.eredlab.g4.ccl.util.G4Constants;
import org.eredlab.g4.rif.taglib.util.TagHelper;
import org.eredlab.g4.rif.util.WebUtils;

/**
 * ArmRoleGrantMenuTree标签:eRedG4_ARM专用
 * 
 * @author XiongChun
 * @since 2010-05-22
 */
public class ArmRoleGrantMenuTreeTag extends TagSupport {

	private static Log log = LogFactory.getLog(ArmRoleGrantMenuTreeTag.class);
	private String key = "";
	private String authorizelevel = "1";

	/**
	 * 标签开始
	 */
	public int doStartTag() throws JspException {
		IDao g4Dao = (IDao) SpringBeanLoader.getSpringBean("g4Dao");
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		Dto grantDto = new BaseDto();
		grantDto.put("roleid", request.getParameter("roleid"));
		grantDto.put("authorizelevel", authorizelevel);
		List grantedList = g4Dao.queryForList("ArmTagSupport.queryGrantedMenusByRoleId", grantDto);
		List menuList = new ArrayList();
		String account = WebUtils.getSessionContainer(request).getUserInfo().getAccount();
		String developerAccount = WebUtils.getParamValue("DEFAULT_DEVELOP_ACCOUNT", request);
		String superAccount = WebUtils.getParamValue("DEFAULT_ADMIN_ACCOUNT", request);
		Dto qDto = new BaseDto();
		String userid = WebUtils.getSessionContainer(request).getUserInfo().getUserid();
		qDto.put("userid", userid);
		String roletype = request.getParameter("roletype");
		String menutype = ArmConstants.MENUTYPE_SYSTEM;
		if (roletype.equals(ArmConstants.ROLETYPE_BUSINESS)) {
			menutype = ArmConstants.MENUTYPE_BUSINESS;
		}
		if (authorizelevel.equals(ArmConstants.AUTHORIZELEVEL_ADMIN)) {
			menutype = ArmConstants.MENUTYPE_BUSINESS;
		}
		qDto.put("roleid", roletype);
		qDto.put("menutype", menutype);
		if (account.equalsIgnoreCase(developerAccount) || account.equalsIgnoreCase(superAccount)) {
			menuList = g4Dao.queryForList("ArmTagSupport.queryMenusForRoleGrant", qDto);
		} else {
			qDto.put("menutype", ArmConstants.MENUTYPE_BUSINESS);
			menuList = g4Dao.queryForList("ArmTagSupport.queryMenusForGrant", qDto);
		}
		for (int i = 0; i < menuList.size(); i++) {
			MenuVo menuVo = (MenuVo) menuList.get(i);
			if (checkGeant(grantedList, menuVo.getMenuid()).booleanValue()) {
				menuVo.setChecked("true");
			} else {
				menuVo.setChecked("false");
			}
			if (menuVo.getParentid().equals("0")) {
				menuVo.setIsRoot("true");
			}
			if (menuVo.getMenuid().length() < 6) {
				menuVo.setExpanded("true");
			}
		}
		Dto dto = new BaseDto();
		dto.put("menuList", menuList);
		dto.put("key", key);
		dto.put("authorizelevel", authorizelevel);
		TemplateEngine engine = TemplateEngineFactory.getTemplateEngine(TemplateType.VELOCITY);
		DefaultTemplate template = new FileTemplate();
		template.setTemplateResource(TagHelper.getTemplatePath(getClass().getName()));
		StringWriter writer = engine.mergeTemplate(template, dto);
		try {
			pageContext.getOut().write(writer.toString());
		} catch (IOException e) {
			log.error(G4Constants.Exception_Head + e.getMessage());
			e.printStackTrace();
		}
		return super.SKIP_BODY;
	}

	/**
	 * 检查授权
	 * 
	 * @param grantList
	 * @param pMenuid
	 * @return
	 */
	private Boolean checkGeant(List grantList, String pMenuid) {
		Boolean result = new Boolean(false);
		for (int i = 0; i < grantList.size(); i++) {
			Dto dto = (BaseDto) grantList.get(i);
			if (pMenuid.equals(dto.getAsString("menuid"))) {
				result = new Boolean(true);
			}
		}
		return result;
	}

	/**
	 * 标签结束
	 */
	public int doEndTag() throws JspException {
		return super.EVAL_PAGE;
	}

	/**
	 * 释放资源
	 */
	public void release() {
		setKey(null);
		setAuthorizelevel(null);
		super.release();
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setAuthorizelevel(String authorizelevel) {
		this.authorizelevel = authorizelevel;
	}
}
