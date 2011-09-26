package com.mitian.airad.model;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * CoreBindInfo.java
 * 
 * @author baojun
 */
public class CoreBindInfo {
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column CORE_BIND_INFO.ID
     * 
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    private Long id;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_BIND_INFO.MEMBER_ID
     * 
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    private Long memberId;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_BIND_INFO.PLATFORM_TYPE
     * 
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    private short platformType;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_BIND_INFO.UID
     * 
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    private Long uid;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_BIND_INFO.EMAIL
     * 
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    private String email;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_BIND_INFO.USER_NAME
     * 
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    private String userName;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_BIND_INFO.USER_JSON
     * 
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    private String userJson;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_BIND_INFO.ADD_TIME
     * 
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    private Date addTime;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * CORE_BIND_INFO.UPD_TIME
     * 
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    private Date updTime;

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_BIND_INFO.ID
     * 
     * @return the value of CORE_BIND_INFO.ID
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_BIND_INFO.ID
     * 
     * @param id the value for CORE_BIND_INFO.ID
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_BIND_INFO.MEMBER_ID
     * 
     * @return the value of CORE_BIND_INFO.MEMBER_ID
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_BIND_INFO.MEMBER_ID
     * 
     * @param memberId the value for CORE_BIND_INFO.MEMBER_ID
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the platformType
     */
    public short getPlatformType() {
        return platformType;
    }

    /**
     * @param platformType the platformType to set
     */
    public void setPlatformType(short platformType) {
        this.platformType = platformType;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_BIND_INFO.UID
     * 
     * @return the value of CORE_BIND_INFO.UID
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public Long getUid() {
        return uid;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_BIND_INFO.UID
     * 
     * @param uid the value for CORE_BIND_INFO.UID
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_BIND_INFO.EMAIL
     * 
     * @return the value of CORE_BIND_INFO.EMAIL
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_BIND_INFO.EMAIL
     * 
     * @param email the value for CORE_BIND_INFO.EMAIL
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_BIND_INFO.USER_NAME
     * 
     * @return the value of CORE_BIND_INFO.USER_NAME
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_BIND_INFO.USER_NAME
     * 
     * @param userName the value for CORE_BIND_INFO.USER_NAME
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_BIND_INFO.USER_JSON
     * 
     * @return the value of CORE_BIND_INFO.USER_JSON
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public String getUserJson() {
        return userJson;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_BIND_INFO.USER_JSON
     * 
     * @param userJson the value for CORE_BIND_INFO.USER_JSON
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public void setUserJson(String userJson) {
        this.userJson = userJson;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_BIND_INFO.ADD_TIME
     * 
     * @return the value of CORE_BIND_INFO.ADD_TIME
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_BIND_INFO.ADD_TIME
     * 
     * @param addTime the value for CORE_BIND_INFO.ADD_TIME
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * CORE_BIND_INFO.UPD_TIME
     * 
     * @return the value of CORE_BIND_INFO.UPD_TIME
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public Date getUpdTime() {
        return updTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * CORE_BIND_INFO.UPD_TIME
     * 
     * @param updTime the value for CORE_BIND_INFO.UPD_TIME
     * @ibatorgenerated Wed Sep 07 16:13:32 CST 2011
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + platformType;
        result = prime * result + ((userJson == null) ? 0 : userJson.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    public boolean equals(CoreBindInfo bInfo) {
        return StringUtils.equals(email, bInfo.email) && StringUtils.equals(userName, bInfo.userName)
                && StringUtils.equals(userJson, bInfo.userJson) && platformType == bInfo.platformType;
    }
}
