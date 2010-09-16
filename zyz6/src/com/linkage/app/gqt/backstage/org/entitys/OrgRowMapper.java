package com.linkage.app.gqt.backstage.org.entitys;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrgRowMapper implements RowMapper<Org>{
	
	/*
	ORGID	NUMBER(16)	N			
	ORGNAME	VARCHAR2(50)	N			机构全名
	ORGSHORTNAME	VARCHAR2(50)	Y			机构简称
	PARENTORGID	NUMBER(16)	N	0		父机构 0:顶级机构
	ORGSTATE	NUMBER(1)	N			机构状态 0:无效 1:有效
	ORGADDRESS	VARCHAR2(128)	Y			机构地址
	ORGTEL	VARCHAR2(32)	Y			机构电话
	ORGCONTACTOR	VARCHAR2(32)	Y			机构联系人
	ORGZIPCODE	VARCHAR2(10)	Y			邮政编码
	ORGTYPEID	NUMBER(16)	N			机构类型(关联ZYZ_ORGTYPE)
	MANAGEORGID	NUMBER(16)	N			服务机构的管理机构ID(管理机构为其自身ID,服务机构填写归口的管理机构的ID)
	ORGSUBTYPEID	NUMBER(16)	N			机构子类型 (关联ZYZ_ORGTYPE)
	REGABLE	NUMBER(1)	N	1		是否允许用户自主注册 0:不允许 1:允许
	ORGLEVEL	NUMBER(1)	N			机构层级(目前为4级,省、市、县(区)、乡镇(街道)) (关联ZYZ_ORGAREA)
	BELONGORGID	NUMBER(16)	N			机构归属 (关联ZYZ_ORGAREA)
	*/
	public Org mapRow(ResultSet rs, int arg1) throws SQLException {
		Org org=new Org();
		org.setOrgId(rs.getLong("ORGID"));
		org.setOrgName(rs.getString("ORGNAME"));
		org.setOrgShotrName(rs.getString("ORGSHORTNAME"));
		org.setParentOrgId(rs.getLong("PARENTORGID"));
		org.setOrgState(rs.getInt("ORGSTATE"));
		org.setOrgAddress(rs.getString("ORGADDRESS"));
		org.setOrgTel(rs.getString("ORGTEL"));
		org.setOrgContactor(rs.getString("ORGCONTACTOR"));
		org.setOrgZipCode(rs.getString("ORGZIPCODE"));
		org.setOrgTypeId(rs.getLong("ORGTYPEID"));
		org.setManageOrgId(rs.getLong("MANAGEORGID"));
		org.setOrgSubTypeId(rs.getLong("ORGSUBTYPEID"));
		org.setRegAble(rs.getInt("REGABLE"));
		org.setOrgLevel(rs.getInt("ORGLEVEL"));
		org.setBelongOrgId(rs.getLong("BELONGORGID"));
		org.setLevel(rs.getInt("LEVEL"));
		return org;
	}
}
