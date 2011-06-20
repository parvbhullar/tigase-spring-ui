package org.eredlab.g4.arm.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

/**
 * 项目主页服务接口
 * 
 * @author XiongChun
 * @since 2010-12-25
 */
public interface ProjectHomeService extends BaseService {
	
	/**
	 * 保存新主题
	 * 
	 * @param inDto
	 * @return
	 */
	public Dto saveTopicItem(Dto inDto);
	
	/**
	 * 更新浏览人次和回帖次数
	 * 
	 * @param inDto
	 * @return
	 */
	public Dto updateCount(Dto inDto);
	
	/**
	 * 保存回帖信息
	 * 
	 * @param inDto
	 * @return
	 */
	public Dto saveReplyTopic(Dto inDto);
	
	/**
	 * 修改主题帖
	 * 
	 * @param inDto
	 * @return
	 */
	public Dto editTopic(Dto inDto);
	
	/**
	 * 删除主题帖
	 * 
	 * @param inDto
	 * @return
	 */
	public Dto deleteTopic(Dto inDto);
	
	/**
	 * 修改回帖
	 * 
	 * @param inDto
	 * @return
	 */
	public Dto editReply(Dto inDto);
	
	/**
	 * 删除回帖
	 * 
	 * @param inDto
	 * @return
	 */
	public Dto deleteReply(Dto inDto);
	
}
