package com.big.river.tlv

import com.big.river.helper.ByteHelper

object TLVHelper {

    fun builderMap(bytes: ByteArray): Map<String, TLV> {
        val hexString = ByteHelper.bytes2HexString(bytes)
        return builderMap(hexString)
    }

    fun builderMap(hexString: String): Map<String, TLV> {
        var position = 0
        val map: HashMap<String, TLV> = HashMap()
        val hexStringUpper = hexString.uppercase()
        while (hexStringUpper.length > position) {
            // get tag
            val tag = getTag(hexString = hexStringUpper, position = position)
            if (tag.isBlank() || tag == "00") {
                break
            }

            position += tag.length

            // get length
            val pair = getLength(hexStringUpper, position)
            val length = pair.first

            position += pair.second

            // get value
            val value = hexStringUpper.substring(position, position + length * 2)

            position += value.length

            val tlv = TLV(tag, length, value)
            map[tag] = tlv

            println("$tag : $value")
        }
        println("===========================TLV==================================")
        return map
    }

    fun tlv2HexString(tlv: TLV): String {
        val builder = StringBuilder()
        val length: String = length2HexString(tlv.length)
        builder.append(tlv.tag)
        builder.append(length)
        builder.append(tlv.value)
        return builder.toString()
    }

    fun tlv2Bytes(tlv: TLV): ByteArray {
        val hexString = tlv2HexString(tlv)
        return ByteHelper.hexString2Bytes(hexString)
    }

    /**
     * 取子域tag标签, tag标签不仅包含1个字节, 2个字节, 还包含3个字节
     */
    fun getTag(hexString: String, position: Int): String {
        val tag: String

        val tagString = hexString.substring(position, position + 2)
        val tagValue = tagString.toInt(16)

        val tag2String = hexString.substring(position + 2, position + 4)
        val tag2Value = tag2String.toInt(16)

        // b5~b1如果全为1, 则说明这个tag下面还有一个子字节, emv里的tag最多占两个字节
        var temp = tagValue and 0x1F
        if (temp == 0x1F) {
            temp = tag2Value and 0x80
            tag = if (temp == 0x80) {
                // 除tag标签首字节外, tag中其他字节最高位为:1-表示后续还有字节, 0-表示为最后一个字节
                hexString.substring(position, position + 6) // 3bytes的tag
            } else {
                hexString.substring(position, position + 4) // 2bytes的tag
            }
        } else {
            tag = hexString.substring(position, position + 2)
        }
        return tag
    }

    /**
     * length域的编码比较简单, 最多有四个字节
     * 如果第一个字节的最高位b8为0, 则b7~b1的值就是value域的长度
     * 如果b8为1, b7~b1的值指示了下面有几个子字节, 下面子字节的值就是value域的长度
     */
    fun getLength(hexString: String, position: Int): Pair<Int, Int> {
        var size: Int

        var lengthString = hexString.substring(position, position + 2)
        val lengthValue = lengthString.toInt(16)

        val temp = lengthValue and 0x80
        if (temp == 0) {
            size = 2
        } else {
            size = 2
            val value = lengthValue and 0x7F // 127的二进制0111 1111
            lengthString = hexString.substring(position + size, position + size + value * 2)
            size += value * 2
        }
        val length = lengthString.toInt(16)
        return Pair(length, size)
    }

    private fun length2HexString(length: Int): String {
        if (length < 0) {
            throw RuntimeException("不符要求的长度")
        }
        return when {
            length <= 0X7F -> String.format("%02x", length)
            length <= 0XFF -> "81" + String.format("%02x", length)
            length <= 0XFFFF -> "82" + String.format("%04x", length)
            length <= 0XFFFFFF -> "83" + String.format("%06x", length)
            else -> throw RuntimeException("TLV 长度最多4个字节")
        }
    }

}