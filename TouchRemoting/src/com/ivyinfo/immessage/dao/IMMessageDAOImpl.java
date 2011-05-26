package com.ivyinfo.immessage.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.cassandra.services.DaoServices;
import com.ivyinfo.communication.bean.CommunicationBean;
import com.ivyinfo.communication.dao.Communication;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.immessage.bean.IMMessageBean;
import com.ivyinfo.immessage.util.IMMessageMap;

public class IMMessageDAOImpl implements IMMessageDAO{
	
	public void AddIMMessage(IMMessageBean imMessageBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "immessage";//supercf IM聊天  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = imMessageBean.getSavename();
		
		IMMessageMap imMessageMap = new IMMessageMap();
		Map<String, String> columnNames = imMessageMap.BeanToMap(imMessageBean);
		
		daoServices.insertSuper(key, StringSerializer.get(), imMessageBean.getId(), columnNames, keyspace, supercf);
	}
	
	public void UpdIMMessage(IMMessageBean imMessageBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "immessage";//supercf IM聊天  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = imMessageBean.getSavename();
		
		IMMessageMap imMessageMap = new IMMessageMap();
		Map<String, String> columnNames = imMessageMap.BeanToMap(imMessageBean);
		
		daoServices.insertSuper(key, StringSerializer.get(), imMessageBean.getId(), columnNames, keyspace, supercf);
	}
	
	public List AllIMMessage(String logname,String id) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		List list = new ArrayList();
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "immessage";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		String[] keys = {""+logname+""};
		String supercs = id;
		Map m = daoServices.getSuperColumnValue(keys, keyspace, supercf, supercs, "", 1000);
		IMMessageMap imMessageMap = new IMMessageMap();
		for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
			Map.Entry e = (Map.Entry) it.next();
			String kk = (String) e.getKey();
			Map mm = (Map) e.getValue();
			
			IMMessageBean imMessageBean = imMessageMap.MapToBean(mm);
			imMessageBean.setId(kk);
			
			list.add(imMessageBean);
		}
		return list;
	}
	
	public List AllIMMessage_ReadORType(String logname,String fieldname,String value) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		List list = new ArrayList();
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "immessage";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		String[] keys = {""+logname+""};
		String supercs = "";
		Map m = daoServices.getSuperColumnValue_IMMessage_ReadORType(keys, keyspace, supercf, supercs, fieldname, value, 1000);
		IMMessageMap imMessageMap = new IMMessageMap();
		for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
			Map.Entry e = (Map.Entry) it.next();
			String kk = (String) e.getKey();
			Map mm = (Map) e.getValue();
			
			IMMessageBean imMessageBean = imMessageMap.MapToBean(mm);
			imMessageBean.setId(kk);
			
			list.add(imMessageBean);
		}
		return list;
	}
	
	public List AllIMMessage_ReadAndType(String logname,String typevalue,String readvalue) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		List list = new ArrayList();
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "immessage";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		String[] keys = {""+logname+""};
		String supercs = "";
		Map m = daoServices.getSuperColumnValue_IMMessage_ReadAndType(keys, keyspace, supercf, supercs, 
				"messagetype", typevalue, "readflag", readvalue, 1000);
		IMMessageMap imMessageMap = new IMMessageMap();
		for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
			Map.Entry e = (Map.Entry) it.next();
			String kk = (String) e.getKey();
			Map mm = (Map) e.getValue();
			
			IMMessageBean imMessageBean = imMessageMap.MapToBean(mm);
			imMessageBean.setId(kk);
			
			list.add(imMessageBean);
		}
		return list;
	}
}
