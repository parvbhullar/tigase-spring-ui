package com.ivyinfo.note.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;

import com.ivyinfo.addressbook.dao.AddressBook;
import com.ivyinfo.cassandra.services.DaoServices;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.note.bean.NoteFolderBean;

public class NoteFolderDAOImpl implements NoteFolderDAO{
	
	public List getFolders(String logname,String id) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		List list = new ArrayList();
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "notefolder";//supercf笔记文件夹  库名
		
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		String[] keys = {""+logname+""};
		String supercs = id;
		Map m = daoServices.getSuperColumnValue(keys, keyspace, supercf, supercs, supercs, 1000);
		for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
			Map.Entry e = (Map.Entry) it.next();
			String kk = (String) e.getKey();
			Map mm = (Map) e.getValue();
			NoteFolderBean noteFolderBean = new NoteFolderBean();
			noteFolderBean.setId(kk);
			noteFolderBean.setUsername(String.valueOf(mm.get(NoteFolder.Username)));
			noteFolderBean.setFolderName(String.valueOf(mm.get(NoteFolder.FolderName)));
			
			list.add(noteFolderBean);
		}
		return list;
	}
	
	public void saveFolder(NoteFolderBean noteFolderBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "notefolder";//supercf笔记文件夹  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = noteFolderBean.getUsername();
		
		Map columnNames = new HashMap();
		columnNames.put(NoteFolder.Username, noteFolderBean.getUsername());
		columnNames.put(NoteFolder.FolderName, noteFolderBean.getFolderName());
		
		daoServices.insertSuper(key, StringSerializer.get(), noteFolderBean.getId(), columnNames, keyspace, supercf);
	}
	
	public void updateFolder(NoteFolderBean noteFolderBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "notefolder";//supercf笔记文件夹  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = noteFolderBean.getUsername();
		
		Map columnNames = new HashMap();
		columnNames.put(NoteFolder.Username, noteFolderBean.getUsername());
		columnNames.put(NoteFolder.FolderName, noteFolderBean.getFolderName());
		
		daoServices.insertSuper(key, StringSerializer.get(), noteFolderBean.getId(), columnNames, keyspace, supercf);
	}
	
	public void deleteFolder(String logname,String id) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "notefolder";//supercf笔记文件夹  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = logname;
		
		daoServices.removeSuperColumn(key, id, null, keyspace, supercf);
	}
}
