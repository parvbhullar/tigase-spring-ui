package com.ivyinfo.mail.util;

import java.io.BufferedReader; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.ivyinfo.mail.bean.MailUtilBean;

import sun.misc.BASE64Encoder;

/**
 * 该类使用Socket连接到邮件服务器，
 * 并实现了向指定邮箱发送邮件及附件的功能。
 * 
 * @author Zhong Lizhi
 */
public class MailUtil {
    
    /**
     * 换行符
     */
    private static final String LINE_END = "\r\n";
    
    /**
     * 值为“true”输出高度信息（包括服务器响应信息），值为“
     * false”则不输出调试信息。
     */
    private boolean isDebug = true;
    
    /**
     * 值为“true”则在发送邮件{@link MailUtil#send()}
     * 过程中会读取服务器端返回的消息，
     * 并在邮件发送完毕后将这些消息返回给用户。
     */
    private boolean isAllowReadSocketInfo = true;
    
    /**
     * 邮件单元的集合，用来存放正文单元和所有的附件单元。
     */
    private List<MailPart> partSet;
    
    private class MailPart extends MailUtilBean{
    	public MailPart() {
        }
    }
    
    /**
     * 不同类型文件对应的{@link MIME} 类型映射。在添加附件
     * {@link #addAttachment(String)}
     * 时，程序会在这个映射中查找对应文件的 {@link MIME}
     * 类型，如果没有， 则使用
     * {@link #defaultAttachmentContentType}
     * 所定义的类型。
     */
    private static Map<String, String> contentTypeMap;
    
    static {
        // MIME Media Types
        contentTypeMap = new HashMap<String, String>();
        contentTypeMap.put("xls", "application/vnd.ms-excel");
        contentTypeMap.put("xlsx", "application/vnd.ms-excel");
        contentTypeMap.put("xlsm", "application/vnd.ms-excel");
        contentTypeMap.put("xlsb", "application/vnd.ms-excel");
        contentTypeMap.put("doc", "application/msword");
        contentTypeMap.put("dot", "application/msword");
        contentTypeMap.put("docx", "application/msword");
        contentTypeMap.put("docm", "application/msword");
        contentTypeMap.put("dotm", "application/msword");
//        contentTypeMap.put("txt", "text/plain");
    }
    
    private MailUtilBean muBean = new MailUtilBean();
    
    /**
     * 默认构造函数
     */
    public MailUtil() {
    	muBean.setDefaultAttachmentContentType("application/octet-stream");
    	muBean.setSimpleDatePattern("yyyy-MM-dd HH:mm:ss");
    	muBean.setBoundary("--=_NextPart_zlz_3907_" + System.currentTimeMillis());
    	muBean.setBoundaryNextPart("--" + muBean.getBoundary());
    	muBean.setContentTransferEncoding("base64");
    	muBean.setContentType("multipart/alternative");
    	muBean.setCharset("UTF-8");
    	muBean.setGbkcharset("GBK");
    	partSet = new ArrayList<MailPart>();
    }
    
    /**
     * 根据指定的完整文件名在
     * {@link #contentTypeMap}
     * 中查找其相应的MIME类型， 如果没找到，则返回
     * {@link #defaultAttachmentContentType}
     * 所指定的默认类型。
     * 
     * @param fileName
     *            文件名
     * @return 返回文件对应的MIME类型。
     */
    private String getPartContentType(String fileName) {
        String ret = null;
        if (null != fileName) {
            int flag = fileName.lastIndexOf(".");
            if (0 <= flag && flag < fileName.length() - 1) {
                fileName = fileName.substring(flag + 1);
            }
            ret = contentTypeMap.get(fileName);
        }
        
        if (null == ret) {
            ret = muBean.getDefaultAttachmentContentType();
        }
        return ret;
    }
    
    /**
     * 将给定字符串转换为base64编码的字符串
     * 
     * @param str
     *            需要转码的字符串
     * @param charset
     *            原字符串的编码格式
     * @return base64编码格式的字符
     */
    private String toBase64(String str, String charset) {
        if (null != str) {
            try {
                return toBase64(str.getBytes(charset));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
    
    /**
     * 将指定的字节数组转换为base64格式的字符串
     * 
     * @param bs
     *            需要转码的字节数组
     * @return base64编码格式的字符
     */
    private String toBase64(byte[] bs) {
        return new BASE64Encoder().encode(bs);
    }
    
    /**
     * 将给定字符串转换为base64编码的字符串
     * 
     * @param str
     *            需要转码的字符串
     * @return base64编码格式的字符
     */
    private String toBase64(String str) {
        return toBase64(str, Charset.defaultCharset().name());
    }
    
    /**
     * 将所有的邮件单元按照标准的MIME格式要求合并。
     * 
     * @return 返回一个所有单元合并后的字符串。
     */
    private String getAllParts() {
        int partCount = partSet.size();
        System.err.println("partCount:"+partCount);
        StringBuilder sbd = new StringBuilder(LINE_END);
        for (int i = partCount - 1; i >= 0; i--) {
            MailUtilBean attachment = partSet.get(i);
            String attachmentContent = attachment.getContent();
            if (null != attachmentContent && 0 < attachmentContent.length()) {
                sbd.append(muBean.getBoundaryNextPart()).append(LINE_END);
                sbd.append("Content-Type: ");
                sbd.append(attachment.getContentType());
                sbd.append(LINE_END);
                sbd.append("Content-Transfer-Encoding: ");
                sbd.append(attachment.getContentTransferEncoding());
                sbd.append(LINE_END);
                if (i != partCount - 1) {
                    sbd.append("Content-Disposition: ");
                    sbd.append(attachment.getContentDisposition());
                    sbd.append(LINE_END);
                }
                
                sbd.append(LINE_END);
                sbd.append(attachment.getContent());
                sbd.append(LINE_END);
            }
        }
        
        sbd.append(LINE_END);
        sbd.append(LINE_END);
        // sbd.append(boundaryNextPart).
        // append(LINE_END);
        partSet.clear();
        return sbd.toString();
    }
    
    /**
     * 添加邮件正文单元
     */
    private void addContent(MailUtilBean mail) {
        if (null != mail.getContent()) {
            MailPart part = new MailPart();
//            part.setContent(mail.getContent());
            part.setContent(toBase64(mail.getContent()));
            part.setContentType("text/plain;charset=\"" + muBean.getCharset() + "\"");
            part.setContentTransferEncoding("base64");
            partSet.add(part);
        }
    }
    
    private String listToMailString(List<String> mailAddressList) {
        StringBuilder sbd = new StringBuilder();
        if (null != mailAddressList) {
            int listSize = mailAddressList.size();
            for (int i = 0; i < listSize; i++) {
                if (0 != i) {
                    sbd.append(",");
                }
                sbd
                .append("<")
                .append(mailAddressList.get(i))
                .append(">");
            }
        }
        return sbd.toString();
    }
    
    private List<String> getrecipient() {
        List<String> list = new ArrayList<String>();
        list.addAll(muBean.getTo());
        
        if(muBean.getCc() != null){
	        if(muBean.getCc().size() != 0){
	        	list.addAll(muBean.getCc());
	        }
        }
        
        if(muBean.getBcc() != null){
	        if(muBean.getBcc().size() != 0){
	        	list.addAll(muBean.getBcc());
	        }
        }
        return list;
    }
    
    /**
     * 添加一个附件单元
     * 
     * @param filePath
     *            文件路径
     */
    public void addAttachment(String filePath) {
        addAttachment(filePath, null);
    }
    
    public void addTo(List mailAddress) {
    	muBean.setTo(mailAddress);
    }
    
    public void addCc(List mailAddress) {
    	muBean.setCc(mailAddress);
    }
    
    public void addBcc(List mailAddress) {
    	muBean.setBcc(mailAddress);
    }
    
    /**
     * 添加一个附件单元
     * 
     * @param filePath
     *            文件路径
     * @param charset
     *            文件编码格式
     */
    public void addAttachment(String filePath, String charset) {
        if (null != filePath && filePath.length() > 0) {
            File file = new File(filePath);
            try {
                addAttachment(file.getName(), new FileInputStream(file),
                        charset);
            } catch (FileNotFoundException e) {
                System.out.println("错误：" + e.getMessage());
                System.exit(1);
            }
        }
    }
    
    /**
     * 添加一个附件单元
     * 
     * @param fileName
     *            文件名
     * @param attachmentStream
     *            文件流
     * @param charset
     *            文件编码格式
     */
    public void addAttachment(String fileName, InputStream attachmentStream,
            String charset) {
        try {
            byte[] bs = null;
            if (null != attachmentStream) {
                int buffSize = 1024;
                byte[] buff = new byte[buffSize];
                byte[] temp;
                bs = new byte[0];
                int readTotal = 0;
                while (-1 != (readTotal = attachmentStream.read(buff))) {
                    temp = new byte[bs.length];
                    System.arraycopy(bs, 0, temp, 0, bs.length);
                    bs = new byte[temp.length + readTotal];
                    System.arraycopy(temp, 0, bs, 0, temp.length);
                    System.arraycopy(buff, 0, bs, temp.length, readTotal);
                }
            }
            
            if (null != bs) {
                MailPart attachmentPart = new MailPart();
                
//                charset = null != charset ? charset : Charset.defaultCharset()
//                        .name();
                charset = muBean.getCharset();
                String gbkcharset = muBean.getGbkcharset();
//                String contentType = getPartContentType(fileName)
//                        + ";name=\"=?" + charset + "?B?" + toBase64(fileName)
//                        + "?=\"";
                String contentType = getPartContentType(fileName)
		                + ";name=\"=?" + gbkcharset + "?Q?" + toBase64(fileName)
		                + "?=\"";
                attachmentPart.setCharset(charset);
                attachmentPart.setContentType(contentType);
                attachmentPart.setContentDisposition("attachment;filename=\"=?"
                        + charset + "?B?" + toBase64(fileName) + "?=\"");
//                attachmentPart.setContentDisposition("attachment;filename=\"=?GBK?Q?" + toBase64(fileName) + "?=\"");
//                attachmentPart.setContent("=?" + charset + "?B?" + toBase64(bs) + "?=");
                attachmentPart.setContent(toBase64(new String(bs,gbkcharset)));
                attachmentPart.setContentTransferEncoding("base64");
                partSet.add(attachmentPart);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != attachmentStream) {
                try {
                    attachmentStream.close();
                    attachmentStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            Runtime.getRuntime().gc();
            Runtime.getRuntime().runFinalization();
        }
    }
    
    /**
     * 发送邮件
     * 
     * @return 邮件服务器反回的信息
     */
    public String send(MailUtilBean mail) {
        
        // 对象申明
        // 当邮件发送完毕后，以下三个对象（Socket、
        // PrintWriter,
        // BufferedReader）需要关闭。
        Socket socket = null;
        PrintWriter pw = null;
        BufferedReader br = null;
        
        try {
            socket = new Socket(mail.getHost(), mail.getPort());
            pw = new PrintWriter(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            
            StringBuilder infoBuilder = new StringBuilder(
                    "\nServer info: \n------------\n");
            
            
            // 与服务器建立连接
            pw.write("HELO ".concat(mail.getHost()).concat(LINE_END)); // 连接到邮件服务
            if (!readResponse(pw, br, infoBuilder, "220"))
                return infoBuilder.toString();
            
            pw.write("AUTH LOGIN".concat(LINE_END)); // 登录
            if (!readResponse(pw, br, infoBuilder, "250"))
                return infoBuilder.toString();
            
            pw.write(toBase64(mail.getUser()).concat(LINE_END)); // 输入用户名
            if (!readResponse(pw, br, infoBuilder, "334"))
                return infoBuilder.toString();
            
            pw.write(toBase64(mail.getPassword()).concat(LINE_END)); // 输入密码
            if (!readResponse(pw, br, infoBuilder, "334"))
                return infoBuilder.toString();
            
            pw.write("MAIL FROM:<" + mail.getFrom() + ">" + LINE_END); // 发件人邮箱地址
            
            if (!readResponse(pw, br, infoBuilder, "235"))
                return infoBuilder.toString();
            
            List<String> recipientList = getrecipient();
            // 收件邮箱地址
            for (int i = 0; i < recipientList.size(); i++) {
                pw.write("RCPT TO:<" + recipientList.get(i) + ">" + LINE_END);
//                if (!readResponse(pw, br, infoBuilder, "250"))
//                	System.err.println("5555555555555555");
//                    return infoBuilder.toString();
            }
            // System.out.println(
            // getAllSendAddress());
            
            pw.write("DATA" + LINE_END); // 开始输入邮件
            if (!readResponse(pw, br, infoBuilder, "250"))
                return infoBuilder.toString();
            
            flush(pw);
            
            // 设置邮件头信息
            StringBuffer sbf = new StringBuffer("From: <" + mail.getFrom() + ">"
                    + LINE_END); // 发件人
            sbf.append("To: " + listToMailString(muBean.getTo()) + LINE_END);// 收件人
            sbf.append("Cc: " + listToMailString(muBean.getCc()) + LINE_END);// 发件人
            sbf.append("Bcc: " + listToMailString(muBean.getBcc()) + LINE_END);// 密送人
            String sub = ("=?"+muBean.getCharset()+"?B?"+toBase64(mail.getSubject())+"?=");
            sbf.append("Subject: " + sub + LINE_END);// 邮件主题
            SimpleDateFormat sdf = new SimpleDateFormat(muBean.getSimpleDatePattern());
//            String date = sdf.format(new Date());
//            String datetime = ("=?"+muBean.getCharset()+"?B?"+toBase64(date)+"?=");
//            sbf.append("Date: ").append(datetime);
//            sbf.append(LINE_END); // 发送时间
            sbf.append("Content-Type: ");
            sbf.append(muBean.getContentType());
            sbf.append(";");
            sbf.append("boundary=\"");
            sbf.append(muBean.getBoundary()).append("\""); // 邮件类型设置
            sbf.append(LINE_END);
            sbf.append("This is a multi-part message in MIME format.");
            sbf.append(LINE_END);
            
            // 添加邮件正文单元
            addContent(mail);
            
            // 合并所有单元，正文和附件。
            sbf.append(getAllParts());
            System.err.println("sbf.toString():"+sbf.toString());
            
            // 发送
            sbf.append(LINE_END).append(".").append(LINE_END);
            pw.write(sbf.toString());
            readResponse(pw, br, infoBuilder, "354");
            flush(pw);
            
            // QUIT退出
            pw.write("QUIT" + LINE_END);
            if (!readResponse(pw, br, infoBuilder, "250"))
                return infoBuilder.toString();
            flush(pw);
            
            return infoBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception:>" + e.getMessage();
        } finally {
            // 释放资源
            try {
                if (null != socket)
                    socket.close();
                if (null != pw)
                    pw.close();
                if (null != br)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            // this.to.clear();
            // this.cc.clear();
            // this.bcc.clear();
            this.partSet.clear();
        }
        
    }
    
    /**
     * 将SMTP命令发送到邮件服务器
     * 
     * @param pw
     *            邮件服务器输入流
     */
    private void flush(PrintWriter pw) {
        if (!isAllowReadSocketInfo) {
            pw.flush();
        }
    }
    
    /**
     * 读取邮件服务器的响应信息
     * 
     * @param pw
     *            邮件服务器输入流
     * @param br
     *            邮件服务器输出流
     * @param infoBuilder
     *            用来存放服务器响应信息的字符串缓冲
     * @param msgCode
     * @return
     * @throws IOException
     */
    private boolean readResponse(PrintWriter pw, BufferedReader br,
            StringBuilder infoBuilder, String msgCode) throws IOException {
        if (isAllowReadSocketInfo) {
            pw.flush();
            String message = br.readLine();
            infoBuilder.append("SERVER:/>");
            infoBuilder.append(message).append(LINE_END);
            if (null == message || 0 > message.indexOf(msgCode)) {
                System.out.println("ERROR: " + message);
                pw.write("QUIT".concat(LINE_END));
                pw.flush();
                return false;
            }
            if (isDebug) {
                System.out.println("DEBUG:/>" + msgCode + "/" + message);
            }
        }
        return true;
    }
    
    
    
    public boolean isAllowReadSocketInfo() {
        return isAllowReadSocketInfo;
    }
    
    public void setAllowReadSocketInfo(boolean isAllowReadSocketInfo) {
        this.isAllowReadSocketInfo = isAllowReadSocketInfo;
    }
    
}
