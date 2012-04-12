package com.xzb.oa.knowledge.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

/**
 * 知识结构模型业务接口
 * @since 2010-01-13
 */
public interface KService extends BaseService{
	
	
	
	/**
	 * 查询目录信息生成目录树
	 * @param pDto
	 * @return
	 */
	public Dto queryDirItems(Dto pDto);
	
	
	public Dto queryDirItems_(Dto pDto);
	/**
	 * 根据用户所属部门编号查询部门对象<br>
	 * 用于构造组织机构树的根节点
	 * @param
	 * @return
	 */
	public Dto queryDeptinfoByDeptid(Dto pDto);
	
	/**
	 * 保存用户主题信息
	 * @param pDto
	 */
	public Dto saveUserTheme(Dto pDto);
	
	/**
	 * 目录操作开始
	 */
	
	/**
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto queryDirCount(Dto pDto);
	/**
	 * 初始化根目录
	 * @param pDto
	 * @return
	 */
	public Dto initDir(Dto pDto);
	
	/**
	 * 保存目录
	 * @param pDto
	 * @return
	 */
	public Dto saveDirItem(Dto pDto);
	
	/**
	 * 修改目录
	 * @param pDto
	 * @return
	 */
	public Dto updateDirItem(Dto pDto);
	
	/**
	 * 删除目录
	 * @param pDto
	 * @return
	 */
	public Dto deleteDirItems(Dto pDto);
	
	/**
	 * 在后台创建文件夹
	 * @param pDto
	 * @return
	 */
	public Dto createDir (Dto pDto) ;
	
	/**
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto getDirItems(Dto pDto);
	
	/**
	 * 目录操作结束
	 */
	
	/**
	 * 文档操作方法开始
	 */
	
	/**
	 * 查询文档信息
	 * @param pDto
	 * @return
	 */
	public Dto queryDocItems(Dto pDto);
	
	/**
	 * 保存文档
	 * @param pDto
	 * @return
	 */
	public Dto saveDocItem(Dto pDto);
	
	/**
	 * 修改文档
	 * @param pDto
	 * @return
	 */
	public Dto updateDocItem(Dto pDto);
	
	/**
	 * 删除文档
	 * @param pDto
	 * @return
	 */
	public Dto deleteDocItems(Dto pDto);
	
	/**
	 * 根据用户所属文档编号查询文档对象<br>
	 * 用于构造组织机构树的根节点
	 * @param
	 * @return
	 */
	public Dto queryDocinfoByDocid(Dto pDto);
	
	/**
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto deleteAtt(Dto pDto);
	
	/**
	 * 文档操作方法结束
	 */
	
	
	/**
	 * 权限设置
	 * @param dto
	 * @return
	 */
	public Dto saveLimitis(Dto dto);
	/**
	 * 查询已经存在的权限
	 * @param dto
	 * @return
	 */
	public Dto queryLimitis(Dto dto);
	
	public Dto queryLimitis_(Dto dto);
	/**
	 * 更新目录共享类型
	 * @param dto
	 * @return
	 */
	public Dto updateShareType(Dto dto);
	/**
	 * 查询当前权限
	 * @param dto
	 * @return
	 */
	public Dto queryAllLimitis(Dto dto);
	/**
	 * 人员所在部门
	 * @param dto
	 * @return
	 */
	public Dto queryUserdep(Dto dto);
	/**
	 * 保存部门权限id
	 * @param dto
	 * @return
	 */
	public Dto saveLimitisDept(Dto dto);
	
}
