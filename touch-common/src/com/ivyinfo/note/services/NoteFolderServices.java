package com.ivyinfo.note.services;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.note.bean.NoteFolderBean;

public interface NoteFolderServices extends IBaseService{
	public List getFolders(String logname,String id) throws Exception;
	
	public void saveFolder(NoteFolderBean noteFolderBean) throws Exception;
	
	public void updateFolder(NoteFolderBean noteFolderBean) throws Exception;
	
	public void deleteFolder(String logname,String id) throws Exception;
}
