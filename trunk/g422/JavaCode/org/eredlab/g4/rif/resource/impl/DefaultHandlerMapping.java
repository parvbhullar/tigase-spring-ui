package org.eredlab.g4.rif.resource.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eredlab.g4.rif.resource.ResourceException;
import org.eredlab.g4.rif.resource.ResourceHandler;
import org.eredlab.g4.rif.resource.handler.CSSMinResourceHandler;
import org.eredlab.g4.rif.resource.handler.GZipResourceHandler;
import org.eredlab.g4.rif.resource.handler.JSMinResourceHandler;
import org.eredlab.g4.rif.resource.handler.NoneResourceHandler;
import org.eredlab.g4.rif.resource.support.HandlerMapping;

/**
 * DefaultHandlerMapping
 * 
 * @author HuangYunHui|XiongChun
 * @since 2009-05-1
 */
public class DefaultHandlerMapping implements HandlerMapping {
	
	private final Log logger = LogFactory.getLog(this.getClass());
	private final static GZipResourceHandler gzip = new GZipResourceHandler();
	private final static NoneResourceHandler none = new NoneResourceHandler();
	private final static CSSMinResourceHandler cssMin = new CSSMinResourceHandler();
	private final static JSMinResourceHandler jsMin = new JSMinResourceHandler();
	private static Map handlers = new HashMap();
	static {
		// key的名字必须是小写
		handlers.put("gzip", gzip);
		handlers.put("none", none);
		handlers.put("cssmin", cssMin);
		handlers.put("jsmin", jsMin);
	}

	public void put(String pHandlerName, ResourceHandler pHandler) {
		handlers.put(pHandlerName, pHandler);
	}

	public ResourceHandler mapping(String pName) throws ResourceException {
		if (pName == null) {
			throw new NullPointerException("资源Handler名称不能为空null");
		}
		String handlerName = pName.toLowerCase();
		if (handlers.containsKey(handlerName) == false) {
			return null;
		}
		ResourceHandler result = (ResourceHandler) handlers.get(handlerName);
		return result;
	}

}
