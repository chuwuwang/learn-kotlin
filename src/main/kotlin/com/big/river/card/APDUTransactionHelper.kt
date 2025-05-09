package com.big.river.card

import com.big.river.tlv.TLV
import com.big.river.tlv.TLVBusiness
import com.big.river.tlv.TLVHelper

object APDUTransactionHelper {

    fun getTransactionTagList(apdu: String): List<TLV> ? {
        var map = APDUHelper.parserResponseAPDU(apdu) ?: return null
        // get P D O L for 9F38
        val tag9F38 = getTag9F38(map)
        if (tag9F38 != null) return tag9F38
        return null
    }

    private fun getTag9F38(map: Map<String, TLV>): List<TLV> ? {
        // get FCl Proprietary Template
        var tlv = map["A5"]
        if (tlv == null || tlv.value.length <= 0) return null
        try {
            val map2nd = TLVHelper.builderMap(tlv.value)
            tlv = map2nd["9F38"]
            if (tlv == null || tlv.value.length <= 0) return null
            val list = TLVBusiness.builderListByNonValue(tlv.value)
            println("9F38: $list")
            return list
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    fun builderTransactionTagMap(apdu: String, list: List<TLV>): Map<String, TLV> ? {
        val map = APDUHelper.parserCommandAPDU(apdu) ?: return null
        // GPO Response Template Format 1 中的 AIP（Application Interchange Profile） 和 AFL（Application File Locator）
        val tag83 = getTag83(map, list)
        if (tag83 != null) return tag83
        return null
    }

    private fun getTag83(map: Map<String, TLV>, list: List<TLV>): Map<String, TLV> ? {
        val tlv = map["83"]
        if (tlv == null || tlv.value.length <= 0) return null
        try {
            var data = tlv.value
            val mapResult = HashMap<String, TLV>()
            for (i in list.indices) {
                val item = list[i]
                val value = data.substring(0, item.length * 2)
                item.value = value
                mapResult[item.tag] = item
                data = data.substring(item.length * 2)
            }
            println("83: $mapResult")
            return mapResult
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }

}
