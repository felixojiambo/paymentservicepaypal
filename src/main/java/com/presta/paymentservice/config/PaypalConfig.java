package com.presta.paymentservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class PaypalConfig {
    @Value("${paypal.client-id")
    private String clientId;
    @Value("${paypal.client-secret")
    private String clientSecret;
    @Value("${paypal.client-mode")
    private  String mode;
    @Bean
    public  ApiContext apiContext(){
        return  new ApiContext(clientId,clientSecret,mode);
    }
}
