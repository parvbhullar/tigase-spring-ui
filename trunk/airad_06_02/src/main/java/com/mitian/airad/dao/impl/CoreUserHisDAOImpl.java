package com.mitian.airad.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mitian.airad.dao.CoreUserHisDAO;
import com.mitian.airad.dao.support.CustomSqlMapClientDaoSupport;
import com.mitian.airad.model.CoreUserHis;

/**
 * CoreUserHisDAOImpl.java
 * 
 * @author baojun
 */
@Repository
public class CoreUserHisDAOImpl extends CustomSqlMapClientDaoSupport implements CoreUserHisDAO {

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table CORE_USER_HIS
     * 
     * @ibatorgenerated Sun Jan 30 11:18:10 CST 2011
     */
    public CoreUserHisDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table CORE_USER_HIS
     * 
     * @ibatorgenerated Sun Jan 30 11:18:10 CST 2011
     */
    public int deleteByPrimaryKey(Integer loginId) {
        CoreUserHis key = new CoreUserHis();
        key.setLoginId(loginId);
        int rows = getSqlMapClientTemplate().delete("CORE_USER_HIS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table CORE_USER_HIS
     * 
     * @ibatorgenerated Sun Jan 30 11:18:10 CST 2011
     */
    public void insert(CoreUserHis record) {
        getSqlMapClientTemplate().insert("CORE_USER_HIS.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table CORE_USER_HIS
     * 
     * @ibatorgenerated Sun Jan 30 11:18:10 CST 2011
     */
    public void insertSelective(CoreUserHis record) {
        getSqlMapClientTemplate().insert("CORE_USER_HIS.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table CORE_USER_HIS
     * 
     * @ibatorgenerated Sun Jan 30 11:18:10 CST 2011
     */
    public CoreUserHis selectByPrimaryKey(Integer loginId) {
        CoreUserHis key = new CoreUserHis();
        key.setLoginId(loginId);
        CoreUserHis record =
                (CoreUserHis) getSqlMapClientTemplate().queryForObject(
                        "CORE_USER_HIS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table CORE_USER_HIS
     * 
     * @ibatorgenerated Sun Jan 30 11:18:10 CST 2011
     */
    public int updateByPrimaryKeySelective(CoreUserHis record) {
        int rows =
                getSqlMapClientTemplate().update("CORE_USER_HIS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table CORE_USER_HIS
     * 
     * @ibatorgenerated Sun Jan 30 11:18:10 CST 2011
     */
    public int updateByPrimaryKey(CoreUserHis record) {
        int rows = getSqlMapClientTemplate().update("CORE_USER_HIS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.dao.CoreUserHisDAO#selectByMemberId(java.lang.Integer)
     */
    public CoreUserHis selectByMemberId(Long memberId) {
        List<CoreUserHis> c = getSqlMapClientTemplate().queryForList("CORE_USER_HIS.selectByMemberId", memberId);
        if (c.isEmpty()) {
            return null;
        }
        else if (c.size() == 1) {
            c.get(0).setFirstLogon(true);
            return c.get(0);
        }
        else {
            return c.get(1);
        }
    }
}
