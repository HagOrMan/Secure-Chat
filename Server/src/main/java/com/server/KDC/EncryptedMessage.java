package com.server.KDC;

public class EncryptedMessage {
    private String cipherText;
    private byte[] iv;

    public EncryptedMessage(String cipherText, byte[] iv) {
        this.cipherText = cipherText;
        this.iv = iv;
    }

    public String getCipherText() {
        return cipherText;
    }

    public byte[] getIv() {
        return iv;
    }
}
