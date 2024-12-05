package com.big.river.helper;

import java.util.Base64;

public class Base64UrlHelper {

    public static String decode(String data) {
        byte[] dataBytes = data.getBytes();
        byte[] decodeBytes = Base64.getUrlDecoder().decode(dataBytes);
        return new String(decodeBytes);
    }

}