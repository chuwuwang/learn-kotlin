package com.payby.tool

import com.big.river.algorithm.RSAAlgorithm
import com.big.river.helper.ByteHelper
import com.google.gson.Gson
import java.util.*

class POSRequestHelper {

    fun request() {
        val publicKey = ""
        val privateKey = ""
        val publicKeyBytes = publicKey.toByteArray()

        val header = mutableMapOf<String, String>()
        header["service"] = "active"
        header["serviceVersion"] = "2.0"
        header["requestTime"] = "2024-01-10T01:59:57.968-12:00"

        val body = ActiveReq()
        body.code = "EKrgTL0kPkkUni7Ng0b/QA=="
        body.posDeviceType = "CUSTOMIZED_V" + "POS"
        body.pubKey = Base64.getEncoder().encodeToString(publicKeyBytes)

        val content = mutableMapOf<String, Any>()
        content["body"] = body
        content["header"] = header

        val contentString = Gson().toJson(content)
        val signBytes = RSAAlgorithm.signByPrivateKey(contentString, privateKey)
        val signHexString = ByteHelper.bytes2HexString(signBytes)
        println(signHexString)
    }

    private fun formatRSA(pubKey: String): String {
        val sb = StringBuffer()
        sb.append("-----BEGIN PUBLIC KEY-----\n")
        var i = 0
        while (i < pubKey.length) {
            if (pubKey.length - i > 64) {
                val string = pubKey.substring(i, i + 64)
                sb.append(string).append("\n")
            } else {
                val string = pubKey.substring(i)
                sb.append(string).append("\n")
            }
            i += 64
        }
        sb.append("-----END PUBLIC KEY-----\n")
        return sb.toString()
    }

}