package com.big.river.ecr;

import com.big.river.helper.ByteHelper;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;

public class ECRMain {

    public static void main(String[] args) {
        EcrRequest request = new EcrRequest();
        request.messageType = "Request";
        request.functionName = "SALE";
        request.requestTime = System.currentTimeMillis();
        request.messageId = "KTX_" + System.currentTimeMillis();
        request.misOrderNo = "MIS_" + System.currentTimeMillis();
        request.subject = "China Tea";
        request.totalAmount = 1.00;
        request.tipAmount = 0.00;
        request.payMethod = "BANK_CARD";
        String json = new Gson().toJson(request);
        System.err.println("request ---> " + json);
        byte[] bytes = json.getBytes(StandardCharsets.US_ASCII);
        String data = ByteHelper.bytes2HexString(bytes);
        String message = new AssemblyPackHelper().assembly(data);
        System.err.println("message ---> " + message);

        String regex = "(.{2})";
        message = message.replaceAll(regex, "$1 ");
        System.err.println("message ---> " + message);
    }

}
