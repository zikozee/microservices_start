package com.zikozee.microservices.currencyconversionservice.domain.currency_conversion;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zikoz
 * @created : 02 May, 2021
 */
@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {

    private final CurrencyExchangeProxy proxy;

    @GetMapping(path = "currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") BigDecimal quantity){

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);


        ResponseEntity<CurrencyConversion>  responseEntity= new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, uriVariables);


        CurrencyConversion currencyConversion = responseEntity.getBody();
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
        currencyConversion.setEnvironment(currencyConversion.getEnvironment() + " rest template");

        return currencyConversion;
    }

    //todo info ===>>> Using feign client
    @GetMapping(path = "currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") BigDecimal quantity){


        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);

        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
        currencyConversion.setEnvironment(currencyConversion.getEnvironment() + " feign");

        return currencyConversion;
    }

    @PostMapping(path = "save-conversion-feign")
    public CurrencyConversion calculateCurrencyConversionFeign(@RequestBody CurrencyExchangeDto currencyExchangeDto){

        return proxy.saveExchange(currencyExchangeDto);
    }
}
