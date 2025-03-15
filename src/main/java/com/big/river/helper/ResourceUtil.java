package com.big.river.helper;

import java.io.*;

public class ResourceUtil {

    public static byte[] getFileText(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return fileInputStream.readAllBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
