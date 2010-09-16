package com.linkage.app.gqt.init.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrgAreaRowMapper implements RowMapper<OrgArea>{
	
	/*
	AREAID	NUMBER(16)	N			区域ID
	AREAPARENTID	NUMBER(16)	N			区域父ID 0:表示顶级区域ID
	AREANAME	VARCHAR2(50)	N			区域名称
	AREALEVEL	VARCHAR2(50)	N			区域等级
	AREASTATE	NUMBER(1)	N	1		区域状态 0:无效 1:有效
	*/
	public OrgArea mapRow(ResultSet rs, int arg1) throws SQLException {
		OrgArea orgArea=new OrgArea();
		orgArea.setAreaId(rs.getLong("AREAID"));
		orgArea.setAreaParentId(rs.getLong("AREAPARENTID"));
		orgArea.setAreaName(rs.getString("AREANAME"));
		orgArea.setAreaLevel(rs.getString("AREALEVEL"));
		orgArea.setAreaState(rs.getInt("AREASTATE"));
		orgArea.setLevel(rs.getInt("LEVEL"));
		return orgArea;
	}
}
