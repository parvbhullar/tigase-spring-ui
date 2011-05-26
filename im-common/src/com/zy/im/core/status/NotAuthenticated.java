package com.zy.im.core.status;

import com.zy.im.core.Status;


/**
 * This class represents an <code>NotAuthenticated</code> status command.
 *
 * @version $Id: NotAuthenticated.java,v 1.4 2007-11-28 11:26:14 nichele Exp $
 */
public class NotAuthenticated extends Status implements java.io.Serializable{
    private static final long serialVersionUID = 2010070507L;
    // --------------------------------------------------------------- Constants
    private static final String protocolVersion = "1.0";
    public static final String CMD_NAME    = "NOT_AUTHENTICATED";    //未通过身份验证

    // ------------------------------------------------------------ Constructors
    /** Creates a new instance of <code>NotAuthenticated</code> status command. */
    public NotAuthenticated() {
    }

    // ---------------------------------------------------------- Public methods
    /**
     * Returns the command name.
     *
     * @return the command name
     */
    public String getName() {
        return CMD_NAME;
    }

    /**
     * Compares <code>this</code> object with input object to establish if are
     * the same object.
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

        return true;
    }

//    /**
//     * Returns a string representation of the object.
//     * @return a string representation of the object.
//     */
//    public String toString() {
//        ToStringBuilder sb = new ToStringBuilder(this);
//        sb.appendSuper(super.toString());
//        return sb.toString();
//    }

}
