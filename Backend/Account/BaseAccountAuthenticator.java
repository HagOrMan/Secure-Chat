package Backend.Account;

public interface BaseAccountAuthenticator {
    boolean authenticate( String username, String password);
}
