package com.big.river.card;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class TransactionAssist {

    public static void onCallbackTransactionInfo(TransactionInfo info) {
        String data = buildCallbackData("transactionData", info);
        System.out.println("Transaction Info: " + data);
    }

    private static String buildCallbackData(String type, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("data", data);
        return new Gson().toJson(map);
    }

}
