package com.ivyinfo.note.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class NoteBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684294L;
	
	private String id;
	private String username;
	private String folderId;
	private String noteContent;
	private Integer posLeft;
	private Integer posTop;
	private Integer posHeight;
	private Integer posWidth;
	private String noteColor;
	private String noteBarColor;
	private String noteBorderColor;
	private String noteDate;
	private String logname;
	
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFolderId() {
		return folderId;
	}
	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
	public Integer getPosLeft() {
		return posLeft;
	}
	public void setPosLeft(Integer posLeft) {
		this.posLeft = posLeft;
	}
	public Integer getPosTop() {
		return posTop;
	}
	public void setPosTop(Integer posTop) {
		this.posTop = posTop;
	}
	public Integer getPosHeight() {
		return posHeight;
	}
	public void setPosHeight(Integer posHeight) {
		this.posHeight = posHeight;
	}
	public Integer getPosWidth() {
		return posWidth;
	}
	public void setPosWidth(Integer posWidth) {
		this.posWidth = posWidth;
	}
	public String getNoteColor() {
		return noteColor;
	}
	public void setNoteColor(String noteColor) {
		this.noteColor = noteColor;
	}
	public String getNoteBarColor() {
		return noteBarColor;
	}
	public void setNoteBarColor(String noteBarColor) {
		this.noteBarColor = noteBarColor;
	}
	public String getNoteBorderColor() {
		return noteBorderColor;
	}
	public void setNoteBorderColor(String noteBorderColor) {
		this.noteBorderColor = noteBorderColor;
	}
	public String getNoteDate() {
		return noteDate;
	}
	public void setNoteDate(String noteDate) {
		this.noteDate = noteDate;
	}
}
