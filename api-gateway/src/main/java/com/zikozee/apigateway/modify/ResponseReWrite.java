package com.zikozee.apigateway.modify;

import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.zikozee.apigateway.modify.Mod.modCurrencyConversionResponse;

/**
 * @author : Ezekiel Eromosei
 * @created : 25 Dec, 2021
 */

@Component
public class ResponseReWrite implements RewriteFunction<CurrencyConversion, CurrencyConversionModified> {

    @Override
    public Publisher<CurrencyConversionModified> apply(ServerWebExchange serverWebExchange, CurrencyConversion currencyConversion) {
        return Mono.just(modCurrencyConversionResponse(currencyConversion));
    }
}
