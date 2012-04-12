package org.eredlab.g4.test.doctest;

import java.sql.Date;

import org.eredlab.g4.ccl.datastructure.PKey;
import org.eredlab.g4.ccl.datastructure.impl.BasePo;

/**
 * 一个学生信息实体持久化对象
 *  技术白皮书随书代码示例
 * 
 * @author XiongChun
 * @since 2011-03-29
 */
public class StudentPo extends BasePo {

	private String studentid;
	
	private String name;
	
	private Integer age;
	
	private Date birthday;

	public PKey getPk() {
		return null;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
