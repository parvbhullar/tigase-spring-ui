package com.linkage.app.gqt.backstage.sysparam.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linkage.app.gqt.backstage.active.entity.TiMthis;

public class SysparamRowMapper implements RowMapper<Sysparam> {
	public Sysparam mapRow(ResultSet rs, int arg1) throws SQLException {
		Sysparam sysparam=new Sysparam();
		sysparam.setParamid(rs.getLong("paramid"));
		sysparam.setParamcode(rs.getString("paramcode"));
		sysparam.setParamkey(rs.getString("paramkey"));
		sysparam.setParamvalue(rs.getString("paramvalue"));
		sysparam.setParamstate(rs.getLong("paramstate"));
		sysparam.setParamorder(rs.getLong("paramorder"));
		sysparam.setParamdesc(rs.getString("paramdesc"));
		sysparam.setParamremark(rs.getString("paramremark"));
		return sysparam;
	}
}