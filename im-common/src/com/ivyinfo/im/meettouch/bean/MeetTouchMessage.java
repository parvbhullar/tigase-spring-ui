/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivyinfo.im.meettouch.bean;

import com.zy.im.core.Information;
import com.zy.im.core.InformationType;

/**
 *
 * @author Administrator
 */
public class MeetTouchMessage extends Information implements java.io.Serializable {
    /** protocol version */
    private String protocolVersion = "1.0";
    /** command or status code */
    private String CMD_NAME = InformationType.POINT_TO_POINT;
    // ---------------------------------------------------------- private methods
    /** 自定义传输值 String 型**/
    private String senduser = "";
    private String touser = "";
    private String message = "";
    private String messagetype = "";
    private String sendtime = "";
    private String nicesenduser = "";
    private String nicetouser = "";
    
    private String id = "";
    private String readtime = "";
    private String readflag = "";

    // ---------------------------------------------------------- Public methods
    

	public String getMessage() {
		return message;
	}

	public String getSenduser() {
		return senduser;
	}

	public void setSenduser(String senduser) {
		this.senduser = senduser;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMessagetype() {
		return messagetype;
	}

	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReadtime() {
		return readtime;
	}

	public void setReadtime(String readtime) {
		this.readtime = readtime;
	}

	public String getReadflag() {
		return readflag;
	}

	public void setReadflag(String readflag) {
		this.readflag = readflag;
	}
	
	

	public String getNicesenduser() {
		return nicesenduser;
	}

	public void setNicesenduser(String nicesenduser) {
		this.nicesenduser = nicesenduser;
	}
	

	public String getNicetouser() {
		return nicetouser;
	}

	public void setNicetouser(String nicetouser) {
		this.nicetouser = nicetouser;
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
        final MeetTouchMessage other = (MeetTouchMessage) obj;
        if ((this.senduser == null) ? (other.senduser != null) : !this.senduser.equals(other.senduser)) {
            return false;
        }
        if ((this.touser == null) ? (other.touser != null) : !this.touser.equals(other.touser)) {
            return false;
        }
        if ((this.message == null) ? (other.message != null) : !this.message.equals(other.message)) {
            return false;
        }
        if ((this.messagetype == null) ? (other.messagetype != null) : !this.messagetype.equals(other.messagetype)) {
            return false;
        }
        return true;
    }
}
