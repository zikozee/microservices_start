package com.zikozee.apigateway.modify;

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
public class CurrencyConversion {

    private Long id;
    private String from;
    private String to;
    private BigDecimal quantity;
    private BigDecimal conversionMultiple;
    private BigDecimal totalCalculatedAmount;
    private String environment;

}
