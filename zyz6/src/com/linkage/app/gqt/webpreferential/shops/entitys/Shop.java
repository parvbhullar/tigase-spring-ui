package com.linkage.app.gqt.webpreferential.shops.entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.linkage.app.gqt.webpreferential.coupons.entitys.Coupon;

public class Shop implements Serializable {

	private static final long serialVersionUID = 8215309847477009603L;
	
	private long id;
	private String name;
	private String introduction;
	private String address;
	private String tel;
	private String logo;
	private String shopImage;
	private Date createTime;
	private int state;
	
	private List<Coupon> couponsList=new ArrayList<Coupon>();
	@SuppressWarnings("unused")
	private int numberOfcoupon;
	
	public String getShopImage() {
		return shopImage;
	}

	public void setShopImage(String shopImage) {
		this.shopImage = shopImage;
	}

	public int getNumberOfcoupon(){
		return couponsList.size();
	}
	
	public void setNumberOfcoupon(int numberOfcoupon){
		this.numberOfcoupon=numberOfcoupon;
	}
	
	public List<Coupon> getCouponsList() {
		return couponsList;
	}
	public void setCouponsList(List<Coupon> couponsList) {
		this.couponsList = couponsList;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

}
