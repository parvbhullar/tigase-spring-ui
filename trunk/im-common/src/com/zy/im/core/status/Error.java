package com.zy.im.core.status;

import com.zy.im.core.Status;


/**
 * This class represents the <code>Error</code> status command.
 *
 * @version $Id: Error.java,v 1.2 2007-11-28 11:26:14 nichele Exp $
 */
public class Error extends Status implements java.io.Serializable{
    private static final long serialVersionUID = 2010070504L;
    // --------------------------------------------------------------- Constants
    private static final String protocolVersion = "1.0";
    public static final String CMD_NAME          = "ERROR"      ;

    public static final String PARAM_DESCRIPTION = "DESCRIPTION";

    // ------------------------------------------------------------ Private data
    private String description = null;

    // ------------------------------------------------------------ Constructors
    /**
     * Creates a new instance of <code>Error</code> status command.
     */
    public Error() {
    }

    /**
     * Create a new instance of <code>Error</code> status command with the error
     * description.
     *
     * @param description the description of the error
     */
    public Error(String description) {
        this.description = description;
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
     * Sets the description of the error.
     *
     * @param description the description of the error
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the error.
     *
     * @return the description of the error
     */
    public String getDescription() {
        return description;
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

        Error cmd = (Error)obj;
        if (this.getDescription() == null) {
            if (cmd.getDescription() != null) {
                return false;
            }
        } else {
            if (cmd.getDescription() == null) {
                return false;
            }

            if (!(this.getDescription().equals(cmd.getDescription()))) {
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
//        sb.append(PARAM_DESCRIPTION           , description);
//        return sb.toString();
//    }
}
