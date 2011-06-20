package org.eredlab.g4.arm.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

/**
 * 角色管理与授权业务接口
 * @author XiongChun
 * @since 2010-01-13
 */
public interface RoleService extends BaseService{
	
	/**
	 * 保存角色
	 * @param pDto
	 * @return
	 */
	public Dto saveRoleItem(Dto pDto);
	
	/**
	 * 删除角色
	 * @param pDto
	 * @return
	 */
	public Dto deleteRoleItems(Dto pDto);
	
	/**
	 * 修改角色
	 * @param pDto
	 * @return
	 */
	public Dto updateRoleItem(Dto pDto);
	
	/**
	 * 保存角色授权信息
	 * @param pDto
	 * @return
	 */
	public Dto saveGrant(Dto pDto);
	
	/**
	 * 保存角色用户关联信息
	 * @param pDto
	 * @return
	 */
	public Dto saveSelectUser(Dto pDto);
}
