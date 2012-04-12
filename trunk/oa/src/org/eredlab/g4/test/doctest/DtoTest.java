package org.eredlab.g4.test.doctest;

import java.util.ArrayList;
import java.util.List;

import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;

/**
 * 技术白皮书随书代码示例
 * 
 * @author XiongChun
 * @since 2011-03-29
 */
public class DtoTest {
	
	public void main(String[] args){
		Dto dto = new BaseDto();
		dto.setDefaultAList(new ArrayList());
		dto.setDefaultBList(new ArrayList());
		List aList = dto.getDefaultAList();
		List bList = dto.getDefaultBList();
		dto.put("cList", new ArrayList());
		List cList = dto.getAsList("cList");
		dto.put("name", "xiongchun");
		String name = dto.getAsString("name");
		dto.put("age", 20);
		Integer age = dto.getAsInteger("age");
		
	}
}
