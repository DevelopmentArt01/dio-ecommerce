package com.ecommerce.checkout.checkout.resource.checkout;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/checkout")
@RequiredArgsConstructor
public class CheckoutResource {


    private final CheckoutRequest checkoutRequest;


    @PostMapping("/")
    public ResponseEntity<Void> create(){
        return null;
    }

}
