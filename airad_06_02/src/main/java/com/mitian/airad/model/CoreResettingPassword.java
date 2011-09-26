package com.mitian.airad.model;

import java.util.Date;

/**
 * CoreResettingPassword.java
 * 
 * @author baojun
 */
public class CoreResettingPassword {
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_RESETTING_PASSWORD.RESETTING_ID
     * 
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    private Integer resettingId;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_RESETTING_PASSWORD.MEMBER_ID
     * 
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    private Long memberId;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_RESETTING_PASSWORD.RESETTING_TIME
     * 
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    private Date resettingTime;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_RESETTING_PASSWORD.SEND_COUNT
     * 
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    private Integer sendCount;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_RESETTING_PASSWORD.ADD_OPER
     * 
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    private String addOper;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_RESETTING_PASSWORD.ADD_TIME
     * 
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    private Date addTime;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_RESETTING_PASSWORD.STATUS
     * 
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    private String status;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_RESETTING_PASSWORD.RESETTING_TYPE
     * 
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    private String resettingType;

    /**
     * 邮件发送密文
     */
    private String ciphertext;

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_RESETTING_PASSWORD.RESETTING_ID
     * 
     * @return the value of CORE_RESETTING_PASSWORD.RESETTING_ID
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public Integer getResettingId() {
        return resettingId;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_RESETTING_PASSWORD.RESETTING_ID
     * 
     * @param resettingId the value for CORE_RESETTING_PASSWORD.RESETTING_ID
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public void setResettingId(Integer resettingId) {
        this.resettingId = resettingId;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_RESETTING_PASSWORD.MEMBER_ID
     * 
     * @return the value of CORE_RESETTING_PASSWORD.MEMBER_ID
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_RESETTING_PASSWORD.MEMBER_ID
     * 
     * @param memberId the value for CORE_RESETTING_PASSWORD.MEMBER_ID
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_RESETTING_PASSWORD.RESETTING_TIME
     * 
     * @return the value of CORE_RESETTING_PASSWORD.RESETTING_TIME
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public Date getResettingTime() {
        return resettingTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_RESETTING_PASSWORD.RESETTING_TIME
     * 
     * @param resettingTime the value for CORE_RESETTING_PASSWORD.RESETTING_TIME
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public void setResettingTime(Date resettingTime) {
        this.resettingTime = resettingTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_RESETTING_PASSWORD.SEND_COUNT
     * 
     * @return the value of CORE_RESETTING_PASSWORD.SEND_COUNT
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public Integer getSendCount() {
        return sendCount;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_RESETTING_PASSWORD.SEND_COUNT
     * 
     * @param sendCount the value for CORE_RESETTING_PASSWORD.SEND_COUNT
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_RESETTING_PASSWORD.ADD_OPER
     * 
     * @return the value of CORE_RESETTING_PASSWORD.ADD_OPER
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public String getAddOper() {
        return addOper;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_RESETTING_PASSWORD.ADD_OPER
     * 
     * @param addOper the value for CORE_RESETTING_PASSWORD.ADD_OPER
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public void setAddOper(String addOper) {
        this.addOper = addOper;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_RESETTING_PASSWORD.ADD_TIME
     * 
     * @return the value of CORE_RESETTING_PASSWORD.ADD_TIME
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_RESETTING_PASSWORD.ADD_TIME
     * 
     * @param addTime the value for CORE_RESETTING_PASSWORD.ADD_TIME
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_RESETTING_PASSWORD.STATUS
     * 
     * @return the value of CORE_RESETTING_PASSWORD.STATUS
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_RESETTING_PASSWORD.STATUS
     * 
     * @param status the value for CORE_RESETTING_PASSWORD.STATUS
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_RESETTING_PASSWORD.RESETTING_TYPE
     * 
     * @return the value of CORE_RESETTING_PASSWORD.RESETTING_TYPE
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public String getResettingType() {
        return resettingType;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_RESETTING_PASSWORD.RESETTING_TYPE
     * 
     * @param resettingType the value for CORE_RESETTING_PASSWORD.RESETTING_TYPE
     * @ibatorgenerated Thu Feb 17 10:43:23 CST 2011
     */
    public void setResettingType(String resettingType) {
        this.resettingType = resettingType;
    }
}
