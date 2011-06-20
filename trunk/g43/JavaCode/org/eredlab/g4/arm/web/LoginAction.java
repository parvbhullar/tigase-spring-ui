package org.eredlab.g4.arm.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.MonitorService;
import org.eredlab.g4.arm.service.OrganizationService;
import org.eredlab.g4.arm.service.UserService;
import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.rif.util.SessionListener;
import org.eredlab.g4.rif.util.WebUtils;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

/**
 * 登录页面Action
 * 
 * @author XiongChun
 * @since 2010-01-13
 * @see BaseAction
 */
public class LoginAction extends BaseAction {

	private static Log log = LogFactory.getLog(LoginAction.class);

	private OrganizationService organizationService = (OrganizationService) super.getService("organizationService");

	private MonitorService monitorService = (MonitorService) super.getService("monitorService");

	/**
	 * 登陆页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bannerPath = getParamValue("LOGIN_WINDOW_BANNER", request);
		bannerPath = request.getContextPath() + bannerPath;
		request.setAttribute("bannerPath", bannerPath);
		request.setAttribute("sysTitle", getParamValue("SYS_TITLE", request));
		return mapping.findForward("loginView");
	}

	/**
	 * 登陆身份验证
	 * 
	 * @param
	 * @return
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		password = G4Utils.encryptBasedDes(password);
		log.info("帐户[" + account + "]正尝试登陆系统...");
		Dto dto = new BaseDto();
		dto.put("account", account);
		Dto outDto = organizationService.getUserInfo(dto);
		UserInfoVo userInfo = (UserInfoVo) outDto.get("userInfo");
		Dto jsonDto = new BaseDto();
		if (G4Utils.isEmpty(userInfo)) {
			jsonDto.put("success", new Boolean(false));
			jsonDto.put("msg", "帐号输入错误,请重新输入!");
			jsonDto.put("errorType", "1");
			log.warn("帐户[" + account + "]登陆失败.(失败原因：不存在此帐户)");
			write(jsonDto.toJson(), response);
			return mapping.findForward("");
		}
		if (!password.equals(userInfo.getPassword())) {
			jsonDto.put("success", new Boolean(false));
			jsonDto.put("msg", "密码输入错误,请重新输入!");
			jsonDto.put("errorType", "2");
			log.warn(userInfo.getUsername() + "[" + userInfo.getAccount() + "]" + "登录系统失败(失败原因：密码输入错误)");
			write(jsonDto.toJson(), response);
			return mapping.findForward("");
		}
		String multiSession = WebUtils.getParamValue("MULTI_SESSION", request);
		if ("0".equals(multiSession)) {
			Integer sessions = (Integer)g4Reader.queryForObject("countHttpSessions", account);
			if (sessions.intValue() > 0) {
				jsonDto.put("success", new Boolean(false));
				jsonDto.put("msg", "此用户已经登录,系统不允许建立多个会话连接!");
				jsonDto.put("errorType", "3");
				log.warn(userInfo.getUsername() + "[" + userInfo.getAccount() + "]" + "登录系统失败(失败原因：此用户已经登录,系统不允许建立多个会话连接)");
				write(jsonDto.toJson(), response);
				return mapping.findForward("");
			}
		}
		jsonDto.put("success", new Boolean(true));
		userInfo.setSessionID(request.getSession().getId());
		userInfo.setSessionCreatedTime(G4Utils.getCurrentTime());
		userInfo.setLoginIP(request.getRemoteAddr());
		userInfo.setExplorer(G4Utils.getClientExplorerType(request));
		super.getSessionContainer(request).setUserInfo(userInfo);
		log.info(userInfo.getUsername() + "[" + userInfo.getAccount() + "]" + "成功登录系统!创建了一个有效Session连接,会话ID:["
				+ request.getSession().getId() + "]" + G4Utils.getCurrentTime());
		SessionListener.addSession(request.getSession(), userInfo); // 保存有效Session
		if (pHelper.getValue("requestMonitor", "0").equals("1")) {
			saveLoginEvent(userInfo, request);
		}
		write(jsonDto.toJson(), response);
		return mapping.findForward("");
	}

	/**
	 * 退出登录
	 * 
	 * @param
	 * @return
	 */
	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfoVo userInfo = super.getSessionContainer(request).getUserInfo();
		if (G4Utils.isNotEmpty(userInfo)) {
			if (pHelper.getValue("requestMonitor", "0").equals("1")) {
				saveLogoutEvent(userInfo, request);
			}
			log.info(userInfo.getUsername() + "退出了系统!");
			super.getSessionContainer(request).setUserInfo(null);
		}
		if (G4Utils.isNotEmpty(request.getSession())) {
			request.getSession().invalidate();
		}
		init(mapping, form, request, response);
		return mapping.findForward("loginView");
	}

	/**
	 * 保存登录事件
	 * 
	 * @param userInfo
	 */
	private void saveLoginEvent(UserInfoVo userInfo, HttpServletRequest request) {
		Dto dto = new BaseDto();
		dto.put("account", userInfo.getAccount());
		dto.put("activetime", G4Utils.getCurrentTime());
		dto.put("userid", userInfo.getUserid());
		dto.put("username", userInfo.getUsername());
		dto.put("description", "登录系统");
		dto.put("requestpath", request.getRequestURI());
		dto.put("methodname", request.getParameter("reqCode"));
		dto.put("eventid", IDHelper.getEventID());
		monitorService.saveEvent(dto);
	}

	/**
	 * 保存退出事件
	 * 
	 * @param userInfo
	 */
	private void saveLogoutEvent(UserInfoVo userInfo, HttpServletRequest request) {
		Dto dto = new BaseDto();
		dto.put("account", userInfo.getAccount());
		dto.put("activetime", G4Utils.getCurrentTime());
		dto.put("userid", userInfo.getUserid());
		dto.put("username", userInfo.getUsername());
		dto.put("description", "退出系统");
		dto.put("requestpath", request.getRequestURI());
		dto.put("methodname", request.getParameter("reqCode"));
		dto.put("eventid", IDHelper.getEventID());
		monitorService.saveEvent(dto);
	}

	/**
	 * 注册新帐户(在线演示系统项目主页使用)
	 * 
	 * @param
	 * @return
	 */
	public ActionForward regAccount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		UserService userService = (UserService) super.getService("userService");
		Dto dto = aForm.getParamAsDto(request);
		dto.put("sex", "0");
		dto.put("deptid", "001003");
		dto.put("roleid", "10000056");
		dto.put("locked", "0");
		// 项目主页注册用户
		dto.put("usertype", "1");
		dto.put("remark", "项目主页注册用户");
		Dto outDto = userService.saveUserItem4Reg(dto);
		write(JsonHelper.encodeObject2Json(outDto), response);
		return mapping.findForward(null);
	}
}
