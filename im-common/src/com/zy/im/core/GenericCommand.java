package com.zy.im.core;



/**
 * This is a base class for "command" classes.
 *
 * @version $Id: GenericCommand.java,v 1.2 2007-11-28 11:26:14 nichele Exp $
 */
public abstract class GenericCommand{
    /**
     * Creates a new instance of <code>GenericCommand</code> command.
     */
    public GenericCommand() {
    }

    /**
     * Returns the command name.
     *
     * @return the name of the command
     */
    public abstract String getName();

    /**
     * Compares <code>this</code> object with input object to establish if are
     * the same object.
     *
     * @param obj the object to compare
     * @return true if the objects are equals, false otherwise
     */
    public abstract boolean deepequals(Object obj);


    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[COMMAND]"+getName());
        return sb.toString();
    }
}
