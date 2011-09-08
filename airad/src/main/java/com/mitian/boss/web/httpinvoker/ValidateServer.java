package com.mitian.boss.web.httpinvoker;

/**
 * httpInvoke服务类，提供前台验证用户服务
 * 
 * @author wangxing
 * 
 */
public interface ValidateServer {

	public String validate(String salerId, String memberId);

}
