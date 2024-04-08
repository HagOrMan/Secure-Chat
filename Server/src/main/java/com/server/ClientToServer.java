package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientToServer {

    private static final String POST_URL = "100.112.5.106";

    private static String pingServer(String ending, List<Map<String, String>> params, String value1) throws IOException {
        // Go through parameters and pass them in.
//        StringBuilder paramString = new StringBuilder("?");
//        for (Map<String, String> map : params) {
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                String key = entry.getKey();
//                String value = entry.getValue();
////                con.setRequestProperty(key, value);
//                System.out.println("request property set for " + key + ": " + value);
//                paramString.append(key).append("=").append(value).append("&");
//            }
//        }

        String url = "http://" + POST_URL + ":8080/" + ending;// + paramString;
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("userID", value1);

//        // Go through parameters and pass them in.
//        for (Map<String, String> map : params) {
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                String key = entry.getKey();
//                String value = entry.getValue();
//                con.setRequestProperty(key, value);
//                System.out.println("request property set for " + key + ": " + value);
//            }
//        }


        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
//        os.write(params.getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();

        } else {
            System.out.println("POST request did not work.");
            throw new RuntimeException("POST request did not give a successful connection");
        }
    }


    private static String pingServerTest(String ending, List<String> params) throws IOException {

        String url = "http://" + POST_URL + ":8080/" + ending;
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        StringBuilder param = new StringBuilder();
        for (String s : params){
            param.append(s);
        }

        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(param.toString().getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();

        } else {
            System.out.println("POST request did not work.");
            throw new RuntimeException("POST request did not give a successful connection");
        }
    }

    private static void startChat(String userID) throws IOException {
        List<Map<String, String>> params = new ArrayList<>();
        params.add(Map.of("userID", userID));
//        String response = pingServer("new-chat", params);
    }

    private static String getPersonalKey(String userID) throws IOException {
        List<Map<String, String>> params = new ArrayList<>();
        params.add(Map.of("userID", userID));
        return pingServer("new-personal-key", params, "User1");
    }

//    private static String createAccount(String user, String password) throws IOException {
//        List<Map<String, String>> params = new ArrayList<>();
//        params.add(Map.of("userID", user));
//        params.add(Map.of("password", password));
//        return pingServer("create-account", params);
//    }

    private static void testCreateAccount(){
//        System.out.println("Testing creating an acccount");
//        try {
//            String res = createAccount("alice", "123");
//            System.out.println("Server response: " + res);
//        } catch (IOException e) {
//            System.out.println("Failed");
//            throw new RuntimeException(e);
//        }
    }

//    private static void testCreateKey(){
//        System.out.println("Testing creating a key");
//        try {
//            createAccount("bob", "123");
//            System.out.println(ClientToServer.getPersonalKey("bob"));
//        } catch (IOException e) {
//            System.out.println("Failed");
//            throw new RuntimeException(e);
//        }
//    }

    private static void testServerConnection(){
        try {
            List<String> params = new ArrayList<>();
            params.add("user=Jamie");
            System.out.println(pingServerTest("testPost", params));
            System.out.println(pingServerTest("test-1-var-post", params));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testServerConnection();
        System.out.println("Testing creating a key");
        try {
            System.out.println(ClientToServer.getPersonalKey("bob"));
        } catch (IOException e) {
            System.out.println("Failed");
            throw new RuntimeException(e);
        }
//        testCreateAccount();
//        testCreateKey();
    }

}
