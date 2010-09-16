package com.linkage.app.gqt.webpreferential.coupons.controller;

import java.io.File;
import java.io.FileOutputStream;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;


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
import com.linkage.app.gqt.webpreferential.coupons.entitys.CouponAd;
import com.linkage.app.gqt.webpreferential.coupons.service.AdCouponsService;
import com.linkage.framework.Constants;

@Controller
@RequestMapping("/manage/webpreferential/ad")
public class ManageAdCouponsController {
	final Logger logger = LoggerFactory.getLogger(ManageAdCouponsController.class);
	private MemcachedClient memcachedClient;
	private AdCouponsService adService;
	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	
	/*
	@Autowired
	public void setAdService(AdCouponsService adService) {
		this.adService = adService;
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
			couponShopList=adService.getEffectiveCouponShopList();
			memcachedClient.set(Constants.EC_WEBPREFERENTIAL_COUPONS_SHOPS, 24*60*60, couponShopList);
		}
			return couponShopList;
	}
	/*
	 * 显示列表-所有记录
	 */
	@RequestMapping(value="/index.action",method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mvc=new ModelAndView("/manage/webpreferential/ad/index");
		mvc.addObject("ads",this.adService.selectAllAd());
		return mvc;
	}
	/*
	 * 新增优惠券 
	 */
	@RequestMapping(value="toCreate.action",method=RequestMethod.GET)
	public ModelAndView createAd(HttpServletRequest request,HttpServletResponse response){
		
		ModelAndView mvc=new ModelAndView("/manage/webpreferential/ad/createAd");
		mvc.addObject("all",getListDataFromMemcached());
		return mvc;
	}
	/*
	 * 增加操作-提交表单
	 */
	@RequestMapping(value="/saveAd.action", method=RequestMethod.POST)
	public ModelAndView saveAd(HttpServletRequest request,HttpServletResponse response,@RequestParam("photo") MultipartFile file) throws Exception {
		String logoPath="";
		if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            if(bytes!=null&&bytes.length>0){
            	String tempFileName=file.getOriginalFilename();
            	long fileNameWithotExtend=System.currentTimeMillis();
            	String fileExtend=tempFileName.substring(tempFileName.lastIndexOf("."));
            	String fileName=fileNameWithotExtend+fileExtend;
            	String fileDir=request.getSession().getServletContext().getRealPath("/upload/webpreferential/ad/");
            	//String filePath=request.getSession().getServletContext().getRealPath("/upload/webpreferential/coupons/")+"/"+fileName;
            	createFileDir(fileDir);
            	String filePath=fileDir+"/"+fileName;
            	logoPath="/upload/webpreferential/ad"+"/"+fileName;
            	FileOutputStream fos = new FileOutputStream(filePath);
            	fos.write(bytes);
            	fos.close();
            	
            }
		}
		String type = request.getParameter("type");
		adService.insertAd(Long.valueOf(request.getParameter("couponId")), logoPath,Integer.valueOf(type));
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPONS);
		logger.info("新增优惠券数据成功,从缓存中删除旧的优惠券数据!");
		return new ModelAndView("redirect:/manage/webpreferential/ad/index.action");
	}
	/*
	 * 查看操作
	 */
	@RequestMapping(value="/{id}/view.action")
	public ModelAndView view(@PathVariable("id") long id) {
		CouponAd coupon = adService.selectAdById(id);
		ModelAndView mvc=new ModelAndView("/manage/webpreferential/ad/ad");
		mvc.addObject("ad", coupon);
		return mvc;
	}
	/*
	 * 修改操作-提交修改表单
	 */
	@RequestMapping(value="/update.action", method=RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response,@RequestParam("oldlogo") String oldlogo,@RequestParam("photo") MultipartFile file) throws Exception{
			String logoPath="";
			String id = request.getParameter("id");
			String cid = request.getParameter("couponId");
			//重新上传图片
			if (!file.isEmpty()) {
				logger.info("调用带图片修改的方法");
	            byte[] bytes = file.getBytes();
	            if(bytes!=null&&bytes.length>0){
	            	//覆盖旧的文件
	            	String tempFileName=file.getOriginalFilename();
	            	String fileExtend=tempFileName.substring(tempFileName.lastIndexOf("."));
	            	String oldFileName=oldlogo.substring(oldlogo.lastIndexOf("/")+1,oldlogo.lastIndexOf("."))+fileExtend;
	            	String fileDir=request.getSession().getServletContext().getRealPath("/upload/webpreferential/ad/");
	            	createFileDir(fileDir);
	            	String filePath=fileDir+"/"+oldFileName;
	            	logoPath="/upload/webpreferential/ad"+"/"+oldFileName;
	            	//覆盖旧的文件
	            	FileOutputStream fos = new FileOutputStream(filePath);
	            	fos.write(bytes);
	            	fos.close();
	            	
	            	adService.updateAdByIdWithPic(Long.valueOf(id), Long.valueOf(cid), logoPath);
	            }
			}else{
				logger.info("调用不带图片修改的方法");
				adService.updateAdByIdWithoutPic(Long.valueOf(id), Long.valueOf(cid));
			}
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPON_AD);
		return new ModelAndView("redirect:/manage/webpreferential/ad/index.action");
	}
	/*
	 * 删除操作-根据ID单个删除
	 */
	@RequestMapping(value="/{id}/del.action")
	public ModelAndView delete(@PathVariable("id") long id) {
		adService.deleteAdById(id);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPON_AD);
		return new ModelAndView("redirect:/manage/webpreferential/ad/index.action");
	}
	/*
	 * 状态修改操作
	 */
	@RequestMapping(value="/{id}/{state}/change.action")
	public ModelAndView changeState(HttpServletRequest request,HttpServletResponse response,@PathVariable("id") long id,@PathVariable("state") int state){
		adService.updateAdById(id, state);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_COUPON_AD);
		return new ModelAndView("redirect:/manage/webpreferential/ad/index.action");
	}
	/*
	 * 修改操作-返回修改表单
	 */
	@RequestMapping(value="/{id}/edit.action")
	public ModelAndView edit(@PathVariable("id") long id) {
		CouponAd ad = adService.selectAdById(id);
		ModelAndView mvc=new ModelAndView("/manage/webpreferential/ad/editAd");
		
		
		mvc.addObject("ad", ad);
		mvc.addObject("coupons",getListDataFromMemcached());
		return mvc;
	}
}
