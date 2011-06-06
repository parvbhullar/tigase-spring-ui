package com.ivyinfo.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.sequence.ISequence;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.bean.SetupMailBean;
import com.ivyinfo.mail.services.AuxiliaryMailServices;
import com.ivyinfo.purview.bean.PurviewBean;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.user.services.UserServices;
import com.ivyinfo.util.Constant;
import com.ivyinfo.util.Page;

public class User {
	private static final Logger LOGGER = LoggerFactory.getLogger(User.class);
	
	private UserServices userServices = (UserServices) SpringContextUtil
	.getBean("userServices");
	
	private AuxiliaryMailServices auxiliaryMailServices = (AuxiliaryMailServices) SpringContextUtil
	.getBean("auxiliaryMailServices");
	
	/**
	 * 查看用户详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String Viewuser(String id) throws Exception{
		UserBean userBean=userServices.View(new Integer(id).intValue());
		
		JSONObject  jsonObj=new JSONObject();
		String ZHnationality = userBean.getNationality();
		String ZHprovince = userBean.getProvince();
		String ZHcity = userBean.getCity();
		/**
		 * 根据输入的英文字母转换成对应的中文名
		 */
		String nationality = ZHnationality;//countrycode.getValue(ZHnationality);
		String province = ZHprovince;//countrycode.getValue(ZHprovince);
		String city = ZHcity;//countrycode.getValue(ZHcity);
		
		jsonObj.put("userid", userBean.getId());
		jsonObj.put("logname", userBean.getLogname());
		jsonObj.put("nickname", userBean.getNickname());
		jsonObj.put("name", userBean.getName());
		jsonObj.put("sex", userBean.getSex());
		jsonObj.put("usersort", userBean.getUsersort());
		jsonObj.put("job", userBean.getJob());
		jsonObj.put("email", userBean.getEmail());
		jsonObj.put("otheremail", userBean.getOtheremail());
		jsonObj.put("officephone", userBean.getOfficephone());
		jsonObj.put("otherphone", userBean.getOtherphone());
		jsonObj.put("mobilephone", userBean.getMobilephone());
		jsonObj.put("faxnumber", userBean.getFaxnumber());
		jsonObj.put("nationality", nationality);
		jsonObj.put("province", province);
		jsonObj.put("city", city);
		jsonObj.put("detailedaddress", userBean.getDetailedaddress());
		jsonObj.put("postcode", userBean.getPostcode());
		jsonObj.put("othermessage", userBean.getOthermessage());
		jsonObj.put("photoname", userBean.getPhotoname());
		jsonObj.put("state", userBean.getState());
		jsonObj.put("deptid", userBean.getDeptid());

		jsonObj.put("password", userBean.getPassword());
		return jsonObj.toString();
	}
	
	/**
	 * 查看用户详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String viewusermail(String id) throws Exception{
		UserBean userBean=userServices.View(new Integer(id).intValue());
		
//		SetupMailBean setupmailBean = auxiliaryMailServices.ViewMailSetupEmail(userBean.getEmail(), userBean.getLogname());
		
		JSONObject  jsonObj=new JSONObject();
		String ZHnationality = userBean.getNationality();
		String ZHprovince = userBean.getProvince();
		String ZHcity = userBean.getCity();
		/**
		 * 根据输入的英文字母转换成对应的中文名
		 */
		String nationality = ZHnationality;//countrycode.getValue(ZHnationality);
		String province = ZHprovince;//countrycode.getValue(ZHprovince);
		String city = ZHcity;//countrycode.getValue(ZHcity);
		
		jsonObj.put("userid", userBean.getId());
		jsonObj.put("logname", userBean.getLogname());
		jsonObj.put("nickname", userBean.getNickname());
		jsonObj.put("name", userBean.getName());
		jsonObj.put("sex", userBean.getSex());
		jsonObj.put("usersort", userBean.getUsersort());
		jsonObj.put("job", userBean.getJob());
		jsonObj.put("email", userBean.getEmail());
		jsonObj.put("otheremail", userBean.getOtheremail());
		jsonObj.put("officephone", userBean.getOfficephone());
		jsonObj.put("otherphone", userBean.getOtherphone());
		jsonObj.put("mobilephone", userBean.getMobilephone());
		jsonObj.put("faxnumber", userBean.getFaxnumber());
		jsonObj.put("nationality", nationality);
		jsonObj.put("province", province);
		jsonObj.put("city", city);
		jsonObj.put("detailedaddress", userBean.getDetailedaddress());
		jsonObj.put("postcode", userBean.getPostcode());
		jsonObj.put("othermessage", userBean.getOthermessage());
		jsonObj.put("photoname", userBean.getPhotoname());
		jsonObj.put("state", userBean.getState());
		jsonObj.put("deptid", userBean.getDeptid());
		
		String usermailaccid = "";
		String mailusername = "";
		String mailpassword = "";
//		if(setupmailBean != null){
//			usermailaccid = setupmailBean.getId();
//			mailusername = setupmailBean.getLogname();
//			mailpassword = setupmailBean.getLogpassword();
//		}
		jsonObj.put("usermailsetupid", usermailaccid);
		jsonObj.put("mailusername", mailusername);
		jsonObj.put("mailpassword", mailpassword);
		
		return jsonObj.toString();
	}
	
	/**
	 * 查询用户列表信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONObject getUserList(HttpServletRequest request,UserBean suserBean) throws Exception{
		String page1=(String)request.getParameter("page");
		page1 = (page1 == null)?"":page1;
		String sidx=(String)request.getParameter("sidx");
		sidx = (sidx == null)?"a.id":sidx;
		String sord=(String)request.getParameter("sord");
		sord = (sord == null)?"desc":sord;
		
		String state=(String)request.getParameter("state");
		state = (state == null)?"2":state;
		
		String orgid = suserBean.getOrganizationid();
		
		Page page=new Page(Constant.PAGE_SIZE_10);
		
		if("".equals(page1)){
			page1="1";
		}
		
		List arrayList=new ArrayList();
		
		int totalCount=0;
		page.setPageNo(new Integer(page1).intValue());
		page.setTotalCount(totalCount);
		
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
        	UserBean userBean =  (UserBean)arrayList.get(i);
                // 存放一条记录的对象
        	String statename = "";
        	if("0".equals(userBean.getState())){
        		statename = "未审批";
        	}else if("1".equals(userBean.getState())){
        		statename = "审批未通过";
        	}else if("2".equals(userBean.getState())){
        		statename = "在职";
        	}else if("3".equals(userBean.getState())){
        		statename = "离职";
        	}
                JSONObject cell = new JSONObject();
                cell.put("id", userBean.getId());
                cell.put("a.logname", userBean.getLogname());
                cell.put("b.name", userBean.getName());
                cell.put("c.email", userBean.getEmail());
                cell.put("c.officephone", userBean.getOfficephone());
                cell.put("c.mobilephone", userBean.getMobilephone());
                cell.put("a.state", statename);
//                cell.put("operation", "冲值卡号");
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
	 * 新增用户信息
	 * @param request
	 * @param userid
	 * @throws Exception
	 */
	public void Adduser(HttpServletRequest request,UserBean suserBean,String orgid,PurviewBean spurviewBean) throws Exception{
		String logname=(String)request.getParameter("logname");
		String userpassword=(String)request.getParameter("userpassword");
		String username=(String)request.getParameter("username");
		String nickname=(String)request.getParameter("nickname");
		nickname = (nickname == null)?"":nickname;
		String sex=(String)request.getParameter("sex");
		sex = (sex == null)?"":sex;
		String usersort=(String)request.getParameter("usersort");
		usersort = (usersort == null)?"":usersort;
		String job=(String)request.getParameter("job");
		job = (job == null)?"":job;
		String deptid=(String)request.getParameter("deptid");
		deptid = (deptid == null)?"":deptid;
		String email=(String)request.getParameter("email");
		email = (email == null)?"":email;
		String otheremail=(String)request.getParameter("otheremail");
		otheremail = (otheremail == null)?"":otheremail;
		String officephone=(String)request.getParameter("officephone");
		officephone = (officephone == null)?"":officephone;
		String otherphone=(String)request.getParameter("otherphone");
		otherphone = (otherphone == null)?"":otherphone;
		String mobilephone=(String)request.getParameter("mobilephone");
		mobilephone = (mobilephone == null)?"":mobilephone;
		String faxnumber=(String)request.getParameter("faxnumber");
		faxnumber = (faxnumber == null)?"":faxnumber;
//		String nationality=(String)request.getParameter("nationality");
//		String province=(String)request.getParameter("province");
//		String city=(String)request.getParameter("city");
		String detailedaddress=(String)request.getParameter("detailedaddress");
		detailedaddress = (detailedaddress == null)?"":detailedaddress;
		String postcode=(String)request.getParameter("postcode");
		postcode = (postcode == null)?"":postcode;
		String othermessage=(String)request.getParameter("othermessage");
		othermessage = (othermessage == null)?"":othermessage;
		String photoname=(String)request.getParameter("photoname");
		photoname = (photoname == null)?"":photoname;
		
		UserBean userBean = new UserBean();
		userBean.setLogname(logname);
		userBean.setNickname(nickname);
		userBean.setPassword(userpassword);
		userBean.setName(username);
		userBean.setSex("0");
		userBean.setUsersort("1");
		userBean.setJob(job);
		userBean.setDeptid(deptid);
		userBean.setOrganizationid(orgid);
		userBean.setEmail(email);
		userBean.setOtheremail(otheremail);
		userBean.setOfficephone(officephone);
		userBean.setOtherphone(otherphone);
		userBean.setMobilephone(mobilephone);
		userBean.setFaxnumber(faxnumber);
//		userBean.setNationality(nationality);
//		userBean.setProvince(province);
//		userBean.setCity(city);
		userBean.setDetailedaddress(detailedaddress);
		userBean.setPostcode(postcode);
		userBean.setOthermessage(othermessage);
		userBean.setPhotoname(photoname);
		userBean.setPhotopath("");
		
		userBean.setType("2");
		userBean.setState("2");
		
		ISequence sequenceService = (ISequence) SpringContextUtil.getBean("Sequence");
		long id = sequenceService.getMaxId("t_sys_user_login");
		long basicid = sequenceService.getMaxId("t_sys_user_basic");
		long contactid = sequenceService.getMaxId("t_sys_user_contact");
		long photoid = sequenceService.getMaxId("t_sys_user_photo");
		userBean.setId(String.valueOf(id));
		userBean.setBasicid(String.valueOf(basicid));
		userBean.setContactid(String.valueOf(contactid));
		userBean.setPhotoid(String.valueOf(photoid));
		userBean.setUserid(String.valueOf(id));
		
		userBean.setUpduserid(suserBean.getUserid());
		userBean.setTimestemp(TimeTools.getString());
		userBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
		
		SetupMailBean setupmailBean = new SetupMailBean();
		long usermailid = sequenceService.getMaxId(logname+"_t_mail_setup");
		setupmailBean.setId(String.valueOf(usermailid));
		setupmailBean.setAddname(email);
		setupmailBean.setTimestemp(TimeTools.getString());
		setupmailBean.setUserlogname(logname);
		
		userServices.AddSubmit(userBean,suserBean,setupmailBean,spurviewBean.getMeetorgid());
		userServices.AddSubmitBasic(userBean);
		userServices.AddSubmitContact(userBean);
		userServices.AddSubmitPhoto(userBean);
	}
	
	/**
	 * 修改用户信息
	 * @param request
	 * @param userid
	 * @throws Exception
	 */
	public void Upduser(HttpServletRequest request,UserBean suserBean,PurviewBean purviewBean) throws Exception{
		String id=(String)request.getParameter("userid");

		String userpassword=(String)request.getParameter("uuserpassword");
		String username=(String)request.getParameter("username");
		String state=(String)request.getParameter("ustate");
		UserBean userBean=userServices.View(new Integer(id).intValue());
		userBean.setName(username);
		userBean.setPassword(userpassword);
		userBean.setState(state);

		// 当前登录用户权限不够,使用系统管理员
		UserBean puserBean = new UserBean();
		puserBean.setLogname("admin");
		puserBean.setPassword("admin");
		
		userBean.setUpduserid(suserBean.getUserid());
		userBean.setTimestemp(TimeTools.getString());
		userBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
		
		userServices.UpdPassword(userBean);
		
		userServices.UpdSubmit(userBean,puserBean,purviewBean);
		userServices.UpdSubmitBasic(userBean);
		userServices.UpdSubmitContact(userBean);
		userServices.UpdSubmitPhoto(userBean);
	}
	
	/**
	 * 删除用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String Deluser(String id,UserBean suserBean) throws Exception{
		String result = "success";
		String errreason = null;
		System.err.println("id:"+id);
		id = id + ",";
		while(id.indexOf(",")>-1){
			String userid = id.substring(0,id.indexOf(","));
			id = id.substring(id.indexOf(",")+1,id.length());
			System.out.println("==========="+id+"=======");
//			userServices.DelPhoto(userid,suserBean);
//			userServices.DelContact(userid);
//			userServices.DelBasic(userid);
			userServices.Del(new Integer(userid).intValue(),suserBean);
		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 查看用户卡号信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String viewusercard(String id) throws Exception{
		UserBean userbean = userServices.View(new Integer(id).intValue());
		
		String userid = userbean.getUserid();
		
		UserBean userBean = userServices.ViewCard(userid);
		
		JSONObject jsonObj=new JSONObject();
		String cardid = "";
		String cardnumber = "";
		String cardpassword = "";
		if(userBean != null){
			cardid = userBean.getCardid();
			cardnumber = userBean.getCardnumber();
			cardpassword = userBean.getCardpassword();
		}
		
		jsonObj.put("cardid", cardid);
		jsonObj.put("userid", userid);
		jsonObj.put("cardnumber", cardnumber);
		jsonObj.put("cardpassword", cardpassword);
		
		return jsonObj.toString();
	}
	
	/**
	 * 新增用户卡号信息
	 * @param request
	 * @throws Exception
	 */
	public void Addcard(HttpServletRequest request) throws Exception{
		String userid = request.getParameter("carduserid");
		String cardnumber = request.getParameter("cardnumber");
		String cardpassword = request.getParameter("cardpassword");
		
		ISequence sequenceService = (ISequence) SpringContextUtil.getBean("Sequence");
		long cardid = sequenceService.getMaxId("t_sys_user_chongzhi");
		
		UserBean userBean = new UserBean();
		userBean.setCardid(String.valueOf(cardid));
		userBean.setUserid(userid);
		userBean.setCardnumber(cardnumber);
		userBean.setCardpassword(cardpassword);
		userBean.setCardtimestemp(TimeTools.getString());
		
		userServices.AddCard(userBean);
	}
	
	/**
	 * 修改用户卡号信息
	 * @param request
	 * @throws Exception
	 */
	public void Updcard(HttpServletRequest request) throws Exception{
		String cardid = request.getParameter("cardid");
		String userid = request.getParameter("carduserid");
		String cardnumber = request.getParameter("cardnumber");
		String cardpassword = request.getParameter("cardpassword");
		
		UserBean userBean = new UserBean();
		userBean.setCardid(cardid);
		userBean.setUserid(userid);
		userBean.setCardnumber(cardnumber);
		userBean.setCardpassword(cardpassword);
		userBean.setCardtimestemp(TimeTools.getString());
		
		userServices.UpdCard(userBean);
	}
	
	/**
	 * 修改用户个人邮箱设置
	 * @param request
	 * @param suserBean
	 * @throws Exception
	 */
	public void Updusersteup(HttpServletRequest request,SessionUserBean sessionUserBean,HttpSession session) throws Exception{
		String id=(String)request.getParameter("userid");
		System.err.println("id:"+id);
		String logname=(String)request.getParameter("lognamehidden");
		String nickname=(String)request.getParameter("nickname");
		String username=(String)request.getParameter("username");
		String sex=(String)request.getParameter("sex");
		String email=(String)request.getParameter("email");
		String officephone=(String)request.getParameter("officephone");
		String otherphone=(String)request.getParameter("otherphone");
		String mobilephone=(String)request.getParameter("mobilephone");
		String faxnumber=(String)request.getParameter("faxnumber");
		String detailedaddress=(String)request.getParameter("detailedaddress");
		String postcode=(String)request.getParameter("postcode");
		String othermessage=(String)request.getParameter("othermessage");
		String photoname=(String)request.getParameter("photoname");
		
		String usersort = request.getParameter("usersort");
		String job = request.getParameter("job");
		String nationality=(String)request.getParameter("nationality");
		System.err.println("nationality:"+nationality);
		String province=(String)request.getParameter("province");
		System.err.println("province:"+province);
		String city=(String)request.getParameter("city");
		System.err.println("city:"+city);
		String state = request.getParameter("state");
		
		String usermailsetupid = request.getParameter("usermailsetupid");
		System.err.println("usermailsetupid:"+usermailsetupid);
		String mailusername = request.getParameter("mailusername");
		String mailpassword = request.getParameter("mailpassword");
		
		UserBean userBean = new UserBean();
		userBean.setId(id);
		userBean.setNickname(nickname);
		userBean.setName(username);
		userBean.setSex(sex);
		userBean.setEmail(email);
		userBean.setOfficephone(officephone);
		userBean.setOtherphone(otherphone);
		userBean.setMobilephone(mobilephone);
		userBean.setFaxnumber(faxnumber);
		userBean.setDetailedaddress(detailedaddress);
		userBean.setPostcode(postcode);
		userBean.setOthermessage(othermessage);
		userBean.setPhotoname(photoname);
		userBean.setPhotopath("");
		
		//隐藏值不修改
		userBean.setUsersort(usersort);
		userBean.setJob(job);
		userBean.setNationality(nationality);
		userBean.setProvince(province);
		userBean.setCity(city);
		userBean.setState(state);
		
		userBean.setUpduserid(sessionUserBean.getUserBean().getUserid());
		userBean.setTimestemp(TimeTools.getString());
		userBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
		
		//个人邮箱设置
		SetupMailBean setupmailBean = new SetupMailBean();
		setupmailBean.setId(usermailsetupid);
		setupmailBean.setLogname(mailusername);
		setupmailBean.setAddname(email);
		setupmailBean.setLogpassword(mailpassword);
		setupmailBean.setTimestemp(TimeTools.getString());
		setupmailBean.setUserlogname(logname);
		
//		auxiliaryMailServices.UpdMailSetup(setupmailBean);
		
		userServices.UpdSubmit(userBean,sessionUserBean.getUserBean(),sessionUserBean.getPurviewBean());
		userServices.UpdSubmitBasic(userBean);
		userServices.UpdSubmitContact(userBean);
		userServices.UpdSubmitPhoto(userBean);
		
		//修改后向session中重新赋值
		sessionUserBean.getUserBean().setEmail(email);
		
		sessionUserBean.getSetupmailBean().setAddname(email);
		sessionUserBean.getSetupmailBean().setLogname(mailusername);
		sessionUserBean.getSetupmailBean().setLogpassword(mailpassword);
		
		session.setAttribute("sessionUserBean", sessionUserBean);
	}
	
	/**
	 * 修改个人邮箱账号密码
	 * @param request
	 * @param suserBean
	 * @throws Exception
	 */
	public void Updusermailsteup(HttpServletRequest request,SessionUserBean sessionUserBean,HttpSession session) throws Exception{
		
		String usermailsetupid = request.getParameter("usermailid");
		System.err.println("usermailsetupid:"+usermailsetupid);
		String mailusername = request.getParameter("mailusername");
		String mailpassword = request.getParameter("mailpassword");
		String addname = request.getParameter("email");
		
		//个人邮箱设置
		SetupMailBean setupmailBean = new SetupMailBean();
		setupmailBean.setId(usermailsetupid);
		setupmailBean.setAddname(addname);
		setupmailBean.setLogname(mailusername);
		setupmailBean.setLogpassword(mailpassword);
		setupmailBean.setTimestemp(TimeTools.getString());
		setupmailBean.setUserlogname(sessionUserBean.getUserBean().getLogname());
		
		UserBean userBean = new UserBean();
		userBean.setId(sessionUserBean.getUserBean().getId());
		userBean.setEmail(addname);
		
		userServices.UpdUserMail(userBean);
		
//		auxiliaryMailServices.UpdUserMailSetup(setupmailBean);
		
		//修改后向session中重新赋值
		sessionUserBean.getUserBean().setEmail(addname);
		
		sessionUserBean.getSetupmailBean().setAddname(addname);
		sessionUserBean.getSetupmailBean().setLogname(mailusername);
		sessionUserBean.getSetupmailBean().setLogpassword(mailpassword);
		
		session.setAttribute("sessionUserBean", sessionUserBean);
	}
	
	/**
	 * 修改个人信息
	 * @param request
	 * @param userid
	 * @throws Exception
	 */
	public void Updpersonalinfo(HttpServletRequest request,UserBean suserBean,PurviewBean purviewBean) throws Exception{
		String id=(String)request.getParameter("personalid");
		
		String nickname=(String)request.getParameter("nickname");
		nickname = (nickname == null)?"":nickname;
//		String username=(String)request.getParameter("username");
//		username = (username == null)?"":username;
		String sex=(String)request.getParameter("sex");
		sex = (sex == null)?"":sex;
		String usersort=(String)request.getParameter("usersort");
		usersort = (usersort == null)?"":usersort;
		String job=(String)request.getParameter("job");
		job = (job == null)?"":job;
		String deptid=(String)request.getParameter("deptid");
		deptid = (deptid == null)?"":deptid;
//		String email=(String)request.getParameter("email");
//		email = (email == null)?"":email;
		String otheremail=(String)request.getParameter("otheremail");
		otheremail = (otheremail == null)?"":otheremail;
		String officephone=(String)request.getParameter("officephone");
		officephone = (officephone == null)?"":officephone;
		String otherphone=(String)request.getParameter("otherphone");
		otherphone = (otherphone == null)?"":otherphone;
		String mobilephone=(String)request.getParameter("mobilephone");
		mobilephone = (mobilephone == null)?"":mobilephone;
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
		String othermessage=(String)request.getParameter("othermessage");
		othermessage = (othermessage == null)?"":othermessage;
		String photoname=(String)request.getParameter("photoname");
		photoname = (photoname == null)?"":photoname;
		String state=(String)request.getParameter("state");
		state = (state == null)?"":state;
		UserBean userBean=userServices.View(new Integer(id).intValue());
		
		userBean.setId(id);
		userBean.setNickname(nickname);
//		userBean.setName(username);
		userBean.setSex(sex);
		userBean.setUsersort(usersort);
		userBean.setJob(job);
		userBean.setDeptid(deptid);
//		userBean.setEmail(email);
		userBean.setOtheremail(otheremail);
		userBean.setOfficephone(officephone);
		userBean.setOtherphone(otherphone);
		userBean.setMobilephone(mobilephone);
		userBean.setFaxnumber(faxnumber);
		userBean.setNationality(nationality);
		userBean.setProvince(province);
		userBean.setCity(city);
		userBean.setDetailedaddress(detailedaddress);
		userBean.setPostcode(postcode);
		userBean.setOthermessage(othermessage);
		userBean.setPhotoname(photoname);
		userBean.setPhotopath("");
		userBean.setState("2");
		
		userBean.setUpduserid(suserBean.getUserid());
		userBean.setTimestemp(TimeTools.getString());
		userBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
		// 当前登录用户权限不够,使用系统管理员
		UserBean puserBean = new UserBean();
		puserBean.setLogname("admin");
		puserBean.setPassword("admin");
		
		userServices.UpdSubmit(userBean,puserBean,purviewBean);
		userServices.UpdSubmitBasic(userBean);
		userServices.UpdSubmitContact(userBean);
		userServices.UpdSubmitPhoto(userBean);
	}
	
	/*
	 * 判断用户登录名是否已存在
	 */
	public boolean checkLoginName(String loginname) throws Exception{
		int result=userServices.ValidationLogname(loginname);
		System.err.println("result:"+result);
		if(result>0)
			return true;
		else
			return false;
	}

}
