package com.zikozee.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * @author : zikoz
 * @created : 09 May, 2021
 */
@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

        //we can allow add filters before authentication here

        return builder.routes()
                .route(p -> p.path("/get")    // http://localhost:8765/get
                        .filters(f -> f.addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))

                .route(p -> p.path("/currency-exchange/**") //any request to this path will be redirected to the loadbalancer name registered on the naming server as below
                        .uri("lb://currency-exchange"))//name registered on load naming server

                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))//name registered on load naming server

                .route(p -> p.path("/currency-conversion-feign/**") // path or uri to be accessed
                        .uri("lb://currency-conversion")) //name registered on load naming server

                //another redirection example
                .route(p -> p.path("/currency-conversion-new/**")
                        .filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)",    //whatever is appended here will be appended to the below
                                "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion")) //name registered on load naming server

                .route(p -> p.path("/fetch-money/**")// we can use different PATH
                        //rewrite to a different path
                        .filters(f -> f.rewritePath("/fetch-money/(?<segment>.*)",    //whatever is appended here will be appended to the below
                                "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion"))
                .build();
    }
}
