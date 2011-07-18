package org.eredlab.g4.demo.esb.httpinvoker;

import java.util.List;

import org.eredlab.g4.bmf.base.IReader;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.njdt.gg.ccl.datastructure.Dto;
import org.njdt.gg.ccl.datastructure.impl.BaseDto;
import org.njdt.gg.ccl.datastructure.impl.BasePo;

/**
 * Httpinvoker实现类
 * 
* @author njdt
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
		inDto.put("menuid", "0101");
		List outList = reader.queryForList("queryMenuItemsByDto", inDto);
		
		inDto = new BaseDto();
		inDto.put("deptid", "001");
		inDto.put("start", "0");
		//{limit=50, deptid=001, firstload=true, start=0, reqCode=queryDeptsForManage, end=50}
		outList = reader.queryForList("queryDeptsForManage", inDto);
		
		inDto = new BaseDto();
		outList = reader.queryForList("queryMenuItemsForManage", inDto);
		return outList;
	}
	
	@Override
	public List queryMenuItems(Dto inDto) {
		IReader reader = (IReader) SpringBeanLoader.getSpringBean("g4Reader");
		List outList = reader.queryForList("queryMenuItemsByDto", inDto);
		return outList;
	}

}
