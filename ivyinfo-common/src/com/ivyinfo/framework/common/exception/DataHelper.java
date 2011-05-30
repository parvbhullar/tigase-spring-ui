/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivyinfo.framework.common.exception;

import java.sql.SQLException;

/**
 *
 * @author 张砚
 */
public abstract class DataHelper {

    /**
     * 截获数据库错误，并提示相应的内容，如主键重复等。
     * @param se
     * @return String
     * @roseuid 3CF48B480568
     */
    public String getMessage(SQLException ex, Class<? extends Object> clazz) {
        return ex.getMessage();
    }

    public String getMessage(Exception ex, Class<? extends Object> clazz) {
        return ex.getMessage();
    }

    public static String getMessage(IvyinfoException ex, Class<? extends Object> clazz) {
        return ex.getMessage();
    }
}
