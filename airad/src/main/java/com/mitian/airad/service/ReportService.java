package com.mitian.airad.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.CommonDef;
import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.dao.CoreReportDAO;
import com.mitian.airad.model.CoreAd;
import com.mitian.airad.model.CoreAdGroup;
import com.mitian.airad.model.CoreApp;
import com.mitian.airad.model.CoreCampaign;
import com.mitian.airad.model.CoreReport;
import com.mitian.airad.model.StatAd;
import com.mitian.airad.model.StatApp;
import com.mitian.airad.utils.ReportDateUtil;
import com.mitian.airad.utils.StringUtil;

@Service
public class ReportService {
    /** spring注入DAO */
    @Autowired
    private CoreReportDAO reportDAO;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private AppService appService;

    @Autowired
    private AdGroupService adGroupService;

    @Autowired
    private AdService adService;

    /*
     * ================================================tangwenjun=====================================================
     */
    public List<CoreReport> queryList(Map<String, String> params) {
        List<CoreReport> listBean = reportDAO.pageQuery(params);
        return listBean;
    }

    /**
     * 查找总的报表条数
     * 
     * @param campaign
     * @return
     */
    public int queryCount(CoreReport report) {
        int k = reportDAO.totalCount(report);
        return k;
    }

    /**
     * 获取商铺主报表
     * 
     * @param memberId
     * @return
     */
    public List<CoreReport> getList4Shopper(Long memberId) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("memberId", String.valueOf(memberId));
        param.put("currentPage", "1");
        param.put("pageSize", "10");
        List<CoreReport> listBean = reportDAO.pageQuery(param);
        return listBean;
    }

    /**
     * 添加报表、修改
     * 
     * @param report
     */
    public void txAddOrEditReport(Map<String, Object> params, Long memberId) {
        CoreReport report = getAddReport(params);
        Integer reportId = report.getReportId();
        // 存在Id为跟新
        if (reportId != null) {
            CoreReport r = reportDAO.selectByPrimaryKey(reportId, memberId);
            r.setReportType(report.getReportType());
            r.setReportStartTime(report.getReportStartTime());
            r.setReportEndTime(report.getReportEndTime());
            r.setDateType(report.getDateType());
            r.setUpdTime(new Date());
            r.setReportName(report.getReportName());
            r.setReportCon(report.getReportCon());
            r.setReportStatus(report.getReportStatus());
            reportDAO.updateByPrimaryKeyWithBLOBs(r, memberId);
        }
        else {
            report.setAddTime(new Date());
            reportDAO.insertSelective(report);
        }

    }

    /**
     * 封装数据
     * 
     * @param params
     * @return
     */
    public CoreReport getReport(Map<String, Object> params) {
        // 报表选择方式
        String dateType = (String) params.get("dateType");
        // 报表选择范围
        String presetValue = (String) params.get("presetValue");
        // 报表开始时间
        String rStartTime = (String) params.get("reportStartTime");
        // 报表结束时间
        String eStartTime = (String) params.get("reportEndTime");
        // 报表对象
        CoreReport report = params.get("report") == null ? new CoreReport() : (CoreReport) params.get("report");
        // 根据选择日期范围的方式作出添加
        // 选择提供的日期范围快捷方式
        if ("1".equals(dateType) && StringUtils.isNotEmpty(presetValue)) {
            Date date = new Date();
            switch (StringUtil.StringToInteger(presetValue)) {
                case 1 :// today
                    String today = ReportDateUtil.getTomorrowDate("yyyy-MM-dd", 0);// 得到今天0，昨天-1，过去7天-7，过去30天-30
                    report.setReportStartTime(StringUtil.getDateY(today));
                    report.setReportEndTime(StringUtil.getDateY(date));
                    break;
                case 2 :// yesterday
                    String yesterday = ReportDateUtil.getTomorrowDate("yyyy-MM-dd", -1);// 昨天
                    report.setReportStartTime(StringUtil.getDateY(yesterday));
                    report.setReportEndTime(StringUtil.getDateY(yesterday));
                    break;
                case 3 :// last7
                    String last7 = ReportDateUtil.getTomorrowDate("yyyy-MM-dd", -7); // 最近7 天
                    String last1 = ReportDateUtil.getTomorrowDate("yyyy-MM-dd", -1);
                    report.setReportStartTime(StringUtil.getDateY(last7));
                    report.setReportEndTime(StringUtil.getDateY(date));
                    break;
                case 4 :// last30
                    String last30 = ReportDateUtil.getTomorrowDate("yyyy-MM-dd", -30); // 最近30 天
                    report.setReportStartTime(StringUtil.getDateY(last30));
                    report.setReportEndTime(StringUtil.getDateY(date));
                    break;
                case 5 :// lastmonth // 上一个月
                    String prevmonthStart =
                            ReportDateUtil
                                    .getBeginningDateOfLastMonth(ReportDateUtil.getDate("yyyy-MM-dd", new Date()));// 上一个月start
                    String prevmonthEnd =
                            ReportDateUtil.getEndingDateOfLastMonth(ReportDateUtil.getDate("yyyy-MM-dd", new Date()));// 上一个月end
                    report.setReportStartTime(StringUtil.getDateY(prevmonthStart));
                    report.setReportEndTime(StringUtil.getDateY(prevmonthEnd));
                    break;
                case 6 :// prevquarter
                    String lastStartQuarter =
                            ReportDateUtil
                                    .getBeginingDateOfLastSeason(ReportDateUtil.getDate("yyyy-MM-dd", new Date()));// 上一个季度start
                    String lastEndQuarter =
                            ReportDateUtil.getEndingDateOfLastSeason(ReportDateUtil.getDate("yyyy-MM-dd", new Date()));// 上一个季度end
                    report.setReportStartTime(StringUtil.getDateY(lastStartQuarter));
                    report.setReportEndTime(StringUtil.getDateY(lastEndQuarter));
                    break;
                case 7 :// ytd
                    String startMonth =
                            ReportDateUtil.getStartMonthDate(ReportDateUtil.getDate("yyyy-MM-dd", new Date()));// 本月开始
                    report.setReportStartTime(StringUtil.getDateY(startMonth));
                    report.setReportEndTime(StringUtil.getDateY(date));
                    break;
                case 8 :// qtd
                    String startQuarter = ReportDateUtil.getStratQuarter();// 本季开始
                    report.setReportStartTime(StringUtil.getDateY(startQuarter));
                    report.setReportEndTime(StringUtil.getDateY(date));
                    break;
                case 9 :// 今天已统计
                    String today1 = ReportDateUtil.getTomorrowDate("yyyy-MM-dd", 0);// 得到今天0，昨天-1，过去7天-7，过去30天-30
                    report.setReportStartTime(StringUtil.getDateY(today1));
                    report.setReportEndTime(StringUtil.getDateY(today1));
                    break;
                case 10 :// 过去90天
                    today1 = ReportDateUtil.getTomorrowDate("yyyy-MM-dd", -90);// 得到今天0，昨天-1，过去7天-7，过去30天-30
                    report.setReportStartTime(StringUtil.getDateY(today1));
                    report.setReportEndTime(StringUtil.getDateY(date));
                    break;
                case 11 :// 过去180天
                    today1 = ReportDateUtil.getTomorrowDate("yyyy-MM-dd", -180);// 得到今天0，昨天-1，过去7天-7，过去30天-30
                    report.setReportStartTime(StringUtil.getDateY(today1));
                    report.setReportEndTime(StringUtil.getDateY(date));
                    break;
                case 12 :// 过去365天
                    today1 = ReportDateUtil.getTomorrowDate("yyyy-MM-dd", -365);// 得到今天0，昨天-1，过去7天-7，过去30天-30
                    report.setReportStartTime(StringUtil.getDateY(today1));
                    report.setReportEndTime(StringUtil.getDateY(date));
                    break;
            }
        }
        // 自主选择日期
        else {
            Date reportStartTime = null;
            Date reportEndTime = null;
            if (!StringUtils.isEmpty(rStartTime)) {
                reportStartTime = StringUtil.getDateY(rStartTime);
                report.setReportStartTime(reportStartTime);
            }
            if (!StringUtils.isEmpty(eStartTime)) {
                reportEndTime = StringUtil.getDateY(eStartTime);
                report.setReportEndTime(reportEndTime);
            }
        }
        return report;
    }

    /**
     * 封装数据
     * 
     * @param params
     * @return
     */
    public CoreReport getAddReport(Map<String, Object> params) {
        // 报表选择方式
        String dateType = (String) params.get("dateType");
        // 报表选择范围
        String presetValue = (String) params.get("presetValue");
        // 报表开始时间
        String rStartTime = (String) params.get("reportStartTime");
        // 报表结束时间
        String eStartTime = (String) params.get("reportEndTime");
        // 报表对象
        CoreReport report = (CoreReport) params.get("report");
        // 根据选择日期范围的方式作出添加
        // 选择提供的日期范围快捷方式
        if ("1".equals(dateType) && StringUtils.isNotEmpty(presetValue)) {
            report.setReportEndTime(null);
            report.setReportStartTime(null);
            report.setDateType(presetValue);
        }
        // 自主选择日期
        else {
            Date reportStartTime = null;
            Date reportEndTime = null;
            if (!StringUtils.isEmpty(rStartTime)) {
                reportStartTime = StringUtil.getDateY(rStartTime);
                report.setReportStartTime(reportStartTime);
            }
            if (!StringUtils.isEmpty(eStartTime)) {
                reportEndTime = StringUtil.getDateY(eStartTime);
                report.setReportEndTime(reportEndTime);
            }
            report.setDateType(CommonDef.dateType.ONESELF);
        }
        return report;
    }

    /**
     * 报告删除
     * 
     * @param report
     */
    public void txDeleteReport(List<Integer> deleteListId, Long memberId) {
        reportDAO.batchDelete(deleteListId, memberId);
    }

    /**
     * 根据id查找报表
     * 
     * @param reportId
     * @return
     * @throws InvalidInfoException
     */
    public CoreReport queryById(Integer reportId, Long memberId) throws InvalidInfoException {
        CoreReport report = reportDAO.selectByPrimaryKey(reportId, memberId);
        if (report == null) {
            throw new InvalidInfoException("queryById error reportId:" + reportId + " ,memberId:" + memberId);
        }
        return report;
    }

    /*
     * ================================================xiaxianli=====================================================
     */
    /**
     * 返回当前插入的id值
     */
    public String insertReturnId(CoreReport record) {
        return reportDAO.insertReturnId(record);
    }

    /***
     * @param type 身份类型
     * @param ids ids 集合
     * @param memberId 创建者
     * @param status 形式（详情 汇总）
     * @param pageSize 页面大小
     * @param currentPage 当前页
     * @param stDate 开始日期
     * @param endDate 解说日期
     * @param sortFlag 排序表示
     * @param list 已有队列
     */
    public List dealEmptyDateList(String type, String ids, Long memberId, String status, int pageSize, int currentPage,
            String stDate, String endDate, boolean sortFlag, List list, List<StatAd> keyNames, List<CoreApp> appList) {
        // 日期跨度队列
        List<String> datelist = null;
        try {
            datelist = ReportDateUtil.getDateList(stDate, endDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return dealEmptyDateList(type, ids, memberId, status, pageSize, currentPage, datelist, sortFlag, list,
                keyNames, appList);
    }

    public List dealEmptyDateList(String type, String ids, Long memberId, String status, int pageSize, int currentPage,
            List<String> datelist, boolean sortFlag, List list, List<StatAd> keyNames, List<CoreApp> appList) {

        int fillCount = 0;

        if (CommonDef.report.REPORT_ADD_AD.equals(type) || CommonDef.report.REPORT_ADD_ADGROUP.equals(type)
                || CommonDef.report.REPORT_ADD_CAM.equals(type)) {
            fillCount = keyNames.size();
        }
        else if (CommonDef.report.REPORT_ADD_DEVELOP.equals(type)) {
            fillCount = appList.size();
        }

        // 页面剩余昨天的应用数量
        int leftCount = fillCount - ((currentPage - 1) * pageSize % fillCount);

        // 首页
        if (currentPage == 1) {
            leftCount = 0;
        }

        // 重复数据位置
        int removeIndex = 0;

        // 汇总类型报表 ， 获取时间数据（用来展示列表顺序），所以这个时轴不用区分不同应用程序
        Map<String, Object> Datekey = new HashMap<String, Object>();
        // 日期索引检索队列
        List<String> dateListIndex = new ArrayList<String>();
        // 重复日期数据位置索引队列
        List<Integer> needRemoveIndex = new ArrayList<Integer>();

        // 活动报告 ; 广告组报告 ; 广告报告
        if (CommonDef.report.REPORT_ADD_AD.equals(type) || CommonDef.report.REPORT_ADD_ADGROUP.equals(type)
                || CommonDef.report.REPORT_ADD_CAM.equals(type)) {

            // 活动报告 ; 广告组报告 ; 广告报告 汇总报表 ,不允许时间重复
            if (CommonDef.report.REPORT_TOTAL_TYPE.equals(status)) {
                try {
                    if (sortFlag) {
                        java.util.Collections.reverse(list);
                    }
                    for (Object app1 : list) {
                        StatAd app = (StatAd) app1;

                        String date = ReportDateUtil.getDate("yyyy-MM-dd", app.getAddTime());
                        removeIndex++;
                        if (!Datekey.containsKey(date)) {
                            dateListIndex.add(date);
                            Datekey.put(date, date);
                        }
                        else {
                            // 将日期重复数据的索引保存
                            needRemoveIndex.add(removeIndex - 1);
                        }
                    }

                    // 移除日期数据重复的索引
                    for (int i = needRemoveIndex.size() - 1; i >= 0; i--) {
                        list.remove((int) needRemoveIndex.get(i));
                    }

                    for (String date : datelist) {

                        // 没有date 天数据
                        if (!Datekey.containsKey(date)) {
                            // 获取数据日期位置索引
                            int dataIndex = 0;
                            StatAd appAd = new StatAd();
                            try {
                                dataIndex = ReportDateUtil.getDateIndex(dateListIndex, date);
                                appAd.setAddTime(ReportDateUtil.getDate("yyyy-MM-dd", date));
                            }// 更新日期索引
                            catch (ParseException e) {
                                e.printStackTrace();
                            }

                            // 更新日期索引队列
                            dateListIndex.add(dataIndex, date);

                            // 给予默认0
                            appAd.setShowNum(0);
                            // 给予默认0
                            appAd.setClickNum(0);
                            // 给予默认0
                            appAd.setClickMoney(BigDecimal.ZERO);
                            appAd.setClickRate(BigDecimal.ZERO);

                            // 给予默认0
                            list.add(dataIndex, appAd);
                        }
                    }
                    // 修改排序
                    if (sortFlag) {
                        java.util.Collections.reverse(list);
                    }
                } // 结束汇总
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else { // 开始详情

                // 修改排序
                try {
                    if (sortFlag) {
                        java.util.Collections.reverse(list);
                    }

                    for (Object app1 : list) {
                        StatAd app = (StatAd) app1;
                        Integer id = null;
                        String addTime = ReportDateUtil.getDate("yyyy-MM-dd", app.getAddTime());

                        if (CommonDef.report.REPORT_ADD_AD.equals(type)) {
                            id = app.getAdId();
                        }
                        else if (CommonDef.report.REPORT_ADD_ADGROUP.equals(type)) {
                            id = app.getAdGroupId();
                        }
                        else if (CommonDef.report.REPORT_ADD_CAM.equals(type)) {
                            id = app.getCampaignId();
                        }
                        removeIndex++;
                        // 增强对重复数据的容错能力，有重复的数据直接略过
                        if (!Datekey.containsKey(addTime + id)) {
                            dateListIndex.add(addTime + id);
                            Datekey.put(addTime + id, null);
                        }
                        else {
                            // 将日期重复数据的索引保存
                            needRemoveIndex.add(removeIndex - 1);
                        }
                    }

                    // 移除日期数据重复的索引
                    for (int i = needRemoveIndex.size() - 1; i >= 0; i--) {
                        list.remove((int) needRemoveIndex.get(i));
                    }

                    for (String date : datelist) {
                        try {
                            for (StatAd apptemp : keyNames) {
                                String appid = apptemp.getAdGroupName();
                                String appName = apptemp.getAdName();

                                if (!Datekey.containsKey(date + appid)) {

                                    // 获取数据日期位置索引
                                    int dataIndex = ReportDateUtil.getDateIndex(dateListIndex, date + appid);
                                    // 更新日期索引
                                    dateListIndex.add(dataIndex, date + appid);
                                    StatAd appstat = new StatAd();

                                    appstat.setAdName(appName);
                                    appstat.setCampaignName(appName);
                                    appstat.setAdGroupName(appName);
                                    appstat.setClickRate(BigDecimal.ZERO);

                                    appstat.setAddTime(ReportDateUtil.getDate("yyyy-MM-dd", date));
                                    appstat.setShowNum(0);
                                    appstat.setClickNum(0);
                                    // 给予默认0
                                    appstat.setClickMoney(BigDecimal.ZERO);
                                    list.add(dataIndex, appstat);
                                }
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    // 修改排序
                    if (sortFlag) {
                        java.util.Collections.reverse(list);
                    }
                    if (currentPage != 0) {
                        if (leftCount != 0) {
                            leftCount = fillCount - leftCount;
                        }
                        list =
                                list.subList(leftCount, pageSize + (leftCount) > list.size() ? list.size() : pageSize
                                        + (leftCount));

                    }
                    // end 详情
                }

                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        // TODO
        else if (CommonDef.report.REPORT_ADD_DEVELOP.equals(type)) {// 应用程序报告

            if (CommonDef.report.REPORT_DETAIL_TYPE.equals(status)) {// 详情

                // 修改排序
                if (sortFlag) {
                    java.util.Collections.reverse(list);
                }

                for (Object app1 : list) {
                    StatApp app = (StatApp) app1;
                    String addTime = ReportDateUtil.getDate("yyyy-MM-dd", app.getAddTime());
                    removeIndex++;
                    if (app.getAppId() != null) {
                        if (!Datekey.containsKey(addTime + app.getAppId())) {
                            Datekey.put(addTime + app.getAppId(), null);
                            dateListIndex.add(addTime + app.getAppId());
                        }
                        else {
                            needRemoveIndex.add(removeIndex - 1);
                        }
                    }
                }

                // 移除日期数据重复的索引
                for (int i = needRemoveIndex.size() - 1; i >= 0; i--) {
                    list.remove((int) needRemoveIndex.get(i));
                }

                for (String date : datelist) {
                    // 修改list
                    for (CoreApp apptemp : appList) {
                        if (!Datekey.containsKey(date + apptemp.getAppId())) {
                            try {
                                // 获取数据日期位置索引
                                int dataIndex = ReportDateUtil.getDateIndex(dateListIndex, date + apptemp.getAppId());

                                // 更新日期索引
                                dateListIndex.add(dataIndex, date + apptemp.getAppId());
                                StatApp appstat = new StatApp();
                                appstat.setAppName(apptemp.getAppName());
                                appstat.setAddTime(ReportDateUtil.getDate("yyyy-MM-dd", date));
                                appstat.setMaxNum(0);
                                appstat.setAppCode(apptemp.getAppCode());
                                appstat.setShowNum(0);
                                appstat.setOffer(BigDecimal.ZERO);
                                appstat.setClickNum(0);
                                appstat.setPutRate(BigDecimal.ZERO);
                                appstat.setClickRate(BigDecimal.ZERO);

                                // 给予默认0
                                list.add(dataIndex, appstat);
                            }
                            catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                // 修改排序
                if (sortFlag) {
                    java.util.Collections.reverse(list);
                }

                // 截取数据
                if (currentPage != 0) {
                    if (leftCount != 0) {
                        leftCount = fillCount - leftCount;
                    }
                    list =
                            list.subList(leftCount, pageSize + (leftCount) > list.size() ? list.size() : pageSize
                                    + (leftCount));
                }

            }
            else {
                // 汇总
                // 获取时间数据（用来展示列表顺序），所以这个时轴不用区分不同应用程序
                if (sortFlag) {
                    java.util.Collections.reverse(list);
                }

                for (Object app1 : list) {
                    StatApp app = (StatApp) app1;
                    String date = ReportDateUtil.getDate("yyyy-MM-dd", app.getAddTime());
                    removeIndex++;
                    if (!Datekey.containsKey(date)) {
                        dateListIndex.add(date);
                        Datekey.put(date, null);
                    }
                    else {
                        needRemoveIndex.add(removeIndex - 1);
                    }
                }

                // 移除日期数据重复的索引
                for (int i = needRemoveIndex.size() - 1; i >= 0; i--) {
                    list.remove((int) needRemoveIndex.get(i));
                }

                for (String date : datelist) {
                    if (!Datekey.containsKey(date)) {
                        // 获取数据日期位置索引
                        int dataIndex = 0;
                        StatApp appstat = new StatApp();
                        try {
                            dataIndex = ReportDateUtil.getDateIndex(dateListIndex, date);
                            appstat.setAddTime(ReportDateUtil.getDate("yyyy-MM-dd", date));
                        }// 更新日期索引
                        catch (ParseException e) {
                            e.printStackTrace();
                        }

                        dateListIndex.add(dataIndex, date);
                        appstat.setMaxNum(0);
                        appstat.setShowNum(0);
                        appstat.setClickNum(0);
                        appstat.setPutRate(BigDecimal.ZERO);
                        appstat.setClickRate(BigDecimal.ZERO);

                        // 给予默认0
                        list.add(dataIndex, appstat);
                    }
                }

                // 修改排序
                if (sortFlag) {
                    java.util.Collections.reverse(list);
                }
            }
        }
        return list;
    }

    /***
     * 获取MAP <name,key>
     * 
     * @param type 报表类型
     * @param memberId 创建者编号
     * @param ids ids
     * @return 返回 <name,key>
     * @throws InvalidInfoException
     */
    public List<StatAd> getStaKeyName(String type, Long memberId, String ids) throws InvalidInfoException {
        List<StatAd> keyNames = new ArrayList<StatAd>();

        List<String> inAppId = new ArrayList<String>();
        for (String str : ids.split(",")) {
            inAppId.add(str);
        }

        Collections.sort(inAppId);

        if (CommonDef.report.REPORT_ADD_AD.equals(type)) {

            for (String adId : inAppId) {
                if (adId.equals("0")) {
                    continue;
                }
                CoreAd ad = adService.findCoreAdByAdId(Integer.valueOf(adId), memberId);
                StatAd s = new StatAd();
                s.setAdGroupName(adId);
                s.setAdName(ad == null ? "NULL" : ad.getAdName());
                keyNames.add(s);
            }
        }
        else if (CommonDef.report.REPORT_ADD_ADGROUP.equals(type)) {

            for (String groupId : inAppId) {
                if (groupId.equals("0")) {
                    continue;
                }
                CoreAdGroup adGroup = adGroupService.findById(groupId, memberId);
                StatAd s = new StatAd();
                s.setAdGroupName(groupId);
                s.setAdName(adGroup == null ? "NULL" : adGroup.getAdGroupName());
                keyNames.add(s);
            }
        }
        else if (CommonDef.report.REPORT_ADD_CAM.equals(type)) {

            for (String campaignId : inAppId) {
                if (campaignId.equals("0")) {
                    continue;
                }
                CoreCampaign campaign = campaignService.findById(campaignId, memberId);
                StatAd s = new StatAd();
                s.setAdGroupName(campaignId);
                s.setAdName(campaign == null ? "NULL" : campaign.getCampaignName());
                keyNames.add(s);
            }
        }

        return keyNames;
    }

    /***
     * 获取MAP <name,key>
     * 
     * @param type 报表类型
     * @param memberId 创建者编号
     * @param ids ids
     * @return 返回 <name,key>
     * @throws InvalidInfoException
     */
    public Map<String, String> getStaKeyName2(String type, Long memberId, String ids) throws InvalidInfoException {
        Map<String, String> keyNames = new LinkedHashMap<String, String>();

        List<String> inAppId = new ArrayList<String>();
        for (String str : ids.split(",")) {
            inAppId.add(str);
        }

        Collections.sort(inAppId);

        if (CommonDef.report.REPORT_ADD_AD.equals(type)) {

            for (String adId : inAppId) {
                if (adId.equals("0")) {
                    continue;
                }
                CoreAd ad = adService.findCoreAdByAdId(Integer.valueOf(adId), memberId);
                keyNames.put(adId, ad == null ? "NULL" : ad.getAdName());
            }
        }
        else if (CommonDef.report.REPORT_ADD_ADGROUP.equals(type)) {

            for (String groupId : inAppId) {
                if (groupId.equals("0")) {
                    continue;
                }
                CoreAdGroup adGroup = adGroupService.findById(groupId, memberId);
                keyNames.put(groupId, adGroup == null ? "NULL" : adGroup.getAdGroupName());
            }
        }
        else if (CommonDef.report.REPORT_ADD_CAM.equals(type)) {

            for (String campaignId : inAppId) {
                if (campaignId.equals("0")) {
                    continue;
                }
                CoreCampaign campaign = campaignService.findById(campaignId, memberId);
                keyNames.put(campaignId, campaign == null ? "NULL" : campaign.getCampaignName());
            }
        }

        return keyNames;
    }

    /**
     * 获取id 为 ids 的全部 APP 队列
     * 
     * @param ids 应用编号
     * @param memberId 创建者ID
     * @return
     */
    public List<CoreApp> getAppList(String ids, Long memberId) {

        // 获取应用列表
        List<String> inAppId = new ArrayList<String>();
        for (String str : ids.split(",")) {
            inAppId.add(str);
        }
        Collections.sort(inAppId);

        List<CoreApp> appList = new ArrayList<CoreApp>();
        for (String str : inAppId) {
            if (str.equals("0")) {
                continue;
            }
            try {
                CoreApp app = appService.queryById(Integer.valueOf(str), memberId);
                appList.add(app);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return appList;

    }

}
