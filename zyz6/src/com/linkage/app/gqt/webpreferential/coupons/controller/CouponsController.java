package com.linkage.app.gqt.webpreferential.coupons.controller;

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
import com.linkage.app.gqt.webpreferential.coupons.entitys.CouponAd;
import com.linkage.app.gqt.webpreferential.coupons.service.CouponsService;
import com.linkage.framework.Constants;
import com.linkage.framework.page.CouponPageUtil;
/**
 * 
 * 优惠券前台action
 *
 * @author jiale.wang
 *
 * @create on 2010-6-12 下午04:27:41
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */

@RequestMapping("/app/webpreferential/coupons")
public class CouponsController {
	final Logger logger = LoggerFactory.getLogger(CouponsController.class);
	private CouponsService couponsService;
	private MemcachedClient memcachedClient;
	
	/*
	@Autowired
	public void setCouponsService(CouponsService couponsService) {
		this.couponsService = couponsService;
	}
	*/
	
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
	private List<Coupon> getCurrentPageList(List<Coupon> list,int page,int size){
		if(list!=null){
			return list.size() > size ? list.subList(page, size) : list.subList(page, list.size());
		}else{
			return new ArrayList<Coupon>();
		}
	}
	
	/*
	 * 辅助方法：
	 * 从缓存中获取数据，如果没有则从数据库中获取数据并放入缓存
	 * 数据结构List<Coupon>
	 * 说明
	 *   1、列表中的Coupon的状态都为1(有效状态),且起止时间符合要求
	 *   2、Coupon关联的Shop的状态都为1(有效状态)
	 */
	@SuppressWarnings("unchecked")
	private List<Coupon> getListDataFromMemcached(){
		List<Coupon> couponShopList=(List<Coupon>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_COUPONS_SHOPS);
		if(couponShopList==null){
			couponShopList=couponsService.getEffectiveCouponShopList();
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_COUPONS_SHOPS, 24*60*60, couponShopList);
		}
			return couponShopList;
	}
	
	
	/*
	 * 显示列表-所有记录 带分页
	 */
	@RequestMapping(value="/index.action")
	public ModelAndView index(@RequestParam("page") int page){
		List<Coupon> couponsList=getListDataFromMemcached();
		int size = Constants.EC_WEBPREFERENTIAL_COUPONS_SIZE;
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(size);
		pageUtil.setCurPage(page);
		pageUtil.setTotalRow(couponsList.size());
		ModelAndView mvc=new ModelAndView("/app/webpreferential/coupons/allCouponsList");
		mvc.addObject("coupons", getCurrentPageList(couponsList,(page-1)*size,page*size));
		mvc.addObject("page",page);
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		return mvc;
	}
	
	/*
	 * 查看详细
	 */
	@RequestMapping(value="/coupon.action")
	public ModelAndView edit(@RequestParam("id") long id) {
		List<Coupon> couponsList=getListDataFromMemcached();
		Coupon coupon=null;
		for(Coupon _coupon:couponsList){
			if(_coupon.getId()==id){
				coupon=_coupon;
				break;
			}
		}
		CouponAd ad = null;
		ad = (CouponAd)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_COUPON_AD);
		if(ad==null)
		{
			List<CouponAd> ads = this.couponsService.selectOnlineAd(1);
			if(ads!=null&&ads.size()>0)
			{
					ad = ads.get(0);
			}
			else
			{
				ad = new CouponAd();
			}
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_COUPON_AD, 60*60*24*30, ad);
			logger.info("加载优惠券广告数据到缓存!");
		}
		else
		{
			logger.info("从缓存中加载优惠券广告数据!");
		}
		ModelAndView mvc=new ModelAndView("/app/webpreferential/coupons/coupon");
		mvc.addObject("coupon", coupon);
		mvc.addObject("ad", ad);
		mvc.addObject("downs", this.couponsService.couponDownNum(id));
		return mvc;
	}
	
	/*
	 * 最新优惠券列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/newsCouponList.action")
	public ModelAndView newsCouponList(@RequestParam("returnObjectNum") int returnObjectNum) {
		List<Coupon> newList=null;
		newList = (ArrayList<Coupon>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_COUPONS_NEW);
		if(newList==null){
			logger.info("加载最新优惠券数据到缓存...");
			newList = this.couponsService.selectNewCoupons(returnObjectNum);
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_COUPONS_NEW, 24*60*60, newList);
		}else{
			logger.info("从缓存读取最新优惠券数据...");
		}
		ModelAndView mvc=new ModelAndView("/app/webpreferential/coupons/newCouponsList");
		mvc.addObject("news",newList);
		return mvc;
	}
	
	/*
	 * 最热优惠券列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/hotCouponList.action")
	public ModelAndView hotCouponList(@RequestParam("returnObjectNum") int returnObjectNum) {
		List<Coupon> hotList=null;
		hotList = (ArrayList<Coupon>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_COUPONS_HOT);
		if(hotList==null){
			logger.info("加载热门下载优惠券数据到缓存...");
			hotList = this.couponsService.selectHotCoupons(returnObjectNum);
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_COUPONS_HOT, 5*60, hotList);
		}else{
			logger.info("从缓存读取最热下载优惠券数据...");
		}
		ModelAndView mvc=new ModelAndView("/app/webpreferential/coupons/hotCouponsList");
		mvc.addObject("hots",hotList);
		return mvc;
	}
	
}
