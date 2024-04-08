package com.server.KDC;

import com.server.dto.ChatRequestResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Imports for cryptography (link used for help: https://www.javatpoint.com/java-code-for-des) 
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import javax.crypto.KeyGenerator;    
import javax.crypto.SecretKey; 

public class KeyDistributionCenter {

    // Scheduler which will regularly update the keys using java threads.
    private ScheduledExecutorService scheduler;
    private KeyDBConnector dbConnector;

    // Info for encrypting/decrypting
    private Encrypter encrypter;
    private String algorithm;
    private int algorithmBits;

    private List<Key> sessionKeys;

    public KeyDistributionCenter(){
        algorithm = "AES";
        dbConnector = new KeyDBConnector(algorithm);
        encrypter = new EncrypterAES();
        algorithmBits = 128;
        sessionKeys = new ArrayList<>();

        // Initializes object with a scheduler and immediately sets it to refresh keys every hour.
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::updateKeys, 0, 1, TimeUnit.HOURS);
    }

    // Shuts down the scheduler because threads are finicky and want to make sure there's nothing running when it shouldn't be.
    public void shutDownScheduler() {
        
        // Ensures we don't call this on a shut down scheduler
        if (scheduler.isShutdown()) {
            scheduler.shutdown();

            // Because the above method waits for tasks to finish, this next block basically tells it that it can only take so long before a force shut down/
            try {

                // Giving it 30 seconds to finish.
                if (!scheduler.awaitTermination(30, TimeUnit.SECONDS)) {
                    // Force the shutdown if 30 seconds have passed without tasks finishing (maybe we decide if there are tasks stacked up, we should let them finish?).
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                // Interrupts the thread if any exception occurs to make sure that the scheduler gets shut down.
                Thread.currentThread().interrupt();
            }
        }
    }

    public ChatRequestResponseDTO startNewChat(String sender, String receiver, String nonce){

        try {
            EncryptedMessage encryptedNonce = encrypter.encrypt(nonce, dbConnector.getKeyByUser(sender).getKey());
            EncryptedMessage senderSessionKey = createEncryptedKey(sender, sender);
            EncryptedMessage encryptedReceiver = encrypter.encrypt(receiver, dbConnector.getKeyByUser(sender).getKey());

            // Double encrypts messages for sender to send to receiver.
            EncryptedMessage receiverSessionKeyBase = encrypter.encrypt(
                    senderSessionKey.getCipherText(), dbConnector.getKeyByUser(receiver).getKey());
            DoubleEncryptedMessage receiverSessionKey = new DoubleEncryptedMessage(
                    receiverSessionKeyBase.getCipherText(), receiverSessionKeyBase.getIv(), senderSessionKey.getIv()
            );

            EncryptedMessage encryptedSender = encrypter.encrypt(sender, dbConnector.getKeyByUser(sender).getKey());
            EncryptedMessage encryptedSender2 = encrypter.encrypt(
                    encryptedSender.getCipherText(), dbConnector.getKeyByUser(receiver).getKey()
            );
            DoubleEncryptedMessage senderEncryptedForReceiver = new DoubleEncryptedMessage(
                    encryptedSender2.getCipherText(), encryptedSender2.getIv(), encryptedSender.getIv()
            );

            return new ChatRequestResponseDTO(encryptedNonce, senderSessionKey, encryptedReceiver,
                    receiverSessionKey, senderEncryptedForReceiver);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error occured when encrypting message for chat between " + sender + " and " + receiver);
        }
    }

    /**
     * This function allows one to request a key for one user, encrypted with the key for another user. 
     * @param user userID of the user that the key is for
     * @param userForEncryption userID of the user whose key should be used to encrypt the message
     * @return the encrypted message
     */
    public EncryptedMessage createEncryptedKey(String user, String userForEncryption){

        try {
            // Get key from db, create new key, save to db, and encrypt the new key to send back.
            SecretKey encryptionKey = dbConnector.getKeyByUser(userForEncryption).getKey();
            SecretKey newKey = generateKey();
            Key newSeshKey = new Key(user, newKey, LocalDateTime.now());
            dbConnector.addKey(newSeshKey);
            sessionKeys.add(newSeshKey);
            return encrypter.encrypt(newKey, encryptionKey); 
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error occured when encrypting message.", e);
        }
    }

    // This function creates a personal key for a user, stores it in the KDC, and sends it back for the user to keep.
    public SecretKey createPersonalKey(String user){
        SecretKey secretKey = generateKey();
        dbConnector.addKey(new Key(user, secretKey, LocalDateTime.now()));
        return secretKey;
    }

    private SecretKey generateKey() {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(algorithm);
            keyGenerator.init(algorithmBits);
            SecretKey key = keyGenerator.generateKey();
            return key;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occured when generating secret Key.", e);
        }
    }

    /**
     * Removes the specified key from key storage
     * @param key the key to be removed
     * @return true if key exists and was removed, false if key does not exist in storage
     */
    public boolean removeKey(Key key){
        return dbConnector.removeKey(key);
    }

    private void updateKeys(){

    }

    public int numKeys(){
        return dbConnector.numKeys();
    }
    
    
}