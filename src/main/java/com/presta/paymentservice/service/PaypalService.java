package com.presta.paymentservice.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PaypalService {
    private  final  APIContext apiContext;
    public  Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl,
            ){
        Amount amount=new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format(Locale.forLanguageTag(currency),"%2f",total));
        Transaction  transaction=new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);
        List<Transaction>transactions=new ArrayList<>();
        transactions.add(transaction);
        Payer payer=new Payer();
        payer.setPaymentMethod(method);
        Payment payment =new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls=new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setRedirectUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);
        return  payment.create(apiContext);
    }
}
