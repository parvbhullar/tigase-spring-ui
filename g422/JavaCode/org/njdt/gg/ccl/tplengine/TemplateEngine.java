package org.njdt.gg.ccl.tplengine;

import java.io.StringWriter;

import org.njdt.gg.ccl.datastructure.Dto;

/**
 * 模板引擎接口
* @author njdt
 * @since 2009-07-26
 */
public interface TemplateEngine {
	/**
	 * 驱动模板
	 * @param pTemplate 模板对象
	 * @param pDto 合并参数集合(将模板中所需变量全部压入Dto)
	 * @return 引擎驱动后的StringWriter对象
	 */
	public StringWriter mergeTemplate(DefaultTemplate pTemplate, Dto pDto);
	
}
