package com.Angular.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Angular.CartEntities.BookToCart;
import com.Angular.CartEntities.CartItem;

public interface BookToCartJPA extends JpaRepository<BookToCart, Integer> {

	BookToCart findByCartItem(CartItem cartItem);
}
