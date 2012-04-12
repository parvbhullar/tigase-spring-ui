package org.eredlab.g4.test.doctest;

import org.eredlab.g4.ccl.util.G4Constants;

/**
 * 技术白皮书随书代码示例
 * 
 * @author XiongChun
 * @since 2011-03-29
 */
public class PoTest {
	
	public static void main(String[] args) {
		StudentPo studentPo = new StudentPo();
		// 将PO对象转为Dto对象
		studentPo.toDto();
		// 将PO对象转换为JSON资料格式
		studentPo.toJson();
		// 将PO对象转换为XML资料格式
		studentPo.toXml(G4Constants.XML_Node);
	}

}
