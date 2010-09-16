package com.linkage.framework.cache;

import java.lang.reflect.Method;

import net.spy.memcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CacheKey {
	
	static Logger logger = LoggerFactory.getLogger(CacheKey.class);
	
	public CacheKey(){}
	
	public final static String ZYZ_SYSPARAM="zyz_sysparam";
	public final static String ZYZ_ORGTYPE="zyz_orgtype";
	public final static String ZYZ_ORGAREA="zyz_orgarea";
	public final static String ZYZ_ORG="zyz_org";
	public final static String ZYZ_NT_FWYX="zyz_nt_fwyx";
	public final static String ZYZ_MANAGE_ORG="zyz_manage_org";
	
	
	public static Object get(MemcachedClient memcachedClient,String key){
		Object result=null;
		if(memcachedClient==null){
			logger.info("缓存对象[{}]不存在,!","memcachedClient");
		}else{
			try{
				result=memcachedClient.get(key);
			}catch(Exception e){
				logger.info("针对{}的get缓存操作失败",key);
			}
		}
		return result;
	}
	
	public static Object getAndAdd(MemcachedClient memcachedClient,int expTime,String key,Object obj,String methodName, Object[] o){
		Object result=null;
		if(memcachedClient==null){
			logger.info("缓存对象[{}]不存在,使用反射获取对象!","memcachedClient");
			result=getObjectMust(obj,methodName,o);
		}else{
			try{
				result=memcachedClient.get(key);
				if(result==null){
					logger.info("缓存对象[{}]存在并且已经连接到缓存服务器,但是缓存中不存在KEY[{}]对应的对象,使用反射获取对象!",new Object[]{"memcachedClient",key});
					result=getObjectMust(obj,methodName,o);
					if(result!=null){
						logger.info("反射成功,缓存并返回对象!");
						memcachedClient.add(key, expTime, result);
					}else{
						logger.info("反射失败,返回空值!");
					}
				}else{
					logger.info("缓存对象[{}]存在并且已经连接到缓存服务器,同时缓存中存在KEY[{}]对应的对象,直接返回对象!",new Object[]{"memcachedClient",key});
				}
			}catch(net.spy.memcached.OperationTimeoutException operationTimeoutException){
				logger.info(operationTimeoutException.getMessage());
				logger.info("缓存对象[{}]存在,但是没有连接到缓存服务器,使用反射获取对象!",new Object[]{"memcachedClient"});
				result=getObjectMust(obj,methodName,o);
				if(result!=null){
					logger.info("反射成功,返回对象!");
					//memcachedClient.add(key, expTime, result);
				}else{
					logger.info("反射失败,返回空值!");
				}
			}
			
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	private static Object getObjectMust(Object obj,String methodName, Object[] o){
		Object result=null;
		Class c=obj.getClass();
		try{
			Method[] methodArray=c.getMethods();
			Method method=null;
			if(methodArray!=null){
				for(int i=0;i<methodArray.length;i++){
					if(methodArray[i].getName().equals(methodName)){
						method=methodArray[i];
						break;
					}
				}
			}
			if(method!=null){
				result= method.invoke(obj, o);
			}
		}catch(Exception ee){
			logger.info("反射失败,返回空值!");
			ee.printStackTrace();
		}
		return result;
	}
	
	public static boolean add(MemcachedClient memcachedClient,int exp,String key,Object o){
		if(memcachedClient==null){
			logger.info("缓存对象[{}]不存在,!","memcachedClient");
			return false;
		}else{
			try{
				memcachedClient.add(key, exp, o);
				return true;
			}catch(Exception e){
				logger.info("针对{}的add缓存操作失败",key);
				return false;
			}
		}
	}
	
	public static  boolean set(MemcachedClient memcachedClient,int exp,String key,Object o){
		if(memcachedClient==null){
			logger.info("缓存对象[{}]不存在,!","memcachedClient");
			return false;
		}else{
			try{
				memcachedClient.set(key, exp, o);
				return true;
			}catch(Exception e){
				logger.info("针对{}的set缓存操作失败",key);
				return false;
			}
		}
	}
	
	public static boolean delete(MemcachedClient memcachedClient,String key){
		if(memcachedClient==null){
			logger.info("缓存对象[{}]不存在,!","memcachedClient");
			return false;
		}else{
			try{
				memcachedClient.delete(key);
				return true;
			}catch(Exception e){
				logger.info("针对{}的set缓存操作失败",key);
				return false;
			}
		}
	}

}
