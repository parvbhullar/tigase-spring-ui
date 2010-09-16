package com.linkage.app.gqt.webpreferential.coupons.entitys;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.linkage.app.gqt.webpreferential.shops.entitys.Shop;
/**
 * 
 *	coupon实体类
 *
 * @author jiale.wang
 *
 * @create on 2010-6-11 下午03:42:01
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */

public class Coupon implements Serializable{

	private static final long serialVersionUID = 3189236101555099347L;
	private long id;
	private String name;
	private String introduction;
	private String help;
	private int state;
	private int isRec;//是否推荐
	private Date startTime;
	private Date endTime;
	private String startTimeStr;
	private String endTimeStr;
	private Date createTime;
	private String pic;
	private long categoryId;
	private String categoryName;
	private long shopId;
	private String shopName;
	@SuppressWarnings("unused")
	private String startTimeWithFm1;
	@SuppressWarnings("unused")
	private String endTimeWithFm1;
	private String shopPic;
	private String message;
	
	private Shop shop;
	
	
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
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
	public String getHelp() {
		return help;
	}
	public void setHelp(String help) {
		this.help = help;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long getShopId() {
		return shopId;
	}
	public void setShopId(long shopId) {
		this.shopId = shopId;
	}
	public String getStartTimeStr() {
		if(this.getStartTime()==null)
		{
			return this.startTimeStr;
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(this.getStartTime());
		}
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
	public String getEndTimeStr() {
		if(this.getEndTime()==null)
		{
			return endTimeStr;
		}
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(this.getEndTime());
		}
	}
	
	public String getStartTimeWithFm1() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(this.getStartTime());
	}
	public void setStartTimeWithFm1(String startTimeWithFm1) {
		this.startTimeWithFm1 = startTimeWithFm1;
	}
	public String getEndTimeWithFm1() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(this.getEndTime());
	}
	public void setEndTimeWithFm1(String endTimeWithFm1) {
		this.endTimeWithFm1 = endTimeWithFm1;
	}
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopPic() {
		return shopPic;
	}
	public void setShopPic(String shopPic) {
		this.shopPic = shopPic;
	}
	public int getIsRec() {
		return isRec;
	}
	public void setIsRec(int isRec) {
		this.isRec = isRec;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
