package com.mitian.airad.dao;

import java.util.List;
import java.util.Map;

import com.mitian.airad.model.StatAgent;

public interface StatAgentDAO {
    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table STAT_AGENT
     * 
     * @ibatorgenerated Thu Mar 10 14:17:51 CST 2011
     */
    int deleteByPrimaryKey(Integer shatAngenId);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table STAT_AGENT
     * 
     * @ibatorgenerated Thu Mar 10 14:17:51 CST 2011
     */
    void insert(StatAgent record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table STAT_AGENT
     * 
     * @ibatorgenerated Thu Mar 10 14:17:51 CST 2011
     */
    void insertSelective(StatAgent record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table STAT_AGENT
     * 
     * @ibatorgenerated Thu Mar 10 14:17:51 CST 2011
     */
    StatAgent selectByPrimaryKey(Integer shatAngenId);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table STAT_AGENT
     * 
     * @ibatorgenerated Thu Mar 10 14:17:51 CST 2011
     */
    int updateByPrimaryKeySelective(StatAgent record);

    /**
     * This method was generated by Apache iBATIS ibator. This method corresponds to the database table STAT_AGENT
     * 
     * @ibatorgenerated Thu Mar 10 14:17:51 CST 2011
     */
    int updateByPrimaryKey(StatAgent record);

    /**
     * 报表查询(分页)
     */
    List<StatAgent> queryAngenTotalList(Map<String, String> map);

    List<StatAgent> queryAngenDetailList(Map<String, String> map);

    /**
     * 报表查询(所有)
     */
    List<StatAgent> selectAngenTotalList(Map<String, String> map);

    List<StatAgent> selectAngenDetailList(Map<String, String> map);

    /**
     * 统计AGENT
     * 
     * @param jobTime
     * @return void findByTiimeAgent(String jobTime, String tomorrowDay, String type) throws Exception;
     */
    /**
     * 查找AGENT总数
     * 
     * @return
     */
    int statCountAgent(StatAgent paramStatAgent);

    /**
     * 分页查找AGENT数据
     * 
     * @param paramStatApp
     * @return
     */
    List<StatAgent> limitStatAgentList(StatAgent paramStatAgent);

    /**
     * 写入AGENT数据
     * 
     * @param statAd
     */
    void addJobStatAgent(StatAgent statAgent);

}
