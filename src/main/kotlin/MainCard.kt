import com.big.river.card.APDUCardHelper
import com.big.river.card.APDUHelper
import com.big.river.card.APDUTransactionHelper
import com.big.river.helper.ByteHelper
import com.big.river.tlv.TLVHelper
import java.nio.charset.Charset
import java.util.*

val charset: Charset = Charset.forName("US-ASCII")

fun main(args: Array<String>) {
    analysisAPDU()
}

private fun analysisAPDU() {
    var apdu =
        "6F30840E325041592E5359532E4444463031A51EBF0C1B61194F08A000000333010101500A50424F432044454249548701019000"
    APDUCardHelper.getCardInfo(apdu)

    apdu =
        "6F568407A0000000031010A54B500A566973612044656269748701019F38189F66049F02069F03069F1A0295055F2A029A039C019F37045F2D02656E9F120A566973612044656269749F110101BF0C089F5A0560078407849000"
    var tagList = APDUTransactionHelper.getTransactionTagList(apdu) ?: emptyList()
    apdu =
        "80A8000023832126800080000000000200000000000000045800000000000458250503008288892900"
    APDUTransactionHelper.builderTransactionTagMap(apdu, tagList)

    apdu =
        "6F368408A000000333010101A52A500A50424F432044454249548701019F38189F66049F02069F03069F1A0295055F2A029A039C019F37049000"
    tagList = APDUTransactionHelper.getTransactionTagList(apdu) ?: emptyList()
    apdu =
        "80A8000023832126800080000000000150000000000000045800000000000458250504009747A22D00"
    APDUTransactionHelper.builderTransactionTagMap(apdu, tagList)
}

private fun parserIccData() {
    val data =
        "ggJ8AIQIoAAAAzMBAQGVBQAAAAAAmgMkASOcAQBfKgIHhF80AQCfAgYAAAAAAAGfAwYAAAAAAACfCQIAMJ8QFgcBAQOgAAABDaE5mQFnICQBIxUpGICfGgIHhJ8eCDAwMDAwOTA1nyYIT2uNQBl751OfJwGAnzMD4PjInzUBIp82AgABnzcEAKopug=="
    val bytes = Base64.getDecoder().decode(data)
    val hexString = ByteHelper.bytes2HexString(bytes)
    println(hexString)
    TLVHelper.builderMap(hexString)
}

private fun parserBase64Data(data: String) {
    val bytes = Base64.getDecoder().decode(data)
    val string = String(bytes)
    println(string)
    val hexString = ByteHelper.bytes2HexString(bytes)
    println(hexString)
}