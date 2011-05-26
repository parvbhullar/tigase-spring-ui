package com.ivyinfo.webtouch.conversion;

import java.util.Map;

import com.ivyinfo.framework.common.file.ResouceLoader;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.generic.country.CountryCode;
import com.ivyinfo.meeting.bean.MeetingDetailSysBean;
import com.ivyinfo.meeting.services.MeetingSysServices;
import com.ivyinfo.organization.bean.OrganizationBean;
import com.ivyinfo.purview.bean.PurviewBean;
import com.ivyinfo.user.bean.FtpUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.user.services.FtpUserServices;

public class UserConversionImpl implements UserConversion{
	
	/**
	 * 创建会议系统用户
	 */
	public Map MeetAddUser(UserBean userBean,UserBean suserBean,String meetorgid) throws Exception{
		MeetingSysServices meetingsysServices = (MeetingSysServices) SpringContextUtil.getBean("meetingsysServices");
		/**
		 * 根据输入的英文字母转换成对应的中文名
		 */
		CountryCode countrycode = new CountryCode();
		String country = countrycode.getValue(userBean.getNationality());
		String province = countrycode.getValue(userBean.getProvince());
		String city = countrycode.getValue(userBean.getCity());
		
		MeetingDetailSysBean MDSBean = new MeetingDetailSysBean();
		MDSBean.setUserName(userBean.getLogname());//登录名
		MDSBean.setNickname(userBean.getNickname());
		MDSBean.setPassword(userBean.getPassword());
		MDSBean.setFirstName(userBean.getName());//姓名
		String sex = userBean.getSex();
		if(userBean.getSex() == null){
			sex = "";
		}
		System.err.println("sex:"+sex);
		String gender = "";
		if("0".equals(sex)){
			gender = "2";
		}else if("1".equals(sex)){
			gender = "1";
		}else if("2".equals(sex)){
			gender = "0";
		}else if("".equals(sex)){
			gender = "0";
		}
		MDSBean.setGender(Integer.parseInt(gender));//性别 男：1 女：0 保密：2
		MDSBean.setCompany("");
		MDSBean.setDuty(userBean.getJob());
		MDSBean.setReportTo(0);//汇报对象用户id
		
		if(userBean.getUsersort()== null || userBean.getUsersort().equals("")){
			userBean.setUsersort("1");
		}
		MDSBean.setUserType(Integer.parseInt(userBean.getUsersort()));//用户类型（企业内部用户：1、代理商用户：2、供应商用户：3、客户用户：4）
		MDSBean.setExtension("");
		MDSBean.setEmail(userBean.getEmail());
		MDSBean.setOtherEmail(userBean.getOtheremail());
		MDSBean.setOfficePhone(userBean.getOfficephone());
		MDSBean.setOtherPhone(userBean.getOtherphone());
		MDSBean.setCellphone(userBean.getMobilephone());
		MDSBean.setFax(userBean.getFaxnumber());
		MDSBean.setCountry(country);//国家
		MDSBean.setProvince(province);//省
		MDSBean.setCity(city);//城市
		MDSBean.setAddress(userBean.getDetailedaddress());
		MDSBean.setPostcode(userBean.getPostcode());
		MDSBean.setDeptId(meetorgid);
		
		Map map = meetingsysServices.AddUser(MDSBean,suserBean);
		
//		meetingsysServices.ListOrgRole("1");
		
		return map;
	}
	
	public Map MeetUpdUser(UserBean userBean,UserBean suserBean,PurviewBean purviewBean) throws Exception{
		MeetingSysServices meetingsysServices = (MeetingSysServices) SpringContextUtil.getBean("meetingsysServices");
		/**
		 * 根据输入的英文字母转换成对应的中文名
		 */
		CountryCode countrycode = new CountryCode();
		String country = countrycode.getValue(userBean.getNationality());
		String province = countrycode.getValue(userBean.getProvince());
		String city = countrycode.getValue(userBean.getCity());
		
		MeetingDetailSysBean MDSBean = new MeetingDetailSysBean();
		MDSBean.setUserName(userBean.getLogname());//登录名
		MDSBean.setNickname(userBean.getNickname());
		MDSBean.setPassword(userBean.getPassword());
		MDSBean.setFirstName(userBean.getName());//姓名
		MDSBean.setLastName("");
		String sex = userBean.getSex();
		if(userBean.getSex() == null){
			sex = "";
		}
		System.err.println("sex:"+sex);
		String gender = "";
		if("0".equals(sex)){
			gender = "2";
		}else if("1".equals(sex)){
			gender = "1";
		}else if("2".equals(sex)){
			gender = "0";
		}else if("".equals(sex)){
			gender = "0";
		}
		MDSBean.setGender(Integer.parseInt(gender));//性别 男：1 女：0 保密：2
		MDSBean.setCompany("");
		MDSBean.setDuty(userBean.getJob());
		MDSBean.setReportTo(0);//汇报对象用户id
		
		if(userBean.getUsersort()== null || userBean.getUsersort().equals("")){
			userBean.setUsersort("1");
		}
		MDSBean.setUserType(Integer.parseInt(userBean.getUsersort()));//用户类型（企业内部用户：1、代理商用户：2、供应商用户：3、客户用户：4）
		MDSBean.setExtension(null);
		MDSBean.setEmail(userBean.getEmail());
		MDSBean.setOtherEmail(userBean.getOtheremail());
		MDSBean.setOfficePhone(userBean.getOfficephone());
		MDSBean.setOtherPhone(userBean.getOtherphone());
		MDSBean.setCellphone(userBean.getMobilephone());
		MDSBean.setFax(userBean.getFaxnumber());
		MDSBean.setCountry(country);//国家
		MDSBean.setProvince(province);//省
		MDSBean.setCity(city);//城市
		MDSBean.setAddress(userBean.getDetailedaddress());
		MDSBean.setPostcode(userBean.getPostcode());
		MDSBean.setDeptId(purviewBean.getMeetorgid());
		
		MDSBean.setEimUsed(false);//是否应用于Eim
		MDSBean.setEasycallUsed(false);
		if("2".equals(userBean.getState())){
			MDSBean.setEnabled(true);//激活状态
		}else if("3".equals(userBean.getState())){
			MDSBean.setEnabled(false);//激活状态
		}
		MDSBean.setForceCreate(false);//是否强制创建，true：如果同名被删除，则还原帐户；false：不还原
		MDSBean.setParameterLists(false);
		MDSBean.setParameters(false);
		MDSBean.setUsersign(true);
		MDSBean.setOrgrolesign(false);
		
		Map map = meetingsysServices.UpdUser(MDSBean,suserBean);
		
		return map;
	}
	
	public Map MeetDelUser(String id,UserBean suserBean) throws Exception{
		MeetingSysServices meetingsysServices = (MeetingSysServices) SpringContextUtil.getBean("meetingsysServices");
		
		Map map = meetingsysServices.ListDelUser(id,suserBean);
		
		return map;
	}
	
	/**
	 * 创建ftp用户
	 */
	public void FtpAddUser(UserBean userBean) throws Exception{
		FtpUserServices ftpUserServices = (FtpUserServices) SpringContextUtil.getBean("ftpUserServices");
		
		FtpUserBean ftpuserBean = new FtpUserBean();
		ftpuserBean.setFtpusername(userBean.getLogname());
		ftpuserBean.setFtppassword(userBean.getPassword());
		
		boolean returnvalue = ftpUserServices.CreateFtpUser(ftpuserBean);
		System.err.println("returnvalue:"+returnvalue);
	}
	
	/**
	 * 修改ftp用户
	 * @param userBean
	 * @throws Exception
	 */
	public void FtpUpdUser(UserBean userBean) throws Exception{
		FtpUserServices ftpUserServices = (FtpUserServices) SpringContextUtil.getBean("ftpUserServices");
		
		FtpUserBean ftpuserBean = new FtpUserBean();
		ftpuserBean.setFtpusername(userBean.getLogname());
		ftpuserBean.setFtppassword(userBean.getPassword());
		
		boolean returnvalue = ftpUserServices.UpdateFtpUser(ftpuserBean);
		System.err.println("returnvalue:"+returnvalue);
	}
	
	/**
	 * 删除ftp用户
	 */
	public void FtpDelUser(UserBean userBean) throws Exception{
		FtpUserServices ftpUserServices = (FtpUserServices) SpringContextUtil.getBean("ftpUserServices");
		
		FtpUserBean ftpuserBean = new FtpUserBean();
		ftpuserBean.setFtpusername(userBean.getLogname());
		ftpuserBean.setFtppassword(userBean.getPassword());
		
		boolean returnvalue = ftpUserServices.DeleteFtpUser(ftpuserBean);
		System.err.println("returnvalue:"+returnvalue);
	}
	
	public String MeetAddOrg(OrganizationBean organizationBean) throws Exception{
		MeetingSysServices meetingsysServices = (MeetingSysServices) SpringContextUtil.getBean("meetingsysServices");
		
		MeetingDetailSysBean meetingdetailsysBean = new MeetingDetailSysBean();
		String id = organizationBean.getId();
		System.err.println("id:"+id);
		int len = id.length();
		System.err.println("len:"+len);
		String code = "";
		if(len == 1){
			code = "0001000"+id;
		}else if(len == 2){
			code = "000100"+id;
		}else if(len == 3){
			code = "00010"+id;
		}else if(len == 4){
			code = "0001"+id;
		}
		meetingdetailsysBean.setCode(code);
		meetingdetailsysBean.setName(organizationBean.getName());
		meetingdetailsysBean.setLinkman("");
		meetingdetailsysBean.setPrincipal("");
		meetingdetailsysBean.setPhone("");
		meetingdetailsysBean.setEmail(organizationBean.getEmail());
		meetingdetailsysBean.setParentOrgId("1");
		meetingdetailsysBean.setAddress("");
		meetingdetailsysBean.setPostcode("");
		meetingdetailsysBean.setDescription("");
		
		Map map = meetingsysServices.AddOrg(meetingdetailsysBean);
		
		String meetorgid = "";
		if(map != null){
			String result = (String)map.get("result");
			String reason = (String)map.get("reason");
			if("SUCCESS".equals(result)){
				meetorgid = reason;
				/**
				 * 给组织机构赋角色权限
				 */
				Map orgrolemap = meetingsysServices.ListOrgRole(reason);
				String orgroleresult = (String)orgrolemap.get("result");
				String orgrolereason = (String)orgrolemap.get("reason");
				if("SUCCESS".equals(orgroleresult)){
					
				}else if("FAILURE".equals(orgroleresult)){
					throw new Exception(orgrolereason);
				}
			}else if("FAILURE".equals(result)){
				throw new Exception(reason);
			}
		}
		return meetorgid;
	}
}
