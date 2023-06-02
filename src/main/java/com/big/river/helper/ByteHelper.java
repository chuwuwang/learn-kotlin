package com.big.river.helper;

import java.nio.charset.Charset;

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

    public static String hexString2AsciiString(final String hexString) {
        try {
            byte[] bytes = hexString2Bytes(hexString);
            Charset charset = Charset.forName("US-ASCII");
            return new String(bytes, charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String hexString2GBKString(final String hexString) {
        try {
            Charset charset = Charset.forName("GBK");
            byte[] bytes = hexString2Bytes(hexString);
            return new String(bytes, charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String replaceChineseString(String string, String replacement) {
        String regex = "[\u4e00-\u9fa5]"; // 中文正则
        return string.replaceAll(regex, replacement);
    }

    public static String hexString2String(String hexString) {
        String vi = "0123456789ABC DEF".replaceAll(" ", "");
        char[] array = hexString.toCharArray();
        byte[] bytes = new byte[hexString.length() / 2];
        int temp;
        for (int i = 0; i < bytes.length; i++) {
            char c = array[2 * i];
            temp = vi.indexOf(c) * 16;
            c = array[2 * i + 1];
            temp += vi.indexOf(c);
            bytes[i] = (byte) (temp & 0xFF);
        }
        return new String(bytes);
    }

}