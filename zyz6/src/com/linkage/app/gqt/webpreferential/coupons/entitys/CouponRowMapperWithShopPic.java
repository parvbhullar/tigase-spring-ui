package com.linkage.app.gqt.webpreferential.coupons.entitys;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
/**
 * MapperRow带商家LOGO
 *
 *
 * @author jiale.wang
 *
 * @create on 2010-6-13 上午11:03:23
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */
public class CouponRowMapperWithShopPic implements RowMapper<Coupon>{
	public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {
		Coupon coupon = new Coupon();
		coupon.setId(rs.getLong("ID"));
		coupon.setName(rs.getString("NAME"));
		coupon.setCategoryId(rs.getLong("CATEGORYID"));
		coupon.setCreateTime(rs.getDate("CREATETIME"));
		
		coupon.setEndTime(rs.getDate("ENDTIME"));
		coupon.setHelp(rs.getString("HELP"));
		coupon.setIntroduction(rs.getString("INTRODUCTION"));
		coupon.setPic(rs.getString("PIC"));
		coupon.setShopId(rs.getLong("SHOPID"));
		coupon.setStartTime(rs.getDate("STARTTIME"));
		coupon.setState(rs.getInt("STATE"));
		coupon.setIsRec(rs.getInt("ISREC"));
		coupon.setShopName(rs.getString("shopName"));
		coupon.setShopPic(rs.getString("shopPic"));
		coupon.setMessage(rs.getString("message"));
		return coupon;
	}
}
