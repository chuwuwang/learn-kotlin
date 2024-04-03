import com.big.river.algorithm.BasicAlgorithm
import com.big.river.algorithm.TripleDESAlgorithm
import com.big.river.ecr.AssemblyPackHelper
import com.big.river.helper.ByteHelper
import com.big.river.hlb.SaleReq
import com.big.river.hlb.VoidReq
import com.big.river.tlv.TLVHelper
import com.google.gson.Gson
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

fun main(args: Array<String>) {

    parserBase64Data("PC9FbnZscGREYXRhPjwvTmNycHRkUElOQmxjaz48UElORnJtdD5JU08wPC9QSU5Gcm10PjwvQ3JkaGxkck9uTGluZVBJTj48L0F1dGhudGNuPjxObT4AADwvTm0+")
    parserBase64Data("PC9DYXJkPjxDcmRobGRyPjxObT4gIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADwvTm0+")
    // PinBlockHelper().calculatePlaintextPIN()

    var bytes = "<Nm>".toByteArray(charset = charset)
    var hexString = ByteHelper.bytes2HexString(bytes)
    println(hexString)

    bytes = "</Nm>".toByteArray(charset = charset)
    hexString = ByteHelper.bytes2HexString(bytes)
    println(hexString)

    var value = ByteHelper.hexString2AsciiString("0000")
    println(value)

    value = ByteHelper.hexString2AsciiString("2020000000000000000000000000000000000000000000000000")
    println(value)

    value = ByteHelper.hexString2GBKString("2020000000000000000000000000000000000000000000000000")
    println(value)

    println("-----------------")
    val invisibleCharacters = ByteHelper.removeInvisibleCharacters(value)
    println(invisibleCharacters)
    println("-----------------")

    val saleReq = SaleReq()
    saleReq.transType = 0
    saleReq.transId = "123456789"
    saleReq.paymentType = 0
    saleReq.amount = 1
    var jsonString = Gson().toJson(saleReq)
    bytes = jsonString.toByteArray()
    value = ByteHelper.bytes2HexString(bytes)
    var assembly = AssemblyPackHelper().assembly(value)
    println("Sale ---> $assembly")

    val voidReq = VoidReq()
    voidReq.transType = 1
    voidReq.transId = "123456789"
    voidReq.oriVoucherNo = "000005"
    jsonString = Gson().toJson(voidReq)
    bytes = jsonString.toByteArray()
    value = ByteHelper.bytes2HexString(bytes)
    assembly = AssemblyPackHelper().assembly(value)
    println("Void ---> $assembly")
    println("-----------------")

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

private fun testEncrypt() {
    val bytes = ByteHelper.hexString2Bytes("111111111111111111111111111111111111111111111111")
    val arg = ByteHelper.hexString2Bytes("C1293E2C4A2F4073162CD0C2A8D5C8529D200BFD327CF48C")
    val resultBytes = BasicAlgorithm.xor(bytes, arg)
    val hexString = ByteHelper.bytes2HexString(resultBytes)
    println(hexString)

    val dataBytes = ByteHelper.hexString2Bytes("000000000000000000000000000000000000000000000000")
    val checkValueBytes = TripleDESAlgorithm.encrypt3DesECB(arg, dataBytes)
    val checkValue = ByteHelper.bytes2HexString(checkValueBytes)
    println(checkValue)
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