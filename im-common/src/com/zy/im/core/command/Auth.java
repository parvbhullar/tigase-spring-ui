package com.zy.im.core.command;

import com.zy.im.core.Command;


/**
 * This class represents the <code>Auth</code> command.
 *
 * @version $Id: Auth.java,v 1.2 2007-11-28 11:26:14 nichele Exp $
 */
public class Auth extends Command implements java.io.Serializable{
    private static final long serialVersionUID = 2010070501L;
    // --------------------------------------------------------------- Constants
    private static final String protocolVersion = "1.0";
    public static final String CMD_NAME       = "AUTH"    ;

    public static final String PARAM_USERNAME = "USERNAME";
    public static final String PARAM_PASS     = "PASS"    ;
    public static final String PARAM_DEVTYPE  = "DEVTYPE"   ;     //设备类型   windows or linux
    public static final String PARAM_FROM     = "FROM"    ;

    // ------------------------------------------------------------ Private data
    private String username = null;
    private String pass     = null;
    private String devtype    = null;
    private String from     = null;


    

    // ------------------------------------------------------------ Constructors
    /**
     * Creates a new instance of <code>Auth</code>.
     */
    public Auth() {
    }

    /**
     * Creates a new instance of <code>Auth</code> with device id, username and
     * user credentials information.
     *
     * @param devid the device id
     * @param username the username
     * @param cred the user credentials
     */
    public Auth(String devtype, String username, String pass) {
        this(devtype, username, pass, null);
    }

    /**
     * Creates a new instance of <code>Auth</code> with device id, username,
     * user credentials and the server originator information.
     *
     * @param devid the device id
     * @param username the username
     * @param cred the user credentials
     * @param from the server originator
     */
    public Auth(String devtype, String username, String pass, String from) {
        this.devtype    = devtype   ;
        this.username = username;
        this.pass     = pass    ;
        this.from     = from    ;
    }

    // ---------------------------------------------------------- Public methods
    /**
     * Returns the command name.
     *
     * @return the command name
     */
    public  String getName() {
        return CMD_NAME;
    }

    /**
     * Sets the device id.
     *
     * @param devid the device id
     */
    public void setDevtype(String devtype) {
        this.devtype = devtype;
    }

    /**
     * Returns the device id.
     *
     * @return the device id
     */
    public String getDevtype() {
        return devtype;
    }

    /**
     * Sets the username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user credentials.
     *
     * @param cred the user credentials
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Returns the user credentials.
     *
     * @return the user credentials
     */
    public String getPass() {
        return pass;
    }

    /**
     * Sets the server originator.
     *
     * @param from the server originator
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Returns the server originator.
     *
     * @return the server originator
     */
    public String getFrom() {
        return from;
    }

    /**
     * Compares <code>this</code> object with input object to establish if are
     * the same object.
     *
     * @param obj the object to compare
     * @return true if the objects are equals, false otherwise
     */
    public boolean deepequals(Object obj) {

        if (obj == null || (!obj.getClass().equals(this.getClass()))) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Auth cmd = (Auth)obj;
        if (this.getDevtype() == null) {
            if (cmd.getDevtype() != null) {
                return false;
            }
        } else {
            if (cmd.getDevtype() == null) {
                return false;
            }

            if (!(this.getDevtype().equals(cmd.getDevtype()))) {
                return false;
            }
        }

        if (this.getUsername() == null) {
            if (cmd.getUsername() != null) {
                return false;
            }
        } else {
            if (cmd.getUsername() == null) {
                return false;
            }

            if (!(this.getUsername().equals(cmd.getUsername()))) {
                return false;
            }
        }

        if (this.getPass() == null) {
            if (cmd.getPass() != null) {
                return false;
            }
        } else {
            if (cmd.getPass() == null) {
                return false;
            }

            if (!(this.getPass().equals(cmd.getPass()))) {
                return false;
            }
        }

        if (this.getFrom() == null) {
            if (cmd.getFrom() != null) {
                return false;
            }
        } else {
            if (cmd.getFrom() == null) {
                return false;
            }

            if (!(this.getFrom().equals(cmd.getFrom()))) {
                return false;
            }
        }

        return true;
    }

//    /**
//     * Returns a string representation of the object.
//     * @return a string representation of the object.
//     */
//    public String toString() {
//        StringBuffer sb = new StringBuffer();
//        sb.append("PARAM_DEVTYPE                 , devtype);
//        sb.append(PARAM_USERNAME              , username);
//        sb.append(PARAM_PASS                  , pass);
//        sb.append(PARAM_FROM                  , from);
//        return sb.toString();
//    }

}