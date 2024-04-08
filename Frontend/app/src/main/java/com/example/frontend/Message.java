package com.example.frontend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String dateTime;
    private String message_txt;

    public Message(String dateTime, String message_txt) {
        this.dateTime = dateTime;
        this.message_txt = message_txt;
    }
    // Empty constructor for firebase deserialization
    public Message() {}

    public String getDateTime() { return dateTime; }
    public String getMessage_txt() { return message_txt; }
    public LocalDateTime DateTimeDateObj() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);
    }

    @Override
    public String toString() {
        return "(@ " + dateTime + ": " + message_txt + ")";
    }

}