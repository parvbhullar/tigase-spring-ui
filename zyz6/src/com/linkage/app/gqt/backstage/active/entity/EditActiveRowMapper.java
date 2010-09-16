package com.linkage.app.gqt.backstage.active.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EditActiveRowMapper implements RowMapper<Active>{

	public Active mapRow(ResultSet rs, int arg1) throws SQLException {
		Active active=new Active();
		// 活动ID
		active.setActid(rs.getLong("actid"));
		// 活动标题
		active.setActtitle(rs.getString("acttitle"));
		// 活动地点
		active.setActaddr(rs.getString("actaddr"));		
		// 开始日期
		active.setStartdate(rs.getString("startdate"));
		// 结束日期
		active.setEnddate(rs.getString("enddate"));
		// 活动人数
		active.setActnum(Integer.parseInt(rs.getString("actnum")));
		// 活动摘要
		active.setSummary(rs.getString("summary"));
		// 活动介绍
		active.setActdesc(rs.getString("actdesc"));
		// 附件
		active.setPath(rs.getString("path"));
		// 活动系数
		active.setRatio(Integer.parseInt(rs.getString("ratio")));
		// 服务意向
		active.setIntention(rs.getString("intention"));
		// 时间储蓄
		active.setActhours(rs.getInt("acthours"));
		return active;
	}
}
