package com.ivyinfo.numbersegment.util;

import org.springframework.jdbc.core.JdbcTemplate;

public class NumberSeg implements NumberSegInterface{
	
//	protected JdbcTemplate jdbcTemplate;

	/**
	 * 获取nextKeyValue
	 */
	public long getMaxId(String tableName,String orgid) throws Exception {
		long x = 1;
		try{
//			System.err.println("=====jdbcTemplate:"+jdbcTemplate);
			x = NumberSegUtils.getInstance().getNextKeyValue(tableName,orgid);
		}catch(Exception ex){
			ex.printStackTrace();
			//throw ex;
		}
		return x;
	}
	
//	public JdbcTemplate getJdbcTemplate() {
//		return jdbcTemplate;
//	}
//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
}
