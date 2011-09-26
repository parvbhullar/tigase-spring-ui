package com.mitian.airad.dao;

import com.mitian.airad.model.ReqWapRich;

public interface ReqWapRichDAO {
    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table REQ_WAP_RICH
     * 
     * @ibatorgenerated Mon Feb 28 14:57:58 CST 2011
     */
    int deleteByPrimaryKey(Integer guideId);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table REQ_WAP_RICH
     * 
     * @ibatorgenerated Mon Feb 28 14:57:58 CST 2011
     */
    void insert(ReqWapRich record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table REQ_WAP_RICH
     * 
     * @ibatorgenerated Mon Feb 28 14:57:58 CST 2011
     */
    void insertSelective(ReqWapRich record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table REQ_WAP_RICH
     * 
     * @ibatorgenerated Mon Feb 28 14:57:58 CST 2011
     */
    ReqWapRich selectByPrimaryKey(Integer guideId);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table REQ_WAP_RICH
     * 
     * @ibatorgenerated Mon Feb 28 14:57:58 CST 2011
     */
    int updateByPrimaryKeySelective(ReqWapRich record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table REQ_WAP_RICH
     * 
     * @ibatorgenerated Mon Feb 28 14:57:58 CST 2011
     */
    int updateByPrimaryKey(ReqWapRich record);

    /**
     * 增加广告导航页请求基础数据表
     * 
     * @param record
     * @return
     */
    int insertReqWapRich(ReqWapRich record);

    /**
     * 根据广告ID查出点击数
     * 
     * @param adId
     * @return
     */
    int findByAdIdCount(Integer adId);
}
