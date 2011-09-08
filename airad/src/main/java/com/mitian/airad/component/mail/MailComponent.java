/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.component.mail;

import java.io.IOException;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.mitian.airad.common.utils.CollectionUtils;

/**
 * MailComponent.java
 * 
 * @author baojun
 */
@Service
public class MailComponent {
    /**
     * @return the mailSender
     */
    public JavaMailSender getMailSender() {
        return mailSender;
    }

    /**
     * @param mailSender the mailSender to set
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @return the taskExecutor
     */
    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    /**
     * @param taskExecutor the taskExecutor to set
     */
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    private Logger logger = Logger.getLogger("mail_log");
    @Autowired
    @Qualifier("mailSender")
    private JavaMailSender mailSender;// 注入Spring封装的javamail，Spring的ml中已让框架装配
    @Autowired
    @Qualifier("taskExecutor")
    private TaskExecutor taskExecutor;// 注入Spring封装的异步执行器private Log log = LogFactory.getLog(getClass());

    public void sendMail(List<Email> emails) throws IOException {
        if (CollectionUtils.isEmpty(emails)) {
            return;
        }
        if (emails.size() > 5) {// 收件人大于5封时，采用异步发送
            sendMailByAsynchronousMode(emails);
        }
        else {
            sendMailBySynchronizationMode(emails);
        }
    }

    /**
     * 异步发送
     * 
     * @see com.zhangjihao.service.MailService#sendMailByAsynchronousMode(com.zhangjihao.bean.Email)
     */
    public void sendMailByAsynchronousMode(final List<Email> emails) {
        taskExecutor.execute(new Runnable() {
            public void run() {
                try {
                    sendMailBySynchronizationMode(emails);
                }
                catch (Exception e) {
                    logger.info("send AsynMail error ", e);
                }
            }
        });
    }

    /**
     * 单封邮件异步发送
     * 
     * @param email
     */
    public void sendSingleMailByAsynchronousMode(final Email email) {
        taskExecutor.execute(new Runnable() {
            public void run() {
                try {
                    sendSingleMail(email);
                }
                catch (Exception e) {
                    logger.error("sendSingleMailByAsynchronousMode error ", e);
                }
            }
        });
    }

    /**
     * 单封邮件同步发送
     * 
     * @param email
     */
    public void sendSingleMail(final Email email) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");
                message.setSubject(email.getSubject());
                message.setSentDate(email.getCreateTime());
                message.setFrom(email.getFromAddress(), email.getPersonal());
                message.setTo(email.getToAddress());
                // MimeUtility
                message.setText(email.getContent(), email.isHtml());
            }
        };
        mailSender.send(preparator);
    }

    /**
     * 批量发送
     * 
     * @param email
     */
    public void batchSendMail(final Email email) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");
                message.setSubject(email.getSubject());
                message.setSentDate(email.getCreateTime());
                message.setFrom(email.getFromAddress(), email.getPersonal());
                message.setTo(email.getToAddresses());
                // MimeUtility
                message.setText(email.getContent(), email.isHtml());
            }
        };
        mailSender.send(preparator);
    }

    /**
     * 同步发送
     * 
     * @throws IOException
     * @see com.zhangjihao.service.MailServiceMode#sendMail(com.zhangjihao.bean.Email)
     */
    public void sendMailBySynchronizationMode(List<Email> emails) throws IOException {
        for (final Email email : emails) {
            try {
                sendSingleMail(email);
            }
            catch (Exception e) {
                logger.error("sendMailBySynchronizationMode error,mail info" + email.toLogString(), e);
            }
        }
    }
}
