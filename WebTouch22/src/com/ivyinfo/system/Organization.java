package com.ivyinfo.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ivyinfo.communication.bean.CommunicationBean;
import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.sequence.ISequence;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.bean.SetupMailBean;
import com.ivyinfo.organization.bean.OrganizationBean;
import com.ivyinfo.organization.services.OrganizationServices;
import com.ivyinfo.orgpurview.bean.OrgPurviewBean;
import com.ivyinfo.orgpurview.services.OrgPurviewServices;
import com.ivyinfo.permissions.bean.PermissionsBean;
import com.ivyinfo.permissions.services.PermissionsServices;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.user.services.UserServices;
import com.ivyinfo.util.Constant;
import com.ivyinfo.util.Page;

public class Organization {
	private static final Logger LOGGER = LoggerFactory.getLogger(Organization.class);
	
	private OrganizationServices organizationServices = (OrganizationServices) SpringContextUtil
	.getBean("organizationServices");
	
	private UserServices userServices = (UserServices) SpringContextUtil
	.getBean("userServices");
	
	private OrgPurviewServices orgpurviewServices = (OrgPurviewServices) SpringContextUtil
	.getBean("orgpurviewServices");
	
	private PermissionsServices permissionsServices = (PermissionsServices) SpringContextUtil
	.getBean("permissionsServices");
	
	/**
	 * 查看机构详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String View(String id) throws Exception{
		OrganizationBean organizationBean=organizationServices.View(new Integer(id).intValue());
		
		JSONObject  jsonObj=new JSONObject();
		
		jsonObj.put("id", organizationBean.getId());
		jsonObj.put("name", organizationBean.getName());
		jsonObj.put("email", organizationBean.getEmail());
		jsonObj.put("officephone", organizationBean.getOfficephone());
		jsonObj.put("faxnumber", organizationBean.getFaxnumber());
		jsonObj.put("nationality", organizationBean.getNationality());
		jsonObj.put("province", organizationBean.getProvince());
		jsonObj.put("city", organizationBean.getCity());
		jsonObj.put("detailedaddress", organizationBean.getDetailedaddress());
		jsonObj.put("postcode", organizationBean.getPostcode());
		jsonObj.put("remark", organizationBean.getRemark());
		
		List arrayList = orgpurviewServices.getOrgPurview(organizationBean.getId());
		String pnames="";
		for(int i=0;i<arrayList.size();i++)
        {
			OrgPurviewBean orgpurviewBean = (OrgPurviewBean) arrayList.get(i);
			String pname = orgpurviewBean.getPname();
			pnames += pname + ",";
        }
		if(pnames!="")
			pnames=pnames.substring(0,pnames.length()-1);
		jsonObj.put("pname", pnames);
		return jsonObj.toString();
	}
	
	/**
	 * 查询机构列表信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONObject getOrgList(HttpServletRequest request) throws Exception{
		String page1=(String)request.getParameter("page");
		String sidx=(String)request.getParameter("sidx");
		String sord=(String)request.getParameter("sord");
		
		Page page=new Page(Constant.PAGE_SIZE_10);
		
		if("".equals(page1)){
			page1="1";
		}
		
		List arrayList=new ArrayList();
		
		int totalCount=organizationServices.OrgListCount();
		page.setPageNo(new Integer(page1).intValue());
		page.setTotalCount(totalCount);
		arrayList=organizationServices.AllIndex(page.getStart(), page.getEnd(),sidx,sord);
		
		// 定义返回的数据类型：json，使用了json-lib
        JSONObject jsonObj = new JSONObject();
        // 根据jqGrid对JSON的数据格式要求给jsonObj赋值
        jsonObj.put("page", page1);                // 当前页
        jsonObj.put("total",page.getTotalPages());        // 总页数
        jsonObj.put("records", totalCount);        // 总记录数
        // 定义rows，存放数据
        JSONArray rows = new JSONArray();
        for(int i=0;i<arrayList.size();i++)
        {
        	OrganizationBean organizationBean =  (OrganizationBean)arrayList.get(i);
                // 存放一条记录的对象
                JSONObject cell = new JSONObject();
                cell.put("id", organizationBean.getId());
                cell.put("name", organizationBean.getName());
                cell.put("officephone", organizationBean.getOfficephone());
                cell.put("remark", organizationBean.getRemark());
                cell.put("operation", "设置权限");
                List parrayList = orgpurviewServices.getOrgPurview(organizationBean.getId());
        		String pnames="";
        		for(int j=0;j<parrayList.size();j++)
                {
        			OrgPurviewBean orgpurviewBean = (OrgPurviewBean) parrayList.get(j);
        			String pname = orgpurviewBean.getPname();
        			pnames += pname + ",";
                }
        		if(pnames!="")
        			pnames=pnames.substring(0,pnames.length()-1);
        		cell.put("pname", pnames);
                // 将该记录放入rows中
                rows.add(cell);
        }
        // 将rows放入json对象中
        jsonObj.put("rows", rows);
        // 自控制台打印输出，以检验json对象生成是否正确
        LOGGER.info("\n sidx="+sidx+";sord="+sord+
        		"\n;(page.getStart()=="+page.getStart()+";;page.getEnd()="+page.getEnd()+";;page.getTotalPages()="+page.getTotalPages()+";totalCount="+totalCount+";page1="+page1+";arrayList.size()="+arrayList.size()+
        		";\n要返回的json对象：\n" + jsonObj.toString());
        
        return jsonObj;
	}
	
	/**
	 * 新增机构信息
	 * @param request
	 * @param userid
	 * @throws Exception
	 */
	public void AddOrg(HttpServletRequest request,SessionUserBean sessionUserBean) throws Exception{
		String name=(String)request.getParameter("orgname");
		System.err.println("name:"+name);
		String email=(String)request.getParameter("email");
		
		String officephone=(String)request.getParameter("officephone");
		officephone = (officephone == null)?"":officephone;
		String faxnumber=(String)request.getParameter("faxnumber");
		faxnumber = (faxnumber == null)?"":faxnumber;
		String nationality=(String)request.getParameter("nationality");
		nationality = (nationality == null)?"":nationality;
		String province=(String)request.getParameter("province");
		province = (province == null)?"":province;
		String city=(String)request.getParameter("city");
		city = (city == null)?"":city;
		String detailedaddress=(String)request.getParameter("detailedaddress");
		detailedaddress = (detailedaddress == null)?"":detailedaddress;
		String postcode=(String)request.getParameter("postcode");
		postcode = (postcode == null)?"":postcode;
		String remark=(String)request.getParameter("remark");
		remark = (remark == null)?"":remark;
		
		String orglogname=(String)request.getParameter("orglogname");
		String orgpassword=(String)request.getParameter("orgpassword");
		
		OrganizationBean organizationBean = new OrganizationBean();
		organizationBean.setName(name);
		organizationBean.setEmail(email);
		organizationBean.setOfficephone(officephone);
		organizationBean.setFaxnumber(faxnumber);
		organizationBean.setNationality(nationality);
		organizationBean.setCity(city);
		organizationBean.setDetailedaddress(detailedaddress);
		organizationBean.setPostcode(postcode);
		organizationBean.setRemark(remark);
		organizationBean.setProvince(province);
		
		UserBean userBean = new UserBean();
		userBean.setLogname(orglogname);
		userBean.setPassword(orgpassword);
		userBean.setNickname(orglogname);
		userBean.setName(name+"管理员");
		userBean.setEmail(email);
		userBean.setType("1");
		userBean.setState("2");
		userBean.setUpduserid(sessionUserBean.getUserBean().getId());
		userBean.setTimestemp(TimeTools.getString());
		userBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
		
		ISequence sequenceService = (ISequence) SpringContextUtil.getBean("Sequence");
		
		long id = sequenceService.getMaxId("t_sys_organization");
		long contactid = sequenceService.getMaxId("t_sys_organization_contact");
		organizationBean.setId(String.valueOf(id));
		organizationBean.setContactid(String.valueOf(contactid));
		organizationBean.setOrganizationid(String.valueOf(id));
		
		organizationBean.setUpduserid(sessionUserBean.getUserBean().getId());
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
		
		SetupMailBean setupmailBean = new SetupMailBean();
		long usermailid = sequenceService.getMaxId(orglogname+"_t_mail_setup");
		setupmailBean.setId(String.valueOf(usermailid));
		setupmailBean.setAddname(email);
		setupmailBean.setTimestemp(TimeTools.getString());
		setupmailBean.setUserlogname(orglogname);
		
		String meetorgid = organizationServices.AddSubmit(organizationBean);
		organizationServices.AddSubmitContact(organizationBean);
		
		userServices.AddSubmit(userBean,sessionUserBean.getUserBean(),setupmailBean,meetorgid);
		userServices.AddSubmitBasic(userBean);
		userServices.AddSubmitContact(userBean);
		userServices.AddSubmitPhoto(userBean);
	}
	
	/**
	 * 修改机构信息
	 * @param request
	 * @param userid
	 * @throws Exception
	 */
	public void Updorg(HttpServletRequest request,SessionUserBean sessionUserBean) throws Exception{
		String id=(String)request.getParameter("id");
		System.err.println("id:"+id);
		String name=(String)request.getParameter("orgname");
		System.err.println("name:"+name);
		String email=(String)request.getParameter("email");
		String officephone=(String)request.getParameter("officephone");
		String faxnumber=(String)request.getParameter("faxnumber");
		String nationality=(String)request.getParameter("nationality");
		String province=(String)request.getParameter("province");
		String city=(String)request.getParameter("city");
		String detailedaddress=(String)request.getParameter("detailedaddress");
		String postcode=(String)request.getParameter("postcode");
		String remark=(String)request.getParameter("remark");
		
//		OrganizationBean organizationBean = new OrganizationBean();
//		organizationBean.setName(name);
//		organizationBean.setEmail(email);
//		organizationBean.setOfficephone(officephone);
//		organizationBean.setFaxnumber(faxnumber);
//		organizationBean.setNationality(nationality);
//		organizationBean.setCity(city);
//		organizationBean.setDetailedaddress(detailedaddress);
//		organizationBean.setPostcode(postcode);
//		organizationBean.setRemark(remark);
//		organizationBean.setProvince(province);
		OrganizationBean organizationBean=organizationServices.View(new Integer(id).intValue());
		organizationBean.setId(id);
		organizationBean.setOrganizationid(id);
		organizationBean.setName(name);
		organizationBean.setEmail(email);
		
		organizationBean.setUpduserid(sessionUserBean.getUserBean().getId());
		organizationBean.setTimestemp(TimeTools.getString());
		organizationBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
		
		organizationServices.UpdSubmit(organizationBean);
		organizationServices.UpdSubmitContact(organizationBean);
	}
	
	/**
	 * 删除机构信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public void Delorg(String id) throws Exception{
		System.err.println("id:"+id);
		id = id + ",";
		while(id.indexOf(",")>-1){
			String orgid = id.substring(0,id.indexOf(","));
			id = id.substring(id.indexOf(",")+1,id.length());
			
			organizationServices.Del(new Integer(orgid).intValue());
			organizationServices.DelContact(orgid);
		}
	}
	
	/**
	 * 根据机构id查询所拥有的权限信息
	 * @param orgid
	 * @return
	 * @throws Exception
	 */
	public JSONObject SetPermissions(String orgid) throws Exception{
		List arrayList=new ArrayList();
		
		//查询机构信息
		OrganizationBean organizationBean = organizationServices.View(Integer.parseInt(orgid));
		//查询所有权限信息
		List list = permissionsServices.AllIndex();
		//查询对应的机构id所拥有的权限信息
		arrayList = orgpurviewServices.getOrgPurview(orgid);
		
		orgid = organizationBean.getId();
		String orgname = organizationBean.getName();
		
		String pnumber = "";
		String pnames = "";
		String allpnumber = "";
		String allpname = "";
		for(int j=0;j<list.size();j++){
			PermissionsBean permissionsBean = (PermissionsBean) list.get(j);
			String pnumber1 = permissionsBean.getNumber();
			String pname = permissionsBean.getName();
			
			allpnumber += pnumber1 + ",";
			allpname += pname + ",";
		}
		
		for(int i=0;i<arrayList.size();i++)
        {
			OrgPurviewBean orgpurviewBean = (OrgPurviewBean) arrayList.get(i);
			String pnumber2 = orgpurviewBean.getPnumber();
			String pname = orgpurviewBean.getPname();
			pnumber += pnumber2 + ",";
			pnames += pname + ",";
        }
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("orgid", orgid);
		jsonObj.put("orgname", orgname);
		jsonObj.put("pnumber", pnumber);
		if(pnames!="")
			pnames=pnames.substring(0,pnames.length()-1);
		if(allpnumber!="")
			allpnumber=allpnumber.substring(0,allpnumber.length()-1);
		jsonObj.put("pnames", pnames);
		jsonObj.put("allpnumber", allpnumber);
		jsonObj.put("allpname", allpname);
		
		
		return jsonObj;
	}
	
	/**
	 * 查询所有的权限信息
	 * @return
	 * @throws Exception
	 */
	public JSONArray getOrgPurview() throws Exception{
		
		JSONArray jsonObj = new JSONArray();
		List attendeeList = permissionsServices.AllIndex();
		
		for(int i = 0;i < attendeeList.size();i++){
			PermissionsBean permissionsBean = (PermissionsBean) attendeeList.get(i);
			JSONObject cell = new JSONObject();
			cell.put("name", permissionsBean.getName());
			cell.put("id", permissionsBean.getNumber());
			jsonObj.add(cell);
		}
		
        // 将rows放入json对象中
        // 自控制台打印输出，以检验json对象生成是否正确
        LOGGER.info("\n arrayList.size()="+attendeeList.size()+
        		";\n要返回的json对象：\n" + jsonObj.toString());
        
        return jsonObj;
	}
	
	public void Addpurview(HttpServletRequest request) throws Exception{
		String orgid = request.getParameter("orgid");
		String[] pname = request.getParameterValues("tokenName");
		if(pname == null){
			orgpurviewServices.DelOrgPurview(orgid);
		}else{
			orgpurviewServices.DelOrgPurview(orgid);
			for(int i=0;i<pname.length;i++){
				OrgPurviewBean orgpurviewBean = new OrgPurviewBean();
				String pnumber = pname[i];
				System.err.println("pnumber:"+pnumber);
				
				orgpurviewBean.setOrgid(orgid);
				orgpurviewBean.setPnumber(pnumber);
				orgpurviewBean.setTimestemp(TimeTools.getString());
				
				orgpurviewServices.SaveOrgPurview(orgpurviewBean);
			}
		}
	}
	
	/**
	 * 注册机构信息
	 * @param request
	 * @param userid
	 * @throws Exception
	 */
	public void RegisterOrg(HttpServletRequest request) throws Exception{
		UserBean suserBean = new UserBean();
		suserBean.setId("1");
		suserBean.setLogname("admin");
		suserBean.setPassword("admin");
		
		String name=(String)request.getParameter("orgname");
		System.err.println("name:"+name);
		String email=(String)request.getParameter("email");
		
		String officephone=(String)request.getParameter("officephone");
		officephone = (officephone == null)?"":officephone;
		String faxnumber=(String)request.getParameter("faxnumber");
		faxnumber = (faxnumber == null)?"":faxnumber;
		String nationality=(String)request.getParameter("nationality");
		nationality = (nationality == null)?"":nationality;
		String province=(String)request.getParameter("province");
		province = (province == null)?"":province;
		String city=(String)request.getParameter("city");
		city = (city == null)?"":city;
		String detailedaddress=(String)request.getParameter("detailedaddress");
		detailedaddress = (detailedaddress == null)?"":detailedaddress;
		String postcode=(String)request.getParameter("postcode");
		postcode = (postcode == null)?"":postcode;
		String remark=(String)request.getParameter("remark");
		remark = (remark == null)?"":remark;
		
		String orglogname=(String)request.getParameter("orglogname");
		String orgpassword=(String)request.getParameter("orgpassword");
		
		OrganizationBean organizationBean = new OrganizationBean();
		organizationBean.setName(name);
		organizationBean.setEmail(email);
		organizationBean.setOfficephone(officephone);
		organizationBean.setFaxnumber(faxnumber);
		organizationBean.setNationality(nationality);
		organizationBean.setCity(city);
		organizationBean.setDetailedaddress(detailedaddress);
		organizationBean.setPostcode(postcode);
		organizationBean.setRemark(remark);
		organizationBean.setProvince(province);
		
		UserBean userBean = new UserBean();
		userBean.setLogname(orglogname);
		userBean.setPassword(orgpassword);
		userBean.setNickname(orglogname);
		userBean.setName(name+"管理员");
		userBean.setEmail(email);
		userBean.setType("1");
		userBean.setState("2");
		userBean.setUpduserid(suserBean.getId());
		userBean.setTimestemp(TimeTools.getString());
		userBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
		
		ISequence sequenceService = (ISequence) SpringContextUtil.getBean("Sequence");
		
		long id = sequenceService.getMaxId("t_sys_organization");
		long contactid = sequenceService.getMaxId("t_sys_organization_contact");
		organizationBean.setId(String.valueOf(id));
		organizationBean.setContactid(String.valueOf(contactid));
		organizationBean.setOrganizationid(String.valueOf(id));
		
		organizationBean.setUpduserid(suserBean.getId());
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
		
		SetupMailBean setupmailBean = new SetupMailBean();
		long usermailid = sequenceService.getMaxId(orglogname+"_t_mail_setup");
		setupmailBean.setId(String.valueOf(usermailid));
		setupmailBean.setAddname(email);
		setupmailBean.setTimestemp(TimeTools.getString());
		setupmailBean.setUserlogname(orglogname);
		
		String meetorgid = organizationServices.AddSubmit(organizationBean);
		organizationServices.AddSubmitContact(organizationBean);
		
		userServices.AddSubmit(userBean,suserBean,setupmailBean,meetorgid);
		userServices.AddSubmitBasic(userBean);
		userServices.AddSubmitContact(userBean);
		userServices.AddSubmitPhoto(userBean);
	}
	
	//判断机构名称是否已存在
	public boolean checkOrgName(String orgName) throws Exception{
		int result=organizationServices.ValidationOrgName(orgName);
		int result2=organizationServices.VOrgName(orgName);
		if(result>0 || result2>0)
			return true;
		else 
			return false;
	}
}
