package com.Angular.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Angular.OrderEntities.ShippingAddress;

public interface OrderShippingAddressJPA extends JpaRepository<ShippingAddress, Integer> {

}
