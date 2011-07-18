package com.ivyinfo.mail.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.ivyinfo.mail.util.MailUtil;

public class MailUtilBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684231L;
	/**
     * 邮件服务器地址
     */
    private String host;
    
    /**
     * 邮件服务器端口号
     */
    private int port;
    
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
     * 设置邮件正文所用的gbk字符集
     */
    private String gbkcharset;
    
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
    
    private String toname;
    private String ccname;
    private String bccname;
    private String fileaddress;
    
    
    

    
	public String getGbkcharset() {
		return gbkcharset;
	}

	public void setGbkcharset(String gbkcharset) {
		this.gbkcharset = gbkcharset;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getToname() {
		return toname;
	}

	public void setToname(String toname) {
		this.toname = toname;
	}

	public String getCcname() {
		return ccname;
	}

	public void setCcname(String ccname) {
		this.ccname = ccname;
	}

	public String getBccname() {
		return bccname;
	}

	public void setBccname(String bccname) {
		this.bccname = bccname;
	}

	public String getFileaddress() {
		return fileaddress;
	}

	public void setFileaddress(String fileaddress) {
		this.fileaddress = fileaddress;
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

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public List<String> getBcc() {
		return bcc;
	}

	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}
    
}
