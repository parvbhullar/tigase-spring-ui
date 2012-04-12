package org.eredlab.g4.demo.esb.httpinvoker;

import java.util.List;

import org.eredlab.g4.bmf.base.IReader;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BasePo;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;

/**
 * Httpinvoker实现类
 * 
 * @author XiongChun
 * @since 2010-10-13
 * @see BasePo
 */
public class HelloWorldImpl implements HelloWorld {
	/**
	 * sayHello
	 */
	public String sayHello(String text) {
		return "Hello," + text;
	}

	/**
	 * 查询一条结算明细测试数据
	 * 
	 * @param jsbh
	 * @return Dto
	 */
	public Dto queryBalanceInfo(String jsbh) {
		IReader reader = (IReader) SpringBeanLoader.getSpringBean("g4Reader");
		Dto inDto = new BaseDto("sxh", jsbh);
		Dto outDto = (BaseDto) reader.queryForObject("queryBalanceInfo", inDto);
		return outDto;
	}

	/**
	 * 查询结算明细测试数据列表
	 * 
	 * @param rownum
	 * @return List
	 */
	public List queryBalanceInfoLimitRownum(Integer rownum) {
		IReader reader = (IReader) SpringBeanLoader.getSpringBean("g4Reader");
		Dto inDto = new BaseDto("rownum", rownum);
		List outList = reader.queryForList("queryBalanceInfo", inDto);
		return outList;
	}

}
