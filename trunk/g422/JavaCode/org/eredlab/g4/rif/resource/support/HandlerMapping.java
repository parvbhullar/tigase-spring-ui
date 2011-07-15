package org.eredlab.g4.rif.resource.support;

import org.eredlab.g4.rif.resource.ResourceException;
import org.eredlab.g4.rif.resource.ResourceHandler;

/**
 * HandlerMapping
 * 
 * @author HuangYunHui|XiongChun
 * @since 2010-2-5
 */
public interface HandlerMapping {

	public ResourceHandler mapping(String pName) throws ResourceException;

}
