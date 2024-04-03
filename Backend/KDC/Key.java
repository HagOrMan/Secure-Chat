package Backend.KDC;

import java.time.ZonedDateTime;

class Key {

    private String user, key;
    private ZonedDateTime createDate;

    public Key(String user, String key, ZonedDateTime createDate){
        this.user = user; this.key = key; this.createDate = createDate;
    }

    public String getUser(){
        return user;
    }

    public String getKey(){
        return key;
    }

    public ZonedDateTime getCreateDate(){
        return createDate;
    }
}