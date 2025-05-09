package com.big.river.tlv

import com.big.river.tlv.TLVHelper.getLength
import com.big.river.tlv.TLVHelper.getTag

object TLVBusiness {

    fun builderMapByResponseDE55(hexString: String): Map< String, List<TLV> > {
        var position = 0
        val hexStringUpper = hexString.uppercase()
        val value71List: ArrayList<TLV> = ArrayList()
        val value72List: ArrayList<TLV> = ArrayList()
        val value91List: ArrayList<TLV> = ArrayList()
        val map: HashMap< String, List<TLV> > = HashMap()
        try {
            while (hexStringUpper.length > position) {
                // get tag
                val tag = TLVHelper.getTag(hexString = hexStringUpper, position = position)
                if (tag.isBlank() || tag == "00") {
                    break
                }

                position += tag.length

                // get length
                val pair = TLVHelper.getLength(hexStringUpper, position)
                val length = pair.first

                position += pair.second

                // get value
                val value = hexStringUpper.substring(position, position + length * 2)

                position += value.length

                if ("71" == tag) {
                    val tlv = TLV("71", length, value)
                    value71List.add(tlv)
                }
                if ("72" == tag) {
                    val tlv = TLV("72", length, value)
                    value72List.add(tlv)
                }
                if ("91" == tag) {
                    val tlv = TLV("91", length, value)
                    value91List.add(tlv)
                }
                println("$tag : $value")
            }
            map["71"] = value71List
            map["72"] = value72List
            map["91"] = value91List
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return map
    }

    fun builderListByNonValue(hexString: String): List<TLV> {
        var position = 0
        val list = ArrayList<TLV>()
        val hexStringUpper = hexString.uppercase()
        try {
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

                val tlv = TLV(tag, length, "")
                list.add(tlv)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return list
    }

}