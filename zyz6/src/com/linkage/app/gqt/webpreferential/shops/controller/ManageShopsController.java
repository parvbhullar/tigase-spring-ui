package com.linkage.app.gqt.webpreferential.shops.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.linkage.app.gqt.webpreferential.shops.entitys.Shop;
import com.linkage.app.gqt.webpreferential.shops.service.ShopsService;
import com.linkage.framework.Constants;
import com.linkage.framework.page.CouponPageUtil;


@RequestMapping("/manage/webpreferential/shops")
public class ManageShopsController {

	final Logger logger = LoggerFactory.getLogger(ManageShopsController.class);
	
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
	
	/*
	 * 辅助方法：
	 * 从缓存中获取数据，如果没有则从数据库中获取数据并放入缓存
	 * 数据结构List<Shop>
	 */
	@SuppressWarnings("unchecked")
	private List<Shop> getListDataFromMemcached(){
		List<Shop> shopList=(List<Shop>)memcachedClient.get(Constants.EC_WEBPREFERENTIAL_SHOPS);
		if(shopList==null){
			shopList=(ArrayList<Shop>)shopsService.selectAll();
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_SHOPS, 30*24*60*60, shopList);
			logger.info("加载店铺数据到缓存!");
		}else{logger.info("从缓存中获取店铺数据!");}
		return shopList;
	}
	
	/*
	 * 辅助方法：
	 * 根据ID从缓存中获取单个对象
	 */
	private Shop getShop(long id){
		Shop shop=null;
		List<Shop> shopList=getListDataFromMemcached();
		for(Shop _shop : shopList){
			if(_shop.getId()==id){
				shop=_shop;
				break;
			}
		}
		return shop;
	}
	
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
	 * 显示列表-所有记录
	 */
	@RequestMapping(value="/index.action")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		List<Shop> shopList=getListDataFromMemcached();
		String pagevar = request.getParameter("page");
		int page = pagevar==null||pagevar.equals("") ? 1 : Integer.valueOf(pagevar);
		int size = Constants.EC_WEBPREFERENTIAL_MANAGE_SHOPS_SIZE;
		
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(size);
		pageUtil.setCurPage(page);
		pageUtil.setTotalRow(shopList==null?0:shopList.size());
		page = page>pageUtil.getTotalPage()? 1:page;
		ModelAndView mvc=new ModelAndView("/manage/webpreferential/shops/index");
		mvc.addObject("shopList",getCurrentPageList(shopList,(page-1)*size,page*size));
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		mvc.addObject("page",page);
		return mvc;
	}
	/**
	 * 辅助方法
	 * @param list
	 * @param page
	 * @param size
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
	 * 根据Id 显示单条记录
	 */
	@RequestMapping(value="/{id}/shop.action")
	public ModelAndView shop(@PathVariable("id") long id){
		Shop shop=getShop(id);
		return new ModelAndView("/manage/webpreferential/shops/shop","shop",shop);
	}

	/*
	 * 分页
	 * 显示列表-根据条件
	 */
	@RequestMapping(value="/{startPage}/index.action")
	public ModelAndView indexPage(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav=new ModelAndView("example/list");
		mav.addObject("userList", null);
		mav.addObject("count", null);
		return mav;
	}
	
	/*
	 * 增加操作-返回增加的表单
	 */
	@RequestMapping(value="/add.action")
	public ModelAndView add() throws Exception {
		return new ModelAndView("/manage/webpreferential/shops/createShop");
	}
	
	/*
	 * 增加操作-提交表单
	 */
	@RequestMapping(value="/create.action", method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request,HttpServletResponse response,@RequestParam("logo") MultipartFile file,@RequestParam("shopImage") MultipartFile shopImage) throws Exception {
		Shop shop=new Shop();
		BeanUtils.populate(shop, request.getParameterMap());
		if (!file.isEmpty()&&!shopImage.isEmpty()) {
			/*
			System.out.println(file.getName());//字段名称 "logo"
			System.out.println(file.getOriginalFilename()); //文件名称 *.*不带路径
			System.out.println(file.getContentType());//内容类型 image/gif
			*/
			long fileNameWithotExtend=System.currentTimeMillis();
            byte[] bytes = file.getBytes();
            if(bytes!=null&&bytes.length>0){
            	String tempFileName=file.getOriginalFilename();
            	String fileExtend=tempFileName.substring(tempFileName.lastIndexOf("."));
            	String fileName=fileNameWithotExtend+"_logo"+fileExtend;
            	String fileDir=request.getSession().getServletContext().getRealPath("/upload/webpreferential/shop/");
            	createFileDir(fileDir);
            	String filePath=fileDir+"/"+fileName;
            	String logoPath="/upload/webpreferential/shop/"+fileName;
            	FileOutputStream fos = new FileOutputStream(filePath);
            	fos.write(bytes);
            	fos.close();
            	shop.setLogo(logoPath);
            }
            byte[] _bytes = shopImage.getBytes();
            if(_bytes!=null&&_bytes.length>0){
            	String tempFileName=shopImage.getOriginalFilename();
            	String fileExtend=tempFileName.substring(tempFileName.lastIndexOf("."));
            	String fileName=fileNameWithotExtend+fileExtend;
            	String fileDir=request.getSession().getServletContext().getRealPath("/upload/webpreferential/shop/");
            	createFileDir(fileDir);
            	String filePath=fileDir+"/"+fileName;
            	String shopImagePath="/upload/webpreferential/shop/"+fileName;
            	FileOutputStream fos = new FileOutputStream(filePath);
            	fos.write(_bytes);
            	fos.close();
            	shop.setShopImage(shopImagePath);
            }
		}
		shopsService.create(shop);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS);
		logger.info("新增店铺数据成功,从缓存中删除旧的店铺数据!");
		return new ModelAndView("redirect:/manage/webpreferential/shops/index.action");
	}
	
	/*
	 * 修改操作-返回修改表单
	 */
	@RequestMapping(value="/{id}/edit.action")
	public ModelAndView edit(@PathVariable("id") long id) {
		Shop shop=getShop(id);
		//Shop shop=shopsService.selectById(id);
		return new ModelAndView("/manage/webpreferential/shops/editShop","shop",shop);
	}
	/*
	 * 修改操作-提交修改表单
	 */
	@RequestMapping(value="/update.action", method=RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response,@RequestParam("oldlogo") String oldlogo,@RequestParam("logo") MultipartFile file,@RequestParam("oldshopimage") String oldshopimage,@RequestParam("shopImage") MultipartFile shopImage) throws Exception{
		Shop shop=new Shop();
		BeanUtils.populate(shop, request.getParameterMap());
		int updateFlag=0;
		//重新上传图片
		if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            if(bytes!=null&&bytes.length>0){   	
            	String tempFileName=file.getOriginalFilename();
            	String fileExtend=tempFileName.substring(tempFileName.lastIndexOf("."));
            	String oldFileName=oldlogo.substring(oldlogo.lastIndexOf("/")+1,oldlogo.lastIndexOf("."))+fileExtend;
            	String fileDir=request.getSession().getServletContext().getRealPath("/upload/webpreferential/shop/");
            	createFileDir(fileDir);
            	String filePath=fileDir+"/"+oldFileName;
            	String logoPath="/upload/webpreferential/shop/"+oldFileName;
            	FileOutputStream fos = new FileOutputStream(filePath);
            	fos.write(bytes);
            	fos.close();
            	shop.setLogo(logoPath);
            	updateFlag=updateFlag+1;
            }
		}
		if (!shopImage.isEmpty()) {
            byte[] bytes = shopImage.getBytes();
            if(bytes!=null&&bytes.length>0){   	
            	String tempFileName=shopImage.getOriginalFilename();
            	String fileExtend=tempFileName.substring(tempFileName.lastIndexOf("."));
            	String oldFileName=oldshopimage.substring(oldshopimage.lastIndexOf("/")+1,oldshopimage.lastIndexOf("."))+fileExtend;
            	String fileDir=request.getSession().getServletContext().getRealPath("/upload/webpreferential/shop/");
            	createFileDir(fileDir);
            	String filePath=fileDir+"/"+oldFileName;
            	String shopImagePath="/upload/webpreferential/shop/"+oldFileName;
            	FileOutputStream fos = new FileOutputStream(filePath);
            	fos.write(bytes);
            	fos.close();
            	shop.setShopImage(shopImagePath);
            	updateFlag=updateFlag+2;
            }
		}
		if(updateFlag==0){
			logger.info("调用不带图片修改的方法");
			shopsService.updateByIdWithoutLogoAndShopImage(shop);
		}else if(updateFlag==1){
			logger.info("调用只带LOGO图片修改的方法");
			shopsService.updateByIdWithLogo(shop);
		}else if(updateFlag==2){
			logger.info("调用只带门店图片修改的方法");
			shopsService.updateByIdWithShopImage(shop);
		}else if(updateFlag==3){
			logger.info("调用带门店图片和LOGO语言图片修改的方法");
			shopsService.updateByIdWithLogoAndShopImage(shop);
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
		logger.info("修改店铺数据成功,从缓存中删除旧的店铺数据!");
		return new ModelAndView("redirect:/manage/webpreferential/shops/index.action");
	}
	
	/*
	 * 删除操作-根据ID单个删除
	 */
	@RequestMapping(value="/{id}/del.action")
	public ModelAndView delete(@PathVariable("id") long id) {
		shopsService.deleteById(id);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_COUPONS);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_NEW);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_MUCH);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_HOT);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_SHOPS);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_NEW);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_HOT);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS);
		logger.info("删除店铺数据成功,从缓存中删除旧的店铺数据!");
		return new ModelAndView("redirect:/manage/webpreferential/shops/index.action");
	}
	
	/*
	 * 删除操作-批量删除
	 */
	@RequestMapping(value="/del.action")
	public ModelAndView batchDelete(HttpServletRequest request,HttpServletResponse response,@RequestParam("allId") String allId){
		logger.info(allId);
		if(allId!=null&&allId.length()>0){
			String[] items=allId.split(",");
			List<String> list=Arrays.asList(items);
			List<Long> _list=new ArrayList<Long>();
			for(String s : list){
				_list.add(Long.valueOf(s));
			}
			shopsService.batchDeleteById(_list);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_COUPONS);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_NEW);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_MUCH);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_HOT);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_SHOPS);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_NEW);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_HOT);
			memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS);
			logger.info("批量删除店铺数据成功,从缓存中删除旧的店铺数据!");
		}
		/*String[] items = request.getParameterValues("items");
		List<String> list=Arrays.asList(items);
		List<Long> _list=new ArrayList<Long>();
		for(String s : list){
			_list.add(Long.valueOf(s));
		}
		shopsService.batchDeleteById(_list);
		memcachedClient.delete("ec_webpreferential_shops");
		logger.info("批量删除店铺数据成功,从缓存中删除旧的店铺数据!");*/
		//return new ModelAndView("/manage/webpreferential/shops/ajaxResult","result","删除成功!");
		return null;
	}
	
	/*
	 * 状态修改操作
	 */
	@RequestMapping(value="/{id}/{state}/change.action")
	public ModelAndView changeState(@PathVariable("id") long id,@PathVariable("state") int state){
		shopsService.updateStateById(id, state);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_COUPONS);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_NEW);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_MUCH);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_SHOPS_HOT);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_SHOPS);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_NEW);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS_HOT);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS);
		logger.info("店铺状态数据修改成功,从缓存中删除旧的店铺数据!");
		return new ModelAndView("redirect:/manage/webpreferential/shops/index.action");
	}
	
}
