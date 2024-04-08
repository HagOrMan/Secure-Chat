package Backend.Account;

import java.io.IOException;

abstract class LogInOut {
    AccountAuthenticator auth;
    abstract void login() throws IOException;
    abstract void logout() ;
    abstract void signup() throws IOException;
}
