package com.zikozee.microservices.currencyconversionservice.domain.currency_conversion;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author : zikoz
 * @created : 09 May, 2021
 */
//@FeignClient(name = "currency-exchange", url = "localhost:8000") // feign helps to call rest apis easily
//@FeignClient(name = "${exchange-server}") //load-balancing (eureka client) now talking to eureka naming server

//KUBERNETES-CHANGE
//CURRENCY_EXCHANGE_SERVICE_HOST is auto-created by k8s based on the application name defined in the currency exchange service
// for kubernetes we set-up an environment variable for "host" CURRENCY_EXCHANGE_SERVICE_HOST, on local we use localhost, port is constant
//@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000") //todo info: NOT RECOMMENDED


//USE CUSTOM-CREATED ENVs  //todo info: RECOMMENDED
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000")
public interface CurrencyExchangeProxy {

    @GetMapping(path = "currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to); // we made this possible by making sure our CurrencyConversion Matches CurrencyExchange

    @PostMapping(path = "currency-exchange/save")
    CurrencyConversion saveExchange(@RequestBody CurrencyExchangeDto currencyExchangeDto);
}
