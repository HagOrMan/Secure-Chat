package com.server.dto;

public class NewChatDTO {

    private String sender, receiver;

    public NewChatDTO(String sender, String receiver){
        this.sender = sender; this.receiver = receiver;
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
}
