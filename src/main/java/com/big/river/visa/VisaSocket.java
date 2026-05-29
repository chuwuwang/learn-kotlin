package com.big.river.visa;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class VisaSocket {

    /**
     * @return : byte[]
     * @description : 发送请求
     * @author : scott
     * @date : 2025/6/25 - 18:21
     * @params : [config, requestBytes]
     */
    private static byte[] sendVisaNetSocketRequest(VisaNetClientConfig config, byte[] iso8583BodyBytes) throws Exception {
        Socket socket = null;
        try {
            // 是否使用证书交互
            if (config.isUseSSL()) {
                SSLSocketFactory sslFactory = createSSLSocketFactory(config);
                log.debug("Connecting to {}:{} using SSL", config.getHost(), config.getPort());
                socket = sslFactory.createSocket(config.getHost(), config.getPort());
                ((SSLSocket) socket).startHandshake();
                log.debug("SSL handshake success");
            } else {
                log.debug("Connecting to {}:{} using plain socket", config.getHost(), config.getPort());
                socket = new Socket(config.getHost(), config.getPort());
                log.debug("Plain socket connected");
            }
            socket.setSoTimeout(config.getTimeoutMillis() * 1000);

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            // === ✅ 添加VMLH Visa framing header === 4个字节：00 3E（长度标识） 00 00（保留位）
            int bodyLen = iso8583BodyBytes.length;
            ByteBuffer sendBuffer = ByteBuffer.allocate(4 + bodyLen);
            sendBuffer.putShort((short) bodyLen);  // 报文长度（不包含 framing 自身）
            sendBuffer.putShort((short) 0);        // 保留字段（默认 0x0000）

            sendBuffer.put(iso8583BodyBytes);
            byte[] finalRequest = sendBuffer.array();
            log.debug("✅开始发送VisaNet网络包: {}", toHex(finalRequest));

            out.write(finalRequest);
            out.flush();

            byte[] buffer = new byte[4096];
            int read = in.read(buffer);
            if (read == -1) {
                throw new IOException("No response from Visa");
            }

            byte[] rawResponse = Arrays.copyOf(buffer, read);
            log.debug("✅成功收到VisaNet原始响应: {}", toHex(rawResponse));

            // === ✅ 去掉前导 header（前4字节） ===
            if (rawResponse.length < 4) {
                throw new IOException("VisaNet response too short");
            }
            byte[] iso8583Response = Arrays.copyOfRange(rawResponse, 4, rawResponse.length);
            return iso8583Response;

        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

}
