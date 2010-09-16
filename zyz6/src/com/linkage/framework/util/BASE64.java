package com.linkage.framework.util;


public class BASE64 {
    public static void main(String[] args) {
    	//setBase64Key();
        String str = "0|王家乐|男|19870110|6281527@qq.com|15077855677|0516|1";
        String[] s = str.split("\\u007C");
        System.out.println("ss="+s[1]);
        System.out.println(str);
        str = utf16to8(str);
        System.out.println(str);
        str = BASE64.base64encode(str);
        System.out.println(str);
        str = BASE64.base64decode(str);
        System.out.println(str);
        str = utf8to16(str);
        System.out.println(str);
    }
    
	private static String base64encodechar;
	
	public String getBase64encodechar() {
		return base64encodechar;
	}

	public  void setBase64encodechar(String base64encodechar) {
		BASE64.base64encodechar = base64encodechar;
	} 

    private static String base64EncodeChars = "k24ToYdpMvBwL63bmC/jUHIxQ80EOaK51nSuyiNXlsAVrh7teRgqGF+z9fZDPJWc";
    private static byte[] base64DecodeChars = tranCode(base64EncodeChars);

    @SuppressWarnings("unused")
	private static String toGb(String uniStr) {
        String gbStr = "";
        if (uniStr == null) {
            uniStr = "";
        }
        try {
            byte[] tempByte = uniStr.getBytes("ISO8859_1");
            gbStr = new String(tempByte, "GB2312");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return gbStr;
    }

    private static String utf16to8(String str) {
        String out = "";
//        str = toGb(str);
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if ((c[i] >= 0x0001) && (c[i] <= 0x007F)) {
                out += (char) c[i];
            }
            else {
                if (c[i] > 0x07FF) {
                    out += (char) (0xE0 | ((c[i] >> 12) & 0x0F));
                    out += (char) (0x80 | ((c[i] >> 6) & 0x3F));
                    out += (char) (0x80 | ((c[i] >> 0) & 0x3F));
                }
                else {
                    out += (char) (0xC0 | ((c[i] >> 6) & 0x1F));
                    out += (char) (0x80 | ((c[i] >> 0) & 0x3F));
                }
            }
        }
        return out;
    }


    private static String utf8to16(String str) {
        String out = "";
        int i, len;
        char char1, char2, char3;
        char[] c = str.toCharArray();

        len = c.length;
        i = 0;
        while (i < len) {
            char1 = c[i++];
            switch (char1 >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    // 0xxxxxxx
                    out += (char) c[i - 1];
                    break;
                case 12:
                case 13:
                    // 110x xxxx   10xx xxxx
                    char2 = (char) c[i++];
                    out += (char) (((char1 & 0x1F) << 6) | (char2 & 0x3F));
                    break;
                case 14:
                    // 1110 xxxx  10xx xxxx  10xx xxxx
                    char2 = (char) c[i++];
                    char3 = (char) c[i++];
                    out += (char) (((char1 & 0x0F) << 12) |
                            ((char2 & 0x3F) << 6) |
                            ((char3 & 0x3F) << 0));
                    break;
            }
        }

        return out;
    }
    //加密
    private static String base64encode(String str) {
        String out;
        int i, len;
        int c1, c2, c3;
        char[] c = str.toCharArray();
        len = c.length;
        i = 0;
        out = "";
        while (i < len) {
            c1 = (int) c[i++] & 0xff;
            if (i == len) {
                out += base64EncodeChars.substring((c1 >> 2), (c1 >> 2) + 1);
                out += base64EncodeChars.substring(((c1 & 0x3) << 4), ((c1 & 0x3) << 4) + 1);
                out += "==";
                break;
            }
            c2 = (int) c[i++];
            if (i == len) {
                out += base64EncodeChars.substring((c1 >> 2), (c1 >> 2) + 1);
                out += base64EncodeChars.substring((((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4)), (((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4)) + 1);
                out += base64EncodeChars.substring(((c2 & 0xF) << 2), ((c2 & 0xF) << 2) + 1);
                out += "=";
                break;
            }
            c3 = (int) c[i++];
            out += base64EncodeChars.substring((c1 >> 2), (c1 >> 2) + 1);
            out += base64EncodeChars.substring((((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4)), (((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4)) + 1);
            out += base64EncodeChars.substring((((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6)), (((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6)) + 1);
            out += base64EncodeChars.substring((c3 & 0x3F), (c3 & 0x3F) + 1);
        }
        return out;
    }

    private static byte[] tranCode(String encode) {
        char[] encodes = encode.toCharArray();
        byte[] decodes = new byte[256];
        for (int i = 0; i < 255; i++)
            decodes[i] = -1;

        for (int j = 0; j < encodes.length; j++)
            decodes[encodes[j]] = (byte) j;

        return decodes;
    }
    //解密
    private static String base64decode(String str) {
        int c1, c2, c3, c4;
        int i, len;
        String out;
        char[] c = str.toCharArray();
        len = c.length;
        i = 0;
        out = "";
        while (i < len) {
            /* c1 */
            do {
                c1 = base64DecodeChars[c[i++] & 0xff];
            } while (i < len && c1 == -1);
            if (c1 == -1)
                break;

            /* c2 */
            do {
                c2 = base64DecodeChars[c[i++] & 0xff];
            } while (i < len && c2 == -1);
            if (c2 == -1)
                break;

            out += (char) ((c1 << 2) | ((c2 & 0x30) >> 4));

            /* c3 */
            do {
                c3 = c[i++] & 0xff;
                if (c3 == 61)
                    return out;
                c3 = base64DecodeChars[c3];
            } while (i < len && c3 == -1);
            if (c3 == -1)
                break;

            out += (char) (((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));

            /* c4 */
            do {
                c4 = c[i++] & 0xff;
                if (c4 == 61)
                    return out;
                c4 = base64DecodeChars[c4];
            } while (i < len && c4 == -1);
            if (c4 == -1)
                break;
            out += (char) (((c3 & 0x03) << 6) | c4);
        }
        return out;
    }

    public static String encode(String code){
        code = utf16to8(code);
        return BASE64.base64encode(code);
    }

    public static String decode(String code){
        code = BASE64.base64decode(code);
        return utf8to16(code);
    }
    public void test()
    {
    	base64EncodeChars ="";
    }
    
    public void setBase64Key(){
//    	Properties props=new Properties();
//    	try {
//    		InputStream in = BASE64.class.getResourceAsStream("./base64key.properties");
////            InputStream in = new FileInputStream("./base64key.properties");
//            props.load(in);
//     	   	in.close();
//     	   	base64EncodeChars = props.getProperty ("base64key.base64encodechar");
//     	   	System.out.println("base64EncodeChars=" + base64EncodeChars);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    	base64EncodeChars = base64encodechar;
    }


}