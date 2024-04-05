package Backend.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Message {
    public String message;
    // TODO: Update type and camelCase name of `datetime` in `Backend.Message`
    public LocalDateTime dateTime;
    // TODO: Consider making this a reference to a `User` object?
    public String senderID;
    ArrayList<MessageReaction> reactions;

    // TODO: Refer to TODOs in `MessageController` concerning this
    public void editMessage(String oldmid, String newMessage) {

    }

//    public void addReaction(MessageReaction messageReaction) {
//
//    }
}

// Demo, requirements, what you're implementing actually, what architecture (did you actually follow?), tech stack, present demo; make sure it can run, can be compiled (JAR file back-end, APK back-end), make sure you can run it; logbook?...
