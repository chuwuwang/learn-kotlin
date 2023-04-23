package com.big.river.algorithm;

public class BasicAlgorithm {

    public static byte[] xor(byte[] bytes, byte[] arg) {
        if (bytes == null || arg == null) return null;
        if (bytes.length == 0 || bytes.length != arg.length) return null;
        int length = bytes.length;
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            byte b1 = bytes[i];
            byte b2 = arg[i];
            result[i] = (byte) (b1 ^ b2);
        }
        return result;
    }

}
