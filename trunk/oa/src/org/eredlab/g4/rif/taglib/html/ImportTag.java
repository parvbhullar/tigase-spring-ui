package org.eredlab.g4.rif.taglib.html;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.tplengine.DefaultTemplate;
import org.eredlab.g4.ccl.tplengine.StringTemplate;
import org.eredlab.g4.ccl.tplengine.TemplateEngine;
import org.eredlab.g4.ccl.tplengine.TemplateEngineFactory;
import org.eredlab.g4.ccl.tplengine.TemplateType;
import org.eredlab.g4.ccl.util.G4Constants;
import org.eredlab.g4.rif.taglib.util.TagHelper;

/**
 * Import标签<br>
 * 导入CSS、JS资源
 * @author XiongChun
 * @since 2010-01-30
 */
public class ImportTag extends TagSupport{
	
	private static Log log = LogFactory.getLog(ImportTag.class);
	private String src;
	
	/**
	 * 标签开始
	 */
	public int doStartTag() throws JspException{
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		String tpl = "#if($type==*css*)<link rel=*stylesheet* type=*text/css* href=*$src*/>#elseif($type==*js*)<script type=*text/javascript* src=*$src* ></script>#end";
		Dto dto = new BaseDto();
		dto.put("type", src.indexOf(".css") == -1 ? "js" : "css");
		dto.put("src", request.getContextPath() + src);
		TemplateEngine engine = TemplateEngineFactory.getTemplateEngine(TemplateType.VELOCITY);
		DefaultTemplate template = new StringTemplate(TagHelper.replaceStringTemplate(tpl));
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
		src = null;
		super.release();
	}

	public void setSrc(String src) {
		this.src = src;
	}
}
