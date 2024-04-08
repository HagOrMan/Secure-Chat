package Backend.Account;

import java.io.IOException;

// Backend.AccountController.java
public class AccountController {
    public void login() throws IOException {
        // Logic to handle login
        LogInOut logInOut = new AccountLogInOut();
        logInOut.login();
    }

    public void logout() {
        // Logic to handle logout
        LogInOut logInOut = new AccountLogInOut();
        logInOut.logout();
    }

    public void signup() throws IOException {
        // Logic to handle signup
        LogInOut logInOut = new AccountLogInOut();
        logInOut.signup();
    }

    public void viewAccountInfo() {
        // Logic to display account information
        AccountPersonalViews accountPersonalViews = new AccountInfo();
        accountPersonalViews.view();
    }

    public void viewSettings() {
        // Logic to display settings
        AccountSettings accountSettings = new AccountSettings();
        accountSettings.view();
    }

    //public void getKeyByUser(String userID) {
        // Logic to retrieve user details by key
        // return userKey;
    //} we may don't need this anymore
}

