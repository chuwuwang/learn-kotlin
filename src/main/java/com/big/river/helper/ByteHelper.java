package com.big.river.helper;

public class ByteHelper {

    public static String bytes2HexString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) return null;
        StringBuilder sb = new StringBuilder();
        String string;
        for (byte b : bytes) {
            string = Integer.toHexString(0xFF & b);
            if (string.length() == 1) {
                string = "0" + string;
            }
            sb.append(string);
        }
        return sb.toString().toUpperCase();
    }

    public static byte[] hexString2Bytes(String hexString) {
        if (hexString == null || hexString.length() == 0) return null;
        hexString = hexString.toLowerCase();
        int length = hexString.length();
        int index = 0;
        byte[] bytes = new byte[length >> 1];
        for (int i = 0; i < length; i++) {
            if (index > hexString.length() - 1) {
                return bytes;
            }
            char highChar = hexString.charAt(index);
            char lowChar = hexString.charAt(index + 1);
            byte highByte = (byte) (Character.digit(highChar, 16) & 0xFF);
            byte lowByte = (byte) (Character.digit(lowChar, 16) & 0xFF);
            bytes[i] = (byte) (highByte << 4 | lowByte);
            index += 2;
        }
        return bytes;
    }

}