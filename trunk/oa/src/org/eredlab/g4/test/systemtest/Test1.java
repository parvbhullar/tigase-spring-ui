package org.eredlab.g4.test.systemtest;

import java.util.List;

import org.eredlab.g4.bmf.base.IDao;
import org.eredlab.g4.bmf.base.IReader;
import org.eredlab.g4.bmf.util.SpringBeanLoader;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IReader g4Reader = (IReader) SpringBeanLoader.getSpringBean("g4Reader");
		List list = g4Reader.queryForList("Demo.testMyqlJDBC");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			System.out.println("---------");
		}
	}

}
