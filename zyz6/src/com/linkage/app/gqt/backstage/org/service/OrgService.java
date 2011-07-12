package com.linkage.app.gqt.backstage.org.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Service;

import com.linkage.app.gqt.backstage.org.dao.OrgDao;
import com.linkage.app.gqt.backstage.org.entitys.Org;
import com.linkage.app.gqt.init.entity.OrgType;
import com.linkage.app.gqt.purview.dao.PurviesDao;


@Service
public class OrgService {
	final Logger logger = LoggerFactory.getLogger(OrgService.class);
	private OrgDao orgDao;
	private PurviesDao purviesDao;
	private OracleSequenceMaxValueIncrementer seqzyzcommon;
	private OracleSequenceMaxValueIncrementer seqzyzuser;
	
	@Autowired
	public OrgService(OrgDao orgDao,PurviesDao purviesDao,OracleSequenceMaxValueIncrementer seqzyzcommon,OracleSequenceMaxValueIncrementer seqzyzuser){
		this.orgDao=orgDao;
		this.purviesDao=purviesDao;
		this.seqzyzcommon=seqzyzcommon;
		this.seqzyzuser=seqzyzuser;
	}
	
	/*
	 * 机构类型表ZYZ_ORGTYPE
	 */
//	public List<OrgType> getAllOrgTypes(){
//		return this.orgDao.selectAllOrgTypes();
//	}
	
	public int addTopOrgType(String orgTypeName,int orgTypeCategory){
		OrgType orgType=new OrgType();
		orgType.setOrgTypeName(orgTypeName);
		orgType.setOrgTypeParentId(0);
		orgType.setCategory(orgTypeCategory);
		return this.orgDao.insertOrgType(orgType);
	}
	
	public int addOrgType(String orgTypeName,long orgTypeParentId,int orgTypeCategory){
		OrgType orgType=new OrgType();
		orgType.setOrgTypeName(orgTypeName);
		orgType.setOrgTypeParentId(orgTypeParentId);
		orgType.setCategory(orgTypeCategory);
		return this.orgDao.insertOrgType(orgType);
	}
	
	public int editOrgTypeWithOrgTypeStateByOrgTypeId(long orgTypeId,int orgTypeState){
		return this.orgDao.updateOrgTypeWithOrgTypeStateByOrgTypeId(orgTypeId, orgTypeState);
	}
	
	public int editOrgTypeWithOrgTypeNameByOrgTypeId(long orgTypeId,String orgTypeName){
		return this.orgDao.updateOrgTypeWithOrgTypeNameByOrgTypeId(orgTypeId, orgTypeName);
	}
	
	public int delOrgTypeByOrgTypeId(long orgTypeId){
		return this.orgDao.deleteOrgTypeByOrgTypeId(orgTypeId);
	}
	
	public String addOrg(Org org){
		long orgId=this.seqzyzcommon.nextLongValue();
		long roleId=0;
		if(org!=null){
			org.setOrgId(orgId);
			org.setOrgState(1);
			if(org.getOrgTypeId()==300){//服务机构
				org.setManageOrgId(org.getParentOrgId());
				org.setRegAble(0);
				roleId=8;
			}else if(org.getOrgTypeId()==100){//行政机构
				org.setManageOrgId(org.getOrgId());
				org.setRegAble(0);
				if(org.getOrgLevel()==1){
					roleId=2;
				}else if(org.getOrgLevel()==2){
					roleId=3;
				}else if(org.getOrgLevel()==3){
					roleId=4;
				}else if(org.getOrgLevel()==4){
					roleId=5;
				}else if(org.getOrgLevel()==5){
					roleId=6;
				}
			}else{//业务机构
				org.setManageOrgId(org.getOrgId());
				roleId=7;
			}
			org.setBelongOrgId(0);
		}else{
			return 0+"";
		}
		logger.info("组织ID={}",orgId);
		int i=this.orgDao.insertOrg(org);
		if(i==1){
			long userId=this.seqzyzuser.nextLongValue();
			logger.info("用户ID={}",userId);
			int j=this.purviesDao.insertUser(userId,orgId,org.getOrgName());
			if(j==1){
				int k=this.purviesDao.insertUserRoleWithAdmin(userId,roleId,orgId);
				if(k==1){
					return org.getOrgName()+"管理员的登录名为:"+userId+",登录初始密码为:1234,请认真保管并尽快修改密码!";
				}else{
					return 0+"";
				}
			}else{
				return 0+"";
			}
		}else{
			return 0+"";
		}
	}
	
	public int editOrgWithOrgStateByOrgId(long orgId,int orgState){
		return this.orgDao.updateOrgWithOrgStateByOrgId(orgId, orgState);
	}
	
	public int editOrgByOrgId(Org org){
		return this.orgDao.updateOrgByOrgId(org);
	}
	
	public int delOrgByOrgId(long orgId){
		return this.orgDao.deleteOrgByOrgId(orgId);
	}
	
	public List<Org> getSubOrgList(List<Org> list,List<Org> newList,long orgId){
		if(list==null||list.size()==0){
			return new ArrayList<Org>();
		}else if(newList==null){
			newList=new ArrayList<Org>();
		}else{
			for(Org org:list){
				if(org.getOrgId()==orgId){
					logger.info(""+orgId);
					newList.add(org);
				}
				if(org.getParentOrgId()==orgId){
					getSubOrgList(list,newList,org.getOrgId());
				}
			}
		}
		return newList;
	}
	
	/**
	 * 返回jstree json data 取当前组织的直接下级树
	 * @param list
	 * @param newList
	 * @param orgId
	 * @return
	 * @throws JSONException 
	 */
	public String getSubOrgListJson(List<Org> list,List<Org> newList,long orgId) throws JSONException{
		JSONArray jSONAaary=new JSONArray();
		JSONObject obj2=new JSONObject();
		StringBuffer sb2=new StringBuffer("[{");
		if(list==null||list.size()==0){
			return jSONAaary.toString();
			//return new ArrayList<Org>();
		}else if(newList==null){
			newList=new ArrayList<Org>();
		}else{
			int i=0;
			StringBuffer sb=new StringBuffer("[{");
			String orgName="";
			for(Org org:list){
				if(org.getOrgId()==orgId){
//					obj2.put("data", org.getOrgName());
					orgName=org.getOrgName();
					logger.info(""+orgId);
					newList.add(org);
				}
				if(org.getParentOrgId()==orgId){
					if(i==0)
					{
						sb.append("\"data\"").append(":\"").append(org.getOrgName()).append("\"}");
						i++;
					}
					else
					{
						sb.append(",{\"data\"").append(":\"").append(org.getOrgName()).append("\"}");
					}
				}
				jSONAaary.put(obj2);
			}
			sb.append("]");
			sb2.append("\"data\"").append(":\"").append(orgName).append("\",").append("\"children\"").append(":").append(sb.toString());
			sb2.append(",\"state\" : \"open\" }]");
		}
		return sb2.toString();
	}
	
	/**
	 * 返回jstree json data 取当前组织的所有下级树
	 * @param list
	 * @param newList
	 * @param orgId
	 * @return
	 * @throws JSONException 
	 */
	public String getAllSubOrgJson(List<Org> list,List<Org> newList,long orgId,int isroot,int level) throws JSONException{
		StringBuffer sb2=new StringBuffer("");
		if(1==isroot)
			sb2=new StringBuffer("[{");
		if(newList==null){
			newList=new ArrayList<Org>();
		}else{
			int i=0;
			StringBuffer sb=new StringBuffer("");
				sb=new StringBuffer("[{");
			String orgName="";
			long belongOrgId=0;
			for(Org org:list){
				if(org.getOrgId()==orgId){
					orgName=org.getOrgName();
					belongOrgId=org.getBelongOrgId();
					logger.info(""+orgId);
					newList.add(org);
				}
				if(org.getParentOrgId()==orgId){
						if(i==0)
						{
							sb.append("\"data\"").append(":\"").append(org.getOrgName()).append("\"");
							
							sb.append(",\"attr\" : { \"id\" : \"").append(org.getOrgId()).append("\",\"alt\":\"").append(org.getBelongOrgId()).append("\"}");
							i++;
						}
						else
						{
							sb.append(",{\"data\"").append(":\"").append(org.getOrgName()).append("\"");
							sb.append(",\"attr\" : { \"id\" : \"").append(org.getOrgId()).append("\",\"alt\":\"").append(org.getBelongOrgId()).append("\"}");
							i++;
						}
					sb.append(getAllSubOrgJson(list,newList,org.getOrgId(),0,level));
				}
			}
				
			if(i>1)
			{
					sb.append("]");
			}
				
			if(1==isroot)
			{
				if(newList.size()!=1)
				{
					sb2.append("\"data\"").append(":\"").append(orgName).append("\",").append("\"attr\" : { \"id\" : \"").append(orgId).append("\",\"alt\":\"").append(belongOrgId).append("\"},").append("\"children\"").append(":").append(sb.toString());
					sb2.append(",\"state\" : \"open\" }]");
				}
					
				else//社区
				{
					sb2.append("\"data\"").append(":\"").append(orgName).append("\",").append("\"attr\" : { \"id\" : \"").append(orgId).append("\",\"alt\":\"").append(belongOrgId).append("\"}");
					sb2.append("}]");
				}
			}
			else
			{
				if(!sb.toString().endsWith("[{"))
				{
				sb2.append(",").append("\"children\"").append(":").append(sb.toString()).append("}");
				}
				else
				{
					sb2.append("}");
				}
			}
		}
		return sb2.toString();
	}	

	public List<Org> _getSubOrgList(List<Org> list,List<Org> newList,long orgId,long _orgId){
		Org orgTemp;
		if(list==null||list.size()==0){
			return new ArrayList<Org>();
		}else if(newList==null){
			newList=new ArrayList<Org>();
		}else{
			for(Org org:list){
				if(org.getOrgId()==orgId){
					if(org.getOrgTypeId()==200){
						if(org.getOrgId()==_orgId){
							orgTemp=new Org();
							orgTemp.setOrgName(org.getParentOrgId()+"_"+org.getOrgId());
							orgTemp.setOrgContactor(org.getOrgName()+";");
							newList.add(orgTemp);
						}
					}else{
						orgTemp=new Org();
						orgTemp.setOrgName(org.getParentOrgId()+"_"+org.getOrgId());
						orgTemp.setOrgContactor(org.getOrgName()+";");
						newList.add(orgTemp);
					}
				}
				if(org.getParentOrgId()==orgId){
					getSubOrgList(list,newList,org.getOrgId());
				}
			}
		}
		return newList;
	}
	
	/**
	 * 取OrgType为100的组织
	 * @param list
	 * @param newList
	 * @param orgId
	 * @return
	 */
	public List<Org> getSubOrgListCommunity(List<Org> list,List<Org> newList,long orgId){
		if(list==null||list.size()==0){
			return new ArrayList<Org>();
		}else if(newList==null){
			newList=new ArrayList<Org>();
		}else{
			for(Org org:list){
//				if(org.getOrgId()==orgId&&org.getOrgTypeId()==100){
//					logger.info(""+orgId);
//					newList.add(org);
//				}
				if(org.getParentOrgId()==orgId&&org.getOrgTypeId()==100){
					newList.add(org);
//					getSubOrgList(list,newList,org.getOrgId());
				}
			}
		}
		return newList;
	}
	
	/**
	 * 根据社区查上级组织
	 * @param list
	 * @param newList
	 * @param orgId
	 * @return
	 */
	public Org getUpOrg(List<Org> list,long orgId){
		Org orgtemp=new Org();
		for(Org org:list){
			if(org.getOrgId()==orgId){
				orgtemp=org;
			}
		}
		for(Org org:list){
			if(org.getOrgId()==orgtemp.getParentOrgId()){
				orgtemp=org;
			}
		}
		return orgtemp;
	}

	public boolean isNanTongUser(Long orgId){
		if(this.orgDao.selectInt(orgId)>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 作用：判断某组织的机构类型（行政机构、组织机构）
	 * @param  组织ID
	 * @return 组织
	 */
	public Org getOrgType(long orgId){
		return this.orgDao.getOrgType(orgId);
	}
	
	/**
	 * 作用：获取某组织的所在地区(江苏的13个地级市)
	 * @param  组织ID
	 * @return 该组织的所在地区
	 */
	public Org getAreaIdByOrgId(long orgId){
		return this.orgDao.getAreaIdByOrgId(orgId);
	}
}
