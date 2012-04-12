package org.eredlab.g4.arm.service.impl;

import java.util.List;

import org.eredlab.g4.arm.service.OrganizationService;
import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.arm.util.idgenerator.IdGenerator;
import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;

/**
 * 组织机构模型业务实现类
 * 
 * @author XiongChun
 * @since 2010-01-13
 */
public class OrganizationServiceImpl extends BaseServiceImpl implements OrganizationService {

	/**
	 * 获取用户信息
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto getUserInfo(Dto pDto) {
		Dto outDto = new BaseDto();
		pDto.put("lock", ArmConstants.LOCK_N);
		pDto.put("enabled", ArmConstants.ENABLED_Y);
		UserInfoVo userInfo = (UserInfoVo) g4Dao.queryForObject("Organization.getUserInfo", pDto);
		outDto.put("userInfo", userInfo);
		return outDto;
	}

	/**
	 * 查询部门信息生成部门树
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto queryDeptItems(Dto pDto) {
		Dto outDto = new BaseDto();
		List deptList = g4Dao.queryForList("Organization.queryDeptItemsByDto", pDto);
		Dto deptDto = new BaseDto();
		for (int i = 0; i < deptList.size(); i++) {
			deptDto = (BaseDto) deptList.get(i);
			if (deptDto.getAsString("leaf").equals(ArmConstants.LEAF_Y))
				deptDto.put("leaf", new Boolean(true));
			else
				deptDto.put("leaf", new Boolean(false));
			if (deptDto.getAsString("id").length() == 6)
				deptDto.put("expanded", new Boolean(true));
		}
		outDto.put("jsonString", JsonHelper.encodeObject2Json(deptList));
		return outDto;
	}

	
	public Dto queryDeptItems_(Dto pDto) {
		Dto outDto = new BaseDto();
		List deptList = g4Dao.queryForList("Organization.queryDeptItemsByDto", pDto);
		List userList=g4Dao.queryForList("Organization.queryUserItemsByDto", pDto);
		Dto deptDto = new BaseDto();
		Dto userDto = new BaseDto();
		for (int i = 0; i < deptList.size(); i++) {
			deptDto = (BaseDto) deptList.get(i);
			if (deptDto.getAsString("leaf").equals(ArmConstants.LEAF_Y))
				deptDto.put("leaf", new Boolean(false));
			else
				deptDto.put("leaf", new Boolean(false));
			if (deptDto.getAsString("id").length() == 6)
				deptDto.put("expanded", new Boolean(true));
		}
		for(int j=0;j<userList.size();j++)
		{
			userDto=(BaseDto)userList.get(j);
			userDto.put("leaf", true);
			deptList.add(userDto);
		}
		outDto.put("jsonString", JsonHelper.encodeObject2Json(deptList));
		return outDto;
	}
	/**
	 * 保存部门
	 * 
	 * @param pDto
	 * @return
	 */
	public synchronized Dto saveDeptItem(Dto pDto) {
		String deptid = IdGenerator.getDeptIdGenerator(pDto.getAsString("parentid"));
		pDto.put("deptid", deptid);
		pDto.put("leaf", ArmConstants.LEAF_Y);
		// MYSQL下int类型字段不能插入空字符
		pDto.put("sortno",
				G4Utils.isEmpty(pDto.getAsString("sortno")) ? Integer.valueOf("0") : pDto.getAsString("sortno"));
		pDto.put("enabled", ArmConstants.ENABLED_Y);
		g4Dao.insert("Organization.saveDeptItem", pDto);
		Dto updateDto = new BaseDto();
		updateDto.put("deptid", pDto.getAsString("parentid"));
		updateDto.put("leaf", ArmConstants.LEAF_N);
		g4Dao.update("Organization.updateLeafFieldInEaDept", updateDto);
		return null;
	}

	/**
	 * 修改部门
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto updateDeptItem(Dto pDto) {
		if (G4Utils.isEmpty(pDto.getAsString("sortno"))) {
			pDto.put("sortno", "0");
		}
		if (pDto.getAsString("parentid").equals(pDto.getAsString("parentid_old"))) {
			pDto.remove("parentid");
			g4Dao.update("Organization.updateDeptItem", pDto);
		} else {
			g4Dao.update("Organization.updateEadeptItem", pDto);
			saveDeptItem(pDto);
			pDto.put("parentid", pDto.getAsString("parentid_old"));
			updateLeafOfDeletedParent(pDto);
		}
		return null;
	}

	/**
	 * 调整被删除部门的直系父级部门的Leaf属性
	 * 
	 * @param pDto
	 */
	private void updateLeafOfDeletedParent(Dto pDto) {
		String parentid = pDto.getAsString("parentid");
		pDto.put("deptid", parentid);
		Integer countInteger = (Integer) g4Dao.queryForObject("Organization.prepareChangeLeafOfDeletedParentForEadept", pDto);
		if (countInteger.intValue() == 0) {
			pDto.put("leaf", ArmConstants.LEAF_Y);
		} else {
			pDto.put("leaf", ArmConstants.LEAF_N);
		}
		g4Dao.update("Organization.updateLeafFieldInEaDept", pDto);
	}

	/**
	 * 删除部门项
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto deleteDeptItems(Dto pDto) {
		Dto dto = new BaseDto();
		if (pDto.getAsString("type").equals("1")) {
			// 列表复选删除
			String[] arrChecked = pDto.getAsString("strChecked").split(",");
			for (int i = 0; i < arrChecked.length; i++) {
				dto.put("deptid", arrChecked[i]);
				deleteDept(dto);
			}
		} else {
			// 部门树右键删除
			dto.put("deptid", pDto.getAsString("deptid"));
			deleteDept(dto);
		}
		return null;
	}

	/**
	 * 删除部门 类内部调用
	 * 
	 * @param pDto
	 */
	private void deleteDept(Dto pDto) {
		Dto changeLeafDto = new BaseDto();
		Dto tempDto = (BaseDto) g4Dao.queryForObject("Organization.queryDeptItemsByDto", pDto);
		if (G4Utils.isNotEmpty(tempDto)) {
			changeLeafDto.put("parentid", tempDto.getAsString("parentid"));
		}
		g4Dao.delete("Organization.deleteEaroleAuthorizeInDeptManage", pDto);
		g4Dao.delete("Organization.deleteEaroleInDeptManage", pDto);
		g4Dao.delete("Organization.deleteEauserauthorizeInDeptManage", pDto);
		g4Dao.delete("Organization.deleteEauserauthorizeInDeptManage2", pDto);
		g4Dao.delete("Organization.deleteEausermenumapInDeptManage", pDto);
		g4Dao.delete("Organization.deleteEausersubinfoInDeptManage", pDto);
		g4Dao.delete("Organization.deleteEausermenumapInDeptManage", pDto);
		g4Dao.delete("Organization.deleteEarolemenumapInDeptManage", pDto);
		g4Dao.update("Organization.updateEauserInDeptManage", pDto);
		g4Dao.update("Organization.updateEadeptItem", pDto);
		if (G4Utils.isNotEmpty(tempDto)) {
			updateLeafOfDeletedParent(changeLeafDto);
		}
	}

	/**
	 * 根据用户所属部门编号查询部门对象<br>
	 * 用于构造组织机构树的根节点
	 * 
	 * @param
	 * @return
	 */
	public Dto queryDeptinfoByDeptid(Dto pDto) {
		Dto outDto = new BaseDto();
		outDto.putAll((BaseDto) g4Dao.queryForObject("Organization.queryDeptinfoByDeptid", pDto));
		outDto.put("success", new Boolean(true));
		return outDto;
	}

	/**
	 * 保存用户主题信息
	 * 
	 * @param pDto
	 */
	public Dto saveUserTheme(Dto pDto) {
		Dto outDto = new BaseDto();
		g4Dao.update("Organization.saveUserTheme", pDto);
		outDto.put("success", new Boolean(true));
		return outDto;
	}

}
