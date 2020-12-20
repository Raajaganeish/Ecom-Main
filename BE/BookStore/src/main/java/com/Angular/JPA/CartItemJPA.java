package com.Angular.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Angular.CartEntities.CartItem;
import com.Angular.CartEntities.ShoppingCart;
import java.util.List;

public interface CartItemJPA extends JpaRepository<CartItem, Integer> {
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingcart);

}
