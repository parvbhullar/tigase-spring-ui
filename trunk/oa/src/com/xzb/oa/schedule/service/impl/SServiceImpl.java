package com.xzb.oa.schedule.service.impl;

import java.util.List;

import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.arm.util.idgenerator.IdGenerator;
import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;
import com.xzb.oa.schedule.service.SService;

/**
 *知识结构模型业务实现类
 * 
 * @since 2010-01-13
 */
public class SServiceImpl extends BaseServiceImpl implements SService {

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
	 * 保存我的日程
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto saveScheItem(Dto pDto) {
		Dto outDto = new BaseDto();
		pDto.put("locked", ArmConstants.LOCK_Y);
		pDto.put("scheid", IDHelper.getScheduleid());
		g4Dao.insert("S.saveScheduleItem", pDto);
		return outDto;
	}

	/**
	 * 批量修改我的日程
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto deleteScheItems(Dto pDto) {
		String updatemode = pDto.getAsString("updatemode");
		String[] arrChecked = pDto.getAsString("strChecked").split(",");
		for (int i = 0; i < arrChecked.length; i++) {
			pDto.put("scheid", arrChecked[i]);
			if (updatemode.equals("finish")) {
				pDto.put("schestatus", "2");
			} else{
				pDto.put("locked", ArmConstants.LOCK_N);
			}
			g4Dao.update("S.updateScheduleItem", pDto);
		}
		return null;
	}

	/**
	 * 修改我的日程
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto updateScheItem(Dto pDto) {
		String updatemode = pDto.getAsString("updatemode");
		if (updatemode.equals("finish")) {
			pDto.put("schestatus", "2");
		} else if(updatemode.equals("delete")) {
			pDto.put("locked", ArmConstants.LOCK_N);
		}
		g4Dao.update("S.updateScheduleItem", pDto);
		return null;
	}
}
