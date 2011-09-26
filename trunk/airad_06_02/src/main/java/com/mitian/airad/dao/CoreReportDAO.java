package com.mitian.airad.dao;

import java.util.List;
import java.util.Map;

import com.mitian.airad.model.CoreReport;

public interface CoreReportDAO {
    /**
     * 查找所有（分页）
     * 
     * @param params
     * @return
     */
    List<CoreReport> pageQuery(Map<String, String> params);

    /**
     * 批量删除报表
     * 
     * @param deleteListId
     */
    void batchDelete(final List<Integer> deleteListId, final Long memberId);

    /**
     * 查询总条数
     * 
     * @param report
     * @return
     */
    int totalCount(CoreReport report);

    void insert(CoreReport record);

    void insertSelective(CoreReport record);

    CoreReport selectByPrimaryKey(Integer reportId, Long memberId);

    int updateByPrimaryKeyWithBLOBs(CoreReport record, Long memberId);

    int updateByPrimaryKeyWithoutBLOBs(CoreReport record, Long memberId);

    String insertReturnId(CoreReport record);
}
