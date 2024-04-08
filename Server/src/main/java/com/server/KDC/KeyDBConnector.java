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
    
    private String storage = "./KeyStorage.csv";
    private String algorithm;
    private List<Key> keys;

    public KeyDBConnector(String algorithm){
        keys = readKeysFromFile();
        this.algorithm = algorithm;
    }

    // Write keys to a text file, replacing what is already there with the current Key list.
    public void saveAllKeys(){
        // Uses PrintWriter to use println and keep each Key information on separate lines for easy reading.
        try (PrintWriter writer = new PrintWriter(storage)) {
            for (Key key : keys) {
                writer.println(key.toString()); 
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // Read keys from a text file
    private List<Key> readKeysFromFile()  {
        List<Key> keys = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(storage))) {
            String line = reader.readLine();
            while ( line != null) {
                String[] parts = line.split(",");
                String user = parts[0];
                byte[] keyBytes = Base64.getDecoder().decode(parts[1]);
                LocalDateTime createDate = LocalDateTime.parse(parts[2]);

                SecretKey secretKey = new SecretKeySpec(keyBytes, algorithm);

                keys.add(new Key(user, secretKey, createDate));
                line = reader.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return keys;
    }

    // Adds a key to the list and file
    public void addKey(Key key){
        keys.add(key);

        // Create print writer in append mode.
        try (PrintWriter writer = new PrintWriter(new FileWriter(storage, true))) {
            writer.println(key.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        
        // Currently not the best, but saves all keys to remove the deleted on from the file.
        saveAllKeys();
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
    
}
