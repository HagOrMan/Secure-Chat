package com.server.KDC;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class KeyConverter {

    // Encodes a SecretKey so it can be parsed on client side and sent as a string over http.
    public static String encodeKey(SecretKey key){
        byte[] keyBytes = key.getEncoded();
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    // Decodes a SecretKey so it can be parsed on client side and sent as a string over http.
    public static SecretKey decodeKey(String key, String algorithm){
        byte[] keyBytes = Base64.getDecoder().decode(key);
        return new SecretKeySpec(keyBytes, algorithm);
    }
}
