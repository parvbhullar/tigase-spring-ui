package com.linkage.app.gqt.init.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.spy.memcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkage.app.gqt.backstage.org.entitys.Org;
import com.linkage.app.gqt.init.dao.InitDao;
import com.linkage.app.gqt.init.entity.OrgArea;
import com.linkage.app.gqt.init.entity.OrgType;
import com.linkage.app.gqt.init.entity.SysParam;
import com.linkage.framework.cache.CacheKey;

@Service
public class InitService {
	
	final Logger logger = LoggerFactory.getLogger(InitService.class);
	private InitDao initDao;
	private MemcachedClient memcachedClient;
	
	@Autowired
	public InitService(InitDao initDao,MemcachedClient memcachedClient){
		this.initDao=initDao;
		try{
			this.memcachedClient=memcachedClient;
		}catch(Exception e){
			e.printStackTrace();
			logger.info("缓存对象初始化失败!");
		}
		init();
	}
	
	private void init(){
		initSysParam();
		initFwyx();
		initOrgType();
		initOrgArea();
		initOrg();
		
	}
	public void initSysParam(){
		logger.info("开始初始化系统参数数据......");
		Map<String, List<SysParam>> map=new HashMap<String, List<SysParam>>();
		try{
			List<SysParam> list=this.initDao.selectAllSysParams();
			String key=null;
			List<SysParam> _list=null;
			for(SysParam sysParam : list){
				if(key==null||!key.equals(sysParam.getParamCode())){
					key=sysParam.getParamCode();
					_list=new ArrayList<SysParam>();
					map.put(key, _list);
				}
				_list.add(sysParam);
			}
			memcachedClient.add(CacheKey.ZYZ_SYSPARAM, 30*24*60*60, map);
		}catch(Exception e){
			logger.info("系统参数数据初始化失败!");
			e.printStackTrace();
		}
	}
	
	public void initOrgType(){
		logger.info("开始初始化系统组织类型数据......");
		try{
			List<OrgType> list=this.initDao.selectAllOrgTypes();
			memcachedClient.add(CacheKey.ZYZ_ORGTYPE, 30*24*60*60, list);
		}catch(Exception e){
			logger.info("系统组织类型数据初始化失败!");
			e.printStackTrace();
		}
	}
	
	public void initFwyx(){
		logger.info("开始初始化南通服务意向......");
		try{
			List<SysParam> list=this.initDao.selectNtFwyx();
			memcachedClient.add(CacheKey.ZYZ_NT_FWYX, 30*24*60*60, list);
		}catch(Exception e){
			logger.info("南通服务意向数据初始化失败!");
			e.printStackTrace();
		}
	}
	
	private void initOrgArea(){
		logger.info("开始初始化系统组织区域数据......");
		try{
			List<OrgArea> list=this.initDao.selectAllOrgAreas();
			memcachedClient.add(CacheKey.ZYZ_ORGAREA, 30*24*60*60, list);
		}catch(Exception e){
			logger.info("系统组织区域数据初始化失败!");
			e.printStackTrace();
		}
	}
	
	public void initOrg(){
		logger.info("开始初始化系统组织机构数据......");
		try{
			List<Org> list=this.initDao.selectAllOrgs();
			memcachedClient.add(CacheKey.ZYZ_ORG, 30*24*60*60, list);
			//List<Org> _list=this.initDao.selectAllManageOrgs();
			//memcachedClient.add(CacheKey.ZYZ_MANAGE_ORG, 30*24*60*60, _list);
		}catch(Exception e){
			logger.info("系统组织机构数据初始化失败!");
			e.printStackTrace();
		}
	}
}
