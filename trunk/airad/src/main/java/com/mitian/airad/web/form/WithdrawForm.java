package com.mitian.airad.web.form;

import java.util.ArrayList;
import java.util.List;

import com.mitian.airad.model.AccountInfoView;
import com.mitian.airad.model.CoreAccountInfo;
import com.mitian.airad.model.CoreAgentRelation;
import com.mitian.airad.model.CoreRechargeHis;
import com.mitian.airad.model.CoreWithdraw;

public class WithdrawForm extends AbstractForm {

    private CoreAccountInfo coreAccountInfo;

    private Long memberId;
    // 查询提现历史记录需要使用coreWithdrawList
    private List<CoreWithdraw> coreWithdrawList;
    private CoreWithdraw coreWithdraw = new CoreWithdraw();
    /** 提现金额 */
    private String drawMoney;
    /** 余额信息 */
    private AccountInfoView accountInfoView = new AccountInfoView();
    /** 转换金额 */
    private String money;
    private String advertistorId;
    /** 代理商下广告商 */
    private List<CoreAgentRelation> advertistorList = new ArrayList<CoreAgentRelation>();
    /** 转换历史记录 */
    private CoreRechargeHis coreRechargeHis = new CoreRechargeHis();
    /** 历史记录标示 */
    private String hisFlag;
    /** 类型 */
    private String status = "null";
    /**
     * 充值类型表
     */
    private List<CoreRechargeHis> hisList = new ArrayList<CoreRechargeHis>();

    /**
     * @return the hisList
     */
    public List<CoreRechargeHis> getHisList() {
        return hisList;
    }

    /**
     * @param hisList the hisList to set
     */
    public void setHisList(List<CoreRechargeHis> hisList) {
        this.hisList = hisList;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public CoreAccountInfo getCoreAccountInfo() {
        return coreAccountInfo;
    }

    public void setCoreAccountInfo(CoreAccountInfo coreAccountInfo) {
        this.coreAccountInfo = coreAccountInfo;
    }

    public CoreWithdraw getCoreWithdraw() {
        return coreWithdraw;
    }

    public void setCoreWithdraw(CoreWithdraw coreWithdraw) {
        this.coreWithdraw = coreWithdraw;
    }

    public List<CoreWithdraw> getCoreWithdrawList() {
        return coreWithdrawList;
    }

    public void setCoreWithdrawList(List<CoreWithdraw> coreWithdrawList) {
        this.coreWithdrawList = coreWithdrawList;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the drawMoney
     */
    public String getDrawMoney() {
        return drawMoney;
    }

    /**
     * @param drawMoney the drawMoney to set
     */
    public void setDrawMoney(String drawMoney) {
        this.drawMoney = drawMoney;
    }

    /**
     * @return the accountInfoView
     */
    public AccountInfoView getAccountInfoView() {
        return accountInfoView;
    }

    /**
     * @param accountInfoView the accountInfoView to set
     */
    public void setAccountInfoView(AccountInfoView accountInfoView) {
        this.accountInfoView = accountInfoView;
    }

    /**
     * @return the money
     */
    public String getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(String money) {
        this.money = money;
    }

    /**
     * @return the advertistorId
     */
    public String getAdvertistorId() {
        return advertistorId;
    }

    /**
     * @param advertistorId the advertistorId to set
     */
    public void setAdvertistorId(String advertistorId) {
        this.advertistorId = advertistorId;
    }

    /**
     * @return the advertistorList
     */
    public List<CoreAgentRelation> getAdvertistorList() {
        return advertistorList;
    }

    /**
     * @param advertistorList the advertistorList to set
     */
    public void setAdvertistorList(List<CoreAgentRelation> advertistorList) {
        this.advertistorList = advertistorList;
    }

    /**
     * @return the coreRechargeHis
     */
    public CoreRechargeHis getCoreRechargeHis() {
        return coreRechargeHis;
    }

    /**
     * @param coreRechargeHis the coreRechargeHis to set
     */
    public void setCoreRechargeHis(CoreRechargeHis coreRechargeHis) {
        this.coreRechargeHis = coreRechargeHis;
    }

    /**
     * @return the hisFlag
     */
    public String getHisFlag() {
        return hisFlag;
    }

    /**
     * @param hisFlag the hisFlag to set
     */
    public void setHisFlag(String hisFlag) {
        this.hisFlag = hisFlag;
    }

    @Override
    public void form2domain() {
        // TODO Auto-generated method stub

    }

}
