package Backend.Account;

import java.io.IOException;

public interface BaseAccountAuthenticator {
    boolean authenticate( String username, String password) throws IOException;
}
