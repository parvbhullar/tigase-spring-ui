package com.linkage.app.gqt.backstage.members.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.backstage.members.entity.PostulantHis;
import com.linkage.app.gqt.backstage.members.entity.PostulantHisRowMapper;
import com.linkage.app.gqt.backstage.members.entity.PostulantRowMapper;
import com.linkage.framework.jdbc.BaseJdbcDaoImpl;

public class PostulantDao extends BaseJdbcDaoImpl{
	final Logger logger = LoggerFactory.getLogger(PostulantDao.class);
	
	/*
	 * 新增志愿者
	 */
	public int insert(Postulant postulant) throws Exception{
		return this.getJdbcTemplate().update("INSERT INTO ZYZ_MEMBERS(ID,LOGINNAME,NAME,PASSWORD,DN,EMAIL,SEX,BIRTHDAY,EDUCATION,PROFESSION,HOBBY,COMMUNITYID,INTENTION,SERVETIMES,CREDCODE,CREDTYPE,ISVALID,ISVERIFY,VERIFYDESC,CREATETIME,REGIP,REGDATE,LASTLOGINIP,TOTALPOINTS,ORGNAME,COMMUNITYNAME,PHONE,ADDRESS,JN,NATION) VALUES(SEQ_ZYZ_MEMBERS_UUID.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,1,'',SYSDATE,?,SYSDATE,'',0,?,?,?,?,?,?)", postulant.getDn(), postulant.getName(), postulant.getPassword(), postulant.getDn(),postulant.getEmail(), postulant.getSex(), postulant.getBirthday(),postulant.getEducation(),postulant.getProfession(),postulant.getHobby(),postulant.getCommunityid(),postulant.getIntention(),postulant.getServetimes(),postulant.getCredcode(),postulant.getCredtype(),postulant.getRegip(),postulant.getOrgname(),postulant.getCommunityname(),postulant.getPhone(),postulant.getAddress(),postulant.getJn(),postulant.getNation());
	}
	
	/**
	 * 后台新增志愿者
	 * @param postulant
	 * @return
	 */
	public int insertBack(Postulant postulant){
		Object[] o=new Object[]{
				postulant.getDn(), postulant.getName(), postulant.getPassword(), postulant.getDn(),
				postulant.getEmail(), postulant.getSex(), postulant.getBirthday(),postulant.getEducation(),
				postulant.getProfession(),postulant.getHobby(),postulant.getCommunityid(),postulant.getIntention(),
				1,postulant.getIntention(),postulant.getCredcode(),postulant.getCredtype(),
				postulant.getRegip(),postulant.getOrgname(),postulant.getCommunityname(),postulant.getPhone(),
				postulant.getAddress(),postulant.getJn(),postulant.getNation()};
		System.out.println(postulant.getOrgname()+","+postulant.getCommunityname()+":中午");
		String sql="INSERT INTO ZYZ_MEMBERS(ID," +
				"LOGINNAME,NAME,PASSWORD,DN," +
				"EMAIL,SEX,BIRTHDAY,EDUCATION," +
				"PROFESSION,HOBBY,COMMUNITYID,INTENTION," +
				"SERVETIMES,ORGID,CREDCODE,CREDTYPE," +
				"ISVALID,ISVERIFY,VERIFYDESC,CREATETIME," +
				"REGIP,REGDATE,LASTLOGINIP," +
				"TOTALPOINTS,ORGNAME,COMMUNITYNAME,PHONE," +
				"ADDRESS,JN,NATION) " +
				"VALUES(SEQ_ZYZ_MEMBERS_UUID.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,1,?,?,?,1,1,'',SYSDATE,?,SYSDATE,'',0,?,?,?,?,?,?)";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		//System.out.println("INSERT INTO ZYZ_MEMBERS(ID,LOGINNAME,NAME,PASSWORD,DN,EMAIL,SEX,BIRTHDAY,EDUCATION,PROFESSION,HOBBY,COMMUNITYID,INTENTION,SERVETIMES,ORGID,CREDCODE,CREDTYPE,ISVALID,ISVERIFY,VERIFYDESC,CREATETIME,REGIP,REGDATE,LASTLOGINIP,TOTALPOINTS,ORGNAME,COMMUNITYNAME,PHONE,ADDRESS,JN,NATION) VALUES(SEQ_ZYZ_MEMBERS_UUID.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,1,'',SYSDATE,?,SYSDATE,'',0,?,?,?,?,?,?)", postulant.getDn(), postulant.getName(), postulant.getPassword(), postulant.getDn(),postulant.getEmail(), postulant.getSex(), postulant.getBirthday(),postulant.getEducation(),postulant.getProfession(),postulant.getHobby(),postulant.getCommunityid(),postulant.getIntention(),postulant.getServetimes(),postulant.getOrgid(),postulant.getCredcode(),postulant.getCredtype(),postulant.getRegip(),postulant.getOrgname(),postulant.getCommunityname(),postulant.getPhone(),postulant.getAddress(),postulant.getJn(),postulant.getNation());
		//return this.getJdbcTemplate().update("INSERT INTO ZYZ_MEMBERS(ID,LOGINNAME,NAME,PASSWORD,DN,EMAIL,SEX,BIRTHDAY,EDUCATION,PROFESSION,HOBBY,COMMUNITYID,INTENTION,SERVETIMES,ORGID,CREDCODE,CREDTYPE,ISVALID,ISVERIFY,VERIFYDESC,CREATETIME,REGIP,REGDATE,LASTLOGINIP,TOTALPOINTS,ORGNAME,COMMUNITYNAME,PHONE,ADDRESS,JN,NATION) VALUES(SEQ_ZYZ_MEMBERS_UUID.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,1,'',SYSDATE,?,SYSDATE,'',0,?,?,?,?,?,?)", postulant.getDn(), postulant.getName(), postulant.getPassword(), postulant.getDn(),postulant.getEmail(), postulant.getSex(), postulant.getBirthday(),postulant.getEducation(),postulant.getProfession(),postulant.getHobby(),postulant.getCommunityid(),postulant.getIntention(),postulant.getServetimes(),postulant.getOrgid(),postulant.getCredcode(),postulant.getCredtype(),postulant.getRegip(),postulant.getOrgname(),postulant.getCommunityname(),postulant.getPhone(),postulant.getAddress(),postulant.getJn(),postulant.getNation());
		return this.getJdbcTemplate().update(sql,o);
	}
	
	/**
	 * 前台注册页面
	 * @param postulant
	 * @return
	 */
	public int insertFornt(Postulant postulant){
		return this.getJdbcTemplate().update("INSERT INTO ZYZ_MEMBERS(ID,LOGINNAME,NAME,PASSWORD,DN,EMAIL,SEX,BIRTHDAY,EDUCATION,PROFESSION,HOBBY,COMMUNITYID,INTENTION,SERVETIMES,ORGID,CREDCODE,CREDTYPE,ISVALID,ISVERIFY,VERIFYDESC,CREATETIME,REGIP,REGDATE,LASTLOGINIP,TOTALPOINTS,ORGNAME,COMMUNITYNAME,PHONE,ADDRESS,JN,NATION) VALUES(SEQ_ZYZ_MEMBERS_UUID.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,1,'',SYSDATE,?,SYSDATE,'',0,?,?,?,?,?,?)", postulant.getLoginName(), postulant.getName(), postulant.getPassword(), postulant.getDn(),postulant.getEmail(), postulant.getSex(), postulant.getBirthday(),postulant.getEducation(),postulant.getProfession(),postulant.getHobby(),postulant.getCommunityid(),postulant.getIntention(),1,postulant.getOrgid(),postulant.getCredcode(),postulant.getCredtype(),postulant.getRegip(),postulant.getOrgname(),postulant.getCommunityname(),postulant.getPhone(),postulant.getAddress(),postulant.getJn(),postulant.getNation());
	}
	
	public int importData(Object[] obj,long cid,String inten,long orgid,String orgname,String cname,String jn){
		int r = 0;
		try{
			Object[] obj1 = new Object[18];
			obj1[0] = (String)obj[0];
			obj1[1] = (String)obj[1];
			obj1[2] = (String)obj[2];
			obj1[3] = (String)obj[3];
			obj1[4] = (String)obj[4];
			

			String sex = (String)obj[5];
			//ps.setInt(6, Integer.valueOf(sex));
			if(null!=sex&&!sex.equals(""))
			{
				obj1[5] = Integer.valueOf(sex);
			}
			else
			{
				obj1[5] = 1;
			}
			
			String birthday = (String)obj[6];
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(birthday!=null&&!birthday.equals(""))
			{
				try {
					obj1[6] = new Date(sdf.parse(birthday).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			else
			{
				obj1[6] = null;
			}
			obj1[7] = (String)obj[7];//hobby
			obj1[8] = cid;//communityid
			obj1[9] = inten;
			obj1[10] = orgid;//orgid
			obj1[11] = (String)obj[9];//credcode
			obj1[12] = (String)obj[8];//credtype
			obj1[13] = orgname;
			
			obj1[14] = cname;
			obj1[15] = (String)obj[10];//phone
			obj1[16] = (String)obj[11];//address
			obj1[17] = jn;//jn
			
			r = this.getJdbcTemplate().update("INSERT INTO ZYZ_MEMBERS(ID,LOGINNAME,NAME,PASSWORD,DN,EMAIL,SEX,BIRTHDAY,HOBBY,COMMUNITYID,INTENTION,ORGID,CREDCODE,CREDTYPE,ISVALID,ISVERIFY,VERIFYDESC,CREATETIME,REGIP,REGDATE,LASTLOGINIP,TOTALPOINTS,ORGNAME,COMMUNITYNAME,PHONE,ADDRESS,JN,SERVETIMES) VALUES(SEQ_ZYZ_MEMBERS_UUID.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,1,1,'',SYSDATE,'000.000.000.000',SYSDATE,'',0,?,?,?,?,?,1)", obj1);
		}catch(DataAccessException e)
		{
			
		}
		return r;
	}
	
	private class myBatchPreparedStatementSetter implements BatchPreparedStatementSetter
	{
		private List<Object[]> list;
		private long communityid;
		private String communityname;
		private long orgid;
		private String orgname;
		private String intention;
		public myBatchPreparedStatementSetter(List<Object[]> list1,long communityid,String communityname,long orgid,String orgname,String intention)
		{
			this.list = list1;
			this.communityid = communityid;
			this.communityname = communityname;
			this.orgid = orgid;
			this.orgname = orgname;
			this.intention = intention;
		}
		public int getBatchSize() {
			return list.size();
		}

		public void setValues(PreparedStatement ps, int i)
				throws SQLException {
				Object[] obj = list.get(i);
				ps.setString(1, (String)obj[0]);
				ps.setString(2, (String)obj[1]);
				ps.setString(3, (String)obj[2]);
				ps.setString(4, (String)obj[3]);
				ps.setString(5, (String)obj[4]);
				String sex = (String)obj[5];
				ps.setInt(6, Integer.valueOf(sex));
				
				
				String birthday = (String)obj[6];
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if(birthday!=null&&!birthday.equals(""))
				{
					try {
						ps.setDate(7, new Date(sdf.parse(birthday).getTime()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				else
				{
					try {
						ps.setDate(7, new Date(sdf.parse("01/01/1970").getTime()));
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
				}
				ps.setString(8, (String)obj[7]);//hobby
				ps.setLong(9, this.communityid);//communityid
				ps.setString(10, this.intention);
				ps.setLong(11, this.orgid);//orgid
				ps.setString(12, (String)obj[9]);//credcode
				ps.setString(13, (String)obj[8]);//credtype
				ps.setString(14, this.orgname);
				
				
				ps.setString(15, this.communityname);
				ps.setString(16, (String)obj[10]);//phone
				ps.setString(17, (String)obj[11]);//address
				
			
		}
		
	}
	/*
	 * 批量新增志愿者
	 */
	public int[] batchinsert(List<Object[]> list,long communityid,String communityname,long orgid,String orgname,String intention){
		int[] r = new int[]{};
		List result = new ArrayList();
		int n = 0;
		try{
		r = this.getJdbcTemplate().batchUpdate("INSERT INTO ZYZ_MEMBERS(ID,LOGINNAME,NAME,PASSWORD,DN,EMAIL,SEX,BIRTHDAY,HOBBY,COMMUNITYID,INTENTION,ORGID,CREDCODE,CREDTYPE,ISVALID,ISVERIFY,VERIFYDESC,CREATETIME,REGIP,REGDATE,LASTLOGINIP,TOTALPOINTS,ORGNAME,COMMUNITYNAME,PHONE,ADDRESS,SERVETIMES) VALUES(SEQ_ZYZ_MEMBERS_UUID.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,1,1,'',SYSDATE,'000.000.000.000',SYSDATE,'',0,?,?,?,?,1)", 
				new myBatchPreparedStatementSetter(list,communityid,communityname,orgid,orgname,intention)
		);
		}catch(DataAccessException e)
		{
			n++;
		}
		//System.out.println("导入失败:"+n);
		return r;
	}
	//验证
	public int[] validate(String dn,String credcode)
	{
		int p1 = this.getJdbcTemplate().queryForInt("SELECT count(*) FROM zyz_members WHERE dn=? ",new Object[]{dn});
		int p2 = this.getJdbcTemplate().queryForInt("SELECT count(*) FROM zyz_members WHERE CREDCODE=? ",new Object[]{credcode});
		return new int[]{p1,p2};
	}
	/*
	 * 更新志愿者
	 */
	public int updatePostulant(Postulant postulant){
		Object[] obj = new Object[]{postulant.getLoginName(), postulant.getName(),postulant.getPassword(), postulant.getDn(),postulant.getEmail(), postulant.getSex(), postulant.getBirthday(),postulant.getEducation(),postulant.getProfession(),postulant.getHobby(),postulant.getCommunityid(),postulant.getIntention(),1,postulant.getIntention(),postulant.getCredcode(),postulant.getCredtype(),postulant.getOrgname(),postulant.getCommunityname(),postulant.getPhone(),postulant.getAddress(),postulant.getJn(),postulant.getNation(),postulant.getId()};
		return this.getJdbcTemplate().update("UPDATE ZYZ_MEMBERS set LOGINNAME=?,NAME=?,PASSWORD=?,DN=?,EMAIL=?,SEX=?,BIRTHDAY=?,EDUCATION=?,PROFESSION=?,HOBBY=?,COMMUNITYID=?,INTENTION=?,SERVETIMES=?,ORGID=?,CREDCODE=?,CREDTYPE=?,ORGNAME=?,COMMUNITYNAME=?,PHONE=?,ADDRESS=?,JN=?,NATION=? where id = ?", obj);
	}
	
	/*
	 * 更新志愿者
	 */
	public int updatePostulantFront(Postulant postulant){
		Object[] obj = new Object[]{ postulant.getName(), postulant.getDn(),postulant.getEmail(), postulant.getSex(), postulant.getEducation(),postulant.getProfession(),postulant.getHobby(),postulant.getIntention(),postulant.getCredcode(),postulant.getCredtype(),postulant.getOrgname(),postulant.getCommunityname(),postulant.getPhone(),postulant.getIntention(),postulant.getCommunityid(),postulant.getBirthday(),postulant.getJn(),postulant.getNation(),postulant.getId()};
		return this.getJdbcTemplate().update("UPDATE ZYZ_MEMBERS set NAME=?,DN=?,EMAIL=?,SEX=?,EDUCATION=?,PROFESSION=?,HOBBY=?,ORGID=?,CREDCODE=?,CREDTYPE=?,ORGNAME=?,COMMUNITYNAME=?,PHONE=?,INTENTION=?,COMMUNITYID=?,BIRTHDAY=?,JN=?,NATION=? where id = ?", obj);
	}
	
	/*
	 * 根据id查询志愿者
	 */
	public Postulant selectById(long id){
		Object[] o=new Object[]{id};
		//logger.info("SELECT c.*,s.name as shopName,s.logo as shopPic FROM ec_webpreferential_coupons c left join ec_webpreferential_shops s on s.id = c.shopid where s.state=1 and c.id= {}",o);
		return this.getJdbcTemplate().queryForObject("SELECT * FROM zyz_members WHERE ID=?",o,new PostulantRowMapper());
	}

	/*
	 * 根据登录名查询志愿者
	 */
	public Postulant selectByUsername(String username){
		Object[] o=new Object[]{username};
		//logger.info("SELECT c.*,s.name as shopName,s.logo as shopPic FROM ec_webpreferential_coupons c left join ec_webpreferential_shops s on s.id = c.shopid where s.state=1 and c.id= {}",o);
		return this.getJdbcTemplate().queryForObject("SELECT * FROM zyz_members WHERE dn=?",o,new PostulantRowMapper());
	}

	/*
	 * 审核
	 */
	public int verify(long id,int vid){
		Object[] o=new Object[]{vid,id};
		//logger.info("SELECT c.*,s.name as shopName,s.logo as shopPic FROM ec_webpreferential_coupons c left join ec_webpreferential_shops s on s.id = c.shopid where s.state=1 and c.id= {}",o);
		return this.getJdbcTemplate().update("UPDATE ZYZ_MEMBERS set ISVERIFY=? WHERE ID=?",o);
	}
	/*
	 * 注销审核不通过
	 */
	public int verify1(long id,int vid){
		Object[] o=new Object[]{vid,id};
		//logger.info("SELECT c.*,s.name as shopName,s.logo as shopPic FROM ec_webpreferential_coupons c left join ec_webpreferential_shops s on s.id = c.shopid where s.state=1 and c.id= {}",o);
		return this.getJdbcTemplate().update("UPDATE ZYZ_MEMBERS set ISVERIFY=?,ISVALID=0 WHERE ID=?",o);
	}
	
	/*
	 * 审核
	 */
	public int logout(long id){
		Postulant postulant = this.getJdbcTemplate().queryForObject("SELECT * FROM zyz_members WHERE ID=?",new Object[]{id},new PostulantRowMapper());
		//logger.info("SELECT c.*,s.name as shopName,s.logo as shopPic FROM ec_webpreferential_coupons c left join ec_webpreferential_shops s on s.id = c.shopid where s.state=1 and c.id= {}",o);
		Object[] o=new Object[]{postulant.getName(),postulant.getDn(),postulant.getCredcode(),postulant.getCommunityid(),postulant.getCommunityname(),postulant.getOrgid(),postulant.getOrgname(),postulant.getOutresion()};
		int r = this.getJdbcTemplate().update("INSERT INTO ZYZ_MEMBERS_HIS(ID,NAME,DN,CREDCODE,COMMUNITYID,COMMUNITYNAME,ORGID,ORGNAME,OUTRESION,OUTDATE) VALUES(SEQ_ZYZ_COMMON.NEXTVAL,?,?,?,?,?,?,?,?,SYSDATE)",o);
		if(r>0)
		{
			r = this.getJdbcTemplate().update("DELETE FROM zyz_members WHERE ID=?",new Object[]{id});
		}
		return r;
	}
	
	public int getPostulantTotal(Map args)
	{
		StringBuffer sb = new StringBuffer();
		//sb.append("SELECT count(*) FROM zyz_members where ISVALID=1");
		sb.append(" SELECT count(*) FROM (");
		sb.append(" SELECT A.Belongorgid  FROM ZYZ_ORGS A WHERE orglevel=3 START WITH A.ORGID = ? CONNECT BY PRIOR A.ORGID = A.PARENTORGID");
		sb.append(" ) b ,zyz_members c WHERE b.belongorgid=c.orgid");
		String username = (String)args.get("s_username");
		String name = (String)args.get("s_name");
		String credcode = (String)args.get("credcode");
		String state = (String)args.get("state");
		List list = new LinkedList();
		if(username!=null)
		{
			sb.append(" and c.loginname like ");
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
		if(state!=null)
		{
			sb.append(" and c.ISVERIFY = ");
			sb.append(Integer.valueOf(state));
		}
		long oid = (Long)args.get("oid");
		return this.getJdbcTemplate().queryForInt(sb.toString(),oid);
		
	}
	public List<Postulant> getPostlantListByOrgId(long orgid)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM zyz_members where ISVALID=1");
		sb.append(" and VOLUNORGID = ?");
		return this.getJdbcTemplate().query(sb.toString(),new Object[]{orgid},new PostulantRowMapper());
	}
	//删除历史
	public List<PostulantHis> getPostlantHisList(Map args)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ( SELECT T.*, ROWNUM RN FROM ( SELECT c.* FROM (");
		sb.append(" SELECT A.orgid  FROM ZYZ_ORGS A WHERE orglevel=3 START WITH A.ORGID = ? CONNECT BY PRIOR A.ORGID = A.PARENTORGID");
		sb.append(" ) b ,zyz_members_his c WHERE b.orgid=c.orgid");
//		String username = (String)args.get("s_username");
//		String name = (String)args.get("s_name");
//		String credcode = (String)args.get("credcode");
//		String state = (String)args.get("state");
		
//		if(username!=null)
//		{
//			sb.append(" and c.loginname like ");
//			sb.append("'%"+username+"%'");
//		}
//		if(name!=null)
//		{
//			sb.append(" and c.name like ");
//			sb.append("'%"+name+"%'");
//		}
//		if(credcode!=null)
//		{
//			sb.append(" and c.credcode like ");
//			sb.append("'%"+credcode+"%'");
//		}
//		if(state!=null)
//		{
//			sb.append(" and c.ISVERIFY = ");
//			sb.append(Integer.valueOf(state));
//		}
		
		
		sb.append(" order by c.outdate desc) T WHERE ROWNUM <= ? )WHERE RN > ?");

		int page = (Integer)args.get("page");
		int pagesize = (Integer)args.get("pagesize");
		long oid = (Long)args.get("oid");
		Object[] obj = new Object[]{oid,pagesize*page,(page-1)*pagesize};
		logger.info(sb.toString(),obj);
		return this.getJdbcTemplate().query(sb.toString(),obj,new PostulantHisRowMapper());
	}
	public List<Postulant> getPostlantList(Map args)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ( SELECT T.*, ROWNUM RN FROM ( SELECT c.* FROM (");
		sb.append(" SELECT A.Belongorgid  FROM ZYZ_ORGS A WHERE orglevel=3 START WITH A.ORGID = ? CONNECT BY PRIOR A.ORGID = A.PARENTORGID");
		sb.append(" ) b ,zyz_members c WHERE b.belongorgid=c.orgid");
		String username = (String)args.get("s_username");
		String name = (String)args.get("s_name");
		String credcode = (String)args.get("credcode");
		String state = (String)args.get("state");
		List list = new LinkedList();
		if(username!=null)
		{
			sb.append(" and c.loginname like ");
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
		if(state!=null)
		{
			sb.append(" and c.ISVERIFY = ");
			sb.append(Integer.valueOf(state));
		}
		
		
		sb.append(" ) T WHERE ROWNUM <= ? )WHERE RN > ?");
		
		int page = (Integer)args.get("page");
		int pagesize = (Integer)args.get("pagesize");
		long oid = (Long)args.get("oid");
		Object[] obj = new Object[]{oid,pagesize*page,(page-1)*pagesize};
		logger.info(sb.toString(),obj);
		return this.getJdbcTemplate().query(sb.toString(),obj,new PostulantRowMapper());
	}
	/*
	 * 删除
	 */
	public int deleteById(long id){
//		Object[] o=new Object[]{id};
//		//logger.info("UPDATE ZYZ_MEMBERS SET ISVALID = 0  WHERE UUID = ?",o);
//		return this.getJdbcTemplate().update("UPDATE ZYZ_MEMBERS SET ISVALID = 0,DELEETTIME=SYSDATE  WHERE ID = ?",o);
		Postulant postulant = this.getJdbcTemplate().queryForObject("SELECT * FROM zyz_members WHERE ID=?",new Object[]{id},new PostulantRowMapper());
		//logger.info("SELECT c.*,s.name as shopName,s.logo as shopPic FROM ec_webpreferential_coupons c left join ec_webpreferential_shops s on s.id = c.shopid where s.state=1 and c.id= {}",o);
		Object[] o=new Object[]{postulant.getName(),postulant.getDn(),postulant.getCredcode(),postulant.getCommunityid(),postulant.getCommunityname(),postulant.getOrgid(),postulant.getOrgname(),"管理员删除"};
		int r = this.getJdbcTemplate().update("INSERT INTO ZYZ_MEMBERS_HIS(ID,NAME,DN,CREDCODE,COMMUNITYID,COMMUNITYNAME,ORGID,ORGNAME,OUTRESION,OUTDATE) VALUES(SEQ_ZYZ_COMMON.NEXTVAL,?,?,?,?,?,?,?,?,SYSDATE)",o);
		if(r>0)
		{
			r = this.getJdbcTemplate().update("DELETE FROM zyz_members WHERE ID=?",new Object[]{id});
		}
		return r;
	}
	/*
	 * 批量删除
	 */
	public int[] batchDelete(final List<Long> idList) {
		String sql = "select * from zyz_members where id in( ";
		for (int i = 0; i < idList.size(); i++) {
			sql += idList.get(i);
			if(i+1<idList.size())
			{
				sql+=",";
			}
		}
		sql += ")";
		
		final List<Postulant> list = this.getJdbcTemplate().query(sql,new PostulantRowMapper());;
		this.getJdbcTemplate().batchUpdate(
                "INSERT INTO ZYZ_MEMBERS_HIS(ID,NAME,DN,CREDCODE,COMMUNITYID,COMMUNITYNAME,ORGID,ORGNAME,OUTRESION,OUTDATE) VALUES(SEQ_ZYZ_COMMON.NEXTVAL,?,?,?,?,?,?,?,?,SYSDATE)",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, list.get(i).getName());
                        ps.setString(2, list.get(i).getDn());
                        ps.setString(3, list.get(i).getCredcode());
                        ps.setLong(4, list.get(i).getCommunityid());
                        ps.setString(5, list.get(i).getCommunityname());
                        ps.setLong(6, list.get(i).getOrgid());
                        ps.setString(7, list.get(i).getOrgname());
                        ps.setString(8, "管理员删除");
                       // logger.info("UPDATE ZYZ_MEMBERS SET ISVALID = 0 WHERE UUID = {}",idList.get(i));
                    }
                    public int getBatchSize() {
                        return list.size();
                    }
                } );
        int[] updateCounts = this.getJdbcTemplate().batchUpdate(
                "DELETE FROM zyz_members WHERE ID=?",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, idList.get(i));
                       // logger.info("UPDATE ZYZ_MEMBERS SET ISVALID = 0 WHERE UUID = {}",idList.get(i));
                    }
                    public int getBatchSize() {
                        return idList.size();
                    }
                } );
        return updateCounts;
    }
	
//	/*
//	 * 删除
//	 */
//	public int deleteById(long id){
//		Object[] o=new Object[]{id};
//		logger.info("DELETE FROM ZYZ_MEMBERS WHERE UUID = {}",o);
//		return this.getJdbcTemplate().update("DELETE FROM ZYZ_MEMBERS WHERE UUID = ?",o);
//	}
//	/*
//	 * 批量删除
//	 */
//	public int[] batchDelete(final List<Long> idList) {
//        int[] updateCounts = this.getJdbcTemplate().batchUpdate(
//                "DELETE FROM ZYZ_MEMBERS WHERE UUID = ?",
//                new BatchPreparedStatementSetter() {
//                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//                        ps.setLong(1, idList.get(i));
//                        logger.info("DELETE FROM ZYZ_MEMBERS WHERE UUID = {}",idList.get(i));
//                    }
//                    public int getBatchSize() {
//                        return idList.size();
//                    }
//                } );
//        return updateCounts;
//    }
}
