/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivyinfo.framework.common.file;

import com.ivyinfo.framework.common.exception.IvyinfoException;
import com.ivyinfo.framework.common.resources.ResourcesConfig;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author 张砚
 */
public class ResouceLoader {

    public static URL getFileURL(String url_name) throws IvyinfoException {
        try {
            return new URL(ResourcesConfig.PROTOCOL_FILE + url_name);
        } catch (Exception e) {
            throw new IvyinfoException("Malformed URL " + url_name, e);
        }
    }

    public static String getFilePath(String filePath) throws IvyinfoException {
        return getFileURL(filePath).getPath();
    }

    /**
     * @param url_name
     * @return url
     * */
    public static URL getResouce(String url_name) throws IvyinfoException {
        return ClassLoader.getSystemResource(url_name);
    }

    /**
     * 根据属性文件路径，获取在hashmap中设置键的值，然后返回hashmap
     * @param propertiesPath
     * @param propertiesMap
     * @return
     */
    public HashMap getProperties(String propertiesPath, HashMap propertiesMap) throws IvyinfoException {
        try {
        	System.err.println("propertiesPath:"+propertiesPath);
            Properties p = new Properties();
            try {
                InputStream is = getClass().getResourceAsStream(propertiesPath);
                p.load(is);
                is.close();
            } catch (Exception e) {
                throw new IvyinfoException("无法获取资源，请检查路径！" + propertiesPath);
            }
            Set s = propertiesMap.keySet();
            for (Iterator it = s.iterator(); it.hasNext();) {
                String key = (String) it.next();
                String value = p.getProperty(key);
                if (value == null) {
                    value = "";
                }
                propertiesMap.put(key, value);
            }
            p.clear();
        } catch (Exception e) {
            throw new IvyinfoException(e.getMessage());
        }
        return propertiesMap;
    }
    
    public String getXMLUrl(String path,String configname) {
		String propertiespath = path;
		String xmlUrl = "";
		try {
			Properties p = new Properties();
			try {
				InputStream is = getClass().getResourceAsStream(propertiespath);
				p.load(is);
				is.close();
			} catch (Exception e) {
				System.out.println("找不到文件"+path);
			}
			xmlUrl = p.getProperty(configname);
			p.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlUrl;
	}
}
