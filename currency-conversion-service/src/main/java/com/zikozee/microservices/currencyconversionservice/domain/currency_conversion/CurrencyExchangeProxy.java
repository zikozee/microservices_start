package com.zikozee.microservices.currencyconversionservice.domain.currency_conversion;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : zikoz
 * @created : 09 May, 2021
 */
//@FeignClient(name = "currency-exchange", url = "localhost:8000") // feign helps to call rest apis easily
@FeignClient(name = "currency-exchange") //load-balancing (eureka client) now talking to eureka naming server
public interface CurrencyExchangeProxy {

    @GetMapping(path = "currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to); // we made this possible by making sure our CurrencyConversion Matches CurrencyExchange
}
