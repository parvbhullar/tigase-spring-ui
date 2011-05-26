package com.ivyinfo.cassandra.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.exceptions.HectorException;

public class testdb {
	public static void main(String[] args) throws HectorException {
		DaoServicesImpl dao = new DaoServicesImpl();
		Cluster c = dao.getCluster("","");
		 try{
			 /**
			  * 非关系型数据库结构
			  * futuo:
			  * addressbook:[supercf(模块名)]
			  * mzq{[key(登录名)]
			  * 	张三:{[supercs]
			  * 		name:张三,
			  * 		email:123@163.com,
			  * 		fax:123456
			  * 	}
			  * }
			  * ls{[key]
			  * 	张三:{[supercs]
			  * 		name:张三,
			  * 		email:123@163.com,
			  * 		fax:123456
			  * 	}
			  * }
			  */
			 String keyspacename = "futuo";//域名  固定不变的
			 String supercf = "addressbook";//supercf通讯录  库名
			 String key = "mzq";//key表名  用户登录名

			 System.err.println("======c:"+c.describeClusterName());
			 /**
			  * 获得keyspace
			  */
			 Keyspace keyspace = dao.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
			 System.err.println("keyspace:"+keyspace.toString());
			 
			 /**
			  * 插入数据
			  */
//			 Map columnNames1 = new HashMap();
//			 Map columnNames2 = new HashMap();
//			 Map columnNames3 = new HashMap();
//			 columnNames1.put("name", "马兆庆");
//			 columnNames1.put("password", "123456");
//			 columnNames1.put("email", "ma19810722@163.com");
//			 columnNames1.put("phone", "13952002501");
//			 
//			 columnNames2.put("name", "张砚");
//			 columnNames2.put("password", "123456");
//			 columnNames2.put("email", "f221981@163.com");
//			 columnNames2.put("fax", "12345678");
//			 
//			 columnNames3.put("name", "你好");
//			 columnNames3.put("password", "123456");
//			 columnNames3.put("email", "123456789@qq.com");
//			 columnNames3.put("sax", "男");
//			 
//			 dao.insertSuper(key, StringSerializer.get(), "马兆庆", columnNames1, keyspace, supercf);
//			 dao.insertSuper(key, StringSerializer.get(), "张砚", columnNames2, keyspace, supercf);
//			 dao.insertSuper(key, StringSerializer.get(), "你好", columnNames3, keyspace, supercf);
			 
			 
			 
			 /**
			  * 删除数据，没有删除key
			  */
//			 String[] keys = {"1","2","3"};
//			 String[] columnNames = {"name","password","email"};
//			 dao.delete(keys, columnNames, StringSerializer.get(), keyspace, tablename);
			 
			 /**
			  * 修改数据
			  */
//			 Map columnNames1 = new HashMap();
////			 columnNames1.put("name", "马兆庆");
////			 columnNames1.put("password", "123456");
////			 columnNames1.put("email", "ma19810722@163.com");
//			 columnNames1.put("sex", "男");
//			 dao.insertSuper(key, StringSerializer.get(), "马兆庆", columnNames1, keyspace, supercf);
			 
			/**
			 * 根据一个key查询一个字段数据
			 */
//			 String fieldname = "name";
//			 String supercs = "马兆庆";//每条记录的key值
//			 String value = dao.getSuperColumnNameValue(key, supercs, fieldname, StringSerializer.get(), keyspace, supercf);
//			 System.err.println("value:"+value);
			 
			 /**
			  * 根据key查询多个字段的多条数据
			  */
			 String[] keys = {"mzq"};
			 Map m = dao.getSuperColumnValue(keys, keyspace, supercf, "马兆庆","", 1000);
			 for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
				 Map.Entry e = (Map.Entry) it.next();
				 String kk = (String) e.getKey();
				 Map mm = (Map) e.getValue();
				 System.out.println("kk:" + kk + " mm: " + mm);
				 for (Iterator it1 = mm.entrySet().iterator(); it1.hasNext();) {
					 Map.Entry ee = (Map.Entry) it1.next();
					 System.out.println("key:" + ee.getKey() + " value: " + ee.getValue());
				 }
			 }
			 
			 /**
			  * 根据条件查询数据信息
			  * 先查询所有信息，再根据信息比对符合条件的记录
			  */
			 
			 System.err.println("end");
			 dao.shutdownCluster(c);
		 }catch(Exception ex){
			 ex.printStackTrace();
			 dao.shutdownCluster(c);
		 }

	 }
}
