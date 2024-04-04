package Backend.Account;

abstract class LogInOut {
    AccountAuthenticator auth;
    abstract void login();
    abstract void logout();
    abstract void signup();
}
