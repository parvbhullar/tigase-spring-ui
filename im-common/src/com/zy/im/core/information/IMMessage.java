/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zy.im.core.information;

import com.zy.im.core.Information;
import java.util.HashMap;
/**
 *
 * @author Administrator
 */
public class IMMessage extends Information implements java.io.Serializable {
//    private static final long serialVersionUID = 2010070510L;
    /** protocol version */
    private String protocolVersion = "1.0";
    /** command or status code */
    private String CMD_NAME = "-1";
    /** Parameters. Codes and values (must be kept in sync) */
    private HashMap parameter = new HashMap();

    /** Build an empty message */
    public IMMessage() {
    }

    /**
     * Sets the command or status code
     *
     * @param the
     *            new code
     */
    public void setCommand(String commandName) {
        this.CMD_NAME = commandName;
    }

    /** Returns the command or status code */
    public String getCommand() {
        return CMD_NAME;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    /**
     * Add one parameter
     *
     * @param code
     *            parameter name
     * @param value
     *            parameter value
     */
    public void addParameter(String name, String value) {
        parameter.put(name, value);
    }

    @Override
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
        final IMMessage other = (IMMessage) obj;
        if ((this.protocolVersion == null) ? (other.protocolVersion != null) : !this.protocolVersion.equals(other.protocolVersion)) {
            return false;
        }
        if ((this.CMD_NAME == null) ? (other.CMD_NAME != null) : !this.CMD_NAME.equals(other.CMD_NAME)) {
            return false;
        }
        if (this.parameter != other.parameter && (this.parameter == null || !this.parameter.equals(other.parameter))) {
            return false;
        }
        return true;
    }
}

