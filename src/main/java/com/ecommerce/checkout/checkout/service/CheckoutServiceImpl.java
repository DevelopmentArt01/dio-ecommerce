package com.ecommerce.checkout.checkout.service;

import com.ecommerce.checkout.checkout.entity.CheckoutEntity;
import com.ecommerce.checkout.checkout.entity.CheckoutItemEntity;
import com.ecommerce.checkout.checkout.entity.ShippingEntity;
import com.ecommerce.checkout.checkout.repository.CheckoutRepository;
import com.ecommerce.checkout.checkout.resource.checkout.CheckoutRequest;
import com.ecommerce.checkout.checkout.streaming.CheckoutCreatedSource;
import com.ecommerce.checkout.checkout.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
@Service
public class CheckoutServiceImpl implements CheckoutService{

    private  CheckoutRepository checkoutRepository;
    private  CheckoutCreatedSource checkoutCreatedSource;
    private  UUIDUtil uuidUtil;


    @Override
    public Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest) {
        log.info("M=create, checkoutRequest={}", checkoutRequest);

        CheckoutEntity checkoutEntity = CheckoutEntity.builder()
                .code(uuidUtil.createUUID().toString())
                .status(CheckoutEntity.Status.CREATED)
                .saveAddress(checkoutRequest.getSaveAddress())
                .saveInformation(checkoutRequest.getSaveInfo())
                .shipping(ShippingEntity.builder()
                        .address(checkoutRequest.getAddress())
                        .complement(checkoutRequest.getComplement())
                        .country(checkoutRequest.getCountry())
                        .state(checkoutRequest.getState())
                        .cep(checkoutRequest.getCeep())
                        .build())
                .build();
        checkoutEntity.setItems(checkoutRequest.getProducts()
                .stream()
                .map(product -> CheckoutItemEntity.builder()
                        .checkout(checkoutEntity)
                        .product(product)
                        .build())
                .collect(Collectors.toList()));
         CheckoutEntity entity = checkoutRepository.save(checkoutEntity);
         CheckoutCreatedEvent checkoutCreatedEvent = CheckoutCreatedEvent.newBuilder()
                .setCheckoutCode(entity.getCode())
                .setStatus(entity.getStatus().name())
                .build();
        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());

        return Optional.empty();


    }

    @Override
    public Optional<CheckoutEntity> updateStatus(String statusCode, CheckoutEntity.Status status) {


        return Optional.empty();


    }
}
