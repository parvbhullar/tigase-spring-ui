package org.eredlab.g4.arm.service.impl;

import org.eredlab.g4.arm.service.ResourceService;
import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.arm.util.idgenerator.IdGenerator;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.util.G4Utils;

/**
 * 资源模型业务实现类
 * 
 * @author XiongChun
 * @since 2010-01-13
 */
public class ResourceServiceImpl extends BaseServiceImpl implements ResourceService {

	/**
	 * 保存代码对照
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto saveCodeItem(Dto pDto) {
		Dto outDto = new BaseDto();
		String codeid = IDHelper.getCodeID();
		pDto.put("codeid", codeid);
		Dto checkDto = (BaseDto) g4Dao.queryForObject("Resource.checkEaCodeByIndex", pDto);
		if (G4Utils.isNotEmpty(checkDto)) {
			outDto.put("success", new Boolean(false));
			outDto.put("msg", "违反唯一约束,[对照字段]和[代码]组合不能重复.");
			return outDto;
		} else {
			g4Dao.insert("Resource.createEacodeDomain", pDto);
			outDto.put("success", new Boolean(true));
		}
		return outDto;
	}

	/**
	 * 删除代码表
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto deleteCodeItem(Dto pDto) {
		Dto dto = new BaseDto();
		String[] arrChecked = pDto.getAsString("strChecked").split(",");
		for (int i = 0; i < arrChecked.length; i++) {
			dto.put("codeid", arrChecked[i]);
			Dto chechkDto = (BaseDto) g4Dao.queryForObject("Resource.getEaCodeByKey", dto);
			if (chechkDto.getAsString("editmode").equals(ArmConstants.EDITMODE_Y)) {
				g4Dao.delete("Resource.deleteCodeItem", dto);
			}
		}
		return null;
	}

	/**
	 * 修改代码表
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto updateCodeItem(Dto pDto) {
		g4Dao.update("Resource.updateCodeItem", pDto);
		return null;
	}

	/**
	 * 保存菜单
	 * 
	 * @param pDto
	 * @return
	 */
	public synchronized Dto saveMenuItem(Dto pDto) {
		String menuid = IdGenerator.getMenuIdGenerator(pDto.getAsString("parentid"));
		pDto.put("menuid", menuid);
		pDto.put("leaf", ArmConstants.LEAF_Y);
		pDto.put("sortno", G4Utils.isEmpty(pDto.getAsString("sortno")) ? Integer.valueOf("0") : pDto
				.getAsString("sortno"));
		g4Dao.insert("Resource.saveMenuItem", pDto);
		Dto updateDto = new BaseDto();
		updateDto.put("menuid", pDto.getAsString("parentid"));
		updateDto.put("leaf", ArmConstants.LEAF_N);
		g4Dao.update("Resource.updateLeafFieldInEaMenu", updateDto);
		return null;
	}

	/**
	 * 删除菜单项
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto deleteMenuItems(Dto pDto) {
		Dto dto = new BaseDto();
		Dto changeLeafDto = new BaseDto();
		if (pDto.getAsString("type").equals("1")) {
			String[] arrChecked = pDto.getAsString("strChecked").split(",");
			for (int i = 0; i < arrChecked.length; i++) {
				dto.put("menuid", arrChecked[i]);
				changeLeafDto.put("parentid", ((BaseDto) g4Dao.queryForObject("Resource.queryMenuItemsByDto", dto))
						.getAsString("parentid"));
				g4Dao.delete("Resource.deleteEamenuItem", dto);
				g4Dao.delete("Resource.deleteEarwauthorizeItem", dto);
				g4Dao.delete("Resource.deleteEausermenumapByMenuid", dto);
				updateLeafOfDeletedParent(changeLeafDto);
			}
		} else {
			dto.put("menuid", pDto.getAsString("menuid"));
			changeLeafDto.put("parentid", ((BaseDto) g4Dao.queryForObject("Resource.queryMenuItemsByDto", dto))
					.getAsString("parentid"));
			g4Dao.delete("Resource.deleteEamenuItem", dto);
			g4Dao.delete("Resource.deleteEarwauthorizeItem", dto);
			g4Dao.delete("Resource.deleteEausermenumapByMenuid", dto);
			updateLeafOfDeletedParent(changeLeafDto);
		}
		return null;
	}

	/**
	 * 调整被删除菜单的直系父级菜单的Leaf属性
	 * 
	 * @param pDto
	 */
	private void updateLeafOfDeletedParent(Dto pDto) {
		String parentid = pDto.getAsString("parentid");
		pDto.put("menuid", parentid);
		Integer countInteger = (Integer) g4Dao.queryForObject("Resource.prepareChangeLeafOfDeletedParent", pDto);
		if (countInteger.intValue() == 0) {
			pDto.put("leaf", ArmConstants.LEAF_Y);
		} else {
			pDto.put("leaf", ArmConstants.LEAF_N);
		}
		g4Dao.update("Resource.updateLeafFieldInEaMenu", pDto);
	}

	/**
	 * 修改菜单
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto updateMenuItem(Dto pDto) {
		if (G4Utils.isEmpty(pDto.getAsString("sortno"))) {
			pDto.put("sortno", "0");
		}
		if (pDto.getAsString("parentid").equals(pDto.getAsString("parentid_old"))) {
			pDto.remove("parentid");
			g4Dao.update("Resource.updateMenuItem", pDto);
		} else {
			g4Dao.delete("Resource.deleteEamenuItem", pDto);
			g4Dao.delete("Resource.deleteEarwauthorizeItem", pDto);
			g4Dao.delete("Resource.deleteEausermenumapByMenuid", pDto);
			saveMenuItem(pDto);
			pDto.put("parentid", pDto.getAsString("parentid_old"));
			updateLeafOfDeletedParent(pDto);
		}
		return null;
	}

}
