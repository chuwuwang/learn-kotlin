package com.big.river.ecr;

import com.big.river.helper.ByteHelper;

/**
 * star len data end lrc
 * STX(1) + LEN(2) + DATA(LEN) + ETX(1) + LRC(1)
 */
public class AssemblyPackHelper {

    public AssemblyPackHelper() {

    }

    public String assembly(String value) {
        int length = value.length();
        String len = String.format("%04d", length / 2);
        String calculateLRCString = len + value + "03";
        String lrc = calculateLRC(calculateLRCString);
        return "02" + calculateLRCString + lrc;
    }

    private String calculateLRC(String string) {
        byte[] bytes = ByteHelper.hexString2Bytes(string);
        byte calLrc = 0X00;
        for (byte temp : bytes) {
            calLrc ^= temp;
        }
        return ByteHelper.byte2HexString(calLrc);
    }

}