package org.eredlab.g4.test.doctest;

import org.eredlab.g4.ccl.datastructure.impl.BaseVo;

/**
 * 一个学生选课VO对象 
 * 技术白皮书随书代码示例
 * 
 * @author XiongChun
 * @since 2011-03-29
 */
public class RegCourceVO extends BaseVo {
	
	private String regCourceId;
	
	private String studentId;
	
	private String courceId;
	
	private String remark;

	public String getRegCourceId() {
		return regCourceId;
	}

	public void setRegCourceId(String regCourceId) {
		this.regCourceId = regCourceId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCourceId() {
		return courceId;
	}

	public void setCourceId(String courceId) {
		this.courceId = courceId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
