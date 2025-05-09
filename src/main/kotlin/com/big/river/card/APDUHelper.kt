package com.big.river.card

import com.big.river.helper.ByteHelper
import com.big.river.tlv.TLV
import com.big.river.tlv.TLVHelper
import net.sf.scuba.smartcards.CommandAPDU
import net.sf.scuba.smartcards.ResponseAPDU

object APDUHelper {

    fun parserCommandAPDU(apdu: String): Map<String, TLV> ? {
        try {
            val bytes = ByteHelper.hexString2Bytes(apdu)
            val command = CommandAPDU(bytes)
            val dataBytes = command.data
            return TLVHelper.builderMap(dataBytes)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }

    fun parserResponseAPDU(apdu: String): Map<String, TLV> ? {
        try {
            val bytes = ByteHelper.hexString2Bytes(apdu)
            val command = ResponseAPDU(bytes)
            val dataBytes = command.data
            val map = TLVHelper.builderMap(dataBytes)

            val entry = map.entries.iterator().next()
            val value = entry.value
            return TLVHelper.builderMap(value.value)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }

}
