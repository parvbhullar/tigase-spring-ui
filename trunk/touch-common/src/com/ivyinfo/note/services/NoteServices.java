package com.ivyinfo.note.services;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.note.bean.NoteBean;

public interface NoteServices extends IBaseService{
	public List getListNote(String logname,String folderId) throws Exception;
	
	public List getNoteById(String logname,String id) throws Exception;
	
	public void saveNote(NoteBean noteBean) throws Exception;
	
	public void updateNote(NoteBean noteBean) throws Exception;
	
	public void deleteNote(String logname,String id) throws Exception;
}
