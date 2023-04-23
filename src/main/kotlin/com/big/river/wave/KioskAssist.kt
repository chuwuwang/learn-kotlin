package com.big.river.wave

import com.big.river.helper.ByteHelper

object KioskAssist {

    fun parse() {
        var command = "0200967B226170704964223A22636F6D2E666C6173682E74657374222C2261707054797065223A223031222C227472616E7354797065223A223030222C22616D6F756E74223A352C226F726465724964223A225031353735353432363539393337227D03E0"
        command = command.substring(6, command.length - 4)
        val bytes = ByteHelper.hexString2Bytes(command)
        val string = String(bytes)
        println(string)
    }

}