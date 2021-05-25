package com.ecommerce.checkout.checkout.listener;

import com.ecommerce.checkout.checkout.entity.CheckoutEntity;
import com.ecommerce.checkout.checkout.repository.CheckoutRepository;
import com.ecommerce.checkout.checkout.service.CheckoutService;
import com.ecommerce.checkout.checkout.streaming.PaymentPaidSink;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PaymentPaidListener {

    private CheckoutRepository checkoutRepository;

    @StreamListener(PaymentPaidSink.INPUT)
    public void handler(PaymentCreatedEvent paymentCreatedEvent) {
     CheckoutEntity  checkoutEntity = checkoutRepository.findByCode(paymentCreatedEvent.getCheckoutCode().toString()).orElseThrow();
     checkoutEntity.setStatus(CheckoutEntity.Status.APPROVED);

     checkoutRepository.save(checkoutEntity);

    }
}
