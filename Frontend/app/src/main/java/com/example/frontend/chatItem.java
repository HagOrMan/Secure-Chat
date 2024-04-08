package com.example.frontend;

public class chatItem {

    int pfp;
    String username;
    String lastOnline;

    public chatItem(int pfp, String username, String lastOnline) {
        this.pfp = pfp;
        this.username = username;
        this.lastOnline = lastOnline;
    }

    public int getPfp() {
        return pfp;
    }

    public void setPfp(int pfp) {
        this.pfp = pfp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(String lastOnline) {
        this.lastOnline = lastOnline;
    }
}
