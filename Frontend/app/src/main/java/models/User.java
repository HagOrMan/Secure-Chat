package models;

public class User {
    private String name;
    private String userID;
    private String gender;
    private String email;
    private String phoneNumber;
    private boolean loggedIn;
    private String personalKey;
    //Status status;

    public User() {}

    public User(String name, String userID, String gender, String email,
                String phoneNumber, boolean loggedIn, String personalKey) {
        this.name = name;
        this.userID = userID;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.loggedIn = loggedIn;
        this.personalKey = personalKey;
    }

    // User methods, getters, setters
    public String getName() { return name; }
    public String getUserID() {
        return userID;
    }
    public String getGender() {
        return gender;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public boolean getLoggedIn() {
        return loggedIn;
    }
    public String getPersonalKey() {
        return personalKey;
    }

    // Please implement this later!!!
    // TODO: Needs implementation!
//    @Override
//    public String toString() {
//        return "(" + senderID + " -> " + targetID + " @ " + dateTime + ": " + message_txt + ")";
//    }

}
