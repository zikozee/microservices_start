package com.zikozee.microservices.currencyexchangeservice.circuit_breaker;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author : zikoz
 * @created : 09 May, 2021
 */
@Slf4j
@RestController
public class CircuitBreakerController {

    @GetMapping(path = "currency-exchange/sample-api")
//    @Retry(name = "default")//default 3 retries
//    @Retry(name = "sample-api")
    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")//check application.yml
//    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
//    @RateLimiter(name = "default")// total number of calls per second e.g 10s => 10000 calls only allow to the sample api
//    @Bulkhead(name = "sample-api")// total number of concurrent calls
    public String sampleApi(){
        log.info("Sample Api call received");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);

        return forEntity.getBody();
//       return "sample-api";
    }

    public String hardcodedResponse(Exception ex){ //we can use any exception in here so far is extends throwable
        return "fallback-response";
    }
}
