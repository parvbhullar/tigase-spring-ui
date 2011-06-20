package org.eredlab.g4.rif.resource.support;

import org.eredlab.g4.rif.resource.ResourceException;
import org.eredlab.g4.rif.resource.ResourceLoader;

/**
 * LoaderMapping
 * 
 * @author HuangYunHui|XiongChun
 * @since 2010-2-5
 */
public interface LoaderMapping {
	public ResourceLoader mapping(String pName) throws ResourceException;
}
