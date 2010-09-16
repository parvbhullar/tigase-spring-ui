package com.linkage.app.gqt.webpreferential.shops.controller;


import java.util.ArrayList;
import java.util.List;

import net.spy.memcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.linkage.app.gqt.webpreferential.coupons.entitys.Coupon;
import com.linkage.app.gqt.webpreferential.shops.entitys.Shop;
import com.linkage.app.gqt.webpreferential.shops.service.ShopsService;
import com.linkage.framework.Constants;
import com.linkage.framework.page.CouponPageUtil;


@RequestMapping("/app/webpreferential/shops")
public class ShopsController {
	
	final Logger logger = LoggerFactory.getLogger(ShopsController.class);
	
	private ShopsService shopsService;
	private MemcachedClient memcachedClient;
	
	@Autowired
	public void setShopsService(ShopsService shopsService) {
		this.shopsService = shopsService;
	}
	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	
	/**
	 * 辅助方法
	 * 获取分页数据
	 * @param list 返回当前list
	 * @param page 当前页
	 * @param size 每页显示条数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List getCurrentPageList(List list,int page,int size){
		if(list!=null){
			return list.size() > size ? list.subList(page, size) : list.subList(page, list.size());
		}else{
			return new ArrayList();
		}
		
	}
	
	/*
	 * 辅助方法：
	 * 从缓存中获取数据，如果没有则从数据库中获取数据并放入缓存
	 * 数据结构List<Shop>
	 * 说明
	 *   1、列表中的Shop的状态都为1(有效状态)
	 *   2、Shop关联的优惠券的状态都为1(有效状态),且起止时间符合要求
	 */
	@SuppressWarnings("unchecked")
	private List<Shop> getListDataFromMemcached(){
		List<Shop> shopCouponlist=(List<Shop>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_SHOPS_COUPONS);
		if(shopCouponlist==null){
			shopCouponlist=shopsService.getEffectiveShopCouponList();
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_SHOPS_COUPONS, 24*60*60, shopCouponlist);
		}
			return shopCouponlist;
	}
	
	/*
	 * 显示店铺列表-所有店铺
	 */
	//@RequestMapping(value="/{page}/index.action")
	//@RequestMapping(value="/index/{page}")
	//public ModelAndView index(@PathVariable("page") int page){
	@RequestMapping(value="/index.action")
	public ModelAndView index(@RequestParam("page") int page){
		List<Shop> shopCouponlist=getListDataFromMemcached();
		int size = Constants.EC_WEBPREFERENTIAL_SHOPS_SIZE;
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(size);
		pageUtil.setCurPage(page);
		pageUtil.setTotalRow(shopCouponlist.size());
		ModelAndView mvc=new ModelAndView("/app/webpreferential/shops/allShopsList");
		mvc.addObject("shopCouponlist", getCurrentPageList(shopCouponlist,(page-1)*size,page*size));
		mvc.addObject("page",page);
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		return mvc;
	}
	
	/*
	 * 显示店铺信息包括店铺的优惠券及店铺的优惠券数量
	 */
	//@RequestMapping(value="/{id}/shop.action")
	//public ModelAndView showShop(@PathVariable("id") String id){
	@RequestMapping(value="/shop.action")
	public ModelAndView showShop(@RequestParam("id") long id,@RequestParam("page") String page){
		List<Shop> shopCouponlist=getListDataFromMemcached();
		Shop shop=null;
		for(Shop _shop : shopCouponlist){
			if(_shop.getId()==id){
				shop=_shop;
				break;
			}
		}
		int cpage = page==null ? 1 : Integer.valueOf(page);
		List<Coupon> shopCoupons = shop.getCouponsList();
		CouponPageUtil pageUtil = new CouponPageUtil();
		int size = Constants.EC_WEBPREFERENTIAL_SHOPS_COUPONS_SIZE;
		pageUtil.setPageSize(size);
		pageUtil.setCurPage(cpage);
		pageUtil.setTotalRow(shopCoupons==null?0:shopCoupons.size());
		ModelAndView mav=new ModelAndView("/app/webpreferential/shops/shop");
		mav.addObject("shop", shop);
		mav.addObject("shopCoupons", getCurrentPageList(shopCoupons,(cpage-1)*size,cpage*size));
		mav.addObject("page",cpage);
		mav.addObject("toolNav",pageUtil.getToolsMenu());
		return mav;
	}
	
	/*
	 * 获取最新的店铺列表
	 */
	@SuppressWarnings("unchecked")
	//@RequestMapping(value="/{returnObjectNum}/newShopsList.action")
	//public ModelAndView getNewShops(@PathVariable("returnObjectNum") String returnObjectNum){
	@RequestMapping(value="/newShopsList.action")
	public ModelAndView getNewShops(@RequestParam("returnObjectNum") String returnObjectNum){
		List<Shop> shopList=null;
		shopList=(ArrayList<Shop>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_SHOPS_NEW);
		if(shopList==null){
			shopList=(ArrayList<Shop>)shopsService.selectNew(Integer.parseInt(returnObjectNum==null?"10":returnObjectNum));
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_SHOPS_NEW, 60*60*24*30, shopList);
			logger.info("加载最新店铺数据到缓存!");
		}else{
			logger.info("从缓存中获取最新店铺数据!");
		}
		return new ModelAndView("/app/webpreferential/shops/newShopsList","shopList", shopList);
	}
	
	/*
	 * 获取优惠券最多的店铺列表
	 */
	@SuppressWarnings("unchecked")
	//@RequestMapping(value="/{returnObjectNum}/muchShopsList.action")
	//public ModelAndView getMuchShops(@PathVariable("returnObjectNum") String returnObjectNum){
	@RequestMapping(value="/muchShopsList.action")
	public ModelAndView getMuchShops(@RequestParam("returnObjectNum") String returnObjectNum){
		List<Shop> shopList=null;
		shopList=(ArrayList<Shop>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_SHOPS_MUCH);
		if(shopList==null){
			shopList=(ArrayList<Shop>)shopsService.selectMuch(Integer.parseInt(returnObjectNum==null?"10":returnObjectNum));
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_SHOPS_MUCH, 60*60*24, shopList);
			logger.info("加载优惠券最多的店铺数据到缓存!");
		}else{
			logger.info("从缓存中获取优惠券最多的店铺数据!");
		}
		ModelAndView mvc=new ModelAndView("/app/webpreferential/shops/muchShopsList");
		mvc.addObject("shopList", shopList);
		return mvc;
	}
	
	/*
	 * 获取优惠券点击量最多的店铺列表
	 */
	@SuppressWarnings("unchecked")
	//@RequestMapping(value="/{returnObjectNum}/hotShopsList.action")
	//public ModelAndView getHotShops(@PathVariable("returnObjectNum") String returnObjectNum){
	@RequestMapping(value="/hotShopsList.action")
	public ModelAndView getHotShops(@RequestParam("returnObjectNum") String returnObjectNum){
		List<Shop> shopList=null;
		shopList=(ArrayList<Shop>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_SHOPS_HOT);
		if(shopList==null){
			shopList=(ArrayList<Shop>)shopsService.selectHot(Integer.parseInt(returnObjectNum==null?"10":returnObjectNum));
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_SHOPS_HOT, 5*60, shopList);
			logger.info("加载优惠券点击量最大的店铺数据到缓存!");
		}else{
			logger.info("从缓存中获取优惠券点击量最大的店铺数据!");
		}
		ModelAndView mvc=new ModelAndView("/app/webpreferential/shops/hotShopsList");
		mvc.addObject("shopList", shopList);
		return mvc;
	}

}
