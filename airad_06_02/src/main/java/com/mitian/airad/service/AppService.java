package com.mitian.airad.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.CommonDef;
import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.dao.CoreAppDAO;
import com.mitian.airad.dao.DictionaryDAO;
import com.mitian.airad.model.CoreApp;
import com.mitian.airad.model.Dictionary;

/**
 * AppService.java
 * 
 * @author baojun
 */
@Service
public class AppService {
    /** spring注入AppDAO */
    @Autowired
    private CoreAppDAO appDao;

    @Autowired
    private DictionaryDAO dictionaryDao;

    /**
     * 应用程序分页
     * 
     * @param coreCampaign
     */
    public List<CoreApp> queryList(Map<String, String> params) {
        List<CoreApp> listBean = appDao.selectByAll(params);
        return listBean;
    }

    /**
     * 应用程序分页(根据时间段查找收益)
     * 
     * @param coreCampaign
     */
    public List<CoreApp> queryListByTimeSlot(Map<String, String> params) {
        List<CoreApp> listBean = appDao.selectByTimeSlot(params);
        return listBean;
    }

    /**
     * 应用程序分页条数(根据时间段查找收益)
     * 
     * @param params
     * @return
     */
    public int queryCountByTimeSlot(Map<String, String> params) {
        int k = 0;
        k = appDao.selectCountByTimeSlot(params);
        return k;
    }

    /**
     * 查找所有
     * 
     * @param params
     * @return
     */
    public List<CoreApp> queryList(CoreApp app) {
        List<CoreApp> listBean = appDao.findList(app);
        return listBean;
    }

    /**
     * 查找总条数
     * 
     * @param coreApp
     * @return
     */
    public int queryCount(Map<String, String> params) {
        int k = appDao.totalCount(params);
        return k;
    }

    /**
     * 查询字典信息
     * 
     * @return
     */
    public List<Dictionary> txQueryDictionaryList() {
        return dictionaryDao.findList();
    }

    /**
     * 增加应用
     * 
     * @param coreApp
     */
    public void txAddApp(CoreApp coreApp) {
        // 添加应用默认状态为待审核
        coreApp.setFlag("1");
        // 默认启用
        coreApp.setAppStatus(CommonDef.appCon.CON_ISSUE);
        coreApp.setUpdTime(new Date());
        coreApp.setAddTime(new Date());
        appDao.insertSelective(coreApp);
    }

    /**
     * 查询应用详细信息
     * 
     * @param appId
     * @return
     * @throws InvalidInfoException
     */
    public CoreApp queryById(Integer appId, Long memberId) throws InvalidInfoException {
        CoreApp app = appDao.selectByPrimaryKey(appId, memberId);
        if (app == null) {
            throw new InvalidInfoException("queryById error appId:" + appId + " ,memberId:" + memberId);
        }
        return app;
    }

    /**
     * 根据CoreApp修改应用信息
     * 
     * @param coreApp
     */
    public void txEditApp(CoreApp coreApp) {
        appDao.updateByPrimaryKeySelective(coreApp);
    }
}
