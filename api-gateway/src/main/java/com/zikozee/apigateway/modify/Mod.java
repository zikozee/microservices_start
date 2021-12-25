package com.zikozee.apigateway.modify;

/**
 * @author : Ezekiel Eromosei
 * @created : 25 Dec, 2021
 */

public class Mod {
    private Mod(){}

    public static CurrencyConversionModified modCurrencyConversionResponse(CurrencyConversion currencyConversion){
        return CurrencyConversionModified.builder()
                .id(currencyConversion.getId())
                .from(currencyConversion.getFrom())
                .to(currencyConversion.getTo())
                .conversionMultiple(currencyConversion.getConversionMultiple())
                .totalCalculatedAmount(currencyConversion.getTotalCalculatedAmount())
                .environment(currencyConversion.getEnvironment())
                .newWishes("MODIFIED BABY")
                .signature("EZEKIEL ZIKO")
                .build();
    }
}
