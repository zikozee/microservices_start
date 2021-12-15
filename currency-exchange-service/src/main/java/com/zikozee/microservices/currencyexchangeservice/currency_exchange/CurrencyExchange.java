package com.zikozee.microservices.currencyexchangeservice.currency_exchange;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author : zikoz
 * @created : 02 May, 2021
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class CurrencyExchange {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "currency_from")
    private String from;   //don't use sql keyword directly, will produce error
    @Column(name = "currency_to")
    private String to;
    private BigDecimal conversionMultiple;
    private String environment;

    public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }
}
