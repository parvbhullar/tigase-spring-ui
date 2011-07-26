package com.ivyinfo.user.services;

import java.util.List;
import java.util.Map;

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
	
	/**
	 * 分页查询
	 * @param inDto
	 * @return
	 * @throws Exception
	 */
	public Dto queryUserForManage(Map map)throws Exception{
		IDao iDao =(IDao) SpringContextUtil.getBean("iDao");
		List list=(List)iDao.queryForList("queryUserForManage", map);
		System.out.println(list.size());
		Dto dto=new BaseDto();
		dto.setDefaultAList(list);
		return dto;
	}
}
