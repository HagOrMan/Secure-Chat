package com.server;

import com.server.KDC.KeyDistributionCenter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class KDCTest {

    private KeyDistributionCenter kdc;

    @BeforeAll
    public void setUp(){
        kdc = new KeyDistributionCenter();
    }

    @Test
    public void testCreateKey(){
        kdc.createPersonalKey("Bob");
    }

}
