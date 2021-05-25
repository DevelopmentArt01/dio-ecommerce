package com.ecommerce.checkout.checkout.service;

import com.ecommerce.checkout.checkout.entity.CheckoutEntity;
import com.ecommerce.checkout.checkout.resource.checkout.CheckoutRequest;

import java.util.Optional;

public interface CheckoutService {

    Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest);

    Optional<CheckoutEntity> updateStatus(String statusCode , CheckoutEntity.Status stutus);
}
