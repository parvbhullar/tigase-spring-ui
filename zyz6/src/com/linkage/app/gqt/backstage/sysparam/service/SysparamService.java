package com.linkage.app.gqt.backstage.sysparam.service;

import java.util.List;
import java.util.Map;

import com.linkage.app.gqt.backstage.sysparam.dao.SysparamDao;
import com.linkage.app.gqt.backstage.sysparam.entity.Sysparam;
import com.linkage.framework.page.CouponPageUtil;

public class SysparamService {
	private SysparamDao sysparamDao;

	public SysparamDao getSysparamDao() {
		return sysparamDao;
	}

	public void setSysparamDao(SysparamDao sysparamDao) {
		this.sysparamDao = sysparamDao;
	}

	/**
	 * 显示系统参数列表
	 * @return
	 */
	public List<Sysparam> showSysparamByParamcode(CouponPageUtil pageUtil,String paramcode)
	{
		return sysparamDao.showSysparamByParamcode(pageUtil,paramcode);
	}
	
	/**
	 * 显示系统参数列表
	 * @return
	 */
	public List<Sysparam> showSysparam(CouponPageUtil pageUtil)
	{
		return sysparamDao.showSysparam(pageUtil);
	}
	/**
	 * 显示系统技能参数列表
	 * @return
	 */
	public List<Sysparam> showJnSysparam(Map args){
		return this.sysparamDao.showJnSysparam(args);
	}
	
	public int insertSysparam(Sysparam sysParam)
	{
		return sysparamDao.insertSysparam(sysParam);
	}
	//插入系统参数
	public int insertJnSysparam(Sysparam sysParam){
		return sysparamDao.insertJnSysparam(sysParam);
	}
	
	/**
	 * 审核状态
	 * @param sysParam
	 * @return
	 */
	public int audit(Sysparam sysParam)
	{
		return sysparamDao.auditSysparam(sysParam);
	}
	
	/**
	 * 审核状态
	 * @param sysParam
	 * @return
	 */
	public int delete(Sysparam sysParam)
	{
		return sysparamDao.deleteSysparam(sysParam);
	}
	
	/**
	 * 编辑系统参数
	 * @param sysParam
	 * @return
	 */
	public Sysparam edit(Sysparam sysParam)
	{
		return sysparamDao.edit(sysParam);
	}
	/**
	 * 更新系统参数
	 * @param sysParam
	 * @return
	 */
	public int updateSysparam(Sysparam sysParam){
		return this.sysparamDao.updateSysparam(sysParam);
	}
	/**
	 * 验证value是否重复
	 * @param paramValue
	 * @return
	 */
	public int validateSysparamValue(String paramValue){
		return this.sysparamDao.validateSysparamValue(paramValue);
	}
	
}
