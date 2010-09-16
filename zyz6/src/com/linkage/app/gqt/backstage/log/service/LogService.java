package com.linkage.app.gqt.backstage.log.service;

import java.util.List;
import java.util.Map;

import com.linkage.app.gqt.backstage.log.dao.LogDao;
import com.linkage.app.gqt.backstage.log.entity.ZyzLog;
/**
 * 日志管理service类
 *
 *
 * @author jiale.wang
 *
 * @create on 2010-8-14 上午11:04:27
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */
public class LogService {
	private LogDao logDao;

	public LogDao getLogDao() {
		return logDao;
	}

	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}
	//插入数据
	public int insert(long tid,long uid,String ip)
	{
		return this.logDao.insert(tid, uid, ip);
	}
	//条件查询返回记录总条数
	public int getLogTotal(Map args){
		return this.logDao.getPostulantTotal(args);
	}
	//条件查询
	public List<ZyzLog> getLogList(Map args)
	{
		return this.logDao.getPostlantList(args);
	}
//	/**
//	 * 短信下发接口
//	 * @param dn 手机号
//	 * @param message 信息
//	 * @return
//	 */
	public int insertMt(String srcId, String dn,String message,Map sysparamMap){
		return this.logDao.insertMt(srcId, dn, message, sysparamMap);
	}
}
