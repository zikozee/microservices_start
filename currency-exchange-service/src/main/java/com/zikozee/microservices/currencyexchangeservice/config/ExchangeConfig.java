package com.zikozee.microservices.currencyexchangeservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Ezekiel Eromosei
 * @created : 18 Dec, 2021
 */

@Configuration
public class ExchangeConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
