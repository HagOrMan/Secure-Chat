package com.server;

import com.server.KDC.EncryptedMessage;
import com.server.KDC.KeyConverter;
import com.server.dto.*;
import com.server.KDC.KeyDistributionCenter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Server {

    private static KeyDistributionCenter kdc;

    private static List<String> users;

    public Server(){
        kdc = new KeyDistributionCenter();
        users = new ArrayList<>();
    }


    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

    @RestController
    public static class Controller {

        @PostMapping("/")
        public String mainPing() {
            return "This is the main page of Secure Chat. Please ping another url for more functionality";
        }

        @PostMapping("/create-account")
        public String createAccount(@RequestBody NewUserDTO newUserDTO) {
            // call account creation class to make new account based on dto
            users.add(newUserDTO.getUser());
            return "Account for " + newUserDTO.getUser() + " created";
        }

        @PostMapping("/signin")
        public String signIn(@RequestBody UserDTO userDTO) {
            // Call Account login out class to sign in
            users.add(userDTO.getUser());
            return "signin";
        }

        @PostMapping("/signout")
        public String signOut(@RequestBody String userID) {
            // Call Account login out class to sign out
            users.remove(userID);
            return "signout";
        }

        @PostMapping("/new-personal-key")
        public ResponseEntity<EncodedKeyDTO> getPersonalKey(@RequestHeader String userID) {
            System.out.println("Connection received by " + userID);
            if (users.contains(userID)){
                SecretKey key = kdc.createPersonalKey(userID);
                EncodedKeyDTO res = new EncodedKeyDTO(KeyConverter.encodeKey(key), key.getAlgorithm());
                return ResponseEntity.ok(res);
            }
            else {
                return ResponseEntity.badRequest().build();
            }
        }

        @PostMapping("/new-chat")
        public ResponseEntity<EncryptedMessage> startChat(@RequestBody NewChatDTO chatUsers) {
            if (users.contains(chatUsers.getSender())){

                EncryptedMessage newMessage = kdc.createEncryptedKey(chatUsers.getReceiver(), chatUsers.getSender());

//                EncodedKeyDTO res = new EncodedKeyDTO(KeyConverter.encodeKey(key), key.getAlgorithm());
                return ResponseEntity.ok(null);
            }
            else {
                return ResponseEntity.badRequest().build();
            }
        }

        @PostMapping("/num-keys")
        public String numKeys(){
            return kdc.numKeys() + " keys in use";
        }

        @GetMapping("/test")
        public String testPing() {
            return "Can send get request to server successfully";
        }

        @PostMapping("/test-dto")
        public String testDTO(@RequestBody UserDTO userDTO) {
            return userDTO.getUser() + ", " + userDTO.getPassword();
        }
    }

}