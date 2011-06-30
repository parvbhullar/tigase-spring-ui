package com.ivyinfo.system;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.ivyinfo.framework.common.validate.ValidatePattern;

public class Validate {
	
	/**
	 * 机构创建验证
	 * @param request
	 * @return jsonObj
	 * @throws Exception
	 */
	public String validOrgCreate(HttpServletRequest request) throws Exception{
		User user = new User();
		Organization organization = new Organization();
		String result = "success";
		String errreason = "";
		String name=request.getParameter("orgname");
		String orglogname=request.getParameter("orglogname");
		String orgpassword=request.getParameter("orgpassword");
		String resurePassword=request.getParameter("resurePassword");
		String email=request.getParameter("email");
		if(name==null || name.equals("")){
			result = "failure";
			errreason = "机构名称必填！";
		}else if(organization.checkOrgName(name)){
			result = "failure";
			errreason = "机构名称已存在！";
		}else if(orglogname==null || orglogname.equals("")){
			result = "failure";
			errreason = "机构登录名必填！";
		}else if(orglogname.length()<3){
			result = "failure";
			errreason = "机构登录名长度必须大于3！";
		}else if(!ValidatePattern.isValidName(orglogname)){
			result = "failure";
			errreason = "机构登录名字符只允许输入英文和数字！";
		}else if(user.checkLoginName(orglogname)){
			result = "failure";
			errreason = "机构登录名已存在！";
		}else if(orgpassword==null || orgpassword.equals("")){
			result = "failure";
			errreason = "密码必填！";
		}else if(!ValidatePattern.isValidName(orgpassword)){
			result = "failure";
			errreason = "密码只允许输入英文和数字！";
		}else if(orgpassword.length()<6){
			result = "failure";
			errreason = "密码长度不能小于6！";
		}else if(!resurePassword.equals(orgpassword)){
			result = "failure";
			errreason = "两次密码输入不一致！";
		}else if(email==null || email.equals("")){
			result = "failure";
			errreason = "电子邮箱必填！";
		}else if(!ValidatePattern.isVaildEmail(email)){
			result = "failure";
			errreason = "电子邮箱格式错误！";
		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 机构修改验证
	 * @param request
	 * @return jsonObj
	 * @throws Exception
	 */
	public String validOrgUpdate(HttpServletRequest request) throws Exception{
		Organization organization = new Organization();
		String result = "success";
		String errreason = "";
		String name=request.getParameter("orgname");
		if(name==null || name.equals("")){
			result = "failure";
			errreason = "机构名称必填！";
		}else if(organization.checkOrgName(name)){
			result = "failure";
			errreason = "机构名称已存在！";
		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 修改个人设置验证
	 * @param request
	 * @return jsonObj
	 * @throws Exception
	 */
	public String validPersonal(HttpServletRequest request) throws Exception{
		String result = "success";
		String errreason = "";
		String username=request.getParameter("username");
		String officephone = request.getParameter("officephone");
		String otherphone = request.getParameter("otherphone");
		String mobilePhone = request.getParameter("mobilephone");
		String fax = request.getParameter("faxnumber");
		String postcode = request.getParameter("postcode");
		
		if(username==null || username.equals("")){
			result = "failure";
			errreason = "用户姓名必填！";
		}else if(true){
			result = "failure";
			errreason = "办公电话格式错误！";
		}else if(true){
			result = "failure";
			errreason = "办公电话格式错误！";
		}else if(true){
			result = "failure";
			errreason = "手机格式错误！";
		}else if(fax!=null && !fax.equals("") && !ValidatePattern.isValidName(fax)){
			result = "failure";
			errreason = "传真格式错误！";
		}else if(postcode!=null && !postcode.equals("") && !ValidatePattern.isValidName(postcode)){
			result = "failure";
			errreason = "邮编格式错误！";
		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 创建用户验证
	 * @param request
	 * @return jsonObj
	 * @throws Exception
	 */
	public String validUserCreate(HttpServletRequest request) throws Exception{
		User user = new User();
		String result = "success";
		String errreason = "";
		String logname=request.getParameter("logname");
		String username=request.getParameter("username");
		String userpassword=request.getParameter("userpassword");
		String resurePassword=request.getParameter("resurePassword");
		if(logname==null || logname.equals("")){
			result = "failure";
			errreason = "用户登录名必填！";
		}else if(logname.length()<3){
			result = "failure";
			errreason = "用户登录名长度必须大于3！";
		}else if(user.checkLoginName(logname)){
			result = "failure";
			errreason = "用户登录名已存在！";
		}else if(!ValidatePattern.isValidName(logname)){
			result = "failure";
			errreason = "用户登录名字符只允许英文和数字！";
		}else if(username==null || username.equals("")){
			result = "failure";
			errreason = "用户姓名必填！";
		}else if(userpassword==null || userpassword.equals("")){
			result = "failure";
			errreason = "密码必填！";
		}else if(!ValidatePattern.isValidName(userpassword)){
			result = "failure";
			errreason = "密码只允许输入英文和数字！";
		}else if(userpassword.length()<6){
			result = "failure";
			errreason = "密码长度不能小于6！";
		}else if(!resurePassword.equals(userpassword)){
			result = "failure";
			errreason = "两次密码输入不一致！";
		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 修改用户验证
	 * @param request
	 * @return jsonObj
	 * @throws Exception
	 */
	public String validUserUpdate(HttpServletRequest request) throws Exception{
		String result = "success";
		String errreason = "";
		String username=request.getParameter("username");
		String userpassword=request.getParameter("uuserpassword");
		if(username==null || username.equals("")){
			result = "failure";
			errreason = "用户姓名必填！";
		}else if(userpassword==null || userpassword.equals("")){
			result = "failure";
			errreason = "密码必填！";
		}else if(!ValidatePattern.isValidName(userpassword)){
			result = "failure";
			errreason = "密码只允许输入英文和数字！";
		}else if(userpassword.length()<6){
			result = "failure";
			errreason = "密码长度不能小于6！";
		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 机构登录名验证
	 * @param request
	 * @return jsonObj
	 * @throws Exception
	 */
	public String validOrgLogname(HttpServletRequest request) throws Exception{
		User user=new User();
		String result = "success";
		String errreason = "";
		String orglogname=request.getParameter("orglogname");
		if(orglogname==null || orglogname.equals("")){
			result = "failure";
			errreason = "机构登录名必填！";
		}else if(orglogname.length()<3){
			result = "failure";
			errreason = "机构登录名长度必须大于3！";
		}else if(!ValidatePattern.isValidName(orglogname)){
			result = "failure";
			errreason = "机构登录名字符只允许英文和数字！";
		}else if(user.checkLoginName(orglogname)){
			result = "failure";
			errreason = "机构登录名已存在！";
		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 机构名称验证
	 * @param request
	 * @return jsonObj
	 * @throws Exception
	 */
	public String validOrgName(HttpServletRequest request) throws Exception{
		Organization organization = new Organization();
		String result = "success";
		String errreason = "";
		String orgname=request.getParameter("orgname");
		byte[] b=orgname.getBytes("ISO-8859-1"); 
		orgname=new String(b); 

		if(orgname==null || orgname.equals("")){
			result = "failure";
			errreason = "机构名称必填！";
		}else if(organization.checkOrgName(orgname)){
			result = "failure";
			errreason = "机构名称已存在！";
		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 密码验证
	 * @param request
	 * @return jsonObj
	 * @throws Exception
	 */
	public String validPassword(HttpServletRequest request) throws Exception{
		String result = "success";
		String errreason = "";
		String userpassword=request.getParameter("password");
		if(userpassword==null || userpassword.equals("")){
			result = "failure";
			errreason = "密码必填！";
		}else if(!ValidatePattern.isValidName(userpassword)){
			result = "failure";
			errreason = "密码只允许输入英文和数字！";
		}else if(userpassword.length()<6){
			result = "failure";
			errreason = "密码长度不能小于6！";
		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 密码确认验证
	 * @param request
	 * @return jsonObj
	 * @throws Exception
	 */
	public String validResurePassword(HttpServletRequest request) throws Exception{
		String result = "success";
		String errreason = "";
		String userpassword=request.getParameter("password");
		String resurePassword=request.getParameter("resurepassword");
		if(!resurePassword.equals(userpassword)){
			result = "failure";
			errreason = "两次密码输入不一致！";
		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * Email验证
	 * @param request
	 * @return jsonObj
	 * @throws Exception
	 */
	public String validEmail(HttpServletRequest request) throws Exception{
		String result = "success";
		String errreason = "";
		String email=request.getParameter("email");
		if(email==null || email.equals("")){
			result = "failure";
			errreason = "电子邮箱必填！";
		}else if(!ValidatePattern.isVaildEmail(email)){
			result = "failure";
			errreason = "电子邮箱格式错误！";
		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 用户登录名验证
	 * @param request
	 * @return jsonObj
	 * @throws Exception
	 */
	public String validUserLogname(HttpServletRequest request) throws Exception{
		User user = new User();
		String result = "success";
		String errreason = "";
		String logname = request.getParameter("logname");
		if(logname==null || logname.equals("")){
			result = "failure";
			errreason = "用户登录名必填！";
		}else if(logname.length()<3){
			result = "failure";
			errreason = "用户登录名长度必须大于3！";
		}else if(user.checkLoginName(logname)){
			result = "failure";
			errreason = "用户登录名已存在！";
		}else if(!ValidatePattern.isValidName(logname)){
			result = "failure";
			errreason = "用户登录名字符只允许英文和数字！";
		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 用户姓名验证
	 * @param request
	 * @return jsonObj
	 * @throws Exception
	 */
	public String validUserName(HttpServletRequest request) throws Exception{
		String result = "success";
		String errreason = "";
		String username = request.getParameter("username");
		if(username==null || username.equals("")){
			result = "failure";
			errreason = "用户姓名必填！";
		} 
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 电话格式验证
	 * @param phone
	 * @return
	 */
	public String validPhone(HttpServletRequest request){
		String result = "success";
		String errreason = "";
		String phone = request.getParameter("phone");
//		if(phone!=null && !phone.equals("") && !ValidatePattern.isValidPhone(phone)){
//			result = "failure";
//			errreason = "电话格式错误！";
//		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 手机格式验证
	 * @param phone
	 * @return
	 */
	public String validMobilePhone(HttpServletRequest request){
		String result = "success";
		String errreason = "";
		String mobilePhone = request.getParameter("phone");
//		System.out.println(ValidatePattern.isValidMobilePhone(mobilePhone));
//		if(mobilePhone!=null && !mobilePhone.equals("") && !ValidatePattern.isValidMobilePhone(mobilePhone)){
//			result = "failure";
//			errreason = "手机格式错误！";
//		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 传真格式验证
	 * @param phone
	 * @return
	 */
	public String validFax(HttpServletRequest request){
		String result = "success";
		String errreason = "";
		String fax = request.getParameter("fax");
//		if(fax!=null && !fax.equals("") && !ValidatePattern.isValidFax(fax)){
//			result = "failure";
//			errreason = "传真格式错误！";
//		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
	
	/**
	 * 邮编格式验证
	 * @param phone
	 * @return
	 */
	public String validPostcode(HttpServletRequest request){
		String result = "success";
		String errreason = "";
		String postcode = request.getParameter("postcode");
		if(true){
			result = "failure";
			errreason = "邮编格式错误！";
		}
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("result", result);
		jsonObj.put("reason", errreason);
		return jsonObj.toString();
	}
}
