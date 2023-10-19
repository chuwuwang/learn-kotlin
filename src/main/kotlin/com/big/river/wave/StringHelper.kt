package com.big.river.wave

import com.big.river.helper.ByteHelper
import java.nio.charset.Charset

object StringHelper {

    fun removeSpecificCharacters(hexString: String): String {
        try {
            var length = 0
            val bytes = ByteHelper.hexString2Bytes(hexString)
            for (index in 0..bytes.size) {
                val byte = bytes[index]
                if (byte.toInt() == 0) {
                    length = index
                    break
                }
            }
            val charset = Charset.forName("US-ASCII")
            return String(bytes, 0, length, charset)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return ByteHelper.hexString2AsciiString(hexString)
    }

}