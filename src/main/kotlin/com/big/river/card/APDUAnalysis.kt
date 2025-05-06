package com.big.river.card

import com.big.river.helper.ByteHelper
import com.big.river.tlv.TLVHelper
import net.sf.scuba.smartcards.ResponseAPDU

object APDUAnalysis {

    fun getCardInfo(apdu: String): CardInfo ? {
        try {
            val bytes = ByteHelper.hexString2Bytes(apdu)
            val commandAPDU = ResponseAPDU(bytes)
            val dataBytes = commandAPDU.data
            var map = TLVHelper.builderMap(dataBytes)
            val entry = map.entries.iterator().next()
            val value = entry.value
            map = TLVHelper.builderMap(value.value)

            var cardInfo: CardInfo ? = null
            var tlv = map["57"]
            if (tlv != null && tlv.value.length > 0) {
                cardInfo = TrackHelper.parseTrack2(tlv.value)
            } else {
                tlv = map["5A"]
                if (tlv != null && tlv.value.length > 0) {
                    cardInfo = CardInfo()
                    var value = tlv.value.uppercase()
                    value = TrackHelper.filterTrack(value)
                    cardInfo.cardNumber = value
                }
                tlv = map["5F24"]
                if (cardInfo != null && tlv != null && tlv.value.length > 0) {
                    val value = tlv.value.uppercase()
                    if (value.length > 4) {
                        cardInfo.expireDate = value.substring(0, 4)
                    } else {
                        cardInfo.expireDate = value
                    }
                }
                tlv = map["5F34"]
                if (cardInfo != null && tlv != null && tlv.value.length > 0) {
                    val value = tlv.value.uppercase()
                    cardInfo.serialNumber = value
                }
            }
            if (cardInfo != null && cardInfo.expireDate != null && cardInfo.expireDate.length >= 4) {
                val value = cardInfo.expireDate
                cardInfo.expireDate = value.substring(2, 4) + "/" + value.substring(0, 2)
            }
            println("cardInfo: $cardInfo")
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }

}
