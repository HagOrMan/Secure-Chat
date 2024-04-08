package com.server;

import com.server.KDC.KeyAdapterServerside;
import com.server.dto.*;
import com.server.KDC.KeyDistributionCenter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;

@SpringBootApplication
public class Server {

    private static KeyDistributionCenter kdc;
    private static KeyAdapterServerside keyAdapter;

    public Server(){
        kdc = new KeyDistributionCenter();
        keyAdapter = new KeyAdapterServerside();
    }


    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

    @RestController
    public static class Controller {

        @PostMapping("/add-user")
        public String mainPing(@RequestBody UserIDDTO userDTO) {
            kdc.createPersonalKey("user1");
            return "Main Ping";
        }

        @PostMapping("/new-personal-key")
        public ResponseEntity<EncodedKeyDTO> getPersonalKey(@RequestBody String userID) {
            SecretKey key = kdc.createPersonalKey(userID);
            EncodedKeyDTO res = new EncodedKeyDTO(keyAdapter.encodeKey(key), key.getAlgorithm());
            return ResponseEntity.ok(res);
        }

        @PostMapping("/num-keys")
        public String numKeys(){
            return kdc.numKeys() + " keys in use";
        }

        @GetMapping("/test")
        public String testPing() {
            return "Can send get request to server successfully";
        }
    }

}