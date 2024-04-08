package com.server;

import com.server.dto.*;
import com.server.KDC.KeyDistributionCenter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Server {

    private static KeyDistributionCenter kdc;

    public Server(){
//        kdc = new KeyDistributionCenter();
    }


    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

    @RestController
    public static class PingController {

        @PostMapping("/add-user")
        public String mainPing(@RequestBody UserIDDTO userDTO) {
            kdc.createPersonalKey("user1");
            return "Main Ping";
        }

        @GetMapping("/test")
        public String testPing() {
            return "Can send get request to server successfully";
        }
    }

}