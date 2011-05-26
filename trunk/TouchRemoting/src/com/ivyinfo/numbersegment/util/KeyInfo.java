package com.ivyinfo.numbersegment.util;
import java.sql.*; 
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.numbersegment.bean.NumberSegmentBean;
import com.ivyinfo.numbersegment.services.NumberSegmentServices;


public class KeyInfo { 
    private long maxKey;        //当前NumberSeg载体的最大值 
    private long minKey;        //当前NumberSeg载体的最小值 
    private long nextKey;       //下一个NumberSeg值 
    private int poolSize;       //NumberSeg值缓存大小 
    private String keyName;     //NumberSeg的名称 
    private static final String sql_update = "UPDATE t_sys_key SET tableid = tableid + ? WHERE tablename = ?"; 
    private static final String sql_query = "SELECT tableid FROM t_sys_key WHERE tablename = ?"; 

    public KeyInfo(String keyName, int poolSize, String orgid) throws SQLException { 
        this.poolSize = poolSize; 
        this.keyName = keyName; 
        retrieveFromDB(orgid); 
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
     * 获取下一个NumberSeg值 
     * 
     * @return 下一个NumberSeg值 
     * @throws SQLException 
     */ 
    public synchronized long getNextKey(String orgid) throws SQLException { 
        if (nextKey > maxKey) { 
            retrieveFromDB(orgid); 
        } 
        return nextKey++; 
    } 

    /** 
     * 执行NumberSeg表信息初始化和更新工作 
     * 
     * @throws SQLException 
     */ 
    private void retrieveFromDB(String orgid) throws SQLException {
        System.out.println(""); 
        //查询数据库 
        Object[] params = new Object[] {keyName};
		int types[] = new int[] { Types.VARCHAR};
        NumberSegmentServices numberSegmentServices =(NumberSegmentServices) SpringContextUtil.getBean("numberSegmentServices");
        NumberSegmentBean numberSegmentBean = null;
		try {
			numberSegmentBean = numberSegmentServices.getNumberSegment(orgid, "1");
		} catch (Exception e) {
			e.printStackTrace();
		}
        String middleseg = numberSegmentBean.getMiddleseg();
        middleseg = (middleseg == null)?numberSegmentBean.getStartseg():middleseg;
        if (middleseg != null && !"".equals(middleseg)) { 
            maxKey = Long.parseLong(middleseg) + poolSize; 
            minKey = maxKey - poolSize + 1; 
            nextKey = minKey;
        }

        //更新数据库 
		try {
			String state = "1";
			if((Long.parseLong(numberSegmentBean.getEndseg())-1) == nextKey){
				state = "2";
			}
			NumberSegmentBean numberSegBean = new NumberSegmentBean();
			numberSegBean.setId(numberSegmentBean.getId());
			numberSegBean.setMiddleseg(String.valueOf(nextKey++));
			numberSegBean.setState(state);
			numberSegmentServices.UpdNumberSegment(numberSegBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
