package com.ivyinfo.numbersegment.util;
import java.util.HashMap; 
import java.util.Map; 
import java.sql.SQLException; 

import org.springframework.jdbc.core.JdbcTemplate;

public class NumberSegUtils { 
    private static NumberSegUtils _instance = new NumberSegUtils(); 
    private Map<String, KeyInfo> keyMap = new HashMap<String, KeyInfo>(1); //NumberSeg载体容器 
    private static final int POOL_SIZE = 1;      //NumberSeg值缓存大小 

    /** 
     * 禁止外部实例化 
     */ 
    private NumberSegUtils() { 
    } 

    /** 
     * 获取NumberSegUtils的单例对象 
     * @return NumberSegUtils的单例对象 
     */ 
    public static NumberSegUtils getInstance() { 
        return _instance; 
    } 

    /** 
     * 获取下一个NumberSeg键值 
     * @param keyName NumberSeg名称 
     * @return 下一个NumberSeg键值 
     */ 
    public synchronized long getNextKeyValue(String keyName,String orgid) { 
        KeyInfo keyInfo = null; 
        Long keyObject = null; 
        try { 
            if (keyMap.containsKey(keyName)) { 
                keyInfo = keyMap.get(keyName); 
            } else { 
                keyInfo = new KeyInfo(keyName, POOL_SIZE, orgid); 
                keyMap.put(keyName, keyInfo); 
            } 
            keyObject = keyInfo.getNextKey(orgid); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
        return keyObject; 
    } 
}
