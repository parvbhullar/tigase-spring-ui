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
import com.ivyinfo.note.bean.NoteBean;

public class NoteDAOImpl implements NoteDAO{
	
	public List getListNote(String logname,String folderId) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		List list = new ArrayList();
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "note";//supercf笔记文件夹  库名
		
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		String[] keys = {""+logname+""};
		String supercs = "";
		Map m = daoServices.getSuperColumnValue_Note_FolderId(keys, keyspace, supercf, supercs, "folderId", folderId, 1000);
		for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
			Map.Entry e = (Map.Entry) it.next();
			String kk = (String) e.getKey();
			Map mm = (Map) e.getValue();
			NoteBean noteBean = new NoteBean();
			noteBean.setId(kk);
			noteBean.setUsername(String.valueOf(mm.get(Note.Username)));
			noteBean.setFolderId(String.valueOf(mm.get(Note.FolderId)));
			noteBean.setNoteContent(String.valueOf(mm.get(Note.NoteContent)));
			noteBean.setPosLeft(Integer.parseInt(String.valueOf(mm.get(Note.PosLeft))));
			noteBean.setPosTop(Integer.parseInt(String.valueOf(mm.get(Note.PosTop))));
			noteBean.setPosHeight(Integer.parseInt(String.valueOf(mm.get(Note.PosHeight))));
			noteBean.setPosWidth(Integer.parseInt(String.valueOf(mm.get(Note.PosWidth))));
			noteBean.setNoteColor(String.valueOf(mm.get(Note.NoteColor)));
			noteBean.setNoteBarColor(String.valueOf(mm.get(Note.NoteBarColor)));
			noteBean.setNoteBorderColor(String.valueOf(mm.get(Note.NoteBorderColor)));
			noteBean.setNoteDate(String.valueOf(mm.get(Note.NoteDate)));
			
			list.add(noteBean);
		}
		return list;
	}
	
	public List getNoteById(String logname,String id) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		List list = new ArrayList();
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "note";//supercf笔记文件夹  库名
		
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
			NoteBean noteBean = new NoteBean();
			noteBean.setId(kk);
			noteBean.setUsername(String.valueOf(mm.get(Note.Username)));
			noteBean.setFolderId(String.valueOf(mm.get(Note.FolderId)));
			noteBean.setNoteContent(String.valueOf(mm.get(Note.NoteContent)));
			noteBean.setPosLeft(Integer.parseInt(String.valueOf(mm.get(Note.PosLeft))));
			noteBean.setPosTop(Integer.parseInt(String.valueOf(mm.get(Note.PosTop))));
			noteBean.setPosHeight(Integer.parseInt(String.valueOf(mm.get(Note.PosHeight))));
			noteBean.setPosWidth(Integer.parseInt(String.valueOf(mm.get(Note.PosWidth))));
			noteBean.setNoteColor(String.valueOf(mm.get(Note.NoteColor)));
			noteBean.setNoteBarColor(String.valueOf(mm.get(Note.NoteBarColor)));
			noteBean.setNoteBorderColor(String.valueOf(mm.get(Note.NoteBorderColor)));
			noteBean.setNoteDate(String.valueOf(mm.get(Note.NoteDate)));
			
			list.add(noteBean);
		}
		return list;
	}
	
	public void saveNote(NoteBean noteBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "note";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = noteBean.getLogname();
		
		Map columnNames = new HashMap();
		columnNames.put(Note.Username, noteBean.getUsername());
		columnNames.put(Note.FolderId, noteBean.getFolderId());
		columnNames.put(Note.NoteContent, noteBean.getNoteContent());
		columnNames.put(Note.PosLeft, noteBean.getPosLeft());
		columnNames.put(Note.PosTop, noteBean.getPosTop());
		columnNames.put(Note.PosHeight, noteBean.getPosHeight());
		columnNames.put(Note.PosWidth, noteBean.getPosWidth());
		columnNames.put(Note.NoteColor, noteBean.getNoteColor());
		columnNames.put(Note.NoteBarColor, noteBean.getNoteBarColor());
		columnNames.put(Note.NoteBorderColor, noteBean.getNoteBorderColor());
		columnNames.put(Note.NoteDate, noteBean.getNoteDate());
		
		daoServices.insertSuper(key, StringSerializer.get(), noteBean.getId(), columnNames, keyspace, supercf);
	}
	
	public void updateNote(NoteBean noteBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "note";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = noteBean.getLogname();
		
		Map columnNames = new HashMap();
		columnNames.put(Note.Username, noteBean.getUsername());
		columnNames.put(Note.FolderId, noteBean.getFolderId());
		columnNames.put(Note.NoteContent, noteBean.getNoteContent());
		columnNames.put(Note.PosLeft, noteBean.getPosLeft());
		columnNames.put(Note.PosTop, noteBean.getPosTop());
		columnNames.put(Note.PosHeight, noteBean.getPosHeight());
		columnNames.put(Note.PosWidth, noteBean.getPosWidth());
		columnNames.put(Note.NoteColor, noteBean.getNoteColor());
		columnNames.put(Note.NoteBarColor, noteBean.getNoteBarColor());
		columnNames.put(Note.NoteBorderColor, noteBean.getNoteBorderColor());
		columnNames.put(Note.NoteDate, noteBean.getNoteDate());
		
		daoServices.insertSuper(key, StringSerializer.get(), noteBean.getId(), columnNames, keyspace, supercf);
	}
	
	public void deleteNote(String logname,String id) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "note";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = logname;
		
		daoServices.removeSuperColumn(key, id, null, keyspace, supercf);
	}
}
