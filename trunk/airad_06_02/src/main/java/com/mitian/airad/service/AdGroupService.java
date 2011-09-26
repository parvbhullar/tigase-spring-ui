/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.CommonDef;
import com.mitian.airad.DictionaryConstants;
import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.common.utils.ArrayUtils;
import com.mitian.airad.dao.CoreAdGroupDAO;
import com.mitian.airad.dao.CoreAdGroupextendDAO;
import com.mitian.airad.dao.DictionaryDAO;
import com.mitian.airad.dao.DictionaryGlobalRegionDAO;
import com.mitian.airad.model.CoreAdGroup;
import com.mitian.airad.model.CoreAdGroupextend;
import com.mitian.airad.model.Dictionary;
import com.mitian.airad.model.DictionaryGlobalRegion;
import com.mitian.airad.refreshable.DictionaryRefreshable;
import com.mitian.airad.utils.StringUtil;

/**
 * CoreAdGroupService.java 广告组接口
 * 
 * @author wangzhongwei
 */
@Service
public class AdGroupService {
    /** spring注入CoreAdGroupDAO */
    @Autowired
    private CoreAdGroupDAO coreAdGroupDAO;
    @Autowired
    private CoreAdGroupextendDAO adGroupextendDAO;

    /**
     * @return the coreAdGroupDAO
     */
    public CoreAdGroupDAO getCoreAdGroupDAO() {
        return coreAdGroupDAO;
    }

    /**
     * @param coreAdGroupDAO the coreAdGroupDAO to set
     */
    public void setCoreAdGroupDAO(CoreAdGroupDAO coreAdGroupDAO) {
        this.coreAdGroupDAO = coreAdGroupDAO;
    }

    @Autowired
    private DictionaryDAO dictionaryDao;

    @Autowired
    private DictionaryGlobalRegionDAO dictionaryGlobalRegionDAO;
    @Autowired
    private DictionaryRefreshable cache;

    public Map<String, Integer> preparePlatformNumMap(CoreAdGroup adGroup) {
        String s = adGroup.getAdTagSp();
        String[] platform = s.split(",");
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("platformNumber", platform.length);
        return map;
    }

    public List<String> preparePlatforms(CoreAdGroup adGroup) {
        String s = adGroup.getAdTagSp();
        String[] platform = s.split(",");
        List<String> list = new ArrayList<String>();
        list = ArrayUtils.toList(platform);

        return list;
    }

    public List<CoreAdGroup> queryAllGroupByTimeSlot(Map params) {
        return coreAdGroupDAO.findAllGroupByTimeSlot(params);
    }

    public int queryCountTotalByTimeSlot(Map<String, String> params) {
        return coreAdGroupDAO.findCountByTimeSlot(params);
    }

    /**
     * copy活动
     * 
     * @param key 广告组
     * @param memberId 会员id
     */
    public void txCopy(CoreAdGroup key, Long memberId) {
        key.setAddTime(new Date());
        key.setUpdTime(new Date());
        coreAdGroupDAO.txCopy(key, memberId);
    }

    /**
     * 查询所有广告组
     * 
     * @return
     */
    public List<CoreAdGroup> queryAdGroupList(String adGroupName, String campaignId, Long memberId, int record,
            int pageSize) {
        Map<String, Object> params = new HashMap<String, Object>(4);
        if (StringUtils.isNotEmpty(adGroupName)) {
            if (!adGroupName.contains("/")) {
                params.put("escape", CommonDef.escape.ESCAPE);
            }
            adGroupName = StringUtil.replaceChar(adGroupName);
            params.put("adGroupName", adGroupName);
        }
        // 去掉campaignId的条件
        // params.put("campaignId", campaignId);
        params.put("memberId", memberId);
        List<CoreAdGroup> list = new ArrayList<CoreAdGroup>();
        // if (StringUtils.isNotBlank(campaignId)) {
        list = coreAdGroupDAO.findAllGroup(params, record, pageSize);
        // }
        return list;
    }

    /**
     * 广告组查询
     * 
     * @param params
     * @return
     */
    public List<CoreAdGroup> queryAdGroupList(Map<String, String> params) {
        List<CoreAdGroup> list = new ArrayList<CoreAdGroup>();
        list = coreAdGroupDAO.findAllGroup(params);
        return list;
    }

    /**
     * 查询所有
     * 
     * @param memeberId
     * @return
     */
    public List<CoreAdGroup> findAllGroupByMemberId(Long memeberId) {
        List<CoreAdGroup> list = coreAdGroupDAO.findAllGroupByMemberId(memeberId);
        return list;

    }

    /**
     * 查询所有
     * 
     * @param memeberId
     * @return
     */
    public List<CoreAdGroup> findAllGroupByWithOutMemberId(Long memeberId) {
        List<CoreAdGroup> list = coreAdGroupDAO.findAllGroupByWithOutMemberId(memeberId);
        return list;

    }

    public int countTotal(String adGroupName, String campaignId, Long memberId) {
        Map<String, Object> params = new HashMap<String, Object>(4);
        if (StringUtils.isNotEmpty(adGroupName)) {
            if (!adGroupName.contains("/")) {
                params.put("escape", CommonDef.escape.ESCAPE);
            }
            adGroupName = StringUtil.replaceChar(adGroupName);
            params.put("adGroupName", adGroupName);
        }
        // 去掉campaignId的条件
        // params.put("campaignId", campaignId);
        params.put("memberId", memberId);
        int size = 0;
        // if (StringUtils.isNotBlank(campaignId)) {
        size = coreAdGroupDAO.countTotal(params);

        // }
        return size;
    }

    public int countTotal(String campaignId) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("campaignId", campaignId);
        int size = 0;
        if (StringUtils.isNotBlank(campaignId)) {
            size = coreAdGroupDAO.countTotal(params);

        }
        return size;

    }

    /**
     * 添加广告组信息
     * 
     * @param coreAdGroup
     * @return
     */
    public int txCreateAdGroup(CoreAdGroup coreAdGroup) {
        coreAdGroup.setDelFlag("0");
        // 默认运行
        coreAdGroup.setSuspendType("0");
        // 临时写入值男性为主
        coreAdGroup.setAdTagSex("1");
        // 临时写入全年龄
        coreAdGroup.setAdTagAge("0");
        // 临时写入值贫媒体
        coreAdGroup.setAdTagType("1");
        // 针对所有流量
        coreAdGroup.setAdFlowInfo("0");
        // 联通
        coreAdGroup.setChangceInfo("0");
        coreAdGroup.setAddTime(new Date());
        int id = 0;
        id = coreAdGroupDAO.insertSelective(coreAdGroup);
        return id;

    }

    /**
     * 获取设备字典数据2级
     * 
     * @return
     */
    public List<Dictionary> queryDictionaryByType2() {
        String type1 = null; // "TAG_SP";
        String type2 = "GROUP_TAG_SP";
        List<Dictionary> arr = dictionaryDao.findListByType(type1, type2);
        return arr;
    }

    /**
     * 获取设备字典数据1级
     * 
     * @return
     */
    public List<Dictionary> queryDictionaryByType1() {
        String type1 = "GROUP_TAG_SP_KEY";
        String type2 = null;// "GROUP_TAG_SP";
        List<Dictionary> arr = dictionaryDao.findListByType(type1, type2);
        return arr;
    }

    public String getTreeString() throws JSONException {
        List<Dictionary> arr1 = queryDictionaryByType1();
        List<Dictionary> arr2 = queryDictionaryByType2();
        JSONArray arr = new JSONArray();
        for (Dictionary d : arr1) {
            JSONObject obj = new JSONObject();
            String id = d.getDictKey();
            String text = d.getDictVal();
            obj.put("id", id);
            obj.put("text", text);
            arr.put(obj);
        }
        JSONObject head = new JSONObject();
        head.put("id", "999");
        head.put("text", "全部");
        head.put("children", arr);
        JSONArray json = new JSONArray();
        json.put(head);
        String jsonstr = json.toString();
        return jsonstr;
    }

    /**
     * 修改广告组根据广告组id
     * 
     * @param coreAdGroup
     * @return
     */
    public Map<String, Object> txUpdateAdGroup(CoreAdGroup coreAdGroup) {
        Map<String, Object> result = new HashMap<String, Object>(1);
        // if (coreAdGroup.getAdGroupId() > 0 && coreAdGroup.getCampaignId() > 0) {
        // result.put("coreAdGroup", coreAdGroup.getAdGroupId());
        // coreAdGroup.setDelFlag("0");
        coreAdGroupDAO.updateByPrimaryKeySelective(coreAdGroup);
        // }
        return result;
    }

    /**
     * 修改所属地区
     * 
     * @param coreAdGroup
     * @return
     */
    public void txSaveOrUpdateAdGroup(CoreAdGroup coreAdGroup, CoreAdGroupextend adGroupextend) {
        coreAdGroupDAO.updateByPrimaryKeySelective(coreAdGroup);
        if (adGroupextend.getAdGroupextendId() != null) {
            adGroupextendDAO.updateByPrimaryKeySelective(adGroupextend);
        }
        else {

            adGroupextendDAO.insertSelective(adGroupextend);
        }
    }

    /**
     * 根据广告组 id查询广告组信息
     * 
     * @param adGroupId
     * @return
     * @throws InvalidInfoException
     */
    public CoreAdGroup queryAdGroupByAdGroupId(Integer adGroupId, Long memberId) throws InvalidInfoException {
        CoreAdGroup cgd = null;
        if (null != adGroupId && adGroupId > 0) {
            cgd = coreAdGroupDAO.findByPrimaryKey(adGroupId, memberId);
            if (cgd == null) {
                throw new InvalidInfoException("get queryAdGroupByAdGroupId Error adGroupId: " + adGroupId
                        + "  ,memberId" + memberId);
            }
        }
        return cgd;

    }

    public CoreAdGroup findById(String adGroupId, Long memberId) throws InvalidInfoException {
        CoreAdGroup adGroup = null;
        if (StringUtils.isNotBlank(adGroupId)) {
            int gId = Integer.parseInt(adGroupId);
            adGroup = queryAdGroupByAdGroupId(gId, memberId);
            Map<String, Integer> num = preparePlatformNumMap(adGroup);
            // 查看区域类型
            adGroup.setAdLoclTypeTitle(cache.getValueByTypeKeyAndValueKey(DictionaryConstants.AD_GROUP_LOCL_TYPE,
                    adGroup.getAdLoclType()));
            adGroup.setPlatformNumber(num.get("platformNumber"));// 获取平台数
            adGroup.setOsNumber(num.get("osNumber"));// 获取终端数
        }
        return adGroup;
    }

    /**
     * 删除广告组根据广告组id
     * 
     * @param coreAdGroup
     * @return
     */
    public void txDeleteAdGroupByAdGroupId(final CoreAdGroup coreAdGroup) {
        coreAdGroupDAO.batchDelete(coreAdGroup);
    }

    /**
     * 暂停广告组根据广告组id
     * 
     * @param coreAdGroup
     * @return
     */
    public Map<String, Object> txStopAdGroupByAdGroupId(String adGroupId) {
        Map<String, Object> result = new HashMap<String, Object>(1);
        CoreAdGroup coreAdGroup = new CoreAdGroup();
        if (StringUtils.isNotBlank(adGroupId)) {
            coreAdGroup.setAdGroupId(Integer.parseInt(adGroupId));
            result.put("adGroupId", adGroupId);
        }
        return result;
    }

    /**
     * 查出所有省
     * 
     * @param dictionaryGlobalRegion
     * @return
     */
    public List<DictionaryGlobalRegion> queryProvList(DictionaryGlobalRegion dictionaryGlobalRegion) {
        return dictionaryGlobalRegionDAO.findProvList(dictionaryGlobalRegion);
    }

    /**
     * 根据省查出所有市
     * 
     * @param dictionaryGlobalRegion
     * @return
     */
    public List<DictionaryGlobalRegion> queryCityList(DictionaryGlobalRegion dictionaryGlobalRegion) {
        return dictionaryGlobalRegionDAO.findCityList(dictionaryGlobalRegion);
    }

    public String getJson(List<DictionaryGlobalRegion> cityList, String proId) throws JSONException {
        // StringBuffer sb = new StringBuffer();
        // sb.append("[{\"text\":\"全省\",\"id\":\"0000\",\"children\":");
        // StringBuffer jsonobj = new StringBuffer();
        // jsonobj.append("[");

        JSONArray jsonarry = new JSONArray();
        DictionaryGlobalRegion d = new DictionaryGlobalRegion();
        d.setRegionType("3");
        List<DictionaryGlobalRegion> areaList = dictionaryGlobalRegionDAO.findAreaList(d);
        for (DictionaryGlobalRegion dictionaryGlobalRegion : cityList) {
            JSONObject jsonobj = new JSONObject();
            JSONArray child = new JSONArray();
            for (DictionaryGlobalRegion area : areaList) {
                JSONObject childObject = new JSONObject();
                if (area.getParentId().equals(dictionaryGlobalRegion.getRegionId())) {
                    String name = area.getRegionName();
                    String id = area.getRegionId().toString();
                    childObject.put("id", id);
                    childObject.put("text", name);
                    childObject.put("area", proId + "," + dictionaryGlobalRegion.getRegionId().toString() + "," + id);
                    child.put(childObject);
                }

            }
            String name = dictionaryGlobalRegion.getRegionName();
            String id = dictionaryGlobalRegion.getRegionId().toString();
            // jsonobj.append("{text:'").append(name).append("',id:'").append(id).append("',children:").append(
            // child.toString()).append("},");
            jsonobj.put("id", id);
            jsonobj.put("text", name);
            jsonobj.put("children", child);
            jsonarry.put(jsonobj);
        }

        // if (jsonobj.length() > 1) {
        // jsonobj.setCharAt(jsonobj.length() - 1, ']');
        // }
        // String json = jsonobj.toString();
        // String json = sb.append(jsonobj.toString()).append("}]").toString();
        JSONObject json = new JSONObject();
        json.put("text", "全省");
        json.put("id", proId);
        json.put("children", jsonarry);
        JSONArray jsonstr = new JSONArray();
        jsonstr.put(json);

        return jsonstr.toString();
    }

    /**
     * 装换数据格式
     * 
     * @param dictionaryGlobalRegion
     * @return
     */
    public String exact(String exact) {
        exact = exact.replaceAll("0000,", "");
        StringBuffer buff = new StringBuffer();
        String[] s = exact.split(";");
        for (int i = 0; i < s.length; i++) {
            String[] s4 = s[i].split(",");
            String s2 = s4[0] + "," + s4[1] + ",";
            for (int j = 2; j < s4.length; j++) {
                buff.append(s2 + s4[j] + ";");
            }
        }
        return buff.toString();
    }

    /**
     * 转换拼写省市区
     * 
     * @param exact
     * @return
     */
    @SuppressWarnings("unchecked")
    public String exactChange(String exact) {
        Map<String, List> map = new LinkedHashMap<String, List>();
        String[] s1 = exact.split(";");
        for (int i = 0; i < s1.length; i++) {
            String s2 = s1[i].substring(0, s1[i].lastIndexOf(","));
            List list = null;
            if (!map.containsKey(s2)) {
                map.put(s2, new ArrayList());
            }
            list = map.get(s2);
            String s3 = s1[i].substring(s1[i].lastIndexOf(",") + 1);
            list.add(s3);
        }
        String str = "";

        for (Entry<String, List> identry : map.entrySet()) {
            String eid = identry.getKey();
            String sl = "";
            List list = map.get(eid);

            for (int j = 0; j < list.size(); j++) {
                String s = (String) list.get(j);
                sl += s + ",";
            }
            sl = sl.substring(0, sl.length() - 1);
            str += eid + ",0000," + sl + ";";

        }
        return str;
    }

    /**
     *根据用户id翻页查询广告组
     */
    public List<CoreAdGroup> findGroupByMemberIdByPage(Long memberId, Map map) {
        List<CoreAdGroup> list = coreAdGroupDAO.findGroupByMemberIdByPage(memberId, map);
        return null;

    }

    /**
     * 根据用户ID 统计默认分组个数
     * 
     * @param params
     * @return
     */
    public int queryCountDefaultCount(Map<String, String> params) {
        return coreAdGroupDAO.findCountDefaultGroup(params);
    }

    public int queryCountDefaultAdCount(Map<String, String> params) {
        return coreAdGroupDAO.findCountDefaultAd(params);
    }

    /**
     * 根据广告组更新广告冻结状态
     * 
     * @param params
     */
    public void updateAdSuspendType(Map<String, String> params) {
        coreAdGroupDAO.updateAdSuspendType(params);
    }

    /**
     * 根据会员ID,查询该会员所有的广告组信息
     * 
     * @param memberId
     * @return
     */
    public List<CoreAdGroup> selectGroupListByMemberId(Long memberId) {
        return coreAdGroupDAO.selectGroupListByMemberId(memberId);
    }
}
