package com.ivyinfo.framework.service.servlet;

import java.util.HashMap;
import java.util.Map;



/**
 * @author Administrator
 *
 */
public class Test {   
    public static void main(String[] args) {   
        try {   
            HttpRequester request = new HttpRequester();   
            //String str = "<d></d><returnType>dXJs</returnType><action>d2RocQ==</action><value><name>bXpx</name><password>MTIzNDU2</password><deviceid>ZmptLTEyNzg1MDE5Mjc2NDg1NWVkNGU=</deviceid><machineid>MQ==</machineid>";
            //str = InfoToXML.base64Decode(str);
    
            //HttpRespons hr = request.sendPost("http://192.168.1.254:5580/integration/xml/",str);
            HttpRespons hr = request.sendPost("http://127.0.0.1:5080/wmTouch/","");
            //HttpRespons hr = request.sendPost("http://10.41.184.13:8080/webcall/indexforhk.jsp?key=O6Hif1m3&sessionid=D046321B1C6FC699DC779A91D66951C6","");
            System.out.println("111=="+hr.getUrlString());   
            System.out.println("211=="+hr.getProtocol());   
            System.out.println("311=="+hr.getHost());   
            System.out.println("411=="+hr.getPort());   
            System.out.println("511=="+hr.getContentEncoding());   
            System.out.println("611=="+hr.getMethod());   
            System.out.println("711=="+hr.getMessage());
               
            System.out.println("811=="+hr.getContent());   
    
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
    }   
} 
