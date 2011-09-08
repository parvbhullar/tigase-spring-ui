package com.mitian.airad.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.CommonDef;
import com.mitian.airad.StreamAccount;
import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.dao.AccIncomePayDAO;
import com.mitian.airad.dao.BssPersonalShareDAO;
import com.mitian.airad.dao.CoreWithdrawDAO;
import com.mitian.airad.dao.SysConfigDAO;
import com.mitian.airad.model.AccIncomePay;
import com.mitian.airad.model.BssPersonalShare;
import com.mitian.airad.model.CoreAccountInfo;
import com.mitian.airad.model.CoreRechargeHis;
import com.mitian.airad.model.CoreWithdraw;
import com.mitian.airad.model.SysConfig;
import com.mitian.airad.web.auth.roles.BaseRole;

@Service
public class WithdrawService {

    @Autowired
    private CoreWithdrawDAO withdrawDao;
    @Autowired
    private AccIncomePayDAO accIncomePayDAO;
    @Autowired
    private BssPersonalShareDAO bssPersonalShareDAO;
    @Autowired
    private SysConfigDAO sysConfigDAO;

    /**
     * 查询根据id 时间排序
     */
    public List<CoreWithdraw> queryList(Map<String, Object> params) {
        return withdrawDao.queryListByOrderTime(params);
    }

    /**
     * 通过会员id查询提现历史记录列表
     * 
     * @param memberId
     * @return
     */
    public List<CoreWithdraw> queryWithdrawListByMemberId(Long memberId, int currentPage, int pageSize, String asce) {
        List<CoreWithdraw> withdrawList = new ArrayList<CoreWithdraw>();
        withdrawList = withdrawDao.selectByMemberId(memberId, currentPage, pageSize, asce);
        return withdrawList;
    }

    // 查询开发者一个人一天提款记录数--lfg添加
    public int queryCount(Long memberId) {

        return withdrawDao.queryCount(memberId);
    }

    //
    public int queryTag(Long memberId) {
        return withdrawDao.queryTag(memberId);
    }

    /**
     * 根据会员id查询提现历史记录的数量
     * 
     * @param memberId
     * @return
     */
    public int selectCountByMemberId(Long memberId) {
        int count = 0;
        count = withdrawDao.selectCountByMemberId(memberId);
        return count;
    }

    /**
     * 通过会员id查询提现历史记录列表(重载)
     * 
     * @param memberId
     * @return
     */
    public List<CoreWithdraw> queryWithdrawListByMemberId(Long memberId) {
        List<CoreWithdraw> withdrawList = new ArrayList<CoreWithdraw>();
        withdrawList = withdrawDao.selectByMemberId(memberId);
        return withdrawList;
    }

    /**
     * 选择性的添加提现申请信息
     * 
     * @param record
     * @return 申请提现id
     */
    public int insertSelective(CoreWithdraw withdraw, CoreAccountInfo coreAccountInfo) {
        withdraw.setUnionpayId(coreAccountInfo.getUnionpayId());
        withdraw.setDrawBankNum(coreAccountInfo.getOpeningBank());
        withdraw.setDrawName(coreAccountInfo.getRealName());
        Date currentTime = new Date();
        withdraw.setDrawTime(currentTime);
        // 受理状态0受理中；1已支付；2支付失败；
        withdraw.setStatus("0");
        // 流水账号生产
        String streamAccount = StreamAccount.getNumber();
        withdraw.setDrawMoneySerialId(streamAccount);
        int key = 0;
        key = withdrawDao.insertSelective(withdraw);
        return key;
    }

    public void txAddWithDraw(AccIncomePay accIncomePay, CoreWithdraw withdraw) {
        // 提现标示
        accIncomePay.setTradeType("6");
        accIncomePay.setAddTime(new Date());
        accIncomePay.setMoney(withdraw.getDrawMoney());
        // 0受理中，1受理完成，2不受理
        accIncomePay.setStatus("0");
        accIncomePayDAO.insertSelective(accIncomePay);
    }

    public void txAddWithDrawAdv(AccIncomePay accIncomePay, CoreRechargeHis rechargeHis, String type) {
        if ("self".equals(type)) {// 给自己充值
            accIncomePay.setMoney(rechargeHis.getMoney());
        }
        else {// 给广告商充值
            BigDecimal money = getMoney(rechargeHis.getMoney(), rechargeHis.getMemberId());
            accIncomePay.setMoney(money);
        }
        accIncomePay.setAddTime(new Date());
        accIncomePay.setReqTime(new Date());
        accIncomePayDAO.insertSelective(accIncomePay);
    }

    /**
     * 代理商给广告商充值支出
     * 
     * @param accIncomePay
     * @param rechargeHis
     * @param type
     */
    public void txAddWithDrawAgent(AccIncomePay accIncomePay, CoreRechargeHis rechargeHis, String type) {
        if ("self".equals(type)) {// 给自己充值
            accIncomePay.setMoney(rechargeHis.getMoney());
        }
        else {// 给广告商充值
            accIncomePay.setMoney(rechargeHis.getMoney());
        }
        accIncomePay.setAddTime(new Date());
        accIncomePay.setReqTime(new Date());
        accIncomePayDAO.insertSelective(accIncomePay);
    }

    /**
     * 代理商或者开发者给自己充值
     * 
     * @param accIncomePay
     * @param rechargeHis
     * @param role
     */
    public void txAddWithDrawAgentOrApp(AccIncomePay accIncomePay, CoreRechargeHis rechargeHis, BaseRole role) {
        /*
         * 插入两条记录，5,A代表实际扣除的钱，5，B代表实际账户得到的钱
         */
        // 扣除记录
        AccIncomePay deductRecode = accIncomePay;
        // 获得记录
        AccIncomePay getRecode = accIncomePay;
        String param = getMoneySelf(rechargeHis.getMoney(), rechargeHis.getMemberId(), role);
        BigDecimal realMoney = rechargeHis.getMoney();
        BigDecimal getMoney = new BigDecimal(0);
        double argument = 1;
        deductRecode.setMoney(realMoney);
        deductRecode.setTradeType(CommonDef.tradeType.CON_CONVERT);
        deductRecode.setIncomePayType(CommonDef.tradeType.CON_ADVERTISER_INCOME_PAY);
        deductRecode.setAddTime(new Date());
        deductRecode.setReqTime(new Date());
        accIncomePayDAO.insertSelective(deductRecode);

        if (StringUtils.isNotEmpty(param)) {
            argument = Double.valueOf(param);
            double a = realMoney.doubleValue();
            double maxMoney = a * Double.valueOf(argument);
            getMoney = new BigDecimal(maxMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        getRecode.setMoney(getMoney);
        getRecode.setTradeType(CommonDef.tradeType.CON_CONVERT);
        getRecode.setIncomePayType(CommonDef.tradeType.CON_AGENT_INCOME_PAY);
        getRecode.setAddTime(new Date());
        getRecode.setReqTime(new Date());

        accIncomePayDAO.insertSelective(getRecode);
    }

    /**
     * 代理商给旗下广告商金额转换（公式换算）
     * 
     * @param money
     * @param memberId
     * @return
     */
    public BigDecimal getMoney(BigDecimal money, Long memberId) {
        /**
         * TODO 根据公式转换金额:广告商实得金额=代理商本次实际充值金额/（1-94.5%*B）,B=代理商分成比例 代理商分成比例字段暂时查询的跟代理商给自己转换字段是一个值，有待改进
         */
        BssPersonalShare bssPersonalShare = new BssPersonalShare();
        bssPersonalShare.setShareMemberId(memberId);
        bssPersonalShare = bssPersonalShareDAO.selectByMemberID(bssPersonalShare);
        String b = "";
        if (null == bssPersonalShare) {
            SysConfig config = new SysConfig();
            config.setSysType(CommonDef.sysConfigType.AGENT_PERCENT);
            config = sysConfigDAO.selectAgentOrAppPercent(config);
            b = config.getSysVal();
        }
        else {
            b = bssPersonalShare.getSharePercent();
        }
        double a = money.doubleValue();
        double c = (1 - (0.945 * Double.valueOf(b)));
        double maxMoney = a / c;
        return new BigDecimal(maxMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 代理商、开发者给自己金额转换（公式换算后实得金额 ）
     * 
     * @param money
     * @param memberId
     * @return
     */
    public String getMoneySelf(BigDecimal money, Long memberId, BaseRole role) {
        // 根据公式转换金额
        BssPersonalShare bssPersonalShare = new BssPersonalShare();
        bssPersonalShare.setShareMemberId(memberId);
        bssPersonalShare = bssPersonalShareDAO.selectByMemberID(bssPersonalShare);
        String b = "";
        if (null == bssPersonalShare) {
            SysConfig config = new SysConfig();
            if (role.isAgents()) {// 代理商给自己转换
                config.setSysType(CommonDef.sysConfigType.AGENT_PERCENT);
                config = sysConfigDAO.selectAgentOrAppPercent(config);
            }
            else {// 开发者给自己转换
                config.setSysType(CommonDef.sysConfigType.APP_PERCENT);
                // 查询出多余一个结果
                config = sysConfigDAO.selectAgentOrAppPercent(config);
            }
            b = config.getSysVal();
        }
        else {
            b = bssPersonalShare.getSharePercent();
        }
        return b;
    }

    public static void main(String[] args) {
        // String b = 0.3 + "";
        // int a = 120;
        // double c = (1 - (0.945 * Double.valueOf(b)));
        // System.out.println(0.945 * Double.valueOf(b) + "3434");
        // System.out.println(c + "dfdfdf");
        // double money = a / c;
        BigDecimal blance = new BigDecimal("0.05").setScale(2);
        double abc = 0.22622222222222222;
        BigDecimal aa = new BigDecimal(String.valueOf(abc)).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(aa);
    }
}
