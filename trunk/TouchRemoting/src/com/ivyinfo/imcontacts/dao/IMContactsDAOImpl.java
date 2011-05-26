package com.ivyinfo.imcontacts.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;

import com.ivyinfo.cassandra.services.DaoServices;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.imcontacts.bean.IMContactsBean;

public class IMContactsDAOImpl implements IMContactsDAO{
	
	public void AddIMContacts(IMContactsBean imcontactsBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "imcontacts";//supercf IM通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = imcontactsBean.getLogname();
		
		Map<String, String> columnNames = new HashMap<String, String>();
		columnNames.put(IMContacts.Nid, imcontactsBean.getNid());
		columnNames.put(IMContacts.Uid, imcontactsBean.getUid());
		columnNames.put(IMContacts.Pkey, imcontactsBean.getPkey());
		columnNames.put(IMContacts.Pval, imcontactsBean.getPval());
		
		daoServices.insertSuper(key, StringSerializer.get(), imcontactsBean.getId(), columnNames, keyspace, supercf);
	}
	
	public void DelIMContacts(String id,String logname) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "imcontacts";//supercf IM通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = logname;
		
		daoServices.removeSuperColumn(key, id, null, keyspace, supercf);
	}
	
	public List getListIMContacts(IMContactsBean imcontactsBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		List list = new ArrayList();
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "imcontacts";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		String[] keys = {""+imcontactsBean.getLogname()+""};
		String supercs = "";
		Map m = daoServices.getSuperColumnValue_IMContacts_NidORPkeyORUid(keys, keyspace, supercf, supercs, 
				"nid", imcontactsBean.getNid(), "pkey", imcontactsBean.getPkey(), "uid", imcontactsBean.getUid(), 1000);
		for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
			Map.Entry e = (Map.Entry) it.next();
			String kk = (String) e.getKey();
			Map mm = (Map) e.getValue();
			
			IMContactsBean imContactsBean = new IMContactsBean();
			
			imContactsBean.setId(kk);
			imContactsBean.setNid(String.valueOf(mm.get(IMContacts.Nid)));
			imContactsBean.setUid(String.valueOf(mm.get(IMContacts.Uid)));
			imContactsBean.setPkey(String.valueOf(mm.get(IMContacts.Pkey)));
			imContactsBean.setPval(String.valueOf(mm.get(IMContacts.Pval)));
			imContactsBean.setLogname(imcontactsBean.getLogname());
			
			list.add(imContactsBean);
		}
		return list;
	}
}
