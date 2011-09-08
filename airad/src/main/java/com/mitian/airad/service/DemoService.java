package com.mitian.airad.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.mitian.airad.web.form.User;

@Service
public class DemoService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;

    public List<Map<String, String>> selectAllUser() {
        List<Map<String, String>> users = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 10; i++) {
            Map<String, String> user = new HashMap<String, String>();
            user.put("name", "demo" + i);
            user.put("age", "2" + i);
            user.put("email", "demo" + i + "@gmail.com");
            users.add(user);
        }
        return users;
    }

    /**
     * 在添加，修改，删除的时候，需要进行事物处理，service方法的名称必须以tx开头
     * 
     * @param user
     * @return
     */
    public Map<String, Object> txCreateUser(User user) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        return result;
    }

    public void register() {
        // Do the registration logic...
        // sendConfirmationEmail(user);
    }

    public String getVmPage() {
        Map model = new HashMap();
        // model.put("user", user);
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "registration.vm", model);
        return text;
    }

    private void sendConfirmationEmail() {
    }
}
