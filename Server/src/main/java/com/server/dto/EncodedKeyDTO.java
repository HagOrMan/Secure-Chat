package com.server.dto;

public class EncodedKeyDTO {

    private String key;
    private String algorithm;

    public EncodedKeyDTO(String key, String algorithm){
        this.key = key;
        this.algorithm = algorithm;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
