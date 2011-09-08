package com.mitian.airad.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mitian.airad.dao.CoreAdExtendDAO;
import com.mitian.airad.dao.support.CustomSqlMapClientDaoSupport;
import com.mitian.airad.model.CoreAdExtend;

@Repository
public class CoreAdExtendDAOImpl extends CustomSqlMapClientDaoSupport implements CoreAdExtendDAO {
    public CoreAdExtendDAOImpl() {
        super();
    }

    /**
     * 查询出某地区的广告扩展对象列表 (non-Javadoc)
     * 
     * @see com.mitian.airad.dao.CoreAdExtendDAO#getADExtendListByPlace(java.lang.String)
     */
    public List<CoreAdExtend> getADExtendListByPlace(Map<String, String> param) {

        List<CoreAdExtend> record =
                getSqlMapClientTemplate().queryForList("CORE_ADEXTEND.ibatorgenerated_selectCoreAdByPlace", param);
        return record;
    }

}
