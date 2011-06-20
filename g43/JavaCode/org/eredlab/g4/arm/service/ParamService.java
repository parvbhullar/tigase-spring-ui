package org.eredlab.g4.arm.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

/*
 * 全局参数管理业务接口
 * @author XiongChun
 * @since 2010-05-13
 */
public interface ParamService extends BaseService{

	/**
	 * 保存参数信息表
	 */
	public Dto saveParamItem(Dto pDto);

	/**
	 * 删除参数信息
	 * 
	 * @param pDto
	 */
	public Dto deleteParamItem(Dto pDto);

	/**
	 * 修改参数信息
	 * 
	 * @param pDto
	 */
	public Dto updateParamItem(Dto pDto);
}
