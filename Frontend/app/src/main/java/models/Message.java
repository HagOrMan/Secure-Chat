package models;

import java.time.LocalDateTime;

public class Message {
    private LocalDateTime dateTime;
    private String message_txt;

    public Message(LocalDateTime dateTime, String message_txt) {
        this.dateTime = dateTime;
        this.message_txt = message_txt;
    }
    // Empty constructor for firebase deserialization
    public Message() {}

    public LocalDateTime getDateTime() { return dateTime; }
    public String getMessage_txt() { return message_txt; }

    @Override
    public String toString() {
        return "(@ " + dateTime + ": " + message_txt + ")";
    }

}
