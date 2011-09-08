package com.mitian.airad.dao;

import java.util.List;
import java.util.Map;

import com.mitian.airad.model.CoreAdExtend;

public interface CoreAdExtendDAO {
    List<CoreAdExtend> getADExtendListByPlace(Map<String, String> param);
}
