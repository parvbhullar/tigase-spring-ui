package org.eredlab.g4.demo.esb.webservice;

import javax.jws.WebService;

import org.njdt.gg.ccl.datastructure.impl.BasePo;

/**
 * WebService接口
 * 
* @author njdt
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
	
	/**
	 * 查询结算明细测试数据列表
	 * @param rownum
	 * @return XML字符串
	 */
	public String queryBalanceInfoLimitRownum(Integer rownum);
	
	
}
