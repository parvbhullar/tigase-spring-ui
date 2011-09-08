package com.mitian.airad.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.mitian.airad.dao.AccountInfoViewDAO;
import com.mitian.airad.dao.support.CustomSqlMapClientDaoSupport;
import com.mitian.airad.model.AccIncome;
import com.mitian.airad.model.AccIncomePay;
import com.mitian.airad.model.AccountInfoView;
import com.mitian.airad.model.AdinfoView;
import com.mitian.airad.model.ReqAdOnlineTime;
import com.mitian.airad.model.SysConfig;
import com.mitian.airad.utils.StringUtil;

@Repository
public class AccountInfoViewDAOImpl extends CustomSqlMapClientDaoSupport implements AccountInfoViewDAO {

    public AccountInfoViewDAOImpl() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.dao.AccountInfoViewDAO#findByAppCode(java.lang.String)
     */
    public AdinfoView findByAppCode(String appCode) {
        AdinfoView adinfoView = new AdinfoView();
        adinfoView.setAppCode(appCode);
        return (AdinfoView) getSqlMapClientTemplate().queryForObject("ADINFO_VIEW.findToAppMemberId", adinfoView);

    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.dao.AccountInfoViewDAO#findByAdId(java.lang.Integer)
     */
    public AdinfoView findByAdId(Integer adId) {
        AdinfoView adinfoView = new AdinfoView();
        adinfoView.setAdId(adId);
        adinfoView = (AdinfoView) getSqlMapClientTemplate().queryForObject("ADINFO_VIEW.findByAdInfo", adinfoView);
        return adinfoView;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.dao.AccountInfoViewDAO#findByAccountInfo(java.lang.Integer)
     */
    public AccountInfoView findByAccountInfo(Long memberId) {
        AccountInfoView accountInfoView = new AccountInfoView();
        accountInfoView.setMemberId(memberId);
        return (AccountInfoView) getSqlMapClientTemplate().queryForObject("ACCOUNT_INFO_VIEW.findByAccountInfo",
                accountInfoView);
    }

    public AccountInfoView findAccountInfoByEmail(String email) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("email", email);
        return (AccountInfoView) getSqlMapClientTemplate().queryForObject("ACCOUNT_INFO_VIEW.findAccountInfoByEmail",
                param);
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.dao.AccountInfoViewDAO#add(java.util.Map)
     */
    public void addTradeRecord(Map<String, Object> map) {
        if (null != map) {
            for (String str : map.keySet()) {
                if ("AccIncome".equals(str)) {
                    List<AccIncome> listAccIncome = (List<AccIncome>) map.get(str);
                    for (AccIncome accIncome : listAccIncome) {
                        // 收益
                        getSqlMapClientTemplate().insert("ACC_INCOME.ibatorgenerated_insertSelective", accIncome);
                    }
                }
                if ("AccIncomePayList".equals(str)) {
                    List<AccIncomePay> listAccIncomePay = (List<AccIncomePay>) map.get(str);
                    for (AccIncomePay accIncomePay : listAccIncomePay) {
                        // 收益交易记录
                        getSqlMapClientTemplate()
                                .insert("ACC_INCOME_PAY.ibatorgenerated_insertSelective", accIncomePay);
                    }
                }
                if ("AccIncomePay".equals(str)) {
                    // 支出交易记录
                    AccIncomePay accIncomePay = (AccIncomePay) map.get(str);
                    getSqlMapClientTemplate().insert("ACC_INCOME_PAY.ibatorgenerated_insertSelective", accIncomePay);
                }
                if ("ReqAdOnlineTime".equals(str)) {
                    // 在线时间记录
                    ReqAdOnlineTime reqAdOnlineTime = (ReqAdOnlineTime) map.get(str);
                    getSqlMapClientTemplate().insert("REQ_AD_ONLINE_TIME.ibatorgenerated_insertSelective",
                            reqAdOnlineTime);
                }
            }
        }
        // batchAddExamlog2(map);
    }

    @SuppressWarnings("unchecked")
    public void batchAddExamlog2(final Map<String, Object> map) {
        getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                if (null != map) {
                    executor.startBatch();
                    for (String str : map.keySet()) {
                        if ("AccIncome".equals(str)) {
                            List<AccIncome> listAccIncome = (List<AccIncome>) map.get(str);
                            for (AccIncome accIncome : listAccIncome) {
                                // 收益
                                executor.insert("ACC_INCOME.ibatorgenerated_insertSelective", accIncome);
                            }
                        }
                        if ("AccIncomePayList".equals(str)) {
                            List<AccIncomePay> listAccIncomePay = (List<AccIncomePay>) map.get(str);
                            for (AccIncomePay accIncomePay : listAccIncomePay) {
                                // 收益交易记录
                                executor.insert("ACC_INCOME_PAY.ibatorgenerated_insertSelective", accIncomePay);
                            }
                        }
                        if ("AccIncomePay".equals(str)) {
                            // 支出交易记录
                            AccIncomePay accIncomePay = (AccIncomePay) map.get(str);
                            executor.insert("ACC_INCOME_PAY.ibatorgenerated_insertSelective", accIncomePay);
                        }
                        if ("ReqAdOnlineTime".equals(str)) {
                            // 在线时间记录
                            ReqAdOnlineTime reqAdOnlineTime = (ReqAdOnlineTime) map.get(str);
                            executor.insert("REQ_AD_ONLINE_TIME.ibatorgenerated_insertSelective", reqAdOnlineTime);
                        }
                    }
                    executor.executeBatch();
                }

                return null;
            }
        });
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.dao.AccountInfoViewDAO#findByMemberId(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<AccountInfoView> findByMemberId(Map<String, String> params) {
        int pageSize = StringUtil.StringToInteger(params.get("pageSize"));
        int currentPage = StringUtil.StringToInteger(params.get("currentPage")) - 1;
        int fromRecord = pageSize * currentPage;
        List<AccountInfoView> accountInfoViewList = new ArrayList<AccountInfoView>();
        accountInfoViewList =
                getSqlMapClientTemplate()
                        .queryForList("ACCOUNT_INFO_VIEW.findByMemberId", params, fromRecord, pageSize);
        return accountInfoViewList;
    }

    public void txRecord(Map<String, Object> map) {
        batchAddExamlog(map);
        // 将展示计费过的数据改FLAG为1
        getSqlMapClientTemplate().update("REQ_BANNER.updateByFlag");
    }

    @SuppressWarnings("unchecked")
    public void batchAddExamlog(final Map<String, Object> map) {
        getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                if (null != map) {
                    executor.startBatch();
                    for (String str : map.keySet()) {
                        if ("AccIncome".equals(str)) {
                            List<AccIncome> listAccIncome = (List<AccIncome>) map.get(str);
                            for (AccIncome accIncome : listAccIncome) {
                                // 收益
                                executor.insert("ACC_INCOME.ibatorgenerated_insertSelective", accIncome);
                            }
                        }
                        if ("AccIncomePayList".equals(str)) {
                            List<AccIncomePay> listAccIncomePay = (List<AccIncomePay>) map.get(str);
                            for (AccIncomePay accIncomePay : listAccIncomePay) {
                                // 收益交易记录
                                executor.insert("ACC_INCOME_PAY.ibatorgenerated_insertSelective", accIncomePay);
                            }
                        }
                        if ("AccIncomePay".equals(str)) {
                            // 支出交易记录
                            AccIncomePay accIncomePay = (AccIncomePay) map.get(str);
                            executor.insert("ACC_INCOME_PAY.ibatorgenerated_insertSelective", accIncomePay);
                        }
                        if ("appincome".equals(str)) {
                            List<AccIncome> listAccIncomeShow = (List<AccIncome>) map.get(str);
                            for (AccIncome accIncome : listAccIncomeShow) {
                                // 收益
                                executor.insert("ACC_INCOME.ibatorgenerated_insertSelective", accIncome);
                            }
                        }
                        if ("appincomePay".equals(str)) {
                            // 支出交易记录
                            List<AccIncomePay> listAccIncomePayShow = (List<AccIncomePay>) map.get(str);
                            for (AccIncomePay accIncomePay : listAccIncomePayShow) {
                                executor.insert("ACC_INCOME_PAY.ibatorgenerated_insertSelective", accIncomePay);
                            }
                        }
                    }
                    executor.executeBatch();
                }

                return null;
            }
        });
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.dao.AccountInfoViewDAO#totalCount(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public int totalCount(Map<String, String> map) {
        List<AccountInfoView> accountInfoViewList = new ArrayList<AccountInfoView>();
        accountInfoViewList = getSqlMapClientTemplate().queryForList("ACCOUNT_INFO_VIEW.findByMemberId", map);
        int k = 0;
        if (null != accountInfoViewList) {
            k = accountInfoViewList.size();
        }
        return k;
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> configIncome(String type) {
        SysConfig key = new SysConfig();
        key.setSysType(type);
        List<SysConfig> list = getSqlMapClientTemplate().queryForList("ACCOUNT_INFO_VIEW.findByTyleListByTypeKey", key);
        // List<SysConfig> list = findConfigListByType(type);
        Map<String, String> map = new HashMap<String, String>();
        for (SysConfig sysConfig : list) {
            map.put(sysConfig.getSysKey(), sysConfig.getSysVal());
        }
        return map;
    }

    public List<AccountInfoView> findByMemberIdList(List<Long> memberIdList) {
        // TODO 待实现
        return null;
    }
}
