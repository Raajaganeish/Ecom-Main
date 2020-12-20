package com.Angular.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Angular.OrderEntities.Payment;

public interface OrderPaymentJPA extends JpaRepository<Payment, Integer> {

}
