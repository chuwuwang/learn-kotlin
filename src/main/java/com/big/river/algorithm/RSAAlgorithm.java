package com.big.river.algorithm;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSAAlgorithm {

    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 从私钥字符串中获取私钥
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] bytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = Base64.getDecoder().decode(bytes);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * RSA私钥签名
     */
    public static byte[] signByPrivateKey(String content, String privateKey) throws Exception {
        PrivateKey priKey = getPrivateKey(privateKey);
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(priKey);
        signature.update(contentBytes);
        return signature.sign();
    }

}