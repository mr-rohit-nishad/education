package com.education.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@Configuration
@AllArgsConstructor
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private final Auth auth = new Auth();
      private final OAuth2 oauth2 = new OAuth2();

    public static class Auth {
        private String tokenSecret;

        public String getTokenIssuer() {
            return tokenIssuer;
        }

        public void setTokenIssuer(String tokenIssuer) {
            this.tokenIssuer = tokenIssuer;
        }

        private String  tokenIssuer;
        private long tokenExpirationMsec;

        public long getSuperTokenExpirationMsec() {
            return superTokenExpirationMsec;
        }

        public void setSuperTokenExpirationMsec(long superTokenExpirationMsec) {
            this.superTokenExpirationMsec = superTokenExpirationMsec;
        }

        private long  superTokenExpirationMsec;

        public String getTokenSecret() {
            return tokenSecret;
        }

        public void setTokenSecret(String tokenSecret) {
            this.tokenSecret = tokenSecret;
        }

        public long getTokenExpirationMsec() {
            return tokenExpirationMsec;
        }

        public void setTokenExpirationMsec(long tokenExpirationMsec) {
            this.tokenExpirationMsec = tokenExpirationMsec;
        }
    }

    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public List<String> getAuthorizedRedirectUris() {
            return authorizedRedirectUris;
        }

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }

    public Auth getAuth() {
        return auth;
    }

    public OAuth2 getOauth2() {
        return oauth2;
    }
}
