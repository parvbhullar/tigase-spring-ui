package com.mitian.airad.dao;

import com.mitian.airad.model.LibCallPhone;

public interface LibCallPhoneDAO {
    public LibCallPhone selectByRichId(Integer richId);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_TAOBAO
     * 
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_TAOBAO
     * 
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    void insert(LibCallPhone record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_TAOBAO
     * 
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    Integer insertSelective(LibCallPhone record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_TAOBAO
     * 
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    LibCallPhone selectByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_TAOBAO
     * 
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    int updateByPrimaryKeySelective(LibCallPhone record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_TAOBAO
     * 
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    int updateByPrimaryKey(LibCallPhone record);
}
