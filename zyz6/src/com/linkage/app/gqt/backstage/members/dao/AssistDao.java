package com.linkage.app.gqt.backstage.members.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.linkage.app.gqt.backstage.members.entity.Assist;
import com.linkage.app.gqt.backstage.members.entity.AssistRowMapper;
import com.linkage.framework.jdbc.BaseJdbcDaoImpl;

public class AssistDao extends BaseJdbcDaoImpl{
	final Logger logger = LoggerFactory.getLogger(AssistDao.class);
	
	private class myBatchPreparedStatementSetter implements BatchPreparedStatementSetter
	{
		private List<Object[]> list;
		private long vid;
		private String vname;
		public myBatchPreparedStatementSetter(List<Object[]> list1,long vid,String vname)
		{
			this.list = list1;
			this.vid = vid;
			this.vname = vname;
		}
		public int getBatchSize() {
			return list.size();
		}

		public void setValues(PreparedStatement ps, int i)
				throws SQLException {
				Object[] obj = list.get(i);
				ps.setString(1, (String)obj[0]);
				ps.setString(2, (String)obj[1]);
				String sex = (String)obj[2];
				if(sex!=null&&!sex.equals(""))
				{
					ps.setInt(3, Integer.valueOf(sex));
				}
				else
				{
					ps.setInt(3, 1);
				}

				String birthday = (String)obj[3];
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				if(birthday!=null&&!birthday.equals(""))
				{
					try {
						ps.setDate(4, new Date(sdf.parse(birthday).getTime()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				else
				{
					try {
						ps.setDate(4, new Date(sdf.parse("01/01/1970").getTime()));
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
				}
				ps.setString(5, (String)obj[4]);
				ps.setString(6, (String)obj[5]);
				ps.setLong(7, this.vid);
				ps.setString(8, vname);
				ps.setString(9, (String)obj[6]);
				ps.setString(10, (String)obj[7]);
				
		}
		
	}
	/*
	 * 批量新增帮扶者
	 */
	public int[] batchinsert(List<Object[]> list,long vid,String vname){
		
		return this.getJdbcTemplate().batchUpdate("INSERT INTO ZYZ_ASSIST(AID,NAME,CELLPHONE,SEX,BIRTHDAY,CREDTYPE,CREDCODE,ISVALID,ISVERIFY,VERIFYDESC,CREATETIME,TOTALPOINTS,VOLUNORGID,VOLUNORGNAME,HOMETEL,ADDRESS) VALUES(SEQ_ZYZ_MEMBERS_UUID.NEXTVAL,?,?,?,?,?,?,1,1,'',SYSDATE,0,?,?,?,?)", 
				new myBatchPreparedStatementSetter(list,vid,vname)
		);
	}
	
	/*
	 * 新增帮扶者
	 */
	public int insert(Assist assist){
		return this.getJdbcTemplate().update("INSERT INTO ZYZ_ASSIST(AID,NAME,CELLPHONE,SEX,BIRTHDAY,CREDTYPE,CREDCODE,ISVALID,ISVERIFY,VERIFYDESC,CREATETIME,TOTALPOINTS,VOLUNORGID,VOLUNORGNAME,HOMETEL,ADDRESS) VALUES(SEQ_ZYZ_MEMBERS_UUID.NEXTVAL,?,?,?,?,?,?,1,1,'',SYSDATE,0,?,?,?,?)", assist.getName(),  assist.getCellPhone(),assist.getSex(), assist.getBirthday(),assist.getCredtype(),assist.getCredcode(),assist.getVolunorgid(),assist.getVolunorgname(),assist.getHometel(),assist.getAddress());
	}
	public List<Assist> getAssistListByOrgId(long orgid)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ZYZ_ASSIST where ISVALID=1");
		sb.append(" and VOLUNORGID = ?");
		return this.getJdbcTemplate().query(sb.toString(),new Object[]{orgid},new AssistRowMapper());
	}
	/*
	 * 更新
	 */
	public int updateAssist(Assist assist){
		Object[] obj = new Object[]{ assist.getName(), assist.getCellPhone(),assist.getSex(), assist.getBirthday(),assist.getCredcode(),assist.getCredtype(),assist.getVolunorgid(),assist.getVolunorgname(),assist.getHometel(),assist.getAddress(),assist.getAid()};
		return this.getJdbcTemplate().update("UPDATE ZYZ_ASSIST set NAME=?,CELLPHONE=?,SEX=?,BIRTHDAY=?,CREDCODE=?,CREDTYPE=?,volunorgid=?,volunorgname=?,hometel=?,address=? where aid = ?", obj);
	}
	
	public int getAssistTotal(Map args)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT count(*) FROM (")
		  .append(" SELECT A.orgid  FROM ZYZ_ORGS A WHERE orglevel=5 START WITH A.ORGID = ? CONNECT BY PRIOR A.ORGID = A.PARENTORGID")
		  .append(" ) b ,zyz_assist c WHERE b.orgid=c.volunorgid and c.isvalid=1");
		String username = (String)args.get("s_username");
		String name = (String)args.get("s_name");
		String credcode = (String)args.get("credcode");
		List list = new LinkedList();
		if(username!=null)
		{
			sb.append(" and c.username like ");
			sb.append("'%"+username+"%'");
		}
		if(name!=null)
		{
			sb.append(" and c.name like ");
			sb.append("'%"+name+"%'");
		}
		if(credcode!=null)
		{
			sb.append(" and c.credcode like ");
			sb.append("'%"+credcode+"%'");
		}
		long oid = (Long)args.get("oid");
		return this.getJdbcTemplate().queryForInt(sb.toString(),oid);
		
	}

	public List<Assist> getAssistList(Map args)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ( SELECT T.*, ROWNUM RN FROM (");
		sb.append(" SELECT * FROM (")
		  .append(" SELECT A.orgid  FROM ZYZ_ORGS A WHERE orglevel=5 START WITH A.ORGID = ? CONNECT BY PRIOR A.ORGID = A.PARENTORGID")
		  .append(" ) b ,zyz_assist c WHERE b.orgid=c.volunorgid and c.isvalid=1");
		String username = (String)args.get("s_username");
		String name = (String)args.get("s_name");
		String credcode = (String)args.get("credcode");
		List list = new LinkedList();
		if(username!=null)
		{
			sb.append(" and c.username like ");
			sb.append("'%"+username+"%'");
		}
		if(name!=null)
		{
			sb.append(" and c.name like ");
			sb.append("'%"+name+"%'");
		}
		if(credcode!=null)
		{
			sb.append(" and c.credcode like ");
			sb.append("'%"+credcode+"%'");
		}
		
		
		sb.append(" ) T WHERE ROWNUM <= ? )WHERE RN > ?");
		int page = (Integer)args.get("page");
		int pagesize = (Integer)args.get("pagesize");
		long oid = (Long)args.get("oid");
		Object[] obj = new Object[]{oid,pagesize*page,(page-1)*pagesize};
		logger.info(sb.toString(),obj);
		return this.getJdbcTemplate().query(sb.toString(),obj,new AssistRowMapper());
	}
	
	
	/*
	 * 删除
	 */
	public int deleteById(long id){
		Object[] o=new Object[]{id};
		//logger.info("DELETE FROM ZYZ_MEMBERS WHERE UUID = {}",o);
		return this.getJdbcTemplate().update("UPDATE ZYZ_ASSIST SET ISVALID = 0  WHERE AID = ?",o);
	}
	/*
	 * 批量删除
	 */
	public int[] batchDelete(final List<Long> idList) {
        int[] updateCounts = this.getJdbcTemplate().batchUpdate(
                "UPDATE ZYZ_ASSIST SET ISVALID = 0  WHERE AID = ?",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, idList.get(i));
//                        logger.info("DELETE FROM ZYZ_MEMBERS WHERE UUID = {}",idList.get(i));
                    }
                    public int getBatchSize() {
                        return idList.size();
                    }
                } );
        return updateCounts;
    }
	/*
	 * 审核
	 */
	public int verify(long id,int vid){
		Object[] o=new Object[]{vid,id};
		//logger.info("SELECT c.*,s.name as shopName,s.logo as shopPic FROM ec_webpreferential_coupons c left join ec_webpreferential_shops s on s.id = c.shopid where s.state=1 and c.id= {}",o);
		return this.getJdbcTemplate().update("UPDATE ZYZ_ASSIST set ISVERIFY=? WHERE AID=?",o);
	}
	
	/*
	 * 根据id查询志愿者
	 */
	public Assist selectById(long id){
		Object[] o=new Object[]{id};
		//logger.info("SELECT c.*,s.name as shopName,s.logo as shopPic FROM ec_webpreferential_coupons c left join ec_webpreferential_shops s on s.id = c.shopid where s.state=1 and c.id= {}",o);
		return this.getJdbcTemplate().queryForObject("SELECT * FROM ZYZ_ASSIST WHERE AID=?",o,new AssistRowMapper());
	}
	
}
