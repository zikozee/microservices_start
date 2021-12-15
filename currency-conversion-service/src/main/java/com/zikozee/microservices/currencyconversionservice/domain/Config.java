package com.zikozee.microservices.currencyconversionservice.domain;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author : zikoz
 * @created : 09 May, 2021
 */
//todo note this package must be on a higher level than CurrencyExchangeProxy to be picked up. else place on @SpringbootApplication

@EnableFeignClients
@Configuration
public class Config {
}
