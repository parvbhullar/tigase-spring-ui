package com.ivyinfo.note.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.note.bean.NoteBean;

public interface NoteDAO extends IBaseDAO{
	public List getListNote(String logname,String folderId) throws Exception;
	
	public List getNoteById(String logname,String id) throws Exception;
	
	public void saveNote(NoteBean noteBean) throws Exception;
	
	public void updateNote(NoteBean noteBean) throws Exception;
	
	public void deleteNote(String logname,String id) throws Exception;
}
