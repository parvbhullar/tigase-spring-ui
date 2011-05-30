package com.ivyinfo.framework.service.sequence;

import org.springframework.jdbc.core.JdbcTemplate;

public class Sequence implements SequenceInterface{
	
	protected JdbcTemplate jdbcTemplate;

	/**
	 * 获取nextKeyValue
	 */
	public long getMaxId(String tableName) throws Exception {
		long x = 1;
		try{
			System.err.println("=====jdbcTemplate:"+jdbcTemplate);
			x = SequenceUtils.getInstance().getNextKeyValue(tableName,jdbcTemplate);
		}catch(Exception ex){
			ex.printStackTrace();
			//throw ex;
		}
		return x;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
