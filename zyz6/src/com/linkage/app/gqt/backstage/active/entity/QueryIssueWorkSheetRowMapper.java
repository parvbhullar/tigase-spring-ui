package com.linkage.app.gqt.backstage.active.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class QueryIssueWorkSheetRowMapper implements RowMapper<IssueWorkSheet>{

	public IssueWorkSheet mapRow(ResultSet rs, int arg1) throws SQLException {
		IssueWorkSheet issueWorkSheet = new IssueWorkSheet();
		// 工单ID
		issueWorkSheet.setId(rs.getLong("id"));
		// 活动ID
		issueWorkSheet.setActid(rs.getLong("actid"));
		// 组织名称
		issueWorkSheet.setOrgname(rs.getString("orgname"));
		// 志愿者id
		issueWorkSheet.setVolunteerid(rs.getLong("volunteerid"));
		// 志愿者姓名
		issueWorkSheet.setVolunteername(rs.getString("volunteername"));
		// 手机
		issueWorkSheet.setDn(rs.getString("dn"));
		// 证件号码
		issueWorkSheet.setCredcode(rs.getString("credcode"));
		// 证件类型
		issueWorkSheet.setCredtype(rs.getString("credtype"));		
		// 服务意向
		issueWorkSheet.setIntention(rs.getString("intention"));
		// 社区ID
		issueWorkSheet.setCommunityid(rs.getLong("communityid"));
		// 社区名称
		issueWorkSheet.setCommunityname(rs.getString("communityname"));
		// 工单状态
		issueWorkSheet.setStatus(rs.getString("status"));
		
		return issueWorkSheet;
	}
}