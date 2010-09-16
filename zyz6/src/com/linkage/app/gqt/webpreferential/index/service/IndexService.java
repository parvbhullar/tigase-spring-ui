package com.linkage.app.gqt.webpreferential.index.service;

import java.util.List;

import com.linkage.app.gqt.webpreferential.coupons.dao.CouponsDAO;
import com.linkage.app.gqt.webpreferential.coupons.entitys.Coupon;
import com.linkage.app.gqt.webpreferential.coupons.entitys.CouponAd;
import com.linkage.app.gqt.webpreferential.shops.dao.ShopsDAO;
import com.linkage.app.gqt.webpreferential.shops.entitys.Shop;

public class IndexService {
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
	public List<Shop> selectAllShops()
	{
		return this.shopsDAO.selectAll();
	}
	public int saveDown(String dn,String ip,long cid,long shopId,String message)
	{
		return couponsDAO.insertDown(dn, ip, cid, shopId,message);
	}
	public List<Coupon> selectAll()
	{
		return couponsDAO.selectAll();
	}
	/**
	 * 查询热门下载优惠券
	 * @param num 返回条数  0:返回所有
	 * @return List
	 */
	public List<Coupon> selectHotCoupons(int num)
	{
		return this.couponsDAO.selectHotCoupons(num);
	}
	public List<Shop> selectNew(int returnObjectNum){
		return shopsDAO.selectNew(returnObjectNum);
	}
	/**
	 * 查询最新加入优惠券
	 * @param num 返回条数 0:返回所有
	 * @return List
	 */
	public List<Coupon> selectNewCoupons(int num)
	{
		return this.couponsDAO.selectNewCoupons(num);
	}
	public List<Shop> selectMuch(int returnObjectNum){
		return shopsDAO.selectMuch(returnObjectNum);
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
	public List<CouponAd> selectOnlineAd(int type)
	{
		return this.couponsDAO.selectOnlineAdById(type);
	}
	public int deleteAdById(long id){
		return couponsDAO.deleteAdById(id);
	}
	public int insertAd(long cid,String pic,int type)
	{
		return couponsDAO.insertAd(cid, pic,type);
	}
	public int updateAdById(long id,int state){
		return couponsDAO.updateAdById(id, state);
	}
	public CouponAd selectAdById(long id)
	{
		return this.couponsDAO.selectAdById(id);
	}
	public int updateAdByIdWithPic(long id,long cid,String pic){
		return couponsDAO.updateAdByIdWithPic(id,cid,pic);
	}
	public List<CouponAd> selectAllStateAd(){
		return couponsDAO.selectAllStateAd();
	}
}
