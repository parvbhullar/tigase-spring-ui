/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivyinfo.framework.common.exception;


import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class ExceptionHelper extends DataHelper {

    public static String getMessage(IvyinfoException se, Class<? extends Object> clazz) {
        if (se.toString().indexOf("SQL0000W") > 0) {
            return ("操作当前数据库成功");
        }else{
           JOptionPane.showMessageDialog(null, se.getMessage(), "未知错误", JOptionPane.ERROR_MESSAGE);
           return se.getMessage();
        }
    }
}
