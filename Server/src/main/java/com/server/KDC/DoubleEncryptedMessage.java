package com.server.KDC;

public class DoubleEncryptedMessage {

    private String cipherText;
    private byte[] iv1, iv2;

    public DoubleEncryptedMessage(String cipherText, byte[] iv1, byte[] iv2) {
        this.cipherText = cipherText;
        this.iv1 = iv1; this.iv2 = iv2;
    }

    public String getCipherText() {
        return cipherText;
    }

    public byte[] getIv1() {
        return iv1;
    }

    public byte[] getIv2() {
        return iv2;
    }
}
