package com.ivyinfo.test.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.test.bean.TestBean;

public interface ITestDAO extends IBaseDAO<TestBean>{
	/**
	 * 添加一本图书至数据库中
	 * 
	 * @param book
	 *            图书对象
	 * @throws RuntimeException
	 */
	public void saveTest(TestBean testBean) throws RuntimeException;

	/**
	 * 删除图书信息
	 * 
	 * @param id
	 *            ID编码
	 * @throws RuntimeException
	 */
	public void deleteTest(int id) throws RuntimeException;

	/**
	 * 更新一本图书的信息
	 * 
	 * @param book
	 *            图书对象
	 * @throws RuntimeException
	 */
	public void updateTest(TestBean testBean) throws RuntimeException;

	/**
	 * 查找库中所有的图书
	 * 
	 * @return 返回图书列表
	 * @throws RuntimeException
	 */
	public List findAllTest() throws RuntimeException;

	/**
	 * 通过ID查找特定的图书
	 * 
	 * @param id
	 *            图书的ID号
	 * @return 返回此ID对应的图书信息
	 * @throws RuntimeException
	 */
	public TestBean findTestById(int id) throws RuntimeException;
	
	public List findTestByPage(int from, int len) throws RuntimeException;
	
	public int count() throws RuntimeException;
}