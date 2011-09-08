package com.mitian.airad.dao;

import com.mitian.airad.model.CoreWapAd;

public interface CoreWapAdDAO {
    CoreWapAd findByAdId(Integer adId, Long memberId);

    int deleteByPrimaryKey(Integer wapId, Long memberId);

    Integer insert(CoreWapAd record, Long memberId);

    void insertSelective(CoreWapAd record, Long memberId);

    CoreWapAd selectByadId(Integer adId, Long memberId);

    int updateByPrimaryKeySelective(CoreWapAd record, Long memberId);

    int updateByPrimaryKeyWithBLOBs(CoreWapAd record, Long memberId);

    int updateByPrimaryKeyWithoutBLOBs(CoreWapAd record, Long memberId);
}
