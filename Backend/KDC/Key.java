package Backend.KDC;

import java.time.LocalDateTime;
import java.util.Base64;

import javax.crypto.SecretKey;

public class Key {

    private String user;
    private SecretKey key;
    private LocalDateTime createDate;

    public Key(String user, SecretKey key, LocalDateTime createDate){
        this.user = user; this.key = key; this.createDate = createDate;
    }

    @Override
    public String toString() {
        String keyString = Base64.getEncoder().encodeToString(key.getEncoded());
        return user + "," + keyString + "," + createDate.toString();
    }

    public String getUser(){
        return user;
    }

    public SecretKey getKey(){
        return key;
    }

    public LocalDateTime getCreateDate(){
        return createDate;
    }
}