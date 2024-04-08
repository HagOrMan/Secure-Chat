package com.server.KDC;

/*
 * Sources: 
 * - writing file https://stackoverflow.com/questions/8210616/printwriter-append-method-not-appending
 * - writing in append mode https://docs.oracle.com/javase/6/docs/api/java/io/FileOutputStream.html
 * - reading files https://www.baeldung.com/java-buffered-reader
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;

public class KeyDBConnector {
    
    private String storage = "";
    private String algorithm;
    private List<Key> keys;

    public KeyDBConnector(String algorithm){
        keys = readKeysFromDB();
        this.algorithm = algorithm;
    }

    // Read keys from db and save locally
    private List<Key> readKeysFromDB()  {
        List<Key> keys = new ArrayList<>();

        // TODO: @ahmed pls help

        return keys;
    }

    // Adds a key to the list and file
    public void addKey(Key key){
        keys.add(key);

        // TODO: @ahmed pls help
        // This is where we add it to the db
    }

    /**
     * Removes the specified key from key storage
     * @param key the key to be removed
     * @return true if key exists and was removed, false if key does not exist in storage
     */
    public boolean removeKey(Key key){
        boolean foundKey = keys.remove(key);

        if (!foundKey){
            return false;
        }
        
        // this will be the part where you remove it from the db
        return true;
    }

    public Key getKeyByUser(String user){
        for (Key key : keys){
            if (key.getUser() == user){
                return key;
            }
        }
        return null;
    }

    public int numKeys(){
        return keys.size();
    }
    
}
