package com.adyt.anima.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "anima.security")
@Data
public class SecurityConfiguration {

    private String secretKey;

}
