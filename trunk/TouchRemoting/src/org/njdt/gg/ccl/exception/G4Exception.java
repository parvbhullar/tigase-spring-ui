package org.njdt.gg.ccl.exception;

import org.njdt.gg.ccl.util.GlobalConstants;

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
