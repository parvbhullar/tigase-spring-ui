package com.linkage.app.gqt.backstage.log.dao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkage.app.gqt.backstage.log.entity.LogRowMapper;
import com.linkage.app.gqt.backstage.log.entity.ZyzLog;
import com.linkage.framework.Constants;
import com.linkage.framework.jdbc.BaseJdbcDaoImpl;

public class LogDao extends BaseJdbcDaoImpl{
	final Logger logger = LoggerFactory.getLogger(LogDao.class);
	
	/*
	 * 增加日志
	 */
	public int insert(long tid,long uid,String ip){
		return this.getJdbcTemplate().update("INSERT INTO ZYZ_LOG(LID,TYPEID,USERID,LOGDATE,LOGIP) VALUES(SEQ_ZYZ_COMMON.NEXTVAL,?,?,SYSDATE,?)",tid,uid,ip );
	}
	
	public int getPostulantTotal(Map args)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from zyz_log a left join zyz_log_type b on a.typeid = b.tid left join zyz_user c on a.userid = c.userid");
		sb.append(" where 1=1 ");
		String typename = (String)args.get("typename");
		String loginname = (String)args.get("loginname");
		String nickname = (String)args.get("nickname");
		
		if(typename!=null)
		{
			sb.append(" and b.name like ");
			sb.append("'%"+typename+"%'");
		}
		if(loginname!=null)
		{
			sb.append(" and c.loginname like ");
			sb.append("'%"+loginname+"%'");
		}
		if(nickname!=null)
		{
			sb.append(" and c.nickname like ");
			sb.append("'%"+nickname+"%'");
		}
		return this.getJdbcTemplate().queryForInt(sb.toString());
		
	}
	
	public List<ZyzLog> getPostlantList(Map args)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ( SELECT T.*, ROWNUM RN FROM (");
		sb.append(" select a.*,b.*,c.loginname,c.nickname from zyz_log a");
		sb.append(" left join zyz_log_type b on a.typeid = b.tid");
		sb.append(" left join zyz_user c on a.userid = c.userid where 1=1");
		String typename = (String)args.get("typename");
		String loginname = (String)args.get("loginname");
		String nickname = (String)args.get("nickname");
		
		if(typename!=null)
		{
			sb.append(" and b.name like ");
			sb.append("'%"+typename+"%'");
		}
		if(loginname!=null)
		{
			sb.append(" and c.loginname like ");
			sb.append("'%"+loginname+"%'");
		}
		if(nickname!=null)
		{
			sb.append(" and c.nickname like ");
			sb.append("'%"+nickname+"%'");
		}
		
		sb.append(" order by a.LOGDATE desc");
		sb.append(" ) T WHERE ROWNUM <= ? )WHERE RN > ?");
		int page = (Integer)args.get("page");
		int pagesize = (Integer)args.get("pagesize");
		Object[] obj = new Object[]{pagesize*page,(page-1)*pagesize};
		logger.info(sb.toString(),obj);
		return this.getJdbcTemplate().query(sb.toString(),obj,new LogRowMapper());
	}
	/*
	 * 短信下发
	 */
	
	public int insertMt(String srcId, String dn,String message, Map sysparamMap){
		logger.info("INSERT INTO TONG.TI_MT@JSWX_ZYZ(GATEWAYID,USERTABLENAME,DSTID,SRCID,FEEID,MSGCONTENT,SERVICEID,RECORDTYPE,COUNTFEE,FIXEDFEE,COMMITTIME) VALUES(?,?,?,?,?,?,?,0,0,0,SYSDATE)", new Object[]{Constants.IT_MT_USER,dn, dn,message});
		return this.getJdbcTemplate().update("INSERT INTO TONG.TI_MT@JSWX_ZYZ(GATEWAYID,USERTABLENAME,DSTID,SRCID,FEEID,MSGCONTENT,SERVICEID,RECORDTYPE,COUNTFEE,FIXEDFEE,COMMITTIME) VALUES(?,?,?,?,?,?,?,0,0,0,SYSDATE)", sysparamMap.get("GATEWAYID"),sysparamMap.get("USERTABLENAME"),dn, srcId,dn,message, sysparamMap.get("SERVICEID"));
		
	}
}
