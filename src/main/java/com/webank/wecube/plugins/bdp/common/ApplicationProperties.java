package com.webank.wecube.plugins.bdp.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author howechen
 */
@ConfigurationProperties(prefix = "plugins.itsm.s3")
public class ApplicationProperties {
    private String host;
    private String port;
    private String username;
    private String password;

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
}
