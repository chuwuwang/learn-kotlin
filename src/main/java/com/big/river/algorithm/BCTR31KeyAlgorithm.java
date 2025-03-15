package com.big.river.algorithm;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;

import java.util.Base64;

public class BCTR31KeyAlgorithm {

    private static final String HEADER_TEMPLATE = "D0144K0A";

    /**
     * Parse TR-31 Key Block
     */
    public static byte[] parseKeyBlock(String keyBlock, byte[] kek, String algorithm) throws Exception {
        String header = keyBlock.substring(0, 8); // First 8 bytes for header
        String encryptedKeyBase64 = keyBlock.substring(8, keyBlock.length() - 6);
        byte[] encryptedKey = Base64.getDecoder().decode(encryptedKeyBase64);
        return decrypt(encryptedKey, kek, algorithm);
    }

    /**
     * Decrypt key using Bouncy Castle
     */
    public static byte[] decrypt(byte[] encryptedKey, byte[] kek, String algorithm) throws Exception {
        return processCipher(encryptedKey, kek, algorithm, false);
    }

    /**
     * Process encryption or decryption using Bouncy Castle
     */
    private static byte[] processCipher(byte[] input, byte[] kek, String algorithm, boolean forEncryption) throws Exception {
        PaddedBufferedBlockCipher cipher = getBlockCipher(algorithm);
        CipherParameters params = new KeyParameter(kek);
        cipher.init(forEncryption, params);

        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int len = cipher.processBytes(input, 0, input.length, output, 0);
        len += cipher.doFinal(output, len);

        byte[] result = new byte[len];
        System.arraycopy(output, 0, result, 0, len);
        return result;
    }

    private static PaddedBufferedBlockCipher getBlockCipher(String algorithm) {
        PaddedBufferedBlockCipher cipher;
        boolean aes = algorithm.equalsIgnoreCase("AES");
        boolean des = algorithm.equalsIgnoreCase("DES");
        if (aes) {
            AESEngine engine = new AESEngine();
            cipher = new PaddedBufferedBlockCipher(engine);
        } else if (des) {
            DESEngine engine = new DESEngine();
            cipher = new PaddedBufferedBlockCipher(engine);
        } else {
            throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        }
        return cipher;
    }

}
