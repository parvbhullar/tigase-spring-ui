package com.linkage.app.gqt.backstage.sysparam.dao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkage.app.gqt.backstage.sysparam.entity.Sysparam;
import com.linkage.app.gqt.backstage.sysparam.entity.SysparamRowMapper;
import com.linkage.framework.jdbc.BaseJdbcDaoImpl;
import com.linkage.framework.page.CouponPageUtil;

public class SysparamDao extends BaseJdbcDaoImpl{
	final Logger logger = LoggerFactory.getLogger(SysparamDao.class);
	
	public List<Sysparam> showSysparam(CouponPageUtil pageUtil)
	{
		String sql="SELECT * FROM zyz_sysparam";
		StringBuffer sb=new StringBuffer(""); 
		sb.append("select * from (");
		sb.append("select row_.*, rownum rownum_ from (");
		sb.append("select * from zyz_sysparam");
		sb.append(") row_ where rownum <="+pageUtil.getEndRow());
		sb.append(") where rownum_ >="+pageUtil.getStartRow());
		List<Sysparam> list=(List<Sysparam>) this.getJdbcTemplate().query(sb.toString(), new SysparamRowMapper());
		sb=new StringBuffer("");
		sb.append("select count(*) from zyz_sysparam t");
		int totalRow=this.getJdbcTemplate().queryForInt(sb.toString());
		pageUtil.setTotalRow(totalRow);
		return list;
	}
	/**
	 * 技能系统参数
	 * @param pageUtil
	 * @return
	 */
	public List<Sysparam> showJnSysparam(Map args)
	{
		String sql="SELECT * FROM zyz_sysparam where PARAMCODE='JN' and paramstate=1";
		if(args.get("paramkey")!=null)
		{
			sql += " and paramkey like '%"+(String)args.get("paramkey")+"%'";
		}
		if(args.get("paramvalue")!=null)
		{
			sql += " and paramvalue like '%"+(String)args.get("paramvalue")+"%'";
		}
		List<Sysparam> list=(List<Sysparam>) this.getJdbcTemplate().query(sql, new SysparamRowMapper());
		
		return list;
	}
	
	/**
	 * 根据paramcode筛选系统参数列表
	 * @param pageUtil
	 * @param paramcode
	 * @return
	 */
	public List<Sysparam> showSysparamByParamcode(CouponPageUtil pageUtil,String paramcode)
	{
		String sql="SELECT * FROM zyz_sysparam";
		StringBuffer sb=new StringBuffer(""); 
		sb.append("select * from (");
		sb.append("select row_.*, rownum rownum_ from (");
		sb.append("select * from zyz_sysparam where paramcode='"+paramcode+"'");
		sb.append(") row_ where rownum <="+pageUtil.getEndRow());
		sb.append(") where rownum_ >="+pageUtil.getStartRow());
		List<Sysparam> list=(List<Sysparam>) this.getJdbcTemplate().query(sb.toString(), new SysparamRowMapper());
		sb=new StringBuffer("");
		sb.append("select count(*) from zyz_sysparam t");
		int totalRow=this.getJdbcTemplate().queryForInt(sb.toString());
		pageUtil.setTotalRow(totalRow);
		return list;
	}

	/**
	 * 插入系统参数
	 * @param sysParam
	 * @return
	 */
	public int insertSysparam(Sysparam sysParam)
	{
		
		return this.getJdbcTemplate().update("INSERT INTO ZYZ_SYSPARAM (PARAMID, PARAMCODE, PARAMKEY,PARAMVALUE, PARAMSTATE, PARAMORDER,PARAMDESC, PARAMREMARK) VALUES" +
				"(  SEQ_ZYZ_COMMON.nextval,?,? ,?, ?, ?,?,? )",
				sysParam.getParamcode(),sysParam.getParamkey(),sysParam.getParamvalue(),sysParam.getParamstate(),sysParam.getParamorder(),sysParam.getParamdesc(),sysParam.getParamremark());
	}
	/**
	 * 插入技能系统参数
	 * @param sysParam
	 * @return
	 */
	public int insertJnSysparam(Sysparam sysParam)
	{
		
		return this.getJdbcTemplate().update("INSERT INTO ZYZ_SYSPARAM (PARAMID, PARAMCODE, PARAMKEY,PARAMVALUE, PARAMSTATE, PARAMORDER,PARAMDESC, PARAMREMARK) VALUES" +
				"(  SEQ_ZYZ_SYSPARAM.nextval,?,? ,?, ?, ?,?,?)",
				sysParam.getParamcode(),sysParam.getParamkey(),sysParam.getParamvalue(),1,sysParam.getParamorder(),sysParam.getParamdesc(),sysParam.getParamremark());
	}
	
	/**
	 * 审核系统参数
	 * @param sysParam
	 * @return
	 */
	public int auditSysparam(Sysparam sysParam)
	{
		Object[] o=new Object[]{sysParam.getParamstate(),sysParam.getParamid()};
		String sql="UPDATE ZYZ_SYSPARAM SET PARAMSTATE=? WHERE PARAMID = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	/**
	 * 更新系统参数
	 * @param sysParam
	 * @return
	 */
	public int updateSysparam(Sysparam sysParam)
	{
		Object[] o=new Object[]{sysParam.getParamkey(),sysParam.getParamvalue(),sysParam.getParamorder(),sysParam.getParamdesc(),sysParam.getParamstate(),sysParam.getParamid()};
		String sql="UPDATE ZYZ_SYSPARAM SET PARAMKEY=?,PARAMVALUE=?,PARAMORDER=?,PARAMDESC=?,PARAMSTATE=? WHERE PARAMID = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	/**
	 * 验证系统参数值
	 * @param sysParam
	 * @return
	 */
	public int validateSysparamValue(String paramValue)
	{
		Object[] o=new Object[]{paramValue};
		String sql="SELECT * FROM ZYZ_SYSPARAM WHERE PARAMVALUE=? AND PARAMCODE='JN'";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	/**
	 * 删除系统参数
	 * @param sysParam
	 * @return
	 */
	public int deleteSysparam(Sysparam sysParam)
	{
		Object[] o=new Object[]{sysParam.getParamid()};
		String sql="DELETE ZYZ_SYSPARAM WHERE PARAMID = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	/**
	 * 编辑系统参数
	 * @param sysParam
	 * @return
	 */
	public Sysparam edit(Sysparam sysParam)
	{
		Object[] o=new Object[]{sysParam.getParamid()};
		String sql="SELECT * FROM ZYZ_SYSPARAM WHERE PARAMID = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().queryForObject(sql,o , new SysparamRowMapper());
	}
}
