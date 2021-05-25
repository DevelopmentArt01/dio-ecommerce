package com.ecommerce.checkout.checkout.repository;

import com.ecommerce.checkout.checkout.entity.CheckoutEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckoutRepository extends CrudRepository<CheckoutEntity, Long>  {


    Optional<CheckoutEntity> findByCode(String code);

}
