package com.big.river.visa;

@Getter
public enum VisaFldFlag {

    /**
     * 十六进制
     *
     */
    HEX_DECIMAL,

    /**
     * 固定长度字段，使用 ASCII 编码
     *
     */
    ASCII_FIXED,

    /**
     * 1位byte可变长度的 ASCII 编码
     *
     */
    ASCII_VAR1,

    /**
     * 2位byte可变长度的 ASCII 编码
     *
     */
    ASCII_VAR2,

    /**
     * 3位byte可变长度的 ASCII 编码
     *
     */
    ASCII_VAR3,

    /**
     * 固定长度的 EBCDIC 编码
     *
     */
    EBCDIC_FIXED,

    /**
     * 1位byte可变长度的 EBCDIC 编码
     *
     */
    EBCDIC_VAR1,

    /**
     * 2位byte可变长度的 EBCDIC 编码
     *
     */
    EBCDIC_VAR2,

    /**
     * 3位byte可变长度的 EBCDIC 编码
     *
     */
    EBCDIC_VAR3,

    /**
     * 固定长度的 BCD 编码
     *
     */
    BCD_FIXED,

    /**
     * 1位byte可变长度的 BCD 编码
     *
     */
    BCD_VAR1,

    /**
     * 2位byte可变长度的 BCD 编码
     *
     */
    BCD_VAR2,

    /**
     * 3位byte可变长度的 BCD 编码
     *
     */
    BCD_VAR3,

    /**
     * 固定长度的 binary 编码
     *
     */
    BINARY_FIXED,

    /**
     * 1位byte可变长度的 binary 编码
     *
     */
    BINARY_VAR1,

    /**
     * 2位byte可变长度的 binary 编码
     *
     */
    BINARY_VAR2,

    /**
     * 3位byte可变长度的 binary 编码
     *
     */
    BINARY_VAR3,

    /**
     * BINARY_EBCDIC_FIXED 编码
     *
     */
    BINARY_EBCDIC_FIXED;

}
