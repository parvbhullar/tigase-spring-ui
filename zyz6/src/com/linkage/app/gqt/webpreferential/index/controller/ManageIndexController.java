package com.linkage.app.gqt.webpreferential.index.controller;

import java.io.File;
import java.io.FileOutputStream;

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

import com.linkage.app.gqt.webpreferential.coupons.entitys.CouponAd;
import com.linkage.app.gqt.webpreferential.index.service.IndexService;
import com.linkage.framework.Constants;



@RequestMapping("/manage/webpreferential/index")
public class ManageIndexController {
	final Logger logger = LoggerFactory.getLogger(ManageIndexController.class);
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
	@RequestMapping(value="/index.action",method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mvc=new ModelAndView("/manage/webpreferential/ad/static/index");
		mvc.addObject("ads",this.indexService.selectAllStateAd());
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
		indexService.insertAd(0, logoPath,Integer.valueOf(type));
		return new ModelAndView("redirect:/manage/webpreferential/index/index.action");
	}
	/*
	 * 查看操作
	 */
	@RequestMapping(value="/{id}/view.action")
	public ModelAndView view(@PathVariable("id") long id) {
		CouponAd coupon = indexService.selectAdById(id);
		ModelAndView mvc=new ModelAndView("/manage/webpreferential/ad/static/ad");
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
	            	
	            	indexService.updateAdByIdWithPic(Long.valueOf(id), 0, logoPath);
	            }
			}else{
			}
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_STATIC_AD);
		return new ModelAndView("redirect:/manage/webpreferential/index/index.action");
	}
	/*
	 * 删除操作-根据ID单个删除
	 */
	@RequestMapping(value="/{id}/del.action")
	public ModelAndView delete(@PathVariable("id") long id) {
		indexService.deleteAdById(id);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_STATIC_AD);
		return new ModelAndView("redirect:/manage/webpreferential/index/index.action");
	}
	/*
	 * 状态修改操作
	 */
	@RequestMapping(value="/{id}/{state}/change.action")
	public ModelAndView changeState(HttpServletRequest request,HttpServletResponse response,@PathVariable("id") long id,@PathVariable("state") int state){
		indexService.updateAdById(id, state);
		memcachedClient.delete(Constants.EC_WEBPREFERENTIAL_STATIC_AD);
		return new ModelAndView("redirect:/manage/webpreferential/index/index.action");
	}
	/*
	 * 修改操作-返回修改表单
	 */
	@RequestMapping(value="/{id}/edit.action")
	public ModelAndView edit(@PathVariable("id") long id) {
		CouponAd ad = indexService.selectAdById(id);
		ModelAndView mvc=new ModelAndView("/manage/webpreferential/ad/static/editPic");
		mvc.addObject("ad", ad);
		return mvc;
	}

}
