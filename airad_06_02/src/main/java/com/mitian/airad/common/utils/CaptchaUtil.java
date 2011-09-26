package com.mitian.airad.common.utils;

import java.util.Date;

import nl.captcha.Captcha;

/**
 * CaptchaUtil.java
 * 
 * @author baojun
 */
public abstract class CaptchaUtil {
    /**
     * 工具类无法实例化
     */
    private CaptchaUtil() {
        super();
    }

    private static final long CAPTCHA_EXPIRE_TIME = 1000 * 60 * 3;

    public static final String CAPTCHA_KEY_IN_SESSION = "check_code";

    // Expire the CAPTCHA after a given number of minutes
    public static boolean isExpire(Captcha captcha) {
        if (captcha == null) {
            return true;
        }
        long ts = captcha.getTimeStamp().getTime();
        long now = new Date().getTime();
        long diff = now - ts;
        return diff >= CAPTCHA_EXPIRE_TIME;
    }
}
