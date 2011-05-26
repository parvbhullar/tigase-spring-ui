package com.ivyinfo.purview.services;

import java.util.List;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.login.bean.LoginBean;
import com.ivyinfo.mail.bean.SetupMailBean;
import com.ivyinfo.mail.dao.AuxiliaryMailDAO;
import com.ivyinfo.organization.bean.OrganizationBean;
import com.ivyinfo.organization.dao.OrganizationDAO;
import com.ivyinfo.orgpurview.bean.OrgPurviewBean;
import com.ivyinfo.orgpurview.dao.OrgPurviewDAO;
import com.ivyinfo.purview.bean.PurviewBean;
import com.ivyinfo.purview.dao.PurviewDAO;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.user.dao.UserDAO;
import com.ivyinfo.webcall.bean.UserLoginSendBean;

public class PurviewServicesImpl implements PurviewServices{
	
	public SessionUserBean setSessionValue(String userid) throws Exception{
		OrganizationDAO organizationDAO =(OrganizationDAO) SpringContextUtil.getBean("organizationDAO");
//		DeptDAO deptDAO =(DeptDAO) SpringContextUtil.getBean("deptDAO");
//		RoleDAO roleDAO =(RoleDAO) SpringContextUtil.getBean("roleDAO");
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		OrgPurviewDAO orgpurviewDAO =(OrgPurviewDAO) SpringContextUtil.getBean("orgpurviewDAO");
		AuxiliaryMailDAO auxiliaryMailDAO =(AuxiliaryMailDAO) SpringContextUtil.getBean("auxiliaryMailDAO");
		SessionUserBean sessionUserBean = new SessionUserBean();
		PurviewBean purviewBean = new PurviewBean();
		UserBean userBean = new UserBean();
		SetupMailBean setupmailBean = new SetupMailBean();
		try{
		//用户
		userBean = userDAO.View(Integer.parseInt(userid));
		
//		setupmailBean = auxiliaryMailDAO.ViewMailSetupEmail(userBean.getEmail(), userBean.getLogname());
		
		//机构
		OrganizationBean orgBean = organizationDAO.View(Integer.parseInt(userBean.getOrganizationid()));
		//角色
		//部门
		
		//权限
		String purviews = "";
		String usertype = userBean.getType();
		/**
		 * XT01			系统设置
		 * XT0101		机构管理
		 * XT0102		用户管理
		 * XT0103		系统参数
		 * XT0104		邮箱管理
		 * XT0105		webcall管理
		 */
		if("0".equals(usertype)){//系统管理员
			purviews = "XT01,XT0101,XT0104,";
		}else if("1".equals(usertype)){//机构管理员
			purviews = "XT01,XT0102,";
		}else if("2".equals(usertype)){//普通用户
			List list = orgpurviewDAO.getOrgPurview(userBean.getOrganizationid());
			for(int i=0;i<list.size();i++){
				OrgPurviewBean orgpurviewBean = (OrgPurviewBean)list.get(i);
				
				purviews += orgpurviewBean.getPnumber() + ",";
			}
		}
		if(orgBean != null){
			purviewBean.setOrganizationid(orgBean.getId());
			purviewBean.setOrganizationname(orgBean.getName());
			purviewBean.setMeetorgid(orgBean.getMeetorgid());
		}
		purviewBean.setUserid(userBean.getId());
		purviewBean.setUsername(userBean.getName());
		purviewBean.setPurview(purviews);
		
		sessionUserBean.setUserBean(userBean);
		sessionUserBean.setPurviewBean(purviewBean);
		sessionUserBean.setSetupmailBean(setupmailBean);
		//用户的webcall卡号
		UserBean CallUserBean = userDAO.ViewCard(userid);
		String cardnumber = "";
		String cardpassword = "";
		if(CallUserBean != null){
			cardnumber = CallUserBean.getCardnumber();
			cardpassword = CallUserBean.getCardpassword();
		}
		//硬编码session中塞入帐号和密码
//		UserLoginSendBean webCallUserBean = new UserLoginSendBean();
//		webCallUserBean.setCode(cardnumber);
//		webCallUserBean.setPassword(cardpassword);
//		sessionUserBean.setWebCallUserBean(webCallUserBean);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return sessionUserBean;
	}
	
	/**
	 * 登陆验证用户信息
	 */
	public LoginBean ValidationLogin(String logname,String password) throws Exception{
		LoginBean loginBean = new LoginBean();
		try{
		PurviewDAO purviewDAO =(PurviewDAO) SpringContextUtil.getBean("purviewDAO");
		UserBean userBean = purviewDAO.ValidationLogin(logname);
		
		String state = "";
		String errmessage = "";
		String userid = "";
		if(userBean != null){
			if("3".equals(userBean.getState())){
				state = "ERROR";
				errmessage = "非法用户名";
			}else if("2".equals(userBean.getState())){
				String vc_password = userBean.getPassword();
				if(vc_password.equals(password)){
					userid = userBean.getId();
					state = "SUCCESS";
				}else{
					state = "ERROR";
					errmessage = "用户密码错误，请重新输入！";
				}
			}
		}else{
			state = "ERROR";
			errmessage = "用户名不存在";
		}
		loginBean.setState(state);
		loginBean.setErrmessage(errmessage);
		loginBean.setUserid(userid);
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return loginBean;
	}
}
