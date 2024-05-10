package com.presta.paymentservice.config;

import org.springframework.beans.factory.annotation.Value;

public class PaypalConfig {
    @Value("${paypal.client-id")
    private String clientId;
    @Value("${paypal.client-secret")
    private String clientSecret;
    @Value("${paypal.client-mode")
    private  String mode;
}
