package com.linkage.app.gqt.webpreferential.shops.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.linkage.app.gqt.webpreferential.shops.entitys.Shop;
import com.linkage.app.gqt.webpreferential.shops.entitys.ShopRowMapper;
import com.linkage.framework.jdbc.BaseJdbcDaoImpl;

public class ShopsDAO extends BaseJdbcDaoImpl{
	
	final Logger logger = LoggerFactory.getLogger(ShopsDAO.class);

	/*
	ID           NUMBER(16) not null,
	NAME         VARCHAR2(30) not null,
	INTRODUCTION VARCHAR2(2000) not null,
	ADDRESS      VARCHAR2(50),
	TEL          VARCHAR2(20),
	LOGO         VARCHAR2(50) not null,
	SHOPIMAGE    VARCHAR2(50) not null,
	CREATETIME   DATE default sysdate not null,
	STATE        NUMBER(1) default 0 not null
	*/
	
	public int insert(String name,String introduction,String address,String tel,String logo,String shopImage){
		logger.info("INSERT INTO EC_WEBPREFERENTIAL_SHOPS(ID,NAME,INTRODUCTION,ADDRESS,TEL,LOGO,SHOPIMAGE,CREATETIME,STATE) VALUES(EC_WEBPREFERENTIAL_SHOPS_SEQ.NEXTVAL,{},{},{},{},{},{},SYSDATE,0)", new Object[]{name, introduction, address, tel, logo,shopImage});
		return this.getJdbcTemplate().update("INSERT INTO EC_WEBPREFERENTIAL_SHOPS(ID,NAME,INTRODUCTION,ADDRESS,TEL,LOGO,SHOPIMAGE,CREATETIME,STATE) VALUES(EC_WEBPREFERENTIAL_SHOPS_SEQ.NEXTVAL,?,?,?,?,?,?,SYSDATE,0)", name, introduction, address, tel, logo,shopImage);
	}
	
	public List<Shop> selectAll(){
		logger.info("SELECT ID,NAME,INTRODUCTION,ADDRESS,TEL,LOGO,SHOPIMAGE,CREATETIME,STATE FROM EC_WEBPREFERENTIAL_SHOPS WHERE STATE < 3 ORDER BY ID");
		return this.getJdbcTemplate().query("SELECT ID,NAME,INTRODUCTION,ADDRESS,TEL,LOGO,SHOPIMAGE,CREATETIME,STATE FROM EC_WEBPREFERENTIAL_SHOPS WHERE STATE < 3 ORDER BY ID",new ShopRowMapper());
	}
	
	public List<Shop> selectEffectiveAll(){
		logger.info("SELECT ID,NAME,INTRODUCTION,ADDRESS,TEL,LOGO,SHOPIMAGE,CREATETIME,STATE FROM EC_WEBPREFERENTIAL_SHOPS WHERE STATE =1  ORDER BY ID");
		return this.getJdbcTemplate().query("SELECT ID,NAME,INTRODUCTION,ADDRESS,TEL,LOGO,SHOPIMAGE,CREATETIME,STATE FROM EC_WEBPREFERENTIAL_SHOPS WHERE STATE =1 ORDER BY ID",new ShopRowMapper());
	}
	
	public Shop selectById(long id){
		Object[] o=new Object[]{id};
		logger.info("SELECT ID,NAME,INTRODUCTION,ADDRESS,TEL,LOGO,SHOPIMAGE,CREATETIME,STATE FROM EC_WEBPREFERENTIAL_SHOPS WHERE ID = {}",o);
		return this.getJdbcTemplate().queryForObject("SELECT ID,NAME,INTRODUCTION,ADDRESS,TEL,LOGO,SHOPIMAGE,CREATETIME,STATE FROM EC_WEBPREFERENTIAL_SHOPS WHERE ID = ?",o,new ShopRowMapper());
	}
	
	public int updateByIdWithoutLogoAndShopImage(long id,String name,String introduction,String address,String tel){
		Object[] o=new Object[]{name,introduction,address,tel,id};
		logger.info("UPDATE EC_WEBPREFERENTIAL_SHOPS SET NAME={},INTRODUCTION={}, ADDRESS={}, TEL={} WHERE ID = {}",o);
		return this.getJdbcTemplate().update("UPDATE EC_WEBPREFERENTIAL_SHOPS SET NAME=?,INTRODUCTION=?, ADDRESS=?, TEL=? WHERE ID = ?",o);
	}
	
	public int updateByIdWithLogo(long id,String name,String introduction,String address,String tel,String logo){
		Object[] o=new Object[]{name,introduction,address,tel,logo,id};
		logger.info("UPDATE EC_WEBPREFERENTIAL_SHOPS SET NAME={},INTRODUCTION={}, ADDRESS={}, TEL={}, LOGO={} WHERE ID = {}",o);
		return this.getJdbcTemplate().update("UPDATE EC_WEBPREFERENTIAL_SHOPS SET NAME=?,INTRODUCTION=?, ADDRESS=?,TEL=?, LOGO=? WHERE ID = ?",o);
	}
	
	public int updateByIdWithShopImage(long id,String name,String introduction,String address,String tel,String shopImage){
		Object[] o=new Object[]{name,introduction,address,tel,shopImage,id};
		logger.info("UPDATE EC_WEBPREFERENTIAL_SHOPS SET NAME={},INTRODUCTION={}, ADDRESS={}, TEL={}, SHOPIMAGE={} WHERE ID = {}",o);
		return this.getJdbcTemplate().update("UPDATE EC_WEBPREFERENTIAL_SHOPS SET NAME=?,INTRODUCTION=?, ADDRESS=?,TEL=?, SHOPIMAGE=? WHERE ID = ?",o);
	}
	
	public int updateByIdWithLogoAndShopImage(long id,String name,String introduction,String address,String tel,String logo,String shopImage){
		Object[] o=new Object[]{name,introduction,address,tel,logo,shopImage,id};
		logger.info("UPDATE EC_WEBPREFERENTIAL_SHOPS SET NAME={},INTRODUCTION={}, ADDRESS={}, TEL={}, LOGO={},SHOPIMAGE={} WHERE ID = {}",o);
		return this.getJdbcTemplate().update("UPDATE EC_WEBPREFERENTIAL_SHOPS SET NAME=?,INTRODUCTION=?, ADDRESS=?,TEL=?, LOGO=?,SHOPIMAGE=? WHERE ID = ?",o);
	}
	
	public int updateStateById(long id,int state){
		Object[] o=new Object[]{state,id};
		logger.info("UPDATE EC_WEBPREFERENTIAL_SHOPS SET STATE={} WHERE ID = {}",o);
		return this.getJdbcTemplate().update("UPDATE EC_WEBPREFERENTIAL_SHOPS SET STATE=? WHERE ID = ?",o);
	}
	
	public int deleteById(long id){
		Object[] o=new Object[]{id};
		logger.info("DELETE FROM EC_WEBPREFERENTIAL_SHOPS WHERE ID = {}",o);
		return this.getJdbcTemplate().update("DELETE FROM EC_WEBPREFERENTIAL_SHOPS WHERE ID = ?",o);
	}
	
	public int[] batchDeleteById(final List<Long> idList) {
        int[] updateCounts = this.getJdbcTemplate().batchUpdate(
                "DELETE FROM EC_WEBPREFERENTIAL_SHOPS WHERE ID = ?",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, idList.get(i));
                        logger.info("DELETE FROM EC_WEBPREFERENTIAL_SHOPS WHERE ID = {}",idList.get(i));
                    }
                    public int getBatchSize() {
                        return idList.size();
                    }
                } );
        return updateCounts;
    }

	/*
	 * 新商家
	 */
	public List<Shop> selectNew(int returnObjectNum){
		Object[] o=new Object[]{returnObjectNum};
		logger.info("SELECT * FROM (SELECT * FROM EC_WEBPREFERENTIAL_SHOPS  ORDER BY CREATETIME DESC) WHERE ROWNUM < {}" ,o);
		return this.getJdbcTemplate().query("SELECT * FROM (SELECT * FROM EC_WEBPREFERENTIAL_SHOPS WHERE STATE=1 ORDER BY CREATETIME DESC) WHERE ROWNUM < ?",o,new ShopRowMapper());
	} 
	
	/*
	 * 优惠券多的商家(返回由行的MAP组成的LIST)
	 */
	public List<Map<String,Object>> _selectMuch(int returnObjectNum){
		Object[] o=new Object[]{returnObjectNum};
		String sql="SELECT * FROM (SELECT A.*,B.N AS COUPONCOUNT FROM EC_WEBPREFERENTIAL_SHOPS A LEFT JOIN "+
		"(SELECT COUNT(SHOPID) AS N ,SHOPID FROM EC_WEBPREFERENTIAL_COUPONS WHERE (TRUNC(SYSDATE)  BETWEEN TRUNC(STARTTIME) AND TRUNC(ENDTIME)) AND STATE=1 GROUP BY SHOPID) B "+
		"ON A.ID=B.SHOPID WHERE A.STATE=1 AND B.N IS NOT NULL ORDER BY COUPONCOUNT DESC) WHERE ROWNUM < ?";
		logger.info(sql.replace("?", "{}"),o);
		return this.getJdbcTemplate().queryForList(sql,o);
	}
	
	/*
	 * 优惠券多的商家(返回SHOP对象)
	 */
	public List<Shop> selectMuch(int returnObjectNum){
		Object[] o=new Object[]{returnObjectNum};
		String sql="SELECT * FROM (SELECT A.* FROM EC_WEBPREFERENTIAL_SHOPS A LEFT JOIN "+
		"(SELECT COUNT(SHOPID) AS N ,SHOPID FROM EC_WEBPREFERENTIAL_COUPONS WHERE (TRUNC(SYSDATE)  BETWEEN TRUNC(STARTTIME) AND TRUNC(ENDTIME)) AND STATE=1 GROUP BY SHOPID) B "+
		"ON A.ID=B.SHOPID WHERE A.STATE=1 AND B.N IS NOT NULL ORDER BY B.N DESC) WHERE ROWNUM < ?";
		logger.info(sql.replace("?", "{}"),o);
		return this.getJdbcTemplate().query(sql,o,new ShopRowMapper());
	}
	
	/*
	 * 下载量大的商家(返回由行的MAP组成的LIST)
	 */
	public List<Map<String,Object>> _selectHot(int returnObjectNum){
		Object[] o=new Object[]{returnObjectNum};
		String sql="SELECT * FROM (SELECT A.*,B.N AS COUPONCOUNT FROM EC_WEBPREFERENTIAL_SHOPS A LEFT JOIN "+
		"(SELECT COUNT(SHOPID) AS N ,SHOPID FROM EC_WEBPREFERENTIAL_DOWNLOAD  GROUP BY SHOPID) B "+
		"ON A.ID=B.SHOPID WHERE A.STATE=1 AND B.N IS NOT NULL ORDER BY COUPONCOUNT DESC) WHERE ROWNUM < ?";
		logger.info(sql.replace("?", "{}"),o);
		return this.getJdbcTemplate().queryForList(sql,o);
	}
	
	/*
	 * 下载量大的商家(返回SHOP对象)
	 */
	public List<Shop> selectHot(int returnObjectNum){
		Object[] o=new Object[]{returnObjectNum};
		String sql="SELECT * FROM (SELECT A.* FROM EC_WEBPREFERENTIAL_SHOPS A LEFT JOIN "+
		"(SELECT COUNT(SHOPID) AS N ,SHOPID FROM EC_WEBPREFERENTIAL_DOWNLOAD  GROUP BY SHOPID) B "+
		"ON A.ID=B.SHOPID WHERE A.STATE=1 AND B.N IS NOT NULL ORDER BY B.N DESC) WHERE ROWNUM < ?";
		logger.info(sql.replace("?", "{}"),o);
		return this.getJdbcTemplate().query(sql,o,new ShopRowMapper());
	}
}
