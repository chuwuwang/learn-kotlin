package com.big.river.visa;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VisaIso8583InterfaceDTO {

    /**
     * 位图域
     */
    private int bitMapLength;

    /**
     * 完整位图
     */
    private String bitMap;

    /**
     * 消息类型
     * fixed length
     * 4 N, 4–bit BCD (unsigned packed), 2 bytes
     */
    // @IsoVisa8583Annotation(fldIndex = 0, dataFldLength = 4, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "Message Type Identifier Structure")
    private String messageTypeIdentifierStructure;

    /**
     * Field 2 – Primary Account Number
     * variable length
     * 1 byte, binary +
     * 19 N, 4-bit BCD (unsigned packed); maximum 11 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 2, dataFldLength = 19, fldFlag = VisaFldFlag.BCD_VAR1, filedDescription = "卡号")
    private String primaryAccountNumber;

    /**
     * Field 3 – Processing Code 使用枚举 VisaProcessingCodeEnum 组装此字段
     * fixed length
     * 6 N, 4-bit BCD (unsigned packed); 3 bytes
     * Positions 1-2, Transaction Type: A 2-digit code identifying the customer transaction type or the center function being processed.
     * Positions 3-4, Account Type (From): A 2-digit code identifying the account type affected by this transaction or from which an account transfer is made.
     * Positions 5-6, Account Type (To): For ATM account transfers, a two-digit code identifying the account type to which an account transfer is made.
     */
    @IsoVisa8583Annotation(fldIndex = 3, dataFldLength = 6, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "处理码")
    private String processingCode;
    private ProcessingCode processingCodeObj;

    /**
     * Field 4 – Amount, Transaction
     * fixed length
     * 12 N, 4-bit BCD (unsigned packed); 6 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 4, dataFldLength = 12, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "交易金额")
    private String amountTransaction;

    /**
     * Field 6 – Amount, Cardholder Billing
     * fixed length
     * 12 N, 4-bit BCD (unsigned packed); 6 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 6, dataFldLength = 12, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "持卡人账单金额")
    private String amountCardholderBilling;

    /**
     * Field 7 – Transmission Date and Time
     * fixed length
     * 10 N, 4-bit BCD (unsigned packed); 5 bytes
     * format: MMDDhhmmss
     */
    @IsoVisa8583Annotation(fldIndex = 7, dataFldLength = 10, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "传输日期和时间")
    private String transmissionDateAndTime;

    /**
     * Field 9 – Conversion Rate, Settlement
     * fixed length
     * 8 N, 4-bit BCD (unsigned packed); 4 bytes
     * 示例：69985022 = 9.985022
     */
    @IsoVisa8583Annotation(fldIndex = 9, dataFldLength = 8, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "结算转换率")
    private String conversionRateSettlement;

    /**
     * Field 10 – Conversion Rate, Cardholder Billing
     * fixed length
     * 8 N, 4-bit BCD (unsigned packed); 4 bytes
     * 示例：69985022 = 9.985022
     */
    @IsoVisa8583Annotation(fldIndex = 10, dataFldLength = 8, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "持卡人账单转换率")
    private String conversionRateCardholderBilling;

    /**
     * Field 11 – System Trace Audit Number
     * fixed length
     * 6 N, 4-bit BCD (unsigned packed); 3 bytes
     * Visa 明确要求：冲正（Reversal）或撤销（Cancellation）必须使用原始授权请求中的 Field 11（System Trace Audit Number, STAN），而不是新生成的 STAN。
     * 所有属于同一笔交易生命周期的消息（授权、撤销、冲正、完成、完成冲正等）都必须复用相同的 Field 11 值
     */
    @IsoVisa8583Annotation(fldIndex = 11, dataFldLength = 6, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "系统跟踪审计号")
    private String systemTraceAuditNumber;

    /**
     * Field 12 – Time, Local Transaction
     * fixed length
     * 6 N, 4-bit BCD (unsigned packed); 3 bytes
     * format: hhmmss
     */
    @IsoVisa8583Annotation(fldIndex = 12, dataFldLength = 6, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "本地交易时间")
    private String timeLocalTransaction;

    /**
     * Field 13 – Date, Local Transaction
     * fixed length
     * 4 N, 4-bit BCD (unsigned packed); 2 bytes
     * format: mmdd
     */
    @IsoVisa8583Annotation(fldIndex = 13, dataFldLength = 4, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "本地交易日期")
    private String dateLocalTransaction;

    /**
     * Field 14 – Date, Expiration
     * fixed length
     * 4 N, 4-bit BCD (unsigned packed); 2 bytes
     * format: yymm
     */
    @IsoVisa8583Annotation(fldIndex = 14, dataFldLength = 4, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "卡号过期日期")
    private String dateExpiration;

    /**
     * Field 15 – Date, Settlement
     * fixed length
     * 4 N, 4-bit BCD (unsigned packed); 2 bytes
     * format: mmdd
     */
    @IsoVisa8583Annotation(fldIndex = 15, dataFldLength = 4, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "结算日期")
    private String dateSettlement;

    /**
     * Field 18 – Merchant Type
     * fixed length
     * 4 N, 4-bit BCD (unsigned packed); 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 18, dataFldLength = 4, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "商户类型")
    private String merchantType;

    /**
     * Field 19 – Acquiring Institution Country Code
     * fixed length
     * 3 N, 4-bit BCD (unsigned packed); 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 19, dataFldLength = 3, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "收单机构国家代码")
    private String acquiringInstitutionCountryCode;

    /**
     * Field 20 – PAN Extended, Country Code
     * fixed length
     * 3 N, 4-bit BCD (unsigned packed); 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 20, dataFldLength = 3, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "PAN扩展国家代码")
    private String panExtendedCountryCode;

    /**
     * Field 22 – Point-of-Service Entry Mode Code
     * fixed length
     * 4 N, 4-bit BCD (unsigned packed); 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 22, dataFldLength = 4, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "服务点输入模式代码")
    private String pointOfServiceEntryModeCode;

    /**
     * Field 23 – Card Sequence Number
     * fixed length
     * 3 N, 4-bit BCD (unsigned packed); 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 23, dataFldLength = 3, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "卡序列号")
    private String cardSequenceNumber;

    /**
     * Field 25 – Point-of-Service Condition Code
     * fixed length
     * 2 N, 4-bit BCD (unsigned packed); 1 byte
     */
    @IsoVisa8583Annotation(fldIndex = 25, dataFldLength = 2, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "服务点条件代码")
    private String pointOfServiceConditionCode;

    /**
     * Field 26 – Point-of-Service PIN Capture Code
     * fixed length
     * 2 N, 4-bit BCD (unsigned packed); 1 byte
     */
    @IsoVisa8583Annotation(fldIndex = 26, dataFldLength = 2, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "服务点PIN捕获代码")
    private String pointOfServicePinCaptureCode;

    /**
     * Field 28 – Amount, Transaction Fee
     * fixed length
     * 1 AN, EBCDIC +
     * 8 N, EBCDIC
     * total: 9 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 28, dataFldLength = 9, fldFlag = VisaFldFlag.EBCDIC_FIXED, filedDescription = "交易费用金额")
    private String amountTransactionFee;

    /**
     * Field 32 – Acquiring Institution Identification Code
     * variable length
     * 1 byte, binary +
     * 11 N, 4-bit BCD (unsigned packed); maximum 7 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 32, dataFldLength = 11, fldFlag = VisaFldFlag.BCD_VAR1, filedDescription = "收单机构标识代码")
    private String acquiringInstitutionIdentificationCode;

    /**
     * Field 33 – Forwarding Institution Identification Code
     * variable length
     * 1 byte, binary +
     * 11 N, 4-bit BCD (unsigned packed); maximum 7 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 33, dataFldLength = 11, fldFlag = VisaFldFlag.BCD_VAR1, filedDescription = "转发机构标识代码")
    private String forwardingInstitutionIdentificationCode;

    /**
     * Field 34 – Acceptance Environment Data (TLV Format)
     * variable length
     * 2 bytes, binary
     * 1535 bytes, variable by usage, maximum 1537 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 34, dataFldLength = 1535, fldFlag = VisaFldFlag.BINARY_VAR2, filedDescription = "接受环境数据(TLV格式)")
    private String acceptanceEnvironmentData;
    // private CustomerRelatedData acceptanceEnvironmentDataObj;
    private AcceptanceEnvironmentDataTlvFormat acceptanceEnvironmentDataTlvFormatObj;

    /**
     * Field 35 – Track 2 Data
     * variable length
     * 1 byte, binary +
     * 37 N, 4-bit BCD (unsigned packed); maximum 20 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 35, dataFldLength = 37, fldFlag = VisaFldFlag.BCD_VAR1, filedDescription = "磁道2数据")
    private String track2Data;

    /**
     * Field 37 – Retrieval Reference Number
     * fixed length
     * 12 AN [content limited to numerics], EBCDIC; 12 bytes
     * format: ydddnnnnnnnn
     * Positions 1-4: the yddd equivalent of the field 7 date，字段 7对应的yddd 日期（儒略日期格式）
     * Positions 5-6: the hours from the time in field 7，字段 7 中时间的小时数
     * Positions 7-12: the value from field 11，字段 11 中的值
     */
    @IsoVisa8583Annotation(fldIndex = 37, dataFldLength = 12, fldFlag = VisaFldFlag.EBCDIC_FIXED, filedDescription = "检索参考号")
    private String retrievalReferenceNumber;

    /**
     * Field 38 – Authorization Identification Response
     * fixed length
     * 6 AN, EBCDIC; 6 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 38, dataFldLength = 6, fldFlag = VisaFldFlag.EBCDIC_FIXED, filedDescription = "授权标识响应")
    private String authorizationIdentificationResponse;

    /**
     * Field 39 – Response Code
     * fixed length
     * 2 AN, EBCDIC; 2 bytes
     *
     */
    @IsoVisa8583Annotation(fldIndex = 39, dataFldLength = 2, fldFlag = VisaFldFlag.EBCDIC_FIXED, filedDescription = "响应代码")
    private String responseCode;

    /**
     * Field 41 – Card Acceptor Terminal Identification
     * fixed length
     * 8 ANS, EBCDIC; 8 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 41, dataFldLength = 8, fldFlag = VisaFldFlag.EBCDIC_FIXED, filedDescription = "卡接受器终端标识")
    private String cardAcceptorTerminalIdentification;

    /**
     * Field 42 – Card Acceptor Identification Code
     * fixed length
     * 15 ANS, EBCDIC; 15 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 42, dataFldLength = 15, fldFlag = VisaFldFlag.EBCDIC_FIXED, filedDescription = "卡接受者标识代码/子商户号")
    private String cardAcceptorIdentificationCode;

    /**
     * Field 43 – Card Acceptor Name/Location
     * fixed length
     * 40 ANS, EBCDIC; 40 bytes
     * - Positions: 1-25 : card acceptor name or Automated Teller Machine (ATM) location
     * - Positions: 26-38 : city name
     * - Positions: 39-40 : country code
     */
    @IsoVisa8583Annotation(fldIndex = 43, dataFldLength = 40, fldFlag = VisaFldFlag.EBCDIC_FIXED, filedDescription = "卡接受者名称/位置")
    private String acceptorNameAndLocation;

    /**
     * Field 44 - Additional Response Data
     * variable length
     * 1 byte, binary +
     * 25 ANS, EBCDIC; maximum 26 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 44, dataFldLength = 25, fldFlag = VisaFldFlag.EBCDIC_VAR1, filedDescription = "附加响应数据")
    private String additionalResponseData;
    private AdditionalResponseData additionalResponseDataObj;

    /**
     * Field 45 – Track 1 Data
     * variable length
     * 1 byte, binary +
     * 76 ANS, EBCDIC; maximum 77 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 45, dataFldLength = 76, fldFlag = VisaFldFlag.EBCDIC_VAR1, filedDescription = "磁道1数据")
    private String track1Data;

    /**
     * Field 46 – Amounts, Fees
     * variable length
     * 1 byte, binary +
     * 216 bytes ANS, EBCDIC; maximum 217 bytes
     * Subfield format = 36 ANS, EBCDIC
     */
    @IsoVisa8583Annotation(fldIndex = 46, dataFldLength = 216, fldFlag = VisaFldFlag.EBCDIC_VAR1, filedDescription = "费用金额")
    private String amountFee;

    /**
     * Field 48 – Additional Data - Private
     * variable length
     * 1 byte, binary +
     * 255 bytes, variable by usage; maximum 256 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 48, dataFldLength = 255, fldFlag = VisaFldFlag.EBCDIC_VAR1, filedDescription = "私有附加数据")
    private String additionalData;

    /**
     * Field 49 – Currency Code, Transaction
     * fixed length
     * 3 N, 4-bit BCD (unsigned packed); 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 49, dataFldLength = 3, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "交易货币代码")
    private String currencyCodeTransaction;

    /**
     * Field 50 – Currency Code, Settlement
     * fixed length
     * 3 N, 4-bit BCD (unsigned packed); 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 50, dataFldLength = 3, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "结算货币代码")
    private String currencyCodeSettlement;

    /**
     * Field 51 – Currency Code, Cardholder Billing
     * fixed length
     * 3 N, 4-bit BCD (unsigned packed); 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 51, dataFldLength = 3, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "持卡人账单货币代码")
    private String currencyCodeCardholderBilling;

    /**
     * Field 52 – Personal Identification Number (PIN) Data
     * fixed length
     * 64 N, bit string; 8 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 52, dataFldLength = 8, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "个人标识号码数据")
    private String personalIdentificationNumberData;

    /**
     * Field 53 – Security-Related Control Information
     * fixed length
     * 16 N, 4-bit BCD (unsigned packed); 8 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 53, dataFldLength = 16, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "安全相关控制信息")
    private String securityRelatedControlInformation;

    /**
     * Field 54 – Additional Amounts
     * variable length
     * 1 byte, binary +
     * 20 ANS, EBCDIC; 21 bytes total
     * or 40 ANS, EBCDIC; 41 bytes total
     * or 60 ANS, EBCDIC; 61 bytes total
     * or 80 ANS, EBCDIC; 81 bytes total
     * or 100 ANS, EBCDIC; 101 bytes total
     * or 120 ANS, EBCDIC; 121 bytes total
     * maximum: 121 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 54, dataFldLength = 240, fldFlag = VisaFldFlag.EBCDIC_VAR1, filedDescription = "附加金额")
    private String additionalAmounts;
    private AdditionalAmounts additionalAmountsObj;

    /**
     * Field 55 – Integrated Circuit Card (ICC)-Related Data
     * variable length
     * 1 byte binary +
     * 255 bytes, variable by usage; maximum 256 bytes
     * 解法同：CustomerRelatedData
     */
    @IsoVisa8583Annotation(fldIndex = 55, dataFldLength = 255, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "集成电路卡相关数据")
    private String intergratedCircuitCardRelatedData;
    private VsdcChipDataOld chipCardDataUsage1;
    private ChipCardDataOld chipCardDataUsage2;

    /**
     * Field 56 – Customer Related Data
     * variable length
     * 1 byte, binary +
     * 255 ANS, EBCDIC; maximum: 256 bytes
     * or
     * variable length
     * 2 byte, binary +
     * 1535 bytes, variable by usage, maximum 1537 bytes
     * <p>
     * Acquirer (收单方) 提交 Account Owner Data    必须使用 2 字节长度前缀
     * 1 字节提交  V.I.P. 忽略字段，或者 拒绝交易（跨境加拿大 OCT）
     * 加拿大跨境 OCT 的 AML 合规  不满足两字节格式 → 返回码 64
     * 加拿大 Issuer 必须支持两字节  不支持则交易失败，返回码 57
     */
    // @IsoVisa8583Annotation(fldIndex = 56, dataFldLength = 255, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "客户相关数据")
    @IsoVisa8583Annotation(fldIndex = 56, dataFldLength = 1537, fldFlag = VisaFldFlag.BINARY_VAR2, //  二字节长度前缀
            // fldFlag = VisaFldFlag.EBCDIC_VAR2, //  二字节长度前缀
            filedDescription = "客户相关数据")
    private String customerRelatedData;
    private CustomerRelatedDataOld customerRelatedDataOldObj;
    private CustomerRelatedData customerRelatedDataObj;

    /**
     * Field 59 – National Point-of-Service Geographic Data
     * variable length
     * 1 byte, binary +
     * 14 ANS, EBCDIC; maximum 15 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 59, dataFldLength = 14, fldFlag = VisaFldFlag.EBCDIC_VAR1, filedDescription = "国家POS地理数据")
    private String nationalPointOfServiceGeographicData;

    /**
     * Field 60 – Additional POS Information
     * variable length
     * 1 byte, binary +
     * 12 N, 4-bit BCD (unsigned packed), 7 bytes total
     */
    @IsoVisa8583Annotation(fldIndex = 60, dataFldLength = 6, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "附加POS信息")
    private String additionalPosInformation;
    private AdditionalPosInformation additionalPosInformationObj;

    // /**
    //  * Field 60 – Additional POS Information
    //  *  variable length
    //  *  1 byte, binary +
    //  *  12 N, 4-bit BCD (unsigned packed), 7 bytes total
    //  */
    // @IsoVisa8583Annotation(
    //         fldIndex = 60, dataFldLength = 12, fldFlag = VisaFldFlag.BCD_VAR1, filedDescription = "附加POS信息"
    // )
    // private String additionalPosInformation;

    /**
     * Field 61 – Other Amounts
     * 1 byte, binary +
     * 12 N, 4-bit BCD (unsigned packed), 7 bytes total
     * or 24 N, 4-bit BCD (unsigned packed), 13 bytes total
     * or 36 N, 4-bit BCD (unsigned packed), 19 bytes total
     */
    @IsoVisa8583Annotation(fldIndex = 61, dataFldLength = 36, fldFlag = VisaFldFlag.BCD_VAR1, filedDescription = "其他金额")
    private String otherAmounts;

    /**
     * Field 62 – Custom Payment Service Fields (Bitmap Format)
     * 1 byte, binary +
     * variable by subfield
     * maximum: 255 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 62, dataFldLength = 255, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "自定义支付服务字段（位图格式）")
    private String customPaymentServiceFieldsBitmapFormat;
    private CustomPaymentServiceField customPaymentServiceFieldObj;

    /**
     * Field 63 – VIP Private-Use Field
     * variable length
     * 1 byte, binary +
     * 255 bytes, variable; maximum: 256 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 63, dataFldLength = 255, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "VIP专用字段")
    private String vipPrivateUseField;
    private VipPrivateUseField vipPrivateUseFieldObj;

    /**
     * Field 68 – Receiving Institution Country Code
     * fixed length
     * 3 N, 4-bit BCD (unsigned packed); 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 68, dataFldLength = 3, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "接收机构国家代码")
    private String receivingInstitutionCountryCode;

    /**
     * Field 70 – Network Management Information Code
     * fixed length
     * 3 N, 4-bit BCD (unsigned packed); 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 70, dataFldLength = 3, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "网络管理信息代码")
    private String networkManagementInformationCode;

    /**
     * Field 73 – Date, Action
     * fixed length
     * 6 N, 4-bit BCD (unsigned packed); 3 bytes
     * format: variable yymmdd
     */
    @IsoVisa8583Annotation(fldIndex = 73, dataFldLength = 6, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "操作日期")
    private String dateAction;

    /**
     * Field 90 – Original Data Elements
     * fixed length
     * 42 N, 4-bit BCD (unsigned packed); 21 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 90, dataFldLength = 42, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "原始数据元素")
    private String originalDataElements;
    private OriginalDataElements originalDataElementsObj;

    /**
     * Field 91 – File Update Code
     * fixed length
     * 1 AN, EBCDIC; 1 byte
     */
    @IsoVisa8583Annotation(fldIndex = 91, dataFldLength = 1, fldFlag = VisaFldFlag.EBCDIC_FIXED, filedDescription = "文件更新代码")
    private String fileUpdateCode;

    /**
     * Field 92 – File Security Code
     * fixed length
     * 2 AN, EBCDIC; 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 92, dataFldLength = 2, fldFlag = VisaFldFlag.EBCDIC_FIXED, filedDescription = "文件安全代码")
    private String fileSecurityCode;

    /**
     * Field 95 – Replacement Amounts
     * fixed length
     * 42 AN, EBCDIC; 42 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 95, dataFldLength = 42, fldFlag = VisaFldFlag.EBCDIC_FIXED, filedDescription = "替换金额")
    private String replacementAmounts;
    private ReplacementAmounts replacementAmountsObj;

    /**
     * Field 96 – Reserved
     * fixed length
     * 64 N, bit string; 8 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 96, dataFldLength = 8, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "保留字段")
    private String reserved;

    /**
     * Field 99 – Settlement Institution Identification Code
     * variable length
     * 1 byte, binary +
     * 11 N, 4-bit BCD (unsigned packed); maximum: 7 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 99, dataFldLength = 11, fldFlag = VisaFldFlag.BCD_VAR1, filedDescription = "结算机构标识代码")
    private String settlementInstitutionIDCode;

    /**
     * Field 100 – Receiving Institution Identification Code
     * variable length
     * 1 byte, binary +
     * 11 N, 4-bit BCD (unsigned packed); maximum: 7 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 100, dataFldLength = 11, fldFlag = VisaFldFlag.BCD_VAR1, filedDescription = "接收机构标识代码")
    private String receivingInstitutionIdCode;

    /**
     * Field 101 – File Name
     * variable length
     * 1 byte, binary +
     * up to 17 ANS, EBCDIC; maximum: 18 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 101, dataFldLength = 17, fldFlag = VisaFldFlag.EBCDIC_VAR1, filedDescription = "文件名")
    private String fileName;

    /**
     * Field 102 – Account Identification 1
     * variable length
     * 1 byte, binary +
     * 1-28 ANS, EBCDIC; maximum: 29 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 102, dataFldLength = 28, fldFlag = VisaFldFlag.EBCDIC_VAR1, filedDescription = "账户标识1")
    private String accountIdentification1;

    /**
     * Field 103 – Account Identification 2
     * variable length
     * 1 byte, binary +
     * 1-28 ANS, EBCDIC; maximum: 29 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 103, dataFldLength = 28, fldFlag = VisaFldFlag.EBCDIC_VAR1, filedDescription = "账户标识2")
    private String accountIdentification2;

    /**
     * Field 104 – Transaction Description and Transaction-Specific Data
     * variable length
     * 1 byte, binary +
     * 255 bytes; variable by usage; maximum 256 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 104, dataFldLength = 255, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "交易描述和交易特定数据")
    private String transactionDescriptionAndTransactionSpecificData;
    // private CustomerRelatedDataOld transactionSpecificDataObj;
    private TransactionSpecificData transactionSpecificDataObj;

    /**
     * Field 105 – Double-Length DES Key (Triple DES)
     * fixed length
     * 128 N, bit string; 16 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 105, dataFldLength = 16, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "双倍长度DES密钥（三重DES）")
    private String doubleLengthDesKey;

    /**
     * Field 108 – Data in Local Language (TLV Format)
     * variable length
     * 2 bytes, binary+
     * 1535 bytes, variable by usage, maximum 1537 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 108, dataFldLength = 1535, fldFlag = VisaFldFlag.BINARY_VAR2, filedDescription = "本地语言数据（TLV格式）")
    private String dataInLocalLanguageTLVFormat;

    /**
     * Field 110 – Encryption Data (TLV Format)
     * variable length
     * 2-byte, binary
     * 1535 bytes, variable by usage; maximum 1537 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 110, dataFldLength = 1535, fldFlag = VisaFldFlag.BINARY_VAR2, filedDescription = "加密数据（TLV格式）")
    private String encryptionDataTLVFormat;


    /**
     * Field 111 – Additional Transaction Specific Data (TLV Format)
     * variable length
     * 2-byte, binary
     * 1535 bytes, variable by usage; maximum 1537 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 111, dataFldLength = 1535, fldFlag = VisaFldFlag.BINARY_VAR2, filedDescription = "附加交易特定数据（TLV格式）")
    private String additionalTransactionSpecificData;
    private CustomerRelatedDataOld additionalTransactionSpecificDataObj;

    /**
     * Field 114 – Domestic and Localized Data (TLV Format)
     * variable length
     * 2 bytes, binary
     * 1535 bytes, variable by usage, maximum 1537 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 114, dataFldLength = 1535, fldFlag = VisaFldFlag.BINARY_VAR2, filedDescription = "国内和本地化数据（TLV格式）")
    private String domesticAndLocalizedData;

    /**
     * Field 115 – Additional Trace Data
     * variable length
     * 1 byte, binary +
     * up to 24 ANS, EBCDIC; maximum: 25 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 115, dataFldLength = 24, fldFlag = VisaFldFlag.EBCDIC_VAR1, filedDescription = "附加跟踪数据")
    private String additionalTraceData;

    /**
     * Field 116 – Card Issuer Reference Data
     * variable length
     * 1 byte, binary +
     * 255 bytes; variable by usage; maximum: 256 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 116, dataFldLength = 255, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "发卡机构参考数据")
    private String cardIssuerReferenceData;

    /**
     * Field 117 – National Use
     * variable length
     * 1 byte, binary +
     * 3 ANS, EBCDIC, +
     * 252 ANS, EBCDIC, variable by usage;
     * maximum 256 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 117, dataFldLength = 255, fldFlag = VisaFldFlag.EBCDIC_VAR1, filedDescription = "国家使用")
    private String nationalUse;

    /**
     * Field 118 – Intra-Country Data
     * variable length
     * 1 byte, binary +
     * 3 ANS, EBCDIC, +
     * 252 ANS, EBCDIC
     * maximum 256 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 118, dataFldLength = 255, fldFlag = VisaFldFlag.EBCDIC_VAR1, filedDescription = "国内数据")
    private String intraCountryData;


    /**
     * Field 120 – Auxiliary Transaction Data (TLV Format)
     * variable length
     * 2 bytes binary
     * 1535 bytes, variable by usage; maximum 1537 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 120, dataFldLength = 1535, fldFlag = VisaFldFlag.BINARY_VAR2, filedDescription = "辅助交易数据（TLV格式）")
    private String auxiliaryTransactionData;

    /**
     * Field 121 – Issuing Institution Identification Code
     * variable length
     * 1 byte, binary +
     * 3-11 AN, EBCDIC; maximum: 12 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 121, dataFldLength = 11, fldFlag = VisaFldFlag.EBCDIC_VAR1, filedDescription = "发卡机构识别代码")
    private String issuingInstitutionIdCode;


    /**
     * Field 123—Verification Data
     * variable length
     * 1 byte, binary +
     * 255 bytes, variable by usage; maximum 256 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 123, dataFldLength = 29, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "验证数据"
//            fldIndex = 123, dataFldLength = 255, fldFlag = VisaFldFlag.EBCDIC_VAR1, filedDescription = "验证数据"
    )
    private String verificationData;

    private CustomerRelatedDataOld verificationDataObj;

    private VerificationDataFixedFormat verificationDataFixedFormatObj;

    /**
     * Field 125 – Supporting Information
     * variable length
     * 1 byte, binary +
     * 255 bytes, variable by usage and subfield; maximum: 256 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 125, dataFldLength = 255, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "支持信息")
    private String supportingInformation;

    private CustomerRelatedDataOld supportingInformationObj;

    /**
     * Field 126 – Visa Private-Use Fields
     * 1 byte, binary +
     * variable by field
     * minimum: 10 bytes
     * maximum: 255 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 126, dataFldLength = 255, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "VISA专用字段")
    private String visaPrivateUseFields;
    private VisaPrivateUseFields VisaPrivateUseFieldsObj;

    /**
     * Field 127 – File Record(s): Action and Data
     * variable length
     * 1 byte, binary +
     * 255 bytes, variable by subfield; maximum: 256 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 127, dataFldLength = 255, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "文件记录：操作和数据")
    private String fileRecordActionAndData;

    /**
     * ield 130 – Terminal Capability Profile
     * fixed length
     * 24 bit string; 3 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 130, dataFldLength = 24, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "终端能力配置文件")
    private String terminalCapabilityProfile;

    /**
     * Field 131 – Terminal Verification Results (TVR)
     * fixed length
     * 40 bit string; 5 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 131, dataFldLength = 40, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "终端验证结果（TVR）")
    private String terminalVerificationResults;

    /**
     * Field 132 – Unpredictable Number
     * fixed length
     * 8 hexadecimal digits; 4 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 132, dataFldLength = 8, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "不可预测的随机数")
    private String unpredictableNumber;

    /**
     * Field 133 – Reserved for Future Use
     * fixed length
     * 8 AN, EBCDIC; 8 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 133, dataFldLength = 8, fldFlag = VisaFldFlag.EBCDIC_FIXED, filedDescription = "保留供将来使用")
    private String reservedForFutureUse;

    /**
     * Field 134 – Visa Discretionary Data
     * variable length
     * 1 byte binary +
     * 255 data bytes; variable by usage and subfield;
     * maximum 256 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 134, dataFldLength = 255, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "VISA专用数据")
    private String visaDiscretionaryData;

    /**
     * Field 135 – Issuer Discretionary Data
     * variable length
     * 1 byte binary + 30 hexadecimal digits; maximum 16 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 135, dataFldLength = 31, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "发卡方专用数据")
    private String issuerDiscretionaryData;

    /**
     * Field 136 – Cryptogram
     * fixed length
     * 16 hexadecimal digits; 8 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 136, dataFldLength = 16, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "密文")
    private String cryptogram;

    /**
     * Field 137 – Application Transaction Counter
     * fixed length
     * 4 hexadecimal digits; a 2-byte binary value
     */
    @IsoVisa8583Annotation(fldIndex = 137, dataFldLength = 4, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "应用交易计数器")
    private String applicationTransactionCounter;

    /**
     * Field 138 – Application Interchange Profile
     * fixed length
     * 16 bit string; 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 138, dataFldLength = 16, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "应用交换配置文件")
    private String applicationInterchangeProfile;

    /**
     * Field 139 – ARPC Response Cryptogram and Code
     * fixed length
     * 16 hexadecimal digits + 2 AN EBCDIC; 10 bytes total
     */
    @IsoVisa8583Annotation(fldIndex = 139, dataFldLength = 10, fldFlag = VisaFldFlag.BINARY_EBCDIC_FIXED, filedDescription = "ARPC + 授权响应码")
    private String arpcResponseCryptogramAndCode;

    /**
     * Field 140 – Issuer Authentication Data
     * variable length
     * 1 byte binary +
     * 255 bytes variable by usage;
     * maximum 256 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 140, dataFldLength = 255, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "发卡方认证数据")
    private String issuerAuthenticationData;

    /**
     * Field 142 – Issuer Script
     * variable length
     * 1 byte +
     * 510 hexadecimal digits; maximum 256 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 142, dataFldLength = 510, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "发卡方脚本")
    private String issuerScript;

    /**
     * Field 143 – Issuer Script Results
     * variable length
     * 1 byte binary +
     * 40 hexadecimal digits; maximum 21 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 143, dataFldLength = 40, fldFlag = VisaFldFlag.BINARY_VAR1, filedDescription = "发卡方脚本结果")
    private String issuerScriptResults;

    /**
     * Field 144 – Cryptogram Transaction Type
     * fixed length
     * 2N, 4 bit BCD (unsigned packed); 1 byte
     */
    @IsoVisa8583Annotation(fldIndex = 144, dataFldLength = 2, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "密文交易类型")
    private String cryptogramTransactionType;

    /**
     * Field 145 – Terminal Country Code
     * fixed length
     * 3N, 4 bit BCD; 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 145, dataFldLength = 3, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "终端国家代码")
    private String terminalCountryCode;

    /**
     * Field 146 – Terminal Transaction Date
     * fixed length
     * 6N, 4 bit BCD; 3 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 146, dataFldLength = 6, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "终端交易日期")
    private String terminalTransactionDate;

    /**
     * Field 147 – Cryptogram Amount
     * fixed length
     * 12N, 4 bit BCD (unsigned packed); 6 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 147, dataFldLength = 12, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "密文金额")
    private String cryptogramAmount;

    /**
     * Field 148 – Cryptogram Currency Code
     * fixed length
     * 3N, 4 bit BCD; 2 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 148, dataFldLength = 3, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "密文货币代码")
    private String cryptogramCurrencyCode;

    /**
     * Field 149 – Cryptogram Cashback Amount
     * fixed length
     * 12N, 4 bit BCD (unsigned packed); 6 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 149, dataFldLength = 12, fldFlag = VisaFldFlag.BCD_FIXED, filedDescription = "密文现金回退金额")
    private String cryptogramCashbackAmount;

    /**
     * Field 152 – Secondary PIN Block
     * fixed length
     * 64 N, bit string; 8 bytes
     */
    @IsoVisa8583Annotation(fldIndex = 152, dataFldLength = 8, fldFlag = VisaFldFlag.BINARY_FIXED, filedDescription = "辅助PIN块")
    private String secondaryPinBlock;

}