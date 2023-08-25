import com.big.river.algorithm.BasicAlgorithm
import com.big.river.algorithm.TripleDESAlgorithm
import com.big.river.helper.ByteHelper
import java.text.SimpleDateFormat
import java.util.*

fun main(args: Array<String>) {
    val data = "CJCP9OChMRDQ/eHhoTEgATI6IjgqMTMwKjg0NCo3NjUqMCo4OTI3MDc2OTkxNDU2MDYzNDU4MjM5MDAxOTAwMDAxMDAqMSo1RiUyMw=="
    val bytes = Base64.getDecoder().decode(data)
    val string = String(bytes)
    println(string)
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