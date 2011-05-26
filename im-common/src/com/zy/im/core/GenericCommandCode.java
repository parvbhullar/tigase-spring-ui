package com.zy.im.core;

//import org.apache.commons.collections.BidiMap;
//import org.apache.commons.collections.bidimap.DualHashBidiMap;


/**
 * This class represents the possible command code (both command and status).
 *
 * @version $Id: GenericCommandCode.java,v 1.3 2007-11-28 11:26:14 nichele Exp $
 */
public class GenericCommandCode {

    // --------------------------------------------------------------- Constants
    // Command code
    public static final String AUTH_CODE  = "AUTH";
    public static final String READY_CODE = "READY";
    public static final String BYE_CODE   = "BYE";
    
    // Status code
    public static final String OK_CODE                = "OK";
    public static final String JUMP_CODE              = "JUMP";
    public static final String ERROR_CODE             = "ERROR";
    public static final String NOT_AUTHENTICATED_CODE = "NOT_AUTHENTICATED";
    public static final String UNAUTHORIZED_CODE      = "UNAUTHORIZED";
    public static final String FORBIDDEN_CODE         = "FORBIDDEN";

    //Message code
//    public static final String INFORMATION_CODE   = "Information";

    // ------------------------------------------------------------ Private data
//    private static BidiMap codeToClass = null;

    // ------------------------------------------------------------ Constructors
    /**
     * Creates a new instance of <code>GenericCommandCode</code>.
     */
    private GenericCommandCode() {
    }

//    static {
//        codeToClass = new DualHashBidiMap();
//
//        codeToClass.put(AUTH_CODE        , com.zy.im.core.command.Auth.class );
//        codeToClass.put(READY_CODE       , com.zy.im.core.command.Ready.class);
//        codeToClass.put(BYE_CODE         , com.zy.im.core.command.Bye.class  );
//        
//        codeToClass.put(OK_CODE          , com.zy.im.core.status.Ok.class   );
//        codeToClass.put(JUMP_CODE        , com.zy.im.core.status.Jump.class );
//        codeToClass.put(ERROR_CODE       , com.zy.im.core.status.Error.class);
//        codeToClass.put(UNAUTHORIZED_CODE, com.zy.im.core.status.Unauthorized.class);
//        codeToClass.put(NOT_AUTHENTICATED_CODE,com.zy.im.core.status.NotAuthenticated.class);
//        codeToClass.put(FORBIDDEN_CODE   ,com.zy.im.core.status.Forbidden.class   );

//        codeToClass.put(INFORMATION_CODE   ,com.ivyinfo.im.demo.bean.TestMessage.class   );
//    }

    // ---------------------------------------------------------- Public methods
    /**
     * Gets the Class instance from the byte code.
     *
     * @param code the code in byte of the class instance
     * @return the Class
     */
//    public static Class getClassFromCode(String name) {
//        return (Class)codeToClass.get(name);
//    }

    /**
     * Gets the command code from the Class.
     *
     * @param classname the Class to find code
     * @return the command code
     */
//    public static int getCodeFromClass(Class classname) {
//        return Integer.parseInt("" + codeToClass.getKey(classname));
//    }
}
