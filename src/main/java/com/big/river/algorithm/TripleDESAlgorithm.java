package com.big.river.algorithm;

import com.big.river.helper.ByteHelper;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class TripleDESAlgorithm {

    public static byte[] encrypt3DesECB(byte[] key, byte[] data) {
        try {
            String des3 = "DES" + "ede";
            SecretKey secretKey = new SecretKeySpec(key, des3);
            Cipher cipher = Cipher.getInstance(des3 + "/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[16];
    }

    public static byte[] decrypt3DesECB(byte[] key, byte[] data) {
        try {
            String des3 = "DES" + "ede";
            SecretKey secretKey = new SecretKeySpec(key, des3);
            Cipher cipher = Cipher.getInstance(des3 + "/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[16];
    }


    public static byte[] encrypt3DesCBC(byte[] key, byte[] data) {
        try {
            String des3 = "DES" + "ede";
            byte[] iv = ByteHelper.hexString2Bytes("0000000000000000");
            SecretKey secretKey = new SecretKeySpec(key, des3);
            Cipher cipher = Cipher.getInstance(des3 + "/CBC/NoPadding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[16];
    }

    public static byte[] decrypt3DesCBC(byte[] key, byte[] data) {
        try {
            String des3 = "DES" + "ede";
            byte[] iv = ByteHelper.hexString2Bytes("0000000000000000");
            SecretKey secretKey = new SecretKeySpec(key, des3);
            Cipher cipher = Cipher.getInstance(des3 + "/CBC/NoPadding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[16];
    }

}