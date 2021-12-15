package com.zikozee.microservices.limitsservice.controller;

import com.zikozee.microservices.limitsservice.bean.Limits;
import com.zikozee.microservices.limitsservice.configuration.LimitsPropertiesConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zikoz
 * @created : 01 May, 2021
 */
@RestController
@RequiredArgsConstructor
public class LimitsController {

    private final LimitsPropertiesConfig limitsPropertiesConfig;

    @GetMapping(path = "limits")
    public Limits retrieveLimits(){
        return new Limits(limitsPropertiesConfig.getMinimum(), limitsPropertiesConfig.getMaximum());
    }
}
