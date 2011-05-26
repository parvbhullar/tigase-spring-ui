package com.ivyinfo.im.meettouch.bean;

import com.zy.im.core.Information;
import com.zy.im.core.InformationType;

public class UserStatus extends Information implements java.io.Serializable {
    /** protocol version */
    private String protocolVersion = "1.0";
    /** command or status code */
    private String CMD_NAME = InformationType.NOT_RECEIVE;
    
    // ---------------------------------------------------------- private methods
    /** 自定义传输值 String 型**/
    private String user = "";
    private String status = "";
    private String clienttype = "";
    
    private String id = "";
    private String lasttime = "";

    // ---------------------------------------------------------- Public methods
    

	/**
     * Sets the command or status code
     *
     * @param the
     *            new code
     */
    public void setCommand(String CMD_NAME) {
        this.CMD_NAME = CMD_NAME;
    }

    public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClienttype() {
		return clienttype;
	}

	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
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
        final UserStatus other = (UserStatus) obj;
        if ((this.user == null) ? (other.user != null) : !this.user.equals(other.user)) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        if ((this.clienttype == null) ? (other.clienttype != null) : !this.clienttype.equals(other.clienttype)) {
            return false;
        }
        return true;
    }
}