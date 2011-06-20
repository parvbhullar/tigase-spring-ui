package org.eredlab.g4.demo.esb.httpinvoker;

import java.util.List;

import org.eredlab.g4.bmf.base.IReader;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.datastructure.impl.BasePo;

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
		Dto inDto = new BaseDto();
		inDto.put("menuid", "01");
		Dto outDto = (BaseDto) reader.queryForObject("queryMenuItemsByDto", inDto);
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
		Dto inDto = new BaseDto();
		inDto.put("menuid", "01");
		List outList = reader.queryForList("queryMenuItemsByDto", inDto);
		
//		inDto = new BaseDto();
//		inDto.put("deptid", "001");
//		inDto.put("start", "0");
//		outList = reader.queryForList("queryDeptsForManage", inDto);
		
//		inDto = new BaseDto();
//		outList = reader.queryForList("queryMenuItemsForManage", inDto);
		return outList;
	}

	@Override
	public List queryMenuItems(String nodeid) {
		IReader reader = (IReader) SpringBeanLoader.getSpringBean("g4Reader");
		Dto inDto = new BaseDto();
		inDto.put("menuid", nodeid);
		List outList = reader.queryForList("queryMenuItemsByDto", inDto);
		return outList;
	}

}
