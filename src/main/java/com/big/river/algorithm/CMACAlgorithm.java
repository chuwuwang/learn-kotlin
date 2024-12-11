package com.big.river.algorithm;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.params.KeyParameter;

public class CMACAlgorithm {

    public static byte[] calculateAESCMAC(byte[] key, byte[] data) {
        try {
            // init CMAC
            CipherParameters keyParam = new KeyParameter(key);
            AESEngine engine = new AESEngine();
            CMac cmac = new CMac(engine);
            cmac.init(keyParam);

            // calculate data
            cmac.update(data, 0, data.length);

            // calculate mac value
            int size = cmac.getMacSize();
            byte[] macResult = new byte[size];
            cmac.doFinal(macResult, 0);

            return macResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
