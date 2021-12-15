package com.zikozee.microservices.limitsservice.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : zikoz
 * @created : 01 May, 2021
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Limits {

    private int minimum;
    private int maximum;
}
