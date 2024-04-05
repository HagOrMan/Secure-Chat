package Backend.KDC;

// Potential middleman class to be used to update users
// I was thinking of maybe initializing with the KDC and UserController that it'd talk to since idk how to make the strings work
// use pub sub

public abstract class KDCUserUpdater {

    protected String[] users;

    public KDCUserUpdater(String[] users){
        this.users = users;
    }

    public abstract void updateUsers();
}
