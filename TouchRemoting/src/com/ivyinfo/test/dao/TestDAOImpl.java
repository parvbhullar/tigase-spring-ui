package com.ivyinfo.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.framework.service.base.BaseDao;
import com.ivyinfo.test.bean.TestBean;

public class TestDAOImpl extends SqlMapClientDaoSupport implements ITestDAO {
	
	public void saveTest(TestBean testBean) throws RuntimeException {
		this.getSqlMapClientTemplate().insert("saveTest", testBean);
	}

	public void deleteTest(int id) throws RuntimeException {
		this.getSqlMapClientTemplate().delete("deleteTest", id);
	}

	public void updateTest(TestBean testBean) throws RuntimeException {
		this.getSqlMapClientTemplate().update("updateTest", testBean);
	}

	@SuppressWarnings("unchecked")
	public List findAllTest() throws RuntimeException {
		return this.getSqlMapClientTemplate().queryForList("findAllTest");
	}

	public TestBean findTestById(int id) throws RuntimeException {
		return (TestBean) this.getSqlMapClientTemplate().queryForObject(
				"findTestById", id);
	}

	public int count() throws RuntimeException {
		// TODO Auto-generated method stub
		String s = (String)this.getSqlMapClientTemplate().queryForObject("count");
		return Integer.parseInt(s);
	}

	public List findTestByPage(int from, int len) throws RuntimeException {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("from", from);
		map.put("len", len);
		
		return this.getSqlMapClientTemplate().queryForList("findByPage",map);
	}
}
