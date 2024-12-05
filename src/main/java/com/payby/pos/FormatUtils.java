package com.payby.pos;

public class FormatUtils {

    private static final String FILL_CHARS_ZERO = "0";

    private static final String FILL_CHARS_BLANK = " ";

    public enum FillZeroPosition {
        RIGHT, LEFT;
    }

    public static String fillZero(String oldValue, int targetLength, FillZeroPosition position) {
        return fillChars(oldValue, targetLength, FILL_CHARS_ZERO, position);
    }

    public static String fillBlank(String oldValue, int targetLength, FillZeroPosition position) {
        return fillChars(oldValue, targetLength, FILL_CHARS_BLANK, position);
    }

    /**
     * 在字符串的左侧、右侧填充0
     *
     * @param oldValue     需要填充的字符串
     * @param targetLength 目标长度
     * @param fillChars    被填充的字符
     * @param position     填充位置
     * @return 结果字符串
     */
    public static String fillChars(String oldValue, int targetLength, String fillChars, FillZeroPosition position) {
        if (oldValue == null) {
            return null;
        }
        if (oldValue.length() >= targetLength) {
            return oldValue.substring(0, targetLength);
        }
        StringBuilder chars = new StringBuilder();
        for (int i = 0; i < targetLength - oldValue.length(); i += fillChars.length()) {
            chars.append(fillChars);
        }
        return FillZeroPosition.LEFT == position ? chars + oldValue : oldValue + chars;
    }

    /**
     * 格式化币种，如果过长去掉左侧的0，如果过短，在左侧补0
     *
     * @param currency     币种编码
     * @param targetLength 目标长度
     * @return
     */
    public static String formatCurrency(String currency, int targetLength) {
        if (currency == null) {
            return fillZero("784", targetLength, FillZeroPosition.LEFT);
        }
        if (currency.length() == targetLength) {
            return currency;
        }
        if (currency.length() > targetLength) {
            return currency.substring(currency.length() - targetLength);
        }
        return FormatUtils.fillZero(currency, targetLength, FillZeroPosition.LEFT);
    }

}
