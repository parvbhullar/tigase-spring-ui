/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.Constants;
import com.mitian.airad.ErrorMessages;
import com.mitian.airad.common.codec.EncryptService;
import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.model.AccountInfoView;
import com.mitian.airad.model.CoreAgentRelation;
import com.mitian.airad.model.CoreInviteCode;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.model.CoreRechargeHis;
import com.mitian.airad.service.AgentRelationService;
import com.mitian.airad.service.InviteCodeService;
import com.mitian.airad.service.RechargeHisService;
import com.mitian.airad.utils.StringUtil;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.AgentRelationForm;
import com.mitian.airad.web.form.RechargeHisForm;

/**
 * AgentRelationController.java 代理商与广告商的关系控制类
 * 
 * @author WANGZHONGWEI
 */
@Controller
@RequestMapping("/agentRelation.do")
@RoleAuthority({RoleFactory.ROLE_AGENTS})
public class AgentRelationController extends AbstractController {

    @Autowired
    private AgentRelationService agentRelationService;
    @Autowired
    private InviteCodeService inviteCodeService;
    @Autowired
    private RechargeHisService rechargeHisService;
    @Autowired
    private EncryptService encryptService;

    /**
     * 广告商验证码生成
     * 
     * @param request
     * @return
     */
    @RequestMapping(params = "action=invitationCode")
    public ModelAndView createInvitationCode(HttpServletRequest request) {
        // 获取登录代理商的id
        // String id = request.getParameter("");
        // 获取生成规则
        String str = String.valueOf(System.currentTimeMillis());
        String s = encryptService.encrypt(str, EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.toString());
        // 向数邀请码表中插入邀请码，那个代理商邀请的id，邀请类型，添加时间，添加人，邀请的一种状态。
        CoreInviteCode cic = new CoreInviteCode();
        // Date d = new Date();
        // String ss = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss hh").format(d);
        Date date = new Date();
        cic.setMemberId(getLogonMemberId());
        cic.setCode(s);
        cic.setType(Constants.INVITE_TYPE_ADVERTISERS);
        cic.setAddOper("1");
        cic.setAddTime(date);
        cic.setInviteStatus("0");
        inviteCodeService.txCreateInviteCode(cic);
        // 邀请生成返回一个字符串。
        String urlName = "http://" + request.getServerName() + ":" + request.getLocalPort();
        String url = urlName + "/member.do?action=register&inviteCode=";
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        sb.append(s);
        request.getSession().setAttribute("invitationCode", sb);
        // 通过email发送给广告商。

        // 返回邀请成功页面。
        ModelAndView mv = new ModelAndView("redirect:/agentRelation.do?action=invitationCodePage");
        return mv;
    }

    @RequestMapping(params = "action=invitationCodePage")
    public ModelAndView createInvitationCodePage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("agentRelation/invitationCode_page");
        return mv;
    }

    /**
     * 代理商下所有广告商列表查看
     * 
     * @param request
     * @return
     */
    @RequestMapping(params = "action=agencyList")
    public ModelAndView agencyList(HttpServletRequest request, AgentRelationForm form) {
        ModelAndView mv = new ModelAndView("agentRelation/agentRelation_list", Constants.DEFAULT_COMMAND, form);
        // 默认降序
        if (StringUtils.isEmpty(form.getAsce())) {
            form.setDesc("desc");
            mv.addObject("flag", "flag");
        }
        else {
            mv.addObject("", "flag");
        }
        Long memberId = getLogonMemberId();

        form.form2domain();
        CoreAgentRelation cmi = form.getCoreAgentRelation();
        // 根据代理商id获取代理商下面所有的广告商。
        int fromRecord = Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1);
        int pageSize = Integer.parseInt(form.getPageSize());
        CoreAgentRelation coreAgentRelation = form.getCoreAgentRelation();
        Map<String, String> params = new HashMap<String, String>();

        params.put("email", coreAgentRelation.getEmail());
        params.put("descFlag", form.getDesc());
        params.put("asceFlag", form.getAsce());
        params.put("pageSize", form.getPageSize());
        params.put("currentPage", form.getCurrentPage());
        params.put("memberId", memberId.toString());
        params.put("startTime", form.getStartTime());
        params.put("endTime", form.getEndTime());

        // List<CoreAgentRelation> agentListBean = agentRelationService.queryList(params);
        List<CoreMemberInfo> memberInfoList = new ArrayList<CoreMemberInfo>();
        int all = 0;
        if (StringUtils.isNotEmpty(form.getTimeSlotFlag())) {
            if (StringUtils.isNotEmpty(form.getStartTime()) && StringUtils.isNotEmpty(form.getEndTime())) {
                if (StringUtil.getDateY(form.getStartTime()).compareTo(StringUtil.getDateY(form.getEndTime())) > 0) {
                    form.setTimeSlotFlag("");
                    form.getErrors().put("REPORTTIME_ERROR", ErrorMessages.REPORT_REPORTTIME_ERROR);
                    return agencyList(request, form);
                }
            }
            all = agentRelationService.queryCountByTimeSlot(params);
            memberInfoList = agentRelationService.queryListByTimeSlot(params);
        }
        else {
            all = agentRelationService.countByMemberId(params);
            memberInfoList = agentRelationService.queryListForAdv(params);
        }

        form.setMemberInfoList(memberInfoList);
        form.setTotalCount(all + "");
        // 判断是否有模糊查询
        mv.addObject("email", cmi.getEmail());
        mv.addObject("p", form);
        return mv;
    }

    /**
     * 代理商给广告商充值列表查询
     * 
     * @param request
     * @return
     */
    @RequestMapping(params = "action=hisList")
    public ModelAndView historyList(AgentRelationForm form) {
        ModelAndView mv = new ModelAndView("agentRelation/history_List", Constants.DEFAULT_COMMAND, form);
        CoreAgentRelation coreAgentRelation = form.getCoreAgentRelation();
        Map<String, String> params = new HashMap<String, String>();

        params.put("email", coreAgentRelation.getEmail());
        params.put("descFlag", form.getDesc());
        params.put("asceFlag", form.getAsce());
        params.put("pageSize", form.getPageSize());
        params.put("currentPage", form.getCurrentPage());
        params.put("memberId", getLogonMemberId().toString());

        // List<CoreAgentRelation> agentListBean = agentRelationService.queryList(params);

        List<AccountInfoView> listBean = agentRelationService.queryByMemberId(params);
        form.setListBean(listBean);
        int count = 0;
        count = agentRelationService.totalCount(params);
        form.setTotalCount(String.valueOf(count));
        mv.addObject("p", form);
        return mv;
    }

    /**
     * 代理商与广告商充值页面
     * 
     * @param form
     * @param request
     * @return
     */
    @RequestMapping(params = "action=open")
    public ModelAndView openRechagePage(HttpServletRequest request, RechargeHisForm form) {
        ModelAndView mv = new ModelAndView("agentRelation/rechage_page", Constants.DEFAULT_COMMAND, form);
        // 获取广告商和代理商的id
        String relationId = request.getParameter("relationId");
        CoreAgentRelation car = agentRelationService.getAgentRelationByRelationId(Integer.parseInt(relationId));
        form.getCoreRechargeHis().setMemberId(car.getMemberId());
        form.getCoreRechargeHis().setAgentAdderId(car.getMemberId().intValue());
        return mv;
    }

    /**
     * 代理商与广告商充值页面
     * 
     * @param form
     * @param request
     * @return
     */
    @RequestMapping(params = "action=addRechange")
    public ModelAndView rechageAdd(HttpServletRequest request, RechargeHisForm form) {
        ModelAndView mv = new ModelAndView("agentRelation/rechage_page", Constants.DEFAULT_COMMAND, form);
        // 获取广告商和代理商的id
        // 选择要填入的数据进行充值
        // 返回广告商充值列表
        if (!form.getErrors().isEmpty()) {
            return mv;
        }
        form.form2domain();
        CoreRechargeHis his = form.getCoreRechargeHis();
        Date date = new Date();
        his.setRechargeTime(date);
        rechargeHisService.txCreateRechargeHis(his);
        mv = new ModelAndView("redirect:/agentRelation.do?action=agencyList");
        return mv;
    }

    /**
     * 代理商与广告商绑定关系解除
     * 
     * @param form
     * @param request
     * @return
     */
    @RequestMapping(params = "action=deleteAgentRelation")
    public ModelAndView getDeleteAgentRelation(HttpServletRequest request) {
        String id = request.getParameter("relationId");
        if (StringUtils.isNotBlank(id)) {
            CoreAgentRelation car = agentRelationService.getAgentRelationByRelationId(Integer.parseInt(id));
            car.setDelOper(getLogonEmail());
            agentRelationService.txDeleteAgentRelation(car);
        }
        ModelAndView mv = new ModelAndView("redirect:/agentRelation.do?action=agencyList");
        return mv;
    }
}
