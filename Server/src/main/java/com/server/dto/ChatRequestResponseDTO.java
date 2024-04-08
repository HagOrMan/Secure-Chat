package com.server.dto;

import com.server.KDC.DoubleEncryptedMessage;
import com.server.KDC.EncryptedMessage;

public class ChatRequestResponseDTO {

    private EncryptedMessage nonce, senderSessionKey, receiver;
    private DoubleEncryptedMessage receiverSessionKey, senderEncryptedForReceiver;

    public ChatRequestResponseDTO(EncryptedMessage nonce, EncryptedMessage senderSessionKey, EncryptedMessage receiver,
                                  DoubleEncryptedMessage receiverSessionKey, DoubleEncryptedMessage senderEncryptedForReceiver){
        this.nonce = nonce; this.receiver = receiver; this.senderEncryptedForReceiver = senderEncryptedForReceiver;
        this.receiverSessionKey = receiverSessionKey; this.senderSessionKey = senderSessionKey;
    }

    public void setNonce(EncryptedMessage nonce) {
        this.nonce = nonce;
    }

    public EncryptedMessage getNonce() {
        return nonce;
    }

    public EncryptedMessage getReceiver() {
        return receiver;
    }

    public DoubleEncryptedMessage getReceiverSessionKey() {
        return receiverSessionKey;
    }

    public DoubleEncryptedMessage getSenderEncryptedForReceiver() {
        return senderEncryptedForReceiver;
    }

    public EncryptedMessage getSenderSessionKey() {
        return senderSessionKey;
    }

    public void setReceiver(EncryptedMessage receiver) {
        this.receiver = receiver;
    }

    public void setReceiverSessionKey(DoubleEncryptedMessage receiverSessionKey) {
        this.receiverSessionKey = receiverSessionKey;
    }

    public void setSenderEncryptedForReceiver(DoubleEncryptedMessage senderEncryptedForReceiver) {
        this.senderEncryptedForReceiver = senderEncryptedForReceiver;
    }

    public void setSenderSessionKey(EncryptedMessage senderSessionKey) {
        this.senderSessionKey = senderSessionKey;
    }
}
