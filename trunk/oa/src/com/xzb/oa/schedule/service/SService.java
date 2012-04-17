package com.xzb.oa.schedule.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

/**
 * 知识结构模型业务接口
 * @since 2010-01-13
 */
public interface SService extends BaseService{
	
	/**
	 * 获取用户信息
	 * @param pDto
	 * @return
	 */
	public Dto getUserInfo(Dto pDto);
	
	/**
	 * 保存我的日程
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto saveScheItem(Dto pDto);

	/**
	 * 批量修改我的日程
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto deleteScheItems(Dto pDto);

	/**
	 * 修改我的日程
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto updateScheItem(Dto pDto);
	
	/**
	 * 改变共享关系
	 * @param dto
	 * @return
	 */
	public Dto updateShareType(Dto dto);
	
	/**
	 * 查询下级
	 * @param dto
	 * @return
	 */
	public Dto queryLower(Dto dto);
	
}
