package com.zikozee.microservices.currencyconversionservice.domain.currency_conversion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
