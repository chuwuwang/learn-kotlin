package com.big.river.visa;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsoVisa8583Annotation {

    /**
     * 域索引
     *
     * @return
     */
    int fldIndex();

    /**
     * 域字段标识
     * FIXED:固定长度；UNFIXED_2:2位变长；UNFIXED_3:3位变长
     *
     * @return
     */
    VisaFldFlag fldFlag();

    /**
     * 数据域长度
     *
     * @return
     */
    int dataFldLength();

    /**
     * 字段描述
     */
    String filedDescription();

    /**
     * 数据域字节长度
     */
    // String dataByteLength();

}