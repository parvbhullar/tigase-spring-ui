package com.linkage.app.gqt.webpreferential.coupons.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.linkage.app.gqt.webpreferential.coupons.entitys.Coupon;
import com.linkage.app.gqt.webpreferential.coupons.entitys.CouponAd;
import com.linkage.app.gqt.webpreferential.coupons.entitys.CouponAdRowMapper;
import com.linkage.app.gqt.webpreferential.coupons.entitys.CouponRowMapper;
import com.linkage.app.gqt.webpreferential.coupons.entitys.CouponRowMapperWithShopPic;
import com.linkage.app.gqt.webpreferential.shops.dao.ShopsDAO;
import com.linkage.framework.Constants;
import com.linkage.framework.jdbc.BaseJdbcDaoImpl;
/**
 * 
 * coupon DAO实现类
 *
 * @author jiale.wang
 *
 * @create on 2010-6-11 下午03:45:03
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see BaseJdbcDaoImpl
 */
public class CouponsDAO extends BaseJdbcDaoImpl{
	final Logger logger = LoggerFactory.getLogger(ShopsDAO.class);
	
	/*
	 * 新增优惠券
	 */
	public int insert(String name,String introduction,String help,String startTime,String endTime,long categoryId,long shopId,String pic,String message){
		logger.info("INSERT INTO EC_WEBPREFERENTIAL_COUPONS(ID,NAME,INTRODUCTION,HELP,STARTTIME,ENDTIME,CATEGORYID,SHOPID,CREATETIME,PIC,STATE,MESSAGE) VALUES(EC_WEBPREFERENTIAL_SHOPS_SEQ.NEXTVAL,{},{},{},{},{},{},{},SYSDATE,{},0,{})", new Object[]{name, introduction, help,startTime, endTime,categoryId, shopId, pic,message});
		return this.getJdbcTemplate().update("INSERT INTO EC_WEBPREFERENTIAL_COUPONS(ID,NAME,INTRODUCTION,HELP,STARTTIME,ENDTIME,CATEGORYID,SHOPID,CREATETIME,PIC,STATE,MESSAGE) VALUES(EC_WEBPREFERENTIAL_SHOPS_SEQ.NEXTVAL,?,?,?,to_date(?,'YYYY-MM-DD HH24:MI:SS'),to_date(?,'YYYY-MM-DD HH24:MI:SS'),?,?,SYSDATE,?,0,?)", name, introduction, help,startTime, endTime,categoryId, shopId, pic,message);
	}
	/*
	 * 新增优惠券下载
	 */
	
	public int insertDown(String dn,String ip,long cid,long shopId,String message){
		System.out.println("&&&&&&&&&& Constants.IT_MT_USER="+Constants.IT_MT_USER+",DN="+dn+",IP="+ip+"&&&&&&&&&&");
		logger.info("INSERT INTO {}.TI_MT(GATEWAYID,USERTABLENAME,DSTID,SRCID,FEEID,MSGCONTENT,SERVICEID,RECORDTYPE,COUNTFEE,FIXEDFEE,COMMITTIME) VALUES('JJCS','HELP',{},'10658576',{},{},'FREE',0,0,0,SYSDATE)", new Object[]{Constants.IT_MT_USER,dn, dn,message});
		this.getJdbcTemplate().update("INSERT INTO "+Constants.IT_MT_USER+".TI_MT(GATEWAYID,USERTABLENAME,DSTID,SRCID,FEEID,MSGCONTENT,SERVICEID,RECORDTYPE,COUNTFEE,FIXEDFEE,COMMITTIME) VALUES('JJCS','HELP',?,'10658576',?,?,'FREE',0,0,0,SYSDATE)", dn, dn,message);
		logger.info("INSERT INTO EC_WEBPREFERENTIAL_DOWNLOAD(ID,DOWNLOADTIME,DN,IP,COUPONID,SHOPID) VALUES(EC_WEBPREFERENTIAL_SHOPS_SEQ.NEXTVAL,SYSDATE,{},{},{},{})", new Object[]{dn, ip, cid, shopId});
		return this.getJdbcTemplate().update("INSERT INTO EC_WEBPREFERENTIAL_DOWNLOAD(ID,DOWNLOADTIME,DN,IP,COUPONID,SHOPID) VALUES(EC_WEBPREFERENTIAL_SHOPS_SEQ.NEXTVAL,SYSDATE,?,?,?,?)", dn, ip, cid, shopId);
	}
	
	/*
	 * 新增广告管理
	 */
	public int insertAd(long cid,String pic,int type){
		logger.info("INSERT INTO EC_WEBPREFERENTIAL_AD(ID,COUPONID,PIC,STATE,ADTYPE) VALUES(EC_WEBPREFERENTIAL_SHOPS_SEQ.NEXTVAL,{},{},0,{})", new Object[]{cid, pic,type});
		return this.getJdbcTemplate().update("INSERT INTO EC_WEBPREFERENTIAL_AD(ID,COUPONID,PIC,STATE,ADTYPE) VALUES(EC_WEBPREFERENTIAL_SHOPS_SEQ.NEXTVAL,?,?,0,?)", cid, pic,type);
	}
	/*
	 * 更新广告
	 */
	public int updateAdById(long id,int state){
		Object[] o=new Object[]{state,id};
		logger.info("UPDATE EC_WEBPREFERENTIAL_AD SET STATE={} WHERE ID = {}",o);
		return this.getJdbcTemplate().update("UPDATE EC_WEBPREFERENTIAL_AD SET STATE=? WHERE ID = ?",o);
	}

	public int updateAdByIdWithoutPic(long id,long cid){
		Object[] o=new Object[]{cid,id};
		logger.info("UPDATE EC_WEBPREFERENTIAL_AD SET COUPONID={} WHERE ID = {}",o);
		return this.getJdbcTemplate().update("UPDATE EC_WEBPREFERENTIAL_AD SET COUPONID=? WHERE ID = ?",o);
	}
	public int updateAdByIdWithPic(long id,long cid,String pic){
		Object[] o=new Object[]{cid,pic,id};
		logger.info("UPDATE EC_WEBPREFERENTIAL_AD SET COUPONID={},PIC={} WHERE ID = {}",o);
		return this.getJdbcTemplate().update("UPDATE EC_WEBPREFERENTIAL_AD SET COUPONID=?,PIC=? WHERE ID = ?",o);
	}
	/*
	 * 获取单个广告
	 */
	public CouponAd selectAdById(long id){
		Object[] o=new Object[]{id};
		//logger.info("SELECT c.*,s.name as shopName,s.logo as shopPic FROM ec_webpreferential_coupons c left join ec_webpreferential_shops s on s.id = c.shopid where s.state=1 and c.id= {}",o);
		return this.getJdbcTemplate().queryForObject("SELECT A.*,C.*  FROM EC_WEBPREFERENTIAL_AD A LEFT JOIN EC_WEBPREFERENTIAL_COUPONS C ON C.ID = A.COUPONID WHERE A.ID=?",o,new CouponAdRowMapper());
	}
	/*
	 * 获取上线广告
	 */
	public List<CouponAd> selectOnlineAdById(int type){
		//logger.info("SELECT c.*,s.name as shopName,s.logo as shopPic FROM ec_webpreferential_coupons c left join ec_webpreferential_shops s on s.id = c.shopid where s.state=1 and c.id= {}",o);
		return this.getJdbcTemplate().query("SELECT A.*,C.*  FROM EC_WEBPREFERENTIAL_AD A LEFT JOIN EC_WEBPREFERENTIAL_COUPONS C ON C.ID = A.COUPONID WHERE A.STATE=1 AND A.adType=?",new Object[]{type},new CouponAdRowMapper());
	}
	public List<CouponAd> selectOnlineAdById(){
		//logger.info("SELECT c.*,s.name as shopName,s.logo as shopPic FROM ec_webpreferential_coupons c left join ec_webpreferential_shops s on s.id = c.shopid where s.state=1 and c.id= {}",o);
		return this.getJdbcTemplate().query("SELECT A.*,C.*  FROM EC_WEBPREFERENTIAL_AD A LEFT JOIN EC_WEBPREFERENTIAL_COUPONS C ON C.ID = A.COUPONID WHERE A.STATE=1",new CouponAdRowMapper());
	}
	/*
	 * 删除广告
	 */
	public int deleteAdById(long id){
		Object[] o=new Object[]{id};
		logger.info("DELETE FROM EC_WEBPREFERENTIAL_AD WHERE ID = {}",o);
		return this.getJdbcTemplate().update("DELETE FROM EC_WEBPREFERENTIAL_AD WHERE ID = ?",o);
	}
	public List<CouponAd> selectAllAd(){
		return this.getJdbcTemplate().query("SELECT A.*,C.*  FROM EC_WEBPREFERENTIAL_AD A LEFT JOIN EC_WEBPREFERENTIAL_COUPONS C ON C.ID = A.COUPONID WHERE A.ADTYPE=1 ORDER BY A.ID",new CouponAdRowMapper());
	}
	public List<CouponAd> selectAllStateAd(){
		return this.getJdbcTemplate().query("SELECT A.*,C.*  FROM EC_WEBPREFERENTIAL_AD A LEFT JOIN EC_WEBPREFERENTIAL_COUPONS C ON C.ID = A.COUPONID WHERE A.ADTYPE=0 ORDER BY A.ID",new CouponAdRowMapper());
	}
	/*
	 * 获取所有的优惠券(该优惠券关联的店铺必须处于有效状态)
	 */
	public List<Coupon> selectAll(){
		logger.info("SELECT C.*, S.NAME AS SHOPNAME FROM EC_WEBPREFERENTIAL_COUPONS C LEFT JOIN EC_WEBPREFERENTIAL_SHOPS S ON C.SHOPID = S.ID WHERE S.STATE = 1 ORDER BY C.ID");
		return this.getJdbcTemplate().query("SELECT C.*, S.NAME AS SHOPNAME FROM EC_WEBPREFERENTIAL_COUPONS C LEFT JOIN EC_WEBPREFERENTIAL_SHOPS S ON C.SHOPID = S.ID WHERE S.STATE = 1 ORDER BY C.ID",new CouponRowMapper());
	}
	
	/*
	 * 获取所有有效的优惠券(该优惠券关联的店铺必须处于有效状态)
	 */
	public List<Coupon> selectEffectiveAll(){
		logger.info("SELECT C.*, S.NAME AS SHOPNAME FROM EC_WEBPREFERENTIAL_COUPONS C LEFT JOIN EC_WEBPREFERENTIAL_SHOPS S ON C.SHOPID = S.ID WHERE (TRUNC(SYSDATE) BETWEEN TRUNC(C.STARTTIME) AND TRUNC(C.ENDTIME)) AND S.STATE = 1 AND C.STATE=1 ORDER BY C.ID");
		return this.getJdbcTemplate().query("SELECT C.*, S.NAME AS SHOPNAME FROM EC_WEBPREFERENTIAL_COUPONS C LEFT JOIN EC_WEBPREFERENTIAL_SHOPS S ON C.SHOPID = S.ID WHERE (TRUNC(SYSDATE) BETWEEN TRUNC(C.STARTTIME) AND TRUNC(C.ENDTIME)) AND S.STATE = 1 AND C.STATE=1 ORDER BY C.ID",new CouponRowMapper());
	}
	
	/*
	 * 获取店铺对应的优惠券(该优惠券关联的店铺必须处于有效状态)
	 */
	public List<Coupon> selectAll(long shopid){
		logger.info("SELECT C.*, S.NAME AS SHOPNAME FROM EC_WEBPREFERENTIAL_COUPONS C LEFT JOIN EC_WEBPREFERENTIAL_SHOPS S ON C.SHOPID = S.ID WHERE S.STATE = 1 AND C.SHOPID = {}", new Object[]{shopid});
		return this.getJdbcTemplate().query("SELECT C.*, S.NAME AS SHOPNAME FROM EC_WEBPREFERENTIAL_COUPONS C LEFT JOIN EC_WEBPREFERENTIAL_SHOPS S ON C.SHOPID = S.ID WHERE S.STATE = 1 AND C.SHOPID = ?",new Object[]{shopid},new CouponRowMapper());
	}
	
	/*
	 * 获取单个优惠券
	 */
	public Coupon selectById(long id){
		Object[] o=new Object[]{id};
		logger.info("SELECT c.*,s.name as shopName,s.logo as shopPic FROM ec_webpreferential_coupons c left join ec_webpreferential_shops s on s.id = c.shopid where s.state=1 and c.id= {}",o);
		return this.getJdbcTemplate().queryForObject("SELECT c.*,s.name as shopName,s.logo as shopPic FROM ec_webpreferential_coupons c left join ec_webpreferential_shops s on s.id = c.shopid where s.state=1 and c.id=?",o,new CouponRowMapperWithShopPic());
	}
	/*
	 * 优惠券下载次数
	 */
	public int downNum(long id){
		Object[] o=new Object[]{id};
		logger.info("select count(t.id) from ec_webpreferential_download t where t.couponid= {}",o);
		return this.getJdbcTemplate().queryForInt("select count(t.id) from ec_webpreferential_download t where t.couponid=?",o);
	}
	
	/*
	 * 更新优惠券带图片修改
	 */
	public int updateByIdWithPic(long id,String name,String introduction,String help,String startTime,String endTime,long categoryId,long shopId,String pic,String message){
		Object[] o=new Object[]{name,introduction,help,startTime,endTime,categoryId,shopId,pic,message,id};
		logger.info("UPDATE EC_WEBPREFERENTIAL_COUPONS SET NAME={},INTRODUCTION={}, HELP={}, startTime={}, endTime={},categoryId={},SHOPID={},pic={},message={} WHERE ID = {}",o);
		return this.getJdbcTemplate().update("UPDATE EC_WEBPREFERENTIAL_COUPONS SET NAME=?,INTRODUCTION=?, HELP=?,startTime=to_date(?,'YYYY-MM-DD HH24:MI:SS'), endTime=to_date(?,'YYYY-MM-DD HH24:MI:SS'),categoryId=?,shopId=?,pic=?,message=? WHERE ID = ?",o);
	}
	
	/*
	 * 更新优惠券
	 */
	public int updateByIdWithoutPic(long id,String name,String introduction,String help,String startTime,String endTime,long categoryId,long shopId,String message){
		Object[] o=new Object[]{name,introduction,help,startTime,endTime,categoryId,shopId,message,id};
		logger.info("UPDATE EC_WEBPREFERENTIAL_COUPONS SET NAME={},INTRODUCTION={}, HELP={}, startTime={}, endTime={},categoryId={},SHOPID={},message={} WHERE ID = {}",o);
		return this.getJdbcTemplate().update("UPDATE EC_WEBPREFERENTIAL_COUPONS SET NAME=?,INTRODUCTION=?, HELP=?,startTime=to_date(?,'YYYY-MM-DD HH24:MI:SS'), endTime=to_date(?,'YYYY-MM-DD HH24:MI:SS'),categoryId=?,shopId=?,message=? WHERE ID = ?",o);
	}
	
	/*
	 * 删除优惠券
	 */
	public int deleteById(long id){
		Object[] o=new Object[]{id};
		logger.info("DELETE FROM EC_WEBPREFERENTIAL_COUPONS WHERE ID = {}",o);
		return this.getJdbcTemplate().update("DELETE FROM EC_WEBPREFERENTIAL_COUPONS WHERE ID = ?",o);
	}
	
	/*
	 * 批量删除优惠券
	 */
	public int[] batchDeleteById(final List<Long> idList) {
        int[] updateCounts = this.getJdbcTemplate().batchUpdate(
                "DELETE FROM EC_WEBPREFERENTIAL_COUPONS WHERE ID = ?",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, idList.get(i));
                        logger.info("DELETE FROM EC_WEBPREFERENTIAL_COUPONS WHERE ID = {}",idList.get(i));
                    }
                    public int getBatchSize() {
                        return idList.size();
                    }
                } );
        return updateCounts;
    }
	
	/*
	 * 更新优惠券状态
	 */
	public int updateStateById(long id,int state){
		Object[] o=new Object[]{state,id};
		logger.info("UPDATE EC_WEBPREFERENTIAL_COUPONS SET STATE={} WHERE ID = {}",o);
		return this.getJdbcTemplate().update("UPDATE EC_WEBPREFERENTIAL_COUPONS SET STATE=? WHERE ID = ?",o);
	}
	/*
	 * 更新优惠券推荐状态
	 */
	public int updateRecById(long id,int state){
		Object[] o=new Object[]{state,id};
		logger.info("UPDATE EC_WEBPREFERENTIAL_COUPONS SET ISREC={} WHERE ID = {}",o);
		return this.getJdbcTemplate().update("UPDATE EC_WEBPREFERENTIAL_COUPONS SET ISREC=? WHERE ID = ?",o);
	}
	
	/*
	 * 最热优惠券
	 */
	public List<Coupon> selectHotCoupons(int num){
		Object[] o=new Object[]{num};
		String sql="SELECT * FROM ( SELECT  AA.*, S.NAME AS SHOPNAME  FROM (SELECT T.COUPONID AS ID, COUNT(*) AS CC FROM EC_WEBPREFERENTIAL_DOWNLOAD T GROUP BY T.COUPONID  ORDER BY CC DESC) BB LEFT JOIN EC_WEBPREFERENTIAL_COUPONS AA ON BB.ID = AA.ID LEFT JOIN EC_WEBPREFERENTIAL_SHOPS S ON S.ID = AA.SHOPID WHERE (TRUNC(SYSDATE) BETWEEN TRUNC(AA.STARTTIME) AND TRUNC(AA.ENDTIME)) AND S.STATE = 1 AND AA.STATE = 1) WHERE ROWNUM<=?";
		logger.info(sql.replace("?", "{}"),o);
		return this.getJdbcTemplate().query(sql,o,new CouponRowMapper());
	}
	
	/*
	 * 最新优惠券
	 */
	public List<Coupon> selectNewCoupons(int num){
		Object[] o=new Object[]{num};
		String sql="SELECT * FROM ( SELECT BB.*, AA.NAME AS SHOPNAME  FROM (SELECT * FROM EC_WEBPREFERENTIAL_COUPONS T WHERE (TRUNC(SYSDATE) BETWEEN TRUNC(T.STARTTIME) AND TRUNC(T.ENDTIME)) AND T.STATE = 1  ORDER BY T.CREATETIME DESC) BB  LEFT JOIN EC_WEBPREFERENTIAL_SHOPS AA ON AA.ID = BB.SHOPID WHERE AA.STATE = 1) WHERE ROWNUM<=?";
		logger.info(sql.replace("?", "{}"),o);
		return this.getJdbcTemplate().query(sql,o,new CouponRowMapper());
	}
}
