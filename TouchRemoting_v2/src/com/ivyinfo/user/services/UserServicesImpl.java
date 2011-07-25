package com.ivyinfo.user.services;

import org.njdt.gg.bmf.base.IDao;
import org.njdt.gg.ccl.datastructure.Dto;

import com.ivyinfo.framework.service.base.BaseService;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.user.bean.UserBean;

public class UserServicesImpl extends BaseService implements UserServices{

	public void saveUserItem(Dto inDto) throws Exception {
		IDao iDao =(IDao) SpringContextUtil.getBean("iDao");
//		super.iDao.insert("saveEausersubinfoItem", inDto);
//		userDAO.UpdCardCZJE(userid, czje);
	}

	public Dto ValidationLogin(Dto inDto) throws Exception {
		IDao iDao =(IDao) SpringContextUtil.getBean("iDao");
//		System.out.println("iDao"+iDao);
		return (Dto)iDao.queryForObject("validateAccount",inDto);
		
	}
}
