package com.ivyinfo.note.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.note.bean.NoteFolderBean;

public interface NoteFolderDAO extends IBaseDAO{
	public List getFolders(String logname,String id) throws Exception;
	
	public void saveFolder(NoteFolderBean noteFolderBean) throws Exception;
	
	public void updateFolder(NoteFolderBean noteFolderBean) throws Exception;
	
	public void deleteFolder(String logname,String id) throws Exception;
}
