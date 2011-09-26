package com.mitian.airad.common.auth.context;

/**
 * 线程安全容器
 * 
 * @author zhoufengbo
 */
public abstract class SecurityContextHolder {
    /**
     * <p>
     * ThreadLocal context holder
     * </p>
     */
    private static final ThreadLocal<SecurityContext> CONTEXT_HOLDER = new ThreadLocal<SecurityContext>();

    /**
     * <p>
     * 获取Context
     * </p>
     * 
     * @return
     */
    public static SecurityContext getContext() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * <p>
     * 设置Context
     * </p>
     * 
     * @param securityContext
     */
    public static void setContext(SecurityContext securityContext) {
        CONTEXT_HOLDER.set(securityContext);
    }

    /**
     * <p>
     * 清除Context
     * </p>
     */
    public static void clearContext() {
        CONTEXT_HOLDER.set(null);
    }
}
