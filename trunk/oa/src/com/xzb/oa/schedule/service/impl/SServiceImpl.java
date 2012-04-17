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
		String limitisId=IDHelper.getK_limitis_ID();
		Dto limits=new BaseDto();
		String scheid=IDHelper.getScheduleid();
		pDto.put("locked", ArmConstants.LOCK_Y);
		pDto.put("scheid", scheid);
		
		limits.put("dirId", scheid);
		limits.put("id", limitisId);
		limits.put("type", "2");
		g4Dao.insert("S.insertLimitisSch", limits);
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

	
	public Dto updateShareType(Dto dto) {
		g4Dao.update("S.updateShareType", dto);		
		return null;
		
	}

	/**
	 * 查询下级
	 */
	public Dto queryLower(Dto dto) {
		
		Dto inDto=new BaseDto();
		Dto outDto=new BaseDto();
		List deptList = g4Dao.queryForList("S.queryAllLower", dto);
		if(!G4Utils.isEmpty("deptList"))
		{
			for(int i=0;i<deptList.size();i++)
			{
				
				inDto=(BaseDto)deptList.get(i);
				inDto.put("text", inDto.getAsString("username")+"  "+inDto.getAsString("deptname"));
				inDto.put("id", inDto.getAsString("userid"));
				int count=(Integer)g4Dao.queryForObject("S.queryCountLeader", inDto);
				if(count==0)
				{
					inDto.put("leaf", true);
					inDto.put("icon", "./resource/image/ext/user.gif");
				}else{
					inDto.put("leaf", false);
					inDto.put("icon", "./resource/image/ext/group.png");
				}
			}
			
			
		}
		outDto.put("jsonString", JsonHelper.encodeObject2Json(deptList));
		
		return outDto;
	}
}
