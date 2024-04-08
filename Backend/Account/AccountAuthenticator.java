package Backend.Account;
import java.util.ArrayList;


public class AccountAuthenticator implements BaseAccountAuthenticator{
    private class UserList{
        private String username;
        private String password;
        public UserList(String username, String password){
            this.username = username;
            this.password = password;
        }
        public String getUsername(){
            return username;
        }
        public String getPassword(){
            return password;
        }
    }

    private ArrayList<UserList> users = new ArrayList<UserList>();
    public AccountAuthenticator(){
        //add user name and password to the list
        users.add(new UserList("user1", "password1"));
        users.add(new UserList("user2", "password2"));
        users.add(new UserList("user3", "password3"));
    }

    public void addUser(String username, String password){
        //add user name and password to the list
        users.add(new UserList(username, password));
    }

    @Override
    public boolean authenticate(String username, String password) {
        // Authenticate the user, using the database 
        boolean authenticated = false;
        //check if the user name and password are correct
        for (UserList user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                authenticated = true;
            }
        }
        return authenticated;
    }
   
}
/*
 * ask michael about the log in and log out page
 * save variable (not scanner), call a new class ( send post request to server with the information, user name and password)
 * make a new page for the log in fail or pop up window
 * need URL later
 * new post request will be front end
 * if true, go to next page
 * http://100.82.74.6/8080/
 */
//make a new class for sendpost