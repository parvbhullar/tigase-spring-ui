package com.mitian.airad.dao;

import java.util.List;

import com.mitian.airad.model.CoreRichAd;

public interface CoreRichAdDAO {
    int deleteByPrimaryKey(Integer richId, Long memberId);

    int deleteByPrimarySortAndAdId(Integer sort, Integer adId, Long memberId);

    Integer insert(CoreRichAd record, Long memberId);

    Integer insertSelective(CoreRichAd record, Long memberId);

    CoreRichAd selectRichIdByAdId(Integer adId, Long memberId);

    List<CoreRichAd> findAdListByAsId(Integer adId, Long memberId);

    /**
     * 修改关联文件
     * 
     * @param record
     * @return
     */
    Integer updateRelByPrimaryKey(CoreRichAd record, Long memberId);

    int updateByPrimaryKey(CoreRichAd record, Long memberId);
}
