package org.eredlab.g4.demo.esb.webservice;

import javax.jws.WebService;

import org.eredlab.g4.ccl.datastructure.impl.BasePo;

/**
 * WebService接口
 * 
 * @author XiongChun
 * @since 2010-10-13
 * @see BasePo
 */
@WebService
public interface HelloWorld {
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
	public String queryBalanceInfo(String jsbh);
	
}
