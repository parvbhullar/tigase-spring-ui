package com.mitian.boss.web.httpinvoker;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext ctx =
                new FileSystemXmlApplicationContext(
                        "D:/workspace/airad_05sv/maven.1312350610515/src/main/webapp/WEB-INF/config/remoting-client.xml");
        ValidateServer s = (ValidateServer) ctx.getBean("validate");
        String result = s.validate("2", "539");
        System.out.println(result);
    }
}
