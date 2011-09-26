package com.mitian.airad.dao.impl;

import org.springframework.stereotype.Repository;

import com.mitian.airad.dao.AccIncomePayDAO;
import com.mitian.airad.dao.support.CustomSqlMapClientDaoSupport;
import com.mitian.airad.model.AccIncomePay;

/**
 * AccIncomePayDAOImpl.java
 * 
 * @author baojun
 */
@Repository
public class AccIncomePayDAOImpl extends CustomSqlMapClientDaoSupport implements AccIncomePayDAO {

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ACC_INCOME_PAY
     * 
     * @ibatorgenerated Thu Feb 17 14:53:14 CST 2011
     */
    public AccIncomePayDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ACC_INCOME_PAY
     * 
     * @ibatorgenerated Thu Feb 17 14:53:14 CST 2011
     */
    public int deleteByPrimaryKey(Integer tradeId) {
        AccIncomePay key = new AccIncomePay();
        key.setTradeId(tradeId);
        int rows = getSqlMapClientTemplate().delete("ACC_INCOME_PAY.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ACC_INCOME_PAY
     * 
     * @ibatorgenerated Thu Feb 17 14:53:14 CST 2011
     */
    public void insert(AccIncomePay record) {
        getSqlMapClientTemplate().insert("ACC_INCOME_PAY.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ACC_INCOME_PAY
     * 
     * @ibatorgenerated Thu Feb 17 14:53:14 CST 2011
     */
    public void insertSelective(AccIncomePay record) {
        getSqlMapClientTemplate().insert("ACC_INCOME_PAY.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ACC_INCOME_PAY
     * 
     * @ibatorgenerated Thu Feb 17 14:53:14 CST 2011
     */
    public AccIncomePay selectByPrimaryKey(Integer tradeId) {
        AccIncomePay key = new AccIncomePay();
        key.setTradeId(tradeId);
        AccIncomePay record =
                (AccIncomePay) getSqlMapClientTemplate().queryForObject(
                        "ACC_INCOME_PAY.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ACC_INCOME_PAY
     * 
     * @ibatorgenerated Thu Feb 17 14:53:14 CST 2011
     */
    public int updateByPrimaryKeySelective(AccIncomePay record) {
        int rows =
                getSqlMapClientTemplate().update("ACC_INCOME_PAY.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ACC_INCOME_PAY
     * 
     * @ibatorgenerated Thu Feb 17 14:53:14 CST 2011
     */
    public int updateByPrimaryKey(AccIncomePay record) {
        int rows = getSqlMapClientTemplate().update("ACC_INCOME_PAY.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
}
