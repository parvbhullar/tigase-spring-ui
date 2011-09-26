/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.refreshable;

/**
 * Refreshable.java
 * 
 * @author baojun
 */
public abstract class Refreshable {

    private static Object lock = new Object();
    private static boolean needRefresh = true;

    public void checkIfShouldRefresh() {
        if (needRefresh) {
            synchronized (lock) {
                if (needRefresh) {
                    refresh();
                    needRefresh = false;
                }
            }
        }
    }

    public void setNeedRefresh() {
        needRefresh = true;
    }

    // bug 477 测试用，看缓存是否刷新标志有问题
    public boolean isNeedRefresh() {
        return needRefresh;
    }

    /**
     * 刷新方法，待子类继承
     */
    public abstract void refresh();

}
