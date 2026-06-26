package com.payermax.tool

import com.big.river.algorithm.BasicAlgorithm
import com.big.river.helper.ByteHelper
import java.security.SecureRandom
import kotlin.math.max

class ECRWorkKeyFacade {

    fun sample() {
        var terminalSN = "P359256QJ0271"
        var randomString = randomString()
        var appId = randomString(24)
        var timeMillis = System.currentTimeMillis().toString()
        println("appId: $appId")
        println("timeMillis: $timeMillis")
        println("randomString: $randomString")

        appId = rightPadZero(appId, 32)
        timeMillis = rightPadZero(timeMillis, 32)
        terminalSN = rightPadZero(terminalSN, 32)
        println("appId: $appId")
        println("timeMillis: $timeMillis")
        println("terminalSN: $terminalSN")


        val appIdByteArray = appId.toByteArray()
        var hexString = ByteHelper.bytes2HexString(appIdByteArray)
        println("appIdByteArray: $hexString")

        val timeMillisByteArray = timeMillis.toByteArray()
        hexString=  ByteHelper.bytes2HexString(timeMillisByteArray)
        println("timeMillisByteArray: $hexString")

        var xorByteArray = BasicAlgorithm.xor(timeMillisByteArray, appIdByteArray)
        hexString=  ByteHelper.bytes2HexString(xorByteArray)
        println("xor1: $hexString")

        val terminalSNByteArray = terminalSN.toByteArray()
        hexString=  ByteHelper.bytes2HexString(terminalSNByteArray)
        println("terminalSNByteArray: $hexString")

        xorByteArray = BasicAlgorithm.xor(xorByteArray, terminalSNByteArray)
        hexString=  ByteHelper.bytes2HexString(xorByteArray)
        println("xor2: $hexString")

        val randomStringByteArray = randomString.toByteArray()
        hexString=  ByteHelper.bytes2HexString(randomStringByteArray)
        println("randomStringByteArray: $hexString")

        xorByteArray = BasicAlgorithm.xor(xorByteArray, randomStringByteArray)
        hexString=  ByteHelper.bytes2HexString(xorByteArray)
        println("xor3: $hexString")

    }

    private val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"

    private val random = SecureRandom()
    fun randomString(length: Int = 32): String {
        return buildString(length) {
            repeat(length) {
                append(chars[random.nextInt(chars.length)])
            }
        }
    }


    fun rightPadZero(str: String, length: Int): String {
        val max = max(0, length - str.length)
        return str + "0".repeat(max)
    }

}