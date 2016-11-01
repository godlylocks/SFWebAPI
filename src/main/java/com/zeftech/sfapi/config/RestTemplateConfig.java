package com.zeftech.sfapi.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Cody_OPI on 10/31/16.
 */
public class RestTemplateConfig {

    @Bean
    public RestTemplate configRestTemplate() {
        RestTemplateBuilder rtb = new RestTemplateBuilder();
        return rtb
                .setReadTimeout(5000)
                .setConnectTimeout(5000)
                .build();
    }
}
