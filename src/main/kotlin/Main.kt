import com.big.river.helper.ByteHelper
import com.big.river.helper.ResourceUtil
import java.text.SimpleDateFormat
import java.util.*

fun main(args: Array<String>) {
    val fileText = ResourceUtil.getFileText("src\\resources\\MCA-A000000615-001-241213.pca")
    val hexString = ByteHelper.bytes2HexString(fileText)
    println(hexString)
}

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