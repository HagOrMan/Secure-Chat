package Backend.Account;

public class User {
    private String name;
    private String userID;
    private String gender;
    private String email;
    private String phoneNumber;
    private boolean loggedIn;
    private String personalKey;
    //may be change this part
    // this for sign up
    //all user name store in backend :)

    // User methods, getters, setters
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getUserID() {
        return userID;
    }
    public void setGender(String gender) {
        this.gender=gender;
    }
    public String getGender() {
        return gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    public boolean getLoggedIn() {
        return loggedIn;
    }
    public void setPersonalKey(String personalKey) {
        this.personalKey = personalKey;
    }
    public String getPersonalKey() {
        return personalKey;
    }
}
