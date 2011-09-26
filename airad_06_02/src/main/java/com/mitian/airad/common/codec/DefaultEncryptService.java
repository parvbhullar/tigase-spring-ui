/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.codec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.mitian.airad.common.codec.digester.ByteDigesterFactory;
import com.mitian.airad.common.codec.digester.Digester;
import com.mitian.airad.common.codec.digester.StringDigesterFactory;
import com.mitian.airad.common.codec.encrypter.ByteEncrypterFactory;
import com.mitian.airad.common.codec.encrypter.Encrypter;
import com.mitian.airad.common.codec.encrypter.StringEncrypterFactory;
import com.mitian.airad.common.utils.StringUtils;

/**
 * 加密/解密/摘要服务 实现类
 * 
 * @author zhoufengbo
 * @since 2010-09-17
 * @version 1.0
 */

enum DEFAULT_DIGESTER {
    MD5
}

enum DEFAULT_ENCRYPTER {
    PBEWithMD5AndDES
}

/**
 * DefaultEncryptService.java
 * 
 * @author baojun
 */
@Service
public class DefaultEncryptService implements EncryptService {
    private static Log logger = LogFactory.getLog(DefaultEncryptService.class);

    public byte[] encrypt(byte[] plaintext, String algorithm) {
        final Encrypter encrypt = getByteEncrypt(algorithm);
        return encrypt.encrypt(plaintext);
    }

    public byte[] decrypt(byte[] cryptotext, String algorithm) {
        final Encrypter encrypt = getByteEncrypt(algorithm);
        return encrypt.decrypt(cryptotext);
    }

    public String decrypt(String cryptotext, String algorithm) {
        try {
            String decrypted = new String(Hex.decodeHex(cryptotext.toCharArray()));
            final Encrypter encrypt = getStringEncrypt(algorithm);
            return encrypt.decrypt(decrypted);
        }
        catch (DecoderException e) {
            logger.error("解密异常", e);
            throw new EncryptException(e);
        }
    }

    public String encrypt(String plaintext, String algorithm) {
        final Encrypter encrypt = getStringEncrypt(algorithm);
        String encrypted = encrypt.encrypt(plaintext);
        return Hex.encodeHexString(encrypted.getBytes());
    }

    public byte[] digest(byte[] digtotext, String algorithm) {
        final Digester digester = getByteDigester(algorithm);
        return digester.digest(digtotext);
    }

    public String digest(String digtotext, String algorithm) {
        final Digester digester = getStringDigester(algorithm);
        return digester.digest(digtotext);
    }

    public boolean matches(byte[] message, byte[] digest, String algorithm) {
        final Digester digester = getByteDigester(algorithm);
        return digester.matches(message, digest);
    }

    public boolean matches(String message, String digest, String algorithm) {
        final Digester digester = getStringDigester(algorithm);
        return digester.matches(message, digest);
    }

    protected Encrypter getStringEncrypt(String algorithm) {
        if (StringUtils.isBlank(algorithm)) {
            algorithm = DEFAULT_ENCRYPTER.PBEWithMD5AndDES.name();
        }
        StringEncrypterFactory factory = StringEncrypterFactory.getInstance();

        return factory.getEncrypter(algorithm);
    }

    protected Encrypter getByteEncrypt(String algorithm) {
        if (StringUtils.isBlank(algorithm)) {
            algorithm = DEFAULT_ENCRYPTER.PBEWithMD5AndDES.name();
        }
        ByteEncrypterFactory factory = ByteEncrypterFactory.getInstance();

        return factory.getEncrypter(algorithm);
    }

    protected Digester getByteDigester(String algorithm) {
        if (StringUtils.isBlank(algorithm)) {
            algorithm = DEFAULT_DIGESTER.MD5.name();
        }
        ByteDigesterFactory factory = ByteDigesterFactory.getInstance();
        return factory.getDigester(algorithm);

    }

    protected Digester getStringDigester(String algorithm) {
        if (StringUtils.isBlank(algorithm)) {
            algorithm = DEFAULT_DIGESTER.MD5.name();
        }
        StringDigesterFactory factory = StringDigesterFactory.getInstance();
        return factory.getDigester(algorithm);

    }

}
