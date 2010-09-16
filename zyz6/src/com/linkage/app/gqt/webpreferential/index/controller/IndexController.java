package com.linkage.app.gqt.webpreferential.index.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.linkage.app.gqt.webpreferential.coupons.entitys.Coupon;
import com.linkage.app.gqt.webpreferential.coupons.entitys.CouponAd;
import com.linkage.app.gqt.webpreferential.index.entitys.User;
import com.linkage.app.gqt.webpreferential.index.service.IndexService;
import com.linkage.app.gqt.webpreferential.shops.entitys.Shop;
import com.linkage.framework.Constants;
import com.linkage.framework.util.BASE64;
/**
 * 
 * 首页Action
 *
 *
 * @create on 2010-6-17 上午11:49:46
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */

@RequestMapping("/app/webpreferential")
public class IndexController {
	final Logger logger = LoggerFactory.getLogger(IndexController.class);
	private IndexService indexService;
	private MemcachedClient memcachedClient;
	
	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	@Autowired
	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
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
			couponShopList=indexService.getEffectiveCouponShopList();
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_COUPONS_SHOPS, 24*60*60, couponShopList);
		}
		return couponShopList;
	}
	
	//接口访问
	@RequestMapping(value="/interfaceIndex.action",method=RequestMethod.POST) 
	public ModelAndView interfaceMall (HttpServletRequest request,HttpServletResponse response) {
		String param = request.getParameter("param");
		logger.info("****************************************param="+param);
		if(param!=null&&!param.equals(""))
		{
			String paramDecode = BASE64.decode(param);
			logger.info("****************************************paramDecode="+paramDecode);
			String[] params = paramDecode.split("\\u007C");
			if(params!=null){
				logger.info("****************************************params.length="+params.length);
				for(int i=0;i<params.length;i++){
					logger.info("****************************************params["+i+"]="+params[i]);
				}
			}
			String isXiao = params[0];
			if(isXiao.equals("0")||isXiao.equals("1"))
			{
				String name = params[1];
				String dn = params[2];
				User user = new User();
				user.setName(name);
				user.setDn(dn);
				user.setState(isXiao);
				user.setLoginName(params[3]);
				user.setPassword(params[4]);
				request.getSession().setAttribute("userinfo", user);
			}
		}
		return mall(request,response);
	}
	
	
	/*
	 * 首页
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/index.action")
	public ModelAndView mall(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mvc=new ModelAndView("/app/webpreferential/index");
		List<Coupon> couponsList=getListDataFromMemcached();
		List<Coupon> tuijian = new ArrayList<Coupon>();
		for (Coupon coupon : couponsList) {
			if(coupon.getIsRec()==1){
				tuijian.add(coupon);
			}
		}
		List<Coupon> hotList=null;
		hotList = (ArrayList<Coupon>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_COUPONS_HOT);
		if(hotList==null)
		{
			logger.info("加载热门下载优惠券数据到缓存...");
			hotList = this.indexService.selectHotCoupons(10);
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_COUPONS_HOT, 300, hotList);
		}
		List<Coupon> newList=null;
		newList = (ArrayList<Coupon>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_COUPONS_NEW);
		if(newList==null)
		{
			logger.info("加载最新优惠券数据到缓存...");
			newList = this.indexService.selectNewCoupons(10);
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_COUPONS_NEW, 300, newList);
		}
		List<Shop> shopList=null;
		shopList=(ArrayList<Shop>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_SHOPS_NEW);
		if(shopList==null){
			shopList=(ArrayList<Shop>)indexService.selectNew(10);
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_SHOPS_NEW, 60*60*24*30, shopList);
			logger.info("加载优惠券最多的店铺数据到缓存!");
		}else{
			logger.info("从缓存中获取优惠券最多的店铺数据!");
		}
		CouponAd ad = null;
		ad = (CouponAd)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_COUPON_AD);
		if(ad==null)
		{
			List<CouponAd> ads = this.indexService.selectOnlineAd(1);
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
		CouponAd sad = null;
		sad = (CouponAd)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_STATIC_AD);
		if(sad==null)
		{
			List<CouponAd> ads = this.indexService.selectOnlineAd(0);
			if(ads!=null&&ads.size()>0)
			{
				sad = ads.get(0);
			}
			else
			{
				sad = new CouponAd();
			}
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_STATIC_AD, 60*60*24*30, sad);
			logger.info("加载优惠券广告数据到缓存!");
		}
		else
		{
			logger.info("从缓存中加载优惠券广告数据!");
		}
		mvc.addObject("jsonlist",convetIntegrationListToJson(newList));
		mvc.addObject("jsonhotlist",convetIntegrationListToJson(hotList));
		mvc.addObject("shopList", shopList);
		mvc.addObject("cad", ad);
		mvc.addObject("sad", sad);
		mvc.addObject("tuijian", tuijian);
		return mvc;
	}
	/**
	 * 辅助方法
	 * @param integrationList
	 * @return
	 */
	private String convetIntegrationListToJson(List<Coupon> integrationList)
	{
	    JSONArray jsonarray = new JSONArray();
	    if(null!=integrationList)
	    {
	       Map<String, String> data = null;
	       for(Coupon vo : integrationList)
	       {
	    	   data = new HashMap<String, String>();
	    		   data.put("url", vo.getPic());
		    	   data.put("title",vo.getName());
		    	   data.put("id", String.valueOf(vo.getId()));
	    	   
	    	   jsonarray.put(data);
	       }
	    }
		return jsonarray.toString();
	}
	/*
	 * 下载优惠券
	 */
	@RequestMapping(value="/download.action",method=RequestMethod.POST)
	public ModelAndView download(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("dn:----->"+request.getParameter("dn"));
		this.indexService.saveDown(request.getParameter("dn"), request.getRemoteAddr(), Long.valueOf(request.getParameter("id")), Long.valueOf(request.getParameter("shopId")),request.getParameter("message"));
		return null;
	}
}
