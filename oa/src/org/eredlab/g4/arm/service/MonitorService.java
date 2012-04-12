package org.eredlab.g4.arm.service;

import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

/**
 * 系统监控业务接口
 * @author XiongChun
 * @since 2010-05-13
 */
public interface MonitorService extends BaseService{
	
	/**
	 * 保存一个HTTP会话
	 * @param userInfo
	 */
	public void saveHttpSession(UserInfoVo userInfo);
	
	/**
	 * 删除一个托管的会话连接
	 * @param pSessionID
	 */
	public void deleteHttpSession(Dto dto);
	
	/**
	 * 创建一个事件
	 * @param inDto
	 */
	public void saveEvent(Dto dto);
	
	/**
	 * 删除事件
	 * @param inDto
	 */
	public Dto deleteEvent(Dto inDto);

	/**
	 * 删除SpringBean监控记录
	 * 
	 * @param inDto
	 */
	public Dto deleteMonitorData(Dto inDto);
	
	/**
	 * 重置监控信息
	 * 
	 * @param inDto
	 */
	public Dto resetMonitorData();
}
