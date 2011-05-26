package com.ivyinfo.imcontactsnode.dao;

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
import com.ivyinfo.imcontactsnode.bean.IMContactsNodeBean;

public class IMContactsNodeDAOImpl implements IMContactsNodeDAO{
	
	public void AddIMContactsNode(IMContactsNodeBean imcontactsNodeBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "imcontactsnode";//supercf IM通讯录节点  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = imcontactsNodeBean.getLogname();
		
		Map<String, String> columnNames = new HashMap<String, String>();
		columnNames.put(IMContactsNode.Nid, imcontactsNodeBean.getNid());
		columnNames.put(IMContactsNode.Uid, imcontactsNodeBean.getUid());
		columnNames.put(IMContactsNode.Parent_nid, imcontactsNodeBean.getParent_nid());
		columnNames.put(IMContactsNode.Node, imcontactsNodeBean.getNode());
		
		daoServices.insertSuper(key, StringSerializer.get(), imcontactsNodeBean.getId(), columnNames, keyspace, supercf);
	}
	
	public List getListIMContactsNode_PidORNode(IMContactsNodeBean imcontactsNodeBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		List list = new ArrayList();
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "imcontactsnode";//supercf通讯录节点  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		String[] keys = {""+imcontactsNodeBean.getLogname()+""};
		String supercs = "";
		Map m = daoServices.getSuperColumnValue_IMContactsNode_PidORNode(keys, keyspace, supercf, supercs, 
				"parent_nid", imcontactsNodeBean.getParent_nid(), "node", imcontactsNodeBean.getNode(), 1000);
		for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
			Map.Entry e = (Map.Entry) it.next();
			String kk = (String) e.getKey();
			Map mm = (Map) e.getValue();
			
			IMContactsNodeBean imContactsNodeBean = new IMContactsNodeBean();
			
			imContactsNodeBean.setId(kk);
			imContactsNodeBean.setNid(String.valueOf(mm.get(IMContactsNode.Nid)));
			imContactsNodeBean.setUid(String.valueOf(mm.get(IMContactsNode.Uid)));
			imContactsNodeBean.setParent_nid(String.valueOf(mm.get(IMContactsNode.Parent_nid)));
			imContactsNodeBean.setNode(String.valueOf(mm.get(IMContactsNode.Node)));
			imContactsNodeBean.setLogname(imcontactsNodeBean.getLogname());
			
			list.add(imContactsNodeBean);
		}
		return list;
	}
	
	public List getListIMContactsNode_NidORUid(IMContactsNodeBean imcontactsNodeBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		List list = new ArrayList();
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "imcontactsnode";//supercf通讯录节点  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		String[] keys = {""+imcontactsNodeBean.getLogname()+""};
		String supercs = "";
		Map m = daoServices.getSuperColumnValue_IMContactsNode_NidORUid(keys, keyspace, supercf, supercs, 
				"nid", imcontactsNodeBean.getNid(), "uid", imcontactsNodeBean.getUid(), 1000);
		for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
			Map.Entry e = (Map.Entry) it.next();
			String kk = (String) e.getKey();
			Map mm = (Map) e.getValue();
			
			IMContactsNodeBean imContactsNodeBean = new IMContactsNodeBean();
			
			imContactsNodeBean.setId(kk);
			imContactsNodeBean.setNid(String.valueOf(mm.get(IMContactsNode.Nid)));
			imContactsNodeBean.setUid(String.valueOf(mm.get(IMContactsNode.Uid)));
			imContactsNodeBean.setParent_nid(String.valueOf(mm.get(IMContactsNode.Parent_nid)));
			imContactsNodeBean.setNode(String.valueOf(mm.get(IMContactsNode.Node)));
			imContactsNodeBean.setLogname(imcontactsNodeBean.getLogname());
			
			list.add(imContactsNodeBean);
		}
		return list;
	}
	
	public void DelIMContactsNode(String id,String logname) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "imcontactsnode";//supercf IM通讯录节点  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = logname;
		
		daoServices.removeSuperColumn(key, id, null, keyspace, supercf);
	}
}
