package com.big.river.ecr;

import com.big.river.helper.ByteHelper;

public class UnAssemblyPackHelper {

    private int readIndex;
    private final byte[] outData = new byte[10 * 1024];

    private OnUnAssemblyListener unAssemblyListener;

    public UnAssemblyPackHelper() {

    }

    public interface OnUnAssemblyListener {

        void onSuccess(byte[] bytes);

        void onFailure(int code, String desc);

    }

    public void setOnUnAssemblyListener(OnUnAssemblyListener listener) {
        unAssemblyListener = listener;
    }

    public void unAssembly(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return;
        }

        System.arraycopy(bytes, 0, outData, readIndex, bytes.length);
        String dataString = ByteHelper.bytes2HexString(outData);
        readIndex += bytes.length;

        boolean bool = checkSTX(outData);
        if (bool) {
            return;
        }

        int length = getDataLength(dataString);
        if (length <= 0) {
            return;
        }

        // 读取的数据不完整, 继续读取
        if (readIndex - 5 < length) {
            System.out.println("读取的数据不完整, 继续读取");
            return;
        }

        bool = checkETX(outData, length);
        if (bool) {
            if (unAssemblyListener != null) {
                unAssemblyListener.onFailure(301, "Not found ETX");
            }
            return;
        }

        bool = checkLRC(outData, length);
        if (bool) {
            if (unAssemblyListener != null) {
                unAssemblyListener.onFailure(302, "LRC ERROR");
            }
            return;
        }

        String data = dataString.substring(6, 6 + length * 2);
        System.out.println("data:" + data);

        readIndex = 0;

        if (unAssemblyListener != null) {
            byte[] dataBytes = ByteHelper.hexString2Bytes(data);
            unAssemblyListener.onSuccess(dataBytes);
        }
    }

    private boolean checkSTX(byte[] data) {
        byte temp = data[0];
        if (0X02 == temp) {
            return false;
        } else {
            readIndex = 0;
            System.out.println("STX ERROR");
            return true;
        }
    }

    private int getDataLength(String data) {
        try {
            String value = data.substring(2, 6);
            int length = Integer.parseInt(value);
            System.out.println("LEN:" + length);
            return length;
        } catch (Exception e) {
            System.out.println("LEN ERROR");
            return 0;
        }
    }

    private boolean checkETX(byte[] data, int length) {
        byte temp = data[length + 3];
        String etxString = ByteHelper.byte2HexString(temp);
        System.out.println("ETX:" + etxString);
        if (0X03 == temp) {
            return false;
        } else {
            readIndex = 0;
            System.out.println("ETX ERROR");
            return true;
        }
    }

    private boolean checkLRC(byte[] data, int length) {
        byte resLrc = data[length + 5 - 1];
        byte[] dataOut = new byte[length + 5 - 2];
        System.arraycopy(data, 1, dataOut, 0, dataOut.length);
        byte calLrc = 0X00;
        for (byte temp : dataOut) {
            calLrc ^= temp;
        }
        String resLrcString = ByteHelper.byte2HexString(resLrc);
        String calLrcString = ByteHelper.byte2HexString(calLrc);
        System.out.println("resLrc:" + resLrcString + " calLrc:" + calLrcString);
        if (resLrc == calLrc) {
            return false;
        } else {
            readIndex = 0;
            System.out.println("LRC ERROR");
            return true;
        }
    }

}