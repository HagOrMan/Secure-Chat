package Backend.AccountController;

public class AccountInfo implements AccountPersonalViews{
    private User user;

    @Override
    public void view() {
        // Implementation of view functionality
    }

    @Override
    public void edit() {
        // Implementation of edit functionality
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

