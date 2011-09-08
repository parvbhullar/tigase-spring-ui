package com.mitian.airad.common.auth.context;

/**
 * 操作环境
 * 
 * @author zhoufengbo
 * @since 2010-09-19
 * @version 1.0
 */
public class OperationEnvironment {
    /**
     * <p>
     * 客户端IP
     * </p>
     */
    private String clientIp;

    /**
     * <p>
     * serverName
     * </p>
     */
    private String serverName;

    /**
     * <p>
     * 访问URL
     * </p>
     */
    private String targetUrl;

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
}
