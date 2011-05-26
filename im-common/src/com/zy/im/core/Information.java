/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zy.im.core;

/**
 *
 * @author Administrator
 */
public abstract class Information extends GenericCommand {

//    private static final long serialVersionUID = 2805284943658356093L;
    /** protocol version */
    private  static String protocolVersion = "1.0";
    /** command or status code */
    public  static String CMD_NAME = "Information";

    /**
     * Creates a new instance of <code>Information</code>.
     */
    public Information() {
    }

    
}
