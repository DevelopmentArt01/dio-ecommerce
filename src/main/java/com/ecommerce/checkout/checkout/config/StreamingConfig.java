package com.ecommerce.checkout.checkout.config;


import com.ecommerce.checkout.checkout.streaming.CheckoutCreatedSource;
import com.ecommerce.checkout.checkout.streaming.PaymentPaidSink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value = {CheckoutCreatedSource.class, PaymentPaidSink.class})
public class StreamingConfig {
}
