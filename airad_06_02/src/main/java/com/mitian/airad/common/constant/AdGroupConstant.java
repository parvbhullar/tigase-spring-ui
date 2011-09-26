/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.constant;

/**
 * AdGroupConstant.java
 * 
 * @author Administrator
 */
public interface AdGroupConstant {
    /**
     * 0全国2精确到区1常用地区选择
     * 
     * @author Administrator
     */
    enum AdLoclType {
        nation(0, "全国"), oftenarea(1, "常用地区"), area(2, "精确到区");
        AdLoclType(int code, String des) {
            this.code = code;
            this.des = des;
        }
        private int code;
        private String des;

        public int getCode() {
            return code;
        }

        public String getDes() {
            return des;
        }
    }
}
