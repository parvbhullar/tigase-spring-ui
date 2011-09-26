/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RandomTest.java
 * 
 * @author xiayuqi
 */
public abstract class RandomUtils {
    public static void main(String[] args) {
        String[] addrs = {"我", "要", "测试", "随机", "数字"};
        Random rand = new Random();
        System.out.println(rand.nextInt(5));
        String server_selected = addrs[rand.nextInt(5)];
        System.out.println(server_selected);
    }

    /**
     * 工具类无法实例化
     */
    private RandomUtils() {
        super();
    }

    /**
     * 从集合中随机取count个，生成集合
     * 
     * @param list
     * @param count
     * @return
     */
    public static List getRandomList(List list, int count) {
        if (count <= 0) {
            return null;
        }
        if (count > list.size()) {
            return list;
        }
        Random rand = new Random();
        List result = new ArrayList();
        for (int i = 0; i < count; i++) {
            int index = rand.nextInt(list.size());
            Object o = list.get(index);
            result.add(o);
            list.remove(index);
        }

        return result;
    }
}
