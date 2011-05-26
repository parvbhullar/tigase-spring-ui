package com.ivyinfo.organization.util;

import com.ivyinfo.framework.common.file.ResouceLoader;
import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.common.tools.RandomUtil;
import com.ivyinfo.framework.service.sequence.ISequence;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.framework.service.servlet.HttpRequester;
import com.ivyinfo.mail.bean.MailUtilBean;
import com.ivyinfo.mail.bean.SetupMailBean;
import com.ivyinfo.mail.services.SendMailServices;
import com.ivyinfo.organization.bean.OrganizationBean;
import com.ivyinfo.organization.bean.OrganizationValidationBean;
import com.ivyinfo.organization.services.OrganizationServices;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.user.services.UserServices;

public class OrganizationUtil {
	
	/**
	 * 验证机构
	 * @param id
	 * @throws Exception
	 */
	public void OrgValidation(int id) throws Exception{
		//根据id查询机构验证表详细信息
		OrganizationServices organizationServices = (OrganizationServices) SpringContextUtil.getBean("organizationServices");
		OrganizationValidationBean orgValidationBean = organizationServices.ViewOrgValidation(id);
		
		//获得服务器地址和默认发送邮箱地址
		ResouceLoader resouceloader = new ResouceLoader();
		String serviceURL = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.serviceurl");
		String mailaddress = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.service.mail.address");
		String mailname = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.service.mail.name");
		String mailpassword = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.service.mail.password");
		
		//获得随机生成的10位数
		RandomUtil randomUtil = new RandomUtil();
    	String random = randomUtil.generateString(10);
    	
    	//发送邮件
    	SendMailServices sendMailServices = (SendMailServices) SpringContextUtil.getBean("sendMailServices");
    	MailUtilBean mailUtilBean = new MailUtilBean();
    	HttpRequester request = new HttpRequester();
    	//加密
    	String orgid = request.base64Encode(orgValidationBean.getId());
    	String random64 = request.base64Encode(random);
    	//验证路径
    	String validationURL = serviceURL+"?oi="+orgid+"key="+random64;
    	//邮件内容
    	String content = "<DIV>尊敬的 "+orgValidationBean.getName()+"<BR></DIV>";
    	content += "<DIV>以下是您的注册信息：</DIV>";
    	content += "<DIV>----------------------------------------</DIV>";
    	content += "<DIV>机构名称："+orgValidationBean.getName()+"</DIV>";
    	content += "<DIV>机构登录名："+orgValidationBean.getLogname()+"</DIV>";
    	content += "<DIV>机构登陆密码："+orgValidationBean.getLogpassword()+"</DIV>";
    	content += "<DIV><BR>您已成功注册。要完成该注册，我们需验证该注册信息的有效性。</DIV>";
    	content += "<DIV>&nbsp;</DIV>";
    	content += "<DIV>您只需点击下方链接即可完成注册验证。</DIV>";
    	content += "<DIV><BR><A style='COLOR: #0088cc' href=''>立即验证 &gt;</A></DIV>";
    	content += "<DIV>&nbsp;</DIV>";
    	content += "<DIV>10天内不验证注册信息，验证将失效。</DIV>";
    	content += "<DIV>&nbsp;</DIV>";
    	content += "<DIV>此邮件为系统自动发送，请勿回复。</DIV>";
    	content += "<DIV><BR>谢谢！</DIV>";
    	//信息放入邮件bean中
    	mailUtilBean.setFrom(mailaddress);//发件人
    	mailUtilBean.setToname(orgValidationBean.getEmail());//收件人
    	mailUtilBean.setUser(mailname);
    	mailUtilBean.setPassword(mailpassword);
    	mailUtilBean.setSubject(orgValidationBean.getName()+"邮件验证信息");
    	mailUtilBean.setContent(content);
    	//发送邮件
    	String sendmail = sendMailServices.SendMail(mailUtilBean);
    	System.err.println("sendmail:"+sendmail);
    	
    	//修改信息
    	TimeTools TimeTools = new TimeTools();
    	orgValidationBean.setValidationcode(random);
    	orgValidationBean.setValidationtime(TimeTools.getCurrentTime());
    	organizationServices.UpdOrgValidation(orgValidationBean);
	}
	
	/**
	 * 判断是否验证成功
	 * @param orgValidationBean
	 * @return
	 * @throws Exception
	 */
	public String ValidationOrgMail(OrganizationValidationBean orgValidationBean) throws Exception{
		String returnvalue = "";
		if(orgValidationBean != null || !"".equals(orgValidationBean.getId())){
			OrganizationServices organizationServices = (OrganizationServices) SpringContextUtil.getBean("organizationServices");
			UserServices userServices = (UserServices) SpringContextUtil.getBean("userServices");
			
			//保存到机构和用户信息表中
			OrganizationBean organizationBean = new OrganizationBean();
			organizationBean.setName(orgValidationBean.getName());
			organizationBean.setEmail(orgValidationBean.getEmail());
			organizationBean.setOfficephone(orgValidationBean.getOfficephone());
			organizationBean.setFaxnumber(orgValidationBean.getFaxnumber());
			organizationBean.setNationality(orgValidationBean.getNationality());
			organizationBean.setCity(orgValidationBean.getCity());
			organizationBean.setDetailedaddress(orgValidationBean.getDetailedaddress());
			organizationBean.setPostcode(orgValidationBean.getPostcode());
			organizationBean.setRemark("");
			organizationBean.setProvince(orgValidationBean.getProvince());
			
			UserBean userBean = new UserBean();
			userBean.setLogname(orgValidationBean.getLogname());
			userBean.setPassword(orgValidationBean.getLogpassword());
			userBean.setNickname(orgValidationBean.getLogname());
			userBean.setName(orgValidationBean.getName()+"管理员");
			userBean.setEmail(orgValidationBean.getEmail());
			userBean.setType("1");
			userBean.setState("2");
			userBean.setUpduserid("1");
			userBean.setTimestemp(TimeTools.getString());
			userBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
			
			ISequence sequenceService = (ISequence) SpringContextUtil.getBean("Sequence");
			
			long id = sequenceService.getMaxId("t_sys_organization");
			long contactid = sequenceService.getMaxId("t_sys_organization_contact");
			organizationBean.setId(String.valueOf(id));
			organizationBean.setContactid(String.valueOf(contactid));
			organizationBean.setOrganizationid(String.valueOf(id));
			
			organizationBean.setUpduserid("1");
			organizationBean.setTimestemp(TimeTools.getString());
			organizationBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
			
			long userloginid = sequenceService.getMaxId("t_sys_user_login");
			long userbasicid = sequenceService.getMaxId("t_sys_user_basic");
			long usercontactid = sequenceService.getMaxId("t_sys_user_contact");
			long userphotoid = sequenceService.getMaxId("t_sys_user_photo");
			
			userBean.setOrganizationid(String.valueOf(id));
			userBean.setId(String.valueOf(userloginid));
			userBean.setBasicid(String.valueOf(userbasicid));
			userBean.setContactid(String.valueOf(usercontactid));
			userBean.setPhotoid(String.valueOf(userphotoid));
			userBean.setUserid(String.valueOf(userloginid));
			
			//个人邮箱设置
			SetupMailBean setupmailBean = new SetupMailBean();
			long usermailid = sequenceService.getMaxId(orgValidationBean.getLogname()+"_t_mail_setup");
			setupmailBean.setId(String.valueOf(usermailid));
			setupmailBean.setAddname(orgValidationBean.getEmail());
			setupmailBean.setTimestemp(TimeTools.getString());
			setupmailBean.setUserlogname(orgValidationBean.getLogname());
			
			//新增机构
			String meetorgid = organizationServices.AddSubmit(organizationBean);
			organizationServices.AddSubmitContact(organizationBean);
			
			//新增用户
			UserBean suserBean = new UserBean();
			suserBean.setLogname("admin");
			suserBean.setPassword("admin");
			userServices.AddSubmit(userBean,suserBean,setupmailBean,meetorgid);
			userServices.AddSubmitBasic(userBean);
			userServices.AddSubmitContact(userBean);
			userServices.AddSubmitPhoto(userBean);
			
			//删除机构验证表信息
			organizationServices.DelOrgValidation(Integer.parseInt(orgValidationBean.getId()));
			
			returnvalue = "SUCCESS";
		}else{
			returnvalue = "FAILURE";
		}
		return returnvalue;
	}
}
