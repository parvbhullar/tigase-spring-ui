package org.eredlab.g4.arm.web;

import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.ProjectHomeService;
import org.eredlab.g4.arm.vo.ReplyVo;
import org.eredlab.g4.arm.vo.TopicVo;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.tplengine.DefaultTemplate;
import org.eredlab.g4.ccl.tplengine.FileTemplate;
import org.eredlab.g4.ccl.tplengine.TemplateEngine;
import org.eredlab.g4.ccl.tplengine.TemplateEngineFactory;
import org.eredlab.g4.ccl.tplengine.TemplateType;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.ccl.util.GlobalConstants;
import org.eredlab.g4.rif.taglib.util.TagHelper;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

/**
 * 项目主页Action 在线演示系统里面的项目主页相关功能
 * 
 * @author XiongChun
 * @since 2010-10-30
 * @see BaseAction
 */
public class ProjectHomeAction extends BaseAction {

	private ProjectHomeService projectHomeService = (ProjectHomeService) getService("projectHomeService");

	/**
	 * 面板页面初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward forumInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("forumView");
	}

	/**
	 * 查询主题列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryTopics(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm cForm = (CommonActionForm) form;
		Dto qDto = cForm.getParamAsDto(request);
		qDto.put("locked", "0");
		if (qDto.getAsString("topictype").equals("0")) {
			qDto.remove("topictype");
		}
		List topicList = g4Reader.queryForPage("queryTopics", qDto);
		Integer count = (Integer) g4Reader.queryForObject("countTopics", qDto);
		String jsonString = JsonHelper.encodeList2PageJson(topicList, count, GlobalConstants.FORMAT_DateTime);
		write(jsonString, response);
		return mapping.findForward(null);
	}

	/**
	 * 保存新主题
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveNewTopic(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		inDto.put("content", inDto.getAsString("contentAdd"));
		inDto.put("userid", getSessionContainer(request).getUserInfo().getUserid());
		inDto.put("username", getSessionContainer(request).getUserInfo().getUsername());
		inDto.put("replyable", "1");
		Timestamp timestamp = G4Utils.getCurrentTimestamp();
		inDto.put("addtime", timestamp);
		inDto.put("updatetime", timestamp);
		inDto.put("replycount", 0);
		inDto.put("viewcount", 1);
		inDto.put("locked", 0);
		Dto outDto = projectHomeService.saveTopicItem(inDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "新主题帖发布成功!");
		write(JsonHelper.encodeObject2Json(outDto), response);
		return mapping.findForward(null);
	}

	/**
	 * 查看主题信息初始化页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward previewTopicInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm cForm = (CommonActionForm) form;
		Dto dto = new BaseDto();
		Dto qDto = cForm.getParamAsDto(request);
		projectHomeService.updateCount(qDto);
		TopicVo topicVo = (TopicVo) g4Reader.queryForObject("queryTopicsById", qDto);
		String contentString = topicVo.getContent();
		contentString = contentString.replace("'", "\\'");
		topicVo.setContent2(contentString);
		dto.put("topicVo", topicVo);
		List replyList = g4Reader.queryForList("queryReplys", qDto);
		for (int i = 0; i < replyList.size(); i++) {
			ReplyVo vo = (ReplyVo) replyList.get(i);
			String replyString = vo.getReplycontent();
			replyString = replyString.replace("'", "\\'");
			vo.setReplycontent2(G4Utils.replace4JsOutput(replyString));
		}
		dto.put("replyList", replyList);
		String flag1 = "0";
		String userid = super.getSessionContainer(request).getUserInfo().getUserid();
		String account = super.getSessionContainer(request).getUserInfo().getAccount();
		if (!account.equalsIgnoreCase("eredlab@vip.qq.com")) {
			if (topicVo.getUserid().equals(userid)) {
				// 隐藏删除
				flag1 = "1";
			} else {
				// 隐藏全部
				flag1 = "2";
			}
		}
		dto.put("flag1", flag1);
		TemplateEngine engine = TemplateEngineFactory.getTemplateEngine(TemplateType.VELOCITY);
		DefaultTemplate template = new FileTemplate();
		template.setTemplateResource(TagHelper.getTemplatePath(getClass().getName(), "TopicDetail.tpl"));
		StringWriter writer = engine.mergeTemplate(template, dto);
		write(writer.toString(), response);
		return mapping.findForward("topicDetailView");
	}

	/**
	 * 回帖数据保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveReplyTopic(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm cForm = (CommonActionForm) form;
		Dto qDto = cForm.getParamAsDto(request);
		qDto.put("userid", getSessionContainer(request).getUserInfo().getUserid());
		qDto.put("username", getSessionContainer(request).getUserInfo().getUsername());
		Timestamp timestamp = G4Utils.getCurrentTimestamp();
		qDto.put("replytime", timestamp);
		qDto.put("updatetime", timestamp);
		qDto.put("topicid", qDto.getAsString("topicidReply"));
		qDto.put("replycontent", qDto.getAsString("contentReply"));
		Dto outDto = projectHomeService.saveReplyTopic(qDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "回帖发布成功,感谢您的参与!");
		write(JsonHelper.encodeObject2Json(outDto), response);
		return mapping.findForward(null);
	}

	/**
	 * 修改主题帖
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editTopic(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm cForm = (CommonActionForm) form;
		Dto qDto = cForm.getParamAsDto(request);
		// qDto.put("updatetime", G4Utils.getCurrentTime());
		Dto outDto = projectHomeService.editTopic(qDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "主题帖修改成功!");
		write(JsonHelper.encodeObject2Json(outDto), response);
		return mapping.findForward(null);
	}

	/**
	 * 删除主题帖
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteTopic(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm cForm = (CommonActionForm) form;
		Dto qDto = cForm.getParamAsDto(request);
		Dto outDto = projectHomeService.deleteTopic(qDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "主题帖删除成功!");
		write(JsonHelper.encodeObject2Json(outDto), response);
		return mapping.findForward(null);
	}

	/**
	 * 修改回帖
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editReply(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm cForm = (CommonActionForm) form;
		Dto qDto = cForm.getParamAsDto(request);
		// qDto.put("updatetime", G4Utils.getCurrentTime());
		Dto outDto = projectHomeService.editReply(qDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "回帖修改成功!");
		write(JsonHelper.encodeObject2Json(outDto), response);
		return mapping.findForward(null);
	}

	/**
	 * 删除回帖
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteReply(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm cForm = (CommonActionForm) form;
		Dto qDto = cForm.getParamAsDto(request);
		Dto outDto = projectHomeService.deleteReply(qDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "回帖删除成功!");
		write(JsonHelper.encodeObject2Json(outDto), response);
		return mapping.findForward(null);
	}
}
