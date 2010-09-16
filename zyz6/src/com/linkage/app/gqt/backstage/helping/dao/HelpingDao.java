package com.linkage.app.gqt.backstage.helping.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.linkage.app.gqt.backstage.active.entity.Active;
import com.linkage.app.gqt.backstage.active.entity.QueryActiveRowMapper;

import com.linkage.app.gqt.backstage.helping.entity.Worksheet;
import com.linkage.app.gqt.backstage.sysparam.entity.Sysparam;
import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.backstage.members.entity.Assist;
import com.linkage.app.gqt.backstage.org.entitys.Org;

import com.linkage.app.gqt.backstage.helping.entity.QueryWorksheetRowMapper;
import com.linkage.app.gqt.backstage.helping.entity.EditWorksheetRowMapper;
import com.linkage.app.gqt.backstage.helping.entity.QueryTypeRowMapper;
import com.linkage.app.gqt.backstage.helping.entity.QueryMemberRowMapper;
import com.linkage.app.gqt.backstage.helping.entity.QueryAssistRowMapper;
import com.linkage.app.gqt.backstage.helping.entity.QueryHelpingCountRowMapper;

import com.linkage.framework.jdbc.BaseJdbcDaoImpl;

public class HelpingDao extends BaseJdbcDaoImpl{
	final Logger logger = LoggerFactory.getLogger(HelpingDao.class);

	/**
	 * 作用：查询已发布的工单
	 * @return 工单列表
	 */
	public List<Worksheet> showWorkSheet(int start, int end, long currentOrgId){
		StringBuffer SQL = new StringBuffer("SELECT * FROM(SELECT ROWNUM AS rn, WORKSHEETID, WORKSHEETNO, H_TITLE, H_STARTDATE, H_OVERDATE, STATE FROM (SELECT");
		SQL.append(" WORKSHEETID,");
		SQL.append(" WORKSHEETNO,");	
		SQL.append(" H_TITLE,");
		SQL.append(" TO_CHAR(H_STARTDATE,'yyyy-MM-dd') AS H_STARTDATE,");
		SQL.append(" TO_CHAR(H_OVERDATE,'yyyy-MM-dd') AS H_OVERDATE,");
		SQL.append(" STATE ");
		SQL.append(" FROM ZYZ_WORKSHEET WHERE STATE = 0 and is_Del = 1 and H_AREAID = " + currentOrgId+ " AND H_AREAID = V_AREAID ORDER BY WORKSHEETID DESC) WHERE ROWNUM <= " + end + ")WHERE rn > " + start);
		logger.info(SQL.toString());
		List<Worksheet> list=(List<Worksheet>) this.getJdbcTemplate()
												.query(SQL.toString(), new QueryWorksheetRowMapper());
		return list;
	}
	
	
	/**
	 * 作用：统计工单数
	 * @return count 总数
	 */
	public int countWorksheet( long currentOrgId){
		StringBuffer SQL = new StringBuffer("SELECT COUNT(1)");
		SQL.append(" FROM ZYZ_WORKSHEET WHERE STATE = 0 and is_Del = 1 and H_AREAID = " + currentOrgId+ " AND H_AREAID = V_AREAID");
		logger.info(SQL.toString());
		int count = this.getJdbcTemplate().queryForInt(SQL.toString());
		return count;
	}	
	
	
	
	
	/**
	 * 作用：查询帮扶对象
	 * @return 列表
	 */
	public List<Assist> showAssist(long typeId ,String name){
		StringBuffer SQL = new StringBuffer("SELECT ");
		SQL.append(" AID,");
		SQL.append(" NAME,");
		SQL.append(" ADDRESS,");	
		SQL.append(" CREDCODE,");
		SQL.append(" VOLUNORGID,");
		SQL.append(" VOLUNORGNAME");
		SQL.append(" FROM ZYZ_ASSIST WHERE VOLUNORGID = " + typeId + " AND NAME like '%" + name + "%' ORDER BY AID ");
		logger.info(SQL.toString());
		List<Assist> list=(List<Assist>) this.getJdbcTemplate()
												.query(SQL.toString(), new QueryAssistRowMapper());
		return list;		
		
	}	
	
	
	/**
	 * 作用：统计被帮扶对象
	 * @return count 总数
	 */
	public int countAssist( long typeId, String name){
		StringBuffer SQL = new StringBuffer("SELECT COUNT(1)");
		SQL.append(" FROM ZYZ_ASSIST WHERE VOLUNORGID = " + typeId + " NAME like '%" + name + "%' ORDER BY AID");
		logger.info(SQL.toString());
		int count = this.getJdbcTemplate().queryForInt(SQL.toString());
		return count;
	}	
	
	/**
	 * 作用：获取帮扶ID
	 * @return worksheetid
	 */
	public long getWorksheetid(){
		String SQL = "SELECT SEQ_ZYZ_BF.NEXTVAL FROM DUAL";
		logger.info(SQL);
		long worksheetid = this.getJdbcTemplate().queryForLong(SQL);
		return worksheetid;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 作用：查询已发布的协助
	 * @return 协助列表
	 */
	public List<Worksheet> showWorkSheet2(int start, int end, long currentOrgId){
		StringBuffer SQL = new StringBuffer("SELECT * FROM(SELECT ROWNUM AS rn, WORKSHEETID, WORKSHEETNO, H_TITLE, H_STARTDATE, H_OVERDATE, STATE FROM (SELECT");
		SQL.append(" WORKSHEETID,");
		SQL.append(" WORKSHEETNO,");	
		SQL.append(" H_TITLE,");
		SQL.append(" TO_CHAR(H_STARTDATE,'yyyy-MM-dd') AS H_STARTDATE,");
		SQL.append(" TO_CHAR(H_OVERDATE,'yyyy-MM-dd') AS H_OVERDATE,");
		SQL.append(" STATE ");
		SQL.append(" FROM ZYZ_WORKSHEET WHERE STATE = 0 and is_Del = 1 and V_AREAID = " + currentOrgId+ " AND H_AREAID <> V_AREAID ORDER BY WORKSHEETID DESC) WHERE ROWNUM <= " + end + ")WHERE rn > " + start);
		logger.info(SQL.toString());
		List<Worksheet> list=(List<Worksheet>) this.getJdbcTemplate()
												.query(SQL.toString(), new QueryWorksheetRowMapper());
		return list;
	}	
	

	/**
	 * 作用：统计协助数
	 * @return count 总数
	 */
	public int countWorksheet2( long currentOrgId){
		StringBuffer SQL = new StringBuffer("SELECT COUNT(1)");
		SQL.append(" FROM ZYZ_WORKSHEET WHERE STATE = 0 and is_Del = 1 and V_AREAID = " + currentOrgId+ " AND H_AREAID <> V_AREAID");
		logger.info(SQL.toString());
		int count = this.getJdbcTemplate().queryForInt(SQL.toString());
		return count;
	}	
	
	
	
	/**
	 * 作用：统计志愿者
	 * @return count 总数
	 */
	public int countPostulant(long typeId){
		StringBuffer SQL = new StringBuffer("SELECT COUNT(1)");
		SQL.append(" FROM ZYZ_MEMBERS c");
		SQL.append(" INNER JOIN (SELECT A.ORGID FROM ZYZ_ORGS A START WITH A.ORGID = 320600");
		SQL.append(" CONNECT BY PRIOR A.ORGID = A.PARENTORGID)B ON C.communityid = B.ORGID");	
		SQL.append(" WHERE JN like '%" + typeId + "%'");
		logger.info(SQL.toString());
		int count = this.getJdbcTemplate().queryForInt(SQL.toString());
		return count;
	}

	
	
	
	
	/**
	 * 作用：查询系统志愿者帮扶类型
	 * @return 类型列表
	 */	
	public List<Sysparam> showType(){
		StringBuffer SQL = new StringBuffer("SELECT ");
		SQL.append(" PARAMID,PARAMKEY,PARAMVALUE,");
		SQL.append(" PARAMORDER");	
		SQL.append(" FROM ZYZ_SYSPARAM WHERE PARAMCODE = 'JN' ORDER BY PARAMID ");
		logger.info(SQL.toString());
		List<Sysparam> list=(List<Sysparam>) this.getJdbcTemplate()
												.query(SQL.toString(), new QueryTypeRowMapper());
		return list;
	}	
	
	
	/**
	 * 作用：查询志愿者
	 * @return 类型列表
	 */	
	public List<Postulant> showMembers(int start, int end, long typeId ,long vorgId){
		StringBuffer SQL = new StringBuffer("SELECT * FROM(SELECT ROWNUM AS rn, ID, NAME, DN, COMMUNITYID, COMMUNITYNAME FROM (SELECT");
		SQL.append(" ID,");
		SQL.append(" NAME,");	
		SQL.append(" DN,");
		SQL.append(" COMMUNITYID,");
		SQL.append(" COMMUNITYNAME,");
		SQL.append(" (case COMMUNITYID when "+vorgId+" then 0 else COMMUNITYID end )  as newid ");
		SQL.append(" FROM ZYZ_MEMBERS c");
		SQL.append(" INNER JOIN (SELECT A.ORGID FROM ZYZ_ORGS A START WITH A.ORGID = 320600 ");
		SQL.append(" CONNECT BY PRIOR A.ORGID = A.PARENTORGID)B ON C.communityid = B.ORGID");	
		SQL.append(" WHERE JN like '%" + typeId + "%' ORDER BY newid,COMMUNITYID) WHERE ROWNUM <= " + end + ")WHERE rn > " + start);
		logger.info(SQL.toString());
		List<Postulant> list=(List<Postulant>) this.getJdbcTemplate()
												.query(SQL.toString(), new QueryMemberRowMapper());
		return list;		
		
		
		/*
		StringBuffer SQL = new StringBuffer("SELECT ");
		SQL.append(" UUID,NAME,CELLPHONE,VOLUNORGID,VOLUNORGNAME,(case VOLUNORGID when 10078 then 0 else VOLUNORGID end )  as newid  ");
		SQL.append(" FROM ZYZ_MEMBERS WHERE INTENTION like '%" + typeId + "%' ORDER BY newid,VOLUNORGID ");
		logger.info(SQL.toString());
		List<Postulant> list=(List<Postulant>) this.getJdbcTemplate()
												.query(SQL.toString(), new QueryMemberRowMapper());
		return list;
		*/
	}		
	
	
	
	

	
	
	
	
	/**
	 * 作用：保存工单
	 * @param active 待发布的活动信息
	 * @return 插入的记录数
	 */
	public int insertWorksheet(Worksheet worksheet){
		// 影响的条数
		int updateNum = 0;
		Object[] para= new Object[16];
		String SQL= "INSERT INTO ZYZ_WORKSHEET ";
		SQL = SQL + "(WORKSHEETID, WORKSHEETNO, H_NAME, H_TITLE, H_ADDRESS, H_AREAID,H_TYPEID, H_STARTDATE, H_OVERDATE, H_NOTE, V_NAME, V_TEL, STATE , ADMINID, IS_DEL, V_AREAID, SYSVALUE, V_ID)";
		SQL = SQL + " VALUES (?,?,?,?,?,?,?,TO_DATE(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'),?,?,?,0,?,1,?,?,?)";
		// 帮扶ID
		para[0] = worksheet.getWorksheetid();
		// 工单号
		para[1] = worksheet.getWorksheetno();
		// 被帮扶者姓名
		para[2] = worksheet.getHName();
		// 事由
		para[3] = worksheet.getHTitle();
		// 被帮扶者地点
		para[4] = worksheet.getHAddress();
		// 所属区域ID
		para[5] = worksheet.getHAreaid();
		// 所属类型ID
		para[6] = worksheet.getHTypeid();
		
		// 帮扶开始时间
		para[7] = worksheet.getHStartdate();		
		//帮扶结束时间
		para[8] = worksheet.getHOverdate();	
	
		// 帮扶简介
		para[9] = worksheet.getHNote();
		// 志愿者姓名
		para[10] = worksheet.getVName();
		// 志愿者电话
		para[11] = worksheet.getVTel();
		// 管理员ID
		para[12] = worksheet.getAdminid();	
		// 志愿者所属ID
		para[13] = worksheet.getVAreaid();
		
		//积分
		para[14] = worksheet.getSysValue();
		//志愿者ID
		para[15] = worksheet.getId();
		
		
		logger.info(SQL.replaceAll("\\?", "{}"), para);
		try{
			updateNum = this.getJdbcTemplate().update(SQL, para);
		}catch(Exception e){}
		return updateNum;
	}
	
	/**
	 * 作用：查询待修改的工单
	 * @param Id 工单ID
	 * @return 待修改的工单
	 */
	public Worksheet findWorksheetById(long Id){
		Worksheet worksheet = null;
		String SQL = " SELECT WORKSHEETID, H_NAME, H_TITLE, H_ADDRESS, H_AREAID, H_TYPEID, TO_CHAR(H_STARTDATE,'yyyy-MM-dd') AS H_STARTDATE,";
		SQL = SQL + " TO_CHAR(H_OVERDATE,'yyyy-MM-dd') AS H_OVERDATE, H_NOTE, V_NAME, V_TEL, V_AREAID, V_ID ";
		SQL = SQL + " FROM ZYZ_WORKSHEET WHERE WORKSHEETID =" + Id + " AND ROWNUM = 1 AND IS_DEL = 1";
		logger.info(SQL.toString());
		List<Worksheet> list=(List<Worksheet>) this.getJdbcTemplate().query(SQL, new EditWorksheetRowMapper());
		if(null != list && list.size() > 0){
			worksheet = list.get(0);
		}
		return worksheet;
	}

	

	/**
	 * 作用：统计帮扶工单********************
	 * @param orgId 区域ID
	 * @return 
	 */
	public List<Org> helpingCount(long orgId,String startDate,String overDate){
		StringBuffer SQL = new StringBuffer("SELECT orgid,orgname,lv,parentOrgId,num from(SELECT distinct");
		SQL.append(" B.orgid,B.orgname,lv,b.parentOrgId,");
		SQL.append(" COUNT(T.v_areaid) OVER(PARTITION BY B.ORGID) NUM");
		
		SQL.append(" FROM ");
		SQL.append(" (SELECT A.*, LEVEL as lv  FROM ZYZ_ORGS A");
		SQL.append(" START WITH A.ORGID = "+orgId);
		SQL.append(" CONNECT BY PRIOR A.ORGID = A.PARENTORGID) B");
		SQL.append(" LEFT JOIN Zyz_Worksheet T ON B.ORGID = T.v_areaid");
		SQL.append(" and t.h_startdate >= TO_DATE('"+startDate+"','yyyy-MM-dd')");
		SQL.append(" and t.h_startdate <= TO_DATE('"+overDate+"','yyyy-MM-dd')");
		
		SQL.append(" and t.is_del = 1)");
		SQL.append(" START WITH ORGID = "+ orgId +" CONNECT BY PRIOR ORGID = PARENTORGID");
		logger.info(SQL.toString());
		List<Org> list=(List<Org>) this.getJdbcTemplate()
												.query(SQL.toString(), new QueryHelpingCountRowMapper());
		return list;
	}	
	
	

	
	/**
	 * 作用：统计志愿者
	 * @return count 总数
	 */
	public int counthelping (long orgId, String startDate,String overDate){
		StringBuffer SQL = new StringBuffer("SELECT COUNT(1)");
		SQL.append(" FROM (SELECT distinct");
		SQL.append(" B.*,");
		SQL.append(" COUNT(T.v_areaid) OVER(PARTITION BY B.ORGID) NUM");	
		SQL.append(" FROM ");
		SQL.append(" (SELECT A.*, LEVEL  FROM ZYZ_ORGS A");
		SQL.append(" START WITH A.ORGID = "+orgId);
		SQL.append(" CONNECT BY PRIOR A.ORGID = A.PARENTORGID) B");
		SQL.append(" LEFT JOIN Zyz_Worksheet T ON B.ORGID = T.v_areaid");
		SQL.append(" )");
		logger.info(SQL.toString());
		int count = this.getJdbcTemplate().queryForInt(SQL.toString());
		return count;
	}	
	
	
	
	
	
	/**
	 * 作用：修改工单
	 * @param worksheet 工单
	 * @return
	 */
	public int updateWorksheet(Worksheet worksheet){
		// 影响的条数
		int updateNum = 0;
		Object[] para= new Object[13];
		String SQL= "UPDATE ZYZ_WORKSHEET SET ";
		SQL = SQL + " H_NAME = ?, H_TITLE = ?, H_ADDRESS = ?, H_AREAID = ?, H_TYPEID = ?, H_STARTDATE = TO_DATE(?,'YYYY-MM-DD'), H_OVERDATE = TO_DATE(?,'YYYY-MM-DD'), H_NOTE = ?, V_NAME = ?, V_TEL = ?, V_AREAID = ?, V_id = ? ";
		SQL = SQL + " WHERE WORKSHEETID = ?";
		// 被帮扶者姓名								
		para[0] = worksheet.getHName();
		// 帮扶事由
		para[1] = worksheet.getHTitle();
		// 被帮扶者住址
		para[2] = worksheet.getHAddress();
		// 所属区域ID
		para[3] = worksheet.getHAreaid();
		// 所属分类ID
		para[4] = worksheet.getHTypeid();
		// 帮扶开始时间
		para[5] = worksheet.getHStartdate();
		// 帮扶结束时间
		para[6] = worksheet.getHOverdate();
		// 帮扶事由简介
		para[7] = worksheet.getHNote();
		// 志愿者姓名		
		para[8] = worksheet.getVName();
		// 志愿者电话
		para[9] = worksheet.getVTel();
		// 志愿者所属区域ID
		para[10] = worksheet.getVAreaid();	
		//志愿者ID
		para[11] = worksheet.getId();
		// id
		para[12] = worksheet.getWorksheetid();
		
		logger.info(SQL.replaceAll("\\?", "{}"), para);
		try{
			updateNum = this.getJdbcTemplate().update(SQL, para);
		}catch(Exception e){}
		return updateNum;
	}
	
	
	
	

	/**
	 * 作用：结束工单
	 * @param workid 工单ID
	 * @return updateNum 影响的记录数
	 */
	public int finishWorksheet(long workid){
		// 影响的条数
		int updateNum = 0;
		Object[] para= new Object[]{workid};
		String SQL= "UPDATE ZYZ_WORKSHEET SET ";
		SQL = SQL + " STATE = 1 ";
		SQL = SQL + " WHERE WORKSHEETID = ?";
		logger.info(SQL.replaceAll("\\?", "{}"), para);
		try{
			updateNum = this.getJdbcTemplate().update(SQL, para);
		}catch(Exception e){}
		return updateNum;
	}


	//根据工单ID号得到该工单积分
	public int queryWorksheetValue(long workid){
		StringBuffer SQL = new StringBuffer("SELECT sysvalue");
		SQL.append(" FROM zyz_worksheet WHERE ");
		SQL.append(" worksheetid = ").append(workid);
		logger.info(SQL.toString());
		int count = this.getJdbcTemplate().queryForInt(SQL.toString());
		return count;
	}		
	
	
	//根据工单ID号得到该志愿者id
	public int queryMemberId(long workid){
		StringBuffer SQL = new StringBuffer("SELECT a.id");
		SQL.append(" FROM zyz_members a,zyz_worksheet b WHERE ");
		SQL.append(" a.ID = b.v_id and b.worksheetid = ").append(workid);
		logger.info(SQL.toString());
		int count = this.getJdbcTemplate().queryForInt(SQL.toString());
		return count;
	}		
	
	//根据志愿者ID得到该志愿者当前积分
	public int queryMemberSysValue(long id){
		StringBuffer SQL = new StringBuffer("SELECT TOTALPOINTS");
		SQL.append(" FROM zyz_members  WHERE ");
		SQL.append(" ID = ").append(id);
		logger.info(SQL.toString());
		int count = this.getJdbcTemplate().queryForInt(SQL.toString());
		return count;
	}		
	
	//跟新志愿者积分
	public int updateMemberValue(long id,long value){
		// 影响的条数
		int updateNum = 0;
		String SQL= "UPDATE ZYZ_members SET ";
		SQL = SQL + " TOTALPOINTS = "+value+" ";
		SQL = SQL + " WHERE id = "+id+"";
		try{
			updateNum = this.getJdbcTemplate().update(SQL);
		}catch(Exception e){}
		return updateNum;
	}	
	
	/**
	 * 作用：删除工单
	 * @param workid 工单ID
	 * @return updateNum 影响的记录数
	 */
	public int delWorksheet(long workid){
		// 影响的条数
		int updateNum = 0;
		Object[] para= new Object[]{workid};
		String SQL= "UPDATE ZYZ_WORKSHEET SET ";
		SQL = SQL + " is_del = 0 ";
		SQL = SQL + " WHERE WORKSHEETID = ?";
		logger.info(SQL.replaceAll("\\?", "{}"), para);
		try{
			updateNum = this.getJdbcTemplate().update(SQL, para);
		}catch(Exception e){}
		return updateNum;
	}	

	

	public List<Worksheet> findHelpingList(long orgid, String startdate, String enddate, int start, int end){
		StringBuffer SQL = new StringBuffer("SELECT * FROM(SELECT ROWNUM AS rn, WORKSHEETID, WORKSHEETNO, H_TITLE, H_STARTDATE, H_OVERDATE, STATE FROM (SELECT");
		SQL.append(" WORKSHEETID,");
		SQL.append(" WORKSHEETNO,");	
		SQL.append(" H_TITLE,");
		SQL.append(" TO_CHAR(H_STARTDATE,'yyyy-MM-dd') AS H_STARTDATE,");
		SQL.append(" TO_CHAR(H_OVERDATE,'yyyy-MM-dd') AS H_OVERDATE,");
		SQL.append(" STATE ");
		SQL.append(" FROM ZYZ_WORKSHEET C WHERE ");
		SQL.append(" C.H_STARTDATE >= TO_DATE('").append(startdate).append("','yyyy-MM-dd')");
		SQL.append(" AND C.H_STARTDATE <= TO_DATE('").append(enddate).append("','yyyy-MM-dd')");
		SQL.append(" AND C.V_AREAID = ").append(orgid);
		SQL.append(" AND IS_DEL = 1 ORDER BY WORKSHEETID DESC) WHERE ROWNUM <= " + end + ")WHERE rn > " + start);
		logger.info(SQL.toString());
		List<Worksheet> list=(List<Worksheet>) this.getJdbcTemplate()
												.query(SQL.toString(), new QueryWorksheetRowMapper());
		return list;
	}	
	
	
	public int countHelpingList(long orgid, String startdate, String enddate){
		StringBuffer SQL = new StringBuffer("SELECT COUNT(1)");
		SQL.append(" FROM ZYZ_worksheet C WHERE ");
		SQL.append(" C.H_STARTDATE >= TO_DATE('").append(startdate).append("','yyyy-MM-dd')");
		SQL.append(" AND C.H_STARTDATE <= TO_DATE('").append(enddate).append("','yyyy-MM-dd')");
		SQL.append(" AND C.V_AREAID = ").append(orgid);
		SQL.append(" AND IS_DEL = 1 ");
		logger.info(SQL.toString());
		int count = this.getJdbcTemplate().queryForInt(SQL.toString());
		return count;
	}	
	
	
	
	//根据系统分类得到积分
	public int querySysValue(long typeId){
		StringBuffer SQL = new StringBuffer("SELECT PARAMVALUE");
		SQL.append(" FROM ZYZ_sysparam WHERE ");
		SQL.append(" PARAMID = ").append(typeId);
		logger.info(SQL.toString());
		int count = this.getJdbcTemplate().queryForInt(SQL.toString());
		return count;
	}	
	
	
}
