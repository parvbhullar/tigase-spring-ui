package com.mitian.airad.dao;

import java.util.List;
import java.util.Map;

import com.mitian.airad.model.AccountInfoView;
import com.mitian.airad.model.AdinfoView;

public interface AccountInfoViewDAO {

    /**
     * 根据memberId集合查找视图记录
     * 
     * @param memberIdList
     * @return
     */
    List<AccountInfoView> findByMemberId(Map<String, String> params);

    /**
     * 根据memberId集合查找总条数
     * 
     * @param memberIdList
     * @return
     */
    int totalCount(Map<String, String> map);

    /**
     * 根据AppCode 查找开发者MEMBER_ID
     * 
     * @param appCode
     * @return
     */
    AdinfoView findByAppCode(String appCode);

    /**
     * 根据广告ID查找广告所关联的信息
     * 
     * @param adId
     * @return
     */
    AdinfoView findByAdId(Integer adId);

    /**
     * 根据会员ID查找余额
     * 
     * @param memberId
     * @return
     */
    AccountInfoView findByAccountInfo(Long memberId);

    /**
     * 根据会员email查找广告商余额
     * 
     * @param email
     * @return
     */
    AccountInfoView findAccountInfoByEmail(String email);

    /**
     * 增加交易记录
     * 
     * @param map
     */
    void addTradeRecord(Map<String, Object> map);

    /**
     * 展示计费
     * 
     * @param map
     */
    void txRecord(Map<String, Object> map);

    /**
     * 配置表收益
     * 
     * @param type
     * @return
     */
    Map<String, String> configIncome(String type);

    /**
     * @param memberIdList
     * @return
     */
    List<AccountInfoView> findByMemberIdList(List<Long> memberIdList);
}
