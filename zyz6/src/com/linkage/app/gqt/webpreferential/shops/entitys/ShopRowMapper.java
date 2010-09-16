package com.linkage.app.gqt.webpreferential.shops.entitys;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ShopRowMapper implements RowMapper<Shop> {
	
	public Shop mapRow(ResultSet rs, int rowNum)throws SQLException{
		Shop shop=new Shop();
		shop.setId(rs.getLong("ID"));
		shop.setName(rs.getString("NAME"));
		shop.setAddress(rs.getString("ADDRESS"));
		shop.setTel(rs.getString("TEL"));
		shop.setCreateTime(rs.getDate("CREATETIME"));
		shop.setLogo(rs.getString("LOGO"));
		shop.setIntroduction(rs.getString("INTRODUCTION"));
		shop.setState(rs.getInt("STATE"));
		shop.setShopImage(rs.getString("SHOPIMAGE"));
		return shop;
	}

}
