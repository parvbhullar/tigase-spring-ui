package com.ivyinfo.communication.dao;

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
import com.ivyinfo.framework.service.server.SpringContextUtil;

public class CommunicationIMDAOImpl extends SqlMapClientDaoSupport implements CommunicationIMDAO{
	
	public List AllIndexIM(String logname) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		List list = new ArrayList();
		
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "addressbookgroup";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		String[] keys = {""+logname+""};
		String supercs = "";
		Map m = daoServices.getSuperColumnValue(keys, keyspace, supercf, supercs, "", 1000);
		for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
			Map.Entry e = (Map.Entry) it.next();
			String kk = (String) e.getKey();
			Map mm = (Map) e.getValue();
			CommunicationBean comBean = new CommunicationBean();
			comBean.setImgid(kk);
			comBean.setImgroupname(String.valueOf(mm.get(Communication.Groupname)));
			comBean.setImsuperiorgroupid(String.valueOf(mm.get(Communication.Superiorgroupid)));
			comBean.setImremark(String.valueOf(mm.get(Communication.Gremark)));
			
			list.add(comBean);
		}
		return list;
	}
	
	public void AddSubmitIM(CommunicationBean communicationBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "addressbookgroup";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = communicationBean.getLogname();
		
		Map columnNames = new HashMap();
		columnNames.put(Communication.Groupname, communicationBean.getImgroupname());
		columnNames.put(Communication.Superiorgroupid, communicationBean.getImsuperiorgroupid());
		columnNames.put(Communication.Gremark, communicationBean.getImremark());
		
		daoServices.insertSuper(key, StringSerializer.get(), communicationBean.getImgroupname(), columnNames, keyspace, supercf);
	}
	
	public CommunicationBean ViewIM(int id,String logname) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		map.put("logname", logname);
		return (CommunicationBean) this.getSqlMapClientTemplate().queryForObject("findAllCommunicationIM", map);
	}
	
	public void UpdSubmitIM(CommunicationBean communicationBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "addressbookgroup";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = communicationBean.getLogname();
		
		Map columnNames = new HashMap();
		columnNames.put(Communication.Groupname, communicationBean.getImgroupname());
		columnNames.put(Communication.Superiorgroupid, communicationBean.getImsuperiorgroupid());
		columnNames.put(Communication.Gremark, communicationBean.getImremark());
		
		daoServices.insertSuper(key, StringSerializer.get(), communicationBean.getImgroupname(), columnNames, keyspace, supercf);
	}
	
	public void DelIM(String id,String logname) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "addressbookgroup";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = logname;
		
		daoServices.removeSuperColumn(key, id, null, keyspace, supercf);
	}
	
	public void CommunicationIMTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("CommunicationIMTable",logname);
	}
	
	public void DropCommunicationIMTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("DropCommunicationIMTable",logname);
	}
}
