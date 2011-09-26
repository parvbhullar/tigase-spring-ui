package com.mitian.airad.dao;

import com.mitian.airad.model.LibImg;

public interface LibImgDAO {
    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_IMG
     * 
     * @ibatorgenerated Thu Mar 10 16:04:14 CST 2011
     */
    int deleteByPrimaryKey(Integer imgId);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_IMG
     * 
     * @ibatorgenerated Thu Mar 10 16:04:14 CST 2011
     */
    int insert(LibImg record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_IMG
     * 
     * @ibatorgenerated Thu Mar 10 16:04:14 CST 2011
     */
    void insertSelective(LibImg record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_IMG
     * 
     * @ibatorgenerated Thu Mar 10 16:04:14 CST 2011
     */
    LibImg selectByPrimaryKey(Integer imgId);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_IMG
     * 
     * @ibatorgenerated Thu Mar 10 16:04:14 CST 2011
     */
    int updateByPrimaryKeySelective(LibImg record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_IMG
     * 
     * @ibatorgenerated Thu Mar 10 16:04:14 CST 2011
     */
    int updateByPrimaryKeyWithBLOBs(LibImg record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_IMG
     * 
     * @ibatorgenerated Thu Mar 10 16:04:14 CST 2011
     */
    int updateByPrimaryKeyWithoutBLOBs(LibImg record);
}
