package com.zy.im.core.status;

import com.zy.im.core.Status;


/**
 * This class represents the <code>Forbidden</code> status command.
 *
 * @version $Id: Forbidden.java,v 1.2 2007-11-28 11:26:14 nichele Exp $
 */
public class Forbidden extends Status implements java.io.Serializable{
    private static final long serialVersionUID = 2010070505L;
    // --------------------------------------------------------------- Constants
    private static final String protocolVersion = "1.0";
    public static final String CMD_NAME = "FORBIDDEN";   //被禁止的

    // ------------------------------------------------------------ Constructors
    /**
     * Creates a new instance of <code>Forbidden</code> status command.
     */
    public Forbidden() {
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
