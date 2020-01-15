package com.webank.wecube.plugins.bdp.support.s3;

public class S3Info {
    private String host;
    private String port;
    private String bucket;
    private String key;

    public S3Info(String host, String port, String bucket, String key) {
        this.host = host;
        this.port = port;
        this.bucket = bucket;
        this.key = key;
    }

    public S3Info() {
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

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
