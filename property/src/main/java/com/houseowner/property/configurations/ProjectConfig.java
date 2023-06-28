package com.houseowner.property.configurations;

import com.mongodb.MongoClientSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Configuration
public class ProjectConfig {


    @Bean
    public String string() {
        return new String();
    }

    @Bean
    public double aDouble() {
        return Double.valueOf("0");
    }

    @Bean
    public BigDecimal bigDecimal() {
        return new BigDecimal(0);
    }


    /**
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity.csrf(csrfSpec -> csrfSpec.disable());
                return httpSecurity.build();
    }
**/
    @Bean
    public MongoClientSettings mongoClientSettings() {
        final MongoClientSettings clientSettings = MongoClientSettings.builder()
                .retryWrites(true)
                .applyToConnectionPoolSettings((ConnectionPoolSettings.Builder builder) -> {
                    builder.maxSize(300) //connections count
                            .minSize(100)
                            .maxConnectionLifeTime(0, TimeUnit.MILLISECONDS)
                            .maxConnectionIdleTime(0, TimeUnit.MILLISECONDS)
                            .maxWaitTime(5000, TimeUnit.MILLISECONDS);
                })
                .applyToSocketSettings(builder -> {
                    builder.connectTimeout(2000, TimeUnit.MILLISECONDS);
                })
                .applicationName("PropertyApplication")
                .build();

        return clientSettings;
    }
}
