package com.linkage.app.gqt.webpreferential.shops.service;

import java.util.List;
import java.util.Map;

import com.linkage.app.gqt.webpreferential.coupons.dao.CouponsDAO;
import com.linkage.app.gqt.webpreferential.coupons.entitys.Coupon;
import com.linkage.app.gqt.webpreferential.shops.dao.ShopsDAO;
import com.linkage.app.gqt.webpreferential.shops.entitys.Shop;

public class ShopsService {
	
	private ShopsDAO shopsDAO;
	private CouponsDAO couponsDAO;

	
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
	
	public int create(Shop shop){
		return shopsDAO.insert(shop.getName(), shop.getIntroduction(), shop.getAddress(), shop.getTel(), shop.getLogo(),shop.getShopImage());
	}
	
	public List<Shop> selectAll(){
		return shopsDAO.selectAll();
	}
	
	public Shop selectById(long id){
		return shopsDAO.selectById(id);
	}
	
	public int updateByIdWithoutLogoAndShopImage(Shop shop){
		return shopsDAO.updateByIdWithoutLogoAndShopImage(shop.getId(), shop.getName(), shop.getIntroduction(), shop.getAddress(), shop.getTel());
	}
	
	public int updateByIdWithLogo(Shop shop){
		return shopsDAO.updateByIdWithLogo(shop.getId(), shop.getName(), shop.getIntroduction(), shop.getAddress(), shop.getTel(),shop.getLogo());
	}
	
	public int updateByIdWithShopImage(Shop shop){
		return shopsDAO.updateByIdWithShopImage(shop.getId(), shop.getName(), shop.getIntroduction(), shop.getAddress(), shop.getTel(),shop.getShopImage());
	}
	
	public int updateByIdWithLogoAndShopImage(Shop shop){
		return shopsDAO.updateByIdWithLogoAndShopImage(shop.getId(), shop.getName(), shop.getIntroduction(), shop.getAddress(), shop.getTel(),shop.getLogo(),shop.getShopImage());
	}
	
	public int updateStateById(long id,int state){
		return shopsDAO.updateStateById(id, state);
	}
	
	public int deleteById(long id){
		return shopsDAO.deleteById(id);
	}
	
	public int[] batchDeleteById(final List<Long> idList){
		return shopsDAO.batchDeleteById(idList);
	}
	
	public List<Shop> selectNew(int returnObjectNum){
		return shopsDAO.selectNew(returnObjectNum);
	}
	
	public List<Map<String,Object>> _selectMuch(int returnObjectNum){
		return shopsDAO._selectMuch(returnObjectNum);
	}
	
	public List<Shop> selectMuch(int returnObjectNum){
		return shopsDAO.selectMuch(returnObjectNum);
	}
	
	public List<Map<String,Object>> _selectHot(int returnObjectNum){
		return shopsDAO._selectHot(returnObjectNum);
	}
	
	public List<Shop> selectHot(int returnObjectNum){
		return shopsDAO.selectHot(returnObjectNum);
	}
	
	public List<Coupon> selectAllCoupons(){
		return couponsDAO.selectAll();
	}
	
	public List<Shop> getEffectiveShopCouponList(){
		List<Shop> shopList=shopsDAO.selectEffectiveAll();
		List<Coupon> couponList=couponsDAO.selectEffectiveAll();
		for(Shop shop :shopList){
			for(Coupon coupon : couponList){
				if(shop.getId()==coupon.getShopId()){
					shop.getCouponsList().add(coupon);
				}
			}
		}
		return shopList;
	}
	
}
