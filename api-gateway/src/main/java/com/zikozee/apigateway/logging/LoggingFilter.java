package com.zikozee.apigateway.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author : zikoz
 * @created : 09 May, 2021
 */
@Slf4j
@Component
public class LoggingFilter implements GlobalFilter {

    // todo info: if we want to authorize all the requests without leaving any out, then here is the best place
    // REMEMBER HOW WE USED FILTER for OncePerRequestFilter in jwt
    // this is also a filter so we can do same here

    // however, if we just want to authenticate some request, then we want to stick to RouteLocator in a granular way

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Path of the request received -> {}", exchange.getRequest().getPath());

        return chain.filter(exchange); //continue to next filter
    }
}
