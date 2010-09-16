package com.linkage.app.gqt.webpreferential.coupons.entitys;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CouponAdRowMapper implements RowMapper<CouponAd>{

	public CouponAd mapRow(ResultSet rs, int rowNum) throws SQLException {
		CouponAd ad = new CouponAd();
		ad.setId(rs.getLong("ID"));
		ad.setCouponId(rs.getLong("COUPONID"));
		ad.setState(rs.getInt("state"));
		ad.setPic(rs.getString("PIC"));
		ad.setAdType(rs.getInt("ADTYPE"));
		Coupon coupon = new Coupon();
		coupon.setId(ad.getCouponId());
		coupon.setName(rs.getString("name"));
		ad.setCoupon(coupon);
		return ad;
	}

}
