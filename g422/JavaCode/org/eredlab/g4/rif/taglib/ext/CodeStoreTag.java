package org.eredlab.g4.rif.taglib.ext;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eredlab.g4.rif.taglib.util.TagConstant;
import org.eredlab.g4.rif.taglib.util.TagHelper;
import org.eredlab.g4.rif.util.WebUtils;
import org.njdt.gg.ccl.datastructure.Dto;
import org.njdt.gg.ccl.datastructure.impl.BaseDto;
import org.njdt.gg.ccl.tplengine.DefaultTemplate;
import org.njdt.gg.ccl.tplengine.FileTemplate;
import org.njdt.gg.ccl.tplengine.TemplateEngine;
import org.njdt.gg.ccl.tplengine.TemplateEngineFactory;
import org.njdt.gg.ccl.tplengine.TemplateType;
import org.njdt.gg.ccl.util.GlobalConstants;

/**
 * CodeStoreTag标签<br>
 * 导入Ext扩展组件的CSS、JS资源
* @author njdt
 * @since 2010-01-30
 */
public class CodeStoreTag extends TagSupport{
	
	private static Log log = LogFactory.getLog(CodeStoreTag.class);
	private String fields;
	private String showCode = "false";
	
	/**
	 * 标签开始
	 */
	public int doStartTag() throws JspException{
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		StringBuffer sb = new StringBuffer();
		sb.append(TagConstant.SCRIPT_START);
		Dto dto = new BaseDto();
		dto.put("showCode", showCode.toLowerCase());
		String[] arrayFields = fields.split(",");
		TemplateEngine engine = TemplateEngineFactory.getTemplateEngine(TemplateType.VELOCITY);
		DefaultTemplate template = new FileTemplate();
		template.setTemplateResource(TagHelper.getTemplatePath(getClass().getName()));
		for (int i = 0; i < arrayFields.length; i++) {
			List codeList = WebUtils.getCodeListByField(arrayFields[i], request);
			dto.put("codeList", codeList);
			dto.put("field", arrayFields[i]);
			StringWriter writer = engine.mergeTemplate(template, dto);
			sb.append(writer.toString());
		}
		sb.append(TagConstant.SCRIPT_END);
		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			log.error(GlobalConstants.Exception_Head + e.getMessage());
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
		fields = null;
		showCode = null;
		super.release();
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public void setShowCode(String showCode) {
		this.showCode = showCode;
	}

}
