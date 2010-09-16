package com.linkage.app.gqt.backstage.members.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
/**
 * 
 *
 *
 * @author jiale.wang
 *
 * @create on 2009-8-13 下午01:31:22
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see Assist
 */
public class AssistRowMapper implements RowMapper<Assist>{

	public Assist mapRow(ResultSet rs, int arg1) throws SQLException {
		Assist assist = new Assist();
		assist.setAid(rs.getLong("AID"));
		assist.setUserName(rs.getString("USERNAME"));
		assist.setName(rs.getString("NAME"));
		assist.setNickName(rs.getString("NICKNAME"));
		assist.setPassword(rs.getString("PASSWORD"));
		assist.setCellPhone(rs.getString("CELLPHONE"));
		assist.setEmail(rs.getString("EMAIL"));
		assist.setSex(rs.getInt("SEX"));
		assist.setBirthday(rs.getDate("BIRTHDAY"));
		assist.setCredcode(rs.getString("CREDCODE"));
		assist.setCredtype(rs.getString("CREDTYPE"));
		assist.setIsvalid(rs.getInt("ISVALID"));
		assist.setIsverify(rs.getInt("ISVERIFY"));
		assist.setVerifydesc(rs.getString("VERIFYDESC"));
		assist.setCreatetime(rs.getDate("CREATETIME"));
		assist.setTotalpoints(rs.getDouble("TOTALPOINTS"));
		assist.setVolunorgid(rs.getLong("VOLUNORGID"));
		assist.setVolunorgname(rs.getString("VOLUNORGNAME"));
		assist.setHometel(rs.getString("HOMETEL"));
		assist.setAddress(rs.getString("ADDRESS"));
		return assist;
	}

}
