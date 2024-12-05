package com.big.river.algorithm;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAKeyGenParameterSpec;
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

    public static String formatRSA(String pubKey) {
        StringBuffer sb = new StringBuffer();
        sb.append("-----BEGIN PUBLIC KEY-----\n");
        var i = 0;
        int length = pubKey.length();
        while (i < length) {
            if (length - i > 64) {
                String string = pubKey.substring(i, i + 64);
                sb.append(string).append("\n");
            } else {
                String string = pubKey.substring(i);
                sb.append(string).append("\n");
            }
            i += 64;
        }
        sb.append("-----END PUBLIC KEY-----\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        var publicKeyString = "";
        var privateKeyString = "";
        try {
            BigInteger publicExponent = new BigInteger("00000003", 16);
            RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(2048, publicExponent);
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(spec);
            KeyPair keyPair = gen.generateKeyPair();
            // 保存公钥
            byte[] pubKey = keyPair.getPublic().getEncoded();
            publicKeyString = Base64.getEncoder().encodeToString(pubKey);
            publicKeyString = formatRSA(publicKeyString);
            System.out.println("publicKeyString: " + publicKeyString);

            // 保存私钥
            byte[] pvtKey = keyPair.getPrivate().getEncoded();
            privateKeyString = Base64.getEncoder().encodeToString(pvtKey);
            System.out.println("privateKeyString: " + privateKeyString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}