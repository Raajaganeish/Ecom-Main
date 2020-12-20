package com.Angular.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Angular.CartEntities.ShoppingCart;

public interface ShoppingCartJPA extends JpaRepository<ShoppingCart, Integer> {

}
