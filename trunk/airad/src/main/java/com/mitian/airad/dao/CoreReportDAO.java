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
    public List<CoreReport> pageQuery(Map<String, String> params);

    /**
     * 批量删除报表
     * 
     * @param deleteListId
     */
    public void batchDelete(final List<Integer> deleteListId, final Long memberId);

    /**
     * 查询总条数
     * 
     * @param report
     * @return
     */
    public int totalCount(CoreReport report);

    public void insert(CoreReport record);

    public void insertSelective(CoreReport record);

    public CoreReport selectByPrimaryKey(Integer reportId, Long memberId);

    public int updateByPrimaryKeyWithBLOBs(CoreReport record, Long memberId);

    public int updateByPrimaryKeyWithoutBLOBs(CoreReport record, Long memberId);

    public String insertReturnId(CoreReport record);
}
