package com.mitian.airad.dao;

import com.mitian.airad.model.LibMarket;

public interface LibMarketDAO {

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_MARKET
     * 
     * @ibatorgenerated Mon Jun 20 10:49:16 CST 2011
     */
    int deleteByPrimaryKey(Integer id);

    LibMarket selectByRichId(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_MARKET
     * 
     * @ibatorgenerated Mon Jun 20 10:49:16 CST 2011
     * @return TODO
     */
    Integer insert(LibMarket record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_MARKET
     * 
     * @ibatorgenerated Mon Jun 20 10:49:16 CST 2011
     * @return TODO
     */
    Integer insertSelective(LibMarket record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_MARKET
     * 
     * @ibatorgenerated Mon Jun 20 10:49:16 CST 2011
     */
    LibMarket selectByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_MARKET
     * 
     * @ibatorgenerated Mon Jun 20 10:49:16 CST 2011
     */
    int updateByPrimaryKeySelective(LibMarket record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table LIB_MARKET
     * 
     * @ibatorgenerated Mon Jun 20 10:49:16 CST 2011
     */
    int updateByPrimaryKey(LibMarket record);
}
