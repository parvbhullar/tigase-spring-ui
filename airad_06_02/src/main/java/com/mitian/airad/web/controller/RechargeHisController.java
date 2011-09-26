package com.mitian.airad.web.controller;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.util.AlipayService;
import com.chinapay.util.ChinapayNotify;
import com.chinapay.util.ChinapayService;
import com.ibm.icu.util.Calendar;
import com.mitian.airad.CommonDef;
import com.mitian.airad.Constants;
import com.mitian.airad.common.constant.PayConstants;
import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.common.utils.DateFormatUtils;
import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.model.AccIncomePay;
import com.mitian.airad.model.AccountInfoView;
import com.mitian.airad.model.CoreRechargeHis;
import com.mitian.airad.service.AccIncomePayService;
import com.mitian.airad.service.RechargeHisService;
import com.mitian.airad.service.TradeService;
import com.mitian.airad.utils.CookieUtils;
import com.mitian.airad.utils.PropertiesOperateUtils;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.RechargeHisForm;
import com.service.PayService;

/**
 * 充值有什么疑问跟支付接口有关的信息跟孙悦平联系
 * 
 * @author wangzhongwei
 */
@Controller
@RequestMapping("/rechargeHis.do")
@RoleAuthority({RoleFactory.ROLE_ALL})
public class RechargeHisController extends AbstractController {
    @Autowired
    private RechargeHisService rechargeHisService;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private AccIncomePayService accIncomePayService;
    // @Autowired
    private PayService ps;

    private static Logger payLog = Logger.getLogger(Constants.LOG_APPENDER_PAYLOG);

    private static final String VERIFYTRANSRESPONSE_FALI_LOG_TEMPLETE =
            " merId: {0}, OrdId: {1}, TransAmt: {2}, CuryId: {3}, TransDate: {4}, TransType: {5}, OrderStatus: {6}, ChkValue: {7}";

    /**
     * 充值单次限额
     */
    private static final BigDecimal RECHARGE_MAX = new BigDecimal(200000);

    public void setPs(PayService ps) {
        this.ps = ps;
    }

    public PayService getPs() {
        return ps;
    }

    /**
     * 验证交易应答 银联应答结果的处理
     * 
     * @param request
     * @param response
     * @return
     * @throws ParseException
     */
    @RequestMapping(params = "action=returnUionpayUrl")
    public ModelAndView returnUionpayUrl(HttpServletRequest request, HttpServletResponse response)
            throws ParseException {

        CoreRechargeHis his = new CoreRechargeHis();
        String transDate = null;
        String merId = null;
        String ordId = null;
        String transType = null;
        String transAmt = null;
        String curyId = null;
        String chkValue = null;
        String orderStatus = null;

        // 交易日期
        transDate = request.getParameter("transdate");
        Date date = DateUtils.parseDate(transDate, new String[]{"yyyyMMdd"});
        his.setRechargeTime(date);
        // 用户id
        merId = request.getParameter("merid");

        // 交易流水号
        ordId = request.getParameter("orderno");
        his.setSerialNum(ordId);
        // 交易类型
        transType = request.getParameter("transtype");
        his.setRechargeType(transType);
        // 充值金额
        transAmt = request.getParameter("amount");
        // 金额转换

        his.setMoney(conversionMoney(transAmt));
        // 货币代码
        curyId = request.getParameter("currencycode");
        his.setCurrency(curyId);
        // 交易状态
        orderStatus = request.getParameter("status");
        if (CommonDef.rechargeHisStatus.RETURN_ORDER_STATUS_SUCC.equals(orderStatus)) {
            his.setRechargeStatus(CommonDef.rechargeHisStatus.CON_RECHARGE_SUCC);
        }

        chkValue = request.getParameter("checkvalue");
        try {
            chinapay.SecureLink t = ChinapayNotify.buildChinaPaySecureLink();
            boolean flag =
                    t.verifyTransResponse(merId, ordId, transAmt, curyId, transDate, transType, orderStatus, chkValue);
            if (!flag) {
                payLog.error("交易验证失败 orderStatus " + orderStatus + " SecureLink info: " + t.toString());
            }
            else {
                /*
                 * …... 若果验证成功，则往数据库写交易历史记录
                 */
                rechargeHisService.txUpdateRecharge(his);

            }
            payLog.warn("chinapay.SecureLink verifyTransResponse result  "
                    + flag
                    + "  "
                    + MessageFormat.format(VERIFYTRANSRESPONSE_FALI_LOG_TEMPLETE, merId, ordId, transAmt, curyId,
                            transDate, transType, orderStatus, chkValue));
        }
        catch (Exception e) {
            payLog.error("returnUionpayUrl error   "
                    + MessageFormat.format(VERIFYTRANSRESPONSE_FALI_LOG_TEMPLETE, merId, ordId, transAmt, curyId,
                            transDate, transType, orderStatus, chkValue), e);
        }
        return new ModelAndView("redirect:/rechargeHis.do?action=list");
    }

    /**
     *银联平台要跳转的页面
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "action=unionpaynotifyUrl")
    public ModelAndView unionpaynotifyUrl(HttpServletRequest request, HttpServletResponse response, RechargeHisForm form) {
        payLog.warn("enter unionpaynotifyUrl:" + toLogString(request));
        String ordId = null;
        ordId = request.getParameter("orderno");
        if (org.apache.commons.lang.StringUtils.isBlank(ordId) || !NumberUtils.isNumber(ordId)) {
            return null;
        }
        CoreRechargeHis his = rechargeHisService.queryRechargeHisByOrderId(ordId);
        if (his != null) {

            // 根据订单号查询出用户id,根据用户id acc_income_pay往充值表里写数据
            AccIncomePay accIncomePay = new AccIncomePay();
            // 会员id
            Long memberId = his.getMemberId();
            // 充值金额
            BigDecimal money = his.getMoney();
            // 写库时间
            if (money != null && memberId != null) {
                accIncomePay.setAddTime(new Date());
                accIncomePay.setMoney(money);
                accIncomePay.setMemberId(memberId);
                // 交易类型
                accIncomePay.setTradeType(CommonDef.tradeType.CON_MEMBER_RECHARGE);
                accIncomePayService.txInsertIncomePayRecord(accIncomePay);
            }

            // new ModelAndView("redirect:/rechargeHis.do?action=list");
            // 显示充值成功页面
            ModelAndView mv = new ModelAndView("rechargeHis/recharge_paysucc", Constants.DEFAULT_COMMAND, form);
            form.setMoney(money.toString());
            mv.addObject("f", form);
            return mv;
        }
        else {
            return null;
        }
    }

    /**
     * 金额转换
     */
    public BigDecimal conversionMoney(String money) {
        String m2 = new BigDecimal(money).toString().substring(new BigDecimal(money).toString().length() - 2);
        String m1 = new BigDecimal(money).toString().substring(0, new BigDecimal(money).toString().length() - 2);
        String mm = m1 + "." + m2;
        return new BigDecimal(mm);
    }

    /***
     * 十二位货币转换
     * 
     * @param m 货币字符串表示
     * @return
     */
    public String getMoney(String m) {
        String money = "";
        if (m.indexOf(".") == -1) {
            m = m + ".00";
            return getMoney(m);

        }
        else if ((m.length() - m.indexOf(".")) == 1) {
            m = m + "00";
            return getMoney(m);

        }
        else if (m.length() - m.indexOf(".") == 2) {
            m = m + "0";
            return getMoney(m);
        }
        else {
            String mm = m.substring(0, m.length() - 3) + m.substring(m.length() - 2);
            String t = "";
            for (int i = 0; i < 12 - mm.length(); i++) {
                t += "0";
            }
            money = t + mm;
            return money;

        }
    }

    /**
     * 打开支付接口
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "action=pay")
    public ModelAndView openPay(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("rechargeHis/pay");
        String orderId = rechargeHisService.getChinapayOrderId();
        String fee = request.getParameter("alimoney");
        String paymethod = request.getParameter("pay_bank");
        Date date = Calendar.getInstance().getTime();
        AlipayService ali = new AlipayService();
        setPs(ali);
        PayService ps = getPs();
        String url =
                ps.pay(orderId, fee, paymethod, date, "rechargeHis.do?action=returnUrl",
                        "rechargeHis.do?action=notifyUrl");
        request.getSession().setAttribute("pay", url);
        return mv;
    }

    /**
     * 充值订单取消入口
     */
    @RequestMapping(params = "action=cancel")
    public ModelAndView cancel(RechargeHisForm form, HttpServletRequest request, HttpServletResponse response) {
        String serialNum = StringUtils.trimToEmpty(form.getSerialNum());
        if (StringUtils.isNotBlank(serialNum)) {
            Long memberId = getLogonMemberId();
            int cancelCount = rechargeHisService.txCancelRechargeOrder(serialNum, memberId);
            if (cancelCount > 0) {
                CookieUtils.setTipMessageCookie(request, response, "订单取消成功！");
            }
        }
        return redirectView("/rechargeHis.do?action=list");
    }

    /**
     * 根据会员id查询会员充值记录列表
     * 
     * @param form
     * @param request
     * @return
     */
    @RequestMapping(params = "action=list")
    public ModelAndView list(RechargeHisForm form, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("rechargeHis/member_recharge_his_list", Constants.DEFAULT_COMMAND, form);
        // 查询余额 根据视图查询
        payLog.warn("enter queryRechargeHisListByMemberId " + toLogString(request));
        Long memberId = getLogonMemberId();
        AccountInfoView aiv = tradeService.findMoneyById(memberId);
        if (null != aiv) {
            mv.addObject("moneylittle", aiv.getAcceptBlance());
        }
        else {
            mv.addObject("moneylittle", 0);
        }
        String status = rechargeHisService.getRechargeStatus(form.getStatus());
        form.setStatus(status);
        // 查询总数
        int totalCount = rechargeHisService.selectCountByMemberId(memberId, status);
        form.setTotalCount(String.valueOf(totalCount));
        // 通过memberId查询会员充值的历史记录
        int currentPage = Integer.parseInt(form.getCurrentPage());
        int pageSize = Integer.parseInt(form.getPageSize());
        String desc = form.getDesc();
        if ("".equals(desc)) {
            desc = "0";
        }
        List<CoreRechargeHis> coreRechargeHisList =
                rechargeHisService.queryRechargeHisListByMemberId(memberId, currentPage, pageSize, status, desc);
        form.setHisList(coreRechargeHisList);
        mv.addObject("f", form);
        return mv;
    }

    /***
     * 充值历史记录列表根据时间排序
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=listRechargeHis")
    public ModelAndView listRechargeHis(RechargeHisForm form) {
        ModelAndView mv = new ModelAndView("rechargeHis/member_recharge_his_list", Constants.DEFAULT_COMMAND, form);
        String status = CommonDef.EMPTY;
        // 查询余额 根据视图查询
        Long memberId = getLogonMemberId();
        AccountInfoView aiv = tradeService.findMoneyById(memberId);
        if (null != aiv) {
            mv.addObject("moneylittle", aiv.getAcceptBlance());
        }
        else {
            mv.addObject("moneylittle", 0);
        }

        if (StringUtils.isEmpty(form.getDesc())) {
            mv.addObject("flag", "flag");

        }
        if (StringUtils.isBlank(form.getStatus())) {
            status = "1";// 充值成功的
        }
        else {
            status = form.getStatus();
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("descFlag", form.getDesc());
        params.put("asceFlag", form.getAsce());
        params.put("pageSize", form.getPageSize());
        params.put("currentPage", form.getCurrentPage());
        params.put("memberId", memberId);
        params.put("status", status);
        // 查询总数
        int totalCount = rechargeHisService.selectCountByMemberId(memberId, status);
        form.setTotalCount(totalCount + "");
        List<CoreRechargeHis> coreRechargeHisList = rechargeHisService.queryList(params);

        form.setHisList(coreRechargeHisList);
        mv.addObject("f", form);
        return mv;
    }

    /***
     * 充值历史记录列表根据时间排序
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=delRechargeHis")
    public ModelAndView delRechargeHis(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        rechargeHisService.updateByPrimaryKeySelective(Integer.parseInt(id));
        try {
            response.getWriter().print("succ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 完成未支付订单
     * 
     * @param request
     * @param form
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=completepay")
    public ModelAndView completePay(HttpServletRequest request, RechargeHisForm form) throws InvalidInfoException {
        String serialNum = StringUtils.trimToEmpty(form.getSerialNum());
        CoreRechargeHis coreRechargeHis = null;
        if (StringUtils.isNotBlank(serialNum)) {
            coreRechargeHis = rechargeHisService.getCoreRechargeHisByOrderIdAndMemberId(serialNum, getLogonMemberId());
        }
        if (coreRechargeHis == null) {
            throw new InvalidInfoException("订单记录不存在");
        }
        if (coreRechargeHis.isWaitForPay()) {
            // 银联支付服务
            ChinapayService unionpay = new ChinapayService();
            setPs(unionpay);
            PayService ps = getPs();
            // 外网URL
            String path = request.getSession().getServletContext().getRealPath(Constants.AIRAD_PROERTIES_PATH);
            String bgRetUrl =
                    PropertiesOperateUtils.getString(path, CommonDef.chinaUnionPay.CHINAUNIONPAY_BGRETURL, null);
            String pageRetUrl =
                    PropertiesOperateUtils.getString(path, CommonDef.chinaUnionPay.CHINAUNIONPAY_PAGERETURL, null);
            String url =
                    ps.pay(coreRechargeHis.getSerialNum(), getMoney(coreRechargeHis.getMoney().toPlainString()),
                            "POST", coreRechargeHis.getRechargeTime(), bgRetUrl
                                    + PayConstants.PAY_RETURNURL_QUERYSTRING, pageRetUrl
                                    + PayConstants.PAY_NOTIFYURL_QUERYSTRING);
            ModelAndView mv = new ModelAndView("rechargeHis/recharge_pay", Constants.DEFAULT_COMMAND, form);
            mv.addObject("pay", url);
            mv.addObject("f", coreRechargeHis);
            return mv;
        }
        else {
            form.getErrors().put("order", "请检查订单状态是否已支付");
            return forwardView("/rechargeHis.do?action=list");
        }

    }

    /**
     * 获取充值金额 修改
     * 
     * @param request
     * @param form
     * @return
     */
    @RequestMapping(params = "action=rechargePay")
    public ModelAndView queryPay(HttpServletRequest request, RechargeHisForm form) throws FileNotFoundException {

        String money = org.apache.commons.lang.StringUtils.trimToEmpty(request.getParameter("money"));
        if (org.apache.commons.lang.StringUtils.isEmpty(money)) {
            form.getErrors().put("money", "请输入充值金额");
            return list(form, request);
        }
        if (!NumberUtils.isNumber(money)) {
            form.getErrors().put("money", "请输入有效充值金额");
            return list(form, request);
        }
        if (RECHARGE_MAX.compareTo(new BigDecimal(money)) < 0) {
            form.getErrors().put("money", "单次充值不能超过200,000元");
            return list(form, request);
        }
        ModelAndView mv = null;
        if (money != null && NumberUtils.isNumber(money)) {
            BigDecimal moneyBigDecimal = new BigDecimal(money);
            CoreRechargeHis his = new CoreRechargeHis();
            his.setMoney(moneyBigDecimal);
            String orderId = rechargeHisService.getChinapayOrderId();
            Long memberId = getLogonMemberId();
            Date payTime = Calendar.getInstance().getTime();
            his.setRechargeTime(payTime);
            his.setSerialNum(orderId);
            his.setMemberId(memberId);
            // 把交易订单，用户id写到数据库
            rechargeHisService.txCreateChinaPayRecord(his);
            form.setCoreRechargeHis(his);

            // 银联支付服务
            ChinapayService unionpay = new ChinapayService();
            setPs(unionpay);
            PayService ps = getPs();
            // 外网URL
            String path = request.getSession().getServletContext().getRealPath(Constants.AIRAD_PROERTIES_PATH);
            String bgRetUrl =
                    PropertiesOperateUtils.getString(path, CommonDef.chinaUnionPay.CHINAUNIONPAY_BGRETURL, null);
            String pageRetUrl =
                    PropertiesOperateUtils.getString(path, CommonDef.chinaUnionPay.CHINAUNIONPAY_PAGERETURL, null);
            String url =
                    ps.pay(orderId, getMoney(money), "POST", payTime,
                            bgRetUrl + PayConstants.PAY_RETURNURL_QUERYSTRING, pageRetUrl
                                    + PayConstants.PAY_NOTIFYURL_QUERYSTRING);
            mv = new ModelAndView("rechargeHis/recharge_pay", Constants.DEFAULT_COMMAND, form);
            mv.addObject("pay", url);
            mv.addObject("f", form.getCoreRechargeHis());

        }
        else {
            mv = new ModelAndView("redirect:/rechargeHis.do?action=list");
        }

        return mv;
    }

    /**
     * 打开支付页面
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "action=taobaoPay")
    public ModelAndView taobaoPay() {
        ModelAndView mv = new ModelAndView("rechargeHis/taobao_pay");
        return mv;
    }

    /* *
     * 功能：付完款后跳转的页面（页面跳转同步通知页面） 版本：3.1 日期：2010-11-01 说明： 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
     * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。 //***********页面功能说明*********** 该页面可在本机电脑测试
     * 该页面称作“页面跳转同步通知页面”，是由支付宝服务器同步调用，可当作是支付完成后的提示信息页，如“您的某某某订单，多少金额已支付成功”。 可放入HTML等美化页面的代码和订单交易完成后的数据库更新程序代码
     * TRADE_FINISHED(表示交易已经成功结束，为普通即时到帐的交易状态成功标识); TRADE_SUCCESS(表示交易已经成功结束，为高级即时到帐的交易状态成功标识);
     * //********************************
     */
    @RequestMapping(params = "action=returnUrl")
    public ModelAndView returnUrl(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("rechargeHis/return_url");
        return mv;
    }

    /* *
     * 功能：支付宝主动通知调用的页面（服务器异步通知页面） 版本：3.1 日期：2010-11-01 说明： 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
     * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。 //***********页面功能说明*********** 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
     * 该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。 该页面调试工具请使用写文本函数log_result，该函数已被默认开启
     * TRADE_FINISHED(表示交易已经成功结束，通用即时到帐反馈的交易状态成功标志); TRADE_SUCCESS(表示交易已经成功结束，高级即时到帐反馈的交易状态成功标志);
     * 该通知页面主要功能是：对于返回页面（return_url.php）做补单处理。如果没有收到该页面返回的 success 信息，支付宝会在24小时内按一定的时间策略重发通知
     * //********************************
     */
    @RequestMapping(params = "action=notifyUrl")
    public ModelAndView notifyUrl(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("rechargeHis/notify_url");
        return mv;
    }

    /**
     * 支付成功返回余额页面
     */
    @RequestMapping(params = "action=returnHistoryList")
    public ModelAndView returnHistoryList(HttpServletRequest request, HttpServletResponse response) {
        String money = request.getParameter("money");
        if (StringUtils.isBlank(money)) {
            return null;
        }
        CoreRechargeHis his = new CoreRechargeHis();
        long date = System.currentTimeMillis();
        String id = String.valueOf(date);
        Date d = new Date();
        his.setSerialNum(id);
        his.setMemberId(getLogonMemberId());
        his.setMoney(BigDecimal.valueOf(Long.parseLong(money)));
        // System.out.println("====================" +
        // Constants.RechargeHisRechargeType.ALIPAY.getTypeNumber());
        // his.setRechargeType("1");
        his.setRechargeTime(d);
        rechargeHisService.txCreateRecharge(his);
        return new ModelAndView("redirect:/rechargeHis.do?action=list");
    }

    /**
     * 充值历史导出csv
     */
    @RequestMapping(params = "action=exportCSV")
    public ModelAndView exportCSV(HttpServletRequest request, HttpServletResponse response) {
        String status = request.getParameter("status");
        String temp = status;
        List list = rechargeHisService.queryRechargeHisListByMemberId(getLogonMemberId(), status);
        List exportData = new ArrayList<Map>();
        LinkedHashMap map = new LinkedHashMap();
        map.put("1", CommonDef.RECHARGE.RHARGE_ID);
        map.put("2", CommonDef.RECHARGE.RHARGE_SERIALID);
        map.put("3", CommonDef.RECHARGE.RHARGE_MONEY);
        map.put("4", CommonDef.RECHARGE.RHARGE_TIME);
        if (CommonDef.rechargeHisType.CON_PLATFORM_APP.equals(status)) {
            map.put("5", CommonDef.RECHARGE.RHARGE_REMARK);
        }
        else {
            map.put("5", CommonDef.RECHARGE.RHARGE_TYPE);
        }
        if (temp.equals("1")) {
            map.put("6", CommonDef.RECHARGE.RHARGE_REMARK);
        }
        for (int j = 0; j < list.size(); j++) {
            CoreRechargeHis ch = (CoreRechargeHis) list.get(j);
            Map rows = new LinkedHashMap<String, String>();
            rows.put("1", (j + 1));// 做判断
            String num = StringUtils.trimToEmpty(ch.getSerialNum());
            rows.put("2", num);
            String mon = "0";
            if (ch.getMoney() != null) {
                mon = ch.getMoney().setScale(2, BigDecimal.ROUND_DOWN).toString();
            }
            rows.put("3", mon);
            String time = StringUtils.EMPTY;
            if (ch.getRechargeTime() == null) {
                time = DateFormatUtils.format(ch.getRechargeTime(), "yyyy-MM-dd");
            }
            rows.put("4", time);
            status = CommonDef.EMPTY;
            if (StringUtils.isNotEmpty(ch.getRechargeType())) {
                switch (Integer.parseInt(ch.getRechargeType())) {
                    case 0 :
                        status = CommonDef.RECHARGE.TRADE_TYPE0;
                        break;
                    case 1 :
                        status = CommonDef.RECHARGE.TRADE_TYPE1;
                        break;
                    case 2 :
                        status = CommonDef.RECHARGE.TRADE_TYPE2;
                        break;
                    case 4 :
                        status = ch.getRemark();
                        break;
                    default :
                        status = CommonDef.RECHARGE.TRADE_TYPE3;
                        break;
                }
            }
            if (CommonDef.rechargeHisType.CON_PLATFORM_APP.equals(temp)) {
                rows.put("5", ch.getRemark());
            }
            else {
                rows.put("5", status);
            }

            if (temp.equals("1")) {
                rows.put("6", ch.getRemark());
            }

            exportData.add(rows);
        }

        BufferedWriter csvFileOutputStream = null;
        try {
            // 处理乱码
            String time = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            String fileName = "充值历史查看(" + time + ").csv";
            fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            OutputStream out = response.getOutputStream();//
            writeCsv(map, exportData, out);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 生成csv文件
     * 
     * @param title 标题
     * @param exportData 数据
     * @param out 输出对象
     */
    @SuppressWarnings("unchecked")
    public void writeCsv(LinkedHashMap title, List exportData, OutputStream out) {
        BufferedWriter csvFileOutputStream = null;
        try {
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(out, "GB2312"), 1024);
            // 写入文件头部
            for (Iterator propertyIterator = title.entrySet().iterator(); propertyIterator.hasNext();) {
                Entry propertyEntry = (Entry) propertyIterator.next();
                if (propertyEntry == null) {
                    return;
                }
                String head = propertyEntry.getValue() == null ? "" : propertyEntry.getValue().toString();
                csvFileOutputStream.write("\"" + head + "\"");
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
            }
            csvFileOutputStream.newLine();
            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
                Object row = iterator.next();
                for (Iterator propertyIterator = title.entrySet().iterator(); propertyIterator.hasNext();) {
                    Entry propertyEntry = (Entry) propertyIterator.next();
                    if (row == null || propertyEntry == null) {
                        return;
                    }
                    String txt = propertyEntry.getKey() == null ? "" : propertyEntry.getKey().toString();
                    String text = BeanUtils.getProperty(row, txt) == null ? "" : BeanUtils.getProperty(row, txt);
                    csvFileOutputStream.write("\"" + text + "\"");
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
            e.printStackTrace();
        }
        finally {
            try {
                csvFileOutputStream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 充值历史导出xml
     */
    @RequestMapping(params = "action=exportXML")
    public ModelAndView exportXML(HttpServletRequest request, HttpServletResponse response) {
        String status = request.getParameter("status");
        String temp = CommonDef.EMPTY;
        List<CoreRechargeHis> list = rechargeHisService.queryRechargeHisListByMemberId(getLogonMemberId(), status);
        Element root = new Element("list");
        for (int j = 0; j < list.size(); j++) {
            CoreRechargeHis ch = list.get(j);
            Element elements = new Element("user");
            elements.setAttribute("id", "" + (j + 1));
            String num = StringUtils.trimToEmpty(ch.getSerialNum());
            String mon = "0";
            if (ch.getMoney() != null) {
                mon = ch.getMoney().setScale(2, BigDecimal.ROUND_DOWN).toString();
            }
            String time = StringUtils.EMPTY;
            if (ch.getRechargeTime() == null) {
                time = DateFormatUtils.format(ch.getRechargeTime(), "yyyy-MM-dd");
            }
            elements.addContent(new Element("serialNum").setText(num));
            elements.addContent(new Element("money").setText(mon));
            elements.addContent(new Element("rechargeTime").setText(time));

            if (StringUtils.isNotEmpty(ch.getRechargeType())) {
                switch (Integer.parseInt(ch.getRechargeType())) {
                    case 0 :
                        temp = CommonDef.RECHARGE.TRADE_TYPE0;
                        break;
                    case 1 :
                        temp = CommonDef.RECHARGE.TRADE_TYPE1;
                        break;
                    case 2 :
                        temp = CommonDef.RECHARGE.TRADE_TYPE2;
                        break;
                    case 4 :
                        temp = ch.getRemark();
                        break;
                    default :
                        temp = CommonDef.RECHARGE.TRADE_TYPE3;
                        break;
                }
            }
            if (CommonDef.rechargeHisType.CON_PLATFORM_APP.equals(status)) {
                elements.addContent(new Element("remark").setText(ch.getRemark()));
            }
            else {
                elements.addContent(new Element("rechargeType").setText(temp));
            }
            if (status.equals("1")) {
                elements.addContent(new Element("remark").setText(StringUtils.trimToEmpty(ch.getRemark())));
            }
            root.addContent(elements);
        }
        Document doc = new Document(root);
        XMLOutputter xmlOut = new XMLOutputter();
        try {
            // 处理乱码
            String time = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            String fileName = "充值历史查看(" + time + ").xml";
            fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            OutputStream out = response.getOutputStream();
            xmlOut.output(doc, out);
        }
        catch (FileNotFoundException e) {
            getLogger().error("exportXML error", e);
        }
        catch (IOException e) {
            getLogger().error("exportXML error", e);
        }
        return null;
    }
}
