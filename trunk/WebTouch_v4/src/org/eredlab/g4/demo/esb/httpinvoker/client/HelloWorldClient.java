package org.eredlab.g4.demo.esb.httpinvoker.client;

import java.util.List;

import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BasePo;

/**
 * Httpinvoker接口
 * 
 * @author XiongChun
 * @since 2010-10-13
 * @see BasePo
 */
public interface HelloWorldClient {
	/**
	 * 需要对外发布的方法
	 * @param text
	 * @return
	 */
	public String sayHello(String text);
	
	/**
	 * 查询一条结算明细测试数据
	 * @param jsbh
	 * @return XML字符串
	 */
	public Dto queryBalanceInfo(String jsbh);
	
	/**
	 * 查询结算明细测试数据列表
	 * @param rownum
	 * @return List
	 */
	public List queryBalanceInfoLimitRownum(Integer rownum);
	
}
