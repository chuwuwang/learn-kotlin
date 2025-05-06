import com.big.river.card.APDUAnalysis
import com.big.river.helper.ByteHelper
import com.big.river.helper.ResourceUtil
import com.big.river.tlv.TLVHelper
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

fun main(args: Array<String>) {
    val fileText = ResourceUtil.getFileText("src\\resources\\MCA-A000000615-001-241213.pca")
    val hexString = ByteHelper.bytes2HexString(fileText)
    println(hexString)
    analysisAPDU()
}


private fun analysisAPDU() {
    val apdu =
        "6F30840E325041592E5359532E4444463031A51EBF0C1B61194F08A000000333010101500A50424F432044454249548701019000"
    APDUAnalysis.getCardInfo(apdu)
}

val charset: Charset = Charset.forName("US-ASCII")

private fun parseDateString() {
    val pattern = "yyyy-MM-dd" + "'T'" + "HH:mm:ss.SSS" + "Z"

    var simpleDateFormat = SimpleDateFormat(pattern)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+0:00")
    // simpleDateFormat.format(date)
    val date = simpleDateFormat.parse("2023-12-29T13:16:11.706+0000")

    simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    // simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+4:00")
    val format = simpleDateFormat.format(date)
    println(format)
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

private fun testDate() {
    val lastTime = Date(122, 0, 31).time
    println(lastTime)
    val endTime = Date(122, 1, 31).time
    println(endTime)
    println(endTime - lastTime)
    val millis = System.currentTimeMillis()
    println(millis)
    val time = Date().time
    println(time)
}

private fun requestTimeToDate(timestamp: Long): String {
    val date = Date(timestamp)
    val locale = Locale.getDefault()
    val simpleDateFormat = SimpleDateFormat("yyyyMMddHHmm" + "ss", locale)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+0:00")
    return simpleDateFormat.format(date)
}