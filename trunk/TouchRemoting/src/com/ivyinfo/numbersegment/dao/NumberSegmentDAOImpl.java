package com.ivyinfo.numbersegment.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.numbersegment.bean.NumberSegmentBean;

public class NumberSegmentDAOImpl extends SqlMapClientDaoSupport implements NumberSegmentDAO{
	
	public void AddNumberSegment(NumberSegmentBean numberSegmentBean) throws Exception{
		this.getSqlMapClientTemplate().insert("AddNumberSegment", numberSegmentBean);
	}
	
	public void UpdNumberSegment(NumberSegmentBean numberSegmentBean) throws Exception{
		this.getSqlMapClientTemplate().update("UpdNumberSegment", numberSegmentBean);
	}
	
	public NumberSegmentBean getNumberSegment(String orgid, String state) throws Exception{
		Map map = new HashMap();
		map.put("orgid", orgid);
		map.put("state", state);
		return (NumberSegmentBean) this.getSqlMapClientTemplate().queryForObject("getNumberSegment", map);
	}
}
