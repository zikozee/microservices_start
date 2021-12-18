package com.zikozee.microservices.currencyexchangeservice.currency_exchange;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author : zikoz
 * @created : 02 May, 2021
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final Environment environment;
    private final CurrencyExchangeRepository repository;
    private final ModelMapper modelMapper;

    @GetMapping(path = "currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to){

        log.info("retrievedExchangeValue called with {} to {}", from, to);

        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to)
                .orElseThrow(() -> new RuntimeException("Unable to find data for " + from  + " to " + to));

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }

    @PostMapping(path = "currency-exchange/save")
    public CurrencyExchange retrieveExchangeValue(@RequestBody CurrencyExchangeDto currencyExchangeDto){
        CurrencyExchange currencyExchange = modelMapper.map(currencyExchangeDto, CurrencyExchange.class);
        return repository.save(currencyExchange);
    }
}
