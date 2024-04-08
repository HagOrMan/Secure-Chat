package com.server;

import com.server.KDC.EncryptedMessage;
import com.server.KDC.KeyDistributionCenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KDCTest {

    private KeyDistributionCenter kdc;

    @BeforeEach
    public void setUp(){
        kdc = new KeyDistributionCenter();
    }

    @Test
    public void testCreateKey(){
        kdc.createPersonalKey("Bob");
    }

    @Test
    public void testEncryptMessage(){
        kdc.createPersonalKey("Bob");
        kdc.createPersonalKey("Alice");
        EncryptedMessage msg = kdc.createEncryptedKey("Bob", "Alice");
        System.out.println(msg.getCipherText());
    }

}
