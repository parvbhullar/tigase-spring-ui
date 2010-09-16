package com.linkage.app.gqt.webpreferential.coupons.service;

import java.util.List;

import com.linkage.app.gqt.webpreferential.coupons.dao.CouponsDAO;
import com.linkage.app.gqt.webpreferential.coupons.entitys.Coupon;
import com.linkage.app.gqt.webpreferential.coupons.entitys.CouponAd;
import com.linkage.app.gqt.webpreferential.shops.dao.ShopsDAO;
import com.linkage.app.gqt.webpreferential.shops.entitys.Shop;

public class AdCouponsService {
	private CouponsDAO couponsDAO;
	private ShopsDAO shopsDAO;
	
	public CouponsDAO getCouponsDAO() {
		return couponsDAO;
	}
	public void setCouponsDAO(CouponsDAO couponsDAO) {
		this.couponsDAO = couponsDAO;
	}
	public ShopsDAO getShopsDAO() {
		return shopsDAO;
	}
	public void setShopsDAO(ShopsDAO shopsDAO) {
		this.shopsDAO = shopsDAO;
	}
	public int insertAd(long cid,String pic,int type)
	{
		return couponsDAO.insertAd(cid, pic,type);
	}
	public int updateAdById(long id,int state){
		return couponsDAO.updateAdById(id, state);
	}
	public int updateAdByIdWithoutPic(long id,long cid){
		return couponsDAO.updateAdByIdWithoutPic(id, cid);
	}
	public int updateAdByIdWithPic(long id,long cid,String pic){
		return couponsDAO.updateAdByIdWithPic(id,cid,pic);
	}
	public List<CouponAd> selectAllAd(){
		return couponsDAO.selectAllAd();
	}
	public int deleteAdById(long id){
		return couponsDAO.deleteAdById(id);
	}
	public List<Coupon> selectAll()
	{
		return couponsDAO.selectAll();
	}
	public List<Shop> selectAllShops()
	{
		return this.shopsDAO.selectAll();
	}
	public CouponAd selectAdById(long id)
	{
		return this.couponsDAO.selectAdById(id);
	}
	public List<Coupon> getEffectiveCouponShopList(){
		List<Shop> shopList=shopsDAO.selectEffectiveAll();
		List<Coupon> couponList=couponsDAO.selectEffectiveAll();
		for(Coupon coupon :couponList){
			for(Shop shop : shopList){
				if(shop.getId()==coupon.getShopId()){
					coupon.setShop(shop);
					break;
				}
			}
		}
		return couponList;
	}
}
