package com.ivyinfo.framework.common.encryption;

/**
 * Class to convert byte(s) into hexadecimal String
 */
public class Hex {
    
    public static String bytesToHex(byte [] b) {
        StringBuffer buf = new StringBuffer("");
        for (int i=0; i< b.length;i++)
            buf.append(byteToHex(b[i]));
        return buf.toString();
    }
    
    public static String byteToHex(byte  b) {
        // Returns hex String representation of byte b
        char hexDigit[] = {
            '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
        return new String(array);
    }
    
}