package com.linkage.app.gqt.backstage.active.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
/**
 * 作用：组织活动树
 * @author Administrator
 *
 */
public class ActiveCountRowMapper implements RowMapper<Active>{

	public Active mapRow(ResultSet rs, int arg1) throws SQLException {
		Active active=new Active();
		// 组织ID
		active.setOrgid(rs.getLong("orgid"));
		// 组织名称
		active.setOrgname(rs.getString("orgname"));
		// 父组织ID
		active.setParentorgid(rs.getLong("parentorgid"));
		// 机构层级
		active.setOrglevel(rs.getInt("orglevel"));
		// 活动数目
		active.setActivenum(rs.getInt("activenum"));
		return active;
	}
}
