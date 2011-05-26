package com.ivyinfo.test.services;

import java.util.List;
import java.util.Map;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.test.bean.TestBean;

public interface ITestServices extends IBaseService{
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
	public void removeTest(int id) throws RuntimeException;

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
	public List getAllTest() throws RuntimeException;

	/**
	 * 通过图书ID号得到图书对象
	 * 
	 * @param id
	 *            图书ID号码
	 * @return 返回此ID对应的图书信息
	 * @throws RuntimeException
	 */
	public TestBean getTestById(int id) throws RuntimeException;

	public List getTestByPage(int from, int len) throws RuntimeException;

	public int count() throws RuntimeException;
	
//	public void getTestTree(Map session) throws RuntimeException;
	
	
}
