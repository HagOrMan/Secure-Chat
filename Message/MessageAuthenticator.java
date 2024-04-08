package Message;

import java.util.ArrayList;

public interface MessageAuthenticator {
    // TODO: `User` objects instead?
    void authenticateMessaging(ArrayList<String> users);
}
