package com.server.KDC;

/*
The links used to help with this: 
- https://www.javatpoint.com/java-code-for-des
- https://www.baeldung.com/java-aes-encryption-decryption
*/ 

/*
Note: Typically, the initialization vector (iv) is prepended or appended to the cipher text, 
however using OOP allows use to encapsulated that information in an object instead.
This makes it easier to extract the information as well instead of choosing a delimeter or something to separate the iv from the text.
 */ 

// Imports for cryptography
import java.security.InvalidAlgorithmParameterException;  
import java.security.InvalidKeyException;  
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;  
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;  
import javax.crypto.SecretKey;  
import javax.crypto.spec.IvParameterSpec; 
import javax.crypto.spec.SecretKeySpec;

public class EncrypterAES implements Encrypter {

    // Always use AES cipher (if wondering why, look at class name)
    private String algorithm = "AES/CBC/PKCS5Padding";

    // Base encryption method which encrypts bytes based on a secret key.
    private EncryptedMessage encrypt(byte[] bytesToEncrypt, SecretKey encryptionKey)
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    
        // Create new iv every time.
        byte[] ivByte = generateIV(); // this is needed to use as the byte[] parameter in the EncryptedMessage constructor.
        IvParameterSpec iv = new IvParameterSpec(ivByte);

        // Encryption code from link.
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, encryptionKey, iv);
        byte[] cipherText = cipher.doFinal(bytesToEncrypt);
        String enryptedString = Base64.getEncoder().encodeToString(cipherText);

        // Returns an encrypted message encapsulated with the string and iv.
        return new EncryptedMessage(enryptedString, ivByte);
    
    }

    // Encrypts a String based on a secret key.
    public EncryptedMessage encrypt(String input, SecretKey encryptionKey) 
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return encrypt(input.getBytes(), encryptionKey);
    }

    // Encrypts a secret key based on another secret key.
    public EncryptedMessage encrypt(SecretKey keyToEncrypt, SecretKey encryptionKey) 
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {   
        return encrypt(keyToEncrypt.getEncoded(), encryptionKey);
    }

    // Base decryption algorithm which returns a btye array of the encrypted message by decrypting it using the secret key.
    private byte[] decrypt(EncryptedMessage encryptedMessage, SecretKey decryptionKey)
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    
        // Extracts information from class to decrypt message.
        IvParameterSpec iv = new IvParameterSpec(encryptedMessage.getIv());
        String cipherText = encryptedMessage.getCipherText();

        // Uses decryption code from link.
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, decryptionKey, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        
        return plainText;
    }

    // Takes an encrypted message which should contain some string and decrypts it.
    public String decryptString(EncryptedMessage encryptedMessage, SecretKey decryptionKey) 
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        
        // Takes decrypted byte array and sends back as a string.
        return new String(decrypt(encryptedMessage, decryptionKey));
    }

    // Takes an encrypted message which should contain the bytes for a SecretKey and decrypts it.
    public SecretKey decryptKey(EncryptedMessage encryptedMessage, SecretKey decryptionKey) 
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        
        // Use decrypt function and convert byte array back to SecretKey.
        return new SecretKeySpec(decrypt(encryptedMessage, decryptionKey), "AES");
    }

    public byte[] generateIV() {
        
        // Use secure random to create the IV (it's good for almost perfectly unique IVs? https://stackoverflow.com/questions/1785555/how-should-i-generate-an-initialization-vector)
        byte[] iv = new byte[16]; // 16 bytes for AES
        new SecureRandom().nextBytes(iv);
        return iv;
    }

}
