package com.mitian.airad.web.controller;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.CommonDef;
import com.mitian.airad.Constants;
import com.mitian.airad.ErrorMessages;
import com.mitian.airad.model.AccIncomePay;
import com.mitian.airad.model.AccountInfoView;
import com.mitian.airad.model.CoreAccountInfo;
import com.mitian.airad.model.CoreAgentRelation;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.model.CoreRechargeHis;
import com.mitian.airad.model.CoreWithdraw;
import com.mitian.airad.service.AccountInfoService;
import com.mitian.airad.service.AgentRelationService;
import com.mitian.airad.service.MemberService;
import com.mitian.airad.service.RechargeHisService;
import com.mitian.airad.service.WithdrawService;
import com.mitian.airad.utils.CookieUtils;
import com.mitian.airad.utils.StringUtil;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.BaseRole;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.WithdrawForm;

/**
 * 提现记录controller
 * 
 * @author chenji,tangwenjun
 */
@Controller
@RequestMapping("/withdraw.do")
@RoleAuthority({RoleFactory.ROLE_DEVELOPERS, RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_AGENTS})
public class WithdrawController extends AbstractController {
    /**
     * 提现记录
     */
    @Autowired
    private WithdrawService withdrawService;
    @Autowired
    private AccountInfoService accountInfoService;
    @Autowired
    private AgentRelationService agentRelationService;
    @Autowired
    private RechargeHisService rechargeHisService;
    private static Logger logger = Logger.getLogger(WithdrawController.class);
    @Autowired
    private MemberService memberService;

    // 根据时间查询提款历史记录

    @RequestMapping(params = "action=listOrderByTime")
    public ModelAndView list(WithdrawForm form) {
        ModelAndView mv = new ModelAndView("withdraw/withdraw_list", Constants.DEFAULT_COMMAND, form);

        if (!StringUtils.isEmpty(form.getAsce())) {
            mv.addObject("flag", "flag");
        }

        // 参数
        Map<String, Object> params = new HashMap<String, Object>();
        Long memberId = getLogonMemberId();
        params.put("descFlag", form.getDesc());
        params.put("asceFlag", form.getAsce());
        params.put("pageSize", form.getPageSize());
        params.put("currentPage", form.getCurrentPage());
        params.put("memberId", memberId);
        // 查询总数
        int totalCount = withdrawService.selectCountByMemberId(memberId);
        form.setTotalCount(totalCount + "");
        List<CoreWithdraw> withdrawList = withdrawService.queryList(params);
        form.setCoreWithdrawList(withdrawList);
        /*
         * 根据memeberId找到用户金额信息
         */
        AccountInfoView accountInfoView = accountInfoService.queryAccountInfoById(memberId);
        if (accountInfoView != null) {
            if (null != accountInfoView.getMemberId()) {
                form.setAccountInfoView(accountInfoView);
            }
        }

        mv.addObject("form", form);
        mv.addObject("values", Constants.WithdrawStatus.values());
        return mv;
    }

    /**
     * 根据id查询提现历史记录列表(会员)
     * 
     * @param 会员id
     * @return 会员提现历史记录列表
     */

    @RequestMapping(params = "action=queryWithdrawListByMemberId")
    public ModelAndView queryWithdrawListByMemberId(WithdrawForm form) {
        ModelAndView mv = new ModelAndView("withdraw/withdraw_list", Constants.DEFAULT_COMMAND, form);
        if (!StringUtils.isEmpty(form.getAsce())) {
            mv.addObject("flag", "flag");
        }
        List<CoreWithdraw> withdrawList = new ArrayList<CoreWithdraw>();
        Long memberId = getLogonMemberId();
        form.setMemberId(memberId);
        // 查询总数
        int totalCount = withdrawService.selectCountByMemberId(memberId);
        form.setTotalCount(String.valueOf(totalCount));
        int currentPage = Integer.parseInt(form.getCurrentPage());
        int pageSize = Integer.parseInt(form.getPageSize());
        withdrawList = withdrawService.queryWithdrawListByMemberId(memberId, currentPage, pageSize, form.getAsce());
        form.setCoreWithdrawList(withdrawList);
        /*
         * 根据memeberId找到用户金额信息
         */
        AccountInfoView accountInfoView = accountInfoService.queryAccountInfoById(memberId);
        if (accountInfoView != null) {
            if (null != accountInfoView.getMemberId()) {
                form.setAccountInfoView(accountInfoView);
            }
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("memberId", memberId.toString());
        List<CoreAgentRelation> advertistorList = agentRelationService.queryList(map);
        form.setAdvertistorList(advertistorList);
        form.setStatus("null");
        mv.addObject("form", form);
        mv.addObject("values", Constants.WithdrawStatus.values());
        return mv;
    }

    /**
     * 根据id查询平台历史记录列表(为开发者充)
     * 
     * @param 会员id
     * @return 会员提现历史记录列表
     */
    @RequestMapping(params = "action=listRecord")
    public ModelAndView listRecord(WithdrawForm form) {
        ModelAndView mv = new ModelAndView("withdraw/withdraw_list", Constants.DEFAULT_COMMAND, form);
        // 查询总数
        String status = form.getStatus();
        Long memberId = getLogonMemberId();
        int totalCount = rechargeHisService.selectCountByMemberId(memberId, status);
        form.setTotalCount(String.valueOf(totalCount));
        // 通过memberId查询会员充值的历史记录
        int currentPage = Integer.parseInt(form.getCurrentPage());
        int pageSize = Integer.parseInt(form.getPageSize());
        String desc = form.getDesc();
        List<CoreRechargeHis> coreRechargeHisList =
                rechargeHisService.queryRechargeHisListByMemberId(memberId, currentPage, pageSize, status, desc);
        AccountInfoView accountInfoView = accountInfoService.queryAccountInfoById(memberId);
        if (accountInfoView != null) {
            if (null != accountInfoView.getMemberId()) {
                form.setAccountInfoView(accountInfoView);
            }
        }
        form.setHisList(coreRechargeHisList);
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 
     */
    @RequestMapping(params = "action=queryAgentRelation")
    public ModelAndView queryAgentRelation(WithdrawForm form) {
        ModelAndView mv = new ModelAndView("withdraw/withdraw_win");
        // 
        // 查找充值对象
        Map<String, String> map = new HashMap<String, String>();
        Long memberId = getLogonMemberId();
        map.put("memberId", memberId.toString());
        List<CoreAgentRelation> advertistorList = agentRelationService.queryList(map);
        form.setAdvertistorList(advertistorList);

        /*
         * 根据memeberId找到用户金额信息
         */
        AccountInfoView accountInfoView = new AccountInfoView();
        accountInfoView = accountInfoService.queryAccountInfoById(memberId);
        BigDecimal incomeBalance = accountInfoView.getIncomeBlance();
        mv.addObject("incomeBalance", incomeBalance);
        mv.addObject("form", form);
        mv.addObject("values", Constants.WithdrawStatus.values());
        return mv;
    }

    /**
     * 开发者、代理商资金转换
     * 
     * @param form
     * @param request
     * @return
     */
    @RequestMapping(params = "action=doRecharge")
    public ModelAndView saveOrUpdate(HttpServletRequest request, HttpServletResponse response, WithdrawForm form) {
        // 发现表单校验有错误返回增加页面
        CoreRechargeHis rechargeHis = form.getCoreRechargeHis();
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        rechargeHis.setMemberId(memberId);
        // 判断正确类型
        if (!StringUtils.isEmpty(form.getMoney())) {
            boolean b = StringUtil.bigDecimalCon(form.getMoney());
            if (!b) {
                form.getErrors().put("MONEY_ERROR", ErrorMessages.MONEY_ERROR);
                return queryWithdrawListByMemberId(form);
            }
            BigDecimal big = new BigDecimal(form.getMoney());
            if (big.compareTo(new BigDecimal(0.01)) == -1) {
                form.getErrors().put("MONEY_ERROR", ErrorMessages.MONEY_IS_ZERO);
                return queryWithdrawListByMemberId(form);
            }
            // 判断是给自己充值还是给广告商充值
            String type = form.getAdvertistorId();
            // 获得角色，主要用来区分是代理商还是开发者给自己充值
            BaseRole role = memberInfo.getRole();
            if ("self".equals(type)) {// 给自己充值
                rechargeHis.setMoney(new BigDecimal(form.getMoney()));
                /*
                 * 根据memeberId找到用户金额信息
                 */
                AccountInfoView accountInfoView = new AccountInfoView();
                /***/
                accountInfoView = accountInfoService.queryAccountInfoById(memberId);
                BigDecimal rechargeHisMoney = rechargeHis.getMoney();
                // 判断收益余额是否足够转换
                if (rechargeHisMoney.compareTo(accountInfoView.getIncomeBlance()) == 1) {
                    form.getErrors().put("BALANCE_ERROR", ErrorMessages.BALANCE_ERROR);
                    return queryWithdrawListByMemberId(form);
                }

                AccIncomePay accIncomePay = new AccIncomePay();
                accIncomePay.setMemberId(memberId);
                accIncomePay.setAddOper(getLogonEmail());
                withdrawService.txAddWithDrawAgentOrApp(accIncomePay, rechargeHis, role);

                rechargeHis.setRechargeTime(new Date());
                rechargeHis.setRechargeType(CommonDef.rechargeHisType.CON_RECHARGE);
                // 资金转换：状态添加：lfg
                rechargeHis.setSerialNum("账户内转换");
                rechargeHis.setRechargeStatus(CommonDef.rechargeHisStatus.CON_RECHARGE_SUCC);
                rechargeHisService.txCreateRecharge(rechargeHis);
                // 资金转换成功提示
                CookieUtils.setTipMessageCookie(request, response, CommonDef.withdraw.WITHDRAW_SUCCESS);
                ModelAndView mv =
                        new ModelAndView("redirect:withdraw.do?action=queryWithdrawListByMemberId",
                                Constants.DEFAULT_COMMAND, form);
                return mv;
            }
            else {// 给广告商充值
                rechargeHis.setMoney(new BigDecimal(form.getMoney()));
                /*
                 * 根据memeberId找到用户金额信息
                 */
                AccountInfoView accountInfoView = new AccountInfoView();
                accountInfoView = accountInfoService.queryAccountInfoById(memberId);
                BigDecimal rechargeHisMoney = rechargeHis.getMoney();
                // 判断账户余额是否足够转换
                // 最大转换金额
                // BigDecimal maxMoney = withdrawService.getMoney(accountInfoView.getAcceptBlance(),
                // getLogonMemberId());
                if (rechargeHisMoney.compareTo(accountInfoView.getAcceptBlance()) == 1) {
                    form.getErrors().put("BALANCE_ERROR", ErrorMessages.BALANCE_ERROR);
                    return queryWithdrawListByMemberId(form);
                }
                // 代理商支出
                AccIncomePay agentAccIncomePay = new AccIncomePay();
                agentAccIncomePay.setMemberId(memberId);
                agentAccIncomePay.setTradeType(CommonDef.tradeType.CON_PAY);
                agentAccIncomePay.setIncomePayType(CommonDef.tradeType.CON_AGENT_INCOME_PAY_OWNER);
                agentAccIncomePay.setAddOper(getLogonEmail());
                agentAccIncomePay.setAdId(StringUtil.StringToInteger(form.getAdvertistorId()));
                withdrawService.txAddWithDrawAgent(agentAccIncomePay, rechargeHis, type);

                // 广告商收益
                AccIncomePay advAccIncomePay = new AccIncomePay();
                Long advertistorId = Long.parseLong(form.getAdvertistorId());
                advAccIncomePay.setMemberId(advertistorId);
                advAccIncomePay.setTradeType(CommonDef.tradeType.CON_AGENT_RECHARGE);
                agentAccIncomePay.setAddOper(getLogonEmail());
                agentAccIncomePay.setAdId(advertistorId.intValue());
                withdrawService.txAddWithDrawAdv(advAccIncomePay, rechargeHis, type);

                // 充值历史记录
                // 资金转换：状态添加：lfg
                rechargeHis.setRechargeStatus(CommonDef.rechargeHisStatus.CON_RECHARGE_SUCC);
                rechargeHis.setMemberId(memberId);
                rechargeHis.setRechargeTime(new Date());
                rechargeHis.setRechargeType(CommonDef.rechargeHisType.CON_AGENT);
                rechargeHis.setSerialNum("账户内转换");
                rechargeHisService.txCreateRecharge(rechargeHis);

                ModelAndView mv = new ModelAndView("redirect:withdraw.do?action=queryWithdrawListByMemberId");
                return mv;
            }
        }
        else {
            form.getErrors().put("MONEY_ERROR", ErrorMessages.REQUIRED_ERROR);
            return queryWithdrawListByMemberId(form);
        }

    }

    /**
     * 显示会员申请提现页面
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=createWithdraw")
    public ModelAndView createWithdraw(WithdrawForm form) {
        ModelAndView mv = new ModelAndView("withdraw/add_withdraw", Constants.DEFAULT_COMMAND, form);
        // 判断是否是第一次提款，是就跳转到提款设置页面
        // 查询财务账户信息,为空则为第一次提款标示
        Long memberId = getLogonMemberId();
        CoreAccountInfo aiv = accountInfoService.selectAccountInfoByMemberId(memberId);
        if (aiv == null) {
            return new ModelAndView("redirect:/account.do?action=updateMemberAccountInfo");
        }

        CoreAccountInfo accountInfo = accountInfoService.selectAccountInfoByMemberId(memberId);
        form.setCoreAccountInfo(accountInfo);
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 执行会员申请提现操作
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=doCreateWithdraw", method = RequestMethod.POST)
    public ModelAndView doCreateWithdraw(HttpServletRequest request, HttpServletResponse response, WithdrawForm form) {
        ModelAndView mv = new ModelAndView("withdraw/add_withdraw", Constants.DEFAULT_COMMAND, form);

        // 检测提款次数:日期为参数：查询相同日期
        Long memberId = getLogonMemberId();
        int count = 0;
        count = withdrawService.queryCount(memberId);
        if (count == 3) {
            CookieUtils.setTipMessageCookie(request, response, CommonDef.withdraw.WITHDRAW_FAIL);
            return new ModelAndView("redirect:/withdraw.do?action=createWithdraw", Constants.DEFAULT_COMMAND, form);
        }

        int key = 0;
        // 得到账户信息bean
        CoreAccountInfo coreAccountInfo = form.getCoreAccountInfo();
        // 得到提现金额信息bean
        CoreWithdraw withdraw = form.getCoreWithdraw();
        // 判断正确类型
        if (!StringUtils.isEmpty(form.getDrawMoney())) {
            boolean b = StringUtil.bigDecimalCon(form.getDrawMoney());
            if (!b) {
                form.getErrors().put("drawMoney", ErrorMessages.MONEY_ERROR);
                return createWithdraw(form);
            }
            BigDecimal money = new BigDecimal(form.getDrawMoney());
            BigDecimal moneyCompare = new BigDecimal(100);
            if (money.compareTo(moneyCompare) == -1) {
                form.getErrors().put("drawMoney", ErrorMessages.MONEYSMALL_ERROR);
                return createWithdraw(form);
            }
            withdraw.setDrawMoney(money);
            /*
             * 根据memeberId找到用户金额信息
             */
            AccountInfoView accountInfoView = accountInfoService.queryAccountInfoById(memberId);
            BigDecimal rechargeHisMoney = withdraw.getDrawMoney();
            if (accountInfoView == null) {
                form.getErrors().put("drawMoney", ErrorMessages.BALANCE_ERROR);
                return createWithdraw(form);
            }
            if (rechargeHisMoney.compareTo(accountInfoView.getIncomeBlance()) == 1) {
                form.getErrors().put("drawMoney", ErrorMessages.BALANCE_ERROR);
                return createWithdraw(form);
            }
            String unionpayId = coreAccountInfo.getUnionpayId();
            String realName = coreAccountInfo.getRealName();
            if (StringUtils.isEmpty(realName) || StringUtils.isEmpty(unionpayId) || StringUtils.isEmpty(unionpayId)) {
                form.getErrors().put("drawMoney", ErrorMessages.WIDTH_ERROR);
                return createWithdraw(form);
            }
            withdraw.setMemberId(memberId);
            key = withdrawService.insertSelective(withdraw, coreAccountInfo);
            AccIncomePay accIncomePay = new AccIncomePay();
            accIncomePay.setMemberId(memberId);
            accIncomePay.setAddOper(getLogonEmail());
            withdrawService.txAddWithDraw(accIncomePay, withdraw);
            CookieUtils.setTipMessageCookie(request, response, CommonDef.withdraw.TIKUAN_SUCCESS);
            mv =
                    new ModelAndView("redirect:/withdraw.do?action=queryWithdrawListByMemberId",
                            Constants.DEFAULT_COMMAND, form);
            return mv;
        }
        else {
            form.getErrors().put("drawMoney", ErrorMessages.REQUIRED_ERROR);
            return createWithdraw(form);
        }
    }

    /**
     * 充值历史导出csv
     */
    @RequestMapping(params = "action=exportCSV")
    public ModelAndView exportCSV(HttpServletRequest request, HttpServletResponse response) {
        Long memberId = getLogonMemberId();
        List<CoreWithdraw> list = withdrawService.queryWithdrawListByMemberId(memberId);
        List<Map> exportData = new ArrayList<Map>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        int count = 1;
        for (CoreWithdraw withdraw : list) {
            Map rows = new LinkedHashMap<String, String>();
            rows.put("1", count++);// 做判断

            rows.put("2", withdraw.getDrawMoneySerialId());
            rows.put("3", withdraw.getDrawMoney());
            if ("0".equals(withdraw.getStatus())) {
                rows.put("4", CommonDef.CVSTB.STATUS1);
            }
            else if ("1".equals(withdraw.getStatus())) {
                rows.put("4", CommonDef.CVSTB.STATUS2);
            }
            else if ("2".equals(withdraw.getStatus())) {
                rows.put("4", CommonDef.CVSTB.STATUS3);
            }
            else {
                rows.put("4", CommonDef.CVSTB.STATUS4);
            }
            // 时间格式转化

            Date drawTime = withdraw.getDrawTime();
            if (drawTime != null) {
                rows.put("5", sdf.format(drawTime));
            }
            else {
                rows.put("5", "");
            }

            exportData.add(rows);
        }
        LinkedHashMap map = new LinkedHashMap();
        map.put("1", CommonDef.CVSTB.DrawMoneyID);
        map.put("2", CommonDef.CVSTB.DrawMoneySerialId);
        map.put("3", CommonDef.CVSTB.DrawMoney);
        map.put("4", CommonDef.CVSTB.Status);
        map.put("5", CommonDef.CVSTB.DrawTime);
        BufferedWriter csvFileOutputStream = null;
        try {
            String fileName =
                    CommonDef.reportImportName.WITHDRAW_IMPORT_NAME + "(" + StringUtil.getStringDateYear(new Date())
                            + ").csv";
            fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            OutputStream out = response.getOutputStream();//
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(out, "GB2312"), 1024);
            // 写入文件头部
            for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                csvFileOutputStream.write("\"" + propertyEntry.getValue().toString() + "\"");
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
            }
            csvFileOutputStream.newLine();
            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
                Object row = iterator.next();

                for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
                    java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                    csvFileOutputStream.write("\""
                            + BeanUtils.getProperty(row, propertyEntry.getKey().toString()).toString() + "\"");
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.write(",");
                    }
                }
                if (iterator.hasNext()) {
                    csvFileOutputStream.newLine();
                }
            }
            csvFileOutputStream.flush();
        }
        catch (Exception e) {
            logger.error("exportCSV error", e);
        }
        finally {
            try {
                csvFileOutputStream.close();
            }
            catch (IOException e) {
                logger.error("exportCSV error", e);
            }
        }
        return null;
    }

    /**
     * 充值历史导出xml
     */
    @RequestMapping(params = "action=exportXML")
    public ModelAndView exportXML(HttpServletRequest request, HttpServletResponse response) {
        Long memberId = getLogonMemberId();
        List<CoreWithdraw> list = withdrawService.queryWithdrawListByMemberId(memberId);
        Element root = new Element("list");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        int count = 1;
        for (CoreWithdraw withdraw : list) {
            Element elements = new Element("user");
            elements.setAttribute("id", "" + count++);
            elements.addContent(new Element("DrawMoneySerialId").setText(withdraw.getDrawMoneySerialId()));
            elements.addContent(new Element("DrawMoney").setText("" + withdraw.getDrawMoney()));
            for (Constants.WithdrawStatus status : Constants.WithdrawStatus.values()) {
                if (StringUtils.isNotEmpty(withdraw.getStatus())) {
                    if (withdraw.getStatus().equals(String.valueOf(status.getStatusNumber()))) {
                        withdraw.setStatus(status.getStatusName());
                    }
                }
            }
            elements.addContent(new Element("Status").setText("" + withdraw.getStatus()));
            Date drawTime = withdraw.getDrawTime();
            if (drawTime != null) {
                elements.addContent(new Element("DrawTime").setText("" + sdf.format(drawTime)));
            }
            else {
                elements.addContent(new Element("DrawTime").setText(""));
            }

            root.addContent(elements);
        }
        Document Doc = new Document(root);
        XMLOutputter XMLOut = new XMLOutputter();
        try {
            String fileName =
                    CommonDef.reportImportName.WITHDRAW_IMPORT_NAME + "(" + StringUtil.getStringDateYear(new Date())
                            + ").xml";
            fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            OutputStream out = response.getOutputStream();
            XMLOut.output(Doc, out);
        }
        catch (FileNotFoundException e) {
            logger.error("exportXML error", e);
        }
        catch (IOException e) {
            logger.error("exportXML error", e);
        }

        return null;
    }
}
