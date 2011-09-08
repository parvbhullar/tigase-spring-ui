package com.mitian.airad.common.auth.context;

/**
 * 线程安全容器
 * 
 * @author zhoufengbo
 */
public class SecurityContextHolder {
    /**
     * <p>
     * ThreadLocal context holder
     * </p>
     */
    private static final ThreadLocal<SecurityContext> contextHolder = new ThreadLocal<SecurityContext>();

    /**
     * <p>
     * 获取Context
     * </p>
     * 
     * @return
     */
    public static SecurityContext getContext() {
        return contextHolder.get();
    }

    /**
     * <p>
     * 设置Context
     * </p>
     * 
     * @param securityContext
     */
    public static void setContext(SecurityContext securityContext) {
        contextHolder.set(securityContext);
    }

    /**
     * <p>
     * 清除Context
     * </p>
     */
    public static void clearContext() {
        contextHolder.set(null);
    }
}
