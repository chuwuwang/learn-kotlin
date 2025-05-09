package com.big.river.card

import com.big.river.tlv.TLV
import com.big.river.tlv.TLVBusiness
import com.big.river.tlv.TLVHelper

object APDUTransactionHelper {

    fun getTransactionTagList(apdu: String): List<TLV> ? {
        val map = APDUHelper.parserResponseAPDU(apdu) ?: return null
        val tag9F38 = getTag9F38(map)
        if (tag9F38 != null) return tag9F38
        val tag8C = getTag8C(map)
        if (tag8C != null) return tag8C
        return null
    }

    private fun getTag9F38(map: Map<String, TLV>): List<TLV> ? {
        // get P D O L for 9F38, FCl Proprietary Template
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

    private fun getTag8C(map: Map<String, TLV>): List<TLV> ? {
        // for mastercard, 8C 是 Card Risk Management Data Object List 1（ C D O L1）
        val tlv = map["8C"]
        if (tlv == null || tlv.value.length <= 0) return null
        try {
            val list = TLVBusiness.builderListByNonValue(tlv.value)
            println("8C: $list")
            return list
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    fun builderTransactionTagMap(apdu: String, list: List<TLV>): Map<String, TLV> ? {
        val tag83 = getTag83(apdu, list)
        if (tag83 != null) return tag83
        val mastercard = getGACByMastercard(apdu, list)
        if (mastercard != null) return mastercard
        return null
    }

    private fun getTag83(apdu: String, list: List<TLV>): Map<String, TLV> ? {
        // GPO Response Template Format 1 中的 AIP（Application Interchange Profile） 和 AFL（Application File Locator）
        val map = APDUHelper.parserCommandAPDU(apdu) ?: return null
        val tlv = map["83"]
        if (tlv == null || tlv.value.length <= 0) return null
        return merge(tlv.value, list)
    }

    private fun getGACByMastercard(apdu: String, list: List<TLV>): Map<String, TLV> ? {
        // 80 AE — GENERATE AC（Generate Application Cryptogram）
        val bool = apdu.startsWith("80AE")
        if (bool == false) return null
        val data = APDUHelper.getCommandAPDUData(apdu) ?: return null
        return merge(data, list)
    }

    private fun merge(data: String, list: List<TLV>): Map<String, TLV>? {
        try {
            var string = data
            val map = HashMap<String, TLV>()
            for (i in list.indices) {
                val item = list[i]
                val value = string.substring(0, item.length * 2)
                item.value = value
                map[item.tag] = item
                string = string.substring(item.length * 2)
            }
            println("Transaction: $map")
            return map
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }

}
