package Message;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Message {
    public String message;
    // TODO: Update type and camelCase name of `datetime` in `Message`
    public LocalDateTime dateTime;
    // TODO: Consider making this a reference to a `User` object?
    public String senderID;
    ArrayList<MessageReaction> reactions;

    // TODO: Refer to TODOs in `MessageController` concerning this
    public void editMessage(String newMessage) {

    }

    public void addReaction(MessageReaction messageReaction) {

    }
}
