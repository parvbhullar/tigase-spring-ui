package org.eredlab.g4.rif.resource.loader;

import org.eredlab.g4.rif.resource.ResourceException;

/**
 * NotFoundResourceException
 * 
 * @author HuangYunHui|XiongChun
 * @since 2009-08-3
 */
public class NotFoundResourceException extends ResourceException {

	private static final long serialVersionUID = 1L;

	public NotFoundResourceException() {
		super();
	}

	public NotFoundResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundResourceException(String message) {
		super(message);
	}

	public NotFoundResourceException(Throwable cause) {
		super(cause);
	}

}
