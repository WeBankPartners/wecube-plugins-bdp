package com.webank.wecube.plugins.bdp.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author howechen
 */
@ConfigurationProperties(prefix = "plugins")
public class ApplicationProperties {
    @ConfigurationProperties(prefix = "plugins.ops")
    public static class OpsProperties {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
