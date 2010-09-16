package com.linkage.app.gqt.front.diary.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkage.app.gqt.front.diary.entitys.Diary;
import com.linkage.app.gqt.front.diary.entitys.DiaryRowMapper;
import com.linkage.app.gqt.webpreferential.coupons.entitys.CouponRowMapperWithShopPic;
import com.linkage.framework.jdbc.BaseJdbcDaoImpl;

public class DiaryDao extends BaseJdbcDaoImpl{
	final Logger logger = LoggerFactory.getLogger(DiaryDao.class);
	
	/**
	 * 显示日志(审核通过)
	 * @return
	 */
	public List<Diary> showDiary()
	{
		String sql="select t.* from zyz_diary t where t.isverify=2 and t.srcrecid=0 order by sendtime desc";
		logger.info(sql);
		List<Diary> list=(List<Diary>) this.getJdbcTemplate().query(sql, new DiaryRowMapper());
		return list;
	}
	
	/**
	 * 显示日志回复列表(审核通过)
	 * @return
	 */
	public List<Diary> showReplyDiary(long srcrecid)
	{
		Object[] o=new Object[]{srcrecid};
		String sql="select t.* from zyz_diary t where t.isverify=2 and t.srcrecid=? order by sendtime desc";
		logger.info(sql);
		List<Diary> list=(List<Diary>) this.getJdbcTemplate().query(sql,o, new DiaryRowMapper());
		return list;
	}
	
	/**
	 *  保存日志
	 * @param diary
	 * @return
	 */
	public int saveDiary(Diary diary)
	{
		return this.getJdbcTemplate().update("INSERT INTO ZYZ_DIARY (RECID, TITLE, CONTENT,SRCRECID,SENDTIME) VALUES ( SEQ_ZYZ_COMMON.nextval,? ,?,?,sysdate)",diary.getTitle(), diary.getContent(),diary.getSrcrecid());
	}
	
	/**
	 *  审核日志
	 * @param diary
	 * @return
	 */
	public int auditDiary(Diary diary)
	{
		Object[] o=new Object[]{diary.getIsverify(),diary.getRecid()};
		String sql="UPDATE zyz_diary SET isverify=? WHERE recid = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	/**
	 * 根据ID获取日志
	 * @param recid
	 * @return
	 */
	public Diary getDiary(long recid)
	{
		Object[] o=new Object[]{recid};
		String sql="select * from zyz_diary t where t.recid=?";
		logger.debug(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().queryForObject(sql,o,new DiaryRowMapper());
	}
}
