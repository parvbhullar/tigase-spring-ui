package com.ivyinfo.note.services;

import java.util.List;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.note.bean.NoteFolderBean;
import com.ivyinfo.note.dao.NoteFolderDAO;

public class NoteFolderServicesImpl implements NoteFolderServices{
	
	public List getFolders(String logname,String id) throws Exception{
		NoteFolderDAO noteFolderDAO =(NoteFolderDAO) SpringContextUtil.getBean("noteFolderDAO");
		return noteFolderDAO.getFolders(logname,id);
	}
	
	public void saveFolder(NoteFolderBean noteFolderBean) throws Exception{
		NoteFolderDAO noteFolderDAO =(NoteFolderDAO) SpringContextUtil.getBean("noteFolderDAO");
		noteFolderDAO.saveFolder(noteFolderBean);
	}
	
	public void updateFolder(NoteFolderBean noteFolderBean) throws Exception{
		NoteFolderDAO noteFolderDAO =(NoteFolderDAO) SpringContextUtil.getBean("noteFolderDAO");
		noteFolderDAO.updateFolder(noteFolderBean);
	}
	
	public void deleteFolder(String logname,String id) throws Exception{
		NoteFolderDAO noteFolderDAO =(NoteFolderDAO) SpringContextUtil.getBean("noteFolderDAO");
		noteFolderDAO.deleteFolder(logname,id);
	}
}
