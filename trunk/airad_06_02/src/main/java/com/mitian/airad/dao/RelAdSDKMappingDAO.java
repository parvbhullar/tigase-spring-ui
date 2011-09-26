package com.mitian.airad.dao;

import com.mitian.airad.model.RelAdSDKMapping;

public interface RelAdSDKMappingDAO {

    int deleteByPrimaryKey(Integer resVId);

    void insert(RelAdSDKMapping record);

}
