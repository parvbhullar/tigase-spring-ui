package com.linkage.app.gqt.webpreferential.coupons.entitys;

import java.io.Serializable;

public class CouponAd implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 623797174211261182L;
	private long id;
	private long couponId;
	private int state;
	private int adType;
	private String pic;
	private Coupon coupon;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCouponId() {
		return couponId;
	}
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public int getAdType() {
		return adType;
	}
	public void setAdType(int adType) {
		this.adType = adType;
	}
	
	
}
