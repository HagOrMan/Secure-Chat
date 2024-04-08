package com.server.KDC;

import javax.crypto.SecretKey;
import java.util.Base64;

public class KeyAdapterServerside {

    // Encodes a SecretKey so it can be parsed on client side and sent as a string over http.
    public String encodeKey(SecretKey key){
        byte[] keyBytes = key.getEncoded();
        return Base64.getEncoder().encodeToString(keyBytes);
    }
}
