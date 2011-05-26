package com.ivyinfo.note.services;

import java.util.List;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.note.bean.NoteBean;
import com.ivyinfo.note.dao.NoteDAO;

public class NoteServicesImpl implements NoteServices{
	
	public List getListNote(String logname,String folderId) throws Exception{
		NoteDAO noteDAO =(NoteDAO) SpringContextUtil.getBean("noteDAO");
		return noteDAO.getListNote(logname,folderId);
	}
	
	public List getNoteById(String logname,String id) throws Exception{
		NoteDAO noteDAO =(NoteDAO) SpringContextUtil.getBean("noteDAO");
		return noteDAO.getNoteById(logname,id);
	}
	
	public void saveNote(NoteBean noteBean) throws Exception{
		NoteDAO noteDAO =(NoteDAO) SpringContextUtil.getBean("noteDAO");
		noteDAO.saveNote(noteBean);
	}
	
	public void updateNote(NoteBean noteBean) throws Exception{
		NoteDAO noteDAO =(NoteDAO) SpringContextUtil.getBean("noteDAO");
		noteDAO.updateNote(noteBean);
	}
	
	public void deleteNote(String logname,String id) throws Exception{
		NoteDAO noteDAO =(NoteDAO) SpringContextUtil.getBean("noteDAO");
		noteDAO.deleteNote(logname,id);
	}
}
