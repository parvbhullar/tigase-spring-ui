package com.ivyinfo.purview.services;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.login.bean.LoginBean;
import com.ivyinfo.purview.dao.PurviewDAO;
import com.ivyinfo.user.bean.UserBean;

public class PurviewServicesImpl implements PurviewServices{
	
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
