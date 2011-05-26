package com.zy.im.core.status;

import com.zy.im.core.Status;


/**
 * This class represents a <code>Jump</code> status command.
 *
 * @version $Id: Jump.java,v 1.2 2007-11-28 11:26:14 nichele Exp $
 */
public class Jump extends Status implements java.io.Serializable{
    private static final long serialVersionUID = 2010070506L;
    // --------------------------------------------------------------- Constants
    private static final String protocolVersion = "1.0";
    public static final String CMD_NAME = "JUMP";

    public static final String PARAM_FROM = "FROM";
    public static final String PARAM_TO   = "TO"  ;

    // ------------------------------------------------------------ Private data
    private String from = null;
    private String to   = null;

    // ------------------------------------------------------------ Constructors
    /**
     * Creates a new instance of <code>Jump</code> status command.
     */
    public Jump() {
    }

    /**
     * Creates a new instance of <code>Jump</code> status command with the
     * originator server, and the ip and port of the server at which the client
     * should connect.
     *
     * @param from the originator server
     * @param to the ip and port of the server at which the client should
     * connect
     */
    public Jump(String from, String to) {
        this.from = from;
        this.to   = to  ;
    }

    // ---------------------------------------------------------- Public methods
    /**
     * Returns the command name.
     * @return the command name
     */
    public String getName() {
        return CMD_NAME;
    }

    /**
     * Sets the originator server.
     *
     * @param from the originator server
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Returns the originator server.
     *
     * @return the originator server
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the ip and port of the server at which the client should connect.
     *
     * @param to the ip and port of the server.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Returns the ip and port of the server at which the client should connect.
     *
     * @return the ip and port of the server
     */
    public String getTo() {
        return to;
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

        Jump cmd = (Jump)obj;
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

        if (this.getTo() == null) {
            if (cmd.getTo() != null) {
                return false;
            }
        } else {
            if (cmd.getTo() == null) {
                return false;
            }

            if (!(this.getTo().equals(cmd.getTo()))) {
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
//        ToStringBuilder sb = new ToStringBuilder(this);
//        sb.appendSuper(super.toString());
//        sb.append(PARAM_FROM, from);
//        sb.append(PARAM_TO, to);
//        return sb.toString();
//    }
}
