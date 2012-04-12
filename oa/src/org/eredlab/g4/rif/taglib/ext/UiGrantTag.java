package org.eredlab.g4.rif.taglib.ext;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eredlab.g4.arm.vo.UserInfoVo;
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
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.rif.taglib.util.TagHelper;
import org.eredlab.g4.rif.util.WebUtils;

/**
 * 
 * UiGrant标签<br>
 * 实现UI元素授权
 * 
 * @author XiongChun
 * @since 2011-09-30
 */
public class UiGrantTag extends TagSupport{
	
	private static Log log = LogFactory.getLog(UiGrantTag.class);
	
	
	/**
	 * 标签开始
	 */
	public int doStartTag() throws JspException{
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		IDao g4Dao = (IDao) SpringBeanLoader.getSpringBean("g4Dao");
		Dto qDto = new BaseDto();
		UserInfoVo userInfoVo = WebUtils.getSessionContainer(request).getUserInfo();
		qDto.put("userid", userInfoVo.getUserid());
		qDto.put("menuid", request.getParameter("menuid4Log"));
		List roleGrantList = g4Dao.queryForList("ArmTagSupport.getUiRoleGrantInfo", qDto);
		List userGrantList = g4Dao.queryForList("ArmTagSupport.getUiUserGrantInfo", qDto);
		List grantList = new ArrayList();
		if (G4Utils.isNotEmpty(roleGrantList)) {
			grantList.addAll(roleGrantList);
		}
		if (G4Utils.isNotEmpty(userGrantList)) {
			grantList.addAll(userGrantList);
		}
		Dto dto = new BaseDto();
		dto.put("grantList", grantList);
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
	 * 标签结束
	 */
	public int doEndTag() throws JspException{
		return super.EVAL_PAGE;
	}
	
	/**
	 * 释放资源
	 */
	public void release(){
		super.release();
	}
}
