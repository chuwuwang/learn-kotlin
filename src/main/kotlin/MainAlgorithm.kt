import com.big.river.algorithm.BCTR31KeyAlgorithm
import com.big.river.algorithm.BasicAlgorithm
import com.big.river.algorithm.CMACAlgorithm
import com.big.river.algorithm.TripleDESAlgorithm
import com.big.river.helper.ByteHelper

fun main(args: Array<String>) {

    testTR31ByBC()

}

fun testTR31ByBC() {
    val kek = ByteHelper.hexString2Bytes("D18965EDC9522A90C17BEB49F9705DE7E5738A126559D289C78241CB099227B7")
    // val encryptedKey = ByteHelper.hexString2Bytes("D0144K0AB00S0000A9FAE698617ECF056256A1554095342A38510F3EEF5B1BC8D4596720A128CE785CFFBA0138180DBF329D80A1BAE95A0F84078FE8CE2D305F0C0ECFAAA80789B4")
    val encryptedKey = "D0144K0AB00S0000A9FAE698617ECF056256A1554095342A38510F3EEF5B1BC8D4596720A128CE785CFFBA0138180DBF329D80A1BAE95A0F84078FE8CE2D305F0C0ECFAAA80789B4"
    val bytes = BCTR31KeyAlgorithm.parseKeyBlock(encryptedKey, kek, "AES")
    val hexString = ByteHelper.bytes2HexString(bytes)
    println("key ---> $hexString")
}

fun testCMAC() {
    val zero = ByteHelper.hexString2Bytes("00000000000000000000000000000000")
    val arg1 = ByteHelper.hexString2Bytes("9E7402DC7C25384EAA9C8A62D921EF95B28BC8EB1F04AB89D54AFF41290138BF")
    var kcv = CMACAlgorithm.calculateAESCMAC(arg1, zero)
    var hexString = ByteHelper.bytes2HexString(kcv)
    println("arg1 kcv ---> $hexString")

    val arg2 = ByteHelper.hexString2Bytes("69F250829445B4FD70FF190DD578DD171C58D77AD48BC85B58FF64E2D22B2C28")
    kcv = CMACAlgorithm.calculateAESCMAC(arg2, zero)
    hexString = ByteHelper.bytes2HexString(kcv)
    println("arg2 kcv ---> $hexString")

    val arg3 = ByteHelper.hexString2Bytes("260F37B32132A6231B187826F5296F654BA09583AED6B15B4A37DA68F2B83320")
    kcv = CMACAlgorithm.calculateAESCMAC(arg3, zero)
    hexString = ByteHelper.bytes2HexString(kcv)
    println("arg3 kcv ---> $hexString")

    var bytes = BasicAlgorithm.xor(arg1, arg2)
    bytes = BasicAlgorithm.xor(bytes, arg3)
    hexString = ByteHelper.bytes2HexString(bytes)
    println("KBPK ---> $hexString")

    kcv = CMACAlgorithm.calculateAESCMAC(bytes, zero)
    hexString = ByteHelper.bytes2HexString(kcv)
    println("final kcv ---> $hexString")
}

private fun test3DES() {
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
