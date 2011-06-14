package org.eredlab.g4.ccl.exception;

import org.eredlab.g4.ccl.util.GlobalConstants;

/**
 * G4公共异常类<br>
 * 
 * @author XiongChun
 * @since 2011-04-27
 */
public class G4Exception extends RuntimeException {

	public G4Exception() {
		super();
	}

	public G4Exception(String msg) {
		super(GlobalConstants.Exception_Head + msg);
	}
}
