package com.dwomo.houseowner.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import java.net.URL;
import java.util.stream.Stream;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring-addons")
public class SpringAddonsProperties {
    private IssuerProperties[] issuers = {};

    @Data
    static class IssuerProperties {
        private URL uri;
        private ClaimMappingProperties[] claims;
        private String usernameJsonPath = JwtClaimNames.SUB;

        @Data
        static class ClaimMappingProperties {
            private String jsonPath;
            private CaseProcessing caseProcessing = CaseProcessing.UNCHANGED;
            private String prefix = "";

            enum CaseProcessing {
                UNCHANGED
            }
        }
    }

    public IssuerProperties get(URL issuerUri) throws MisconfigurationException {
        final var issuerProperties = Stream.of(issuers).filter(iss -> issuerUri.equals(iss.getUri())).toList();
        if (issuerProperties.size() == 0) {
            throw new MisconfigurationException("Missing authorities mapping properties for %s".formatted(issuerUri.toString()));
        }
        if (issuerProperties.size() > 1) {
            throw new MisconfigurationException("Too many authorities mapping properties for %s".formatted(issuerUri.toString()));
        }
        return issuerProperties.get(0);
    }

    static class MisconfigurationException extends RuntimeException {
        private static final long serialVersionUID = 5887967904749547431L;

        public MisconfigurationException(String msg) {
            super(msg);
        }
    }
}