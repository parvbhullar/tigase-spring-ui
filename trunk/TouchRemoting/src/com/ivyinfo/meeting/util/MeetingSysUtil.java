package com.ivyinfo.meeting.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ivyinfo.framework.service.servlet.HttpRequester;
import com.ivyinfo.framework.service.servlet.HttpRespons;
import com.ivyinfo.generic.country.CountryCode;
import com.ivyinfo.meeting.bean.MeetingDetailListSysBean;
import com.ivyinfo.meeting.bean.MeetingDetailSysBean;
import com.ivyinfo.meeting.bean.MeetingReturnBean;
import com.ivyinfo.meeting.bean.MeetingReturnSysBean;
import com.ivyinfo.meeting.bean.MeetingSysBean;
import com.ivyinfo.meeting.bean.MeetingXMLSysBean;
import com.ivyinfo.user.bean.UserBean;

public class MeetingSysUtil implements MeetingXMLSysBean{
	
	/**
	 * 会议系统管理组织信息页面
	 * @return
	 * @throws Exception
	 */
	public MeetingReturnSysBean OrgIndex() throws Exception{
		List parameterList = new ArrayList();
		
		MeetingDetailListSysBean MDLSBean = new MeetingDetailListSysBean();
		MDLSBean.setType(1);
//		MDLSBean.setValue(orgCode);
		
		parameterList.add(MDLSBean);
		
		MeetingDetailSysBean MDSBean = new MeetingDetailSysBean();
		MDSBean.setParameterLists(true);
		MDSBean.setParameters(true);
		MDSBean.setParameterList(parameterList);
		MDSBean.setUsersign(false);
		MDSBean.setOrgrolesign(false);
		
		MeetingSysBean MSBean = new MeetingSysBean();
		MSBean.setAction("getOrganizationAll");
		MSBean.setService("siteadmin");
		MSBean.setType("xml");
		MSBean.setSiteName("box");
		MSBean.setUserName("admin");
		MSBean.setPassword("admin");
		MSBean.setMDSBean(MDSBean);
		
		JavaBeanToXMLSys JBeanToXMLSys = new JavaBeanToXMLSys();
		String meetingxml = JBeanToXMLSys.BeanToXMLSys(MSBean);
		System.err.println("meetingxml:"+meetingxml);
		
		MeetingInterface MeetingInterface = new MeetingInterface();
		HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
		
		String returnxml = HttpRequester.base64Decode(hr.getContent());
		System.err.println("returnxml:"+returnxml);
		
		XMLToSys XMLToSys = new XMLToSys();
		MeetingReturnSysBean mrsbean = XMLToSys.MeetingListXMLToValue(returnxml);
		
		return mrsbean;
	}
	
	/**
	 * 新增组织
	 * @param meetingdetailsysBean
	 * @throws Exception
	 */
	public Map AddOrg(MeetingDetailSysBean meetingdetailsysBean) throws Exception{
		
		MeetingDetailSysBean MDSBean = new MeetingDetailSysBean();
		MDSBean.setCode(meetingdetailsysBean.getCode());
		MDSBean.setName(meetingdetailsysBean.getName());
		MDSBean.setLinkman(meetingdetailsysBean.getLinkman());
		MDSBean.setPrincipal(meetingdetailsysBean.getPrincipal());
		MDSBean.setPhone(meetingdetailsysBean.getPhone());
		MDSBean.setEmail(meetingdetailsysBean.getEmail());
		MDSBean.setParentOrgId(meetingdetailsysBean.getParentOrgId());
		MDSBean.setAddress(meetingdetailsysBean.getAddress());
		MDSBean.setPostcode(meetingdetailsysBean.getPostcode());
		MDSBean.setDescription(meetingdetailsysBean.getDescription());
		MDSBean.setParameterLists(false);
		MDSBean.setParameters(false);
		MDSBean.setUsersign(false);
		MDSBean.setOrgrolesign(false);
		
		MeetingSysBean MSBean = new MeetingSysBean();
		MSBean.setAction("createOrg");
		MSBean.setService("siteadmin");
		MSBean.setType("xml");
		MSBean.setSiteName("box");
		MSBean.setUserName("admin");
		MSBean.setPassword("admin");
		MSBean.setMDSBean(MDSBean);
		
		JavaBeanToXMLSys JBeanToXMLSys = new JavaBeanToXMLSys();
		String meetingxml = JBeanToXMLSys.BeanToXMLSys(MSBean);
		
		System.err.println("meetingxml:"+meetingxml);
		
		MeetingInterface MeetingInterface = new MeetingInterface();
		HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
		
		String returnxml = HttpRequester.base64Decode(hr.getContent());
		System.err.println("returnxml:"+returnxml);
		
		String result = returnxml.substring(returnxml.indexOf("<result>")+8,returnxml.indexOf("</result>"));
		Map map = new HashMap();
		if("SUCCESS".equals(result)){
			map.put("result", "SUCCESS");
			map.put("reason", returnxml.substring(returnxml.indexOf("<id>")+4,returnxml.indexOf("</id>")));
		}else if("FAILURE".equals(result)){
			map.put("result", "FAILURE");
			map.put("reason", returnxml.substring(returnxml.indexOf("<reason>")+8,returnxml.indexOf("</reason>")));
		}
		return map;
	}
	
	/**
	 * 查看组织详细信息
	 * @param orgid
	 * @return
	 * @throws Exception
	 */
	public MeetingReturnSysBean ListUpdOrg(String orgid) throws Exception{
		List parameterList = new ArrayList();
		
		MeetingDetailListSysBean MDLSBean = new MeetingDetailListSysBean();
		MDLSBean.setType(1);
		MDLSBean.setValue(orgid);
		
		parameterList.add(MDLSBean);
		
		MeetingDetailSysBean MDSBean = new MeetingDetailSysBean();
		MDSBean.setParameterLists(true);
		MDSBean.setParameters(true);
		MDSBean.setParameterList(parameterList);
		MDSBean.setUsersign(false);
		MDSBean.setOrgrolesign(false);
		
		MeetingSysBean MSBean = new MeetingSysBean();
		MSBean.setAction("getOrganizationBatch");
		MSBean.setService("siteadmin");
		MSBean.setType("xml");
		MSBean.setSiteName("box");
		MSBean.setUserName("admin");
		MSBean.setPassword("admin");
		MSBean.setMDSBean(MDSBean);
		
		JavaBeanToXMLSys JBeanToXMLSys = new JavaBeanToXMLSys();
		String meetingxml = JBeanToXMLSys.BeanToXMLSys(MSBean);
		
		System.err.println("meetingxml:"+meetingxml);
		
		MeetingInterface MeetingInterface = new MeetingInterface();
		HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
		
		String returnxml = HttpRequester.base64Decode(hr.getContent());
		System.err.println("returnxml:"+returnxml);
		
		XMLToSys XMLToSys = new XMLToSys();
		List list = new ArrayList();
        list.add(RESULT);
        list.add(EXCEPTIONID);
        list.add(REASON);
        list.add(ORGNAME);
        list.add(ORGCODE);
        list.add(ORGID);
        list.add(RPARENTORGNAME);
        list.add(RPHONE);
        list.add(REMAIL);
        list.add(RPARENTORGID);
        list.add(RADDRESS);
        list.add(RPOSTCODE);
        list.add(RDESCRIPTION);
        list.add(RLINKMAN);
        list.add(RPRINCIPAL);
		MeetingReturnSysBean mrsbean = XMLToSys.XMLToValue(returnxml,list);
		
		return mrsbean;
	}
	
	public void UpdOrg(MeetingDetailSysBean meetingdetailsysBean) throws Exception{
		MeetingDetailSysBean MDSBean = new MeetingDetailSysBean();
		MDSBean.setOrgId(meetingdetailsysBean.getOrgId());
		MDSBean.setCode(meetingdetailsysBean.getCode());
		MDSBean.setName(meetingdetailsysBean.getName());
		MDSBean.setLinkman(meetingdetailsysBean.getLinkman());
		MDSBean.setPrincipal(meetingdetailsysBean.getPrincipal());
		MDSBean.setPhone(meetingdetailsysBean.getPhone());
		MDSBean.setEmail(meetingdetailsysBean.getEmail());
		MDSBean.setParentOrgId(meetingdetailsysBean.getParentOrgId());
		MDSBean.setAddress(meetingdetailsysBean.getAddress());
		MDSBean.setPostcode(meetingdetailsysBean.getPostcode());
		MDSBean.setDescription(meetingdetailsysBean.getDescription());
		MDSBean.setParameterLists(false);
		MDSBean.setParameters(false);
		MDSBean.setUsersign(false);
		MDSBean.setOrgrolesign(false);
		
		MeetingSysBean MSBean = new MeetingSysBean();
		MSBean.setAction("updateOrg");
		MSBean.setService("siteadmin");
		MSBean.setType("xml");
		MSBean.setSiteName("box");
		MSBean.setUserName("admin");
		MSBean.setPassword("admin");
		MSBean.setMDSBean(MDSBean);
		
		JavaBeanToXMLSys JBeanToXMLSys = new JavaBeanToXMLSys();
		String meetingxml = JBeanToXMLSys.BeanToXMLSys(MSBean);
		
		System.err.println("meetingxml:"+meetingxml);
		
		MeetingInterface MeetingInterface = new MeetingInterface();
		HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
		
		String returnxml = HttpRequester.base64Decode(hr.getContent());
		System.err.println("returnxml:"+returnxml);
	}
	
	public void ListDelOrg(String orgId) throws Exception{
		List parameterList = new ArrayList();
		
		MeetingDetailListSysBean MDLSBean = new MeetingDetailListSysBean();
		MDLSBean.setType(1);
		MDLSBean.setValue(orgId);
		
		parameterList.add(MDLSBean);
		
		MeetingDetailSysBean MDSBean = new MeetingDetailSysBean();
		MDSBean.setParameterLists(true);
		MDSBean.setParameters(false);
		MDSBean.setParameterList(parameterList);
		MDSBean.setUsersign(false);
		MDSBean.setOrgrolesign(false);
		
		MeetingSysBean MSBean = new MeetingSysBean();
		MSBean.setAction("delOrg");
		MSBean.setService("siteadmin");
		MSBean.setType("xml");
		MSBean.setSiteName("box");
		MSBean.setUserName("admin");
		MSBean.setPassword("admin");
		MSBean.setMDSBean(MDSBean);
		
		JavaBeanToXMLSys JBeanToXMLSys = new JavaBeanToXMLSys();
		String meetingxml = JBeanToXMLSys.BeanToXMLSys(MSBean);
		
		System.err.println("meetingxml:"+meetingxml);
		
		MeetingInterface MeetingInterface = new MeetingInterface();
		HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
		
		String returnxml = HttpRequester.base64Decode(hr.getContent());
		System.err.println("returnxml:"+returnxml);
	}
	
	public Map ListOrgRole(String orgId) throws Exception{
		String roles = "";
		roles += "1,";
		roles += "2,";
		roles += "3,";
		roles += "4,";
		roles += "6,";
		roles += "24000,";
		roles += "1a1c94ddbc5e4628bc1783e21d9d2dfd,";
		MeetingDetailSysBean MDSBean = new MeetingDetailSysBean();
		MDSBean.setId(orgId);
		MDSBean.setSite_id(0);
		MDSBean.setParentorg("");
		MDSBean.setParentorgName("");
		MDSBean.setOrgCode("");
		MDSBean.setOrgName("");
		MDSBean.setTelephone("");
		MDSBean.setEmail("");
		MDSBean.setDutyuser(0);
		MDSBean.setLinkuser(0);
		MDSBean.setAddress("");
		MDSBean.setPostcode("");
		MDSBean.setDescription("");
		MDSBean.setCreator("");
		MDSBean.setCreateTime("");
		MDSBean.setModifier("");
		MDSBean.setModifyTime("");
		MDSBean.setServerInfoId(0);
		MDSBean.setServerName("");
		MDSBean.setServerNickname("");
		MDSBean.setChecked(false);
		MDSBean.setOrgSequence(0);
		MDSBean.setRoles(roles);
		MDSBean.setParameterLists(false);
		MDSBean.setParameters(false);
		MDSBean.setUsersign(false);
		MDSBean.setOrgrolesign(true);
		
		MeetingSysBean MSBean = new MeetingSysBean();
		MSBean.setAction("updateOrgAndRole_touch");
		MSBean.setService("siteadmin");
		MSBean.setType("xml");
		MSBean.setSiteName("box");
		MSBean.setUserName("admin");
		MSBean.setPassword("admin");
		MSBean.setMDSBean(MDSBean);
		
		JavaBeanToXMLSys JBeanToXMLSys = new JavaBeanToXMLSys();
		String meetingxml = JBeanToXMLSys.BeanToXMLSys(MSBean);
		
		System.err.println("meetingxml:"+meetingxml);
		
		MeetingInterface MeetingInterface = new MeetingInterface();
		HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
		
		String returnxml = HttpRequester.base64Decode(hr.getContent());
		System.err.println("returnxml:"+returnxml);
		
		String result = returnxml.substring(returnxml.indexOf("<result>")+8,returnxml.indexOf("</result>"));
		Map map = new HashMap();
		if("SUCCESS".equals(result)){
			map.put("result", "SUCCESS");
			map.put("reason", returnxml.substring(returnxml.indexOf("<id>")+4,returnxml.indexOf("</id>")));
		}else if("FAILURE".equals(result)){
			map.put("result", "FAILURE");
			map.put("reason", returnxml.substring(returnxml.indexOf("<reason>")+8,returnxml.indexOf("</reason>")));
		}
		
		return map;
	}
	
	public MeetingReturnSysBean MeetUserAll() throws Exception{
		List parameterList = new ArrayList();
		
		MeetingDetailListSysBean MDLSBean = new MeetingDetailListSysBean();
//		MDLSBean.setType("");
//		MDLSBean.setValue("");
		
		parameterList.add(MDLSBean);
		
		MeetingDetailSysBean MDSBean = new MeetingDetailSysBean();
		MDSBean.setParameterLists(true);
		MDSBean.setParameters(true);
		MDSBean.setParameterList(parameterList);
		MDSBean.setUsersign(false);
		MDSBean.setOrgrolesign(false);
		
		MeetingSysBean MSBean = new MeetingSysBean();
		MSBean.setAction("getCemUserAll_webtouch");
		MSBean.setService("siteadmin");
		MSBean.setType("xml");
		MSBean.setSiteName("box");
		MSBean.setUserName("admin");
		MSBean.setPassword("admin");
		MSBean.setMDSBean(MDSBean);
		
		JavaBeanToXMLSys JBeanToXMLSys = new JavaBeanToXMLSys();
		String meetingxml = JBeanToXMLSys.BeanToXMLSys(MSBean);
		
		System.err.println("meetingxml:"+meetingxml);
		
		MeetingInterface MeetingInterface = new MeetingInterface();
		HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
		
		String returnxml = HttpRequester.base64Decode(hr.getContent());
		System.err.println("returnxml:"+returnxml);
		
		XMLToSys XMLToSys = new XMLToSys();
		MeetingReturnSysBean mrsbean = XMLToSys.UserListXMLToValue(returnxml);
		
		return mrsbean;
	}
	
	public Map AddUser(MeetingDetailSysBean meetingdetailsysBean,UserBean suserBean) throws Exception{
		List listgroup = new ArrayList();
		List listrole = new ArrayList();
		
		/**
		 * 根据输入的中文名转换成对应的英文字母
		 */
		CountryCode countrycode = new CountryCode();
		String country = countrycode.getKey(meetingdetailsysBean.getCountry());
		String province = countrycode.getKey(meetingdetailsysBean.getProvince());
		String city = countrycode.getKey(meetingdetailsysBean.getCity());
		
		MeetingDetailSysBean MDSBean = new MeetingDetailSysBean();
		MDSBean.setUserName(meetingdetailsysBean.getUserName());//登录名
		MDSBean.setNickname(meetingdetailsysBean.getNickname());
		MDSBean.setPassword(meetingdetailsysBean.getPassword());
		MDSBean.setFirstName(meetingdetailsysBean.getFirstName());//姓名
		MDSBean.setLastName("");
		MDSBean.setGender(meetingdetailsysBean.getGender());//性别 男：1 女：0 保密：2
		MDSBean.setCompany(meetingdetailsysBean.getCompany());
		MDSBean.setDeptId(meetingdetailsysBean.getDeptId());//组织id
		MDSBean.setGroupIds(listgroup);
		MDSBean.setRoleIds(listrole);
		MDSBean.setDuty(meetingdetailsysBean.getDuty());
		MDSBean.setReportTo(meetingdetailsysBean.getReportTo());//汇报对象用户id
		MDSBean.setUserType(meetingdetailsysBean.getUserType());//用户类型（企业内部用户：1、代理商用户：2、供应商用户：3、客户用户：4）
		MDSBean.setExtension(meetingdetailsysBean.getExtension());
		MDSBean.setEimUsed(false);//是否应用于Eim
		MDSBean.setEasycallUsed(false);//是否应用于easycall
		MDSBean.setEmail(meetingdetailsysBean.getEmail());
		MDSBean.setOtherEmail(meetingdetailsysBean.getOtherEmail());
		MDSBean.setOfficePhone(meetingdetailsysBean.getOfficePhone());
		MDSBean.setOtherPhone(meetingdetailsysBean.getOtherPhone());
		MDSBean.setCellphone(meetingdetailsysBean.getCellphone());
		MDSBean.setFax(meetingdetailsysBean.getFax());
		MDSBean.setCountry(country);//国家
		MDSBean.setProvince(province);//省
		MDSBean.setCity(city);//城市
		MDSBean.setAddress(meetingdetailsysBean.getAddress());
		MDSBean.setPostcode(meetingdetailsysBean.getPostcode());
		MDSBean.setOtherInfo("");
		MDSBean.setEnabled(true);//激活状态
		MDSBean.setForceCreate(false);//是否强制创建，true：如果同名被删除，则还原帐户；false：不还原
		MDSBean.setParameterLists(false);
		MDSBean.setParameters(false);
		MDSBean.setUsersign(true);
		MDSBean.setOrgrolesign(false);
		
		MeetingSysBean MSBean = new MeetingSysBean();
		MSBean.setAction("createCemUser");
		MSBean.setService("siteadmin");
		MSBean.setType("xml");
		MSBean.setSiteName("box");
		MSBean.setUserName(suserBean.getLogname());
		MSBean.setPassword(suserBean.getPassword());
		MSBean.setMDSBean(MDSBean);
		
		JavaBeanToXMLSys JBeanToXMLSys = new JavaBeanToXMLSys();
		String meetingxml = JBeanToXMLSys.BeanToXMLSys(MSBean);
		
		System.err.println("meetingxml:"+meetingxml);
		
		MeetingInterface MeetingInterface = new MeetingInterface();
		HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
		
		String returnxml = HttpRequester.base64Decode(hr.getContent());
		System.err.println("returnxml:"+returnxml);
		
		String result = returnxml.substring(returnxml.indexOf("<result>")+8,returnxml.indexOf("</result>"));
		Map map = new HashMap();
		if("SUCCESS".equals(result)){
			map.put("result", "SUCCESS");
			map.put("reason", returnxml.substring(returnxml.indexOf("<userId>")+8,returnxml.indexOf("</userId>")));
		}else if("FAILURE".equals(result)){
			map.put("result", "FAILURE");
			map.put("reason", returnxml.substring(returnxml.indexOf("<reason>")+8,returnxml.indexOf("</reason>")));
		}
		return map;
	}
	
	public MeetingReturnSysBean ListUpdUser(String userid) throws Exception{
		List parameterList = new ArrayList();
		
		MeetingDetailListSysBean MDLSBean = new MeetingDetailListSysBean();
		MDLSBean.setType(2);
		MDLSBean.setValue(userid);
		
		parameterList.add(MDLSBean);
		
		MeetingDetailSysBean MDSBean = new MeetingDetailSysBean();
		MDSBean.setParameterLists(true);
		MDSBean.setParameters(true);
		MDSBean.setParameterList(parameterList);
		MDSBean.setUsersign(false);
		MDSBean.setOrgrolesign(false);
		
		MeetingSysBean MSBean = new MeetingSysBean();
		MSBean.setAction("getCemUserBatch");
		MSBean.setService("siteadmin");
		MSBean.setType("xml");
		MSBean.setSiteName("box");
		MSBean.setUserName("admin");
		MSBean.setPassword("admin");
		MSBean.setMDSBean(MDSBean);
		
		JavaBeanToXMLSys JBeanToXMLSys = new JavaBeanToXMLSys();
		String meetingxml = JBeanToXMLSys.BeanToXMLSys(MSBean);
		
		System.err.println("meetingxml:"+meetingxml);
		
		MeetingInterface MeetingInterface = new MeetingInterface();
		HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
		
		String returnxml = HttpRequester.base64Decode(hr.getContent());
		System.err.println("returnxml:"+returnxml);
		
		XMLToSys XMLToSys = new XMLToSys();
		List list = new ArrayList();
        list.add(RESULT);
        list.add(EXCEPTIONID);
        list.add(REASON);
        list.add(RACTIVEDATE);
        list.add(RADDRESS);
        list.add(CELLPHONE);
        list.add(CITY);
        list.add(COMPANY);
        list.add(COUNTRY);
        list.add(DEPTID);
        list.add(DUTY);
        list.add(EASYCALLUSED);
        list.add(EIMUSED);
        list.add(REMAIL);
        list.add(ENABLED);
        list.add(EXTENSION);
        list.add(FAX);
        list.add(FIRSTNAME);
        list.add(GENDER);
        list.add(ID);
        list.add(INACTIVEDATE);
        list.add(LASTNAME);
        list.add(NICKNAME);
        list.add(OFFICEPHONE);
        list.add(OTHEREMAIL);
        list.add(OTHERINFO);
        list.add(OTHERPHONE);
        list.add(RPASSWORD);
        list.add(UPOSTCODE);
        list.add(PROVINCE);
        list.add(REPORTTO);
        list.add(SITEID);
        list.add(RUSERNAME);
        list.add(USERTYPE);
		MeetingReturnSysBean mrsbean = XMLToSys.XMLToValue(returnxml,list);
		
		/**
		 * 根据输入的中文名转换成对应的英文字母
		 */
		CountryCode countrycode = new CountryCode();
		String country = countrycode.getValue(mrsbean.getCountry());
		String province = countrycode.getValue(mrsbean.getProvince());
		String city = countrycode.getValue(mrsbean.getCity());
		
		mrsbean.setCountry(country);
		mrsbean.setProvince(province);
		mrsbean.setCity(city);
		
		return mrsbean;
	}
	
	public Map UpdUser(MeetingDetailSysBean meetingdetailsysBean,UserBean suserBean) throws Exception{
		List listgroup = new ArrayList();
		List listrole = new ArrayList();
		
		/**
		 * 根据输入的中文名转换成对应的英文字母
		 */
		CountryCode countrycode = new CountryCode();
		String country = countrycode.getKey(meetingdetailsysBean.getCountry());
		String province = countrycode.getKey(meetingdetailsysBean.getProvince());
		String city = countrycode.getKey(meetingdetailsysBean.getCity());
		
		MeetingDetailSysBean MDSBean = new MeetingDetailSysBean();
		MDSBean.setUserName(meetingdetailsysBean.getUserName());//登录名
		MDSBean.setNickname(meetingdetailsysBean.getNickname());
		MDSBean.setPassword(meetingdetailsysBean.getPassword());
		MDSBean.setFirstName(meetingdetailsysBean.getFirstName());//姓名
		MDSBean.setLastName(meetingdetailsysBean.getLastName());
		MDSBean.setGender(meetingdetailsysBean.getGender());//性别 男：1 女：0 保密：2
		MDSBean.setCompany(meetingdetailsysBean.getCompany());
		MDSBean.setDeptId(meetingdetailsysBean.getDeptId());
		MDSBean.setGroupIds(listgroup);
		MDSBean.setRoleIds(listrole);
		MDSBean.setDuty(meetingdetailsysBean.getDuty());
		MDSBean.setReportTo(meetingdetailsysBean.getReportTo());//汇报对象用户id
		MDSBean.setUserType(meetingdetailsysBean.getUserType());//用户类型（企业内部用户：1、代理商用户：2、供应商用户：3、客户用户：4）
		MDSBean.setExtension(meetingdetailsysBean.getExtension());
		MDSBean.setEimUsed(meetingdetailsysBean.getEimUsed());//是否应用于Eim
		MDSBean.setEasycallUsed(meetingdetailsysBean.getEasycallUsed());//是否应用于easycall
		MDSBean.setEmail(meetingdetailsysBean.getEmail());
		MDSBean.setOtherEmail(meetingdetailsysBean.getOtherEmail());
		MDSBean.setOfficePhone(meetingdetailsysBean.getOfficePhone());
		MDSBean.setOtherPhone(meetingdetailsysBean.getOtherPhone());
		MDSBean.setCellphone(meetingdetailsysBean.getCellphone());
		MDSBean.setFax(meetingdetailsysBean.getFax());
		MDSBean.setCountry(country);//国家
		MDSBean.setProvince(province);//省
		MDSBean.setCity(city);//城市
		MDSBean.setAddress(meetingdetailsysBean.getAddress());
		MDSBean.setPostcode(meetingdetailsysBean.getPostcode());
		MDSBean.setOtherInfo("");
		MDSBean.setEnabled(meetingdetailsysBean.getEnabled());//激活状态
		MDSBean.setParameterLists(false);
		MDSBean.setParameters(false);
		MDSBean.setUsersign(true);
		MDSBean.setOrgrolesign(false);
		
		MeetingSysBean MSBean = new MeetingSysBean();
		MSBean.setAction("updateCemUser");
		MSBean.setService("siteadmin");
		MSBean.setType("xml");
		MSBean.setSiteName("box");
		MSBean.setUserName(suserBean.getLogname());
		MSBean.setPassword(suserBean.getPassword());
		MSBean.setMDSBean(MDSBean);
		
		JavaBeanToXMLSys JBeanToXMLSys = new JavaBeanToXMLSys();
		String meetingxml = JBeanToXMLSys.BeanToXMLSys(MSBean);
		
		System.err.println("meetingxml:"+meetingxml);
		
		MeetingInterface MeetingInterface = new MeetingInterface();
		HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
		
		String returnxml = HttpRequester.base64Decode(hr.getContent());
		System.err.println("returnxml:"+returnxml);
		
		String result = returnxml.substring(returnxml.indexOf("<result>")+8,returnxml.indexOf("</result>"));
		Map map = new HashMap();
		if("SUCCESS".equals(result)){
			map.put("result", "SUCCESS");
			map.put("reason", returnxml.substring(returnxml.indexOf("<userId>")+8,returnxml.indexOf("</userId>")));
		}else if("FAILURE".equals(result)){
			map.put("result", "FAILURE");
			map.put("reason", returnxml.substring(returnxml.indexOf("<reason>")+8,returnxml.indexOf("</reason>")));
		}
		return map;
	}
	
	public Map ListDelUser(String userid,UserBean suserBean) throws Exception{
		List parameterList = new ArrayList();
		
		MeetingDetailListSysBean MDLSBean = new MeetingDetailListSysBean();
		MDLSBean.setType(2);
		MDLSBean.setValue(userid);
		
		parameterList.add(MDLSBean);
		
		MeetingDetailSysBean MDSBean = new MeetingDetailSysBean();
		MDSBean.setParameterLists(true);
		MDSBean.setParameters(false);
		MDSBean.setParameterList(parameterList);
		MDSBean.setUsersign(false);
		MDSBean.setOrgrolesign(false);
		
		MeetingSysBean MSBean = new MeetingSysBean();
		MSBean.setAction("delCemUser");
		MSBean.setService("siteadmin");
		MSBean.setType("xml");
		MSBean.setSiteName("box");
		MSBean.setUserName(suserBean.getLogname());
		MSBean.setPassword(suserBean.getPassword());
		MSBean.setMDSBean(MDSBean);
		
		JavaBeanToXMLSys JBeanToXMLSys = new JavaBeanToXMLSys();
		String meetingxml = JBeanToXMLSys.BeanToXMLSys(MSBean);
		
		System.err.println("meetingxml:"+meetingxml);
		
		MeetingInterface MeetingInterface = new MeetingInterface();
		HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);	
		
		String returnxml = HttpRequester.base64Decode(hr.getContent());
		System.err.println("returnxml:"+returnxml);
		
		String result = returnxml.substring(returnxml.indexOf("<result>")+8,returnxml.indexOf("</result>"));
		Map map = new HashMap();
		if("SUCCESS".equals(result)){
			map.put("result", "SUCCESS");
			map.put("reason", returnxml.substring(returnxml.indexOf("<userId>")+8,returnxml.indexOf("</userId>")));
		}else if("FAILURE".equals(result)){
			map.put("result", "FAILURE");
			map.put("reason", returnxml.substring(returnxml.indexOf("<reason>")+8,returnxml.indexOf("</reason>")));
		}
		return map;
	}
}
