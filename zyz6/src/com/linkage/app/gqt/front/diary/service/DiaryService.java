package com.linkage.app.gqt.front.diary.service;

import java.util.List;

import com.linkage.app.gqt.front.diary.dao.DiaryDao;
import com.linkage.app.gqt.front.diary.entitys.Diary;

public class DiaryService {
	private DiaryDao diaryDao;
	
	public DiaryDao getDiaryDao() {
		return diaryDao;
	}
	public void setDiaryDao(DiaryDao diaryDao) {
		this.diaryDao = diaryDao;
	}
	
	/**
	 * 显示日志列表
	 * @return
	 */
	public List<Diary> showDiary()
	{
		return this.diaryDao.showDiary();
	}
	
	/**
	 * 显示日志列表
	 * @return
	 */
	public List<Diary> showDiaryCount()
	{
		return this.diaryDao.showDiary();
	}
	
	/**
	 * 显示日志回复列表
	 * @return
	 */
	public List<Diary> showReplyDiary(long srcrecid)
	{
		return this.diaryDao.showReplyDiary(srcrecid);
	}
	
	
	
	/**
	 * 保存日志
	 * @param diary
	 * @return
	 */
	public int saveDiary(Diary diary)
	{
		return this.diaryDao.saveDiary(diary);
	
	
	}
	
	/**
	 * 审核日志
	 * @param diary
	 * @return
	 */
	public int auditDiary(Diary diary)
	{
		return this.diaryDao.auditDiary(diary);
	}
	
	/**
	 * 显示志愿者日志
	 * @param recid
	 * @return
	 */
	public Diary getDiary(long recid)
	{
		return this.diaryDao.getDiary(recid);
	}
}
