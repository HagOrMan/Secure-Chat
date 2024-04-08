package Backend.KDC;

/*
The links used to help with this: 
- https://www.javatpoint.com/java-code-for-des
- https://www.baeldung.com/java-aes-encryption-decryption
*/ 

/*
Note: Typically, the initialization vector (iv) is prepended or appended to the cipher text, 
however using OOP allows use to encapsulated that information in an object instead.
This makes it easier to extract the information as well instead of choosing a delimeter or something.
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

public class EncrypterAES implements Encrypter {

    // Always use AES cipher (if wondering why, look at class name)
    private String algorithm = "AES/CBC/PKCS5Padding";

    public EncryptedMessage encrypt(String input, SecretKey key) 
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    
        // Create new iv every time.
        byte[] ivByte = generateIV(); // this is needed to use the byte[] object in the EncryptedMessage class.
        IvParameterSpec iv = new IvParameterSpec(ivByte);

        // Encryption code from link.
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        String enryptedString =  Base64.getEncoder().encodeToString(cipherText);

        // Returns an encrypted message encapsulated with the string and iv.
        return new EncryptedMessage(enryptedString, ivByte);
    }

    public String decrypt(EncryptedMessage encryptedMessage, SecretKey key) 
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    
        // Extracts information from class to decrypt message.
        IvParameterSpec iv = new IvParameterSpec(encryptedMessage.getIv());
        String cipherText = encryptedMessage.getCipherText();

        // Uses decryption code from link.
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
            .decode(cipherText));
        return new String(plainText);
    }

    public byte[] generateIV() {
        
        // Use secure random to create the IV (it's good for almost perfectly unique IVs? https://stackoverflow.com/questions/1785555/how-should-i-generate-an-initialization-vector)
        byte[] iv = new byte[16]; // 16 bytes for AES
        new SecureRandom().nextBytes(iv);
        return iv;
    }

}
