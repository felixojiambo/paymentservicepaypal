package com.presta.paymentservice.controller;
import com.presta.paymentservice.service.PaypalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaypalController {
    private final PaypalService paypalService;


    @GetMapping("/home")
    public  String home(){
     return  "index";
    }
    @PostMapping("/payment/create"){
        try {
            String cancelUrl="https://localhost:8080/payment/cancel";
            String successUrl="https://localhost:8080/payment/success";
            Payment payment=paypalService.createPayment(
                    10.0,
                    "KES",
                    "paypal",
                    "sale",
                    "payment for products"
                    cancelUrl,
                    successUrl
            );
            for(Links links:payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return new RedirectView(Links.getHref());
                }
            }
        }catch (PayPalRESTException e) {
            log.error("Error occured: ",e);
        }return new RedirectView("/payment/error");
        }
        @GetMapping("/payment/success")
    public  String paymentSuccess(
            @RequestParam("paymentId") String paymentId,@RequestParam("payerId") String payerId{
                try {
                    Payment payment=paypalService.executePayment(paymentId,payerId);
                  if(payment.getState().equals("approved")){
                      return  "paymentSuccess"
                  }
                }
                catch (PayPalRESTException e){
                    log.error("Error occured: ",e);
                }return  "paymentSuccess"
        }

        )
    }
}
