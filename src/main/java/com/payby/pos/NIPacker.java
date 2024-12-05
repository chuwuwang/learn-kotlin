package com.payby.pos;

import com.big.river.helper.ByteHelper;
import org.apache.commons.lang.StringUtils;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.BASE24Packager;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class NIPacker {

    public static void main(String[] args) throws Exception {
        String cardAcceptorName = getCardAcceptorName("AL ZAHRA PRINTING MAT & MEDIA INSTRUMENTS TR");
        System.out.printf("Card Acceptor Name: %s\n", cardAcceptorName);
        byte[] bytes = cardAcceptorName.getBytes("US-ASCII");
        String hexString = ByteHelper.bytes2HexString(bytes);
        System.out.printf("Hex String: %s\n", hexString);

        String instOrderNo = "D51240912003944";
        String rrn = StringUtils.substring(instOrderNo, instOrderNo.length() - 12);
        System.out.printf("rrn: %s\n", rrn);

        String mcc = "5462";
        String storeId = "9176";
        String terminalId = "11064";
        String transmitDateTime = getTransmitDateTime();
        BASE24Packager base24Packager = new BASE24Packager();
        String currentTime = getCurrentDate(LOCAL_TIME_FORMAT);
        String currentDate = getCurrentDate(LOCAL_DATE_FORMAT);
        String traceNo = StringUtils.substring(instOrderNo, instOrderNo.length() - 6);

        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(base24Packager);
        isoMsg.set(4, "123");
        isoMsg.set(7, transmitDateTime);
        isoMsg.set(11, traceNo);
        isoMsg.set(12, currentTime);
        isoMsg.set(13, currentDate);
        isoMsg.set(17, currentDate);
        isoMsg.set(18, mcc);
        isoMsg.set(32, "78400000784");
        isoMsg.set(41, terminalId);
        isoMsg.set(42, storeId);
        isoMsg.set(43, cardAcceptorName);
        isoMsg.set(48, storeId);
        isoMsg.set(60, "PYBPPYBP+0000000");
        isoMsg.set(61, "0000000000000000000");
        isoMsg.set(100, "00000000000");
        isoMsg.set(124, "000000000");
        isoMsg.set(126, "00000000000000000000000000000000000000");

        isoMsg.setMTI("0200");
        isoMsg.set(3, "000000");
        isoMsg.set(22, "071");
        isoMsg.set(35, "5364342500317180=29012261294686200000");
        isoMsg.set(25, "00");
        isoMsg.set(37, rrn);
        isoMsg.set(23, "000");
        isoMsg.set(49, "784");

        byte[] pack = isoMsg.pack();
        String string = "ISO026000077" + new String(pack);
        System.out.printf("String: %s\n", string);

        new NIPacker().pack();
    }

    public void pack() throws ISOException {
        String instOrderNo = "D51240912003944";
        String rrn = StringUtils.substring(instOrderNo, instOrderNo.length() - 12);

        ISOMsg isoMsg = new NIPacker().initIsoMsg();
        isoMsg.setMTI("0200");
        isoMsg.set(PROCESSING_CODE, "000000");
        isoMsg.set(POS_ENTRY_MODE, "071");
        isoMsg.set(TRACK2, "5364342500317180=29012261294686200000");
        isoMsg.set(POS_CONDITION_CODE, DEFAULT_POS_CONDITION_CODE);
        isoMsg.set(RRN, rrn);

        byte[] pack = isoMsg.pack();
        String string = "ISO026000077" + new String(pack);
        System.out.printf("String: %s\n", string);
    }

    private ISOMsg initIsoMsg() {
        String instOrderNo = "D51240912003944";
        String transmitDateTime = getTransmitDateTime();
        BASE24Packager BASE_24_PACKAGER = new BASE24Packager();

        String mcc = "5462";
        String storeId = "461001000141";
        String terminalId = "47100053";
        String date = getCurrentDate(LOCAL_DATE_FORMAT);
        String time = getCurrentDate(LOCAL_TIME_FORMAT);
        String traceNo = StringUtils.substring(instOrderNo, instOrderNo.length() - 6);
        String cardAcceptorName = getCardAcceptorName("AL ZAHRA PRINTING MAT—— & MEDIA INSTRUMENTS TR ");

        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(BASE_24_PACKAGER);

        isoMsg.set(TRAN_AMOUNT, "777");
        isoMsg.set(TRANSMIT_DATE_AND_TIME, transmitDateTime);
        isoMsg.set(TRACE_NUMBER, traceNo);

        isoMsg.set(LOCAL_TIME, time);
        isoMsg.set(LOCAL_DATE, date);
        isoMsg.set(CAPTURE_DATE, "0913");

        isoMsg.set(MERCHANT_TYPE, mcc);
        isoMsg.set(ACQUIRING_INST_ID, DEFAULT_ACQUIRING_INST_ID);
        isoMsg.set(CARD_ACCEPTOR_TERM_ID, terminalId);
        isoMsg.set(CARD_ACCEPTOR_ID, storeId);
        isoMsg.set(CARD_ACCEPTOR_NAME, "AL  ZAHRA PRINTING MAT                 AE");
        isoMsg.set(ADDITION_DATA_PRIVATE, storeId);
        isoMsg.set(POS_TERMINAL_DATA, DEFAULT_POS_TERMINAL_DATA);
        isoMsg.set(POS_CARD_RESPONSE_CODE, DEFAULT_POS_CARD_RESPONSE_CODE);
        isoMsg.set(RCV_INST_ID, DEFAULT_RCV_INST_ID);
        isoMsg.set(RESERVED, DEFAULT_RESERVED);
        isoMsg.set(RESERVED_PRIVATE, DEFAULT_RESERVED_PRIVATE);

        return isoMsg;
    }

    public static String getCardAcceptorName(String merchantName) {
        return FormatUtils.fillBlank(StringUtils.substring(merchantName, 0, 22), 38, FormatUtils.FillZeroPosition.RIGHT) + "AE";
    }

    public static String getTransmitDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
        return LocalDateTime.now(ZoneOffset.UTC).format(formatter);
    }

    public static String getCurrentDate(DateTimeFormatter format) {
        ZoneOffset zoneOffset = ZoneOffset.ofHours(4);
        return LocalDateTime.now(zoneOffset).format(format);
    }

    final static DateTimeFormatter LOCAL_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmmss");

    final static DateTimeFormatter LOCAL_DATE_FORMAT = DateTimeFormatter.ofPattern("MMdd");

    int PAN = 2;
    int PROCESSING_CODE = 3;
    int TRAN_AMOUNT = 4;
    int TRANSMIT_DATE_AND_TIME = 7;
    int TRACE_NUMBER = 11;
    int LOCAL_TIME = 12;
    int LOCAL_DATE = 13;
    int EXPIRY_DATE = 14;
    int SETTLEMENT_DATE = 15;
    int CAPTURE_DATE = 17;
    int MERCHANT_TYPE = 18;
    int POS_ENTRY_MODE = 22;
    int CARD_SEQ_NUMBER = 23;
    int POS_CONDITION_CODE = 25;
    int ACQUIRING_INST_ID = 32;
    int TRACK2 = 35;
    int RRN = 37;
    int AUTH_ID_RESP = 38;
    int RESPONSE_CODE = 39;
    int CARD_ACCEPTOR_TERM_ID = 41;
    int CARD_ACCEPTOR_ID = 42;
    int CARD_ACCEPTOR_NAME = 43;
    int ADDITION_DATA_PRIVATE = 48;
    int TRAN_CURRENCY_CODE = 49;
    int PIN_DATA = 52;
    int POS_TERMINAL_DATA = 60;
    int POS_CARD_RESPONSE_CODE = 61;
    int BANK_NET_DATA = 63;
    int ORIGINAL_DATA_ELEMENTS = 90;
    int RCV_INST_ID = 100;
    int RESERVED = 124;
    int RESERVED_PRIVATE = 126;

    protected static final String DEFAULT_POS_CONDITION_CODE = "00";
    private static final String DEFAULT_POS_TERMINAL_DATA = "PYBPPYBP+0000000";
    private static final String DEFAULT_ACQUIRING_INST_ID = "78400000784";
    private static final String DEFAULT_POS_CARD_RESPONSE_CODE = "0000000000000000000";
    private static final String DEFAULT_RCV_INST_ID = "00000000000";
    private static final String DEFAULT_RESERVED = "000000000";
    private static final String DEFAULT_RESERVED_PRIVATE = "00000000000000000000000000000000000000";

}
