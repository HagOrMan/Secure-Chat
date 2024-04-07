package Backend.Account;
import java.util.Scanner;

public class AccountLogInOut extends LogInOut {
    private AccountAuthenticator authenticator;
    private Scanner scanner;
    
    public AccountLogInOut() {
        this.authenticator = new AccountAuthenticator();
        this.scanner = new Scanner(System.in);
    }

    @Override
    void login() {
        // Implementation of login functionality
        //request information
        //scanner 
        //call authenticator
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        boolean authenticated = authenticator.authenticate(username, password);
        if (authenticated) {
            System.out.println("You have successfully logged in.");
        } else {
            AccountError accountErrorMessages = new AccountError();
            accountErrorMessages.display(1);//ERROR CODE NEEDED HERE
            //not sure if this should be a account error or an authentication error
        }
    }

    @Override
    void logout() {
        // Implementation of logout functionality
        System.out.println("You have successfully logged out.");
    }

    @Override
    void signup() {
        // Implementation of signup functionality
        //scanner
        //add user to database
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        authenticator.addUser(username, password);
        //Check if the user has been added
        if(authenticator.authenticate(username, password)){
            System.out.println("You have successfully signed up.");
        } else {
            AccountError accountErrorMessages = new AccountError();
            accountErrorMessages.display(2);//ERROR CODE NEEDED HERE
        }
    }
}
