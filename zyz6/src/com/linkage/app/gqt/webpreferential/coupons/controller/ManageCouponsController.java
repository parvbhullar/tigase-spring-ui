package com.linkage.app.gqt.webpreferential.coupons.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.linkage.app.gqt.webpreferential.coupons.entitys.Coupon;
import com.linkage.app.gqt.webpreferential.coupons.service.CouponsService;
import com.linkage.app.gqt.webpreferential.shops.entitys.Shop;
import com.linkage.framework.Constants;
import com.linkage.framework.page.CouponPageUtil;

@Controller
@RequestMapping("/manage/webpreferential/coupons")
public class ManageCouponsController {
	final Logger logger = LoggerFactory.getLogger(ManageCouponsController.class);
	private CouponsService couponsService;
	private MemcachedClient memcachedClient;
	
	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	/*
	@Autowired
	public void setCouponsService(CouponsService couponsService) {
		this.couponsService = couponsService;
	}
	*/
	
	/*
	 * 辅助方法
	 * 递归创建文件夹
	 */
	private void createFileDir(String path)throws Exception{
		File dirFile = new File(path);
		boolean bFile = dirFile.exists();
		if( bFile == true ){
			logger.info("文件路径[{}]存在!",path);
		} else {
			logger.info("文件路径[{}]不存在,需要创建!",path);
			bFile = dirFile.mkdirs();
			logger.info("文件路径[{}]创建成功!",path);
		}
	}
	
	/*
	 * 
	 */
	private List<Coupon> getCurrentPageList(List<Coupon> list,int page,int size){
		if(list!=null){
			return list.size() > size ? list.subList(page, size) : list.subList(page, list.size());
		}else{
			return new ArrayList<Coupon>();
		}	
	}
	
	private List<Coupon> coverMap2List(Map<Long,List<Coupon>> map){
		Iterator<List<Coupon>> it = map.values().iterator();
		List<Coupon> rt = new ArrayList<Coupon>();
		while(it.hasNext()){
			List<Coupon> cs = (ArrayList<Coupon>)it.next();
			for(Coupon obj : cs){
				rt.add(obj);
			}
		}
		return rt;
	}
	
	/*
	 * 显示列表-所有记录
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/index.action",method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		logger.info("进入coupons");
		Map<Long,List<Coupon>> map = null;
		map=(HashMap<Long,List<Coupon>>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_COUPONS);
		if(map==null){
			map = new HashMap<Long,List<Coupon>>();
			List<Shop> shopList=null;
			shopList=(ArrayList<Shop>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_SHOPS);
			if(shopList==null){
				shopList=(ArrayList<Shop>)couponsService.selectAllShops();
				memcachedClient.set(Constants.EC_WEBPREFERENTIAL_SHOPS, 24*60*60, shopList);
			}
			List<Coupon> allCoupons = this.couponsService.selectAll();
			if(null!=shopList){
				for (Shop shop : shopList) {
					if(shop.getState()==1){
						List<Coupon> couponList = new ArrayList<Coupon>();
						for (Coupon cp : allCoupons) {
							if(shop.getId() == cp.getShopId()){
								couponList.add(cp);
							}
							map.put(shop.getId(), couponList);
						}
					}
				}
			}
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_COUPONS, 24*60*60, map);
			logger.info("加载优惠券数据到缓存!");
		}else{
			logger.info("从缓存中获取优惠券数据!");
		}
		List<Coupon> couponsList = coverMap2List(map);
		int size = Constants.EC_WEBPREFERENTIAL_MANAGE_SHOPS_SIZE;
		String pages = request.getParameter("page");
		int page = pages==null||pages.equals("") ? 1 : Integer.valueOf(pages);
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(size);
		pageUtil.setCurPage(page);
		pageUtil.setTotalRow(couponsList.size());
		page = page>pageUtil.getTotalPage()? 1:page;
		ModelAndView mvc=new ModelAndView("/manage/webpreferential/coupons/index");
		mvc.addObject("coupons", getCurrentPageList(couponsList,(page-1)*size,page*size));
		mvc.addObject("page",page);
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		return mvc;
	}
	
	/*
	 * 查看操作
	 */
	@RequestMapping(value="/{id}/view.action")
	public ModelAndView view(@PathVariable("id") long id) {
		Coupon coupon = couponsService.selectById(id);
		ModelAndView mvc=new ModelAndView("/manage/webpreferential/coupons/coupon");
		mvc.addObject("coupon", coupon);
		return mvc;
	}
	
	/*
	 * 新增优惠券 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="createCoupons.action",method=RequestMethod.GET)
	public ModelAndView createCoupons(){
		List<Shop> shopList=null;
		shopList=(ArrayList<Shop>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_SHOPS);
		if(shopList==null){
			shopList=(ArrayList<Shop>)couponsService.selectAllShops();
			memcachedClient.set("ec_webpreferential_shops", 24*60*60, shopList);
		}
		List<Shop> tempShopList=new ArrayList<Shop>();
		for(Shop shop : shopList){
			if(shop.getState()!=1){
				tempShopList.add(shop);
				//shopList.remove(shop);
			}
		}
		shopList.removeAll(tempShopList);
		ModelAndView mvc=new ModelAndView("/manage/webpreferential/coupons/createCoupons");
		mvc.addObject("shopList", shopList);
		return mvc;
	}
	
	/*
	 * 增加操作-提交表单
	 */
	@RequestMapping(value="/saveCoupon.action", method=RequestMethod.POST)
	public ModelAndView saveCoupon(HttpServletRequest request,HttpServletResponse response,@RequestParam("photo") MultipartFile file) throws Exception {
		Coupon coupon=new Coupon();
		BeanUtils.populate(coupon, request.getParameterMap());
		if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            if(bytes!=null&&bytes.length>0){
            	String tempFileName=file.getOriginalFilename();
            	long fileNameWithotExtend=System.currentTimeMillis();
            	String fileExtend=tempFileName.substring(tempFileName.lastIndexOf("."));
            	String fileName=fileNameWithotExtend+fileExtend;
            	String fileDir=request.getSession().getServletContext().getRealPath("/upload/webpreferential/coupons/");
            	//String filePath=request.getSession().getServletContext().getRealPath("/upload/webpreferential/coupons/")+"/"+fileName;
            	createFileDir(fileDir);
            	String filePath=fileDir+"/"+fileName;
            	String logoPath="/upload/webpreferential/coupons"+"/"+fileName;
            	FileOutputStream fos = new FileOutputStream(filePath);
            	fos.write(bytes);
            	fos.close();
            	coupon.setPic(logoPath);
            }
		}
		couponsService.saveCoupon(coupon);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS);
		logger.info("新增优惠券数据成功,从缓存中删除旧的优惠券数据!");
		return new ModelAndView("redirect:/manage/webpreferential/coupons/index.action");
	}
	
	/*
	 * 修改操作-返回修改表单
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/{id}/edit.action")
	public ModelAndView edit(@PathVariable("id") long id) {
		Coupon coupon = couponsService.selectById(id);
		ModelAndView mvc=new ModelAndView("/manage/webpreferential/coupons/editCoupon");
		List<Shop> shopList=null;
		shopList=(ArrayList<Shop>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_SHOPS);
		if(shopList==null){
			shopList=(ArrayList<Shop>)couponsService.selectAllShops();
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_SHOPS, 24*60*60, shopList);
		}
		mvc.addObject("shopList", shopList);
		mvc.addObject("coupon", coupon);
		return mvc;
	}
	
	/*
	 * 修改操作-提交修改表单
	 */
	@RequestMapping(value="/update.action", method=RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response,@RequestParam("oldlogo") String oldlogo,@RequestParam("photo") MultipartFile file) throws Exception{
		Coupon coupon=new Coupon();
		BeanUtils.populate(coupon, request.getParameterMap());
			//重新上传图片
			if (!file.isEmpty()) {
				logger.info("调用带图片修改的方法");
	            byte[] bytes = file.getBytes();
	            if(bytes!=null&&bytes.length>0){
	            	//覆盖旧的文件
	            	String tempFileName=file.getOriginalFilename();
	            	String fileExtend=tempFileName.substring(tempFileName.lastIndexOf("."));
	            	String oldFileName=oldlogo.substring(oldlogo.lastIndexOf("/")+1,oldlogo.lastIndexOf("."))+fileExtend;
	            	String fileDir=request.getSession().getServletContext().getRealPath("/upload/webpreferential/coupons/");
	            	createFileDir(fileDir);
	            	String filePath=fileDir+"/"+oldFileName;
	            	String logoPath="/upload/webpreferential/coupons"+"/"+oldFileName;
	            	//覆盖旧的文件
	            	FileOutputStream fos = new FileOutputStream(filePath);
	            	fos.write(bytes);
	            	fos.close();
	            	coupon.setPic(logoPath);
	            	couponsService.updateByIdWithPic(coupon);
	            }
			}else{
				logger.info("调用不带图片修改的方法");
				couponsService.updateByIdWithoutPic(coupon);
			}
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_COUPONS);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_NEW);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_MUCH);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_HOT);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_SHOPS);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_NEW);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_HOT);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS);
		logger.info("修改优惠券数据成功,从缓存中删除旧的优惠券数据!");
		return new ModelAndView("redirect:/manage/webpreferential/coupons/index.action");
	}
	
	/*
	 * 删除操作-根据ID单个删除
	 */
	@RequestMapping(value="/{id}/del.action")
	public ModelAndView delete(@PathVariable("id") long id) {
		couponsService.deleteById(id);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_COUPONS);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_NEW);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_MUCH);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_HOT);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_SHOPS);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_NEW);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_HOT);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS);
		logger.info("删除优惠券数据成功,从缓存中删除旧的优惠券数据!");
		return new ModelAndView("redirect:/manage/webpreferential/coupons/index.action");
	}
	
	/*
	 * 删除操作-批量删除
	 */
	@RequestMapping(value="/del.action")
	public ModelAndView batchDelete(HttpServletRequest request,HttpServletResponse response,@RequestParam("allId") String allId){
		//logger.info(allId);
		if(allId!=null&&allId.length()>0){
			String[] items=allId.split(",");
			List<String> list=Arrays.asList(items);
			List<Long> _list=new ArrayList<Long>();
			for(String s : list){
				_list.add(Long.valueOf(s));
			}
			couponsService.batchDeleteById(_list);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_COUPONS);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_NEW);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_MUCH);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_HOT);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_SHOPS);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_NEW);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_HOT);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS);
			logger.info("批量删除优惠券数据成功,从缓存中删除旧的优惠券数据!");
		}
		return new ModelAndView("redirect:/manage/webpreferential/coupons/index.action");
	}
	
	/*
	 * 状态修改操作
	 */
	@RequestMapping(value="/{id}/{state}/change.action")
	public ModelAndView changeState(HttpServletRequest request,HttpServletResponse response,@RequestParam("page") int page,@PathVariable("id") long id,@PathVariable("state") int state){
		if(state<5){
			couponsService.updateStateById(id, state);
		}
		else
		{
			state = state-10;
			couponsService.updateRecById(id, state);
		}
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_COUPONS);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_NEW);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_MUCH);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_HOT);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_SHOPS);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_NEW);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_HOT);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS);
		logger.info("优惠券状态数据修改成功,从缓存中删除旧的优惠券数据!");
		return new ModelAndView("redirect:/manage/webpreferential/coupons/index.action?page="+page);
	}
}
