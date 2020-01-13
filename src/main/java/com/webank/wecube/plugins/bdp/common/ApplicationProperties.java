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

    @ConfigurationProperties(prefix = "plugins.itsm.s3")
    public static class ItsmS3Properties {
        private String username;
        private String password;

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
}
