/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.model;

/**
 * AgentMemberExtend.java
 * 
 * @author Administrator
 */
public class AgentMemberExtend {
    private Long memberId;
    private String email;
    private String addtime;
    private String sharePercent;
    private String shareMemo;

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

    /**
     * @return the addtime
     */
    public String getAddtime() {
        return addtime;
    }

    /**
     * @param addtime the addtime to set
     */
    public void setAddtime(String addtime) {
        if (".0".equals(addtime.substring(addtime.length() - 2))) {
            this.addtime = addtime.substring(0, addtime.length() - 2);
        }
        else {
            this.addtime = addtime;
        }
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
     * @return the shareMemo
     */
    public String getShareMemo() {
        return shareMemo;
    }

    /**
     * @param shareMemo the shareMemo to set
     */
    public void setShareMemo(String shareMemo) {
        this.shareMemo = shareMemo;
    }
}
