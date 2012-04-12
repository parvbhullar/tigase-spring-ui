package org.eredlab.g4.ccl.util;

import java.util.ArrayList;
import java.util.List;

import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.rif.web.BaseAction;

import com.xzb.oa.knowledge.service.KService;

public class G4RoleLimitis extends BaseAction {
	private KService kService = (KService) getService("kService");
	
	public  List FilterRoleLimitis(List list,Dto dto)
	{
		
		String userid=dto.getAsString("loginuserid");
		List newList=new ArrayList();
		int s=0;
		G4RoleLimitis g=new G4RoleLimitis();
		if(G4Utils.isEmpty(list))
		{
			return null;
		}
		Dto dto2=new BaseDto();
		for(int i=0;i<list.size();i++)
		{
			dto2=(BaseDto) list.get(i);
			String loginuserid="";
			if(!G4Utils.isEmpty(dto2.getAsString("loginuserid")))
			{
				loginuserid=dto2.getAsString("loginuserid");
				if(loginuserid.equals(userid))
				{
					//个人目录
					
					
				}else
				{
					s=g.filterDirLimitis(dto2,dto);
					if(s==0)
					{
						//不共享
					}else if(s==1)
					{
						newList.add(dto2);
					}else if(s==2)
					{
						//共享，但只能查看
						dto2.put("share", "1");
						newList.add(dto2);
					}else if(s==3)
					{
						//共享。可以修改
						dto2.put("share", "2");
						newList.add(dto2);
					}
				}
				
				
				
			}else{
				//是公司目录
				
			}
			
		}
		
		
		
		
		return newList;
	}
	
	
	public  int filterDirLimitis(Dto dto1,Dto indto)
	{
		
		String moduleId="";//id
		String share="";//共享类型。0.1.2
		String userid="";
		
		String deptid=indto.getAsString("deptid");
		
		if(!G4Utils.isEmpty(dto1.getAsString("id")))
		{
			moduleId=dto1.getAsString("id");
		}else{
			return 0;
		}
		
		if(G4Utils.isEmpty(indto.getAsString("loginuserid")))
		{
			return 0;//0代表不共享
		}else
			{
				userid=indto.getAsString("loginuserid");
			}
		Dto inDto1=new BaseDto();
		Dto lDto=new BaseDto();
		
		if(G4Utils.isEmpty(dto1.getAsString("share")))
		{
			share="0";//0代表不共享给别人
			return 0;
		}else if(dto1.getAsString("share").equals("1"))
		{
			//共享给所有人 
			return 1;//所有人都可修改，查看
		}else if(dto1.getAsString("share").equals("2"))
		{
			inDto1.put("moduleId", moduleId);
			inDto1.put("moduleType", indto.getAsString("moduleType"));
			lDto=kService.queryAllLimitis(inDto1);
			
			//先从人员开始判断
			if(!G4Utils.isEmpty(lDto.getAsString("userid")))
			{
				String user=lDto.getAsString("userid");
				String[] allUsers=user.split(",");
				for(int i=0;i<allUsers.length;i++)
				{
					if(allUsers[i].equals(userid))
					{
						return 3;//可以修改
					}
				}
			}else if(!G4Utils.isEmpty(lDto.getAsString("deptid")))
					{
						String d=lDto.getAsString("deptid");
						String[] allDepts=d.split(",");
						for(int i=0;i<allDepts.length;i++)
						{
							if(allDepts[i].equals(deptid))
							{
								return 3;//可以查看
							}
						}
						
					}	
		}
		return 0;
	}
	
	
	
	
	
	
	

}
