package com.ivyinfo.immessage.services;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.immessage.bean.IMMessageBean;

public interface IMMessageServices extends IBaseService{
	/**
	 * 新增用户IM聊天信息
	 * 必填字段：
	 * senduser：发送用户的登录名
	 * touser：接收用户的登录名
	 * message：消息内容
	 * nicesenduser：发送用户的昵称
	 * nicetouser：接收用户的昵称
	 * readflag：读取标记  0：未读  1：已读
	 * messagetype：消息类型  1：发送  2：接收
	 * savename：保存姓名
	 * @param imMessageBean
	 * @throws Exception
	 */
	public void AddIMMessage(IMMessageBean imMessageBean) throws Exception;
	
	/**
	 * 更新用户IM聊天信息
	 * 必填字段：
	 * id：id
	 * savename：保存姓名
	 * readflag：读取标记    0：未读  1：已读
	 * @param imMessageBean
	 * @throws Exception
	 */
	public void UpdIMMessage(IMMessageBean imMessageBean) throws Exception;
	
	/**
	 * 查询所有用户聊天信息
	 * @param logname
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List AllIMMessage(String logname,String id) throws Exception;
	
	/**
	 * 根据一个条件查询用户聊天信息
	 * @param logname 用户登录名
	 * @param fieldname 查询的字段名  如：readflag
	 * @param value 字段值  如：0
	 * @return
	 * @throws Exception
	 */
	public List AllIMMessage_ReadORType(String logname,String fieldname,String value) throws Exception;
	
	/**
	 * 根据两个条件查询用户聊天信息
	 * @param logname
	 * @param typevalue  消息类型值
	 * @param readvalue  读取类型值
	 * @return
	 * @throws Exception
	 */
	public List AllIMMessage_ReadAndType(String logname,String typevalue,String readvalue) throws Exception;
}
