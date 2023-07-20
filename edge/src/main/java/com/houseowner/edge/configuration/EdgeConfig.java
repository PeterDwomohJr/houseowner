package com.houseowner.edge.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EdgeConfig {



    @Bean
    public String getString()
    {
        return "";
    }



    @Bean
    public Integer getInteger()
    {
        return 0;
    }
}
