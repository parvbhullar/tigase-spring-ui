package com.mitian.airad.dao;

import com.mitian.airad.model.CoreBanner;

public interface CoreBannerDAO {
    int deleteByPrimaryKey(Integer bannerId, Long memberId);

    int insert(CoreBanner record, Long memberId);

    int insertSelective(CoreBanner record, Long memberId);

    CoreBanner selectByPrimaryKey(Integer bannerId, Long memberId);

    CoreBanner selectBannerByAdId(Integer adId, Long memberId);

    int updateByPrimaryKeySelective(CoreBanner record, Long memberId);

    int updateByPrimaryKey(CoreBanner record, Long memberId);
}
