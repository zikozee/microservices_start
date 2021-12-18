package com.zikozee.microservices.currencyexchangeservice.currency_exchange;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author : zikoz
 * @created : 02 May, 2021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyExchangeDto {
    private String from;
    private String to;
    private BigDecimal conversionMultiple;

}
