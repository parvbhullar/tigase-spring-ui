package org.eredlab.g4.arm.service.impl;

import org.eredlab.g4.arm.service.MonitorService;
import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.njdt.gg.ccl.datastructure.Dto;
import org.njdt.gg.ccl.datastructure.impl.BaseDto;

/**
 * 系统监控业务接口
 * 
 * @author XiongChun
 * @since 2010-09-13
 */
public class MonitorServiceImpl extends BaseServiceImpl implements MonitorService {

	/**
	 * 保存一个HTTP会话
	 * 
	 * @param userInfo
	 */
	public void saveHttpSession(UserInfoVo userInfo) {
		g4Dao.insert("saveHttpSession", userInfo);
	}

	/**
	 * 删除一个托管的会话连接
	 * 
	 * @param pSessionID
	 */
	public void deleteHttpSession(Dto dto) {
		g4Dao.delete("deleteHttpSession", dto);
	}

	/**
	 * 创建一个事件
	 * 
	 * @param inDto
	 */
	public void saveEvent(Dto dto) {
		String eventid = IDHelper.getEventID();
		dto.put("eventid", eventid);
		g4Dao.insert("saveEvent", dto);
	}

	/**
	 * 删除事件
	 * 
	 * @param inDto
	 */
	public Dto deleteEvent(Dto inDto) {
		if (inDto.getAsString("type").equalsIgnoreCase("reset")) {
			g4Dao.delete("resetEvent");
		} else {
			String[] checked = inDto.getAsString("strChecked").split(",");
			for (int i = 0; i < checked.length; i++) {
				g4Dao.delete("deleteEvent", checked[i]);
			}
		}
		return null;
	}

	/**
	 * 删除SpringBean监控记录
	 * 
	 * @param inDto
	 */
	public Dto deleteMonitorData(Dto inDto) {
		if (inDto.getAsString("type").equalsIgnoreCase("reset")) {
			g4Dao.delete("resetBeanMonitorRecords");
		} else {
			String[] checked = inDto.getAsString("strChecked").split(",");
			for (int i = 0; i < checked.length; i++) {
				g4Dao.delete("deleteBeanMonitorRecord", checked[i]);
			}
		}
		return null;
	}

	/**
	 * 重置监控信息
	 * 
	 * @param inDto
	 */
	public Dto resetMonitorData() {
		g4Dao.delete("resetJdbcMonitorData");
		return null;
	}

}
