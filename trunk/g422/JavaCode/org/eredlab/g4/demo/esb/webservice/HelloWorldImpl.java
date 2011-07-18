package org.eredlab.g4.demo.esb.webservice;

import java.util.List;

import javax.jws.WebService;

import org.eredlab.g4.bmf.base.IReader;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.njdt.gg.ccl.datastructure.Dto;
import org.njdt.gg.ccl.datastructure.impl.BaseDto;
import org.njdt.gg.ccl.datastructure.impl.BasePo;
import org.njdt.gg.ccl.xml.XmlHelper;

/**
 * WebService实现类
 * 
* @author njdt
 * @since 2010-10-13
 * @see BasePo
 */
@WebService
public class HelloWorldImpl implements HelloWorld {
	/**
	 * sayHello
	 */
	public String sayHello(String text) {
		return "Hello," + text;
	}
	
	/**
	 * 查询一条结算明细测试数据
	 * @param jsbh
	 * @return XML字符串
	 */
	public String queryBalanceInfo(String jsbh){
		IReader reader = (IReader)SpringBeanLoader.getSpringBean("g4Reader");
		Dto inDto = new BaseDto("sxh", jsbh);
		Dto outDto = (BaseDto)reader.queryForObject("queryBalanceInfo", inDto);
		String outXml = XmlHelper.parseDto2Xml(outDto, "root", "balanceInfo");
		return outXml;
	}
	
	/**
	 * 查询结算明细测试数据列表
	 * @param rownum
	 * @return XML字符串
	 */
	public String queryBalanceInfoLimitRownum(Integer rownum){
		IReader reader = (IReader)SpringBeanLoader.getSpringBean("g4Reader");
		Dto inDto = new BaseDto("rownum", rownum);
		List outList = reader.queryForList("queryBalanceInfo", inDto);
		String outXml = XmlHelper.parseList2Xml(outList, "root", "row");
		return outXml;
	}

}
