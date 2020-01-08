package com.webank.wecube.plugins.bdp.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author howechen
 */
@ConfigurationProperties(prefix = "wecube.plugins.bdp")
public class ApplicationProperties {
    private String server;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
