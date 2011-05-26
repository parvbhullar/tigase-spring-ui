package com.ivyinfo.mail.util;

import java.io.BufferedReader; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Encoder;

/**
 * 该类使用Socket连接到邮件服务器，
 * 并实现了向指定邮箱发送邮件及附件的功能。
 * 
 * @author Zhong Lizhi
 */
public class Mail {
    
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
     * 值为“true”则在发送邮件{@link Mail#send()}
     * 过程中会读取服务器端返回的消息，
     * 并在邮件发送完毕后将这些消息返回给用户。
     */
    private boolean isAllowReadSocketInfo = true;
    
    /**
     * 邮件服务器地址
     */
    private String host;
    
    /**
     * 发件人邮箱地址
     */
    private String from;
    
    /**
     * 收件人邮箱地址
     */
    private List<String> to;
    
    /**
     * 抄送地址
     */
    private List<String> cc;
    
    /**
     * 暗送地址
     */
    private List<String> bcc;
    
    /**
     * 邮件主题
     */
    private String subject;
    
    /**
     * 用户名
     */
    private String user;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * MIME邮件类型
     */
    private String contentType;
    
    /**
     * 用来绑定多个邮件单元{@link #partSet}
     * 的分隔标识，我们可以将邮件的正文及每一个附件都看作是一个邮件单元
     * 。
     */
    private String boundary;
    
    /**
     * 邮件单元分隔标识符，该属性将用来在邮件中作为分割各个邮件单元的标识
     * 。
     */
    private String boundaryNextPart;
    
    /**
     * 传输邮件所采用的编码
     */
    private String contentTransferEncoding;
    
    /**
     * 设置邮件正文所用的字符集
     */
    private String charset;
    
    /**
     * 内容描述
     */
    private String contentDisposition;
    
    /**
     * 邮件正文
     */
    private String content;
    
    /**
     * 发送邮件日期的显示格式
     */
    private String simpleDatePattern;
    
    /**
     * 附件的默认MIME类型
     */
    private String defaultAttachmentContentType;
    
    /**
     * 邮件单元的集合，用来存放正文单元和所有的附件单元。
     */
    private List<MailPart> partSet;
    
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
    }
    
    /**
     * 该类用来实例化一个正文单元或附件单元对象，他继承了
     * {@link Mail}
     * ，在这里制作这个子类主要是为了区别邮件单元对象和邮件服务对象
     * ，使程序易读一些。 这些邮件单元全部会放到partSet
     * 中，在发送邮件 {@link #send()}时, 程序会调用
     * {@link #getAllParts()}
     * 方法将所有的单元合并成一个符合MIME格式的字符串。
     * 
     * @author Zhong Lizhi
     */
    private class MailPart extends Mail {
        public MailPart() {
        }
    }
    
    /**
     * 默认构造函数
     */
    public Mail() {
        defaultAttachmentContentType = "application/octet-stream";
        simpleDatePattern = "yyyy-MM-dd HH:mm:ss";
        boundary = "--=_NextPart_zlz_3907_" + System.currentTimeMillis();
        boundaryNextPart = "--" + boundary;
        contentTransferEncoding = "base64";
        contentType = "multipart/alternative";
        charset = Charset.defaultCharset().name();
        partSet = new ArrayList<MailPart>();
        to = new ArrayList<String>();
        cc = new ArrayList<String>();
        bcc = new ArrayList<String>();
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
            ret = defaultAttachmentContentType;
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
        StringBuilder sbd = new StringBuilder(LINE_END);
        for (int i = partCount - 1; i >= 0; i--) {
            Mail attachment = partSet.get(i);
            String attachmentContent = attachment.getContent();
            if (null != attachmentContent && 0 < attachmentContent.length()) {
                sbd.append(getBoundaryNextPart()).append(LINE_END);
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
    private void addContent() {
        if (null != content) {
            MailPart part = new MailPart();
            part.setContent(toBase64(content));
            part.setContentType("text/plain;charset=\"" + charset + "\"");
            partSet.add(part);
        }
    }
    
    private String listToMailString(List<String> mailAddressList) {
        StringBuilder sbd = new StringBuilder();
        if (null != mailAddressList) {
            int listSize = mailAddressList.size();
            for (int i = 0; i < listSize; i++) {
                if (0 != i) {
                    sbd.append(";");
                }
                sbd.append("<").append(mailAddressList.get(i)).append(">");
            }
        }
        return sbd.toString();
    }
    
    private List<String> getrecipient() {
        List<String> list = new ArrayList<String>();
        list.addAll(to);
        if(cc.size() != 0){
        	list.addAll(cc);
        }
        if(bcc.size() != 0){
        	list.addAll(bcc);
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
    
    public void addTo(String mailAddress) {
        this.to.add(mailAddress);
    }
    
    public void addCc(String mailAddress) {
        this.cc.add(mailAddress);
    }
    
    public void addBcc(String mailAddress) {
        this.bcc.add(mailAddress);
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
                charset = null != charset ? charset : Charset.defaultCharset()
                        .name();
                String contentType = getPartContentType(fileName)
                        + ";name=\"=?" + charset + "?B?" + toBase64(fileName)
                        + "?=\"";
                attachmentPart.setCharset(charset);
                attachmentPart.setContentType(contentType);
                attachmentPart.setContentDisposition("attachment;filename=\"=?"
                        + charset + "?B?" + toBase64(fileName) + "?=\"");
                attachmentPart.setContent(toBase64(bs));
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
    public String send() {
        
        // 对象申明
        // 当邮件发送完毕后，以下三个对象（Socket、
        // PrintWriter,
        // BufferedReader）需要关闭。
        Socket socket = null;
        PrintWriter pw = null;
        BufferedReader br = null;
        
        try {
            socket = new Socket(host, 25);
            pw = new PrintWriter(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            
            StringBuilder infoBuilder = new StringBuilder(
                    "\nServer info: \n------------\n");
            
            // 与服务器建立连接
            pw.write("HELO ".concat(host).concat(LINE_END)); // 连接到邮件服务
            if (!readResponse(pw, br, infoBuilder, "220"))
                return infoBuilder.toString();
            
            pw.write("AUTH LOGIN".concat(LINE_END)); // 登录
            if (!readResponse(pw, br, infoBuilder, "250"))
                return infoBuilder.toString();
            
            pw.write(toBase64(user).concat(LINE_END)); // 输入用户名
            if (!readResponse(pw, br, infoBuilder, "334"))
                return infoBuilder.toString();
            
            pw.write(toBase64(password).concat(LINE_END)); // 输入密码
            if (!readResponse(pw, br, infoBuilder, "334"))
                return infoBuilder.toString();
            
            pw.write("MAIL FROM:<" + from + ">" + LINE_END); // 发件人邮箱地址
            if (!readResponse(pw, br, infoBuilder, "235"))
                return infoBuilder.toString();
            
            List<String> recipientList = getrecipient();
            // 收件邮箱地址
            for (int i = 0; i < recipientList.size(); i++) {
                pw.write("RCPT TO:<" + recipientList.get(i) + ">" + LINE_END);
                if (!readResponse(pw, br, infoBuilder, "250"))
                    return infoBuilder.toString();
            }
            // System.out.println(
            // getAllSendAddress());
            
            pw.write("DATA" + LINE_END); // 开始输入邮件
            if (!readResponse(pw, br, infoBuilder, "250"))
                return infoBuilder.toString();
            
            flush(pw);
            
            // 设置邮件头信息
            StringBuffer sbf = new StringBuffer("From: <" + from + ">"
                    + LINE_END); // 发件人
            sbf.append("To: " + listToMailString(to) + LINE_END);// 收件人
            sbf.append("Cc: " + listToMailString(cc) + LINE_END);// 收件人
            sbf.append("Bcc: " + listToMailString(bcc) + LINE_END);// 收件人
            sbf.append("Subject: " + subject + LINE_END);// 邮件主题
            SimpleDateFormat sdf = new SimpleDateFormat(simpleDatePattern);
            sbf.append("Date: ").append(sdf.format(new Date()));
            sbf.append(LINE_END); // 发送时间
            sbf.append("Content-Type: ");
            sbf.append(contentType);
            sbf.append(";");
            sbf.append("boundary=\"");
            sbf.append(boundary).append("\""); // 邮件类型设置
            sbf.append(LINE_END);
            sbf.append("This is a multi-part message in MIME format.");
            sbf.append(LINE_END);
            
            // 添加邮件正文单元
            addContent();
            
            // 合并所有单元，正文和附件。
            sbf.append(getAllParts());
            
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
    
    public String getBoundaryNextPart() {
        return boundaryNextPart;
    }
    
    public void setBoundaryNextPart(String boundaryNextPart) {
        this.boundaryNextPart = boundaryNextPart;
    }
    
    public String getDefaultAttachmentContentType() {
        return defaultAttachmentContentType;
    }
    
    public void setDefaultAttachmentContentType(
            String defaultAttachmentContentType) {
        this.defaultAttachmentContentType = defaultAttachmentContentType;
    }
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public String getFrom() {
        return from;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }
    
    public List<String> getTo() {
        return to;
    }
    
    public void setTo(List<String> to) {
        this.to = to;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getUser() {
        return user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getContentType() {
        return contentType;
    }
    
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    public String getBoundary() {
        return boundary;
    }
    
    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }
    
    public String getContentTransferEncoding() {
        return contentTransferEncoding;
    }
    
    public void setContentTransferEncoding(String contentTransferEncoding) {
        this.contentTransferEncoding = contentTransferEncoding;
    }
    
    public String getCharset() {
        return charset;
    }
    
    public void setCharset(String charset) {
        this.charset = charset;
    }
    
    public String getContentDisposition() {
        return contentDisposition;
    }
    
    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }
    
    public String getSimpleDatePattern() {
        return simpleDatePattern;
    }
    
    public void setSimpleDatePattern(String simpleDatePattern) {
        this.simpleDatePattern = simpleDatePattern;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public boolean isAllowReadSocketInfo() {
        return isAllowReadSocketInfo;
    }
    
    public void setAllowReadSocketInfo(boolean isAllowReadSocketInfo) {
        this.isAllowReadSocketInfo = isAllowReadSocketInfo;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        // 应用示例：线程化发送邮件
        new Thread() {
            @Override
            public void run() {
                System.out.println("SENDER-" + this.getId() + ":/>"
                        + "开始发送邮件...");
                
                // 创建邮件对象
                Mail mail = new Mail();
                mail.setHost("smtp.test.com"); // 邮件服务器地址
                mail.setFrom("sender@m.com"); // 发件人邮箱
                mail.addTo("recipient@m.com"); // 收件人邮箱
                mail.addCc("test@m1.com");
                mail.addBcc("test@m2.com");
                mail.setSubject("CC Test Mail!!"); // 邮件主题
                mail.setUser("123"); // 用户名
                mail.setPassword("123"); // 密码
                mail.setContent("这是一个测试，请不要回复！"); // 邮件正文
                
                mail.addAttachment("utf8中.txt"); // 添加附件
                // mail.addAttachment(
                // "e:/test.htm"); //
                // 添加附件
                
                System.out.println(mail.send()); // 发送
                
                System.out.println("SENDER-" + this.getId() + ":/>"
                        + "邮件已发送完毕！");
            }
        }.start();
        
    }
    
}