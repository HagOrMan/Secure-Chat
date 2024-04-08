package Backend.Account;

import java.io.IOException;

public class AccountAuthenticator implements BaseAccountAuthenticator {

    @Override
    public boolean authenticate(String username, String password) throws IOException {
        //if database has the username and password, return true
        return URLconnector.sendGET(username, password);
    }

    public boolean addUser(String username, String password) throws IOException {
        URLconnector.sendPOST(username, password);
        return URLconnector.sendGET(username, password);
    }
    
}
