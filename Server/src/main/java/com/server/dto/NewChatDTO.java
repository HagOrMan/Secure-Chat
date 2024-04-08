package com.server.dto;

public class NewChatDTO {

    private String sender, receiver, nonce;

    public NewChatDTO(String sender, String receiver, String nonce){
        this.sender = sender; this.receiver = receiver; this.nonce = nonce;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }
}
