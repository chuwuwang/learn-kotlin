package com.big.river.card

object APDUCardHelper {

    fun getCardInfo(apdu: String): CardInfo ? {
        val map = APDUHelper.parserResponseAPDU(apdu) ?: return null
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
        return null
    }

}
