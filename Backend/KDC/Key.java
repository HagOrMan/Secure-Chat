package Backend.KDC;

import java.time.ZonedDateTime;

import javax.crypto.SecretKey;

public class Key {

    private String user;
    private SecretKey key;
    private ZonedDateTime createDate;

    public Key(String user, SecretKey key, ZonedDateTime createDate){
        this.user = user; this.key = key; this.createDate = createDate;
    }

    public String getUser(){
        return user;
    }

    public SecretKey getKey(){
        return key;
    }

    public ZonedDateTime getCreateDate(){
        return createDate;
    }
}