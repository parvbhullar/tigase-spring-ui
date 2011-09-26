package com.mitian.airad.common.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.core.io.Resource;

/**
 * 解析配置邮件模板的xml文件
 * 
 * @author yuanchao
 */
@SuppressWarnings("unchecked")
public class JDomParseUtils {

    private static Log log = LogFactory.getLog(JDomParseUtils.class);
    private Document docMail;
    private Document docSms;
    private Resource smsTemplate;
    private Resource mailTemplate;

    public void initLoadXml() throws JDOMException, IOException {

        // 使用的是默认的解析器
        SAXBuilder builder = new SAXBuilder(false);
        // 得到Document
        try {
            log.info("begin to loading xml !");
            docMail = builder.build(mailTemplate.getInputStream());
            docSms = builder.build(smsTemplate.getInputStream());
            log.info("loading  xml successful !");
        }
        catch (JDOMException e) {
            log.error("loading  xml fail !", e);
            throw e;
        }
        catch (IOException e) {
            log.error("loading  xml fail !", e);
            throw e;
        }
    }

    public Map<String, Object> parseXmlMail(String velocityId) {

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 得到根元素
            Element velocities = docMail.getRootElement();
            // 得到元素（节点）的集合
            List listVelocity = velocities.getChildren("velocity");

            for (Iterator it = listVelocity.iterator(); it.hasNext();) {
                Element velocity = (Element) it.next();
                // 取得元素velocity的属性名为“id”的属性值。
                String id = velocity.getAttributeValue("id");
                // 判断是否是当前使用的模板
                if (StringUtils.trimToEmpty(id).equals(velocityId)) {
                    // 取得当前模板的路径
                    String location = velocity.getAttributeValue("location");
                    map.put("location", location);
                    // 取得邮件的主题
                    String subject = velocity.getChildText("subject");
                    map.put("subject", subject);
                    String senderAddress = velocity.getChildText("senderAddress");
                    map.put("senderAddress", senderAddress);
                    String senderDispaly = velocity.getChildText("senderDispaly");
                    map.put("senderDispaly", senderDispaly);
                    String replyTo = velocity.getChildText("replyTo");
                    map.put("replyTo", replyTo);

                    // 取得代表邮件中的内嵌资源的元素
                    Element resourceInline = velocity.getChild("resourceInline");
                    if (resourceInline != null) {
                        // 取得内嵌资源的元素集合
                        List resources = resourceInline.getChildren("resource");
                        Map<String, String> map1 = new HashMap<String, String>();
                        for (Iterator itr = resources.iterator(); itr.hasNext();) {
                            Element resource = (Element) itr.next();
                            // 取出内嵌资源的key
                            String resourceKey = resource.getAttributeValue("key");
                            // 取出内嵌资源对应的路径
                            String keyPath = resource.getAttributeValue("keyPath");

                            map1.put(resourceKey, keyPath);
                        }
                        map.put("inLineResource", map1);
                    }
                    // 取得代表邮件中的附件的元素
                    Element mailAttaches = velocity.getChild("mailattach");
                    if (mailAttaches != null) {
                        // 取得附件的元素集合
                        List attaches = mailAttaches.getChildren("attach");
                        Map<String, String> map2 = new HashMap<String, String>();
                        for (Iterator ita = attaches.iterator(); ita.hasNext();) {
                            Element attach = (Element) ita.next();
                            String fileName = attach.getAttributeValue("fileName");
                            String filePath = attach.getAttributeValue("filePath");
                            map2.put(fileName, filePath);
                        }
                        map.put("attach", map2);
                    }

                }
            }
            return map;
        }
        catch (Exception e) {
            e.printStackTrace();
            return map;
        }
    }

    public Map<String, Object> parseSmsXml(String velocityId) {
        Map<String, Object> map = new HashMap<String, Object>();

        // 得到根元素
        Element velocities = docSms.getRootElement();
        // 得到元素（节点）的集合
        List listVelocity = velocities.getChildren("velocity");
        for (Iterator it = listVelocity.iterator(); it.hasNext();) {
            Element velocity = (Element) it.next();
            // 取得元素velocity的属性名为“id”的属性值。
            String id = velocity.getAttributeValue("id");
            // 判断是否是当前使用的模板
            if (StringUtils.trimToEmpty(id).equals(velocityId)) {
                // 取得当前模板的路径
                String location = velocity.getAttributeValue("location");
                map.put("location", location);
                // 取得代表短信发送时间的元素
                Element sendTimeElement = velocity.getChild("sendTime");
                if (sendTimeElement != null) {
                    String sendTime = sendTimeElement.getText();
                    map.put("sendTime", sendTime);
                }
                // 取得代表短信优先级的元素
                Element smsPriorityElement = velocity.getChild("smsPriority");
                if (smsPriorityElement != null) {
                    String priority = smsPriorityElement.getText();
                    map.put("priority", priority);
                }
            }
        }

        return map;
    }

    public Resource getSmsTemplate() {
        return smsTemplate;
    }

    public void setSmsTemplate(Resource smsTemplate) {
        this.smsTemplate = smsTemplate;
    }

    public Resource getMailTemplate() {
        return mailTemplate;
    }

    public void setMailTemplate(Resource mailTemplate) {
        this.mailTemplate = mailTemplate;
    }

}
