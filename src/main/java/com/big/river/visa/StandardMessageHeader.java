package com.big.river.visa;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StandardMessageHeader {

    // +------------------------+
    // | Reject Message Header  |  <-- VisaNet 加的(Header field 1 length must be 26 or higher)
    // +------------------------+
    // | Original Msg Header    |  <-- 原始发过来的报文头
    // +------------------------+
    // | Original Msg Data      |  <-- 原始发过来的数据部分（如 Bitmap + Data Elements）
    // +------------------------+

    /**
     * Field 1 – Header Length
     * fixed length：报头字段 1 以十六进制指定此报头中的字节数
     * 1 byte
     */
    @IsoVisa8583Annotation(fldIndex = 1, dataFldLength = 1, fldFlag = VisaFldFlag.HEX_DECIMAL, filedDescription = "头长度")
    private String headerLength;

    /**
     * Header Field 2 – Header Flag and  Format
     * 8 N, bit string
     * 1 byte
     */
    @IsoVisa8583Annotation(fldIndex = 2, dataFldLength = 1, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "头标识和格式")
    private String headerFlagAndFormat;

    /**
     * Header Field 3 – Text Format
     * 1B (binary)
     * 1 byte
     */
    @IsoVisa8583Annotation(fldIndex = 3, dataFldLength = 1, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "文本格式")
    private String textFormat;

    /**
     * Header Field 4 – Total Message Length
     * 2B (binary)
     * 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 4, dataFldLength = 2, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "消息总长度")
    private String totalMessageLength;

    /**
     * Header Field 5 – Destination Station ID
     * 6 N, 4-bit Binary-Coded Decimal Notation (BCD) (unsigned packed)
     * 3 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 5, dataFldLength = 3, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "目的地ID")
    private String destinationStationId;

    /**
     * Header Field 6 – Source Station ID
     * 6 N, 4-bit BCD (unsigned packed)
     * 3 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 6, dataFldLength = 6, // �� 明确指明数字长度为 6（将会编码为 3 字节）
            fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "源站 ID")
    private String sourceStationId;

    /**
     * Header Field 7 – Round-Trip Control Information
     * 8 N, bit string
     * 1 byte
     */
    @IsoVisa8583Annotation(fldIndex = 7, dataFldLength = 1, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "往返控制信息")
    private String roundTripControlInformation;

    /**
     * Header Field 8 – V.I.P. Flags
     * 16 N, bit string
     * 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 8, dataFldLength = 2, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "VIP标识")
    private String vipFlags;

    /**
     * Header Field 9 – Message Status Flags:此字段用于控制消息的处理。当前定义的标志在图中以复选标记标识
     * 3 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 9, dataFldLength = 3, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "消息状态标志")
    private String messageStatusFlags;

    /**
     * Header Field 10 – Batch Number
     * 此字段包含 VisaNet 为该报文分配的批次号。VisaNet 收到每个新的请求或通知时，都会将当前的对账批次号插入此字段。
     * 当 VisaNet 收到之前已处理的重复报文时，批次号和字段 15 中的结算日期将设置为先前处理时确定的值。
     * VisaNet 为来自清算端点的交易创建的通知分配批次号 255
     * 1B (binary)
     * 1 byte
     */
    @IsoVisa8583Annotation(fldIndex = 10, dataFldLength = 1, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "批次号")
    private String batchNumber;

    /**
     * Header Field 11 – Reserved
     * 3B (binary)
     * 3 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 11, dataFldLength = 3, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "保留字段")
    private String reserved;

    /**
     * Header Field 12 – User Information
     * 1B (binary)
     * 1 byte
     */
    @IsoVisa8583Annotation(fldIndex = 12, dataFldLength = 1, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "用户信息")
    private String userInformation;

    /**
     * Header Field 13 – Bitmap
     * 16 N, bit string   拒绝的头中才会有此数据信息
     * 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 13, dataFldLength = 2, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "位图")
    private String bitmap;

    /**
     * Header Field 14 – Bitmap, Reject Data Group
     * 4 N, 4-bit BCD (unsigned packed) 拒绝的头中才会有此数据信息
     * 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 14, dataFldLength = 4, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "位图，拒绝数据组")
    private String bitmapRejectDataGroup;

}