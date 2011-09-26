package com.mitian.airad.model;

import java.math.BigDecimal;
import java.util.Date;

import com.mitian.airad.CommonDef;

/**
 * 账号充值历史记录表 CoreRechargeHis.java
 * 
 * @author wangzhongwei
 */
public class CoreRechargeHis {

    private Integer id;
    /**
     * 描述
     */
    private String remark;

    private String count;

    /**
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 流水号
     */
    private String serialNum;

    /**
     * 充值金额
     */
    private BigDecimal money;

    /**
     *充值币种
     */
    private String currency;

    /**
     * 充值时间
     */
    private Date rechargeTime;

    /**
     * 充值人姓名
     */
    private String rechargeName;

    /**
     * 充值人帐户
     */
    private String account;

    /**
     * 收款行名称
     */
    private String bankName;

    /**
     * 收款行地址
     */
    private String bankAddress;

    /**
     *充值状态
     */
    private String rechargeStatus;

    /**
     * 充值方式
     */
    private String rechargeType;

    /**
     * 是否发票
     */
    private String invoice;

    /**
     * 付款行名称
     */
    private String payBankName;

    /**
     * 付款行地址
     */
    private String payBankAddr;

    /**
     * 付款人全称
     */
    private String payerName;

    /**
     * 付款人帐户
     */
    private String payerAccount;

    /**
     * 付款人地址
     */
    private String payerAddr;
    /**
     * 代理商下广告商的id
     */
    private Integer agentAdderId;
    /**
     * 广告商名称
     */
    private String email;

    /**
     * 数据库中增加转换公式(GAOWENDONG)
     */
    private String covertFormule;

    private String fromRecord;
    private String pageSize;
    private String desc;

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the fromRecord
     */
    public String getFromRecord() {
        return fromRecord;
    }

    /**
     * @param fromRecord the fromRecord to set
     */
    public void setFromRecord(String fromRecord) {
        this.fromRecord = fromRecord;
    }

    /**
     * @return the pageSize
     */
    public String getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the covertFormule
     */
    public String getCovertFormule() {
        return covertFormule;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param covertFormule the covertFormule to set
     */
    public void setCovertFormule(String covertFormule) {
        this.covertFormule = covertFormule;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the memberId
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the serialNum
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * @param serialNum the serialNum to set
     */
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * @return the money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the rechargeTime
     */
    public Date getRechargeTime() {
        return rechargeTime;
    }

    /**
     * @param rechargeTime the rechargeTime to set
     */
    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    /**
     * @return the rechargeName
     */
    public String getRechargeName() {
        return rechargeName;
    }

    /**
     * @param rechargeName the rechargeName to set
     */
    public void setRechargeName(String rechargeName) {
        this.rechargeName = rechargeName;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the bankAddress
     */
    public String getBankAddress() {
        return bankAddress;
    }

    /**
     * @param bankAddress the bankAddress to set
     */
    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    /**
     * @param rechargeStatus the rechargeStatus to set
     */
    public void setRechargeStatus(String rechargeStatus) {
        this.rechargeStatus = rechargeStatus;
    }

    /**
     * @return the rechargeStatus
     */
    public String getRechargeStatus() {
        return rechargeStatus;
    }

    /**
     * @return the invoice
     */
    public String getInvoice() {
        return invoice;
    }

    /**
     * @param invoice the invoice to set
     */
    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    /**
     * @return the payBankName
     */
    public String getPayBankName() {
        return payBankName;
    }

    /**
     * @param payBankName the payBankName to set
     */
    public void setPayBankName(String payBankName) {
        this.payBankName = payBankName;
    }

    /**
     * @return the payBankAddr
     */
    public String getPayBankAddr() {
        return payBankAddr;
    }

    /**
     * @param payBankAddr the payBankAddr to set
     */
    public void setPayBankAddr(String payBankAddr) {
        this.payBankAddr = payBankAddr;
    }

    /**
     * @return the payerName
     */
    public String getPayerName() {
        return payerName;
    }

    /**
     * @param payerName the payerName to set
     */
    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    /**
     * @return the payerAccount
     */
    public String getPayerAccount() {
        return payerAccount;
    }

    /**
     * @param payerAccount the payerAccount to set
     */
    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount;
    }

    /**
     * @return the payerAddr
     */
    public String getPayerAddr() {
        return payerAddr;
    }

    /**
     * @param payerAddr the payerAddr to set
     */
    public void setPayerAddr(String payerAddr) {
        this.payerAddr = payerAddr;
    }

    /**
     * @return the agentAdderId
     */
    public Integer getAgentAdderId() {
        return agentAdderId;
    }

    /**
     * @param agentAdderId the agentAdderId to set
     */
    public void setAgentAdderId(Integer agentAdderId) {
        this.agentAdderId = agentAdderId;
    }

    /**
     * @return the rechargeType
     */
    public String getRechargeType() {
        return rechargeType;
    }

    /**
     * @param rechargeType the rechargeType to set
     */
    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

    /**
     * 订单是否待支付
     * 
     * @return
     */
    public boolean isWaitForPay() {
        return CommonDef.rechargeHisStatus.CON_RECHARGE_WAIT.equals(rechargeStatus);
    }
}
