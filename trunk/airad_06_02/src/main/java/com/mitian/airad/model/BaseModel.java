/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.model;

import org.apache.commons.lang.StringUtils;

/**
 * BaseModel.java
 * 
 * @author baojun
 */
public abstract class BaseModel {

    /**
     * 列表查询的数量限制
     */
    private int limit;
    /**
     * 分页码
     */
    private int offset;

    /**
     * 排序字段
     */
    private String sortName;
    /**
     * 降序还是升序
     */
    private String sortType;

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * @return the sortName
     */
    public String getSortName() {
        return sortName;
    }

    /**
     * @param sortName the sortName to set
     */
    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    /**
     * @return the sortType
     */
    public String getSortType() {
        if (StringUtils.endsWithIgnoreCase("desc", StringUtils.trimToEmpty(sortType))) {
            return "desc";
        }
        return "asc";
    }

    /**
     * @param sortType the sortType to set
     */
    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

}
