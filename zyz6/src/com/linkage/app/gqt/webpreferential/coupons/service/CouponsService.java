package com.linkage.app.gqt.webpreferential.coupons.service;

import java.util.List;

import com.linkage.app.gqt.webpreferential.coupons.dao.CouponsDAO;
import com.linkage.app.gqt.webpreferential.coupons.entitys.Coupon;
import com.linkage.app.gqt.webpreferential.coupons.entitys.CouponAd;
import com.linkage.app.gqt.webpreferential.shops.dao.ShopsDAO;
import com.linkage.app.gqt.webpreferential.shops.entitys.Shop;
/**
 * 
 *	Coupons Service实现类
 *
 * @author jiale.wang
 *
 * @create on 2010-6-11 下午03:52:41
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */
public class CouponsService {
	private CouponsDAO couponsDAO;
	private ShopsDAO shopsDAO;

	public CouponsDAO getCouponsDAO() {
		return couponsDAO;
	}

	public void setCouponsDAO(CouponsDAO couponsDAO) {
		this.couponsDAO = couponsDAO;
	}
	public List<Shop> selectMuch(int returnObjectNum){
		return shopsDAO.selectMuch(returnObjectNum);
	}
	public List<Shop> selectAllShops()
	{
		return this.shopsDAO.selectAll();
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
	/**
	 * 查询最新加入优惠券
	 * @param num 返回条数 0:返回所有
	 * @return List
	 */
	public List<Coupon> selectNewCoupons(int num)
	{
		return this.couponsDAO.selectNewCoupons(num);
	}
//	public int saveDown(String dn,String ip,long cid,long shopId)
//	{
//		return couponsDAO.insertDown(dn, ip, cid, shopId);
//	}
	public int saveCoupon(Coupon coupon)
	{
		return couponsDAO.insert(coupon.getName(), coupon.getIntroduction(),coupon.getHelp(),coupon.getStartTimeStr(),coupon.getEndTimeStr(),coupon.getCategoryId(),coupon.getShopId(),coupon.getPic(),coupon.getMessage());
	}
	public List<Coupon> selectAll()
	{
		return couponsDAO.selectAll();
	}
	public List<Coupon> selectAll(long shopid)
	{
		return couponsDAO.selectAll(shopid);
	}
	public int selectTotal()
	{
		return 1;
	}
	public Coupon selectById(long id)
	{
		return this.couponsDAO.selectById(id);
	}
	public int updateByIdWithPic(Coupon coupon)
	{
		return this.couponsDAO.updateByIdWithPic(coupon.getId(), coupon.getName(), coupon.getIntroduction(), coupon.getHelp(), coupon.getStartTimeStr(), coupon.getEndTimeStr(), coupon.getCategoryId(), coupon.getShopId(),coupon.getPic(),coupon.getMessage());
	}
	public int updateByIdWithoutPic(Coupon coupon)
	{
		return this.couponsDAO.updateByIdWithoutPic(coupon.getId(), coupon.getName(), coupon.getIntroduction(), coupon.getHelp(), coupon.getStartTimeStr(), coupon.getEndTimeStr(), coupon.getCategoryId(), coupon.getShopId(),coupon.getMessage());
	}
	public int deleteById(long id){
		return couponsDAO.deleteById(id);
	}
	public int[] batchDeleteById(final List<Long> idList){
		return couponsDAO.batchDeleteById(idList);
	}
	public int updateStateById(long id,int state)
	{
		return couponsDAO.updateStateById(id, state);
	}
	public int updateRecById(long id,int state)
	{
		return couponsDAO.updateRecById(id, state);
	}
	public int couponDownNum(long id)
	{
		return couponsDAO.downNum(id);
	}
	
	public List<CouponAd> selectOnlineAd()
	{
		return this.couponsDAO.selectOnlineAdById();
	}
	public List<CouponAd> selectOnlineAd(int type)
	{
		return this.couponsDAO.selectOnlineAdById(type);
	}

	public ShopsDAO getShopsDAO() {
		return shopsDAO;
	}

	public void setShopsDAO(ShopsDAO shopsDAO) {
		this.shopsDAO = shopsDAO;
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
