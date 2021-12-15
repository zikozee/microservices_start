package com.zikozee.microservices.currencyconversionservice.domain.currency_conversion;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author : zikoz
 * @created : 02 May, 2021
 */
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyConversion {

    private Long id;
    private String from;
    private String to;
    private BigDecimal quantity;
    private BigDecimal conversionMultiple;
    private BigDecimal totalCalculatedAmount;
    private String environment;

}
