package Backend;

import java.util.Random;

public class ClientMessageAuthenticator {

    protected Random rand;

    public ClientMessageAuthenticator(){
        rand = new Random();
    }

    // Generates a nonce (used for crypto protocol) based on the current system time and adds a random number.
    protected String generateNonce()
    {
        long timestamp = System.currentTimeMillis();
        timestamp += rand.nextLong(1000000);
        return Long.toString(timestamp);
    }


    
}
