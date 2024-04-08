package Backend.Account;

import java.io.IOException;

public class testAuth {
    // Test the authenticate method
    public static void main(String[] args) throws IOException {
        //test the authenticate method
        AccountAuthenticator auth = new AccountAuthenticator();
        System.out.println("Testing authenticate with correct credentials:user1, password1");
        System.out.println(auth.authenticate("user1", "password1"));//should return true
        System.out.println("Testing authenticate with incorrect credentials:user1, wrong");
        System.out.println((auth.authenticate("user1", "wrong")));//should return false

        //test the addUser method
        System.out.println("Testing addUser with user4, password4");
        auth.addUser("user4", "password4");
        System.out.println("Testing authenticate with correct credentials:user4, password4");
        System.out.println(auth.authenticate("user4", "password4"));//should return true

        //test the LogInOut class
        AccountLogInOut logInOut = new AccountLogInOut();
        System.out.println("Testing login with correct credentials:user1, password1");
        logInOut.login();//should return "You have successfully logged in."
        System.out.println("Testing login with incorrect credentials:user1, wrong");
        logInOut.login();//should return "Invalid username or password."
        System.out.println("Testing signup with user5, password5");
        logInOut.signup();//should return "You have successfully signed up."
        System.out.println("Testing login with correct credentials:user5, wrong");
        logInOut.login();//should return "You have successfully logged in."
    }

}