package com.linkage.app.gqt.init.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkage.app.gqt.backstage.org.entitys.Org;
import com.linkage.app.gqt.backstage.org.entitys.OrgRowMapper;
import com.linkage.app.gqt.init.entity.OrgArea;
import com.linkage.app.gqt.init.entity.OrgAreaRowMapper;
import com.linkage.app.gqt.init.entity.OrgType;
import com.linkage.app.gqt.init.entity.OrgTypeRowMapper;
import com.linkage.app.gqt.init.entity.SysParam;
import com.linkage.app.gqt.init.entity.SysParamRowMapper;
import com.linkage.framework.jdbc.BaseJdbcDaoImpl;

public class InitDao extends BaseJdbcDaoImpl{

	final Logger logger = LoggerFactory.getLogger(InitDao.class);
	
	/*
	 * 参数表ZYZ_SYSPARAM
	 */
	public List<SysParam> selectAllSysParams(){
		String sql="SELECT * FROM ZYZ_SYSPARAM A WHERE A.PARAMSTATE=1 ORDER BY A.PARAMCODE";
		logger.info(sql);
		List<SysParam> list=this.getJdbcTemplate().query(sql, new SysParamRowMapper());
		return list;
	}
	
	/*
	 * 取得南通服务意向
	 */
	public List<SysParam> selectNtFwyx(){
		String sql="select m.paramid,m.paramcode,m.paramkey,m.paramvalue,m.paramstate,m.paramorder,m.paramdesc,m.paramremark"+
			" from zyz_orgs t,(select * from zyz_sysparam where paramcode='FWYX') m"+
            " where t.parentorgid=320600 and t.orgtypeid=200 and m.paramvalue=t.belongorgid order by m.paramvalue";
		logger.info(sql);
		List<SysParam> list=this.getJdbcTemplate().query(sql, new SysParamRowMapper());
		return list;
	}
	
	public List<SysParam> selectSysParamsByParamCode(String paramCode){
		Object[] o=new Object[]{paramCode};
		String sql="SELECT * FROM ZYZ_SYSPARAM A WHERE A.PARAMSTATE=1 AND A.PARAMCODE=? ORDER BY A.PARAMORDER";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		List<SysParam> list=this.getJdbcTemplate().query(sql, new SysParamRowMapper());
		return list;
	}
	
	public SysParam selectSysParamByParamId(long paramId){
		Object[] o=new Object[]{paramId};
		String sql="SELECT * FROM ZYZ_SYSPARAM A WHERE A.PARAMSTATE=1 AND A.PARAMID=?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		SysParam sysParam=this.getJdbcTemplate().queryForObject(sql, o, new SysParamRowMapper());
		return sysParam;
	}
	
	/*
	 * 机构类型表ZYZ_ORGTYPE
	 */
	public List<OrgType> selectAllOrgTypes(){
		//String sql="SELECT A.*, LEVEL FROM ZYZ_ORGTYPE A WHERE A.ORGTYPESTATE=1 START WITH A.ORGTYPEPARENTID = 0 CONNECT BY PRIOR A.ORGTYPEID = A.ORGTYPEPARENTID";
		String sql="SELECT A.*, LEVEL FROM ZYZ_ORGTYPE A  START WITH A.ORGTYPEPARENTID = 0 CONNECT BY PRIOR A.ORGTYPEID = A.ORGTYPEPARENTID";
		logger.info(sql);
		List<OrgType> list=this.getJdbcTemplate().query(sql, new OrgTypeRowMapper());
		return list;
	}
	
	/*
	 * 行政区域表ZYZ_ORGAREA
	 */
	public List<OrgArea> selectAllOrgAreas(){
		//String sql="SELECT T.*, LEVEL FROM ZYZ_ORGAREA T WHERE T.AREASTATE=1 START WITH T.AREAPARENTID=0 CONNECT BY PRIOR T.AREAID=T.AREAPARENTID";
		String sql="SELECT T.*, LEVEL FROM ZYZ_ORGAREA T  START WITH T.AREAPARENTID=0 CONNECT BY PRIOR T.AREAID=T.AREAPARENTID";
		logger.info(sql);
		List<OrgArea> list=this.getJdbcTemplate().query(sql, new OrgAreaRowMapper());
		return list;
	}
	
	/*
	 * 机构表ZYZ_ORGS
	 */
//	public List<Org> selectAllManageOrgs(){
//		String sql="SELECT A.*, LEVEL FROM ZYZ_ORGS A  WHERE ORGTYPEID=100 START WITH A.PARENTORGID = 0 CONNECT BY PRIOR A.ORGID = A.PARENTORGID";
//		logger.info(sql);
//		List<Org> list=this.getJdbcTemplate().query(sql, new OrgRowMapper());
//		return list;
//	}
	
	public List<Org> selectAllOrgs(){
		String sql="SELECT A.* , LEVEL FROM ZYZ_ORGS A START WITH A.ORGID = 320000 CONNECT BY PRIOR A.ORGID = A.PARENTORGID";
		logger.info(sql);
		List<Org> list=this.getJdbcTemplate().query(sql, new OrgRowMapper());
		return list;
	}
	
	
}
