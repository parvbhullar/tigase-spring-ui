package com.mitian.airad.common.auth;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.mitian.airad.common.auth.cookie.CookieGenerator;
import com.mitian.airad.common.codec.EncryptService;
import com.mitian.airad.common.utils.Assert;

/**
 * 抽象认证类，除了验证密码之外，还要写Cookie
 */
public abstract class AbstractAuthService implements AuthService {

    private CookieGenerator cookieGenerator;

    @Autowired
    private EncryptService encryptService;

    public CookieGenerator getCookieGenerator() {
        return cookieGenerator;
    }

    public void setCookieGenerator(CookieGenerator cookieGenerator) {
        this.cookieGenerator = cookieGenerator;
    }

    public AuthResult authenticate(HttpServletResponse response, AuthenticationToken token) {
        AuthResult result = new AuthResult();
        try {
            AuthenticationToken auth = doAuthentication(token);
            String encryptUserId = getEncryptedUserId(auth);
            addCookie(response, encryptUserId);
            result.setDefaultModel(auth);
            result.setSuccess(true);
        }
        catch (Exception ex) {
            result.setSuccess(false);
            result.setErrorMessage(ex.getMessage());
        }
        return result;

    }

    /**
     * @param auth
     * @return
     */
    private String getEncryptedUserId(AuthenticationToken auth) {
        Long userId = auth.getUserId();
        Assert.notNull(userId);

        String encrypted =
                encryptService.encrypt(userId.toString(), EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.name());
        return encrypted;
    }

    /**
     *添加Cookie, 此处写入的Cookie，跳转时从浏览器请求中获取不到，设置 Cookie的 Secure=FALSE解决。
     * 
     * @param auth
     * @param response
     */
    private void addCookie(HttpServletResponse response, String encryptUserId) {
        cookieGenerator.addCookie(response, encryptUserId);

    }

    /**
     * 子类实现具体的认证
     * 
     * @param token
     * @return
     */

    protected abstract AuthenticationToken doAuthentication(AuthenticationToken token);
}
