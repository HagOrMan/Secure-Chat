package Backend.KDC;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

// Encrypter abstract class that includes a generate IV
public interface Encrypter {
    public EncryptedMessage encrypt(String input, SecretKey encryptionKey) 
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException;

    public EncryptedMessage encrypt(SecretKey keyToEncrypt, SecretKey encryptionKey) 
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException;

    public String decryptString(EncryptedMessage encryptedMessage, SecretKey decryptionKey)
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException;

    public SecretKey decryptKey(EncryptedMessage encryptedMessage, SecretKey decryptionKey)
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException;

}
