package com.big.river.pin.block;

public class HexUtils {

    public static int fromHex(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        }
        throw new IllegalArgumentException();
    }

    public static char toHex(int nybble) {
        if (nybble < 0 || nybble > 15) {
            throw new IllegalArgumentException();
        }
        return "0123456789ABCDEF".charAt(nybble);
    }

    public static String xorHex(String a, String b) {
        char[] chars = new char[a.length()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = toHex(fromHex(a.charAt(i)) ^ fromHex(b.charAt(i)));
        }
        return new String(chars).toUpperCase();
    }

    public static String generatePinBlock(String pin, String cardNumber) throws Exception {
        if (pin.length() < 4 || pin.length() > 6) { // Throw exception here
        }
        //  as an alternative to below pinBlock formation, you can execute: String pinBlock = StringUtils.rightPad("0" + pin, 16, 'F');
        String pinBlock = String.format("%s%d%s", "0", pin.length(), pin);
        while (pinBlock.length() != 16) {
            pinBlock += "F";
        }
        int cardLen = cardNumber.length();
        String pan = "0000" + cardNumber.substring(cardLen - 13, cardLen - 1);
        return xorHex(pinBlock, pan);
    }

}