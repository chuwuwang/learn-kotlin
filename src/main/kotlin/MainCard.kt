import com.big.river.card.APDUAnalysis
import com.big.river.helper.ByteHelper
import com.big.river.tlv.TLVHelper
import java.nio.charset.Charset
import java.util.*

val charset: Charset = Charset.forName("US-ASCII")

fun main(args: Array<String>) {

}

private fun analysisAPDU() {
    val apdu =
        "6F30840E325041592E5359532E4444463031A51EBF0C1B61194F08A000000333010101500A50424F432044454249548701019000"
    APDUAnalysis.getCardInfo(apdu)
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