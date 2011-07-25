package com.ivyinfo.user.services;

import org.njdt.gg.bmf.base.IDao;
import org.njdt.gg.ccl.datastructure.Dto;
import org.njdt.gg.ccl.datastructure.impl.BaseDto;

import com.ivyinfo.framework.service.base.BaseService;
import com.ivyinfo.framework.service.server.SpringContextUtil;

public class UserServicesImpl extends BaseService implements UserServices{

	public Dto saveUserItem(Dto inDto) throws Exception {
		IDao iDao =(IDao) SpringContextUtil.getBean("iDao");
		Dto dto=new BaseDto();
		iDao.insert("saveUserItem", inDto);
		dto.put("id", "");
		return dto;
	}

	public Dto ValidationLogin(Dto inDto) throws Exception {
		IDao iDao =(IDao) SpringContextUtil.getBean("iDao");
//		System.out.println("iDao"+iDao);
		return (Dto)iDao.queryForObject("validateAccount",inDto);
		
	}
}
