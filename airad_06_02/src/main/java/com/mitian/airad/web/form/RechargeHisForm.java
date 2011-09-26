/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.form;

import java.util.ArrayList;
import java.util.List;

import com.mitian.airad.Constants;
import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.model.CoreAgentRelation;
import com.mitian.airad.model.CoreRechargeHis;

/**
 * RechargeHisForm.java
 * 
 * @author Administrator
 */
public class RechargeHisForm extends AbstractForm {
    /**
     * id
     */
    private String id;

    /**
     * 会员id
     */
    private String memberId;

    /**
     * 流水号
     */
    private String serialNum;

    /**
     * 充值金额
     */
    private String money;

    /**
     *充值币种
     */
    private String currency;

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
    private String agentAdderId;
    /**
     * 广告商名称
     */
    private String email;

    /**
     * 充值类型表
     */
    private List<CoreRechargeHis> hisList = new ArrayList<CoreRechargeHis>();
    /**
     * 代理商给给充值的广告商Id
     */
    private int advertistorId;

    /**
     * 代理商旗下广告商
     */
    private List<CoreAgentRelation> advertistorList = new ArrayList<CoreAgentRelation>();

    private String pageSize;

    public RechargeHisForm() {

        pageSize = Constants.DEFAULT_PAGE_SIZE_HIS;
    }

    /**
     * @return the pageSize
     */
    @Override
    public String getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    @Override
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
    private String status;

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

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId) {
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
     * @return the rechargeStatus
     */
    public String getRechargeStatus() {
        return rechargeStatus;
    }

    /**
     * @param rechargeStatus the rechargeStatus to set
     */
    public void setRechargeStatus(String rechargeStatus) {
        this.rechargeStatus = rechargeStatus;
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
    public String getAgentAdderId() {
        return agentAdderId;
    }

    /**
     * @param agentAdderId the agentAdderId to set
     */
    public void setAgentAdderId(String agentAdderId) {
        this.agentAdderId = agentAdderId;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    private CoreRechargeHis coreRechargeHis = new CoreRechargeHis();

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
     * @return the advertistorId
     */
    public int getAdvertistorId() {
        return advertistorId;
    }

    /**
     * @param advertistorId the advertistorId to set
     */
    public void setAdvertistorId(int advertistorId) {
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

    @Override
    public void form2domain() {
        if (StringUtils.isNotBlank(id)) {
            coreRechargeHis.setId(Integer.parseInt(id));
        }
        if (StringUtils.isNotBlank(memberId)) {
            coreRechargeHis.setId(Integer.parseInt(memberId));
        }
        if (StringUtils.isNotBlank(agentAdderId)) {
            coreRechargeHis.setId(Integer.parseInt(agentAdderId));
        }
        if (StringUtils.isNotBlank(email)) {
            coreRechargeHis.setEmail(email);
        }
    }

}
