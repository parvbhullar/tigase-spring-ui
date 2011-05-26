package com.zy.im.core;


/**
 * This represents a CTP Message.
 *
 * @version $Id: Message.java,v 1.2 2007-11-28 11:26:14 nichele Exp $
 */
public class Message {

    // ------------------------------------------------------------ Private data
    private String         protocolVersion;
    private GenericCommand command        ;

    // ------------------------------------------------------------ Constructors
    /**
     * Creates a new instance of Message.
     */
    public Message() {
    }

    /**
     * Creates a new instance of Message with the protocol version and a
     * command.
     *
     * @param protocolVersion the protocol version
     * @param command the command
     */
    public Message(String protocolVersion, GenericCommand command) {
        this.protocolVersion = protocolVersion;
        this.command         = command        ;
    }

    // ---------------------------------------------------------- Public methods
    /**
     * Sets the protocol version.
     *
     * @param protocolVersion the protocol version
     */
    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    /**
     * Returns the protocol version.
     *
     * @return the protocol version
     */
    public String getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * Sets the command.
     *
     * @param command the command
     */
    public void setCommand(GenericCommand command) {
        this.command = command;
    }

    /**
     * Returns the command.
     *
     * @return the command
     */
    public GenericCommand getCommand() {
        return command;
    }

    /**
     * Compares <code>this</code> object with input object to establish if are
     * the same object.
     *
     * @param obj the object to compare
     * @return true if the objects are equals, false otherwise
     */
    public boolean deepequals(Object obj) {
         if( this == obj ) {
             return true;
         }

         if( obj instanceof Message) {
             Message msg = (Message)obj;

             if ( (msg.getProtocolVersion() == null && this.getProtocolVersion() != null) ||
                  (msg.getProtocolVersion() != null && this.getProtocolVersion() == null) ) {
                 return false;
             }
             if (!msg.getProtocolVersion().equals(this.getProtocolVersion())) {
                 return false;
             }

             if (msg.getCommand() != null && this.getCommand() != null) {

                 return msg.getCommand().deepequals(this.getCommand());

             } else if (msg.getCommand() == null && this.getCommand() == null) {
                 return true;
             }
         }
         return false;
     }


    public String toString() {
        return getProtocolVersion() + " " + getCommand().toString();
    }
}
