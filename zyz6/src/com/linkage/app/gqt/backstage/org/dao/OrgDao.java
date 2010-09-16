package com.linkage.app.gqt.backstage.org.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkage.app.gqt.backstage.org.entitys.Org;
import com.linkage.app.gqt.backstage.org.entitys.OrgTypeIDRowMapper;
import com.linkage.app.gqt.init.entity.OrgType;
import com.linkage.framework.jdbc.BaseJdbcDaoImpl;

public class OrgDao extends BaseJdbcDaoImpl{
	
	
	final Logger logger = LoggerFactory.getLogger(OrgDao.class);
	
	/*
	 * 机构类型表ZYZ_ORGTYPE
	 */
//	public List<OrgType> selectAllOrgTypes(){
//		String sql="SELECT A.*, LEVEL FROM ZYZ_ORGTYPE A  START WITH A.ORGTYPEPARENTID = 0 CONNECT BY PRIOR A.ORGTYPEID = A.ORGTYPEPARENTID";
//		logger.info(sql);
//		List<OrgType> list=this.getJdbcTemplate().query(sql, new OrgTypeRowMapper());
//		return list;
//	}
	
	public int insertOrgType(OrgType orgType){
		Object[] o=new Object[]{orgType.getOrgTypeParentId(),orgType.getOrgTypeName(),orgType.getCategory()};
		String sql="INSERT INTO ZYZ_ORGTYPE (ORGTYPEID,ORGTYPEPARENTID,ORGTYPENAME,ORGTYPESTATE,CATEGORY) VALUES(SEQ_ZYZ_COMMON.NEXTVAL,?,?,1,?)";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	public int updateOrgTypeWithOrgTypeStateByOrgTypeId(long orgTypeId,int orgTypeState){
		Object[] o=new Object[]{orgTypeState,orgTypeId};
		String sql="UPDATE ZYZ_ORGTYPE SET ORGTYPESTATE=? WHERE ORGTYPEID = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	public int updateOrgTypeWithOrgTypeNameByOrgTypeId(long orgTypeId,String orgTypeName){
		Object[] o=new Object[]{orgTypeName, orgTypeId};
		String sql="UPDATE ZYZ_ORGTYPE SET ORGTYPENAME=? WHERE ORGTYPEID = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	public int updateOrgType(OrgType orgType){
		Object[] o=new Object[]{orgType.getOrgTypeParentId(),orgType.getOrgTypeName(),orgType.getOrgTypeState(),orgType.getOrgTypeId()};
		String sql="UPDATE ZYZ_ORGTYPE SET ORGTYPEPARENTID=?,ORGTYPENAME=?,ORGTYPESTATE=? WHERE ORGTYPEID = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	public int deleteOrgTypeByOrgTypeId(long orgTypeId){
		Object[] o=new Object[]{orgTypeId};
		String sql="DELETE FROM ZYZ_ORGTYPE WHERE ORGTYPEID = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	/*
	 * 行政区域表ZYZ_ORGAREA
	 */
//	public List<OrgArea> selectAllOrgAreas(){
//		String sql="SELECT T.*, LEVEL FROM ZYZ_ORGAREA T WHERE T.AREASTATE=1 START WITH T.AREAPARENTID=0 CONNECT BY PRIOR T.AREAID=T.AREAPARENTID";
//		logger.info(sql);
//		List<OrgArea> list=this.getJdbcTemplate().query(sql, new OrgAreaRowMapper());
//		return list;
//	}
	
	/*
	 * 机构表ZYZ_ORGS
	 */
//	public List<Org> selectAllOrgs(){
//		String sql="SELECT A.*, LEVEL FROM ZYZ_ORGS A  START WITH A.PARENTORGID = 0 CONNECT BY PRIOR A.ORGID = A.PARENTORGID";
//		logger.info(sql);
//		List<Org> list=this.getJdbcTemplate().query(sql, new OrgRowMapper());
//		return list;
//	}
	
	public int insertOrg(Org org){
		Object[] o=new Object[]{org.getOrgId(),org.getOrgName(),org.getOrgShotrName(),org.getParentOrgId(),org.getOrgAddress(),org.getOrgTel(),org.getOrgContactor(),org.getOrgZipCode(),org.getOrgTypeId(),org.getManageOrgId(),org.getOrgSubTypeId(),org.getOrgLevel(),org.getBelongOrgId()};
		//String sql="INSERT INTO ZYZ_ORGS (ORGID,ORGNAME,ORGSHORTNAME,PARENTORGID,ORGSTATE,ORGADDRESS,ORGTEL,ORGCONTACTOR,ORGZIPCODE,ORGTYPEID,MANAGEORGID,ORGSUBTYPEID,REGABLE,ORGLEVEL,BELONGORGID) VALUES(SEQ_ZYZ_COMMON.NEXTVAL,?,?,?,1,?,?,?,?,?,?,?,1,?,?)";
		String sql="INSERT INTO ZYZ_ORGS (ORGID,ORGNAME,ORGSHORTNAME,PARENTORGID,ORGSTATE,ORGADDRESS,ORGTEL,ORGCONTACTOR,ORGZIPCODE,ORGTYPEID,MANAGEORGID,ORGSUBTYPEID,REGABLE,ORGLEVEL,BELONGORGID) VALUES(?,?,?,?,1,?,?,?,?,?,?,?,1,?,?)";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	public int updateOrgWithOrgStateByOrgId(long orgId,int orgState){
		Object[] o=new Object[]{orgState,orgId};
		String sql="UPDATE ZYZ_ORGS SET ORGSTATE=? WHERE ORGID = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	/*public int updateOrgByOrgId(Org org){
		Object[] o=new Object[]{org.getOrgName(),org.getOrgShotrName(),org.getParentOrgId(),org.getOrgAddress(),org.getOrgTel(),org.getOrgContactor(),org.getOrgZipCode(),org.getOrgTypeId(),org.getManageOrgId(),org.getOrgSubTypeId(),org.getOrgLevel(),org.getBelongOrgId(),org.getOrgId()};
		String sql="UPDATE ZYZ_ORGS SET ORGNAME=?,ORGSHORTNAME=?,PARENTORGID=?,ORGSTATE=?,ORGADDRESS=?,ORGTEL=?,ORGCONTACTOR=?,ORGZIPCODE=?,ORGTYPEID=?,MANAGEORGID=?,ORGSUBTYPEID=?,REGABLE=?,ORGLEVEL=?,BELONGORGID=? WHERE ORGID = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}*/
	
	public int updateOrgByOrgId(Org org){
		Object[] o=new Object[]{org.getOrgName(),org.getOrgShotrName(),org.getOrgAddress(),org.getOrgTel(),org.getOrgContactor(),org.getOrgZipCode(),org.getOrgId()};
		String sql="UPDATE ZYZ_ORGS SET ORGNAME=?,ORGSHORTNAME=?,ORGADDRESS=?,ORGTEL=?,ORGCONTACTOR=?,ORGZIPCODE=? WHERE ORGID = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	public int deleteOrgByOrgId(long orgId){
		Object[] o=new Object[]{orgId};
		String sql="DELETE FROM ZYZ_ORGS WHERE ORGID = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	public int selectInt(long orgId){
		Object[] o=new Object[]{orgId};
		String sql="SELECT COUNT(*) FROM (SELECT * FROM ZYZ_ORGS START WITH ORGID = ?  CONNECT BY PRIOR PARENTORGID = ORGID)  WHERE ORGID = 320600";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().queryForInt(sql, o);
	}
	
	/**
	 * 作用：判断某组织的机构类型（行政机构、组织机构）
	 * @param  组织ID
	 * @return 组织
	 */
	public Org getOrgType(long orgId){
		Org org = null;
		String SQL = " SELECT ORGTYPEID FROM ZYZ_ORGS WHERE ORGID = " + orgId;
		logger.info(SQL);
		org = this.getJdbcTemplate().queryForObject(SQL, new OrgTypeIDRowMapper());
		return org;
	}
	
	/**
	 * 作用：获取某组织的所在地区(江苏的13个地级市)
	 * @param  组织ID
	 * @return 该组织的所在地区
	 */
	public Org getAreaIdByOrgId(long orgId){
		Org org = null;
		String SQL = "SELECT * FROM ZYZ_ORGS WHERE ORGLEVEL =2 START WITH ORGID = "+orgId+"  CONNECT BY PRIOR PARENTORGID = ORGID";
		logger.info(SQL);
		org = this.getJdbcTemplate().queryForObject(SQL, new OrgTypeIDRowMapper());
		return org;
	}
}
