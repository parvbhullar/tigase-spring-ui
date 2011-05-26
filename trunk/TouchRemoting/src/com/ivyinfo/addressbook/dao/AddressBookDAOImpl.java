package com.ivyinfo.addressbook.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;

import com.ivyinfo.addressbook.bean.AddressBookBean;
import com.ivyinfo.cassandra.services.DaoServices;
import com.ivyinfo.framework.service.server.SpringContextUtil;

public class AddressBookDAOImpl implements AddressBookDAO{
	
	public List AllIndex(String logname,String id) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		List list = new ArrayList();
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "addressbook";//supercf通讯录  库名
		
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		String[] keys = {""+logname+""};
		String supercs = id;
		Map m = daoServices.getSuperColumnValue(keys, keyspace, supercf, supercs, supercs, 1000);
		for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
			Map.Entry e = (Map.Entry) it.next();
			String kk = (String) e.getKey();
			Map mm = (Map) e.getValue();
			AddressBookBean comBean = new AddressBookBean();
			comBean.setId(kk);
			comBean.setUsername(String.valueOf(mm.get(AddressBook.Username)));
			comBean.setFirstName(String.valueOf(mm.get(AddressBook.FirstName)));
			comBean.setMiddleName(String.valueOf(mm.get(AddressBook.MiddleName)));
			comBean.setLastName(String.valueOf(mm.get(AddressBook.LastName)));
			comBean.setTitle(String.valueOf(mm.get(AddressBook.Title)));
			comBean.setSex(String.valueOf(mm.get(AddressBook.Sex)));
			comBean.setGsmNoPrimary(String.valueOf(mm.get(AddressBook.GsmNoPrimary)));
			comBean.setGsmNoAlternate(String.valueOf(mm.get(AddressBook.GsmNoAlternate)));
			comBean.setEmailPrimary(String.valueOf(mm.get(AddressBook.EmailPrimary)));
			comBean.setEmailAlternate(String.valueOf(mm.get(AddressBook.EmailAlternate)));
			comBean.setWebPage(String.valueOf(mm.get(AddressBook.WebPage)));
			comBean.setPersonalNote(String.valueOf(mm.get(AddressBook.PersonalNote)));
			comBean.setSpouseName(String.valueOf(mm.get(AddressBook.SpouseName)));
			comBean.setNickName(String.valueOf(mm.get(AddressBook.NickName)));
			comBean.setHomeAddress(String.valueOf(mm.get(AddressBook.HomeAddress)));
			comBean.setHomeCity(String.valueOf(mm.get(AddressBook.HomeCity)));
			comBean.setHomeProvince(String.valueOf(mm.get(AddressBook.HomeProvince)));
			comBean.setHomeZip(String.valueOf(mm.get(AddressBook.HomeZip)));
			comBean.setHomeCountry(String.valueOf(mm.get(AddressBook.HomeCountry)));
			comBean.setHomePhone(String.valueOf(mm.get(AddressBook.HomePhone)));
			comBean.setHomeFaks(String.valueOf(mm.get(AddressBook.HomeFaks)));
			comBean.setWorkCompany(String.valueOf(mm.get(AddressBook.WorkCompany)));
			comBean.setWorkJobTitle(String.valueOf(mm.get(AddressBook.WorkJobTitle)));
			comBean.setWorkOffice(String.valueOf(mm.get(AddressBook.WorkOffice)));
			comBean.setWorkProfession(String.valueOf(mm.get(AddressBook.WorkProfession)));
			comBean.setWorkManagerName(String.valueOf(mm.get(AddressBook.WorkManagerName)));
			comBean.setWorkAssistantName(String.valueOf(mm.get(AddressBook.WorkAssistantName)));
			comBean.setWorkAddress(String.valueOf(mm.get(AddressBook.WorkAddress)));
			comBean.setWorkCity(String.valueOf(mm.get(AddressBook.WorkCity)));
			comBean.setWorkProvince(String.valueOf(mm.get(AddressBook.WorkProvince)));
			comBean.setWorkZip(String.valueOf(mm.get(AddressBook.WorkZip)));
			comBean.setWorkCountry(String.valueOf(mm.get(AddressBook.WorkCountry)));
			comBean.setWorkPhone(String.valueOf(mm.get(AddressBook.WorkPhone)));
			comBean.setWorkFaks(String.valueOf(mm.get(AddressBook.WorkFaks)));
			comBean.setWorkDepartment(String.valueOf(mm.get(AddressBook.WorkDepartment)));
			comBean.setBirthDay(String.valueOf(mm.get(AddressBook.BirthDay)));
			comBean.setBirthMonth(String.valueOf(mm.get(AddressBook.BirthMonth)));
			comBean.setAnniversaryDay(String.valueOf(mm.get(AddressBook.AnniversaryDay)));
			comBean.setAnniversaryMonth(String.valueOf(mm.get(AddressBook.AnniversaryMonth)));
			
			list.add(comBean);
		}
		return list;
	}
	
	public List AllIndexCondition(String logname,String fieldname,String fieldvalue) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		List list = new ArrayList();
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "addressbook";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		String[] keys = {""+logname+""};
		String supercs = "";
		Map m = daoServices.getSuperColumnValue_AddressBook_One(keys, keyspace, supercf, supercs, fieldname, fieldvalue, 1000);
		for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
			Map.Entry e = (Map.Entry) it.next();
			String kk = (String) e.getKey();
			Map mm = (Map) e.getValue();
			AddressBookBean comBean = new AddressBookBean();
			comBean.setId(kk);
			comBean.setUsername(String.valueOf(mm.get(AddressBook.Username)));
			comBean.setFirstName(String.valueOf(mm.get(AddressBook.FirstName)));
			comBean.setMiddleName(String.valueOf(mm.get(AddressBook.MiddleName)));
			comBean.setLastName(String.valueOf(mm.get(AddressBook.LastName)));
			comBean.setTitle(String.valueOf(mm.get(AddressBook.Title)));
			comBean.setSex(String.valueOf(mm.get(AddressBook.Sex)));
			comBean.setGsmNoPrimary(String.valueOf(mm.get(AddressBook.GsmNoPrimary)));
			comBean.setGsmNoAlternate(String.valueOf(mm.get(AddressBook.GsmNoAlternate)));
			comBean.setEmailPrimary(String.valueOf(mm.get(AddressBook.EmailPrimary)));
			comBean.setEmailAlternate(String.valueOf(mm.get(AddressBook.EmailAlternate)));
			comBean.setWebPage(String.valueOf(mm.get(AddressBook.WebPage)));
			comBean.setPersonalNote(String.valueOf(mm.get(AddressBook.PersonalNote)));
			comBean.setSpouseName(String.valueOf(mm.get(AddressBook.SpouseName)));
			comBean.setNickName(String.valueOf(mm.get(AddressBook.NickName)));
			comBean.setHomeAddress(String.valueOf(mm.get(AddressBook.HomeAddress)));
			comBean.setHomeCity(String.valueOf(mm.get(AddressBook.HomeCity)));
			comBean.setHomeProvince(String.valueOf(mm.get(AddressBook.HomeProvince)));
			comBean.setHomeZip(String.valueOf(mm.get(AddressBook.HomeZip)));
			comBean.setHomeCountry(String.valueOf(mm.get(AddressBook.HomeCountry)));
			comBean.setHomePhone(String.valueOf(mm.get(AddressBook.HomePhone)));
			comBean.setHomeFaks(String.valueOf(mm.get(AddressBook.HomeFaks)));
			comBean.setWorkCompany(String.valueOf(mm.get(AddressBook.WorkCompany)));
			comBean.setWorkJobTitle(String.valueOf(mm.get(AddressBook.WorkJobTitle)));
			comBean.setWorkOffice(String.valueOf(mm.get(AddressBook.WorkOffice)));
			comBean.setWorkProfession(String.valueOf(mm.get(AddressBook.WorkProfession)));
			comBean.setWorkManagerName(String.valueOf(mm.get(AddressBook.WorkManagerName)));
			comBean.setWorkAssistantName(String.valueOf(mm.get(AddressBook.WorkAssistantName)));
			comBean.setWorkAddress(String.valueOf(mm.get(AddressBook.WorkAddress)));
			comBean.setWorkCity(String.valueOf(mm.get(AddressBook.WorkCity)));
			comBean.setWorkProvince(String.valueOf(mm.get(AddressBook.WorkProvince)));
			comBean.setWorkZip(String.valueOf(mm.get(AddressBook.WorkZip)));
			comBean.setWorkCountry(String.valueOf(mm.get(AddressBook.WorkCountry)));
			comBean.setWorkPhone(String.valueOf(mm.get(AddressBook.WorkPhone)));
			comBean.setWorkFaks(String.valueOf(mm.get(AddressBook.WorkFaks)));
			comBean.setWorkDepartment(String.valueOf(mm.get(AddressBook.WorkDepartment)));
			comBean.setBirthDay(String.valueOf(mm.get(AddressBook.BirthDay)));
			comBean.setBirthMonth(String.valueOf(mm.get(AddressBook.BirthMonth)));
			comBean.setAnniversaryDay(String.valueOf(mm.get(AddressBook.AnniversaryDay)));
			comBean.setAnniversaryMonth(String.valueOf(mm.get(AddressBook.AnniversaryMonth)));
			
			list.add(comBean);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public void AddSubmit(AddressBookBean addressBookBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "addressbook";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = addressBookBean.getUsername();
		
		Map columnNames = new HashMap();
		columnNames.put(AddressBook.Username, addressBookBean.getUsername());
		columnNames.put(AddressBook.FirstName, addressBookBean.getFirstName());
		columnNames.put(AddressBook.MiddleName, addressBookBean.getMiddleName());
		columnNames.put(AddressBook.LastName, addressBookBean.getLastName());
		columnNames.put(AddressBook.Title, addressBookBean.getTitle());
		columnNames.put(AddressBook.Sex, addressBookBean.getSex());
		columnNames.put(AddressBook.GsmNoPrimary, addressBookBean.getGsmNoPrimary());
		columnNames.put(AddressBook.GsmNoAlternate, addressBookBean.getGsmNoAlternate());
		columnNames.put(AddressBook.EmailPrimary, addressBookBean.getEmailPrimary());
		columnNames.put(AddressBook.EmailAlternate, addressBookBean.getEmailAlternate());
		columnNames.put(AddressBook.WebPage, addressBookBean.getWebPage());
		columnNames.put(AddressBook.PersonalNote, addressBookBean.getPersonalNote());
		columnNames.put(AddressBook.SpouseName, addressBookBean.getSpouseName());
		columnNames.put(AddressBook.NickName, addressBookBean.getNickName());
		columnNames.put(AddressBook.HomeAddress, addressBookBean.getHomeAddress());
		columnNames.put(AddressBook.HomeCity, addressBookBean.getHomeCity());
		columnNames.put(AddressBook.HomeProvince, addressBookBean.getHomeProvince());
		columnNames.put(AddressBook.HomeZip, addressBookBean.getHomeZip());
		columnNames.put(AddressBook.HomeCountry, addressBookBean.getHomeCountry());
		columnNames.put(AddressBook.HomePhone, addressBookBean.getHomePhone());
		columnNames.put(AddressBook.HomeFaks, addressBookBean.getHomeFaks());
		columnNames.put(AddressBook.WorkCompany, addressBookBean.getWorkCompany());
		columnNames.put(AddressBook.WorkJobTitle, addressBookBean.getWorkJobTitle());
		columnNames.put(AddressBook.WorkOffice, addressBookBean.getWorkOffice());
		columnNames.put(AddressBook.WorkProfession, addressBookBean.getWorkProfession());
		columnNames.put(AddressBook.WorkManagerName, addressBookBean.getWorkManagerName());
		columnNames.put(AddressBook.WorkAssistantName, addressBookBean.getWorkAssistantName());
		columnNames.put(AddressBook.WorkAddress, addressBookBean.getWorkAddress());
		columnNames.put(AddressBook.WorkCity, addressBookBean.getWorkCity());
		columnNames.put(AddressBook.WorkProvince, addressBookBean.getWorkProvince());
		columnNames.put(AddressBook.WorkZip, addressBookBean.getWorkZip());
		columnNames.put(AddressBook.WorkCountry, addressBookBean.getWorkCountry());
		columnNames.put(AddressBook.WorkPhone, addressBookBean.getWorkPhone());
		columnNames.put(AddressBook.WorkFaks, addressBookBean.getWorkFaks());
		columnNames.put(AddressBook.WorkDepartment, addressBookBean.getWorkDepartment());
		columnNames.put(AddressBook.BirthDay, addressBookBean.getBirthDay());
		columnNames.put(AddressBook.BirthMonth, addressBookBean.getBirthMonth());
		columnNames.put(AddressBook.AnniversaryDay, addressBookBean.getAnniversaryDay());
		columnNames.put(AddressBook.AnniversaryMonth, addressBookBean.getAnniversaryMonth());
		
		daoServices.insertSuper(key, StringSerializer.get(), addressBookBean.getId()
				, columnNames, keyspace, supercf);
	}
	
	@SuppressWarnings("unchecked")
	public void UpdSubmit(AddressBookBean addressBookBean) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "addressbook";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = addressBookBean.getUsername();
		
		Map columnNames = new HashMap();
		columnNames.put(AddressBook.FirstName, addressBookBean.getFirstName());
		columnNames.put(AddressBook.MiddleName, addressBookBean.getMiddleName());
		columnNames.put(AddressBook.LastName, addressBookBean.getLastName());
		columnNames.put(AddressBook.Title, addressBookBean.getTitle());
		columnNames.put(AddressBook.Sex, addressBookBean.getSex());
		columnNames.put(AddressBook.GsmNoPrimary, addressBookBean.getGsmNoPrimary());
		columnNames.put(AddressBook.GsmNoAlternate, addressBookBean.getGsmNoAlternate());
		columnNames.put(AddressBook.EmailPrimary, addressBookBean.getEmailPrimary());
		columnNames.put(AddressBook.EmailAlternate, addressBookBean.getEmailAlternate());
		columnNames.put(AddressBook.WebPage, addressBookBean.getWebPage());
		columnNames.put(AddressBook.PersonalNote, addressBookBean.getPersonalNote());
		columnNames.put(AddressBook.SpouseName, addressBookBean.getSpouseName());
		columnNames.put(AddressBook.NickName, addressBookBean.getNickName());
		columnNames.put(AddressBook.HomeAddress, addressBookBean.getHomeAddress());
		columnNames.put(AddressBook.HomeCity, addressBookBean.getHomeCity());
		columnNames.put(AddressBook.HomeProvince, addressBookBean.getHomeProvince());
		columnNames.put(AddressBook.HomeZip, addressBookBean.getHomeZip());
		columnNames.put(AddressBook.HomeCountry, addressBookBean.getHomeCountry());
		columnNames.put(AddressBook.HomePhone, addressBookBean.getHomePhone());
		columnNames.put(AddressBook.HomeFaks, addressBookBean.getHomeFaks());
		columnNames.put(AddressBook.WorkCompany, addressBookBean.getWorkCompany());
		columnNames.put(AddressBook.WorkJobTitle, addressBookBean.getWorkJobTitle());
		columnNames.put(AddressBook.WorkOffice, addressBookBean.getWorkOffice());
		columnNames.put(AddressBook.WorkProfession, addressBookBean.getWorkProfession());
		columnNames.put(AddressBook.WorkManagerName, addressBookBean.getWorkManagerName());
		columnNames.put(AddressBook.WorkAssistantName, addressBookBean.getWorkAssistantName());
		columnNames.put(AddressBook.WorkAddress, addressBookBean.getWorkAddress());
		columnNames.put(AddressBook.WorkCity, addressBookBean.getWorkCity());
		columnNames.put(AddressBook.WorkProvince, addressBookBean.getWorkProvince());
		columnNames.put(AddressBook.WorkZip, addressBookBean.getWorkZip());
		columnNames.put(AddressBook.WorkCountry, addressBookBean.getWorkCountry());
		columnNames.put(AddressBook.WorkPhone, addressBookBean.getWorkPhone());
		columnNames.put(AddressBook.WorkFaks, addressBookBean.getWorkFaks());
		columnNames.put(AddressBook.WorkDepartment, addressBookBean.getWorkDepartment());
		columnNames.put(AddressBook.BirthDay, addressBookBean.getBirthDay());
		columnNames.put(AddressBook.BirthMonth, addressBookBean.getBirthMonth());
		columnNames.put(AddressBook.AnniversaryDay, addressBookBean.getAnniversaryDay());
		columnNames.put(AddressBook.AnniversaryMonth, addressBookBean.getAnniversaryMonth());
		
		daoServices.insertSuper(key, StringSerializer.get(), addressBookBean.getId(), columnNames, keyspace, supercf);
	}
	
	public void Del(String id,String logname) throws Exception{
		DaoServices daoServices =(DaoServices) SpringContextUtil.getBean("noSqlApiServices");
		Cluster c = daoServices.getCluster("","");
		String keyspacename = "futuo";//域名  固定不变的
		String supercf = "addressbook";//supercf通讯录  库名
		/**
		  * 获得keyspace
		  */
		Keyspace keyspace = daoServices.getKeyspace(keyspacename, supercf, "Super", "UTF8Type", c);
		
		String key = logname;
		
		daoServices.removeSuperColumn(key, id, null, keyspace, supercf);
	}
}
