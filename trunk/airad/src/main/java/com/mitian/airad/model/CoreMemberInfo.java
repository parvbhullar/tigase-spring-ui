package com.mitian.airad.model;

import java.math.BigDecimal;
import java.util.Date;

import com.mitian.airad.common.constant.MemberConstant;
import com.mitian.airad.web.auth.roles.BaseRole;
import com.mitian.airad.web.auth.roles.RoleFactory;

/**
 * 会员基本信息
 * 
 * @author chenji
 */
public class CoreMemberInfo {
    // 会员角色
    private BaseRole role;
    // 登录控制信息
    private LogonInfo logonInfo;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 广告id
     */
    private Integer advertiserId;
    /**
     * 会员email
     */
    private String email;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 通讯地址
     */
    private String address;
    /**
     * 邮政编码
     */
    private String zip;
    /**
     * QQ
     */
    private String qq;
    /**
     * MSN
     */
    private String msn;
    /**
     * 固定电话
     */
    private String phone;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     *注册时间
     */
    private Date registerTime;
    /**
     *激活时间
     */
    private Date activeTime;
    /**
     *激活码
     */
    private String activeCode;
    /**
     *会员类型 '0-无角色 1-广告商 2-开发者 3-广告商开发者 4-代理商 5-店铺主';
     */
    private Integer memberType;
    /**
     *会员状态
     */
    private String memberStatus;
    /**
     *注册ip地址
     */
    private String ipAddress;
    /**
     *更新时间
     */
    private Date updTime;
    /**
     *删除人
     */
    private String delOper;
    /**
     *删除时间
     */
    private Date delTime;
    /**
     *删除标记
     */
    private String delFlag;
    private String accId;
    /** 点击数 */
    private Integer clickNum;
    /** 展示数 */
    private Integer showNum;
    /** 收益 */
    private BigDecimal offer;
    /** 关联Id */
    private Integer relationId;

    /** 验证码 **/
    private String inviteCode;

    private String urlName;

    /**
     * 推广id
     */
    private int promotionId;

    /**
     * 收益分成，BSS_PERSONAL_SHARE表字段
     */
    private String sharePercent;

    /**
     * @return the accId
     */
    public String getAccId() {
        return accId;
    }

    /**
     * @param accId the accId to set
     */
    public void setAccId(String accId) {
        this.accId = accId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Integer advertiserId) {
        this.advertiserId = advertiserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public Integer getMemberType() {
        return memberType;
    }

    public String getMemberTypeShowName() {
        if (role == null) {
            role = RoleFactory.getRole(memberType);
        }
        return role.getShowName();
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public String getDelOper() {
        return delOper;
    }

    public void setDelOper(String delOper) {
        this.delOper = delOper;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return the clickNum
     */
    public Integer getClickNum() {
        return clickNum;
    }

    /**
     * @param clickNum the clickNum to set
     */
    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    /**
     * @return the showNum
     */
    public Integer getShowNum() {
        return showNum;
    }

    /**
     * @param showNum the showNum to set
     */
    public void setShowNum(Integer showNum) {
        this.showNum = showNum;
    }

    /**
     * @return the offer
     */
    public BigDecimal getOffer() {
        return offer;
    }

    /**
     * @param offer the offer to set
     */
    public void setOffer(BigDecimal offer) {
        this.offer = offer;
    }

    /**
     * @return the relationId
     */
    public Integer getRelationId() {
        return relationId;
    }

    /**
     * @param relationId the relationId to set
     */
    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    /**
     * 判断用户是否已经激活
     * 
     * @return
     */
    public boolean isActive() {
        return MemberConstant.ACTIVE_CODE_ACTIVED.equals(activeCode);
    }

    /**
     * @param inviteCode the inviteCode to set
     */
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    /**
     * @return the inviteCode
     */
    public String getInviteCode() {
        return inviteCode;
    }

    /**
     * @param urlName the urlName to set
     */
    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    /**
     * @return the urlName
     */
    public String getUrlName() {
        return urlName;
    }

    /**
     * @param promotionId the promotionId to set
     */
    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    /**
     * @return the promotionId
     */
    public int getPromotionId() {
        return promotionId;
    }

    /**
     * @return the sharePercent
     */
    public String getSharePercent() {
        return sharePercent;
    }

    /**
     * @param sharePercent the sharePercent to set
     */
    public void setSharePercent(String sharePercent) {
        this.sharePercent = sharePercent;
    }

    /**
     * @return the role
     */
    public BaseRole getRole() {
        if (role == null) {
            role = RoleFactory.getRole(memberType);
        }
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(BaseRole role) {
        this.role = role;
    }

    /**
     * 会员角色初始化
     */
    public void selfInitRole() {
        role = RoleFactory.getRole(memberType);
    }

    /**
     * @return the logonInfo
     */
    public LogonInfo getLogonInfo() {
        return logonInfo;
    }

    /**
     * @param logonInfo the logonInfo to set
     */
    public void setLogonInfo(LogonInfo logonInfo) {
        this.logonInfo = logonInfo;
    }

    /**
     * 判断会员状态是否冻结
     * 
     * @return
     */
    public boolean isForzen() {
        return com.mitian.airad.Constants.MEMBER_STATUS_FREEZE.equals(memberStatus);
    }

}
