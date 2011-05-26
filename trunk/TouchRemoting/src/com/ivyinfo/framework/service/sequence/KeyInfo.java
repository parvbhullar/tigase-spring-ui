package com.ivyinfo.framework.service.sequence;
import java.sql.*; 
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class KeyInfo { 
    private long maxKey;        //当前Sequence载体的最大值 
    private long minKey;        //当前Sequence载体的最小值 
    private long nextKey;       //下一个Sequence值 
    private int poolSize;       //Sequence值缓存大小 
    private String keyName;     //Sequence的名称 
    private static final String sql_update = "UPDATE t_sys_key SET tableid = tableid + ? WHERE tablename = ?"; 
    private static final String sql_query = "SELECT tableid FROM t_sys_key WHERE tablename = ?"; 

    public KeyInfo(String keyName, int poolSize,JdbcTemplate jdbcTemplate) throws SQLException { 
        this.poolSize = poolSize; 
        this.keyName = keyName; 
        retrieveFromDB(jdbcTemplate); 
    } 

    public String getKeyName() { 
        return keyName; 
    } 

    public long getMaxKey() { 
        return maxKey; 
    } 

    public long getMinKey() { 
        return minKey; 
    } 

    public int getPoolSize() { 
        return poolSize; 
    } 

    /** 
     * 获取下一个Sequence值 
     * 
     * @return 下一个Sequence值 
     * @throws SQLException 
     */ 
    public synchronized long getNextKey(JdbcTemplate jdbcTemplate) throws SQLException { 
        if (nextKey > maxKey) { 
            retrieveFromDB(jdbcTemplate); 
        } 
        return nextKey++; 
    } 

    /** 
     * 执行Sequence表信息初始化和更新工作 
     * 
     * @throws SQLException 
     */ 
    private void retrieveFromDB(JdbcTemplate jdbcTemplate) throws SQLException { 
        System.out.println(""); 
        //查询数据库 
        Object[] params = new Object[] {keyName};
		int types[] = new int[] { Types.VARCHAR};
        List ls = jdbcTemplate.query(sql_query,params,types, new tableid_marc());
        MaxidBean oabean = null;
		for(int i=0; i<ls.size(); i++){
			oabean = (MaxidBean)ls.get(i);
		}
        if (oabean != null && !"".equals(oabean.getTableId())) { 
            maxKey = oabean.getTableId() + poolSize; 
            minKey = maxKey - poolSize + 1; 
            nextKey = minKey;  
        } else { 
            System.out.println("执行Sequence数据库初始化工作！"); 
            String init_sql = "INSERT INTO t_sys_key(tablename,tableid) VALUES(?,?)"; 
            Object[] params_inst = new Object[] {keyName,1+poolSize};
			int types_inst[] = new int[] { Types.VARCHAR,Types.INTEGER};
			jdbcTemplate.update(init_sql, params_inst, types_inst); 
            maxKey = 1 + poolSize; 
            minKey = maxKey - poolSize + 1; 
            nextKey = minKey; 
            return; 
        } 

        //更新数据库 
        //jdbcTemplate.getDataSource().getConnection().setAutoCommit(false);
        System.out.println("更新Sequence最大值！"); 
        Object[] params_max = new Object[] {poolSize,keyName};
		int types_max[] = new int[] { Types.INTEGER, Types.VARCHAR};
		jdbcTemplate.update(sql_update, params_max, types_max); 
        //jdbcTemplate.getDataSource().getConnection().commit();  
    } 
    
	protected class tableid_marc implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaxidBean bean = new MaxidBean();
			bean.setTableId(rs.getLong("tableid"));
			//bean.setTableName(rs.getString("vc_tablename"));
			return bean;
		}
	}
    
}
