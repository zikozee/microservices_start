package com.zikozee.microservices.limitsservice.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : zikoz
 * @created : 01 May, 2021
 */
@Component
@ConfigurationProperties("limits-service")
@NoArgsConstructor @Getter @Setter
public class LimitsPropertiesConfig {

    private int minimum;
    private int maximum;
}
