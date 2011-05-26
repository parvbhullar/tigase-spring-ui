/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivyinfo.im.demo.bean;

import com.zy.im.core.Information;
import com.zy.im.core.InformationType;

/**
 *
 * @author Administrator
 */
public class TestMessage extends Information implements java.io.Serializable {
    /** protocol version */
    private String protocolVersion = "1.0";
    /** command or status code */
    private String CMD_NAME = InformationType.NOT_RECEIVE;
    // ---------------------------------------------------------- private methods
    /** 自定义传输值 String 型**/
    private String sendUser = "";
    private String testValue = "";

    // ---------------------------------------------------------- Public methods
    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }

    /**
     * Sets the command or status code
     *
     * @param the
     *            new code
     */
    public void setCommand(String CMD_NAME) {
        this.CMD_NAME = CMD_NAME;
    }

    /**
     * Returns the command name.
     *
     * @return the command name
     */
    public String getName() {
        return CMD_NAME;
    }

    @Override
    public boolean deepequals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TestMessage other = (TestMessage) obj;
        if ((this.testValue == null) ? (other.testValue != null) : !this.testValue.equals(other.testValue)) {
            return false;
        }
        return true;
    }
}
