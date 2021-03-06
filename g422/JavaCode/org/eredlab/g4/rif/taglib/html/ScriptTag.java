package org.eredlab.g4.rif.taglib.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.njdt.gg.ccl.util.GlobalConstants;

/**
 * Script标签
* @author njdt
 * @since 2010-02-30
 */
public class ScriptTag extends TagSupport {
	private static Log log = LogFactory.getLog(DivTag.class);

    /**
     * 标签开始
     */
    public int doStartTag() throws JspException{
		String begin = "<script type=\"text/javascript\">";
		try {
			pageContext.getOut().write(begin);
		} catch (IOException e) {
			log.error(GlobalConstants.Exception_Head + e.getMessage());
			e.printStackTrace();
		}
		return super.EVAL_BODY_INCLUDE;
    }
    
    /**
     * 标签结束
     */
    public int doEndTag() throws JspException{
		try {
			pageContext.getOut().write("</script>");
		} catch (IOException e) {
			log.error(GlobalConstants.Exception_Head + e.getMessage());
			e.printStackTrace();
		}
    	return super.EVAL_PAGE;
    }
    
    /**
     * 释放资源
     */
    public void release(){
    	super.release();
    }
}
