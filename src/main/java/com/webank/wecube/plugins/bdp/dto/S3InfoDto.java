package com.webank.wecube.plugins.bdp.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class S3InfoDto {
    String host;
    String port;
    String username;
    String password;
    String bucketName;
    String keyName;

    public S3InfoDto(String host, String port, String username, String password, String bucketName, String keyName) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.bucketName = bucketName;
        this.keyName = keyName;
    }

    public S3InfoDto() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
