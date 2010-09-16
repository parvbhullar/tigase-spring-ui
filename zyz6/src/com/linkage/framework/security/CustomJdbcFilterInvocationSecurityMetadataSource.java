package com.linkage.framework.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;

import com.linkage.app.gqt.purview.dao.PurviesDao;
import com.linkage.app.gqt.purview.entity.Resource;

public class CustomJdbcFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{

	final Logger logger = LoggerFactory.getLogger(CustomJdbcFilterInvocationSecurityMetadataSource.class);
	
	private PurviesDao purviesDao;
	private static Map<String,Collection<ConfigAttribute>> resourceMap=null;
	private UrlMatcher urlMatcher=new AntUrlPathMatcher();
	
	public PurviesDao getPurviesDao() {
		return purviesDao;
	}

	public void setPurviesDao(PurviesDao purviesDao) {
		this.purviesDao = purviesDao;
	}

	public CustomJdbcFilterInvocationSecurityMetadataSource(){
		//loadResourceDefine();
	}
	
	private void loadResourceDefine(){
		logger.info("开始加载资源权限......");
		resourceMap=new HashMap<String,Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> attrs=null;
		List<Resource> resourceList=purviesDao.selectResources();
		for(Resource resource:resourceList){
			String url = resource.getString();
            String role = resource.getRoleName();
            ConfigAttribute ca=new SecurityConfig(role);
            if (resourceMap.containsKey(url)) {
            	Collection<ConfigAttribute> atts = resourceMap.get(url);
            	atts.add(ca);
                resourceMap.put(url, atts);
            } else {
            	attrs=new ArrayList<ConfigAttribute>();
            	attrs.add(ca);
                resourceMap.put(url, attrs);
            }
		}
		logger.info("资源权限加载结束!");
		
		logger.info("资源权限信息:");
		Iterator<String> iterator=resourceMap.keySet().iterator();
		while(iterator.hasNext()){
			String url=(String)iterator.next();
			Collection<ConfigAttribute> _attrs=resourceMap.get(url);
			logger.info("资源名称="+url+">>>角色名称="+_attrs.toString());
		}
	}
	
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if(resourceMap==null){
			loadResourceDefine();
		}
		String requestUrl=((FilterInvocation)object).getRequestUrl();
		logger.info("开始验证资源[{}]的权限!",requestUrl);
		Iterator<String> iterator=resourceMap.keySet().iterator();
		while(iterator.hasNext()){
			String url=(String)iterator.next();
			if(urlMatcher.pathMatchesUrl(requestUrl, url)){
				logger.info("资源[{}]的权限:[{}]=",new Object[]{url,resourceMap.get(url).toString()});
				return resourceMap.get(url);
			}
		}
		logger.info("资源[{}]的权限:[{NULL}]=",new Object[]{requestUrl});
		return null;
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}
}
