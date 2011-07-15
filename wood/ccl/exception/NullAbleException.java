package org.njdt.gg.ccl.exception;

import org.njdt.gg.ccl.util.GlobalConstants;

/**
 * 非空异常校验类<br>
 * 
 * @author XiongChun
 * @since 2009-07-15
 */
public class NullAbleException extends RuntimeException {

	private String nullField;

	/**
	 * 构造函数1
	 * 
	 * @param 非空校验类
	 */
	public NullAbleException() {
		super(GlobalConstants.Exception_Head + "对象不能为空,请检查.");
	}

	/**
	 * 构造函数2
	 * 
	 * @param 非空校验类
	 */
	public NullAbleException(Class cs) {
		super(GlobalConstants.Exception_Head + "对象不能为空,请检查.[" + cs + "]");
	}

	/**
	 * 构造函数3
	 * 
	 * @param pNullField
	 *            异常附加信息
	 */
	public NullAbleException(String pNullField) {
		super(GlobalConstants.Exception_Head + "对象属性[" + pNullField + "]不能为空,请检查.");
		this.setNullField(pNullField);
	}

	public String getNullField() {
		return nullField;
	}

	public void setNullField(String nullField) {
		this.nullField = nullField;
	}
}
