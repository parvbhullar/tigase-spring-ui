package org.eredlab.g4.rif.resource.support;

import org.eredlab.g4.rif.resource.ResourceException;

/**
 * ResourceConfigMapping
 * 
 * @author HuangYunHui|XiongChun
 * @since 2010-2-5
 */
public interface ResourceConfigMapping {
	public ResourceConfig mapping(String pUri) throws ResourceException;
}
