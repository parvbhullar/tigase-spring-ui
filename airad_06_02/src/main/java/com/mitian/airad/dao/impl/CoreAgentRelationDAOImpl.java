package com.mitian.airad.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mitian.airad.dao.CoreAgentRelationDAO;
import com.mitian.airad.dao.support.CustomSqlMapClientDaoSupport;
import com.mitian.airad.model.CoreAgentRelation;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.utils.StringUtil;

/**
 * CoreAgentRelationDAOImpl.java
 * 
 * @author baojun
 */
@Repository
public class CoreAgentRelationDAOImpl extends CustomSqlMapClientDaoSupport implements CoreAgentRelationDAO {

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table
     * CORE_AGENT_RELATION
     * 
     * @ibatorgenerated Thu Jan 20 16:39:10 CST 2011
     */
    public CoreAgentRelationDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table
     * CORE_AGENT_RELATION
     * 
     * @ibatorgenerated Thu Jan 20 16:39:10 CST 2011
     */
    public int deleteByPrimaryKey(Integer relationId) {
        CoreAgentRelation key = new CoreAgentRelation();
        key.setRelationId(relationId);
        int rows = getSqlMapClientTemplate().delete("CORE_AGENT_RELATION.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table
     * CORE_AGENT_RELATION
     * 
     * @ibatorgenerated Thu Jan 20 16:39:10 CST 2011
     */
    public void insert(CoreAgentRelation record) {
        getSqlMapClientTemplate().insert("CORE_AGENT_RELATION.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table
     * CORE_AGENT_RELATION
     * 
     * @ibatorgenerated Thu Jan 20 16:39:10 CST 2011
     */
    public void insertSelective(CoreAgentRelation record) {
        getSqlMapClientTemplate().insert("CORE_AGENT_RELATION.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table
     * CORE_AGENT_RELATION
     * 
     * @ibatorgenerated Thu Jan 20 16:39:10 CST 2011
     */
    public CoreAgentRelation selectByPrimaryKey(Integer relationId) {
        CoreAgentRelation key = new CoreAgentRelation();
        key.setRelationId(relationId);
        CoreAgentRelation record =
                (CoreAgentRelation) getSqlMapClientTemplate().queryForObject(
                        "CORE_AGENT_RELATION.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table
     * CORE_AGENT_RELATION
     * 
     * @ibatorgenerated Thu Jan 20 16:39:10 CST 2011
     */
    public int updateByPrimaryKeySelective(CoreAgentRelation record) {
        int rows =
                getSqlMapClientTemplate().update("CORE_AGENT_RELATION.ibatorgenerated_updateByPrimaryKeySelective",
                        record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table
     * CORE_AGENT_RELATION
     * 
     * @ibatorgenerated Thu Jan 20 16:39:10 CST 2011
     */
    public int updateByPrimaryKey(CoreAgentRelation record) {
        int rows = getSqlMapClientTemplate().update("CORE_AGENT_RELATION.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.dao.CoreAgentRelationDAO#pageQueryCoreAgentRelationList(int, java.lang.String)
     * 根据代理商id查询代理商下所有的广告商信息
     */
    public List<CoreMemberInfo> pageQueryCoreAgentRelationList(Map<String, String> params) {
        int pageSize = StringUtil.stringToInteger(params.get("pageSize"));
        int currentPage = StringUtil.stringToInteger(params.get("currentPage")) - 1;
        int fromRecord = pageSize * currentPage;
        params.put("fromRecord", String.valueOf(fromRecord));
        params.put("pageSize", String.valueOf(pageSize));
        List<CoreMemberInfo> memberIdList = new ArrayList<CoreMemberInfo>();
        memberIdList =
                getSqlMapClientTemplate().queryForList("CORE_AGENT_RELATION.ibatorgenerated_selectAdvertiser", params);
        return memberIdList;
    }

    public List<CoreMemberInfo> pageQueryCoreAgentRelationListByTimeSlot(Map<String, String> params) {
        int pageSize = StringUtil.stringToInteger(params.get("pageSize"));
        int currentPage = StringUtil.stringToInteger(params.get("currentPage")) - 1;
        int fromRecord = pageSize * currentPage;
        params.put("fromRecord", String.valueOf(fromRecord));
        params.put("pageSize", String.valueOf(pageSize));
        List<CoreMemberInfo> memberIdList = new ArrayList<CoreMemberInfo>();
        memberIdList =
                getSqlMapClientTemplate().queryForList(
                        "CORE_AGENT_RELATION.ibatorgenerated_selectAdvertiserByTimeSlot", params);
        return memberIdList;
    }

    public int pageQueryCountByTimeSlot(Map params) {
        List<CoreMemberInfo> memberIdList = new ArrayList<CoreMemberInfo>();
        memberIdList =
                getSqlMapClientTemplate().queryForList(
                        "CORE_AGENT_RELATION.ibatorgenerated_selectAdvertiserByTimeSlot", params);
        return memberIdList.size();
    }

    public List<CoreAgentRelation> agentRelationList(Map<String, String> map) {
        List<CoreAgentRelation> coreAgentRelation =
                getSqlMapClientTemplate().queryForList("CORE_AGENT_RELATION.ibatorgenerated_selectAllAdvertiser", map);
        return coreAgentRelation;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.dao.CoreAgentRelationDAO#totalCount(java.util.Map) 统计查询分页总数
     */
    public int totalCount(Map params) {
        List<CoreMemberInfo> memberIdList = new ArrayList<CoreMemberInfo>();
        memberIdList =
                getSqlMapClientTemplate().queryForList("CORE_AGENT_RELATION.ibatorgenerated_selectAdvertiser", params);
        return memberIdList.size();
    }
}
