package com.big.river.card;

public class TransactionInfo {

    public long amount;
    public String cardNumber;
    public String currencyCode;
    public String transactionDate;
    public long createTime = System.currentTimeMillis();

    @Override
    public String toString() {
        return "TransactionInfo{" +
                "amount=" + amount +
                ", cardNumber='" + cardNumber + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}
