package com.big.river.pin.block

import com.big.river.algorithm.BasicAlgorithm
import com.big.river.helper.ByteHelper

class PinBlockHelper {

    fun calculatePlaintextPIN() {
        val pinBlock = HexUtils.generatePinBlock("123456", "123456789012345678")
        println("pinBlock:$pinBlock")

        val cardNumber = "123456789012345678"
        val length = cardNumber.length
        val calculateCardNumber = cardNumber.substring(length - 13, length - 1)
        println("calculateCardNumber:$calculateCardNumber")

        val encryptPAN = byteArrayOf(
            0x00,
            0x00,
            calculateCardNumber.substring(0, 2).toInt(16).toByte(),
            calculateCardNumber.substring(2, 4).toInt(16).toByte(),
            calculateCardNumber.substring(4, 6).toInt(16).toByte(),
            calculateCardNumber.substring(6, 8).toInt(16).toByte(),
            calculateCardNumber.substring(8, 10).toInt(16).toByte(),
            calculateCardNumber.substring(10, 12).toInt(16).toByte(),
        )
        val encryptPANHexString = ByteHelper.bytes2HexString(encryptPAN)
        println("encryptPANHexString:$encryptPANHexString")

        val plaintextPINBlock = byteArrayOf(
            0x06,
            0x12, 0x34, 0x56,
            0xff.toByte(),
            0xff.toByte(),
            0xff.toByte(),
            0xff.toByte(),
        )
        // val plaintextPINBlock = ByteHelper.hexString2Bytes("60509BE2E70E3BAD")
        val plaintextPINBlockBytes = BasicAlgorithm.xor(plaintextPINBlock, encryptPAN)
        val plaintextPIN = ByteHelper.bytes2HexString(plaintextPINBlockBytes)
        println("plaintextPIN:$plaintextPIN")
    }

}