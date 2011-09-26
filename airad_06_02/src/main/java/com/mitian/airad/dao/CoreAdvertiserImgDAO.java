package com.mitian.airad.dao;

import java.util.Map;

import com.mitian.airad.model.CoreAdvertiserImg;

public interface CoreAdvertiserImgDAO {
    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table
     * CORE_ADVERTISER_IMG
     * 
     * @ibatorgenerated Sat Feb 12 18:14:21 CST 2011
     */
    int deleteByPrimaryKey(Integer cardImgId);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table
     * CORE_ADVERTISER_IMG
     * 
     * @ibatorgenerated Sat Feb 12 18:14:21 CST 2011
     */
    void insert(CoreAdvertiserImg record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table
     * CORE_ADVERTISER_IMG
     * 
     * @ibatorgenerated Sat Feb 12 18:14:21 CST 2011
     */
    void insertSelective(CoreAdvertiserImg record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table
     * CORE_ADVERTISER_IMG
     * 
     * @ibatorgenerated Sat Feb 12 18:14:21 CST 2011
     */
    CoreAdvertiserImg selectByPrimaryKey(Integer cardImgId);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table
     * CORE_ADVERTISER_IMG
     * 
     * @ibatorgenerated Sat Feb 12 18:14:21 CST 2011
     */
    int updateByPrimaryKeySelective(CoreAdvertiserImg record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table
     * CORE_ADVERTISER_IMG
     * 
     * @ibatorgenerated Sat Feb 12 18:14:21 CST 2011
     */
    int updateByPrimaryKeyWithBLOBs(CoreAdvertiserImg record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table
     * CORE_ADVERTISER_IMG
     * 
     * @ibatorgenerated Sat Feb 12 18:14:21 CST 2011
     */
    int updateByPrimaryKeyWithoutBLOBs(CoreAdvertiserImg record);

    /**
     * 每天认证的次数
     * 
     * @param record
     * @return
     */
    Integer selectAuthentication(Map<String, String> map);

    /**
     * 根据memberId修改
     * 
     * @param record
     * @return
     */
    int updateAdvertiserImgBymemberId(CoreAdvertiserImg record);
}
