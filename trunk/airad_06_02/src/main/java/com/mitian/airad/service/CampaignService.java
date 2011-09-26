package com.mitian.airad.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.CommonDef;
import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.dao.CoreCampaignDAO;
import com.mitian.airad.model.CoreAdGroup;
import com.mitian.airad.model.CoreCampaign;
import com.mitian.airad.utils.StringUtil;

/**
 * CampaignService.java
 * 
 * @author baojun
 */


@Service
public class CampaignService {
    /** spring注入CoreCampaignDAO */
    //@Autowired
    private CoreCampaignDAO coreCampaignDao;

    /**
     * 广告活动下所有广告暂停 和发布,调用此方法。首先要判断广告商身份，可以在页面做，也可以将值传入后台做，或者用AJAX做，返回给客户端 TODO
     * 
     * @param key 活动
     * @param memberId 会员id
     * @return
     */
    public int txSuspend(CoreCampaign key, Long memberId) {
        campaignPackage(key);
        key.setMemberId(memberId);
        key.setInGroupId(StringUtil.formatStringToInCondition(selectAdGroupId(key.getCampaignId(), memberId)));
        return coreCampaignDao.txSuspend(key);
    }

    /**
     * 查找广告组ID
     * 
     * @param campaignId 活动ID
     * @return
     */
    public String selectAdGroupId(Integer campaignId, Long memberId) {
        CoreCampaign key = new CoreCampaign();
        key.setCampaignId(campaignId);
        campaignPackage(key);
        key.setMemberId(memberId);
        List<CoreAdGroup> list = coreCampaignDao.selectAdGroupId(key);
        String str = CommonDef.EMPTY;
        for (CoreAdGroup coreAdGroup : list) {
            str += coreAdGroup.getAdGroupId() + CommonDef.STR_COMMA;
        }
        return str;
    }

    /**
     * copy活动
     * 
     * @param key 广告活动
     * @param memberId 会员id
     * @throws InvalidInfoException
     */
    public void txCopy(CoreCampaign key, Long memberId) throws InvalidInfoException {
        campaignPackage(key);
        key.setMemberId(memberId);
        coreCampaignDao.txCopy(key);
    }

    /**
     * 删除广告活动
     * 
     * @param campaignId
     */
    public void txdel(CoreCampaign key, Long memberId) {
        key.setDelTime(new Date());
        key.setMemberId(memberId);
        coreCampaignDao.txDel(key);
    }

    /**
     * CoreCampaign 封装BEAN
     * 
     * @param coreCampaign CoreCampaign
     */
    public void campaignPackage(CoreCampaign coreCampaign) {
        // 修改时间
        coreCampaign.setUpdTime(new Date());
        // 删除标志
        coreCampaign.setDelFlag(CommonDef.dataBaseConstant.DEL_FLAG);
        // 是否冻结
        coreCampaign.setBlocking(CommonDef.dataBaseConstant.BLOCKING);
    }

    /**
     * 根据提供的CoreCampaign对象添加活动
     * 
     * @param coreCampaign
     */
    public int txAddCampaign(CoreCampaign coreCampaign) {
        // 设置默认暂停标志，0代表默认值，1代表暂停
        coreCampaign.setSuspendType(CommonDef.campaignStatus.SUSPENDTYPE);
        // 设置审核标志，0代表未审核，1代表审核
        coreCampaign.setBlocking(CommonDef.campaignStatus.BLOCKING_NO);
        // 设置投放方式标准
        coreCampaign.setPubKind(CommonDef.campaignStatus.PUT_KING);
        // 设置添加时间
        coreCampaign.setAddTime(new Date());
        return coreCampaignDao.insertReturnId(coreCampaign);
    }

    /**
     * 根据提供的CoreCampaign对象对活动信息进行修改
     * 
     * @param coreCampaign
     * @return
     */
    public int txEditCampaign(CoreCampaign coreCampaign, Long memberId) {
        coreCampaign.setUpdTime(new Date());
        return coreCampaignDao.updateByPrimaryKeySelective(coreCampaign, memberId);
    }

    /**
     * 查询所有分页
     * 
     * @return
     */
    public List<CoreCampaign> queryList(Map<String, String> params) {
        List<CoreCampaign> listBean = coreCampaignDao.selectByAll(params);
        return listBean;
    }

    /**
     * 根据时间段统计收益
     * 
     * @param params
     * @return
     */
    public List<CoreCampaign> queryListByTimeSlot(Map<String, String> params) {

        List<CoreCampaign> listBean = coreCampaignDao.selectStatisticByTimeSlot(params);
        return listBean;
    }

    /**
     * 查找总条数
     * 
     * @param campaign
     * @return
     */
    public int queryCount(Map<String, String> params) {
        int k = coreCampaignDao.totalCount(params);
        return k;
    }

    public int queryCount(Long memberId, String campaignName) {
        Map<String, String> params = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(campaignName)) {
            if (!campaignName.contains("/")) {
                params.put("escape", CommonDef.escape.ESCAPE);
            }
            campaignName = StringUtil.replaceChar(campaignName);
            params.put("campaignName", campaignName);

        }
        params.put("memberId", Long.toString(memberId));
        int k = coreCampaignDao.totalCount(params);
        return k;
    }

    public int queryCountByTimeSlot(Map<String, String> params) {
        int k = coreCampaignDao.totalCountByTimeSlot(params);
        return k;
    }

    public int queryCount(Long memberId, String campaignName, String startTime, String endTime) {
        Map<String, String> params = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(campaignName)) {
            if (!campaignName.contains("/")) {
                params.put("escape", CommonDef.escape.ESCAPE);
            }
            campaignName = StringUtil.replaceChar(campaignName);
            params.put("campaignName", campaignName);

        }
        params.put("memberId", Long.toString(memberId));
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        int k = coreCampaignDao.totalCount(params);
        return k;
    }

    /**
     * 根据条件查询所有
     * 
     * @return
     */
    public List<CoreCampaign> queryByNameList(CoreCampaign campaign) {
        List<CoreCampaign> listBean = coreCampaignDao.findByNameList(campaign);
        return listBean;
    }

    /**
     * 根据条件查询
     * 
     * @param campaign
     * @return
     */
    public List<CoreCampaign> queryBycondition(CoreCampaign campaign, Long memberId) {
        campaign.setMemberId(memberId);
        List<CoreCampaign> campaigns = coreCampaignDao.queryBycondition(campaign);
        return campaigns;
    }

    /**
     * 根据主键查询
     * 
     * @param memberId TODO
     * @param cid
     * @return
     * @throws InvalidInfoException
     */
    public CoreCampaign findCampaignById(Integer campaignId, Long memberId) throws InvalidInfoException {
        CoreCampaign campaign = coreCampaignDao.selectByPrimaryKey(campaignId, memberId);
        if (campaign == null) {
            throw new InvalidInfoException("get CoreCampaign Error campaignId: " + campaignId + "   memberId: "
                    + memberId);
        }
        return campaign;
    }

    public CoreCampaign findById(String campaignId, Long memberId) throws InvalidInfoException {
        CoreCampaign campaign = null;
        if (StringUtils.isNotBlank(campaignId)) {
            campaign = findCampaignById(Integer.parseInt(campaignId), memberId);
        }
        return campaign;
    }

    public CoreCampaign queryCampaign(CoreCampaign campaign) {
        return null;
    }

    /**
     * @return the coreCampaignDao
     */
    public CoreCampaignDAO getCoreCampaignDao() {
        return coreCampaignDao;
    }

    /**
     * @param coreCampaignDao the coreCampaignDao to set
     */
    public void setCoreCampaignDao(CoreCampaignDAO coreCampaignDao) {
        this.coreCampaignDao = coreCampaignDao;
    }
}
