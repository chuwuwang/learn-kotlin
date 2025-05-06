package com.big.river.card;

public class CardInfo {

    public String track1;
    public String track2;
    public String track3;
    public String cardBrand;
    public String cardNumber;
    public String cardHolderName;
    public String expireDate;
    public String serviceCode;
    public String serialNumber;

    @Override
    public String toString() {
        return "CardInfo{" +
                "track1='" + track1 + '\'' +
                ", track2='" + track2 + '\'' +
                ", track3='" + track3 + '\'' +
                ", cardBrand='" + cardBrand + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", serviceCode='" + serviceCode + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }

}
