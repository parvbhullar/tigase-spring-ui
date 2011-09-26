package com.mitian.airad.dao;

import java.util.List;
import java.util.Map;

import com.mitian.airad.model.AccIncome;

public interface AccIncomeDAO {
    /**
     * 开发者收益（分页）
     * 
     * @param params
     * @return
     */
    List<AccIncome> selectByAll(Map<String, String> params);

    /**
     * 开发者收益（总条数 ）
     * 
     * @param app
     * @return
     */
    int totalCount(Long memberId);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ACC_INCOME
     * 
     * @ibatorgenerated Thu Feb 17 14:53:14 CST 2011
     */
    int deleteByPrimaryKey(Integer incomeId);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ACC_INCOME
     * 
     * @ibatorgenerated Thu Feb 17 14:53:14 CST 2011
     */
    void insert(AccIncome record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ACC_INCOME
     * 
     * @ibatorgenerated Thu Feb 17 14:53:14 CST 2011
     */
    void insertSelective(AccIncome record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ACC_INCOME
     * 
     * @ibatorgenerated Thu Feb 17 14:53:14 CST 2011
     */
    AccIncome selectByPrimaryKey(Integer incomeId);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ACC_INCOME
     * 
     * @ibatorgenerated Thu Feb 17 14:53:14 CST 2011
     */
    int updateByPrimaryKeySelective(AccIncome record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ACC_INCOME
     * 
     * @ibatorgenerated Thu Feb 17 14:53:14 CST 2011
     */
    int updateByPrimaryKey(AccIncome record);
}
